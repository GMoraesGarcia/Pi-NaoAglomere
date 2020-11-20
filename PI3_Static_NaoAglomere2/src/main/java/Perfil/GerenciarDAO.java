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

        String sql = "";

        try (Connection conn = ConnectionUtilMySql.obterConexao()) {

            try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery() ) {
                
                

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        return null; 
    }
    
    public ArrayList<GerenciarDados> Empresa(String cnpj) throws SQLException {
        
        String sql = "";

        try (Connection conn = ConnectionUtilMySql.obterConexao()) {
            try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

              
                
            } catch (SQLException e) {
                conn.rollback();
                throw e;

            }
        }
        return null;
    }
}
