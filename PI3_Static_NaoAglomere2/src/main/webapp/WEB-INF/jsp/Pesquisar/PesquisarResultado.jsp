<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Resultado dados preenchidos</title>
        <meta charset=UTF-8">  
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/Pesquisar.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/footer.css">
        <link rel="stylesheet" href="css/Estilo.css">
    </head>
    <body>
        <header class="header">
            <c:import url="../header.jsp"/> 
        </header>
        <div class="containerw">
            <h1>Resultado dados Pesquisados</h1>
            <h2><c:out value ="${dados.pesquisa}" /></h2>

            <a class="btn btn-info" href="${pageContext.request.contextPath}/agendamento" >Agendar</a>
        </div>

        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>

    </body>
</html>
