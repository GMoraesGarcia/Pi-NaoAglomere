package Perfil;

import Cad_Empresa.Cad_Empresa_dados;
import ConexãoBD.Connection_db2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author leona
 */
public class GerenciarDAO {

    public ArrayList<GerenciarDados> User(String cpf) throws SQLException {

        ArrayList<GerenciarDados> agendamentos = new ArrayList<>();

        String sql = "call Sp_selectAgendUser(?)";

        try (Connection conn = Connection_db2.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    GerenciarDados agendamento = new GerenciarDados();

                    String data = (rs.getString("data_agend"));
                    String dataFormat = agendamento.getDataFormatada(data);

                    agendamento.setNumAgendamento(rs.getString("num_agendamento"));
                    agendamento.setNomeUser(rs.getString("nome"));
                    agendamento.setNomeEmpresa(rs.getString("nome_empresa"));
                    agendamento.setData(dataFormat);
                    agendamento.setHorario(rs.getString("horario"));

                    agendamentos.add(agendamento);

                }

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        return agendamentos;
    }

    public ArrayList<GerenciarDados> Empresa(String cnpj) throws SQLException {

        ArrayList<GerenciarDados> agendamentos = new ArrayList<>();

        String sql = "call Sp_selectAgentEmpresa(?)";

        try (Connection conn = Connection_db2.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, cnpj);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    GerenciarDados agendamento = new GerenciarDados();
                    String data = (rs.getString("data_agend"));
                    String dataFormat = agendamento.getDataFormatada(data);

                    agendamento.setNomeUser(rs.getString("nome"));
                    agendamento.setNomeEmpresa(rs.getString("nome_empresa"));
                    agendamento.setData(dataFormat);
                    agendamento.setHorario(rs.getString("horario"));

                    agendamentos.add(agendamento);
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;

            }
        }
        return agendamentos;
    }

    public void Delete(int numAgendamento) throws SQLException {

        String sql = "DELETE FROM AGENDAMENTO WHERE NUM_AGENDAMENTO = ?";

        try (Connection conn = Connection_db2.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numAgendamento);

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e);
        }
    }

    public ArrayList<Cad_Empresa_dados> Codigos(String cpf) throws SQLException {
        
        ArrayList<Cad_Empresa_dados> codigosGerados = new ArrayList<>();

        String sql = "call sp_codigosUsuario(?)";
        try (Connection conn = Connection_db2.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Cad_Empresa_dados dados = new Cad_Empresa_dados();
                    dados.setNome_empresa(rs.getString("nome_empresa"));
                    dados.setCodigo(rs.getString("codigo"));

                    codigosGerados.add(dados);

                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;

            }
        }
        return codigosGerados;
    }

}
