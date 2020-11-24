/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Perfil;

import ConexãoBD.ConnectionUtilMySql;
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

        try (Connection conn = ConnectionUtilMySql.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    GerenciarDados agendamento = new GerenciarDados();

                    agendamento.setNomeUser(rs.getString("nome"));
                    agendamento.setNomeEmpresa(rs.getString("nome_empresa"));
                    agendamento.setData(rs.getString("data_agend"));
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

        try (Connection conn = ConnectionUtilMySql.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, cnpj);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    GerenciarDados agendamento = new GerenciarDados();

                    agendamento.setNomeUser("nome");
                    agendamento.setNomeEmpresa("nome_empresa");
                    agendamento.setData("data_agend");
                    agendamento.setHorario("horario");

                    agendamentos.add(agendamento);
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;

            }
        }
        return agendamentos;
    }
}
