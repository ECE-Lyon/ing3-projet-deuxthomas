import java.sql.*;



public class Clients {
    private String Identifiant ;
    private String  nom;
    private String prenom;
    private String motDePasse;

    public Clients(String identifiant, String nom, String prenom, String motDePasse) {
        Identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
    }

    public String getIdentifiant() {
        return Identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.Identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public static void AjouterClient(String identifiant, String nom, String prenom, String motDePasse) {

    }
}

