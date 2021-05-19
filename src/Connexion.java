import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class Connexion {
    Connection connection = null;

    public static Connection Connexion(){
        try{

            Connection conn = DriverManager.getConnection("jdbc:h2:./default");
            if (conn!= null){System.out.println("La connexion a réussie");}
            else{System.out.println("Il y a problème de connexion");}
            return conn;
        } catch (Exception e) {
            System.out.println("--> SQLException : " + e);
            return null;
        }
    }
    public static void main(String[] args) {





    }

}
