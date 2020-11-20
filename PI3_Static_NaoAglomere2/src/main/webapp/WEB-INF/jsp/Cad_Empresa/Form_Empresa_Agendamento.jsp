<%-- 
    Document   : Form_Empresa_Agendamento
    Created on : 14/11/2020, 23:58:14
    Author     : leona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/Estilo.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/footer.css">
        <title>Cadastro de agendamento</title>
    </head>
    <body>

        <c:if test="${Erro != null}">
            <span class="erro"><c:out value="${Erro}"/></span>
        </c:if>

        <h1>Seleção de horários</h1>
        <form method="post" action="cad-agendamento-salvar" class="containerw">
            <div> 
                <h5>Horário de funcionamento:</h5>
                <label>Horário de abertura:</label>
                <input type="time" name="HoraAb" placeholder="Ex: 08:00" value="${HoraAb}" class="form-control">
                <c:if test="${horaabErro != null}">
                    <span class="erro"><c:out value="${horaabErro}"/></span>
                </c:if>
            </div>
            <div>
                <label>Horário de fechamento:</label>
                <input type="time" name="HoraFh" placeholder="Ex: 18:00" value="${HoraFh}" class="form-control">
                <c:if test="${horafhErro != null}">
                    <span class="erro"><c:out value="${horafhErro}"/></span>
                </c:if>
            </div>
            <div>
                <h5>Tempo de atendimento:</h5>
                <input type="time" name="periodo" placeholder="Ex: 01:30" value="${Periodo}" class="form-control">
                <c:if test="${periodoErro != null}">
                    <span class="erro"><c:out value="${periodoErro}"/></span>
                </c:if>
            </div>
            <div>
                <button class="btn btn-success botoes botoes" type="submit" >Concluído</button> 
            </div>
        </form>
            <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>      
    </body>
</html>
