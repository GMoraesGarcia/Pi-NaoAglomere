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
                        <input  type="submit" class="btn btn-info" name="pesquisa" value="mercado">    
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
                <c:if test="${Erro != null}">
                    <span class="erro"><c:out value="${Erro}"/></span>
                </c:if>
                <c:if test="${dados.pesquisa != null}">
                    <h1>Resultado dados Pesquisados</h1>
                    <h2><c:out value ="${dados.pesquisa}" /></h2>
                    <c:choose>
                        <c:when test="${dados.getEstabelecimentos() != null }">               
                            <ul>                    
                                <c:forEach var="interesse" items="${dados.getEstabelecimentos()}">
                                    
                                    <p>Nome: <c:out value="${interesse.getNome_Empresa()}" /></p>
                                    <p>Email: <c:out value="${interesse.getEmail()}" /></p>
                                    <p>Descrição: <c:out value="${interesse.getDescricao()}" /></p>
                                    <p>Telefone: <c:out value="${interesse.getTelefone()}" /></p>
                                    <p>Quantidade Maxima de pessoas: <c:out value="${interesse.getQtd_max()}" /></p>
                                    <p>Rua: <c:out value="${interesse.getRua()}" /></p>
                                    <p>Bairro: <c:out value="${interesse.getBairro()}" /></p>
                                    <p>Numero: <c:out value="${interesse.getNumero_Rua()}" /></p>
                                    <p>Regras: <c:out value="${interesse.getRegras()}" /></p>
                                    <a class="btn btn-info" href="${pageContext.request.contextPath}/agendamento" >Agendar</a>
                                </c:forEach>
                            </ul>
                            
                        </c:when>
                        <c:otherwise>
                            <p> Não tem nenhum interesse informado</p>
                        </c:otherwise>
                    </c:choose>                    
                </c:if>
            </section>
        </div>
        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>
    </body>
</html>
