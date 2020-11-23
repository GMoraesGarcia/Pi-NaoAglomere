/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cad_Empresa;

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
@WebServlet(name = "Cad_Agendamento_Salvar", urlPatterns = {"/cad-agendamento-salvar"})
public class Cad_Agendamento_Salvar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login/Login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        Cad_Empresadados empresa = (Cad_Empresadados) sessao.getAttribute("dados");

        //String CNPJ = empresa.getCNPJ(); // inserir busca da empresa pelo CNPJ para recuperar o ID para inserir os horários na tabela de horarios disponiveis
        String horaAb = request.getParameter("horaAb");
        String horaFh = request.getParameter("horaFh");
        String periodo = request.getParameter("periodo");

        boolean horaabValida = horaAb != null && horaAb.trim().length() > 0;

        boolean horafhValida = horaFh != null && horaFh.trim().length() > 0;

        boolean periodoValido = periodo != null && periodo.trim().length() > 0;

        boolean camposValidos = horaabValida && horafhValida && periodoValido;

        if (!camposValidos) {

            if (!horaabValida) {
                request.setAttribute("horaabErro", "Nome inválido ou deve ser preenchida");
            }
            if (!horafhValida) {
                request.setAttribute("horafhErro", "Nome inválido ou deve ser preenchida");
            }
            if (!periodoValido) {
                request.setAttribute("periodoErro", "Nome inválido ou deve ser preenchido");
            }

            request.setAttribute("horaAb", horaAb);
            request.setAttribute("horaFh", horaFh);
            request.setAttribute("periodo", periodo);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Empresa/Form_Empresa_Agendamento.jsp");
            dispatcher.forward(request, response);
            return;
        }

        //INSERIR AS CONVERSÕES E CONTAS DE HORÁRIO
        System.out.println(horaAb + " " + horaFh + " " + periodo);
        try {
            ArrayList<Time> horas;

            Cad_Empresadados dados = new Cad_Empresadados();
            float qtdAgendamentos = dados.agendPorDia(horaAb, horaFh, periodo);
            System.out.println(qtdAgendamentos);
            horas = dados.horariosAgend(horaAb, horaFh, qtdAgendamentos);
            for (int i = 0; i < horas.size(); i++) {
                System.out.println(horas.get(i));
            }

            // EmpresaDao dao = new EmpresaDao();
            //inserir dao para adicionar horários na tabela de horários disponiveis
            response.sendRedirect("cad-agendamento-salvar");

        } catch (/*SQL*/Exception e) {
            request.setAttribute("Erro", e);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Empresa/Form_Empresa_Agendamento.jsp");
            dispatcher.forward(request, response);
        }

    }

}
