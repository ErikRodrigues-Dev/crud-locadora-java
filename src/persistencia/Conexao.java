package persistencia;
    
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost/java2";
    private static final String USER = "root";
    private static final String PASS = "admin";
    public static Connection getConnection () {
        try {
              Connection c = DriverManager.getConnection(URL, USER, PASS);
              return c;
        } catch (SQLException e) {
            return null;
        }
    }

    static Connection getConexao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
