package Pesquisa;

import Cad_Empresa.Cad_Empresa_dados;
import Cad_Empresa.EmpresaDao;
import ConexãoBD.ConnectionUtilMySql;
import ConexãoBD.Connection_db2;
import Perfil.GerenciarDados;
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

        ArrayList<Cad_Empresa_dados> empresas = new ArrayList<>();

        String sql = "SELECT NOME_EMPRESA, EMAIL, DESCRICAO, TELEFONE, QTD_MAX, RUA, BAIRRO, NUMERO, REGRAS, AGENDAMENTO, ID_empresa FROM EMPRESA where DESCRICAO LIKE '%" + pesquisa + "%' OR NOME_EMPRESA LIKE '%" + pesquisa + "%'";

        try (Connection conn = Connection_db2.obterConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            //String idEmp = rs.getString("ID_empresa");
           
           

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
                 int qtdAgend = getQtdAgendamentos(String.valueOf(empresa.getEmpresa_Id()));
                 empresa.setQtdAgendamentos(qtdAgend);
                empresas.add(empresa);
                busca.setPesquisa(pesquisa);
            }
            busca.setEstabelecimentos(empresas);//atualiza o array do objeto dados (referente a pesquisa)            
        }
        return busca; // retorna a pesquisa que foi encontrada ou dados nulos caso a pesquisa não for encontrada
    }
    
    public int getQtdAgendamentos(String IdEmpresa) throws SQLException {
        String sql = "call Sp_Qtd_Agend (sysdate(),?)";

          try (Connection conn = Connection_db2.obterConexao();
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
    
    public int getQtdPessoas(String iDEmpresa) throws SQLException{
        EmpresaDao dao = new EmpresaDao();
     String nomeEmp =  dao.findyByID(iDEmpresa);
        
        
        return -1;
    }

}
