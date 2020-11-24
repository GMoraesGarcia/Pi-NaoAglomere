/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cad_Empresa;

import DataHelper.DataHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;
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
@WebServlet(name = "Cad_Agendamento_Salvar", urlPatterns = {"/cad-horario-salvar"})
public class Cad_HorarioSalvar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        HttpSession sessao = request.getSession();
        Cad_Empresa_dados empresa = (Cad_Empresa_dados) sessao.getAttribute("cadastroE");

        //String CNPJ = empresa.getCNPJ(); // inserir busca da empresa pelo CNPJ para recuperar o ID para inserir os horários na tabela de horarios disponiveis
        String horaAb = request.getParameter("HoraAb");
        String horaFh = request.getParameter("HoraFh");

        boolean horaabValida = horaAb != null && horaAb.trim().length() > 0;

        boolean horafhValida = horaFh != null && horaFh.trim().length() > 0;

        boolean camposValidos = horaabValida && horafhValida;

        if (!camposValidos) {

            if (!horaabValida) {
                request.setAttribute("horaabErro", "hora inválido ou deve ser preenchida");
            }
            if (!horafhValida) {
                request.setAttribute("horafhErro", "hora inválido ou deve ser preenchida");
            }

            request.setAttribute("horaAb", horaAb);
            request.setAttribute("horaFh", horaFh);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Empresa/Form_Empresa_Agendamento.jsp");
            dispatcher.forward(request, response);
            return;
        }

        EmpresaDao dao = new EmpresaDao();

        try {
            dao.addHorarios(empresa);

            request.setAttribute("horarios", empresa);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
            dispatcher.forward(request, response);

        } catch (/*SQL*/Exception e) {

            request.setAttribute("Erro", e);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Empresa/Form_Empresa_Horarios.jsp");
            dispatcher.forward(request, response);
        }

    }

}
