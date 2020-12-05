package Cad_Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gabriel
 */
@WebServlet(name = "Cad_Usuario_Salvar", urlPatterns = {"/salvar-usuario"})
public class Cad_Usuario_Salvar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Cad_Usuario novoUsuario = (Cad_Usuario) sessao.getAttribute("novouser");
        sessao.removeAttribute("novouser");

        request.setAttribute("novouser", novoUsuario);
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
        dispacher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Cad_Usuario novoUsuario = new Cad_Usuario();
        String nomeStr = request.getParameter("nome");
        String cpfStr = request.getParameter("cpf");
        String emailStr = request.getParameter("email");
        String dtNascimentoStr = request.getParameter("dataNascimento");
        String telefoneStr = request.getParameter("telefone");
        String senhaStr = request.getParameter("senha");
        String confSenhaStr = request.getParameter("confirmarSenha");

        //Validação Nome
        boolean nomeValido = nomeStr != null && nomeStr.trim().length() > 0;

        //Validação e-mail
        boolean emailValido = (emailStr != null && emailStr.trim().length() > 0);

        //Validação de data de nascimento
        LocalDate dataNascimento = null;
        if (dtNascimentoStr != null && dtNascimentoStr.trim().length() > 0) {
            dataNascimento = LocalDate.parse(dtNascimentoStr);
        }
        boolean dataNascimentoValida = (dataNascimento != null && dataNascimento.isBefore(LocalDate.now()));

        //Validação CPF
        boolean validaCpf = false;
        if (cpfStr != "" && cpfStr.matches("[0-9]+")) {
            char arrayCpf[] = cpfStr.toCharArray();
            validaCpf = novoUsuario.validaCPF(arrayCpf);
        }

        //Validação do Telefone
        boolean telefoneValido = telefoneStr != null && telefoneStr.trim().length() > 0;
        if (telefoneValido) {
            Pattern telefonePattern = Pattern.compile("(\\([0-9]{2}\\))\\s([9]{1})?([0-9]{4})-([0-9]{4})");
            Matcher telefoneMatcher = telefonePattern.matcher(telefoneStr);
            telefoneValido = telefoneValido && telefoneMatcher.matches();
        }
        //validação senha
        boolean validarSenha = (senhaStr != null && senhaStr.trim().length() >= 8);

        //Senhas Iguais
        boolean validarConfirmaSenha = (confSenhaStr != null && confSenhaStr.equals(senhaStr));

        //Tratar erros
        boolean camposValidos = (nomeValido && emailValido && validaCpf && telefoneValido && dataNascimentoValida);

        if (!camposValidos) {
            //mensagens de erro
            if (!nomeValido) {
                request.setAttribute("nomeErro", "Nome deve ser preenchido ou Válido");
            }
            if (!emailValido) {
                request.setAttribute("emailErro", "E-mail deve ser preenchido ou Válido");
            }
            if (!validaCpf) {
                request.setAttribute("cpfErro", " CPF deve ser preenchido ou Válido");
            }
            if (!telefoneValido) {
                request.setAttribute("telefoneErro", " Telefone deve ser preenchido ou Válido");
            }
            if (!dataNascimentoValida) {
                request.setAttribute("dtNascimentoErro", " Data de Nascimento deve ser preenchido ou Válido");
            }
            if (!validarSenha) {
                request.setAttribute("senhaErro", "Senha deve ser preenchido, e sua senha deve conter pelo menos 8 caracters");
            }
            if (!validarConfirmaSenha) {
                request.setAttribute("ConfirmSenhaErro", "Senhas devem ser iguais");
            }

            request.setAttribute("nome", nomeStr);
            request.setAttribute("cpf", cpfStr);
            request.setAttribute("email", emailStr);
            request.setAttribute("dataNascimento", dtNascimentoStr);
            request.setAttribute("telefone", telefoneStr);
            request.setAttribute("senha", senhaStr);
            request.setAttribute("confirmarSenha", confSenhaStr);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Usuario/Form_Cad_Usuario.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        novoUsuario.setNome(nomeStr);
        novoUsuario.setCpf(cpfStr);
        novoUsuario.setEmail(emailStr);
        novoUsuario.setDataNascimento(LocalDate.parse(dtNascimentoStr));
        novoUsuario.setTelefone(telefoneStr);
        novoUsuario.setSenha(senhaStr);
        novoUsuario.setConfirmarSenha(confSenhaStr);

        UsuarioDAO dao = new UsuarioDAO();
        try {

            dao.addNovoUsuario(novoUsuario);
            request.setAttribute("add", "Cadasreo realizado com sucesso");
            HttpSession sessao = request.getSession();
            sessao.setAttribute("novouser", novoUsuario);

            response.sendRedirect("salvar-usuario");

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}
