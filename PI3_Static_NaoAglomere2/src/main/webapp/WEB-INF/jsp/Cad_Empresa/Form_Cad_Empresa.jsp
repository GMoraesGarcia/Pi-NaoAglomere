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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
        
        <script type="text/javascript">
            $("#telefone").mask("(00) 00000-0000");
            
        </script>
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
                            <label id="CNPJ">CPNJ:</label> <label id="modelo">XX.XXX.XXX/XXXX-XX</label>
                            <input type="text" class="form-control" id="CNPJtext" name="CNPJ" placeholder="12.345.678/9012-34"
                                   inputmode="numeric" minlength="18" maxlength="18" value="${CNPJ}">
                            <c:if test="${cnpjErro != null}">
                                <span class="erro"><c:out value="${cnpjErro}"/></span>
                            </c:if>
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
                            <label id="telefone">Telefone:</label> <label id="modelo">(XX) XXXXX-XXXX ou (XX) XXXX-XXXX</label>
                            <input id="telefone" type="tel" class="form-control" name="telefone" placeholder="(11) 91234-5678" value="${telefone}" >
                            <c:if test="${telefoneErro != null}">
                                <span class="erro"><c:out value="${telefoneErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label id="descricao">Descrição:</label>
                            <select class="custom-select" id="descricaoop" name="descricao">
                                <option disabled selected>Selecione uma opção</option>
                                <option value="Mercado">Mercado</option>
                                <option value="Bar ou Restaurante">Bar ou Restaurante</option>
                                <option value="Loja">Loja</option>
                                <option value="Salão de Beleza ou Estudio">Salão de Beleza ou Estudio</option>
                                <option value="Banco">Banco</option>
                            </select> 
                           <c:if test="${descricaoErro != null}">
                                <span class="erro"><c:out value="${descricaoErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <label>Agendamento obrigatório:</label>
                            <select class="custom-select" id="agendamento" name="agendamento">
                                <option disabled selected>Selecione uma opção</option>
                                <option value="Sim">Sim</option>
                                <option value="Não">Não</option>
                            </select>
                            <c:if test="${agendamentoErro != null}">
                                <span class="erro"><c:out value="${agendamentoErro}"/></span>
                            </c:if>
                        </div>
                        <div>
                            <input name="check" type="checkbox"> Li e aceito a <button type="button" class="btn btn-link" data-toggle="modal" data-target="#politica">Política de privacidade</button> e os <button type="button" class="btn btn-link" data-toggle="modal" data-target="#termo">termos de uso</button>
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
                                        <h4 class="modal-title">Política de privacidade</h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>
                                    <!-- Modal body -->
                                    <div class="modal-body">
                                        A sua privacidade é importante para nós. É política do Não Aglomere respeitar a sua privacidade em relação a qualquer informação sua que possamos coletar no nosso site. Não compartilhamos informações de identificação pessoal publicamente ou com terceiros, exceto quando exigido por lei.
                                        <br>O nosso site pode ter links para sites externos que não são operados por nós. Esteja ciente de que não temos controle sobre o conteúdo e práticas desses sites e não podemos aceitar responsabilidade por suas respectivas políticas de privacidade.
                                        <br>O uso continuado de nosso site será considerado como aceitação de nossas práticas em torno de privacidade e informações pessoais.
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
                                        Ao utilizar os serviços do Não Aglomere, você concorda em não agir com más intenções ou fazer mal uso da plataforma para quaisquer outros fins que não sejam:
                                        <br>1-Realizar cadastro na plataforma.
                                        <br>2-Fazer consultas de estabelecimentos com fins de se informar sobre a situação do mesmo.
                                        <br>3-Gerar código de ida ao estabelecimento.*
                                        <br>4-Realizar agendamento de ida ao estabelecimento.**
                                        <br>5-Ser informado sobre novidades em relação ao COVID-19.
                                        <br>*Só será possível gerar um único código por pessoa em cada estabelecimento a cada dia, os estabelecimentos poderão oferecer benefícios que julguem condizentes com a geração e apresentação do código no endereço cadastrado no site.
                                        <br>**Caso a empresa reporte o não comparecimento ou o usuário não informe a empresa, na primeira vez haverá uma advertência, na próxima ocorrência o cadastro será suspenso.
                                        <br>Mais informações em contatonaoaglomere@gmail.com
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
                            <label id="numero">Número:</label>
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
                            <label id="qtd_pessoas">Quantidade máxima de pessoas:</label>
                            <input type="number" class="form-control" name="qtd_pessoas" placeholder="Exemplo: 25" value="${qtd_pessoas}">
                            <c:if test="${qtdErro != null}">
                                <span class="erro"><c:out value="${qtdErro}"/></span>
                            </c:if>
                        </div>             
                        <div>
                            <label id="regras">Regras:</label>
                            <textarea name="regras" class="form-control" rows="5" cols="64"
                                      placeholder="Exemplo: Proibida a entrada sem máscara"></textarea>
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
