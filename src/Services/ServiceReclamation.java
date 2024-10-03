/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DB.Database;
import Entities.Reclamation;
import entities.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fairouz
 */
public class ServiceReclamation implements IService<Reclamation>{
     private Connection cnx = Database.getInstance().getCon();

    
    public void ajouter(Reclamation r) {
         try {
                    String req = "INSERT INTO reclamation(reclamation_Date,reclamation_category_id,reclamation_type,contenu,objet,id_user) VALUES(?,?,?,?,?,?)";
            
                    java.sql.PreparedStatement pst = cnx.prepareStatement(req);
             java.sql.Date date =java.sql.Date.valueOf(r.getReclamation_Date());
       
        
        pst.setDate(1, date );
        pst.setInt(2, r.getReclamation_category_id());
        pst.setString(3, r.getReclamation_type());
         pst.setString(4, r.getContenu());
          pst.setString(5, r.getObjet());
          pst.setInt(6, r.getUser().getId());
      
        

            pst.executeUpdate();
            System.out.println("Reclamation ajoutee !");
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
    }

  
    public void modifier(Reclamation r) {
        try {
        

        String requete = "UPDATE reclamation SET reclamation_Date=?,reclamation_category_id=?,reclamation_type=?,contenu=?,objet=? where id=?";
        PreparedStatement pst = cnx.prepareStatement(requete);

       

       
         
             java.sql.Date date =java.sql.Date.valueOf(r.getReclamation_Date());
       
        
        pst.setDate(1, date );
        pst.setInt(2, r.getReclamation_category_id());
        pst.setString(3, r.getReclamation_type());
         pst.setString(4, r.getContenu());
          pst.setString(5, r.getObjet());
                  pst.setInt(6, r.getId());


        pst.executeUpdate();

        System.out.println("reclamation est modifiée !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    } catch (IllegalArgumentException ex) {
        System.err.println(ex.getMessage());
    }
        
    }

    public void supprimer(Reclamation r) {
        String req = "DELETE FROM reclamation WHERE id="+r.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reclamation supprimee !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Reclamation> afficher() {
        List<Reclamation> list = new ArrayList<>();
        
        
        try{
 String requete = "SELECT * FROM reclamation";
  PreparedStatement pst = cnx.prepareStatement(requete);
  ResultSet rs = pst.executeQuery();

  while (rs.next()) {

java.sql.Date date =rs.getDate("reclamation_Date");//traduire date en date sql
LocalDate Localdate= date.toLocalDate();
    int id = rs.getInt("id");
    int cat = rs.getInt("reclamation_category_id");
    String type = rs.getString("reclamation_type");
    String contenu = rs.getString("contenu");
    String objet = rs.getString("objet");
    
   

    
   

    Reclamation r = new Reclamation(id,Localdate,cat,type,contenu,objet);
    list.add(r);
      System.out.println(list);
  }}
catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
return list ;

    }  
        public List<Reclamation> afficheruser(User user) {
        List<Reclamation> list = new ArrayList<>();
        
        
        try{
 String requete = "SELECT * FROM reclamation where id_user = ?";
  PreparedStatement pst = cnx.prepareStatement(requete);
  pst.setInt(1, user.getId());
  ResultSet rs = pst.executeQuery();

  while (rs.next()) {

java.sql.Date date =rs.getDate("reclamation_Date");//traduire date en date sql
LocalDate Localdate= date.toLocalDate();
    int id = rs.getInt("id");
    int cat = rs.getInt("reclamation_category_id");
    String type = rs.getString("reclamation_type");
    String contenu = rs.getString("contenu");
    String objet = rs.getString("objet");
    
   

    
   

    Reclamation r = new Reclamation(id,Localdate,cat,type,contenu,objet);
    list.add(r);
      System.out.println(list);
  }}
catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
return list ;

    } 
}

    

