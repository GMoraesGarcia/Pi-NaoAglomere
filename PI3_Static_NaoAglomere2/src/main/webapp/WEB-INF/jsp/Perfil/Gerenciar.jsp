<%-- 
    Document   : gerenciar
    Created on : 20/11/2020, 07:10:22
    Author     : leona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/footer.css">
        <link rel="stylesheet" href="css/Estilo.css">
        <title>Gerenciar agendamentos</title>
    </head>
    <body>
        <header class="header">
            <c:import url="../header.jsp"/>
        </header>

        <h1>Gerenciar agendamentos</h1>

        <c:if test="${login.getTipo_cadastro() == 'usuário'}">

            <c:choose>
                <c:when test="${agendamentos != null }">               
                    <ul>                    
                        <c:forEach var="agendamento" items="${agendamentos}">
                            <li><p>Nome: <c:out value="${agendamento.getNomeUser()}" /></p></li>
                            <li><p>Nome da empresa: <c:out value="${agendamento.getNomeEmpresa()}" /></p></li>
                            <li><p>Data: <c:out value="${agendamento.getData()}" /></p></li>
                            <li><p>Horário: <c:out value="${agendamento.getHorario()}" /></p></li>
                            </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <p>Nenhum agendamento feito.</p>
                </c:otherwise>
            </c:choose>         
        </c:if>

        <c:if test="${login.getTipo_cadastro() == 'empresa'}">

            <c:choose>
                <c:when test="${agendamentos != null }">               
                    <ul>                    
                        <c:forEach var="agendamento" items="${agendamentos}">
                            <li><p>Nome da empresa: <c:out value="${agendamento.getNomeEmpresa()}" /></p></li>
                            <li><p>Nome: <c:out value="${agendamento.getNomeUser()}" /></p></li>
                            <li><p>Data: <c:out value="${agendamento.getData()}" /></p></li>
                            <li><p>Horário: <c:out value="${agendamento.getHorario()}" /></p></li>
                            </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <p>Nenhum agendamento feito.</p>
                </c:otherwise>
            </c:choose>         
        </c:if>


        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>
    </body>
</html>
