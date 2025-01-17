import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    private static Connection conn = null;

    // Méthode pour obtenir la connexion
    public static Connection getConnection() throws Exception {
        if (conn == null || conn.isClosed()) {
            // Si la connexion est fermée ou n'existe pas, on en crée une nouvelle
            String url = "jdbc:postgresql://localhost:5432/bibliotheque";
            String user = "pens";
            String password = "postgres";
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }

    // Méthode pour fermer la connexion manuellement
    public static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
