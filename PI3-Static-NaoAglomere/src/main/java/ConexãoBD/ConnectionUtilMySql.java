package ConexãoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtilMySql {

    public static Connection obterConexao() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/staticdb?useUnicode=yes&characterEncoding=UTF-8&useTimezone=true&serverTimezone=UTC",
                "root" /*usuário do MySqlWorkbench*/,
                "kuruma" /*senha do MySqlWorkbench*/);
        return conn;
    }
}
