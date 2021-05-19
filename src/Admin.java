import javax.swing.*;

public class Admin {
    private final Movie film;



    public Admin(Movie film) {

        this.film = film;

    }


    public void MiseAJourFilm(String genre, String titre, String date, String runningTime, ImageIcon image) {
        film.setTitre(titre);
        film.setGenre(genre);
        film.setDate(date);
        film.setRunningtime(runningTime);
        film.setImage(image);
    }
}