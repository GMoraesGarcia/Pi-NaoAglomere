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
        <div class="containerw">


            <h1>Gerenciar agendamentos</h1>

            <c:if test="${login.getTipo_cadastro() == 'usuário'}">

                <c:if test="${Excluído != null}">
                    <script>
                        alert("${Excluído}");
                    </script>
                </c:if>


                <c:choose>
                    <c:when test="${agendamentos != null }">  
                        <table class="table ">
                            <thead class="thead-dark">
                                <tr>
                                    <th></th>
                                    <th>Nome</th>
                                    <th>Empresa</th>
                                    <th>Data</th>
                                    <th>Horário</th>
                                    <th></th>
                                </tr>
                            </thead>            
                            <c:forEach var="agendamento" items="${agendamentos}">
                                <tbody>
                                    <tr>
                                <form method="post" action="gerenciar-salvar">
                                    <td><input name="num_agendamento" type="hidden" value="${agendamento.getNumAgendamento()}"></td>
                                    <td> <c:out value="${agendamento.getNomeUser()}" /></td>  
                                    <td><c:out value="${agendamento.getNomeEmpresa()}" /></td> 
                                    <td> <c:out value="${agendamento.getData()}" /></td>
                                    <td><c:out value="${agendamento.getHorario()}" /></td>
                                    <td><button type="submit" class="btn-danger">Excluir</button></td>
                                </form>
                                </tr>
                                </tbody>
                            </c:forEach>
                        </table>  
                    </c:when>
                    <c:otherwise>
                        <p>Nenhum agendamento feito.</p>
                    </c:otherwise>
                </c:choose>  
                <c:choose>
                    <c:when test="${codigos != null}">
                        <table class="table ">
                            <thead class="thead-dark">
                                <tr>
                                    <th></th>
                                    <th>Estabelecimento</th>
                                    <th>Código</th>
                                </tr>
                            </thead> 
                            <c:forEach var="codigo" items="${codigos}">
                                <tbody>
                                    <tr>
                                <form method="post" action="gerenciar-salvar">
                                    <td> <c:out value="${codigo.getNome_empresa()}" /></td>  
                                    <td><c:out value="${codigo.getCodigo()}" /></td> 
                                </form>
                                </tr>
                                </tbody>
                            </c:forEach>
                        </table>

                    </c:when>
                </c:choose>
            </form>
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
                                    <td><c:out value="${agendamento.getNomeEmpresa()}" /></td>
                                    <td><c:out value="${agendamento.getNomeUser()}" /></td>
                                    <td><c:out value="${agendamento.getData()}" /></td>
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

    </div>
    <footer class="footer">
        <c:import url="../footer.jsp"/>
    </footer>

</body>
</html>
