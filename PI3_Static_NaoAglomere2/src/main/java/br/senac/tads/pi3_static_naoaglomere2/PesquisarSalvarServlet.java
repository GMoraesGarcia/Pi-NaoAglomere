package br.senac.tads.pi3_static_naoaglomere2;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PesquisarSalvarServlet", urlPatterns = {"/pesquisar-Salvar"})
public class PesquisarSalvarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String pesquisa = request.getParameter("pesquisa");
        
        PesquisarDados dados = new PesquisarDados();
        
        dados.setPesquisa(pesquisa);
        
        request.setAttribute("dados", dados);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/PesquisarResultado.jsp");
        dispatcher.forward(request, response);
    }

}
