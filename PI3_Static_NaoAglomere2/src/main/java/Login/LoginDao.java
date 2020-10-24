package Login;

import ConexãoBD.ConnectionUtilMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author leona
 */
public class LoginDao {

    public LoginDados findByEmail(String email) throws SQLException {

        LoginDados dados = new LoginDados();

        String sql = "SELECT EMAIL,SENHA,TIPO_CADASTRO FROM EMPRESA WHERE EMAIL=?";

        try (Connection conn = ConnectionUtilMySql.obterConexao(); // abre e fecha a conexão
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

        sql = "SELECT EMAIL,SENHA,TIPO_CADASTRO FROM USUARIO WHERE EMAIL=?";

        try (Connection conn = ConnectionUtilMySql.obterConexao(); // abre e fecha a conexão
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
}
