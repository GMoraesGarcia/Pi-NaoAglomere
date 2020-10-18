<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Resultado dados preenchidos</title>
        <meta charset=UTF-8">  
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <h1>Resultado dados preenchidos</h1>
        <h2><c:out value ="${dados.pesquisa}" /></h2>

    </body>
</html>
