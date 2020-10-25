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
        <title>Cadastro de Usuários</title>
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
                    <input type="text" class="form-control" name="cpf" value="${cpf}" placeholder="Digite apenas Números">
                    <c:if test="${cpfErro != null}">
                        <span class="erro"><c:out value="${cpfErro}"/></span> 
                    </c:if>
                </div>
                <div>
                    <label>E-mail: </label>
                    <input type="email" class="form-control"  name="email" value="${email}">
                    <c:if test="${emailErro != null}">
                        <span class="erro"><c:out value="${emailErro}"/></span> 
                    </c:if>

                </div>
                <div>
                    <label>Data de Nascimento:</label>
                    <input type="date" class="form-control" name="dataNascimento" value="${dataNascimento}">
                     <c:if test="${dtNascimentoErro != null}">
                        <span class="erro"><c:out value="${dtNascimentoErro}"/></span> 
                    </c:if>
                </div>
                <div>
                    <label>Telefone:</label>
                    <input type="text" class="form-control" placeholder="( )_____-____" name="telefone" value="${telefone}"
                         >
                    <c:if test="${telefoneErro != null}">
                        <span class="erro"><c:out value="${telefoneErro}"/></span> 
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
                    <label>Confirmar Senha:</label>
                    <input type="password" class="form-control" name="confirmarSenha" value="${confirmarSenha}">
                    <c:if test="${ConfirmSenhaErro != null}">
                        <span class="erro"><c:out value="${ConfirmSenhaErro}"/></span>
                    </c:if>
                        <c:if test="${add != null}">
                            <span><c:out value="${add}/"/></span>
                        </c:if>

                </div>
                <div>
                    <button type="submit" class="btn btn-success botoes">Efetuar Cadastro</button>
                    <button type="reset" class="btn btn-danger botoes">Cancelar</button>

                </div>
            </form>
        </div>
        
        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>
    </body>
</html>
