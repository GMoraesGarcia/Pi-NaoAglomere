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
        <meta charset=UTF-8">
        <title>Cadastro de Empresa</title>
    </head>
    <body>
        <h1>Dados da Empresa</h1>
        <div>
            <form class="col-md-8 col-xl-9" method="post">

                <div class="row">

                    <fieldset class="col-lg-6">
                        <div>
                            <label id="Nome_Empresa">Nome da empresa: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="text" class="form-control" name="nome_empresa" required>
                        </div>
                        <p></p>
                        <div>
                            <label id="CNPJ">CPNJ: </label> <label id="asterisco">*</label> <label id="modelo">XX.XXX.XXX/XXXX-XX</label>
                            <br>
                            <input type="text" class="form-control" id="CNPJtext" name="CNPJ" placeholder="12.345.678/9012-34"
                                   inputmode="numeric" minlength="18" maxlength="18" 
                                   pattern="^[0-9]{2}.?[0-9]{3}.?[0-9]{3}/?[0-9]{4}-?[0-9]{2}" required>
                        </div>
                        <p></p>
                        <div>
                            <label id="Email">E-mail Corporativo: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="email" class="form-control" name="email" placeholder="email@exemplo.com" 
                                   pattern="^[a-z]@?[a-z]"  required>
                        </div>
                        <p></p>
                        <div>
                            <label id="senha">Senha: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="password" class="form-control" name="senha" placeholder="*******" required>
                        </div>
                        <p></p>
                        <div>
                            <label id="confirmasenha">Confirmar senha: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="password" class="form-control" name="confirmasenha" placeholder="*******" required>
                        </div>
                        <p></p>
                        <div>
                            <label id="telefone">Telefone: </label> <label id="asterisco">*</label> <label id="modelo">(XX)XXXXX-XXXX</label> 
                            <br>
                            <input type="tel" class="form-control" name="telefone" placeholder="11 91234-5678" 
                                   inputmode="numeric" minlength="13" maxlength="14" required>
                        </div>
                        <p></p>
                        <div>
                            <label id="descricao">Descrição: </label> <label id="asterisco">*</label>
                            <br>
                            <select class="custom-select" id="descricaoop" name="descricao" required>
                                <option disabled selected>Selecione uma opção</option>
                                <option value="mercado">Mercado</option>
                                <option value="estudio_tatuagem">Estúdio de tatuagem</option>
                                <option value="cabelereiro">Cabelereiro</option>
                            </select>
                        </div>
                        <p></p>
                        <input type="submit" value="Efetuar cadastro"> <input type="reset" value="Cancelar">
                    </fieldset>

                    <fieldset class="col-lg-6">
                        <div>
                            <label id="rua">Rua: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="text" class="form-control" name="rua" placeholder="Exemplo: Rua um" required>
                        </div>
                        <p></p>
                        <div>
                            <label id="numero">Número </label> <label id="asterisco">*</label>
                            <br>
                            <input type="number" class="form-control" name="numero_rua" placeholder="Exemplo: 123" required>
                        </div>
                        <p></p>
                        <div>
                            <label id="bairro">Bairro: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="text" class="form-control" name="bairro" placeholder="Exemplo: Santo Amaro" required>
                        </div>
                        <p></p>
                        <div>
                            <label id="qtd_pessoas">Quantidade máxima de pessoas: </label> <label id="asterisco">*</label>
                            <br>
                            <input type="number" class="form-control" name="qtd_pessoas" placeholder="Exemplo: 25" required>
                        </div>
                        <p></p>                        
                        <div>
                            <label id="regras">Regras: </label> <label id="asterisco">*</label>
                            <br>
                            <textarea name="regras" class="form-control" rows="5" cols="64" placeholder="Exemplo: Proibida a 
                                      entrada sem máscara" required></textarea>
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

    </body>
</html>
