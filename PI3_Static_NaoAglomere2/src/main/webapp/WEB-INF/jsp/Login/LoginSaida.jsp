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
    </head>
    <body>
        <h1>Saida</h1>
        <c:if test="${Erro != null}">
            <span class="erro"><c:out value="${Erro}"/></span>
        </c:if>
        <label>olá </label> <c:out value="${novoLogin.getEmail()}" />
    </body>
</html>
