/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexãoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fedts
 */
public class Connection_db2 {

    /**
     * Função responsável pela conexão com MySQL 8
     * @return 
     */
    public static Connection obterConexao() throws SQLException {
        // 1) Declarar o driver JDBC de acordo com o Banco de dados usado
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
        
        // 2) Abrir a conexão
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/staticdb?useUnicode=yes&characterEncoding=UTF-8&useTimezone=true&serverTimezone=UTC",
                "root", // Usuário de conexão no BD
                ""); // Senha
        return conn ;
    }

}
