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
  .carousel-inner img {
    width: 100%;
    height: 100%;
  }
  </style>
    </head>
    <body>
        <header class="header">
            <c:import url="../header.jsp"/> 
        </header>
        <div class="containerw">       
            <h1>Não Aglomere</h1>
            <div id="demo" class="carousel slide" data-ride="carousel">

                <!-- Indicators -->
                <ul class="carousel-indicators">
                    <li data-target="#demo" data-slide-to="0" class="active"></li>
                    <li data-target="#demo" data-slide-to="1"></li>
                    <li data-target="#demo" data-slide-to="2"></li>
                </ul>

                <!-- The slideshow -->
                <section class="carousel-inner img">
                    <div class="carousel-item active">
                        <figure>
                            <img src="imagens/nova.png" alt="img01" width="1100px" height="500px">
                        </figure>
                    </div>
                    <div class="carousel-item">
                        <img src="imagens/img02.jpg" alt="Chicago" width="1100px" height="500px">
                    </div>
                    <div class="carousel-item">
                        <img src="imagens/prevencao-corona.jpg" alt="New York" width="1100px" height="500px">
                    </div>
                </section>

                <!-- Left and right controls -->
                <a class="carousel-control-prev" href="#demo" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#demo" data-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </a>
            </div>


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
