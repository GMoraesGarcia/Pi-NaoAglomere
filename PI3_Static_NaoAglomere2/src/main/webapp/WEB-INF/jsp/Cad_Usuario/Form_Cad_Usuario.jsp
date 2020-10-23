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
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/footer.css">
        <title>JSP Page</title>
    </head>
    <body>
        <header class="header">
            <c:import url="../header.jsp"/>
        </header>        
        <h1>Cadastro de Usuário</h1>
        <div class="containerw"> 
            <form method="post" action="salvar-usuario" class="form-group" novalidate>
                <div>
                    <label>Nome: </label>
                    <input type="text" class="form-control" name="nome" value="${nome}">
                    <c:if test="${nomeErro != null}">
                        <span class="erro"><c:out value="${nomeErro}"/></span> 
                    </c:if>

                </div>
                <div>
                    <label>CPF: </label>
                    <input type="text" class="form-control" name="cpf" value="${cpf}">
                    <c:if test="${cpfErro != null}">
                        <span><c:out value="${cpfErro}"/></span> 
                    </c:if>
                </div>
                <div>
                    <label>E-mail: </label>
                    <input type="email" class="form-control"  name="email" value="${email}">
                    <c:if test="${emailErro != null}">
                        <span><c:out value="${emailErro}"/></span> 
                    </c:if>

                </div>
                <div>
                    <label>Data de Nascimento:</label>
                    <input type="date" class="form-control" name="dataNascimento" value="${dataNascimento}">
                     <c:if test="${dtNascimentoErro != null}">
                        <span><c:out value="${dtNascimentoErro}"/></span> 
                    </c:if>
                </div>
                <div>
                    <label>Telefone:</label>
                    <input type="text" class="form-control" placeholder="( )_____-____" name="telefone" value="${telefone}">
                    <c:if test="${telefoneErro != null}">
                        <span><c:out value="${telefoneErro}"/></span> 
                    </c:if>
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
        
        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>
    </body>
</html>
