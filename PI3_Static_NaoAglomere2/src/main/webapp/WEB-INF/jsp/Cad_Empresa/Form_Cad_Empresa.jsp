<%-- 
    Document   : Form_Cad_Empresa
    Created on : 19/10/2020, 01:07:47
    Author     : leona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/cad-empresa.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/footer.css">
        <meta charset=UTF-8">

        <style>
            #asterisco{
                color: red;
            }
            #modelo{
                opacity: 0.2;
            }
            .erro{
                color: red;
            }
        </style>
        
        <title>Cadastro de Empresa</title>
    </head>
    <body>
        <header class="header">
            <c:import url="../header.jsp"/>
        </header>
        <h1>Dados da Empresa</h1>
        <c:if test="${Erro != null}">
            <span class="erro"><c:out value="${Erro}"/></span>
        </c:if>
        <div>
            <form class="col-md-8 col-xl-9" method="post" action="cad-empresa-salvar" novalidate>

                <div class="row">

                    <fieldset class="col-lg-6">
                        <div>
                            <label id="Nome_Empresa">Nome da empresa: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="text" class="form-control" name="nome_empresa" required value="${nome_empresa}">
                            <c:if test="${nomeErro != null}">
                                <span class="erro"><c:out value="${nomeErro}"/></span>
                            </c:if>
                        </div>
                        <p></p>
                        <div>
                            <label id="CNPJ">CPNJ: </label> <label id="asterisco">*</label> <label id="modelo">XX.XXX.XXX/XXXX-XX</label>
                            <br>
                            <input type="text" class="form-control" id="CNPJtext" name="CNPJ" placeholder="12.345.678/9012-34"
                                   inputmode="numeric" minlength="18" maxlength="18" 
                                   pattern="^[0-9]{2}.?[0-9]{3}.?[0-9]{3}/?[0-9]{4}-?[0-9]{2}" required value="${CNPJ}">
                            <c:if test="${cnpjErro != null}">
                                <span class="erro"><c:out value="${cnpjErro}"/></span>
                            </c:if>
                        </div>
                        <p></p>
                        <div>
                            <label id="Email">E-mail Corporativo: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="email" class="form-control" name="email" placeholder="email@exemplo.com" 
                                   pattern="^[a-z0-9.]+@[a-z0-9]+\.[a-z]+(\.[a-z]+)?$" required value="${email}">
                            <c:if test="${emailErro != null}">
                                <span class="erro"><c:out value="${emailErro}"/></span>
                            </c:if>
                        </div>
                        <p></p>
                        <div>
                            <label id="senha">Senha: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="password" class="form-control" name="senha" placeholder="*******" required>
                            <c:if test="${senhaErro != null}">
                                <span class="erro"><c:out value="${senhaErro}"/></span>
                            </c:if>
                        </div>
                        <p></p>
                        <div>
                            <label id="confirmasenha">Confirmar senha: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="password" class="form-control" name="confirmasenha" placeholder="*******" required>
                            <c:if test="${confirmaErro != null}">
                                <span class="erro"><c:out value="${confirmaErro}"/></span>
                            </c:if>
                        </div>
                        <p></p>
                        <div>
                            <label id="telefone">Telefone: </label> <label id="asterisco">*</label> <label id="modelo">(XX) XXXXX-XXXX ou (XX) XXXX-XXXX</label> 
                            <br>
                            <input type="tel" class="form-control" name="telefone" placeholder="(11) 91234-5678" 
                                   pattern="(\([0-9]{2}\))\s([9]{1})?([0-9]{4})-([0-9]{4})"
                                   inputmode="numeric" minlength="14" maxlength="15" required value="${telefone}">
                            <c:if test="${telefoneErro != null}">
                                <span class="erro"><c:out value="${telefoneErro}"/></span>
                            </c:if>
                        </div>
                        <p></p>
                        <div>
                            <label id="descricao">Descrição: </label> <label id="asterisco">*</label>
                            <br>
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
                        <p></p>
                        <input type="submit" value="Efetuar cadastro"> <input type="reset" value="Cancelar">
                    </fieldset>

                    <fieldset class="col-lg-6">
                        <div>
                            <label id="rua">Rua: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="text" class="form-control" name="rua" placeholder="Exemplo: Rua um" required
                                   value="${rua}">
                            <c:if test="${ruaErro != null}">
                                <span class="erro"><c:out value="${ruaErro}"/></span>
                            </c:if>
                        </div>
                        <p></p>
                        <div>
                            <label id="numero">Número: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="number" class="form-control" name="numero_rua" placeholder="Exemplo: 123" required
                                   value="${numero_rua}">
                            <c:if test="${numeroErro != null}">
                                <span class="erro"><c:out value="${numeroErro}"/></span>
                            </c:if>
                        </div>
                        <p></p>
                        <div>
                            <label id="bairro">Bairro: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="text" class="form-control" name="bairro" placeholder="Exemplo: Santo Amaro" required
                                   value="${bairro}">
                            <c:if test="${bairroErro != null}">
                                <span class="erro"><c:out value="${bairroErro}"/></span>
                            </c:if>
                        </div>
                        <p></p>
                        <div>
                            <label id="qtd_pessoas">Quantidade máxima de pessoas: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="number" class="form-control" name="qtd_pessoas" min="1" placeholder="Exemplo: 25" required
                                   value="${qtd_pessoas}">
                            <c:if test="${qtdErro != null}">
                                <span class="erro"><c:out value="${qtdErro}"/></span>
                            </c:if>
                        </div>
                        <p></p>                        
                        <div>
                            <label id="regras">Regras: </label> <label id="asterisco">*</label>
                            <br>
                            <textarea name="regras" class="form-control" rows="5" cols="64"
                                      placeholder="Exemplo: Proibida a entrada sem máscara" required></textarea>
                            <c:if test="${regrasErro != null}">
                                <span class="erro"><c:out value="${regrasErro}"/></span>
                            </c:if>
                        </div>
                        <p></p>
                        <div>
                            <label id="imagem">Carregar imagem: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="file" name="imagem">
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
