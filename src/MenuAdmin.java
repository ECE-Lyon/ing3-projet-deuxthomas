import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MenuAdmin {


    private String genre;
    private String titre;
    private String duree;
    private String date;
    private JTextField champGenre;
    private JTextField champTitre;
    private JTextField champDuree;
    private JTextField champDate;
    JTextField ChampsNombreDeTicket;
    private final Admin admin;
    private int  a;
    private JLabel l;
    private Connection conn = null;
    private final ResultSet rs = null;
    private PreparedStatement ps = null;




    public MenuAdmin(Admin admin) throws HeadlessException {
        conn = Connexion.Connexion();
        JFrame jFrame;
        this.admin = admin;
        jFrame = new JFrame("Menu principal administrateur");
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setSize(800, 800);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        JPanel panelMenuPrincipalAdministrateur = (JPanel) jFrame.getContentPane();
        jFrame.setLayout(new GridLayout(8, 1));
        jFrame.add(titrePage());
        panelMenuPrincipalAdministrateur.add(NombreDeFilm());
        panelMenuPrincipalAdministrateur.add(titreFilm());
        panelMenuPrincipalAdministrateur.add(genreFilm());
        panelMenuPrincipalAdministrateur.add(dureeFilm());
        panelMenuPrincipalAdministrateur.add(dateFilm());
        panelMenuPrincipalAdministrateur.add(Photo());
        panelMenuPrincipalAdministrateur.add(panelValidation());


    }

    public JPanel titrePage() {
        JPanel panelTitre = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        JLabel titre = new JLabel("Page de saisie des films");
        panelTitre.add(titre);
        return panelTitre;
    }

    public JPanel titreFilm()
    {
        JPanel panelTitreFilm = new JPanel(new FlowLayout(FlowLayout.CENTER , 50 , 5));
        JLabel titre = new JLabel("Titre:");
        panelTitreFilm.add(titre);
        champTitre = new JTextField(20);
        panelTitreFilm.add(champTitre);
        return  panelTitreFilm ;

    }

    public JPanel genreFilm()
    {
        JPanel panelGenreFilm = new JPanel(new FlowLayout(FlowLayout.CENTER , 50 , 5));
        JLabel genre = new JLabel("Genre:");
        panelGenreFilm.add(genre);
        champGenre = new JTextField(20);
        panelGenreFilm.add(champGenre);
        return  panelGenreFilm ;

    }
    public JPanel dureeFilm()
    {
        JPanel panelDureeFilm = new JPanel(new FlowLayout(FlowLayout.CENTER , 50 , 5));
        JLabel duree = new JLabel("Durée:");
        panelDureeFilm.add(duree);
        champDuree = new JTextField(20);
        panelDureeFilm.add(champDuree);
        return  panelDureeFilm ;

    }
    public JPanel dateFilm()
    {
        JPanel panelDateFilm = new JPanel(new FlowLayout(FlowLayout.CENTER , 50 , 5));
        JLabel date = new JLabel("Date:");
        panelDateFilm.add(date);
        champDate = new JTextField(20);
        panelDateFilm.add(champDate);
        return  panelDateFilm ;

    }


    public JPanel panelValidation() {

        JPanel Validation = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton boutonValidation = new JButton("Validation");
        Validation.add(boutonValidation);
        boutonValidation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genre = champGenre.getText();
                titre = champTitre.getText();
                date = champDate.getText();
                duree = champDuree.getText();
                try{
                    String requete = "insert into film(genre,titre,duree,date_sortie) values (?,?,?,?)";
                    ps =conn.prepareStatement(requete);
                    ps.setString(1,genre);
                    ps.setString(2,titre);
                    ps.setString(3,duree);
                    ps.setString(4,date);
                    ps.executeQuery();
                    JOptionPane.showMessageDialog(null,"le compte à bien été crée");
                }catch (Exception e2){
                    System.out.println("--> Exception : " + e2);
                }finally{
                    try{
                        ps.close();
                        rs.close();
                    }catch (Exception e3){
                        System.out.println("--> Exception : " + e3);
                    }
                }
                admin.MiseAJourFilm(champGenre.getText(), champTitre.getText(), champDate.getText(), champDuree.getText(), (ImageIcon) l.getIcon());
                JOptionPane.showMessageDialog(null, "bravo votre compte vient d'être crée");
                champTitre.setText("");
                champDate.setText("");
                champDuree.setText("");
                champGenre.setText("");

            }
        });

        return Validation;
    }

    public JPanel NombreDeFilm(){

        JPanel panelNombreDeFilm = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
        JLabel NombreDeFilm = new JLabel("Combien de film voulez vous ajouter:");
        panelNombreDeFilm.add(NombreDeFilm);
        ChampsNombreDeTicket = new JTextField(20);
        panelNombreDeFilm.add(ChampsNombreDeTicket);
        JButton valider = new JButton("Valider");
        panelNombreDeFilm.add(valider);
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = Integer.parseInt(ChampsNombreDeTicket.getText());

            }
        });
        return panelNombreDeFilm;

    }
    public JPanel Photo(){

        JPanel panelPhoto = new JPanel(new GridLayout(1,2));
        l = new JLabel();
        l.setBounds(0,0,10,10);
        panelPhoto.add(l);
        panelPhoto.add(boutonPhoto());

        return panelPhoto;
    }
    public JPanel boutonPhoto() {
        JPanel panelBoutonPhoto = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        JButton choixPhoto = new JButton("Choisir une photo");
        panelBoutonPhoto.add(choixPhoto);
        choixPhoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser choisir = new JFileChooser();
                try {
                    choisir = new JFileChooser();
                    choisir.setCurrentDirectory(new File(System.getProperty("user.home")));
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","png");
                    choisir.addChoosableFileFilter(filter);


                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null,"veuillez choisir une image");
                }
                int res = choisir.showSaveDialog(null);
                //si l'utilisateur clique sur enregistrer dans Jfilechooser
                if(res == JFileChooser.APPROVE_OPTION){
                    File selFile = choisir.getSelectedFile();
                    String path = selFile.getAbsolutePath();
                    l.setIcon(resize(path));

                }
            }
        });
        return panelBoutonPhoto;
    }


    // Méthode pour redimensionner l'image avec la même taille du Jlabel
    public ImageIcon resize(String imgPath)
    {
        ImageIcon path = new ImageIcon(imgPath);
        Image img = path.getImage();
        Image newImg = img.getScaledInstance(l.getWidth(), l.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
    public static void main(String[] args) {


        Movie film = new Movie();


        Admin admin = new Admin(film);
        MenuAdmin menuPrincipalAdministrateur = new MenuAdmin(admin);



    }

}