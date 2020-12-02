/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pesquisa;

import ConexãoBD.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gabriel
 */
public class CodigoDAO {

    public void inserirInfoCodigo(CodigoDados cod) throws SQLException {
        String sql = "insert into Codigos_Gerados (codigo, empresa_id, data_geracao,horario_geracao ) values(?,?, current_date(), current_time())";
        try (Connection conn = Connection_db2.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cod.getCodigo());
                stmt.setInt(2, cod.getId());
                stmt.executeUpdate();

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        }

    }

    public boolean isEmpty(int cod_empresa) throws SQLException {
        String sql = "select * from Codigos_Gerados where empresa_id =? ";

        try (Connection conn = Connection_db2.obterConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, cod_empresa);

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getAtualizacaoCodigo(int cod_empresa) throws SQLException {
        String sql = "select * from Codigos_Gerados where empresa_id =? and data_geracao < current_date();";
        try (Connection conn = Connection_db2.obterConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, cod_empresa);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* public CodigoDados findByEmpId(int cod_empresa)throws SQLException{
     String sql = "";
    
    }*/
}
