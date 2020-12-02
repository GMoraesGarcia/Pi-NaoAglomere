/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agendamento;

import Cad_Empresa.Cad_Empresa_dados;
import Login.LoginDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
@WebServlet(name = "Form_Agendamento_Abrir", urlPatterns = {"/agendamento"})
public class Form_Agendamento_Abrir extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        Cad_Empresa_dados empresa = new Cad_Empresa_dados();
        LoginDao dao = new LoginDao();
        try {
            empresa.setHorariosDisponiveis(dao.findHorarios(Integer.parseInt(request.getParameter("id"))));
        } catch (SQLException ex) {
            Logger.getLogger(Form_Agendamento_Abrir.class.getName()).log(Level.SEVERE, null, ex);
        }
        empresa.setEmpresa_id(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("empresa", empresa);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Agendamento/Form_Agendamento.jsp");

        dispatcher.forward(request, response);
    }

}
