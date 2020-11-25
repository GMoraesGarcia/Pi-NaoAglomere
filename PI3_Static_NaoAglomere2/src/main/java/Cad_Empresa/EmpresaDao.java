package Cad_Empresa;

import ConexãoBD.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDao {
    
    public void addNew(Cad_Empresa_dados empresaDados) throws SQLException {
        
        String sql = "INSERT INTO EMPRESA (NOME_EMPRESA, CNPJ, EMAIL, DESCRICAO, TELEFONE, SENHA, QTD_MAX,"
                + "RUA, BAIRRO, NUMERO, REGRAS, TIPO_CADASTRO, AGENDAMENTO"/*, FOTO*/ + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?" +/*,?*/ ")";

        //ConnectionUtilMySql
        try (Connection conn = ConnectionUtilMySql.obterConexao()) {
            
            conn.setAutoCommit(false);
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, empresaDados.getNome_empresa());
                stmt.setString(2, empresaDados.getCnpj());
                stmt.setString(3, empresaDados.getEmail());
                stmt.setString(4, empresaDados.getDescricao());
                stmt.setString(5, empresaDados.getTelefone());
                stmt.setString(6, empresaDados.getSenha());
                stmt.setInt(7, empresaDados.getQtd_max());
                stmt.setString(8, empresaDados.getRua());
                stmt.setString(9, empresaDados.getBairro());
                stmt.setInt(10, empresaDados.getNumero_rua());
                stmt.setString(11, empresaDados.getRegras());
                stmt.setString(12, "empresa"); // tipo de cadastro usado para login
                stmt.setString(13, empresaDados.getAgendamento());
                //stmt.setString(14, empresaDados.getFoto());

                stmt.executeUpdate();
                
                conn.commit();
                
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    public void update(Cad_Empresa_dados empresaDados) throws SQLException {
        
        String sql = "UPDATE EMPRESA SET NOME_EMPRESA = ?, EMAIL = ?, TELEFONE = ?, RUA = ?, NUMERO = ?, BAIRRO = ?,"
                + "QTD_MAX = ?, REGRAS = ?, DESCRICAO = ?, AGENDAMENTO = ? WHERE CNPJ = ?";
        
        try (Connection conn = ConnectionUtilMySql.obterConexao()) {
            
            conn.setAutoCommit(false);
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, empresaDados.getNome_empresa());
                stmt.setString(2, empresaDados.getEmail());
                stmt.setString(3, empresaDados.getTelefone());
                stmt.setString(4, empresaDados.getRua());
                stmt.setInt(5, empresaDados.getNumero_rua());
                stmt.setString(6, empresaDados.getBairro());
                stmt.setInt(7, empresaDados.getQtd_max());
                stmt.setString(8, empresaDados.getRegras());
                stmt.setString(9, empresaDados.getDescricao());
                stmt.setString(10, empresaDados.getAgendamento());
                //stmt.setString(11, empresaDados.getFoto());
                stmt.setString(11, empresaDados.getCnpj());
                
                stmt.executeUpdate();
                
                conn.commit();
                
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        
    }
    
    public void updateImage(Cad_Empresa_dados empresaDados) throws SQLException {
        
        String sql = "UPDATE EMPRESA SET FOTO = ? WHERE CNPJ = ?";
        
        try (Connection conn = ConnectionUtilMySql.obterConexao()) {
            
            conn.setAutoCommit(false);
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, empresaDados.getFoto());
                stmt.setString(2, empresaDados.getCnpj());
                
                stmt.executeUpdate();
                
                conn.commit();
                
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    public void addHorarios(Cad_Empresa_dados empresaDados) throws SQLException {
        String sql = "insert into HorariosDisponiveis (id_empresa,horarios_disponiveis,Status_horarios) values (?,?,?);";
        
        try (Connection conn = ConnectionUtilMySql.obterConexao()) {
            
            conn.setAutoCommit(false);
            
            int id = findLastEmp();
            
            for (int i = 0; i < empresaDados.getHorariosDisponiveis().size(); i++) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, id);
                    stmt.setString(2, empresaDados.getHorariosDisponiveis().get(i));
                    stmt.setString(3, "Livre");
                    stmt.executeUpdate();
                    conn.commit();
                    
                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                }
            }
            
        }
        
    }
    
    public int findLastEmp() throws SQLException {
        String sql = "select ID_empresa from empresa order by ID_empresa  desc  limit 1;";
        int id = 0;
        
        try (Connection conn = Connection_db2.obterConexao();) {
            try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    id = rs.getInt("ID_empresa");
                }
                
            } catch (SQLException e) {
                conn.rollback();
                throw e;
                
            }
        }
        
        return id;
    }
    
    public Cad_Empresa_dados findEmpresa(String cnpj) throws SQLException {
        Cad_Empresa_dados empresa = new Cad_Empresa_dados();
        
        String sql = "SELECT ID_EMPRESA,NOME_EMPRESA,CNPJ,EMAIL,DESCRICAO,TELEFONE,QTD_MAX,RUA,BAIRRO,NUMERO,REGRAS,AGENDAMENTO,FOTO FROM EMPRESA WHERE CNPJ = ?";
        
        try (Connection conn = Connection_db2.obterConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            
            stmt.setString(1, cnpj);
            
            try (ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    empresa.setEmpresa_id(rs.getInt("ID_EMPRESA"));
                    empresa.setNome_empresa(rs.getString("NOME_EMPRESA"));
                    empresa.setCnpj(rs.getString("CNPJ"));
                    empresa.setEmail(rs.getString("EMAIL"));
                    empresa.setDescricao(rs.getString("DESCRICAO"));
                    empresa.setTelefone(rs.getString("TELEFONE"));
                    empresa.setQtd_max(rs.getInt("QTD_MAX"));
                    empresa.setRua(rs.getString("RUA"));
                    empresa.setBairro(rs.getString("BAIRRO"));
                    empresa.setNumero_rua(rs.getInt("NUMERO"));
                    empresa.setRegras(rs.getString("REGRAS"));
                    empresa.setAgendamento(rs.getString("AGENDAMENTO"));
                    empresa.setFoto(rs.getString("FOTO"));
                    return empresa;
                }
            }
        }
        
        return empresa;
    }
    
}
