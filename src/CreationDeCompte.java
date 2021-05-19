import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class CreationDeCompte extends JFrame {


    private String motDePasse;
    private String nom;
    private String prenom;
    private String identifiant;
    private JTextField champsIdentifiant;
    private JTextField champsPrenom;
    private  JTextField champsNom;
    private JPasswordField jPasswordField;
    private Clients nouveauClient ;
    private Connection connection = null;
    private final ResultSet rs = null;
    private PreparedStatement ps = null;





    public CreationDeCompte() throws HeadlessException {

        super("Bienvenue, creer un compte !");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(900,900);
        this.setLocationRelativeTo(null);
        JPanel panelCreationCompte = (JPanel) this.getContentPane();
        setLayout(new GridLayout(   6 , 1));
        connection = Connexion.Connexion();

        panelCreationCompte.add(TitreDeLaPage());
        panelCreationCompte.add(nom());
        panelCreationCompte.add(prenom());
        panelCreationCompte.add(panel3());
        panelCreationCompte.add(panel2());
        panelCreationCompte.add(panel5());




    }

    public JPanel TitreDeLaPage()
    {
        JPanel un2 = new JPanel(new FlowLayout());
        JLabel creation2 = new JLabel("Creation d'un compte");
        un2.add(creation2);

        return  un2;
    }

    public JPanel nom()
    {
        JPanel panelNom = new JPanel(new FlowLayout(FlowLayout.CENTER , 100 , 20));
        JLabel nom = new JLabel("Nom");
        panelNom.add(nom);
        panelNom.setBackground(Color.red);
        champsNom = new JTextField(20);
        panelNom.add(champsNom);
        return  panelNom ;

    }

    public JPanel panel2()
    {
        JPanel deux = new JPanel(new FlowLayout(FlowLayout.CENTER , 100 , 20));
        JLabel mot_de_passe = new JLabel("Mot de Passe");
        deux.add(mot_de_passe);
        jPasswordField = new JPasswordField(20);
        deux.add(jPasswordField);
        return  deux ;

    }

    public JPanel prenom()
    {
        JPanel panelPrenom = new JPanel(new FlowLayout(FlowLayout.CENTER , 100 , 20));
        JLabel prenom = new JLabel("Prenom");
        panelPrenom.add(prenom);
        prenom.setBackground(Color.red);
        champsPrenom = new JTextField(20);
        champsPrenom.getText();
        panelPrenom.add(champsPrenom);
        return  panelPrenom ;

    }

    public JPanel panel3()
    {
        JPanel trois = new JPanel(new FlowLayout(FlowLayout.CENTER , 100 , 20));
        JLabel identifiant = new JLabel("Identifiant");
        trois.add(identifiant);
        trois.setBackground(Color.red);
        champsIdentifiant = new JTextField(20);
        trois.add(champsIdentifiant);
        return  trois ;

    }

    public JPanel panel5()
    {
        JPanel quatre = new JPanel(new FlowLayout(FlowLayout.CENTER , 100 , 20));
        JButton valider = new JButton("valider");
        quatre.add(valider);
        quatre.setBackground(Color.red);
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prenom = champsPrenom.getText();
                nom = champsNom.getText();
                motDePasse = jPasswordField.getText();
                identifiant = champsIdentifiant.getText();
                try{
                    String requete2 = "insert into client(id_client,nom,prenom,motDePasse) values (?,?,?,?)";
                    ps = connection.prepareStatement(requete2);
                    ps.setString(1,identifiant);
                    ps.setString(2,nom);
                    ps.setString(3,prenom);
                    ps.setString(4,motDePasse);
                    ps.executeQuery();
                    JOptionPane.showMessageDialog(null,"le compte a bien été crée, félicitation");
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
                champsIdentifiant.setText("");
                champsNom.setText("");
                jPasswordField.setText("");
                champsPrenom.setText("");

            }
        });

        return  quatre ;

    }




    public static void main(String[] args) {


        CreationDeCompte creationDeComptevue = new CreationDeCompte();
        creationDeComptevue.setVisible(true);


    }


}
