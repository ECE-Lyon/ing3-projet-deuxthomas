import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Menu extends Frame {

    private Connection conn = null;
    private ResultSet res = null;
    private PreparedStatement ps = null;
    private JLabel champGenre;
    private JLabel champTitre;
    private JLabel champDuree;
    private JLabel champHeure;
    private JLabel champDate;
    private JLabel champPrix;
    private JLabel champPlace;
    private static String ligne;
    private JTable table;
    private JLabel photo;
    private JTextField champsNombreDeTicket;
    private DefaultTableModel model;


    public Menu() throws SQLException {

        /*
        String query = "SELECT * FROM film";
        Statement stm = conn.createStatement();
        res = stm.executeQuery(query);
         */

        


        JFrame film = new JFrame("Menu principal");
        film.setSize(800, 800);
        film.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        film.setVisible(true);
        JPanel gallery= new JPanel();
        film.setContentPane(gallery);

        GridLayout grid = new GridLayout(3,6);
        gallery.setLayout(grid);

        //Je veux faire une matrice de 3 lignes et 6 colonnes, mettre les affiches des films en haut, la ligne dessous chaque description, et la dernière ligne, les reservations

        ImageIcon icon1 = new ImageIcon("image/Avatar.png");
        JLabel label1 = new JLabel(icon1);
        gallery.add(label1);


        ImageIcon icon2 = new ImageIcon("image/Cars.png");
        JLabel label2 = new JLabel(icon2);
        gallery.add(label2);


        ImageIcon icon3 = new ImageIcon("image/HarryPotter.png");
        JLabel label3 = new JLabel(icon3);
        gallery.add(label3);


        ImageIcon icon4 = new ImageIcon("image/Intouchables.png");
        JLabel label4 = new JLabel(icon4);
        gallery.add(label4);


        ImageIcon icon5 = new ImageIcon("image/kungFuPanda.png");
        JLabel label5 = new JLabel(icon5);
        gallery.add(label5);


        ImageIcon icon6 = new ImageIcon("image/VoyagedeChihiro.png");
        JLabel label6 = new JLabel(icon6);
        gallery.add(label6);


        pack();
        validate();


        setLocationRelativeTo(null);


        for (int i = 0; i <= 6; i++) {

            gallery.add(titreFilm());
            gallery.add(genreFilm());
            gallery.add(dureeFilm());
            gallery.add(dateFilm());
            gallery.add(heureFilm());

            gallery.setLayout(new GridLayout());
            gallery.setSize(500, 500);

        }
        for (int i = 0; i <= 6; i++) {

            gallery.add(reservation());
            gallery.setSize(500, 500);

        }
        gallery.setVisible(true);


    }


    public JPanel titreFilm()
    {
        JPanel panelTitreFilm = new JPanel(new FlowLayout(FlowLayout.CENTER , 50 , 5));
        JLabel titre = new JLabel("Titre:");
        panelTitreFilm.add(titre);
        champTitre = new JLabel();
        panelTitreFilm.add(champTitre);
        return  panelTitreFilm ;

    }
    public JPanel placeFilm()
    {
        JPanel panelPlaceFilm = new JPanel(new FlowLayout(FlowLayout.CENTER , 50 , 5));
        JLabel place = new JLabel("Place:");
        panelPlaceFilm.add(place);
        champPlace= new JLabel();
        panelPlaceFilm.add(champPlace);
        return  panelPlaceFilm ;

    }
    public JPanel prixFilm()
    {
        JPanel panelPlaceFilm = new JPanel(new FlowLayout(FlowLayout.CENTER , 50 , 5));
        JLabel prix = new JLabel("Prix:");
        panelPlaceFilm.add(prix);
        champPrix= new JLabel();
        panelPlaceFilm.add(champPrix);
        return  panelPlaceFilm ;

    }

    public JPanel genreFilm()
    {
        JPanel panelGenreFilm = new JPanel(new FlowLayout(FlowLayout.CENTER , 50 , 5));
        JLabel genre = new JLabel("Genre:");
        panelGenreFilm.add(genre);
        champGenre = new JLabel();
        panelGenreFilm.add(champGenre);
        return  panelGenreFilm ;

    }
    public JPanel dureeFilm()
    {
        JPanel panelDureeFilm = new JPanel(new FlowLayout(FlowLayout.CENTER  , 50 , 5));
        JLabel duree = new JLabel("Durée:");
        panelDureeFilm.add(duree);
        champDuree = new JLabel();
        panelDureeFilm.add(champDuree);
        return  panelDureeFilm ;

    }
    public JPanel dateFilm() {
        JPanel panelDateFilm = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 5));
        JLabel date = new JLabel("Date:");
        panelDateFilm.add(date);
        champDate = new JLabel();
        panelDateFilm.add(champDate);
        return panelDateFilm;
    }
    public JPanel heureFilm() {
        JPanel panelHeureFilm = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 5));
        JLabel heure = new JLabel("Heure de la séance:");
        panelHeureFilm.add(heure);
        champHeure = new JLabel();
        panelHeureFilm.add(champHeure);
        return panelHeureFilm;
    }

    public JPanel photo()
    {
        JPanel panelPhoto = new JPanel();
        photo = new JLabel();
        panelPhoto.add(photo);
        return  panelPhoto ;

    }

    public JPanel reservation(){
        JPanel panelReservation = new JPanel();
        JButton acheter = new JButton("réservation");
        panelReservation.add(acheter);
        acheter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                String select = (String) model.getValueAt(row,2 );
                if (row >= 0){
                    try{
                        String requet = "update film set place = where id_film = ?";
                        ps = conn.prepareStatement(requet);
                        ps.setString(1,(String) table.getValueAt(row,7));
                        ps.setString(2,(String) table.getValueAt(row,0));
                        ps.executeUpdate();
                        ps.close();
                    }catch(Exception e11){
                        System.out.println("--> Exception : " + e);

                    }
                }
            }
        });
        return panelReservation;
    }

    public JPanel nombreDeTicket(){
        JPanel panelNombreDeTicket = new JPanel(new FlowLayout(FlowLayout.CENTER , 20,5));
        champsNombreDeTicket = new JTextField(20);
        panelNombreDeTicket.add(champsNombreDeTicket);


        return panelNombreDeTicket;
    }

    public void Deplace (){
        try{
            int row = table.getSelectedRow();
            this.ligne = (table.getModel().getValueAt(row,0).toString());
            String requet ="select * from film where titre ='"+ ligne +"' ";
            ps = conn.prepareStatement(requet);
            res = ps.executeQuery();
            if (res.next()){
                String t1 = res.getString("titre");
                champTitre.setText(t1);
                String t2 = res.getString("genre");
                champGenre.setText(t2);
                String t3 = res.getString("duree");
                champDuree.setText(t3);
                String t4 = res.getString("date");
                champDate.setText(t4);
                String t5 = res.getString("place");
                champPlace.setText(t5);
                String t6 = res.getString("prix");
                champPrix.setText(t6);
                String t7 = res.getString("heure_seance");
                champHeure.setText(t7);
                byte[] image = res.getBytes("image");
                ImageIcon format = new ImageIcon(image);
                photo.setIcon(format);
            }
        }catch (Exception e5){
            System.out.println("--> Exception : " + e5);
        }
    }


    public static void main(String[] args) throws SQLException {
            Menu menu = new Menu();
    }
}



