/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cad_Usuario;

import java.sql.Connection;
import ConexãoBD.Connection_db2;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gabriel
 */
public class UsuarioDAO {

    public void addNovoUsuario(Cad_Usuario user) throws SQLException {
        String sql = "INSERT INTO usuario (nome,cpf,email,data_nascimento,telefone,senha,tipo_cadastro) values(?,?,?,?,?,?,?);";
        try (Connection conn = Connection_db2.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getNome());
                stmt.setString(2, user.getCpf());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, String.valueOf(user.getDataNascimento()));
                stmt.setString(5, user.getTelefone());
                stmt.setString(6, user.getSenha());
                stmt.setString(7, "usuário");
                stmt.executeUpdate();

                System.out.println("Cadastrado com secesso");

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        }

    }

    public void Update(Cad_Usuario user) throws SQLException {
        String sql = "update usuario set nome =?, email = ?,data_nascimento=?,telefone = ? where cpf = ?";
        try (Connection conn = Connection_db2.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getNome());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, String.valueOf(user.getDataNascimento()));
                stmt.setString(4, user.getTelefone());
                stmt.setString(5, user.getCpf());
                System.out.println(user.getEmail());

                stmt.executeUpdate();
                System.out.println("alterado com secesso");

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        }
    }

    public int findByCPF(String cpf) throws SQLException {
        int id = 0;
        String sql = "select id from usuario where cpf =?";
        try (Connection conn = Connection_db2.obterConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id");
                    return id;
                }

            }
        }
        return -1;
    }

}
