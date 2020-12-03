package Agendamento;

import Cad_Empresa.Cad_Empresa_dados;
import Login.LoginDao;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        request.setAttribute("agendamento", "Agendamento feito com sucesso!");
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/Home/Home.jsp");
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

        //Validação de data de agendamento
        boolean dataAgendamentoValida = validarData(dataStr);

        //Validação horario
        boolean horarioValido = validarHoraMinSeg(horaStr);

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
                request.setAttribute("horarioErro", "Campo com horário deve ser preeenchido");
            }
            if (!telefoneValido) {
                request.setAttribute("telefoneErro", "Telefone deve ser preenchido ou Válido");
            }
            if (!dataAgendamentoValida) {
                request.setAttribute("dataAgendamentoErro", "Data de Agendamento deve ser preenchido ou Válido");
            }

            request.setAttribute("nome", nomeStr);
            request.setAttribute("email", emailStr);
            request.setAttribute("horario", horaStr);
            request.setAttribute("dataAgendamento", dataStr);
            request.setAttribute("telefone", telefoneStr);

            LoginDao daoLogin = new LoginDao();
            Cad_Empresa_dados empresa = new Cad_Empresa_dados();
            try {
                empresa.setHorariosDisponiveis(daoLogin.findHorarios(Integer.parseInt(request.getParameter("id"))));
            } catch (SQLException ex) {
                Logger.getLogger(Form_Agendamento_Abrir.class.getName()).log(Level.SEVERE, null, ex);
            }
            empresa.setEmpresa_id(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("empresa", empresa);
            request.setAttribute("id", request.getParameter("id"));

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

    public static boolean validarData(String data) {
        try {
            //dd = dia, MM = mes, yyyy = ano
            //o "M" dessa String = maiusculo porque "m" minusculo = minutos
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");            
            //converter a String em um objeto do tipo date, se funcionar
            //sua data é valida
            sdf.parse(data);
            //se nada deu errado retorna true (verdadeiro)
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    public static boolean validarHoraMinSeg(String hora) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setLenient(false);
            if (hora == null) {
                return false;
            }
            sdf.parse(hora);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
}
