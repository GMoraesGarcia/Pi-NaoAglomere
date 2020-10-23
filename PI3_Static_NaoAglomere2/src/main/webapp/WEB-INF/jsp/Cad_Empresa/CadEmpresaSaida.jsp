<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saída</title>
    </head>
    <body>
        <h1>Dados</h1>
        
        <span>Adicionado com sucesso</span>
        <c:if test="${Erro != null}">
            <span class="erro"><c:out value="${Erro}"/></span>
        </c:if>
        <label>CPNJ: </label> <c:out value="${dados.getDescricao()}" />

    </body>
</html>
