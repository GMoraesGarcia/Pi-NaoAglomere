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
                    <h1 class="titulo-pesquisa"><b>Resultado da busca</b></h1>

                    <h2><c:out value ="${busca.pesquisa}" /></h2>
                    <div class="row-cols-2">
                        <c:choose>
                            <c:when test="${busca.getEstabelecimentos() != null }">  
                                <fieldset class="col-auto">

                                    <c:forEach var="interesse" items="${busca.getEstabelecimentos()}">
                                        <c:choose>                                            
                                            <c:when test="${interesse.getFoto() != null}">
                                                <div class="imagem-est" >
                                                    <img src="${interesse.getFoto()}"width="300" height="200">
                                                </div>                                            
                                            </c:when>
                                            <c:otherwise>
                                                <img class="imagem-est" src="C:/PI-FOTOS/avatar-padrao.jpg"width="300" height="200">                                                
                                            </c:otherwise>
                                        </c:choose>
                                        <li><p><b>Nome</b>: <c:out value="${interesse.getNome_empresa()}" /></p></li>
                                        <li><p><b>Email:</b> <c:out value="${interesse.getEmail()}" /></p></li>
                                        <li><p><b>Descrição:</b> <c:out value="${interesse.getDescricao()}" /></p></li>
                                        <li><p><b>Telefone:</b> <c:out value="${interesse.getTelefone()}" /></p></li>
                                        <li><p><b>Máximo de pessoas:</b> <c:out value="${interesse.getQtd_max()}" /></p></li>
                                        <li><p><b>Pessoas do Local:</b> <c:out value="${interesse.getQtdAgendamentos()}" /></p></li>



                                        <li><p><b>Rua: </b><c:out value="${interesse.getRua()}" /></p></li>
                                        <li><p><b>Bairro:</b> <c:out value="${interesse.getBairro()}" /></p></li>
                                        <li><p><b>Numero: </b><c:out value="${interesse.getNumero_rua()}" /></p></li>
                                        <li><p><b>Regras: </b><c:out value="${interesse.getRegras()}" /></p></li>



                                        <c:if test="${interesse.getAgendamento() == 'Sim'}">

                                            <form action="${pageContext.request.contextPath}/agendamento" method="get">
                                                <input type="hidden"  name="id" value="${interesse.getEmpresa_Id()}" />
                                                <button id="botoes" class="btn btn-info" type="submit"  >Agendar Horário</button>
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
                                        <p id="espaço-busca">.</p>
                                    </c:forEach>                                        


                                </c:when>
                                <c:otherwise>
                                    <p> Não tem nenhuma Busca Informada</p>
                                </c:otherwise>
                            </c:choose> 
                        </fieldset>
                    </div>                    
                </c:if>
            </section>
        </div>
        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>
    </body>
</html>
