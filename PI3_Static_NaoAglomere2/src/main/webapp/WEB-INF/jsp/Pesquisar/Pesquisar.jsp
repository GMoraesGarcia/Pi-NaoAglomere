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
    </head>
    <body>
        <header class="container">

            <nav class="menu-opcoes">
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Login</a></li>
                    <li><a href="/PI3_Static_NaoAglomere2/cad_usuario">Cadastrar Usuario</a></li>
                    <li><a href="/PI3_Static_NaoAglomere2/cad-empresa-abrir">Cadastrar Empresa</a></li>
                    
                    
                </ul>
            </nav>

            <nav class="sugestões">
                <ul>
                    <li><a href="#">Mercados</a></li>
                    <li><a href="#">Salões de Beleza e Estudios</a></li>
                    <li><a href="#">Bares e restaurantes</a></li>
                    <li><a href="#">Lojas</a></li>
                    <li><a href="#">Bancos</a></li>                    
                </ul>
            </nav>
        </header>
        <div>            
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

        <footer>
            <div class="container">

                <p>@Todos os direitos reservados</p>
            </div>
        </footer>

    </body>
</html>
