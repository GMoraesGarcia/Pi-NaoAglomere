package Pesquisa;

import Cad_Empresa.Cad_EmpresaDados;
import ConexãoBD.Connection_db2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class PesquisaDao {

    public PesquisarDados findEstabelecimento(String pesquisa) throws SQLException {

        PesquisarDados busca = new PesquisarDados();

        ArrayList<Cad_EmpresaDados> empresas = new ArrayList<>();

        String sql = "SELECT NOME_EMPRESA, EMAIL, DESCRICAO, TELEFONE, QTD_MAX, RUA, BAIRRO, NUMERO, REGRAS, AGENDAMENTO,ID_empresa FROM EMPRESA where DESCRICAO LIKE '%" + pesquisa + "%' OR NOME_EMPRESA LIKE '%" + pesquisa + "%'";

        try (Connection conn = Connection_db2.obterConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {// enquanto tiver empresas adiciona no array
                Cad_EmpresaDados empresa = new Cad_EmpresaDados();
                empresa.setNome_Empresa(rs.getString("NOME_EMPRESA"));
                empresa.setEmail(rs.getString("EMAIL"));
                empresa.setDescricao(rs.getString("DESCRICAO"));
                empresa.setTelefone(rs.getString("TELEFONE"));
                empresa.setQtd_max(rs.getInt("QTD_MAX"));
                empresa.setRua(rs.getString("RUA"));
                empresa.setBairro(rs.getString("BAIRRO"));
                empresa.setNumero_Rua(rs.getInt("NUMERO"));
                empresa.setRegras(rs.getString("REGRAS"));
                empresa.setAgendamento(rs.getString("AGENDAMENTO"));
              //  empresa.setId(rs.getInt("ID_empresa"));
                empresas.add(empresa);
                busca.setPesquisa(pesquisa);
            }
            busca.setEstabelecimentos(empresas);//atualiza o array do objeto dados (referente a pesquisa)            
        }
        return busca; // retorna a pesquisa que foi encontrada ou dados nulos caso a pesquisa não for encontrada
    }

}
