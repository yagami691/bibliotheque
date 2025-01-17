
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;






public  class EmpruntDAO{
     public static ArrayList<Membre> emprunts = new ArrayList<>(); // Liste pour garder les emprunts en mémoire
    public static Scanner scanner = new Scanner(System.in);

    // Méthode pour ajouter un membre dans la base et la liste
    public static void enregistrerEmprunt() {
       

    // Saisie des informations utilisateur
    System.out.print("Entrez l'ID du membre : ");
    int membreId = scanner.nextInt();

    System.out.print("Entrez l'ID du livre : ");
    int livreId = scanner.nextInt();

    LocalDate dateEmprunt = LocalDate.now(); // Date d'emprunt actuelle
    LocalDate dateRetourPrevue = dateEmprunt.plusDays(14); // Exemple : retour prévu dans 14 jours

    scanner.nextLine(); // Consomme la ligne restante
    System.out.print("Entrez la date de retour effective (au format dd/MM/yyyy, ou laissez vide si non retourné) : ");
    String dateRetourEffectiveStr = scanner.nextLine();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    

    LocalDate dateRetourEffective = null;
     try {
             if (!dateRetourEffectiveStr.isBlank()) {
                dateRetourEffective = LocalDate.parse(dateRetourEffectiveStr, formatter);
    }
     } catch (Exception e) {
        System.out.println("Vous avez mal saisi la date au format demandé");
        return;
     }

    // Requête SQL d'insertion
    String insertSql = """
        INSERT INTO Emprunt (membre_id, livre_id, dateEmprunt, dateRetourPrevue, dateRetourEffective)
        VALUES (?, ?, ?, ?, ?)
    """;

    String checkSql = "SELECT COUNT(*) FROM Emprunt WHERE membre_id = ? AND livre_id = ? AND dateRetourEffective IS NULL";

     try (Connection conn = DBConnection.getConnection();
         PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

        // Vérification de l'existence
        checkStmt.setInt(1, membreId);
        checkStmt.setInt(2, livreId);

        try (ResultSet rs = checkStmt.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Cet emprunt existe déjà pour ce membre et ce livre !");
                return;
            }
        }

        // Si l'emprunt n'existe pas, procéder à l'insertion
        try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
            insertStmt.setInt(1, membreId);
            insertStmt.setInt(2, livreId);
            insertStmt.setDate(3, java.sql.Date.valueOf(dateEmprunt));
            insertStmt.setDate(4, java.sql.Date.valueOf(dateRetourPrevue));

            if (dateRetourEffective != null) {
                insertStmt.setDate(5, java.sql.Date.valueOf(dateRetourEffective));
            } else {
                insertStmt.setNull(5, java.sql.Types.DATE);
            }

            int rowsAffected = insertStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Emprunt enregistré avec succès !");
            } else {
                System.out.println("Échec de l'enregistrement de l'emprunt.");
            }
        }

    }catch (Exception e) {
        e.printStackTrace();
    }
       
    
    }



     public static void calculerPenalite(LocalDate dateRetourPrevue, LocalDate dateRetourEffective) {
        int tarifParJour=100;
        if (dateRetourEffective == null) {
            System.out.println("Le livre n'a pas encore été retourné. Impossible de calculer les pénalités.");
            return;
        }

        long joursDeRetard = ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourEffective);

        if (joursDeRetard > 0) {
            double penalite = joursDeRetard * tarifParJour;
            System.out.printf("Le livre a %d jours de retard. La pénalité est de %.2f fcfa.%n", joursDeRetard, penalite);
        } else {
            System.out.println("Aucun retard. Aucune pénalité n'est appliquée.");
        }
    }
}