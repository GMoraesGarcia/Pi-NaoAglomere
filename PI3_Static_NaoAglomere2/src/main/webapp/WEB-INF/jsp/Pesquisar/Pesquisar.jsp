<%-- 
    Document   : Pesquisar
    Created on : 15/10/2020, 15:08:02
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisar Estabelecimentos</title>
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
        <nav class="sugestões">
            <form method="post" action="pesquisar-Salvar">
                <div>            
                    <input  type="submit" class="btn btn-info" name="pesquisa" value="mercados">    
                    <input  type="submit" class="btn btn-info" name="pesquisa" value="Salões de Beleza e Estudios"> 
                    <input  type="submit" class="btn btn-info" name="pesquisa" value="Bares e restaurantes"> 
                    <input  type="submit" class="btn btn-info" name="pesquisa" value="Lojas">   
                    <input  type="submit" class="btn btn-info" name="pesquisa" value="Bancos">                     
                </div>
            </form>
        </nav>
        <div >            
            <section class="pesquisar">                
                <p>Nome do estabelecimento:</p>
                <form method="post" action="pesquisar-Salvar">
                    <div>            
                        <input type="search" name="pesquisa" size="50">
                        <button type="submit">Pesquisar</button>
                    </div>
                </form>
            </section>
        </div>

        <section class="telaPesquisa">

        </section>
        </div>
        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>


    </body>
</html>
