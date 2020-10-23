package Cad_Empresa;

import ConexãoBD.ConnectionUtilMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDao {

    public List<Cad_EmpresaDados> findAll() throws SQLException {

        String sql = "";
        
        List<Cad_EmpresaDados> resultados = new ArrayList<>();

        try (Connection conn = ConnectionUtilMySql.obterConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cad_EmpresaDados empresaDados = new Cad_EmpresaDados();
                empresaDados.setID(rs.getInt("id"));
                empresaDados.setNome_Empresa(rs.getString("nome_empresa"));
                empresaDados.setCNPJ(rs.getString("cnpj"));
                
                
                resultados.add(empresaDados);
            }
        }
        return resultados;
    }

    public void addNew(Cad_EmpresaDados empresaDados) throws SQLException {

        String sql = "INSERT INTO EMPRESA (NOME_EMPRESA, CNPJ, EMAIL, DESCRICAO, TELEFONE, SENHA, QTD_MAX,"
                + "RUA, BAIRRO, NUMERO, REGRAS) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = ConnectionUtilMySql.obterConexao()) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, empresaDados.getNome_Empresa());
                stmt.setString(2, empresaDados.getCNPJ());
                stmt.setString(3, empresaDados.getEmail());
                stmt.setString(4, empresaDados.getDescricao());
                stmt.setString(5, empresaDados.getTelefone());
                stmt.setString(6, empresaDados.getSenha());
                stmt.setInt(7, empresaDados.getQtd_max());
                stmt.setString(8, empresaDados.getRua());
                stmt.setString(9, empresaDados.getBairro());
                stmt.setInt(10, empresaDados.getNumero_Rua());
                stmt.setString(11, empresaDados.getRegras());

                int resultados = stmt.executeUpdate();

                /*try (ResultSet rs = stmt.getGeneratedKeys()) {

                    while (rs.next()) {
                        Integer idGerado = rs.getInt(1);
                        empresaDados.setID(idGerado);
                    }
                }*/
                
                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
}
