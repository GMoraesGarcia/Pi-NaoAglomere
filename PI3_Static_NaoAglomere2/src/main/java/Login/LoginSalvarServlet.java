package Login;

import Cad_Usuario.Cad_Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginSalvarServlet", urlPatterns = {"/login-salvar"})
public class LoginSalvarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        LoginDados novoLogin = (LoginDados) sessao.getAttribute("novoLogin");
        sessao.removeAttribute("novoLogin");

        request.setAttribute("novoLogin", novoLogin);
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
        dispacher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String emailStr = request.getParameter("email");
        String senhaStr = request.getParameter("senha");

        //Validação e-mail
        boolean emailValido = (emailStr != null && emailStr.trim().length() > 0);

        //validação senha
        boolean validarSenha = (senhaStr != null && senhaStr.trim().length() <= 8);

        //Tratar erros
        boolean camposValidos = (emailValido && validarSenha);

        if (!camposValidos) {
            //mensagens de erro            
            if (!emailValido) {
                request.setAttribute("emailErro", "E-mail deve ser preenchido ou Válido");
            }
            if (!validarSenha) {
                request.setAttribute("senhaErro", "Senha deve ser preenchido, e sua senha deve conter pelo menos 8 caracters");
            }

            request.setAttribute("email", emailStr);
            request.setAttribute("senha", senhaStr);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        LoginDados novoLogin = new LoginDados();

        novoLogin.setEmail(emailStr);
        novoLogin.setSenha(senhaStr);

        /*request.setAttribute("novo", novoLogin);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
        dispatcher.forward(request, response);*/
        HttpSession sessao = request.getSession();
        sessao.setAttribute("novoLogin", novoLogin);

        response.sendRedirect("login-salvar");
    }

}
