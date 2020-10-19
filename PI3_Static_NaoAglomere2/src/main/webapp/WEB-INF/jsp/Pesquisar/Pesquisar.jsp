<%-- 
    Document   : Pesquisar
    Created on : 15/10/2020, 15:08:02
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/open-iconic-bootstrap.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisar Estabelecimentos</title>
    </head>
    <body>
        <div>
            <h1>Pesquisar Estabelecimentos</h1>

            <p>Nome do estabelecimento:</p>
            <form method="post" action="pesquisar-Salvar">
                <div>            
                    <input type="text" name="pesquisa">
                    <button type="submit">Pesquisar</button>
                </div>
            </form>
        </div>

    </body>
</html>
