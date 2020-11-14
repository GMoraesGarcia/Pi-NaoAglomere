<%-- 
    Document   : login
    Created on : 24/10/2020, 14:42:48
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/Login.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/footer.css">
        <link rel="stylesheet" href="css/Estilo.css">
    </head>
    <body>
        <header class="header">
            <c:import url="../header.jsp"/> 
        </header>
        <div class="containerw">


            <form method="post" action="${pageContext.request.contextPath}/login" class="form-group">
                <div>
                    <label>E-mail: </label>
                    <input type="email" class="form-control"  name="email" value="${email}">
                    <c:if test="${emailErro != null}">
                        <span class="erro"><c:out value="${emailErro}"/></span> 
                    </c:if>

                </div>
                <div>
                    <label>Senha:</label>
                    <input type="password" class="form-control" name="senha" value="${senha}">
                    <c:if test="${senhaErro != null}">
                        <span class="erro"><c:out value="${senhaErro}"/></span>
                    </c:if>

                </div>
                <div>
                    <button type="submit" class="btn btn-success botoes">Login</button>                
                </div>
                <c:if test="${Erro != null}">
                    <span class="erro"><c:out value="${Erro}"/></span>
                </c:if>
            </form>
        </div>
        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>
    </body>

    <c:if test="${dados.getCNPJ() != null}">
        <script>
            alert("Cadastrado com sucesso!");
        </script>
    </c:if>
    <c:if test="${novouser.getCpf() != null}">
        <script>
            alert("Cadastrado com sucesso!");
        </script>
    </c:if>
</html>
