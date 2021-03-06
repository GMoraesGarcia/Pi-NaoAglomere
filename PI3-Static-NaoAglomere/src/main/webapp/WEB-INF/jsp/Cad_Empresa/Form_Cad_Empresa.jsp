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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/Estilo.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/footer.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>

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

            <form class="containerw" method="post" action="cad-empresa-salvar" novalidate enctype="multipart/form-data">
                <h1>Dados da Empresa</h1>

                <div class="row">

                    <fieldset class="col-lg-6">
                        <div>
                            <label id="Nome_Empresa">Nome da empresa:</label>
                            <input type="text" class="form-control" name="nome_empresa" placeholder="Empresa" value="${nome_empresa}">
                            <c:if test="${nomeErro != null}">
                                <span class="erro"><c:out value="${nomeErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="CNPJ">CPNJ:</label>
                            <input type="text" class="form-control" id="CNPJtext" name="CNPJ" placeholder="12.345.678/9012-34"
                                   inputmode="numeric" minlength="18" maxlength="18" value="${CNPJ}">
                            <c:if test="${cnpjErro != null}">
                                <span class="erro"><c:out value="${cnpjErro}"/></span>
                            </c:if>
                            <script>
                                $("#CNPJtext").mask("00.000.000/0000-00");
                            </script>
                        </div>
                        <div>
                            <label id="Email">E-mail Corporativo:</label>
                            <input type="email" class="form-control" name="email" placeholder="email@exemplo.com" value="${email}">
                            <c:if test="${emailErro != null}">
                                <span class="erro"><c:out value="${emailErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="senha">Senha:</label>
                            <input type="password" class="form-control" name="senha" placeholder="*******">
                            <c:if test="${senhaErro != null}">
                                <span class="erro"><c:out value="${senhaErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="confirmasenha">Confirmar senha:</label>
                            <input type="password" class="form-control" name="confirmasenha" placeholder="*******" required>
                            <c:if test="${confirmaErro != null}">
                                <span class="erro"><c:out value="${confirmaErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label>Telefone:</label>
                            <input id="telefone" type="text" class="form-control" name="telefone" placeholder="(11) 91234-5678" value="${telefone}">
                            <c:if test="${telefoneErro != null}">
                                <span class="erro"><c:out value="${telefoneErro}"/></span>
                            </c:if>
                            <script>
                                $("#telefone").mask("(00) 00000-0000");
                            </script>
                        </div>
                        <div>
                            <label id="descricao">Descri????o:</label>
                            <select class="custom-select" id="descricaoop" name="descricao">
                                <option disabled selected>Selecione uma op????o</option>
                                <option value="Mercados">Mercados</option>
                                <option value="Bares e restaurantes">Bares e Restaurantes</option>
                                <option value="Lojas">Lojas</option>
                                <option value="Sal??es de Beleza e Estudios">Sal??es de Beleza e Estudios</option>
                                <option value="Bancos">Bancos</option>
                            </select> 
                            <c:if test="${descricaoErro != null}">
                                <span class="erro"><c:out value="${descricaoErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label>Agendamento obrigat??rio:</label>
                            <select class="custom-select" id="agendamento" name="agendamento">
                                <option disabled selected>Selecione uma op????o</option>
                                <option value="Sim">Sim</option>
                                <option value="N??o">N??o</option>
                            </select>
                            <c:if test="${agendamentoErro != null}">
                                <span class="erro"><c:out value="${agendamentoErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <input name="check" type="checkbox"> Li e aceito a <button type="button" class="btn-link" data-toggle="modal" data-target="#politica">Pol??tica de privacidade</button> e os <button type="button" class="btn-link" data-toggle="modal" data-target="#termo">termos de uso</button>
                            <c:if test="${checkErro != null}">
                                <span class="erro"><c:out value="${checkErro}"/></span>
                            </c:if>
                        </div>

                        <!-- The Modal -->
                        <div class="modal" id="politica">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">Pol??tica de privacidade</h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>
                                    <!-- Modal body -->
                                    <div class="modal-body">
                                        A sua privacidade ?? importante para n??s. ?? pol??tica do N??o Aglomere respeitar a sua privacidade em rela????o a qualquer informa????o sua que possamos coletar no nosso site. N??o compartilhamos informa????es de identifica????o pessoal publicamente ou com terceiros, exceto quando exigido por lei.
                                        <br>O nosso site pode ter links para sites externos que n??o s??o operados por n??s. Esteja ciente de que n??o temos controle sobre o conte??do e pr??ticas desses sites e n??o podemos aceitar responsabilidade por suas respectivas pol??ticas de privacidade.
                                        <br>O uso continuado de nosso site ser?? considerado como aceita????o de nossas pr??ticas em torno de privacidade e informa????es pessoais.
                                    </div>

                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- The Modal -->
                        <div class="modal" id="termo">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">Termos de uso</h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>
                                    <!-- Modal body -->
                                    <div class="modal-body">
                                        Ao utilizar os servi??os do N??o Aglomere, voc?? concorda em n??o agir com m??s inten????es ou fazer mal uso da plataforma para quaisquer outros fins que n??o sejam:
                                        <br>1-Realizar cadastro na plataforma.
                                        <br>2-Fazer consultas de estabelecimentos com fins de se informar sobre a situa????o do mesmo.
                                        <br>3-Gerar c??digo de ida ao estabelecimento.*
                                        <br>4-Realizar agendamento de ida ao estabelecimento.**
                                        <br>5-Ser informado sobre novidades em rela????o ao COVID-19.
                                        <br>*S?? ser?? poss??vel gerar um ??nico c??digo por pessoa em cada estabelecimento a cada dia, os estabelecimentos poder??o oferecer benef??cios que julguem condizentes com a gera????o e apresenta????o do c??digo no endere??o cadastrado no site.
                                        <br>**Caso a empresa reporte o n??o comparecimento ou o usu??rio n??o informe a empresa, na primeira vez haver?? uma advert??ncia, na pr??xima ocorr??ncia o cadastro ser?? suspenso.
                                        <br>Mais informa????es em contatonaoaglomere@gmail.com
                                    </div>
                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div>
                            <button class="btn btn-success botoes botoes" type="submit" >Efetuar Cadastro</button> 
                            <button class=" btn btn-danger botoes" type="reset">Cancelar</button>
                        </div>
                    </fieldset>

                    <fieldset class="col-lg-6">
                        <div>
                            <label id="rua">Rua:</label>
                            <input type="text" class="form-control" name="rua" placeholder="Exemplo: Rua um" value="${rua}">
                            <c:if test="${ruaErro != null}">
                                <span class="erro"><c:out value="${ruaErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="numero">N??mero:</label>
                            <input type="number" class="form-control" name="numero_rua" placeholder="Exemplo: 123" value="${numero_rua}">
                            <c:if test="${numeroErro != null}">
                                <span class="erro"><c:out value="${numeroErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="bairro">Bairro:</label>
                            <input type="text" class="form-control" name="bairro" placeholder="Exemplo: Santo Amaro" value="${bairro}">
                            <c:if test="${bairroErro != null}">
                                <span class="erro"><c:out value="${bairroErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="qtd_pessoas">Quantidade m??xima de pessoas:</label>
                            <input type="number" class="form-control" name="qtd_pessoas" placeholder="Exemplo: 25" value="${qtd_pessoas}">
                            <c:if test="${qtdErro != null}">
                                <span class="erro"><c:out value="${qtdErro}"/></span>
                            </c:if>
                        </div>             
                        <div>
                            <label id="regras">Regras:</label>
                            <textarea name="regras" class="form-control" rows="5" cols="64"
                                      placeholder="Exemplo: Proibida a entrada sem m??scara"></textarea>
                            <c:if test="${regrasErro != null}">
                                <span class="erro"><c:out value="${regrasErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label >Carregar imagem:</label>
                            <input type="file" id="imagem" name="foto" class="form-control">
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
