<%-- 
    Document   : Home
    Created on : 24/10/2020, 16:36:24
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Não Aglomere</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/footer.css">
        <link rel="stylesheet" href="css/Home.css">
        <link rel="stylesheet" href="css/Estilo.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <style>
  /* Make the image fully responsive */
 
  </style>
    </head>
    <body>
        <header class="header">
            <c:import url="../header.jsp"/> 
        </header>
        <div class="containerw ">       
            <h1>Não Aglomere</h1>
            <div >
            <iframe class="col-lg-12" width="560" height="450" src="https://www.youtube.com/embed/LwUjglzIUhc" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            </div>
            
            <p><a href="${pageContext.request.contextPath}/salvar-usuario">Cadastre-se </a> ou se já é cadastrado faça  <a href="${pageContext.request.contextPath}/login">Login</a> para realizar agendamentos ou ir a algum estabelecimento, sem aglomerar #FICAEMCASA</p>

        </div>
        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>
    </body>

    <c:if test="${agendamento != null}">
        <script>
            alert("${agendamento}");
        </script>
    </c:if>
</html>
