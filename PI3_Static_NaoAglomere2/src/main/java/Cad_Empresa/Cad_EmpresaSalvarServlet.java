/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cad_Empresa;

import java.io.IOException;
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
 * @author leona
 */
@WebServlet(name = "Cad_EmpresaSalvarServlet", urlPatterns = {"/cad-empresa-salvar"})
public class Cad_EmpresaSalvarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        Cad_EmpresaDados empresa_dados = (Cad_EmpresaDados) sessao.getAttribute("dados");
        sessao.removeAttribute("dados");

        request.setAttribute("dados", empresa_dados);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cad_Empresa/CadEmpresaSaida.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nome_empresa = request.getParameter("nome_empresa");
        String CNPJ = request.getParameter("CNPJ");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");
        String descricao = request.getParameter("descricao");
        String rua = request.getParameter("rua");
        String numeroStr = request.getParameter("numero_rua");
        String bairro = request.getParameter("bairro");
        String qtd_pessoasStr = request.getParameter("qtd_pessoas");
        String regras = request.getParameter("regras");

        Cad_EmpresaDados empresa_dados = new Cad_EmpresaDados();

        empresa_dados.setNome_Empresa(nome_empresa);
        empresa_dados.setCNPJ(CNPJ);
        empresa_dados.setEmail(email);
        empresa_dados.setSenha(senha);
        empresa_dados.setTelefone(telefone);
        empresa_dados.setDescricao(descricao);
        empresa_dados.setRua(rua);
        empresa_dados.setNumero_Rua(Integer.parseInt(numeroStr));
        empresa_dados.setBairro(bairro);
        empresa_dados.setQtd_max(Integer.parseInt(qtd_pessoasStr));
        empresa_dados.setRegras(regras);

        request.setAttribute("dados", empresa_dados);

        HttpSession sessao = request.getSession();
        sessao.setAttribute("dados", empresa_dados);
        response.sendRedirect("cad-empresa-salvar");
    }

}
