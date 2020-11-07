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
        <c:if test="${dados.getTipo_cadastro() == 'usuário'}">
            <h1>Bem Vindo(a)! <c:out value="${user.getNome()}"/></h1>
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
        </c:if>

        <c:if test="${dados.getTipo_cadastro() == 'empresa'}">
            <h1>Bem Vindo(a)! <c:out value="${empresa.getNome_Empresa()}"/></h1>
            <div class="containerw">
                <form class="form-group">
                    <div class="row">
                        <fieldset class="col-lg-6">
                            <div>
                                <label id="Nome_Empresa">Nome da empresa:</label>
                                <input type="text" class="form-control" name="nome_empresa" value="${empresa.getNome_Empresa()}">
                            </div>
                            <div>
                                <label id="CNPJ">CPNJ:</label>
                                <input type="text" class="form-control" id="CNPJtext" name="CNPJ" value="${empresa.getCNPJ()}" disabled>
                            </div>
                            <div>
                                <label id="Email">E-mail Corporativo:</label>
                                <input type="email" class="form-control" name="email" required value="${empresa.getEmail()}">
                            </div>
                            <div>
                                <label id="telefone">Telefone:</label> <label id="modelo">(XX) XXXXX-XXXX ou (XX) XXXX-XXXX</label>
                                <input type="tel" class="form-control" name="telefone" required value="${empresa.getTelefone()}">
                            </div>
                            <div>
                                <label id="descricao">Descrição:</label>
                                <input type="text" class="form-control" name="descricao" required value="${empresa.getDescricao()}">
                            </div>
                            <div>
                                <label>Agendamento obrigatório:</label>
                                <input type="text" class="form-control" name="agendamento" required value="${empresa.getAgendamento()}">
                            </div>

                            <div>
                                <button class="btn btn-success botoes botoes" type="submit" >Alterar</button>
                            </div>
                        </fieldset>

                        <fieldset class="col-lg-6">
                            <div>
                                <label id="rua">Rua:</label>
                                <input type="text" class="form-control" name="rua" value="${empresa.getRua()}">
                            </div>
                            <div>
                                <label id="numero">Número:</label>
                                <input type="number" class="form-control" name="numero_rua" value="${empresa.getNumero_Rua()}">
                            </div>
                            <div>
                                <label id="bairro">Bairro:</label>
                                <input type="text" class="form-control" name="bairro" value="${empresa.getBairro()}">
                            </div>
                            <div>
                                <label id="qtd_pessoas">Quantidade máxima de pessoas:</label>
                                <input type="number" class="form-control" name="qtd_pessoas" value="${empresa.getQtd_max()}">
                            </div>             
                            <div>
                                <label id="regras">Regras:</label>
                                <input name="regras" class="form-control" required value="${empresa.getRegras()}">
                            </div>
                            <div>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <label>Horário de abertura:</label>
                                        <input type="time" class="form-control" required value="">
                                    </div>
                                    <div class="col-lg-6">
                                        <label>Horário de fechamento:</label>
                                        <input type="time" class="form-control" required value="">
                                    </div>
                                </div>
                            </div>
                            <div>
                                <label id="imagem">Carregar imagem: </label>
                                <input  type="file" name="imagem">
                            </div>
                        </fieldset>
                    </div>

                </form> 
            </div>
        </c:if>
        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>
    </body>
</html>
