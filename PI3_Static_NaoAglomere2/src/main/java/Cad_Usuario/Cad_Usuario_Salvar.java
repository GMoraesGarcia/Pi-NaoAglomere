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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gabriel
 */
@WebServlet(name = "Cad_Usuario_Salvar", urlPatterns = {"/salvar-usuario"})
public class Cad_Usuario_Salvar extends HttpServlet {

  
    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession sessao = request.getSession();
       Cad_Usuario novoUsuario = (Cad_Usuario)sessao.getAttribute("novouser");
       sessao.removeAttribute("novouser");
       
       request.setAttribute("novouser", novoUsuario);
       RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Usuario/Form_Saida.jsp");
       dispacher.forward(request, response);
       
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
        
           
           
            
            
            //Validação Nome
            boolean nomeValido = nomeStr != null && nomeStr.trim().length()>0;
            
            //Validação e-mail
            boolean emailValido = (emailStr != null && emailStr.trim().length()>0);
            
            //Validação de data de nascimento
              LocalDate dataNascimento = null;
               if(dtNascimentoStr != null && dtNascimentoStr.trim().length() >0){
                     dataNascimento = LocalDate.parse(dtNascimentoStr);
                }
             boolean dataNascimentoValida = (dataNascimento != null && dataNascimento.isBefore(LocalDate.now()));
            
             
             //Validação CPF
               boolean validaCpf = false;
            if(cpfStr!= ""){
              char arrayCpf[] = cpfStr.toCharArray();
               validaCpf = validaCPF(arrayCpf);
            } 
         
              
              //Validação do Telefone
              boolean validaTelefone = (telefoneStr != null && telefoneStr.trim().length() == 11);
              
             
              boolean camposValidos = (nomeValido && emailValido && validaCpf && validaTelefone && dataNascimentoValida);
              
              if(!camposValidos){
                  //mensagens de erro
                  if(!nomeValido){
                      request.setAttribute("nomeErro", "Nome deve ser preenchido ou Válido");
                  }
                  if(!emailValido){
                      request.setAttribute("emailErro", "E-mail deve ser preenchido ou Válido");
                  }
                  if(!validaCpf){
                      request.setAttribute("cpfErro", " CPF deve ser preenchido ou Válido");
                  }
                  if(!validaTelefone){
                      request.setAttribute("telefoneErro", " Telefone deve ser preenchido ou Válido");
                  }
                  if(!dataNascimentoValida){
                      request.setAttribute("dtNascimentoErro", " Data de Nascimento deve ser preenchido ou Válido");
                  }
                  
                  request.setAttribute("nome", nomeStr);
                  request.setAttribute("cpf", cpfStr);
                  request.setAttribute("email", emailStr);
                  request.setAttribute("dataNascimento", dtNascimentoStr);
                  request.setAttribute("telefone", telefoneStr);
                  
                   RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Usuario/Form_Cad_Usuario.jsp");
                    dispatcher.forward(request, response);
                    return;
              }
              
        Cad_Usuario novoUsuario = new Cad_Usuario();
        
        novoUsuario.setNome(nomeStr);
        novoUsuario.setCpf(cpfStr);
        novoUsuario.setEmail(emailStr);
        novoUsuario.setDataNascimento(LocalDate.parse(dtNascimentoStr));
        novoUsuario.setTelefone(telefoneStr);
        novoUsuario.setSenha(senhaStr);
        novoUsuario.setConfirmarSenha(confSenhaStr);
        
        /*request.setAttribute("novo", novoUsuario);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Usuario/Form_Saida.jsp");
       dispatcher.forward(request, response);*/
        
        HttpSession sessao = request.getSession();
        sessao.setAttribute("novoUsuario", novoUsuario);
        
        response.sendRedirect("salvar-usuario");
     
      
    }
    public static boolean validaCPF(char ArrayCPF [] ){
          String digito01 = String.valueOf(ArrayCPF[9]);
          String digito02 = String.valueOf(ArrayCPF[10]);
           int num =10, r=0, i=0;
       
       while(num >=2){
           //Validação do primeiro digito
            String m =  String.valueOf(ArrayCPF[i]);
            int m2 = Integer.parseInt(m);
             r += m2*num;
           num--; i++;
       }
       int mult = (r*10)%11;
       if(mult ==10){
           mult =0;
       }
       if(mult == Integer.parseInt(digito01)){
           //Validação para o segundo Digito
           num = 11; i =0; r =0;
           while(num >=2){
               String valoresSegundoDigitos = String.valueOf(ArrayCPF[i]);
               int valor2 = Integer.parseInt(valoresSegundoDigitos);
               r+= valor2 * num;
               i++;num--;
           }
           int mult2 = (r*10)%11;
            if(mult2 ==10){
                mult2 =0;
            }
            if(mult2 == Integer.parseInt(digito02))
              return true;
       }
     
           return false;

    }

  

}