/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agendamento;

import Cad_Usuario.Cad_Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
@WebServlet(name = "Agendamento_Salvar", urlPatterns = {"/agendamento-salvar"})
public class Agendamento_Salvar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Agendamento a = (Agendamento) sessao.getAttribute("a");
        sessao.removeAttribute("a");

        request.setAttribute("a", a);
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Usuario/Form_Saida.jsp");
        dispacher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        AgendamentoDAO dao = new AgendamentoDAO();
        Agendamento a = new Agendamento();

        String idStr = request.getParameter("id");
        String nomeStr = request.getParameter("nome");
        String emailStr = request.getParameter("email");
        String telefoneStr = request.getParameter("telefone");
        String dataStr = request.getParameter("data");
        String horaStr = request.getParameter("horario");

        //Validação Nome
        boolean nomeValido = nomeStr != null && nomeStr.trim().length() > 0;

        //Validação empresa cadastrada
        //Validação e-mail
        boolean emailValido = (emailStr != null && emailStr.trim().length() > 0);

        //Validação do Telefone
        boolean telefoneValido = telefoneStr != null && telefoneStr.trim().length() > 0;
        /* if (telefoneValido) {
            Pattern telefonePattern = Pattern.compile("(\\([0-9]{2}\\))\\s([9]{1})?([0-9]{4})-([0-9]{4})");
            Matcher telefoneMatcher = telefonePattern.matcher(telefoneStr);
            telefoneValido = telefoneValido && telefoneMatcher.matches();
        }*/

        //Validação de data de agendamento
        LocalDate dataAgendamento = null;
        if (dataStr != null && dataStr.trim().length() > 0) {
            dataAgendamento = LocalDate.parse(dataStr);
        }
        boolean dataAgendamentoValida = (dataAgendamento != null);

        //Validação horario
        boolean horarioValido = (horaStr != null);

        boolean camposValidos = (nomeValido && emailValido && telefoneValido && dataAgendamentoValida && horarioValido);

        if (!camposValidos) {
            //mensagens de erro
            if (!nomeValido) {
                request.setAttribute("nomeErro", "Nome deve ser preenchido e Válido");
            }
            if (!emailValido) {
                request.setAttribute("emailErro", "E-mail deve ser preenchido e/ou Válido");
            }
            if (!horarioValido) {
                request.setAttribute("horarioErro", " Campo com horário deve ser preeenchido");
            }
            if (!telefoneValido) {
                request.setAttribute("telefoneErro", " Telefone deve ser preenchido ou Válido");
            }
            if (!dataAgendamentoValida) {
                request.setAttribute("dataAgendamentoErro", " Data de Agendamento deve ser preenchido ou Válido");
            }

            request.setAttribute("nome", nomeStr);
            request.setAttribute("email", emailStr);
            request.setAttribute("horario", horaStr);
            request.setAttribute("dataAgendamento", dataStr);
            request.setAttribute("telefone", telefoneStr);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Agendamento/Form_Agendamento.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            int num = dao.findByEmail(emailStr);

            a.setIdEmpresa(Integer.parseInt(idStr));
            a.setIdUsuario(num);
            a.setEmail(emailStr);
            a.setTelefone(telefoneStr);
            a.setData(LocalDate.parse(dataStr));
            a.setHora(horaStr);

            dao.addAgendamento(a);

            HttpSession sessao = request.getSession();
            sessao.setAttribute("dados", a);

            response.sendRedirect("agendamento-salvar");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
