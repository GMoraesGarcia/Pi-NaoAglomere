<%-- 
    Document   : Form_Cad_Empresa
    Created on : 19/10/2020, 01:07:47
    Author     : leona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:h="http://xmlns.jcp.org/jsf/html" xmlns:f="http://xmlns.jcp.org/jsf/core">
    <head>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/Estilo.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/footer.css">
        <meta charset=UTF-8">

        <title>Cadastro de Empresa</title>
    </head>
    <body class="">
        <header class="header">
            <c:import url="../header.jsp"/>
        </header>

        <c:if test="${Erro != null}">
            <span class="erro"><c:out value="${Erro}"/></span>
        </c:if>
        <div>

            <form class="containerw" method="post" action="cad-empresa-salvar" novalidate>
                <h1>Dados da Empresa</h1>

                <div class="row">

                    <fieldset class="col-lg-6">
                        <div>
                            <label id="Nome_Empresa">Nome da empresa: </label>
                            <input type="text" class="form-control" name="nome_empresa" placeholder="Empresa" required value="${nome_empresa}">
                            <c:if test="${nomeErro != null}">
                                <span class="erro"><c:out value="${nomeErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="CNPJ">CPNJ: </label> <label id="modelo">XX.XXX.XXX/XXXX-XX</label>
                            <input type="text" class="form-control" id="CNPJtext" name="CNPJ" placeholder="12.345.678/9012-34"
                                   inputmode="numeric" minlength="18" maxlength="18" 
                                   pattern="^[0-9]{2}.?[0-9]{3}.?[0-9]{3}/?[0-9]{4}-?[0-9]{2}" required value="${CNPJ}">
                            <c:if test="${cnpjErro != null}">
                                <span class="erro"><c:out value="${cnpjErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="Email">E-mail Corporativo: </label>
                            <input type="email" class="form-control" name="email" placeholder="email@exemplo.com" 
                                   pattern="^[a-z0-9.]+@[a-z0-9]+\.[a-z]+(\.[a-z]+)?$" required value="${email}">
                            <c:if test="${emailErro != null}">
                                <span class="erro"><c:out value="${emailErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="senha">Senha: </label>
                            <input type="password" class="form-control" name="senha" placeholder="*******" required>
                            <c:if test="${senhaErro != null}">
                                <span class="erro"><c:out value="${senhaErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="confirmasenha">Confirmar senha: </label>
                            <input type="password" class="form-control" name="confirmasenha" placeholder="*******" required>
                            <c:if test="${confirmaErro != null}">
                                <span class="erro"><c:out value="${confirmaErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="telefone">Telefone: </label> <label id="modelo">(XX) XXXXX-XXXX ou (XX) XXXX-XXXX</label>
                            <input type="tel" class="form-control" name="telefone" placeholder="(11) 91234-5678" 
                                   pattern="(\([0-9]{2}\))\s([9]{1})?([0-9]{4})-([0-9]{4})"
                                   inputmode="numeric" minlength="14" maxlength="15" required value="${telefone}">
                            <c:if test="${telefoneErro != null}">
                                <span class="erro"><c:out value="${telefoneErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="descricao">Descrição: </label>
                            <select class="custom-select" id="descricaoop" name="descricao" required>
                                <option disabled selected>Selecione uma opção</option>
                                <option value="Mercado">Mercado</option>
                                <option value="Estúdio de tatuagem">Estúdio de tatuagem</option>
                                <option value="Cabelereiro">Cabelereiro</option>
                            </select>
                            <c:if test="${descricaoErro != null}">
                                <span class="erro"><c:out value="${descricaoErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label>Agendamento obrigatório:</label>
                            <select class="custom-select" id="agendamento" name="agendamento" required>
                                <option disabled selected>Selecione uma opção</option>
                                <option value="Sim">Sim</option>
                                <option value="Não">Não</option>
                            </select>
                            <c:if test="${agendamentoErro != null}">
                                <span class="erro"><c:out value="${agendamentoErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <input type="checkbox"/> Li e aceito os <a class="termos" 
                                                                       onclick="alert('Termos de responsabilidade:\n\Só poderá ser feito um agendamento por dia.')">termos de responsabilidade.</a>
                        </div>    

                        <div>
                            <button class="btn btn-success botoes botoes" type="submit" >Efetuar Cadastro</button> 
                            <button class=" btn btn-danger botoes" type="reset">Cancelar</button>
                        </div>
                    </fieldset>

                    <fieldset class="col-lg-6">
                        <div>
                            <label id="rua">Rua: </label>
                            <input type="text" class="form-control" name="rua" placeholder="Exemplo: Rua um" required
                                   value="${rua}">
                            <c:if test="${ruaErro != null}">
                                <span class="erro"><c:out value="${ruaErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="numero">Número: </label>
                            <input type="number" class="form-control" name="numero_rua" placeholder="Exemplo: 123" required
                                   value="${numero_rua}">
                            <c:if test="${numeroErro != null}">
                                <span class="erro"><c:out value="${numeroErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="bairro">Bairro: </label>
                            <input type="text" class="form-control" name="bairro" placeholder="Exemplo: Santo Amaro" required
                                   value="${bairro}">
                            <c:if test="${bairroErro != null}">
                                <span class="erro"><c:out value="${bairroErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="qtd_pessoas">Quantidade máxima de pessoas: </label>
                            <input type="number" class="form-control" name="qtd_pessoas" min="1" placeholder="Exemplo: 25" required
                                   value="${qtd_pessoas}">
                            <c:if test="${qtdErro != null}">
                                <span class="erro"><c:out value="${qtdErro}"/></span>
                            </c:if>
                        </div>             
                        <div>
                            <label id="regras">Regras: </label>
                            <textarea name="regras" class="form-control" rows="5" cols="64"
                                      placeholder="Exemplo: Proibida a entrada sem máscara" required></textarea>
                            <c:if test="${regrasErro != null}">
                                <span class="erro"><c:out value="${regrasErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <div class="row">
                                <div class="col-lg-6">
                                    <label>Horário de abertura:</label>
                                    <input type="time" class="form-control" required>
                                </div>
                                <div class="col-lg-6">
                                    <label>Horário de fechamento:</label>
                                    <input type="time" class="form-control" required>
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
        <footer class="footer">
            <c:import url="../footer.jsp"/>
        </footer>
    </body>
</html>
