<%-- 
    Document   : Form_Cad_Usuario
    Created on : 18/10/2020, 18:56:53
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/Cad_Usuario.css">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-light justify-content-end espaco">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#">Link 1</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link 2</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link 3</a>
                </li>
            </ul>
        </nav>
        <h1>Cadastro de Usuário</h1>
        <div class="containerw"> 
            <form method="get" action="salvar-usuario" class="form-group">
                <div>
                    <label>Nome: </label>
                    <input type="text" class="form-control" name="nome">

                </div>
                <div>
                    <label>CPF: </label>
                    <input type="text" class="form-control" name="cpf">
                </div>
                <div>
                    <label>E-mail: </label>
                    <input type="email" class="form-control"  name="email">

                </div>
                <div>
                    <label>Data de Nascimento:</label>
                    <input type="date" class="form-control" name="dataNascimento">

                </div>
                <div>
                    <label>Telefone:</label>
                    <input type="text" class="form-control" placeholder="( )_____-____" name="telefone">

                </div>
                <div>
                    <label>Senha:</label>
                    <input type="password" class="form-control" name="senha">

                </div>
                <div>
                    <label>Confirmar Senha:</label>
                    <input type="password" class="form-control" name="confirmarSenha">

                </div>
                <div>
                    <button type="submit" class="btn btn-success">Efetuar Cadastro</button>
                    <button type="reset" class="btn btn-danger">Cancelar</button>

                </div>
            </form>
        </div>
    </body>
</html>
