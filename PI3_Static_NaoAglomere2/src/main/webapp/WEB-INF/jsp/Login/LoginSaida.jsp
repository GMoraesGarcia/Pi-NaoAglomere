<%-- 
    Document   : LoginSaida
    Created on : 24/10/2020, 15:35:55
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login efetuado</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/Pesquisar.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/footer.css">
    </head>
    <body>
        <h1>Saida</h1>
        <header class="header">
            <c:import url="../header.jsp"/> 
        </header>
        
        <c:if test="${Erro != null}">
            <span class="erro"><c:out value="${Erro}"/></span>
        </c:if>
        <label>olá! </label> <c:out value="${novoLogin.getEmail()}" />
        
        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>
    </body>
</html>
