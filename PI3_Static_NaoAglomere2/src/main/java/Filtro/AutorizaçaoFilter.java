/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filtro;

import Login.LoginDados;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author leona
 */
@WebFilter(filterName = "AutorizaçaoFilter", urlPatterns = {"/agendamento"})
public class AutorizaçaoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession sessao = httpRequest.getSession();
        if (sessao.getAttribute("user") == null && sessao.getAttribute("login") == null) {

            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }
        
        LoginDados usuario = (LoginDados) sessao.getAttribute("login");

        if (verificarAcesso(usuario, httpRequest)) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/erro-nao-autorizado.jsp");
        }

    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    private boolean verificarAcesso(LoginDados usuario, HttpServletRequest httpRequest) {

        String paginaAcessada = httpRequest.getRequestURI();

        if (paginaAcessada.endsWith("/agendamento") && usuario.getTipo_cadastro().equals("usuário")) {
            return true;
        }
        

        return false;
    }
}
