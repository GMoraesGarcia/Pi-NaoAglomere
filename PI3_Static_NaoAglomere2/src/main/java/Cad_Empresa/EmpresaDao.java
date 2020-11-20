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

    public void addNew(Cad_EmpresaDados empresaDados) throws SQLException {

        String sql = "INSERT INTO EMPRESA (NOME_EMPRESA, CNPJ, EMAIL, DESCRICAO, TELEFONE, SENHA, QTD_MAX,"
                + "RUA, BAIRRO, NUMERO, REGRAS, TIPO_CADASTRO, AGENDAMENTO, FOTO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //ConnectionUtilMySql
        try (Connection conn = ConnectionUtilMySql.obterConexao()) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

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
                stmt.setString(14, empresaDados.getFoto());

                stmt.executeUpdate();

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public void update(Cad_EmpresaDados empresaDados) throws SQLException {

        String sql = "UPDATE EMPRESA SET NOME_EMPRESA = ?, EMAIL = ?, TELEFONE = ?, RUA = ?, NUMERO = ?, BAIRRO = ?,"
                + "QTD_MAX = ?, REGRAS = ?, DESCRICAO = ?, AGENDAMENTO = ?, FOTO = ? WHERE CNPJ = ?";

        try (Connection conn = ConnectionUtilMySql.obterConexao()) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, empresaDados.getNome_Empresa());
                stmt.setString(2, empresaDados.getEmail());
                stmt.setString(3, empresaDados.getTelefone());
                stmt.setString(4, empresaDados.getRua());
                stmt.setInt(5, empresaDados.getNumero_Rua());
                stmt.setString(6, empresaDados.getBairro());
                stmt.setInt(7, empresaDados.getQtd_max());
                stmt.setString(8, empresaDados.getRegras());
                stmt.setString(9, empresaDados.getDescricao());
                stmt.setString(10, empresaDados.getAgendamento());
                stmt.setString(11, empresaDados.getFoto());
                stmt.setString(12, empresaDados.getCNPJ());

                stmt.executeUpdate();

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }

    }

    public void addHorario(Cad_EmpresaDados empresaDados) throws SQLException {
        String sql = "insert into HorariosDisponiveis (id_empresa,horario) values (?,?);";

        try (Connection conn = ConnectionUtilMySql.obterConexao()) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, empresaDados.getId());
                stmt.setString(2, empresaDados.getHoraAb());

                stmt.executeUpdate();

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw e;
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
}
