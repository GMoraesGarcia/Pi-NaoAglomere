package Login;

import Cad_Empresa.Cad_EmpresaDados;
import Cad_Empresa.EmpresaDao;
import Cad_Usuario.Cad_Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        LoginDao dao = new LoginDao();

        try {
            LoginDados dados = dao.findLogin(emailStr, senhaStr); //procura o login e retorna o tipo de cadastro

            if (dados.getTipo_cadastro().equals("usuário")) { //processo de pesquisa de usuario para o perfil -----começo----

                try {
                    //nome,cpf,email,data_nascimento,telefone 
                    Cad_Usuario user = dao.findUsuario(emailStr, senhaStr);
                    request.setAttribute("user", user);
                    request.setAttribute("dados", dados);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Perfil/Perfil_entrada.jsp");
                    dispatcher.forward(request, response);
                    return;

                } catch (SQLException e) {
                    System.out.println(e);
                }
            } //processo de pesquisa de usuario para o perfil -----FIM----
            
            else if (dados.getTipo_cadastro().equals("empresa")) { //processo de pesquisa de empresa para o perfil -----começo----

                try {
                    //nome,cnpj,email,descricao,telefone,qtd_max,rua,bairro,numero,regras,agendamento
                    Cad_EmpresaDados empresa = dao.findEmpresa(emailStr, senhaStr);
                    request.setAttribute("empresa", empresa);
                    request.setAttribute("dados", dados);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Perfil/Perfil_entrada.jsp");
                    dispatcher.forward(request, response);
                    return;

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            } //processo de pesquisa de empresa para o perfil -----FIM----

            if (dados.getEmail() == null) { //caso email não esteja no banco de dados
                request.setAttribute("Erro", "Email ou Senha inválida");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            if (dados.getEmail().equals(emailStr) && dados.getSenha().equals(senhaStr)) { //email e senha corretos
                request.setAttribute("novoLogin", dados);
                HttpSession sessao = request.getSession();
                sessao.setAttribute("novoLogin", dados);
                response.sendRedirect("Perfil-entrada");

            } else { //caso a senha esteja errada
                request.setAttribute("Erro", "Email ou Senha inválida 2");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
                dispatcher.forward(request, response);
            }
            
        } catch (SQLException e) {

            request.setAttribute("Erro", "Erro no banco de dados");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
            dispatcher.forward(request, response);
        }

    }

}
