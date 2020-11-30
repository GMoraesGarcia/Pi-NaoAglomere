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
                        <button type="submit" class="btn btn-info" name="pesquisa" value="Mercados">Mercados</button>
                        <button type="submit" class="btn btn-info" name="pesquisa" value="Bares e restaurantes">Bares e restaurantes</button>
                        <button type="submit" class="btn btn-info" name="pesquisa" value="Salões de Beleza e Estudios">Salões de Beleza e Estudios</button>
                        <button type="submit" class="btn btn-info" name="pesquisa" value="Lojas">Lojas</button>
                        <button type="submit" class="btn btn-info" name="pesquisa" value="Bancos">Bancos</button>
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
                <c:if test="${busca.pesquisa != null}">
                    <h1>Resultado da busca</h1>
                    <h2><c:out value ="${busca.pesquisa}" /></h2>
                    <c:choose>
                        <c:when test="${busca.getEstabelecimentos() != null }">               
                            <ul>                    
                                <c:forEach var="interesse" items="${busca.getEstabelecimentos()}">

                                    <li><p>Nome: <c:out value="${interesse.getNome_empresa()}" /></p></li>
                                    <li><p>Email: <c:out value="${interesse.getEmail()}" /></p></li>
                                    <li><p>Descrição: <c:out value="${interesse.getDescricao()}" /></p></li>
                                    <li><p>Telefone: <c:out value="${interesse.getTelefone()}" /></p></li>
                                    <li><p>Quantidade Maxima de pessoas: <c:out value="${interesse.getQtd_max()}" /></p></li>
                                    <li><p>Quantidade de Pessoas: <c:out value="${interesse.getQtdAgendamentos()}" /></p></li>
                                    <li><p>Rua: <c:out value="${interesse.getRua()}" /></p></li>
                                    <li><p>Bairro: <c:out value="${interesse.getBairro()}" /></p></li>
                                    <li><p>Numero: <c:out value="${interesse.getNumero_rua()}" /></p></li>
                                    <li><p>Regras: <c:out value="${interesse.getRegras()}" /></p></li>

                                    <c:if test="${interesse.getAgendamento() == 'Sim'}">

                                        <form action="${pageContext.request.contextPath}/agendamento" method="get">
                                            <input type="text"  name="id" value="${interesse.getEmpresa_Id()}" />
                                            <button class="btn btn-info" type="submit"  >Agendar Horário</button>
                                        </form>
                                    </c:if> 
                                    <c:if test="${interesse.getAgendamento() == 'Não'}">
                                        <c:choose>
                                            <c:when test="${sessionScope.user != null}">
                                                <form method="post" action="pesquisar-Salvar">
                                                    <input name="id_Emp" type="hidden" value="${interesse.getEmpresa_Id()}">
                                                    <button class="btn btn-info" type="submit"  >Gerar Código</button>
                                                </form>
                                            </c:when>                                                
                                            <c:otherwise>
                                                <li><a class="btn btn-info"  href="${pageContext.request.contextPath}/login" >Fazer Login para Gerar Código</a></li>   
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </c:forEach>
                            </ul>

                        </c:when>
                        <c:otherwise>
                            <p> Não tem nenhuma Busca Informada</p>
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
