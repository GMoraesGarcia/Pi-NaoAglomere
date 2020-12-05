package Pesquisa;

import Cad_Empresa.Cad_Empresa_dados;
import ConexãoBD.ConnectionAzureMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class PesquisaDao {

    public PesquisarDados findEstabelecimento(String pesquisa) throws SQLException {

        LocalTime att = LocalTime.now();
        PesquisarDados busca = new PesquisarDados();
        CodigoDAO daoCod = new CodigoDAO();
        CodigoDados dadosCod = new CodigoDados();

        ArrayList<Cad_Empresa_dados> empresas = new ArrayList<>();

        String sql = "SELECT NOME_EMPRESA, EMAIL, DESCRICAO, TELEFONE, QTD_MAX, RUA, BAIRRO, NUMERO, REGRAS, AGENDAMENTO, ID_empresa,FOTO FROM EMPRESA where DESCRICAO LIKE '%" + pesquisa + "%' OR NOME_EMPRESA LIKE '%" + pesquisa + "%'";

        try (Connection conn = ConnectionAzureMysql.obterConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {// enquanto tiver empresas adiciona no array

                Cad_Empresa_dados empresa = new Cad_Empresa_dados();
                empresa.setNome_empresa(rs.getString("NOME_EMPRESA"));
                empresa.setEmail(rs.getString("EMAIL"));
                empresa.setDescricao(rs.getString("DESCRICAO"));
                empresa.setTelefone(rs.getString("TELEFONE"));
                empresa.setQtd_max(rs.getInt("QTD_MAX"));
                empresa.setRua(rs.getString("RUA"));
                empresa.setBairro(rs.getString("BAIRRO"));
                empresa.setNumero_rua(rs.getInt("NUMERO"));
                empresa.setRegras(rs.getString("REGRAS"));
                empresa.setAgendamento(rs.getString("AGENDAMENTO"));
                empresa.setEmpresa_id(rs.getInt("ID_empresa"));
                empresa.setFoto(rs.getString("FOTO"));

                //gera a quantidade de pessoas agendadas a partir da pesquisa
                int qtdAgend = getQtdAgendamentos(String.valueOf(empresa.getEmpresa_Id()));
                empresa.setQtdAgendamentos(qtdAgend);
                empresas.add(empresa);
                busca.setPesquisa(pesquisa);
                //gera e adiciona o codigo de cada empresa que não precisa realizar agendamento 
                if (empresa.getAgendamento().equalsIgnoreCase("não") && daoCod.isEmpty(empresa.getEmpresa_Id())
                        || daoCod.getAtualizacaoCodigo(empresa.getEmpresa_Id())) {
                    dadosCod.setCodigo(busca.gerarCodigo(empresa.getNome_empresa()));
                    dadosCod.setId(empresa.getEmpresa_Id());

                    daoCod.inserirInfoCodigo(dadosCod);
                }
                
                CodigoDados dadoscod = daoCod.getPrimeirohorario(empresa.getEmpresa_Id()); // faz a pesquisa do primeiro horário em que foi realizado o agendamento
                if (dadoscod.getHorario_Geracao() != null) {
                    att = dadoscod.getHorario_Geracao();// armazena esse horário em uma variavel
                    
                    if (dadoscod.getTempoLimite(att)) { // faz a verificação se o horário que foi passado ja se passou uma hora
                        att = LocalTime.now();// se já tives passado uma hora esse novo horário é armazenado
                        dadoscod.setHorario_Geracao(LocalTime.now());// e setado no objeto
                    }

                    CodigoDados qtd = daoCod.getQtdPessoas(att, empresa.getEmpresa_Id());// é feita uma pesquisa no banco para gerar a quantidade de pessoa a partir do horário passado
                    empresa.setQtdAgendamentos(qtd.getQuantidade());// novo horário é setado no objeto

                }
            }
            busca.setEstabelecimentos(empresas);//atualiza o array do objeto dados (referente a pesquisa)            
        }
        return busca; // retorna a pesquisa que foi encontrada ou dados nulos caso a pesquisa não for encontrada
    }

    public int getQtdAgendamentos(String IdEmpresa) throws SQLException {
        String sql = "call Sp_Qtd_Agend (CURRENT_DATE,?)";

        try (Connection conn = ConnectionAzureMysql.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, IdEmpresa);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    int res = rs.getInt("qtd_Agend");

                    return res;

                }

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        return -1;
    }

}
