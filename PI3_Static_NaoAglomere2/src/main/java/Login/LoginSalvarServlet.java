package Login;

import Cad_Empresa.Cad_EmpresaDados;
import Cad_Empresa.EmpresaDao;
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

@WebServlet(name = "LoginSalvarServlet", urlPatterns = {"/Perfil-entrada"})
public class LoginSalvarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        LoginDados novoLogin = (LoginDados) sessao.getAttribute("novoLogin");
        sessao.removeAttribute("novoLogin");

        request.setAttribute("novoLogin", novoLogin);
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/Perfil/Perfil_entrada.jsp");
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
        
        
        //proceso de pesquisa de usuario para o perfil -----começo----
        LoginDao dao = new LoginDao();
        try{
            //nome,cpf,email,data_nascimento,telefone 
            Cad_Usuario user = dao.findUser(emailStr, senhaStr);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Perfil/Perfil_entrada.jsp");
            dispatcher.forward(request, response);
            return;
            
        }catch (SQLException e){
            System.out.println(e);
        }
        //proceso de pesquisa de usuario para o perfil -----FIM----

     
        
        
        
        
        try {
            LoginDados dados = dao.findByEmail(emailStr);

            if (dados.getEmail() == null) {
                request.setAttribute("Erro", "Email ou Senha inválida");
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            if (dados.getEmail().equals(emailStr) && dados.getSenha().equals(senhaStr)) {
                request.setAttribute("novoLogin", dados);
                HttpSession sessao = request.getSession();
                sessao.setAttribute("novoLogin", dados);
                response.sendRedirect("Perfil-entrada");
            } else {
                request.setAttribute("Erro", "Email ou Senha inválida 2");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
                dispatcher.forward(request, response);
                return;
            }

        } catch (SQLException e) {

            request.setAttribute("Erro", "Erro no banco de dados");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
            dispatcher.forward(request, response);
            return;
        }

    }

}
