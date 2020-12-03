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
 * @author Gabriel
 */
public class ConnectionAzureMysql {

    public static Connection obterConexao() throws SQLException {
// 1) Declarar o driver JDBC do MySQL 8 (lembrar de incluir dependência no pom.xml)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
// 2) Abrir a conexão com banco rodando no Azure
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://static-naoaglomere-mysql.mysql.database.azure.com:3306/StaticDB?"
                + "useUnicode=yes&"
                + "characterEncoding=UTF-8&"
                + "useTimezone=true&"
                + "serverTimezone=UTC",
                "USER-AZURE",
                "SENHA_AZURE");
        return conn;
    }
}
