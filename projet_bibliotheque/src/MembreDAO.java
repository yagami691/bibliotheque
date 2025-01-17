import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MembreDAO {
    public static ArrayList<Membre> membres = new ArrayList<>(); // Liste pour garder les membres en mémoire
    public static Scanner scanner = new Scanner(System.in);

    // Méthode pour ajouter un membre dans la base et la liste
    public static void inscrireMembre() {

       
        System.out.print("Entrez le nom du membre : ");
        String nom = scanner.nextLine();

        System.out.print("Entrez le prénom du membre : ");
        String prenom= scanner.nextLine();

        System.out.print("Entrez l'email du membre : ");
        String email= scanner.nextLine();

        System.out.print("Entrez la date d'adhésion du membre  au format jour/mois/année : ");
        String adhesionDateStr = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate adhesionDate = LocalDate.parse(adhesionDateStr, formatter);

        Membre membre = new Membre(nom, prenom, email, adhesionDate);

          String sql = """
            INSERT INTO Membre (nom, prenom, email, adhesionDate)
            VALUES (?, ?, ?, ?) RETURNING id
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Définir les paramètres pour l'insertion
            pstmt.setString(1, membre.getNom());
            pstmt.setString(2, membre.getPrenom());
            pstmt.setString(3, membre.getEmail());
            pstmt.setObject(4,membre.getAdhesionDate());

            // Exécuter la requête et récupérer l'ID généré
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int generatedId = rs.getInt("id");
                    // Mettre à jour l'ID de l'objet Membre
                   membre.setId(generatedId);
                }
            }

            // Ajouter le membre à la liste
            membres.add(membre);
            System.out.println("membre inscrit en mémoire et dans la base de données : " + membre.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void supprimerMembre(String nom){
       String sql = "DELETE FROM Membre WHERE nom ILIKE ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Remplace le ? par le nom du membre
        pstmt.setString(1, "%" + nom + "%");

        // Exécuter la suppression
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Membre(s) avec le nom contenant \"" + nom + "\" ont été supprimés.\n");
        } else {
            System.out.println("Aucun membre trouvé avec le nom : " + nom);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }


     public static void rechercheParNom(String membreRecherche) {
        String sql = "SELECT * FROM Membre WHERE nom ILIKE ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Remplace le ? par la valeur de l'auteur
        pstmt.setString(1, "%" + membreRecherche + "%"); // Recherche insensible à la casse

        try (ResultSet rs = pstmt.executeQuery()) {
            boolean livreTrouve = false;
            while (rs.next()) {
                livreTrouve = true;
                System.out.println("Membre trouvé :");
                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("nom : " + rs.getString("nom"));
                System.out.println("prenom: " + rs.getString("prenom"));
                System.out.println("email : " + rs.getString("email"));
                System.out.println("date d'adhesion \n: " + rs.getObject("adhesionDate"));
             
            }

            if (!livreTrouve) {
                System.out.println("Aucun livre trouvé pour l'auteur : " + membreRecherche);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
