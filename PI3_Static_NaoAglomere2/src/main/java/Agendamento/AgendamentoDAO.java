/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agendamento;

import ConexãoBD.Connection_db2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author Gabriel
 */
public class AgendamentoDAO {
    public void addAgendamento(Agendamento agend) throws SQLException{
        String sql = "insert into agendamento (email,usuario_id,telefone,data_Agend,horario) values (?,?,?,?,?);";
         try (Connection conn = Connection_db2.obterConexao()){
             conn.setAutoCommit(false);
             try(PreparedStatement stmt = conn.prepareStatement(sql)){
                 stmt.setString(1, agend.getEmail());
                 System.out.println(agend.getEmail());
                 stmt.setString(2, agend.getNome());
                 System.out.println(agend.getNome());
                 stmt.setString(3, agend.getTelefone());
                 stmt.setString(4, String.valueOf(agend.getData()));
                 stmt.setString(5, agend.getHora());
                  stmt.executeUpdate();
                  System.out.println("Cadastrado com secesso");
                  conn.commit();
             }catch(SQLException e){
                conn.rollback();
                throw e;
            }
             
             
         }
         
         
    }
    
}
