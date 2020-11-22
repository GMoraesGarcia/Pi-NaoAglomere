package Login;

import Cad_Empresa.cad_Empresadados;
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

    public LoginDados findLogin(String email, String senha) throws SQLException {

        LoginDados login = new LoginDados();

        String sql = "SELECT EMAIL,SENHA,TIPO_CADASTRO FROM EMPRESA WHERE EMAIL=?";

        try (Connection conn = Connection_db2.obterConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    login = new LoginDados();
                    login.setEmail(rs.getString("EMAIL"));
                    login.setSenha(rs.getString("SENHA"));
                    login.setTipo_cadastro(rs.getString("TIPO_CADASTRO"));

                    return login; // retorna tipo de cadastro = empresa
                }
            }
        }

        sql = "SELECT EMAIL,SENHA,TIPO_CADASTRO FROM USUARIO WHERE EMAIL=?";

        try (Connection conn = Connection_db2.obterConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    login = new LoginDados();
                    login.setEmail(rs.getString("EMAIL"));
                    login.setSenha(rs.getString("SENHA"));
                    login.setTipo_cadastro(rs.getString("TIPO_CADASTRO"));

                    return login; // retorna tipo de cadastro = usuario
                }
            }
        }

        return login; // retorna dados nulos caso o email não esteja cadastrado
    }

    public Cad_Usuario findUsuario(String email, String senha) throws SQLException {
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

    public cad_Empresadados findEmpresa(String email, String senha) throws SQLException {
        cad_Empresadados empresa = new cad_Empresadados();
        String sql = "SELECT NOME_EMPRESA,CNPJ,EMAIL,DESCRICAO,TELEFONE,QTD_MAX,RUA,BAIRRO,NUMERO,REGRAS,AGENDAMENTO,FOTO FROM EMPRESA WHERE EMAIL=? AND SENHA=?";
        
        try (Connection conn = Connection_db2.obterConexao(); // abre e fecha a conexão
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    empresa.setNome_empresa(rs.getString("NOME_EMPRESA"));
                    empresa.setCnpj(rs.getString("CNPJ"));
                    empresa.setEmail(rs.getString("EMAIL"));
                    empresa.setDescricao(rs.getString("DESCRICAO"));
                    empresa.setTelefone(rs.getString("TELEFONE"));
                    empresa.setQtd_max(rs.getInt("QTD_MAX"));
                    empresa.setRua(rs.getString("RUA"));
                    empresa.setBairro(rs.getString("BAIRRO"));
                    empresa.setNumero_rua(rs.getInt("NUMERO"));
                    empresa.setRegras(rs.getString("REGRAS"));
                    empresa.setAgendamento(rs.getString("AGENDAMENTO"));
                    empresa.setFoto(rs.getString("FOTO"));
                    return empresa;
                }
            }
        }
        
        return empresa;
    }
}
