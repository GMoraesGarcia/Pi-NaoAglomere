<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="menu-opcoes navbar-expand-sm ">
    <ul class="">
        <li><a href="${pageContext.request.contextPath}/home-abrir">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/pesquisar-abrir">Realizar Pesquisa</a></li>
        

        <c:choose>
            <c:when test="${sessionScope.user == null && sessionScope.empresa == null }">
                <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                <li><a  href="${pageContext.request.contextPath}/cad_usuario">Cadastrar Usuario</a></li>
                <li> <a href="${pageContext.request.contextPath}/cad-empresa-abrir">Cadastrar Empresa</a></li>
<!--                <li><a href="${pageContext.request.contextPath}/Perfil-usuario" >Perfil</a></li>-->
                </c:when>                
                <c:when test="${sessionScope.user != null}">
                <li><a href="${pageContext.request.contextPath}/Perfil-usuario" >olá! <c:out value="${sessionScope.user.email}" /></a></li>
                </c:when>
                <c:when test="${sessionScope.empresa != null}">
                <li><a href="${pageContext.request.contextPath}/Perfil-usuario" >olá! <c:out value="${sessionScope.empresa.email}" /></a></li>
                </c:when>                
            </c:choose>

    </ul>
</nav>
