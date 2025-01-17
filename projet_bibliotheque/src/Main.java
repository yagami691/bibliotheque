import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean quitter = false;

     while(!quitter){
       System.out.println("1.ajouter un livre");
       System.out.println("2.rechercher un livre");
       System.out.println("3.inscrire un membre");
       System.out.println("4.supprimer un membre");
       System.out.println("5.rechercher un membre");
       System.out.println("6.enregistrer un emprunt");
       System.out.println("7.quitter");
       System.out.println();

      System.out.print("Choisi une option: ");
      int option =scanner.nextInt();
      scanner.nextLine();

      switch (option) {
          case 1:
              LivreDAO.ajouterLivre();
              break;
          case 2:
               System.out.println("1.par titre");
               System.out.println("2.par auteur");
               System.out.println("3.par categorie\n");
               
              System.out.print("Choisir un type de recherche: ");
              int typeRecherche =scanner.nextInt();
              scanner.nextLine();
              
           switch (typeRecherche) {
               case 1:
                   System.out.print("entrer un titre : ");
                   String titreRecherche = scanner.nextLine();
                   LivreDAO.rechercheParTitre(titreRecherche);
                   break;
               case 2:
                   System.out.print("entrer un auteur: ");
                   String auteurRecherche = scanner.nextLine();
                   LivreDAO.rechercheParAuteur(auteurRecherche);
                   break;
               case 3:
                   System.out.print("entrer une categorie : ");
                   String categorieRecherche = scanner.nextLine();
                   LivreDAO.rechercheParCategorie(categorieRecherche);
                   break;
               default:
                   System.out.println("option invalide");
                   break;
           }

              break;

          case 3:
              MembreDAO.inscrireMembre();
              break;
          
          case 4:
              System.out.print("entrer le nom du membre à supprimer : ");
              String nom = scanner.nextLine();
              MembreDAO.supprimerMembre(nom);
              break;
          
          case 5:
              System.out.print("entrer le nom du membre à rechercher : ");
              String nomDumembre = scanner.nextLine();
              MembreDAO.rechercheParNom(nomDumembre);
              break;
          case 6:
              EmpruntDAO.enregistrerEmprunt();
              break;
          case 7:
            quitter=true;
            System.out.println("au revoir");
            break;

          default:
              System.out.println("option invalide");
      }
     }

     
    }
}


 //  java -cp ".;out;lib/postgresql-42.7.3.jar" Main
  // javac -d out -cp "lib/postgresql-42.7.3.jar" src/*.java
