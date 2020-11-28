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
        <div class="containerw">
            <header class="header">
                <c:import url="../header.jsp"/>
            </header>

            <h1>Gerenciar agendamentos</h1>

            <c:if test="${login.getTipo_cadastro() == 'usuário'}">

                <c:choose>
                    <c:when test="${agendamentos != null }">  
                        <table class="table ">
                            <thead class="thead-dark">
                                <tr>
                                    <th>Nome</th>
                                    <th>Empresa</th>
                                    <th>Data</th>
                                    <th>Horário</th>
                                </tr>
                            </thead>            
                            <c:forEach var="agendamento" items="${agendamentos}">
                                <tbody>
                                    <tr>
                                        <td> <c:out value="${agendamento.getNomeUser()}" /></td>  
                                        <td><c:out value="${agendamento.getNomeEmpresa()}" /></td> 
                                        <td> <c:out value="${agendamento.getData()}" /></td>
                                        <td><c:out value="${agendamento.getHorario()}" /></td> 
                                    </tr>
                                </tbody>
                            </c:forEach>
                        </table>  
                    </c:when>
                    <c:otherwise>
                        <p>Nenhum agendamento feito.</p>
                    </c:otherwise>
                </c:choose>         
            </c:if>

            <c:if test="${login.getTipo_cadastro() == 'empresa'}">

                <c:choose>
                    <c:when test="${agendamentos != null }">    
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th>Empresa</th>
                                    <th>Cliente</th>
                                    <th>Data de Agendamento</th>
                                    <th>Horário</th>
                                </tr>
                            </thead>            
                            <c:forEach var="agendamento" items="${agendamentos}">
                                <tbody>
                                    <tr>
                                        <td>Nome da empresa: <c:out value="${agendamento.getNomeEmpresa()}" /></td>
                                        <td>Nome: <c:out value="${agendamento.getNomeUser()}" /></td>
                                        <td>Data: <c:out value="${agendamento.getData()}" /></td>
                                        <td>Horário: <c:out value="${agendamento.getHorario()}" /></td>
                                    </tr>
                                </tbody> 
                            </c:forEach>
                        </table>
                    </c:when>

                    <c:otherwise>
                        <p>Nenhum agendamento feito.</p>
                    </c:otherwise>
                </c:choose>         
            </c:if>

        </div>
        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>

    </body>
</html>
