package trabalhodsoo2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:sqlite:" +
        System.getProperty("user.dir") + System.getProperty("file.separator") +
        "src" + System.getProperty("file.separator") +
        "trabalhodsoo2" + System.getProperty("file.separator") +
        "banco.db" + System.getProperty("file.separator") +
        "bancoDSOO.db";

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro na conexão com o banco: " + e.getMessage(), e);
        }
    }
}
