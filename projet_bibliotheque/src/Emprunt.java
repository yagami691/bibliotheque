
import java.time.LocalDate;

public class Emprunt{
    private int idEmprunt;
    private int membreId;
    private int livreId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    public Emprunt(int membreId, int livreId, LocalDate dateEmprunt, LocalDate dateRetourPrevue,LocalDate dateRetourEffective){
        this.membreId=membreId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
    }

    public int getId(){
        return idEmprunt;
    }

    
    public int getMembreId(){
        return membreId;
    }
    
    public int livreId(){
        return livreId;
    }

    public LocalDate getDateEmprunt(){
        return dateEmprunt;
    }

    public LocalDate getdateRetourPrevue(){
        return dateRetourPrevue;
    }
    
    public LocalDate getdateRetourEffective(){
        return dateRetourEffective;
    }

    public void setMembreId(int membreId){
        this.membreId = membreId;
    }

    public void setLivreId(int livreId){
        this.livreId = livreId;
    }

    public void setDateEmprunt(LocalDate dateEmprunt){
        this.dateEmprunt = dateEmprunt;
    }
    
    public void setDateRetourPrevue(LocalDate dateRetourPrevue){
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public void setDateRetourEffective(LocalDate dateRetourEffective){
        this.dateRetourEffective = dateRetourEffective;
    }
    

     @Override
    public String toString() {
        return "Livre{" +
                "id=" + idEmprunt +
                ", idMembre='" + membreId + '\'' +
                ", idLivre='" + livreId + '\'' +
                ", date d'emprunt=" + dateEmprunt +  '\'' +
                ", date de retour prevue=" + dateRetourPrevue +  '\'' +
                ", date de retour effective=" + (dateRetourEffective != null ? dateRetourEffective : "Non spécifiée") + '\'' +
                '}';
    }
    
}