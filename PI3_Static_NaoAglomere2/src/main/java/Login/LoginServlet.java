package Login;

import Cad_Empresa.Cad_Empresadados;
import Cad_Usuario.Cad_Usuario;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginSalvarServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp").forward(request, response);
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
        boolean validarSenha = (senhaStr != null && senhaStr.trim().length() >= 8);

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

        LoginDao dao = new LoginDao();

        try {

            LoginDados login = dao.findLogin(emailStr, senhaStr); //procura o login e retorna o tipo de cadastro

            if (login.getEmail() == null) { //caso email não esteja no banco de dados
                request.setAttribute("Erro", "Email ou Senha inválida");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (!login.validarSenha(senhaStr)) { //caso a senha esteja errada
                request.setAttribute("Erro", "Email ou Senha inválida 2");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
                dispatcher.forward(request, response);
            }

            if (login.getTipo_cadastro().equals("usuário")) { //processo de pesquisa de usuario para o perfil -----começo----

                try {
                    //nome,cpf,email,data_nascimento,telefone 
                    Cad_Usuario user = dao.findUsuario(emailStr, senhaStr);
                    HttpSession sessao = request.getSession();
                    sessao.setAttribute("user", user);
                    sessao.setAttribute("login", login);
                    response.sendRedirect(request.getContextPath() + "/Perfil-usuario");

                } catch (SQLException e) {
                    System.out.println(e);
                }
            } //processo de pesquisa de usuario para o perfil -----FIM----
            else if (login.getTipo_cadastro().equals("empresa")) { //processo de pesquisa de empresa para o perfil -----começo----

                try {
                    //nome,cnpj,email,descricao,telefone,qtd_max,rua,bairro,numero,regras,agendamento
                    Cad_Empresadados empresa = dao.findEmpresa(emailStr, senhaStr);
                    HttpSession sessao = request.getSession();
                    sessao.setAttribute("empresa", empresa);
                    sessao.setAttribute("login", login);
                    response.sendRedirect(request.getContextPath() + "/Perfil-usuario");
                    return;

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            } //processo de pesquisa de empresa para o perfil -----FIM----

        } catch (SQLException e) {

            request.setAttribute("Erro", "Erro no banco de dados");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
            dispatcher.forward(request, response);
        }

    }

}
