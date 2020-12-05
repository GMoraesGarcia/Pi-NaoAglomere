<%-- 
    Document   : Form_Agendamento
    Created on : 19/10/2020, 23:05:20
    Author     : Gabriel
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
        <script type="text/javascript">
            $("#telefone, #celular").mask("(00) 00000-0000");
            $("#data").mask("0000-00-00");
            $("#horario").mask("00:00");
        </script>

        <title>Agendamento</title>
    </head>
    <body>
        <header class="header">
            <c:import url="../header.jsp"/>
        </header>     

        <div class="containerw">
            <h1>Agendamento</h1>
            <form method="post" action="agendamento-salvar" class="form-group">
                <input name="id" value="${id}" type="hidden" >
                <div>

                    <label>Nome:</label>
                    <input type="text" name="nome" value="${sessionScope.user.nome}" class="form-control" readonly>
                    <c:if test="${nomeErro != null}">
                        <span class="erro"><c:out value="${nomeErro}"/></span> 
                    </c:if>
                    <c:if test="${empErro != null}">
                        <span class="erro"><c:out value="${empErro}" /></span>
                    </c:if>
                </div>
                <div>
                    <label>E-mail:</label>
                    <input type="email" name="email" value="${sessionScope.user.email}" class="form-control" readonly>
                    <c:if test="${emailErro != null}">
                        <span class="erro"> <c:out value="${emailErro}"/></span> 
                    </c:if>
                </div>
                <div>
                    <label>Telefone:</label>
                    <input type="text" name="telefone" value="${sessionScope.user.telefone}" id="telefone"  class="form-control" readonly>
                    <c:if test="${telefoneErro != null}">
                        <span class="erro"><c:out value="${telefoneErro}"/></span> 
                    </c:if>
                </div>
                <div>
                    <label>Data:</label>
                    <input id="data" type="date" name="data" value="${data}" class="form-control">
                    <c:if test="${dataAgendamentoErro != null}">
                        <span class="erro"><c:out value="${dataAgendamentoErro}"/></span> 
                    </c:if>
                </div>
                <div>

                    <c:choose>
                        <c:when test="${empresa.getHorariosDisponiveis() != null }">
                            <label>Horários:</label>
                            <select id="id" name="horario" class="custom-select">       
                                <option disabled selected>Selecione um Horário</option>
                                <c:forEach var="hora2" items="${empresa.getHorariosDisponiveis()}">
                                    <option value="${hora2}">${hora2}</option>    
                                </c:forEach>
                            </select>
                            <c:if test="${horarioErro != null}">
                                <span class="erro"><c:out value="${horarioErro}"/></span> 
                            </c:if>                                                     
                           
                        </c:when>
                        <c:otherwise>
                            <p> Não há horarios disponiveis para este estabelecimento</p>
                        </c:otherwise>
                    </c:choose>

                </div>
                <button type="submit" class="btn btn-success botoes">Agendar</button>
                <button type="reset"  class="btn btn-danger botoes">Cancelar</button>
            </form>

        </div>
        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>
    </body>

</html>
