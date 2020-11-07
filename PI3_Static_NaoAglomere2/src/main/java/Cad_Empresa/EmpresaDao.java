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

    public void addNew(Cad_EmpresaDados empresaDados) throws SQLException {

        String sql = "INSERT INTO EMPRESA (NOME_EMPRESA, CNPJ, EMAIL, DESCRICAO, TELEFONE, SENHA, QTD_MAX,"
                + "RUA, BAIRRO, NUMERO, REGRAS, TIPO_CADASTRO, AGENDAMENTO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
                stmt.setString(12, "empresa"); // tipo de cadastro usado para login
                stmt.setString(13, empresaDados.getAgendamento());
                
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
