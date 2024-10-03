package DB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Andrew
 */
public class Database {
    final String url="jdbc:mysql://localhost/fancytrade";
    final String user="root";
    final String pwd="";
    Connection con;
    
    //creer un 
    public static Database db;
    
  
   //rendre le constructeur prive
    private Database() {
        
        try {
            System.out.println("Connexion en cours");
            con= (Connection) DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            System.out.println("Probleme de connexion : "+ex.getMessage());
            
        }
    }
    
    //une methode static
    public static Database getInstance(){
        if(db==null)
            db=new Database();
        return db;
        
    }

    public Connection getCon() {
        return con;
    }
    
    
    
    
    
            
    
}
