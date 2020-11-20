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
        Cad_EmpresaDados empresa = (Cad_EmpresaDados) sessao.getAttribute("dados");

        //String CNPJ = empresa.getCNPJ(); // inserir busca da empresa pelo CNPJ para recuperar o ID para inserir os horários na tabela de horarios disponiveis

        String HoraAb = request.getParameter("HoraAb");
        String HoraFh = request.getParameter("HoraFh");
        String Periodo = request.getParameter("periodo");

        boolean horaabValida = HoraAb != null && HoraAb.trim().length() > 0;

        boolean horafhValida = HoraFh != null && HoraFh.trim().length() > 0;

        boolean periodoValido = Periodo != null && Periodo.trim().length() > 0;

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

            request.setAttribute("HoraAb", HoraAb);
            request.setAttribute("HoraFh", HoraFh);
            request.setAttribute("Periodo", Periodo);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Empresa/Form_Empresa_Agendamento.jsp");
            dispatcher.forward(request, response);
            return;
        }

        //INSERIR AS CONVERSÕES E CONTAS DE HORÁRIO
        System.out.println(HoraAb + " " + HoraFh + " " +Periodo );
        try {
            ArrayList<Time> horas;
            

            Cad_EmpresaDados dados = new Cad_EmpresaDados();
            float qtdAgendamentos = dados.agendPorDia(HoraAb, HoraFh, Periodo);
            System.out.println(qtdAgendamentos);
            horas = dados.horariosAgend(HoraAb, HoraFh, qtdAgendamentos);
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
