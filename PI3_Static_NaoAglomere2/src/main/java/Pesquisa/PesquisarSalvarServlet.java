package Pesquisa;

import Cad_Empresa.Cad_Empresa_dados;
import Cad_Empresa.EmpresaDao;
import Cad_Usuario.Cad_Usuario;
import Cad_Usuario.UsuarioDAO;
import Login.LoginDados;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PesquisarSalvarServlet", urlPatterns = {"/pesquisar-Salvar"})
public class PesquisarSalvarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        PesquisarDados novaPesquisa = (PesquisarDados) sessao.getAttribute("novaPesquisa");
        sessao.removeAttribute("novaPesquisa");

        request.setAttribute("novaPesquisa", novaPesquisa);
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/Pesquisar/Pesquisar.jsp");
        dispacher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pesquisa = request.getParameter("pesquisa");

        PesquisaDao dao = new PesquisaDao();
        UsuarioDAO daoUser = new UsuarioDAO();
        CodigoDAO daoCod = new CodigoDAO();
        CodigoDados dados = new CodigoDados();

        try {

            PesquisarDados busca = dao.findEstabelecimento(pesquisa);
            request.setAttribute("busca", busca);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Pesquisar/Pesquisar.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            request.setAttribute("Erro", "Erro no banco de dados");
            System.out.println(e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Pesquisar/Pesquisar.jsp");
            dispatcher.forward(request, response);
        }

        String emp = request.getParameter("id_Emp");
        HttpSession sessao = request.getSession();
        Cad_Usuario user = (Cad_Usuario) sessao.getAttribute("user");

        boolean idempresa = emp != null;
        boolean validaUser = user != null;

        boolean camposValidos = idempresa && validaUser;
        if (!camposValidos) {
            if (!idempresa) {
                request.setAttribute("erroEmp", "codigo da empresa não encontado");
            }
            if (!validaUser) {
                request.setAttribute("erroUser", "Usuário não encontrado");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Pesquisar/Pesquisar.jsp");
            dispatcher.forward(request, response);
        }

        if (emp != null) {

            String userCpf = user.getCpf();
            try {

                int userId = daoUser.findByCPF(userCpf);
                String codigo = daoCod.findCodigo(Integer.parseInt(emp));

                dados.setCodigo(codigo);
                dados.setIdUsuario(userId);

                daoCod.addCodigoUsuario(dados);

            } catch (SQLException e) {
                System.out.println(e);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Pesquisar/Pesquisar.jsp");
                dispatcher.forward(request, response);
            }

        }

    }

}
