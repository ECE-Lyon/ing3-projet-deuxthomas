import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Login extends JFrame {
    private Admin admin;
    private JPasswordField champPassword;
    private JTextField champsIdentifiant;
    private String identifiant;
    private String motDePasse;
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement ps = null;



    public Login() throws HeadlessException {
        super("Bienvenue sur l'application CinemApp");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBackground(Color.cyan);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        JPanel menu = (JPanel) this.getContentPane();
        setLayout(new GridLayout(4, 1));
        menu.add(log());
        menu.add(id());
        menu.add(mdp());
        menu.add(vali());
        connection = Connexion.Connexion();

    }

    public JPanel log()
    {
        JPanel un = new JPanel(new FlowLayout());
        JLabel creation = new JLabel("Bienvenue sur l'application CinemApp");
        un.setBackground(Color.cyan);
        un.add(creation);
        return  un;
    }

    public JPanel mdp()
    {
        JPanel deux = new JPanel(new FlowLayout(FlowLayout.LEFT , 200 , 50));
        JLabel password = new JLabel("Mot de passe");
        deux.setBackground(Color.cyan);
        deux.add(password);
        champPassword = new JPasswordField(20);
        deux.add(champPassword);
        return  deux ;

    }

    public JPanel id()
    {
        JPanel trois = new JPanel(new FlowLayout(FlowLayout.LEFT , 200 , 50));
        JLabel identifiant = new JLabel("identifiant");
        trois.setBackground(Color.cyan);
        trois.add(identifiant);
        champsIdentifiant = new JTextField(20);
        trois.add(champsIdentifiant);
        return  trois ;

    }

    public JPanel picture()
    {
        JPanel picture = new JPanel(new FlowLayout(FlowLayout.RIGHT , 200 , 50));
        JLabel pic = new JLabel(new ImageIcon("C:/Users/THOMAS/IdeaProjects/ProjectDeJava/image/bienvenue.png"));
        picture.setBackground(Color.cyan);
        picture.add(pic);
        return  picture ;

    }

    public JPanel vali()
    {

        String motdepasse = "bobo";
        String Identifiant = "lolo";
        JPanel quatre = new JPanel(new FlowLayout(FlowLayout.LEFT , 200 , 50));
        JButton valider = new JButton("valider");
        quatre.setBackground(Color.cyan);
        quatre.add(valider);

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    Menu menu = new Menu();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });



        valider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                identifiant = champsIdentifiant.getText();
                motDePasse = champPassword.getText();
                String requete = "select * from Client where identifiant = ? and motdepasse = ?  ";
                try{
                    ps = connection.prepareStatement(requete);
                    ps.setString(1,identifiant);
                    ps.setString(2,motDePasse);
                    rs = ps.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null, "connexion reussie, félicitation");
                        Menu menuPrincipal = new Menu();


                    }
                    else if (identifiant.equals("Administrateur") && motDePasse.equals("motdepasseadmin")){
                        Movie film = new Movie();
                        JOptionPane.showMessageDialog(null, "connexion reussie");
                        Admin admin1 = new Admin(film);
                        MenuAdmin menuPrincipalAdministrateur = new MenuAdmin(admin1);

                    }
                    else{JOptionPane.showMessageDialog(null,"mot de passe ou identifiant incorrect veuillez reessayer");}
                }catch (Exception e1){
                    System.out.println("--> Exception : " + e1);
                }


                champsIdentifiant.setText("");
                champPassword.setText("");
            }
        });

        JButton creeUnCompte= new JButton("creer un compte");
        creeUnCompte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CreationDeCompte creationDeComptevue = new CreationDeCompte();




            }
        });
        quatre.add(creeUnCompte);

        return  quatre ;

    /*
        JButton logintoadmin= new JButton("Espace Admin");
        logintoadmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MenuPrincipalAdministrateurVue menuPrincipalAdministrateurVue = new MenuPrincipalAdministrateurVue(controllerMenuPrincipalAdministrateur);

            }
        });
        quatre.add(logintoadmin);
    */
    }




    public static void main(String[] args) {

        Login login = new Login();

    }


}
