/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cad_Empresa;

import DataHelper.DataHelper;
import java.io.IOException;
import java.sql.SQLException;
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

        String horaAb = request.getParameter("HoraAb");
        String horaFh = request.getParameter("HoraFh");
        String horaAt = request.getParameter("HoraAt");

        boolean horaabValida = horaAb != null && horaAb.trim().length() > 0;

        boolean horafhValida = horaFh != null && horaFh.trim().length() > 0;

        boolean horaatValida = horaAt != null && horaAt.trim().length() > 0;

        boolean camposValidos = horaabValida && horafhValida && horaatValida;

        if (!camposValidos) {

            if (!horaabValida) {
                request.setAttribute("horabErro", "hora inválido ou deve ser preenchida");
            }
            if (!horafhValida) {
                request.setAttribute("horafhErro", "hora inválido ou deve ser preenchida");
            }
            if (!horaatValida) {
                request.setAttribute("horaatErro", "duração do atendimento inválida ou deve ser preenchida");
            }

            request.setAttribute("HoraAb", horaAb);
            request.setAttribute("HoraFh", horaFh);
            request.setAttribute("HoraAt", horaAt);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Empresa/Form_Empresa_Horarios.jsp");
            dispatcher.forward(request, response);
            return;
        }
        // inicio dos calculos para disponibilzação do array horarios disponiveis
        DataHelper dataAbertura = new DataHelper(horaAb);
        DataHelper dataFechamento = new DataHelper(horaFh);
        DataHelper dataAtendimento = new DataHelper(horaAt);        

        //loop encadeado para ir adicionando horarios no array da empresa desde a abertura até o fechamento
        for (DataHelper temp = dataAbertura; temp.comparar(dataFechamento.getData()) <= 0;
                temp.adicionarHorasEMinutos(dataAtendimento.getHoras(), dataAtendimento.getMinutos())) {
            //for encadeado caso o numero de pessoas simultaneas seja 1 ou mmais pessoas
            for (int i = 0; i < empresa.getQtd_max(); i++) {
                empresa.addHorario(temp.getAsString());                
            }
        }

        EmpresaDao dao = new EmpresaDao();

        try {
            dao.addHorarios(empresa);

            response.sendRedirect("login");

        } catch (SQLException e) {

            request.setAttribute("Erro", e);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Empresa/Form_Empresa_Horarios.jsp");
            dispatcher.forward(request, response);
        }

    }

}
