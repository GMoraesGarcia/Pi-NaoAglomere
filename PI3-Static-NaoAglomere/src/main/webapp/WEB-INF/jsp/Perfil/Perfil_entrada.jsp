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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
        <title>Perfil de Usuário</title>
    </head>
    <body>
        <header class="header">
            <c:import url="../header.jsp"/>
        </header>

        <!-- inicio do form para usuarios-->

        <c:if test="${login.getTipo_cadastro() == 'usuário'}">
            <h1>Bem Vindo(a)! <c:out value="${user.getNome()}"/></h1>
            <div class="containerw" >
                <form class="form-group" method="post" action="perfil-alterado">
                    <label>CPF:</label>
                    <input  class="form-control" type="text" name="cpf" value="${user.getCpf()}" readonly="readonly">

                    <label>E-mail</label>
                    <input  class="form-control" type="text" name="email" value="${user.getEmail()}">
                    <c:if test="${emailErro != null}">
                        <span class="erro"><c:out value="${emailErro}"/></span> 
                    </c:if>

                    <label>Nome:</label>
                    <input  class="form-control" type="text" name="nome" value="${user.getNome()}">
                    <c:if test="${nomeErro != null}">
                        <span class="erro"><c:out value="${nomeErro}"/></span> 
                    </c:if>

                    <label>Data de Nascimento</label>
                    <input id="dataNascimento" class="form-control" type="date" name="dataNascimento" value="${user.getDataNascimento()}">
                    <c:if test="${dtNascimentoErro != null}">
                        <span class="erro"><c:out value="${dtNascimentoErro}"/></span> 
                    </c:if>
                    <script type="text/javascript">
                        $("#dataNascimento").mask("0000-00-00");
                    </script>

                    <label>Telefone:</label>
                    <input id="telefone" class="form-control" type="text" name="telefone" value="${user.getTelefone()}">
                    <c:if test="${telefoneErro != null}">
                        <span class="erro"><c:out value="${telefoneErro}"/></span> 
                    </c:if>
                    <script>
                        $("#telefone").mask("(00) 00000-0000");
                    </script>

                    <button type="submit" class="btn btn-success botoes" >Alterar</button>

                </form> 



                <a class="btn btn-info" href="${pageContext.request.contextPath}/logout">Logout</a>

            </div>
        </c:if>

        <c:if test="${Erro != null}">
            <span class="erro"><c:out value="${Erro}"/></span>
        </c:if>

        <!--inicio do form para empresas-->

        <c:if test="${login.getTipo_cadastro() == 'empresa' || empresa.getNome_empresa() != null}">
            <h1>Bem Vindo(a)! <c:out value="${empresa.getNome_empresa()}"/></h1>
            <div class="containerw">
                <form class="form-group" method="post" action="perfil-alterado" novalidate enctype="multipart/form-data">
                    <div class="row">
                        <fieldset class="col-lg-6">
                            <div>
                                <label id="Nome_Empresa">Nome da empresa:</label>
                                <input type="text" class="form-control" name="nome_empresa" value="${empresa.getNome_empresa()}">
                                <c:if test="${nomeErro != null}">
                                    <span class="erro"><c:out value="${nomeErro}"/></span>
                                </c:if>
                            </div>
                            <div>
                                <label id="CNPJ">CPNJ:</label>
                                <input type="text" class="form-control" id="CNPJtext" name="CNPJ" value="${empresa.getCnpj()}" readonly="readonly">
                            </div>
                            <div>
                                <label id="Email">E-mail Corporativo:</label>
                                <input type="email" class="form-control" name="email" value="${empresa.getEmail()}" >
                                <c:if test="${emailErro != null}">
                                    <span class="erro"><c:out value="${emailErro}"/></span>
                                </c:if>
                            </div>
                            <div>
                                <label>Telefone:</label>
                                <input id="telefone" type="tel" class="form-control" name="telefone" value="${empresa.getTelefone()}">
                                <c:if test="${telefoneErro != null}">
                                    <span class="erro"><c:out value="${telefoneErro}"/></span>
                                </c:if>
                                <script>
                                    $("#telefone").mask("(00) 00000-0000");
                                </script>
                            </div>
                            <div>
                                <label id="descricao">Descrição:</label>
                                <input type="text" class="form-control" name="descricao" value="${empresa.getDescricao()}" readonly="readonly">
                            </div>
                            <div>
                                <label>Agendamento obrigatório:</label>
                                <input type="text" class="form-control" name="agendamento"  value="${empresa.getAgendamento()}">
                                <c:if test="${agendamentoErro != null}">
                                    <span class="erro"><c:out value="${agendamentoErro}"/></span>
                                </c:if>
                            </div>

                            <div>
                                <button class="btn btn-success botoes botoes" type="submit" >Alterar</button>
                            </div>
                        </fieldset>

                        <fieldset class="col-lg-6">
                            <div>
                                <label id="rua">Rua:</label>
                                <input type="text" class="form-control" name="rua" value="${empresa.getRua()}">
                                <c:if test="${ruaErro != null}">
                                    <span class="erro"><c:out value="${ruaErro}"/></span>
                                </c:if>
                            </div>
                            <div>
                                <label id="numero">Número:</label>
                                <input type="number" class="form-control" name="numero_rua" value="${empresa.getNumero_rua()}">
                                <c:if test="${numeroErro != null}">
                                    <span class="erro"><c:out value="${numeroErro}"/></span>
                                </c:if>
                            </div>
                            <div>
                                <label id="bairro">Bairro:</label>
                                <input type="text" class="form-control" name="bairro" value="${empresa.getBairro()}">
                                <c:if test="${bairroErro != null}">
                                    <span class="erro"><c:out value="${bairroErro}"/></span>
                                </c:if>
                            </div>
                            <div>
                                <label id="qtd_pessoas">Quantidade máxima de pessoas:</label>
                                <input type="number" class="form-control" name="qtd_pessoas" value="${empresa.getQtd_max()}">
                                <c:if test="${qtdErro != null}">
                                    <span class="erro"><c:out value="${qtdErro}"/></span>
                                </c:if>
                            </div>             
                            <div>
                                <label id="regras">Regras:</label>
                                <input class="form-control" name="regras" value="${empresa.getRegras()}">
                                <c:if test="${regrasErro != null}">
                                    <span class="erro"><c:out value="${regrasErro}"/></span>
                                </c:if>
                            </div>
                            <div>
                                <c:choose>
                                    <c:when test="${empresa.getFoto() == null}">
                                        <label id="imagem">Carregar imagem: </label>
                                        <input class="form-control" type="file" name="foto">
                                    </c:when>
                                    <c:when test="${empresa.getFoto() != null}">
                                        <label id="imagem">Foto: </label>
                                        <div class="imagem-est" >
                                            <img src="${empresa.getFoto()}" width="300" height="200">
                                        </div>                                        
                                    </c:when>
                                </c:choose>
                            </div>
                        </fieldset>
                        <c:if test="${empresa.getAgendamento() == 'Sim'}">
                            <c:choose>
                                <c:when test="${empresa.getHorariosDisponiveis() != null }">               
                                    <ul class="horarios">
                                        <li><p>Horarios: disponiveis<c:out value="${empresa.getHorariosDisponiveis()}" /></p></li>
                                    </ul>
                                </c:when>
                                <c:otherwise>
                                    <p> Não tem nenhum interesse informado</p>
                                </c:otherwise>
                            </c:choose>   
                        </c:if>

                    </div>
                </form>

                <a class="btn btn-info" href="${pageContext.request.contextPath}/logout">Logout</a>

            </div>


            <footer class="footer">
                <c:import url="../footer.jsp"/>
            </footer>
        </c:if>
    </body>

    <c:if test="${sucesso != null}">
        <script>
            alert("Alterado com sucesso!");
        </script>
    </c:if>
</html>
