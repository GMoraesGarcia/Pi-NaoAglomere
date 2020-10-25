/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cad_Usuario;

import java.sql.Connection;
import ConexãoBD.Connection_db2;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author Gabriel
 */
public class UsuarioDAO {
    
    public void addNovoUsuario(Cad_Usuario user) throws SQLException{
        String sql = "INSERT INTO usuario (nome,cpf,email,data_nascimento,telefone,senha) values(?,?,?,?,?,?);";
        try (Connection conn = Connection_db2.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);
            
            try(PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, user.getNome());
                stmt.setString(2, user.getCpf());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, String.valueOf(user.getDataNascimento()));
                stmt.setString(5, user.getTelefone());
                stmt.setString(6, user.getSenha());
                stmt.executeUpdate();
                
                System.out.println("Cadastrado com secesso");
                
                 conn.commit();
            
            } catch(SQLException e){
                conn.rollback();
                throw e;
            }
            
        }
    
    }
    
}
