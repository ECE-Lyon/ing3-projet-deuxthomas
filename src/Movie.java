import javax.swing.*;
import java.util.Observable;
import java.sql.*;

public class Movie extends Observable {


    private int identifiant; //numero du film
    private String titre;
    private String date;
    private String genre;
    private String runningtime;
    private ImageIcon image ;

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {

        this.titre = titre;
        setChanged();
        notifyObservers(this);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

        this.date = date;
        setChanged();
        notifyObservers(this);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {

        this.genre = genre;
        setChanged();
        notifyObservers(this);
    }

    public String getRunningtime() {
        return runningtime;
    }

    public void setRunningtime(String runningtime) {

        this.runningtime = runningtime;
        setChanged();
        notifyObservers(this);
    }





}