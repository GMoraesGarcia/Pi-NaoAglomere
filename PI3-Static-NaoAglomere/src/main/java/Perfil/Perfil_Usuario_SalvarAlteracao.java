package Perfil;

import Cad_Empresa.Cad_Empresa_dados;
import Cad_Empresa.EmpresaDao;
import Cad_Usuario.Cad_Usuario;
import Cad_Usuario.UsuarioDAO;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
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
 * @author Gabriel
 */
@WebServlet(name = "Perfil_Usuario_SalvarAlteracao", urlPatterns = {"/perfil-alterado"})
@MultipartConfig(maxFileSize = 20848820) // 5MB == 20848820 bytes == 5 * 1024 * 1024
public class Perfil_Usuario_SalvarAlteracao extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        Cad_Empresa_dados empresa_dados = (Cad_Empresa_dados) sessao.getAttribute("empresa");
        request.getAttribute("sucesso");

        request.setAttribute("empresa", empresa_dados);
        request.setAttribute("sucesso", "Alterado com sucesso");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Perfil/Perfil_entrada.jsp");
        dispatcher.forward(request, response);

        Cad_Usuario novoUsuario = (Cad_Usuario) sessao.getAttribute("novouser");
        sessao.removeAttribute("novouser");

        request.setAttribute("novouser", novoUsuario);
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/Perfil/Perfil_entrada.jsp");
        dispacher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //ALTERA????O DE DADOS DA EMPRESA ------COME??O------
        request.setCharacterEncoding("UTF-8");

        String nome_empresa = request.getParameter("nome_empresa");
        String cnpj = request.getParameter("CNPJ");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String descricao = request.getParameter("descricao");
        String rua = request.getParameter("rua");
        String numeroStr = request.getParameter("numero_rua");
        String bairro = request.getParameter("bairro");
        String qtd_pessoasStr = request.getParameter("qtd_pessoas");
        String regras = request.getParameter("regras");
        String agendamento = request.getParameter("agendamento");
        Part arquivo = request.getPart("foto");

        String caminho = null;
        InputStream conteudoArquivo = null;
        Path destino = null;

        if (arquivo != null) {
            if (!Paths.get(arquivo.getSubmittedFileName()).getFileName().toString().equals("")) {
                String nomeArquivo = Paths.get(arquivo.getSubmittedFileName()).getFileName().toString();
                String diretorioDestino = "C:/PI-FOTOS";
                conteudoArquivo = arquivo.getInputStream();
                destino = Paths.get(diretorioDestino + "/" + nomeArquivo);
                caminho = "/PI-FOTOS/" + nomeArquivo;
            }
        }
        if (cnpj != null) {

            //Valida????o Nome
            boolean nomeValido = nome_empresa != null && nome_empresa.trim().length() > 0;

            //Valida????o email
            boolean emailValido = (email != null && email.trim().length() > 0);
            if (emailValido) {
                Pattern emailPattern = Pattern.compile("^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?$");
                Matcher emailMatcher = emailPattern.matcher(email);
                emailValido = emailValido && emailMatcher.matches();
            }

            //Valida????o CNPJ
            boolean validaCNPJ = cnpj != null && cnpj.trim().length() > 0;

            //Valida????o telefone 
            boolean telefoneValido = telefone != null && telefone.trim().length() > 0;
            if (telefoneValido) {
                Pattern telefonePattern = Pattern.compile("(\\([0-9]{2}\\))\\s([9]{1})?([0-9]{4})-([0-9]{4})");
                Matcher telefoneMatcher = telefonePattern.matcher(telefone);
                telefoneValido = telefoneValido && telefoneMatcher.matches();
            }

            //Valida????o descricao
            boolean descricaoValida = descricao != null && descricao.trim().length() > 0;

            //Valida????o rua
            boolean ruaValida = rua != null && rua.trim().length() > 0;

            //Valida????o n??mero
            Integer numero = null;
            boolean numeroValido = numeroStr != null && numeroStr.trim().length() > 0;
            if (numeroValido) {
                numero = Integer.parseInt(numeroStr);
            }

            //Valida????o bairro
            boolean bairroValido = bairro != null && bairro.trim().length() > 0;

            //Valida????o quantidade de pessoas
            Integer qtdPessoas = null;
            boolean qtdValida = qtd_pessoasStr != null && qtd_pessoasStr.trim().length() > 0;
            if (qtdValida) {
                qtdPessoas = Integer.parseInt(qtd_pessoasStr);
            }

            //Valida????o regras
            boolean regrasValidas = regras != null && regras.trim().length() > 0;

            //Valida????o Nome
            boolean agendamentoValido = agendamento != null && agendamento.trim().length() > 0;

            boolean camposValidos = nomeValido && emailValido && validaCNPJ && telefoneValido && ruaValida
                    && numeroValido && bairroValido && descricaoValida && qtdValida && regrasValidas && agendamentoValido;

            if (!camposValidos) {

                if (!nomeValido) {
                    request.setAttribute("nomeErro", "Nome inv??lido ou deve ser preenchido");
                }
                if (!emailValido) {
                    request.setAttribute("emailErro", "Email inv??lido ou deve ser preenchido");
                }
                if (!validaCNPJ) {
                    request.setAttribute("cnpjErro", "CPNJ inv??lido ou deve ser preenchido");
                }
                if (!telefoneValido) {
                    request.setAttribute("telefoneErro", "Telefone inv??lido ou deve ser preenchido");
                }
                if (!ruaValida) {
                    request.setAttribute("ruaErro", "Rua inv??lida ou deve ser preenchida");
                }
                if (!numeroValido) {
                    request.setAttribute("numeroErro", "N??mero inv??lido ou deve ser preenchido");
                }
                if (!bairroValido) {
                    request.setAttribute("bairroErro", "Bairro inv??lido ou deve ser preenchido");
                }
                if (!descricaoValida) {
                    request.setAttribute("descricaoErro", "Descri????o deve ser selecionada");
                }
                if (!qtdValida) {
                    request.setAttribute("qtdErro", "Quantidade inv??lida ou deve ser preenchida");
                }
                if (!regrasValidas) {
                    request.setAttribute("regrasErro", "Regras inv??lidas ou devem ser preenchidas");
                }
                if (!agendamentoValido) {
                    request.setAttribute("agendamentoErro", "Uma op????o deve ser selecionada");
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

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Perfil/Perfil_entrada.jsp");
                dispatcher.forward(request, response);
                return;

            }

            Cad_Empresa_dados empresa_dados = new Cad_Empresa_dados();

            empresa_dados.setNome_empresa(nome_empresa);
            empresa_dados.setCnpj(cnpj);
            empresa_dados.setEmail(email);
            empresa_dados.setTelefone(telefone);
            empresa_dados.setDescricao(descricao);
            empresa_dados.setRua(rua);
            empresa_dados.setNumero_rua(numero);
            empresa_dados.setBairro(bairro);
            empresa_dados.setQtd_max(qtdPessoas);
            empresa_dados.setRegras(regras);
            empresa_dados.setAgendamento(agendamento);
            if (arquivo != null) {
                if (!Paths.get(arquivo.getSubmittedFileName()).getFileName().toString().equals("")) {
                    empresa_dados.setFoto(caminho);
                }
            }
            EmpresaDao dao = new EmpresaDao();

            try {

                dao.update(empresa_dados);

                if (arquivo != null) {
                    if (!Paths.get(arquivo.getSubmittedFileName()).getFileName().toString().equals("")) {

                        dao.updateImage(empresa_dados);

                        Files.copy(conteudoArquivo, destino);
                    }
                }
                request.setAttribute("empresa", empresa_dados);

                //Pegar os dados atualizados do banco da empresa
                Cad_Empresa_dados dadosEmpresa = dao.findEmpresa(empresa_dados.getCnpj());

                HttpSession sessao = request.getSession();
                sessao.setAttribute("empresa", dadosEmpresa);

                response.sendRedirect("perfil-alterado");

            } catch (SQLException e) {
                System.out.println(e);
                request.setAttribute("Erro", "Erro no banco de dados");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Perfil/Perfil_entrada.jsp");
                dispatcher.forward(request, response);
            }
            //ALTERA????O DE DADOS DA EMPRESA ------FIM-------
        } else {
            //ALTERA????O DE DADOS DE USU??RIO -------COME??O--------
            request.setCharacterEncoding("UTF-8");

            String nomeStr = request.getParameter("nome");
            String cpfStr = request.getParameter("cpf");
            String emailStr = request.getParameter("email");
            String dtNascimentoStr = request.getParameter("dataNascimento");
            String telefoneStr = request.getParameter("telefone");

            //Valida????o Nome
            boolean nomeValidoUser = nomeStr != null && nomeStr.trim().length() > 0;

            //Valida????o e-mail
            boolean emailValidoUser = (emailStr != null && emailStr.trim().length() > 0);

            //Valida????o de data de nascimento
            LocalDate dataNascimento = null;
            if (dtNascimentoStr != null && dtNascimentoStr.trim().length() > 0) {
                dataNascimento = LocalDate.parse(dtNascimentoStr);
            }
            boolean dataNascimentoValida = (dataNascimento != null && dataNascimento.isBefore(LocalDate.now()));

            boolean telefoneValidoUser = telefoneStr != null && telefoneStr.trim().length() > 0;

            boolean camposValidosUser = (nomeValidoUser && emailValidoUser && telefoneValidoUser && dataNascimentoValida);

            if (!camposValidosUser) {
                //mensagens de erro
                if (!nomeValidoUser) {
                    request.setAttribute("nomeErro", "Nome deve ser preenchido ou V??lido");
                }
                if (!emailValidoUser) {
                    request.setAttribute("emailErro", "E-mail deve ser preenchido ou V??lido");
                }

                if (!telefoneValidoUser) {
                    request.setAttribute("telefoneErro", " Telefone deve ser preenchido ou V??lido");
                }
                if (!dataNascimentoValida) {
                    request.setAttribute("dtNascimentoErro", " Data de Nascimento deve ser preenchido ou V??lido");
                }

                request.setAttribute("nome", nomeStr);
                request.setAttribute("cpf", cpfStr);
                request.setAttribute("email", emailStr);
                request.setAttribute("dataNascimento", dtNascimentoStr);
                request.setAttribute("telefone", telefoneStr);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Perfil/Perfil_entrada.jsp");
                dispatcher.forward(request, response);
                return;
            }

            Cad_Usuario user = new Cad_Usuario();

            user.setNome(nomeStr);
            user.setCpf(cpfStr);
            user.setEmail(emailStr);
            user.setDataNascimento(LocalDate.parse(dtNascimentoStr));
            user.setTelefone(telefoneStr);

            UsuarioDAO daoUser = new UsuarioDAO();
            try {

                daoUser.Update(user);
                request.setAttribute("add", "altera????o realizada com sucesso");
                HttpSession sessao = request.getSession();
                sessao.setAttribute("novouser", user);

                response.sendRedirect("perfil-alterado");

            } catch (SQLException e) {
                request.setAttribute("Erro", "Erro no banco de dados");
                System.out.println(e);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Perfil/Perfil_entrada.jsp");
                dispatcher.forward(request, response);
            }

        }
    }

}
