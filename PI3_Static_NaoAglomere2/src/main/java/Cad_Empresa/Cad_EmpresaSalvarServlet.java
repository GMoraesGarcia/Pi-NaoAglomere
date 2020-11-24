/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cad_Empresa;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author leona
 */
@WebServlet(name = "Cad_EmpresaSalvarServlet", urlPatterns = {"/cad-empresa-salvar"})
@MultipartConfig(maxFileSize = 20848820) // 5MB == 20848820 bytes == 5 * 1024 * 1024
public class Cad_EmpresaSalvarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        Cad_Empresa_dados empresa_dados = (Cad_Empresa_dados) sessao.getAttribute("dados");
        sessao.removeAttribute("dados");

        request.setAttribute("dados", empresa_dados);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nome_empresa = request.getParameter("nome_empresa");
        String cnpj = request.getParameter("CNPJ");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String confirmasenha = request.getParameter("confirmasenha");
        String telefone = request.getParameter("telefone");
        String descricao = request.getParameter("descricao");
        String rua = request.getParameter("rua");
        String numeroStr = request.getParameter("numero_rua");
        String bairro = request.getParameter("bairro");
        String qtd_pessoasStr = request.getParameter("qtd_pessoas");
        String regras = request.getParameter("regras");
        String agendamento = request.getParameter("agendamento");
        Part arquivo = request.getPart("foto");
        String check = request.getParameter("check");

        String caminho = null;
        InputStream conteudoArquivo = null;
        Path destino = null;

        if (!Paths.get(arquivo.getSubmittedFileName()).getFileName().toString().equals("")) {
            String nomeArquivo = Paths.get(arquivo.getSubmittedFileName()).getFileName().toString();
            String diretorioDestino = "C:/PI-FOTOS";
            conteudoArquivo = arquivo.getInputStream();
            destino = Paths.get(diretorioDestino + "/" + nomeArquivo);
            caminho = "/PI-FOTOS/" + nomeArquivo;
        }

        //Validação leitura dos termos
        boolean checkValido = false;
        if (check != null) {
            if (check.equals("on")) {
                checkValido = true;
            }
        }

        //Validação Nome
        boolean nomeValido = nome_empresa != null && nome_empresa.trim().length() > 0;

        //Validação email
        boolean emailValido = (email != null && email.trim().length() > 0);
        if (emailValido) {
            Pattern emailPattern = Pattern.compile("^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?$");
            Matcher emailMatcher = emailPattern.matcher(email);
            emailValido = emailValido && emailMatcher.matches();
        }

        //validação senha
        boolean senhaValida = (senha != null && senha.trim().length() >= 8);

        //Senhas Iguais
        boolean ConfirmaSenhaValida = (confirmasenha != null && confirmasenha.equals(senha));

        //Validação CNPJ
        boolean validaCNPJ = isCNPJ(cnpj);

        //Validação telefone 
        boolean telefoneValido = telefone != null && telefone.trim().length() > 0;
        if (telefoneValido) {
            Pattern telefonePattern = Pattern.compile("(\\([0-9]{2}\\))\\s([9]{1})?([0-9]{4})-([0-9]{4})");
            Matcher telefoneMatcher = telefonePattern.matcher(telefone);
            telefoneValido = telefoneValido && telefoneMatcher.matches();
        }

        //Validação descricao
        boolean descricaoValida = descricao != null && descricao.trim().length() > 0;

        //Validação rua
        boolean ruaValida = rua != null && rua.trim().length() > 0;

        //Validação número
        Integer numero = null;
        boolean numeroValido = numeroStr != null && numeroStr.trim().length() > 0;
        if (numeroValido) {
            numero = Integer.parseInt(numeroStr);
        }

        //Validação bairro
        boolean bairroValido = bairro != null && bairro.trim().length() > 0;

        //Validação quantidade de pessoas
        Integer qtdPessoas = null;
        boolean qtdValida = qtd_pessoasStr != null && qtd_pessoasStr.trim().length() > 0;
        if (qtdValida) {
            qtdPessoas = Integer.parseInt(qtd_pessoasStr);
        }

        //Validação regras
        boolean regrasValidas = regras != null && regras.trim().length() > 0;

        //Validação Nome
        boolean agendamentoValido = agendamento != null && agendamento.trim().length() > 0;

        boolean camposValidos = nomeValido && emailValido && validaCNPJ && telefoneValido && ruaValida
                && numeroValido && bairroValido && descricaoValida && qtdValida && regrasValidas && senhaValida
                && ConfirmaSenhaValida && agendamentoValido && checkValido;

        if (!camposValidos) {

            if (!nomeValido) {
                request.setAttribute("nomeErro", "Nome inválido ou deve ser preenchido");
            }
            if (!emailValido) {
                request.setAttribute("emailErro", "Email inválido ou deve ser preenchido");
            }
            if (!validaCNPJ) {
                request.setAttribute("cnpjErro", "CPNJ inválido ou deve ser preenchido");
            }
            if (!senhaValida) {
                request.setAttribute("senhaErro", "Senha inválida ou deve ser preenchida");
            }
            if (!ConfirmaSenhaValida) {
                request.setAttribute("confirmaErro", "Senhas devem ser iguais");
            }
            if (!telefoneValido) {
                request.setAttribute("telefoneErro", "Telefone inválido ou deve ser preenchido");
            }
            if (!ruaValida) {
                request.setAttribute("ruaErro", "Rua inválida ou deve ser preenchida");
            }
            if (!numeroValido) {
                request.setAttribute("numeroErro", "Número inválido ou deve ser preenchido");
            }
            if (!bairroValido) {
                request.setAttribute("bairroErro", "Bairro inválido ou deve ser preenchido");
            }
            if (!descricaoValida) {
                request.setAttribute("descricaoErro", "Descrição deve ser selecionada");
            }
            if (!qtdValida) {
                request.setAttribute("qtdErro", "Quantidade inválida ou deve ser preenchida");
            }
            if (!regrasValidas) {
                request.setAttribute("regrasErro", "Regras inválidas ou devem ser preenchidas");
            }
            if (!agendamentoValido) {
                request.setAttribute("agendamentoErro", "Uma opção deve ser selecionada");
            }
            if (!checkValido) {
                request.setAttribute("checkErro", "Opção deve ser selecionada");
            }

            request.setAttribute("nome_empresa", nome_empresa);
            request.setAttribute("CNPJ", cnpj);
            request.setAttribute("email", email);
            request.setAttribute("telefone", telefone);
            request.setAttribute("descricao", descricao);
            request.setAttribute("rua", rua);
            request.setAttribute("numero_rua", numeroStr);
            request.setAttribute("bairro", bairro);
            request.setAttribute("qtd_pessoas", qtd_pessoasStr);
            request.setAttribute("regras", regras);
            request.setAttribute("agendamento", agendamento);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Empresa/Form_Cad_Empresa.jsp");
            dispatcher.forward(request, response);
            return;

        }

        Cad_Empresa_dados empresa_dados = new Cad_Empresa_dados();

        empresa_dados.setNome_empresa(nome_empresa);
        empresa_dados.setCnpj(cnpj);
        empresa_dados.setEmail(email);
        empresa_dados.setSenha(senha);
        empresa_dados.setTelefone(telefone);
        empresa_dados.setDescricao(descricao);
        empresa_dados.setRua(rua);
        empresa_dados.setNumero_rua(numero);
        empresa_dados.setBairro(bairro);
        empresa_dados.setQtd_max(qtdPessoas);
        empresa_dados.setRegras(regras);
        empresa_dados.setAgendamento(agendamento);
        if (!Paths.get(arquivo.getSubmittedFileName()).getFileName().toString().equals("")) {
            empresa_dados.setFoto(caminho);
        }

        EmpresaDao dao = new EmpresaDao();

        try {
            dao.addNew(empresa_dados);

            if (!Paths.get(arquivo.getSubmittedFileName()).getFileName().toString().equals("")) {
                Files.copy(conteudoArquivo, destino);

            }
            request.setAttribute("cadastroE", empresa_dados);

            request.setAttribute("dados", empresa_dados);

            HttpSession sessao = request.getSession();
            sessao.setAttribute("cadastroE", empresa_dados);

            System.out.println(empresa_dados.getAgendamento());

            if (empresa_dados.getAgendamento().equals("Sim")) {
                response.sendRedirect("cad-horario-abrir");
            } else {
                response.sendRedirect("cad-empresa-salvar");
            }

        } catch (SQLException e) {
            System.out.println(e);
            request.setAttribute("Erro", "Erro no banco de dados");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Empresa/Form_Cad_Empresa.jsp");
            dispatcher.forward(request, response);
        }

    }

    public static boolean isCNPJ(String CNPJ) { //VALIDAR CNPJ REAL

        CNPJ = removeCaracteresEspeciais(CNPJ);

        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                || (CNPJ.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        // "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // converte o i-ésimo caractere do CNPJ em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static String removeCaracteresEspeciais(String doc) {
        if (doc.contains(".")) {
            doc = doc.replace(".", "");
        }
        if (doc.contains("-")) {
            doc = doc.replace("-", "");
        }
        if (doc.contains("/")) {
            doc = doc.replace("/", "");
        }
        return doc;
    }
}
