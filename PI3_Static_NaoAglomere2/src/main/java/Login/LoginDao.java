package Login;

import Cad_Usuario.Cad_Usuario;
import ConexãoBD.Connection_db2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author leona
 */
public class LoginDao {

    public LoginDados findByEmail(String email) throws SQLException {

        LoginDados dados = new LoginDados();

        String sql = "SELECT EMAIL,SENHA,TIPO_CADASTRO FROM EMPRESA WHERE EMAIL=?";

        try (Connection conn = Connection_db2.obterConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    dados = new LoginDados();
                    dados.setEmail(rs.getString("EMAIL"));
                    dados.setSenha(rs.getString("SENHA"));
                    dados.setTipo_cadastro(rs.getString("TIPO_CADASTRO"));

                    return dados;
                }
            }
        }
         return dados;
    }
    
    public Cad_Usuario findUser (String email, String senha) throws SQLException{
       Cad_Usuario user = new Cad_Usuario();
       String sql = "select nome,cpf,email,data_nascimento,telefone from usuario where email=? and senha = ?";
   
        try (Connection conn = Connection_db2.obterConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            

            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    user.setNome(rs.getString("nome"));
                    user.setCpf(rs.getString("cpf"));
                    user.setEmail(rs.getString("email"));
                    user.setDataNascimento(LocalDate.parse(rs.getString("data_nascimento")));
                    user.setTelefone(rs.getString("telefone"));
                     return user;

                }
            }
        }
          return user;   
   } 
}
