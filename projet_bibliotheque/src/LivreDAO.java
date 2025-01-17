
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class LivreDAO {
    public static ArrayList<Livre> livres = new ArrayList<>(); // Liste pour garder les livres en mémoire
    public static Scanner scanner = new Scanner(System.in);

    // Méthode pour ajouter un livre dans la base et la liste
    public static void ajouterLivre() {
        // Récupération des informations de l'utilisateur
        System.out.print("Entrez le titre du livre : ");
        String titre = scanner.nextLine();

        System.out.print("Entrez l'auteur : ");
        String auteur = scanner.nextLine();

        System.out.print("Entrez la catégorie : ");
        String categorie = scanner.nextLine();

        System.out.print("Entrez le nombre d'exemplaires : ");
        int nombreExemplaires = scanner.nextInt();

        // Création d'un nouvel objet Livre avec les valeurs récupérées
        Livre livre = new Livre(titre, auteur, categorie, nombreExemplaires);

        // Requête SQL pour insérer un livre dans la base de données
        String sql = "INSERT INTO Livre (titre, auteur, categorie, nombreExemplaires) " +
                     "VALUES (?, ?, ?, ?) RETURNING id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Définir les paramètres pour l'insertion
            pstmt.setString(1, livre.getTitre()); // Titre
            pstmt.setString(2, livre.getAuteur()); // Auteur
            pstmt.setString(3, livre.getCategorie()); // Catégorie
            pstmt.setInt(4, livre.getNombreExemplaires()); // Nombre d'exemplaires

            // Exécuter la requête et récupérer l'ID généré
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int generatedId = rs.getInt("id"); // Récupérer l'ID généré
                    // Mettre à jour l'ID dans l'objet Livre
                    livre.setId(generatedId);
                }
            }

            // Ajouter le livre à la liste
            livres.add(livre);
            System.out.println("Livre ajouté en mémoire et dans la base de données : " + livre);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }  

   
   public static void rechercheParTitre(String titre) {
    
    // Rechercher dans la base de données
    String sql = "SELECT * FROM Livre WHERE titre ILIKE ?"; // ILIKE pour ignorer la casse
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, titre);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                // Récupérer les données du livre trouvé
                int id = rs.getInt("id");
                String titreBD =rs.getString("titre");
                String auteur = rs.getString("auteur");
                String categorie = rs.getString("categorie");
                int nombreExemplaires = rs.getInt("nombreExemplaires");

                // Afficher le livre trouvé
                System.out.println("Livre trouvé dans la base de données :");
                System.out.println("ID : " + id + ", Titre : " + titreBD + ", Auteur : " + auteur
                        + ", Catégorie : " + categorie + ", Nombre d'exemplaires : " + nombreExemplaires);
            } else {
                System.out.println("Aucun livre trouvé pour le titre : " + titre);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

     public static void rechercheParAuteur( String auteur) {
          String sql = "SELECT * FROM Livre WHERE auteur ILIKE ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Remplace le ? par la valeur de l'auteur
        pstmt.setString(1, "%" + auteur + "%"); // Recherche insensible à la casse

        try (ResultSet rs = pstmt.executeQuery()) {
            boolean livreTrouve = false;
            while (rs.next()) {
                livreTrouve = true;
                System.out.println("Livre trouvé :");
                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("Titre : " + rs.getString("titre"));
                System.out.println("Auteur : " + rs.getString("auteur"));
                System.out.println("Catégorie : " + rs.getString("categorie"));
                System.out.println("Nombre d'exemplaires : " + rs.getInt("nombreExemplaires"));
             
            }

            if (!livreTrouve) {
                System.out.println("Aucun livre trouvé pour l'auteur : " + auteur);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    }

    public static void rechercheParCategorie(String categorie) {
        String sql = "SELECT * FROM Livre WHERE categorie ILIKE ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Remplace le ? par la valeur de l'auteur
        pstmt.setString(1, "%" + categorie + "%"); // Recherche insensible à la casse

        try (ResultSet rs = pstmt.executeQuery()) {
            boolean livreTrouve = false;
            while (rs.next()) {
                livreTrouve = true;
                System.out.println("Livre trouvé :");
                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("Titre : " + rs.getString("titre"));
                System.out.println("Auteur : " + rs.getString("auteur"));
                System.out.println("Catégorie : " + rs.getString("categorie"));
                System.out.println("Nombre d'exemplaires : " + rs.getInt("nombreExemplaires"));
               
            }

            if (!livreTrouve) {
                System.out.println("Aucun livre trouvé pour la categorie : " + categorie);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
        
    }
    
    
}
