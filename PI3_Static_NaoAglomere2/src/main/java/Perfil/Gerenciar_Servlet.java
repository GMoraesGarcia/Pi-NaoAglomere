/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Perfil;

import Cad_Empresa.Cad_Empresa_dados;
import Cad_Usuario.Cad_Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author leona
 */
@WebServlet(name = "Gerenciar_Servlet", urlPatterns = {"/gerenciar-abrir"})
public class Gerenciar_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();

        sessao.getAttribute("login");

        Cad_Usuario usuario = (Cad_Usuario) sessao.getAttribute("user");
        Cad_Empresa_dados empresa = (Cad_Empresa_dados) sessao.getAttribute("empresa");

        if (usuario != null) {

            GerenciarDAO dao = new GerenciarDAO();

            try {

                ArrayList agendamentos = dao.User(usuario.getCpf());
                request.setAttribute("agendamentos", agendamentos);

            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }
        if (empresa != null) {

            GerenciarDAO dao = new GerenciarDAO();

            try {

                ArrayList agendamentos = dao.Empresa(empresa.getCnpj());

                request.setAttribute("agendamentos", agendamentos);

            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Perfil/Gerenciar.jsp");
        dispatcher.forward(request, response);
    }
}
