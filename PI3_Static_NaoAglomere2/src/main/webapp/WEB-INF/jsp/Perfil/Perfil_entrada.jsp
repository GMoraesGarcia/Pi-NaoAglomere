<%-- 
    Document   : Perfil
    Created on : 06/11/2020, 22:11:10
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
        <title>Perfil de Usuário</title>
    </head>
    <body>
        <header class="header">
            <c:import url="../header.jsp"/>
        </header>     
            <h1>Bem Vindo! <c:out value="${user.getNome()}"/></h1>
        <div class="containerw">
            <form class="form-group">
                <label>CPF:</label>
                <input  class="form-control" type="text" name="cpf" value="${user.getCpf()}" disabled>
                
                 <label>E-mail</label>
                 <input  class="form-control" type="text" name="email" value="${user.getEmail()}">
                
                 <label>Nome:</label>
                <input  class="form-control" type="text" name="nome" value="${user.getNome()}">
                
                <label>Data de Nascimento</label>
                <input  class="form-control" type="date" name="dataNascimento" value="${user.getDataNascimento()}">
                
                <label>Telefone:</label>
                <input  class="form-control" type="text" name="telefone" value="${user.getTelefone()}">
                
                 <button type="submit" class="btn btn-success botoes" >Alterar</button>
               
            </form> 
        </div>
        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>
    </body>
</html>
