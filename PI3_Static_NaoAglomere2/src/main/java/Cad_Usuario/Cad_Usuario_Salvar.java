/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cad_Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
@WebServlet(name = "Cad_Usuario_Salvar", urlPatterns = {"/salvar-usuario"})
public class Cad_Usuario_Salvar extends HttpServlet {

  
    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
       
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         
            String nomeStr = request.getParameter("nome");
            String cpfStr = request.getParameter("cpf");
            String emailStr = request.getParameter("email");
            String dtNascimentoStr = request.getParameter("dataNascimento");
            String telefoneStr = request.getParameter("telefone");
            String senhaStr = request.getParameter("senha");
            String confSenhaStr = request.getParameter("confirmarSenha");
        
        Cad_Usuario novoUsuario = new Cad_Usuario();
        
        novoUsuario.setNome(nomeStr);
        novoUsuario.setCpf(cpfStr);
        novoUsuario.setEmail(emailStr);
        novoUsuario.setDataNascimento(LocalDate.parse(dtNascimentoStr));
        novoUsuario.setTelefone(telefoneStr);
        novoUsuario.setSenha(senhaStr);
        novoUsuario.setConfirmarSenha(confSenhaStr);
        
        request.setAttribute("novo", novoUsuario);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Usuario/Form_Saida.jsp");
       dispatcher.forward(request, response);
        
        
     
      
    }

  

}
