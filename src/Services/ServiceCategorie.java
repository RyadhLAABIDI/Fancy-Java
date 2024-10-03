/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.Database;
import Entities.categorie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author azyz
 */
public class ServiceCategorie implements Iservice1<categorie> {
    
     Connection cnx = Database.getInstance().getCon();
    @Override 
    public void ajouter(categorie d) {
        try {
            String req = "INSERT INTO categorie (Nom) VALUES ('" + d.getNom() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Domaine created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM categorie  WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Domaine deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void modifier(categorie d) {
        try {
            String req = "UPDATE categorie SET Nom = '" + d.getNom() + "' WHERE categorie.id = " + d.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Domaine updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public List<categorie> getAll() {
        List<categorie> list = new ArrayList<>();
        try {
            String req = "Select * from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                
                categorie d = new categorie(rs.getInt(1), rs.getString(2));
                list.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    @Override
    public categorie getOneById(int id) {
        categorie d = null;
        try {
            String req = "Select * from categorie WHERE categorie.id = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                d = new categorie(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return d;
    }
    
    public categorie getOneByName(String test) {
        categorie d = null;
        try {
            String req = "Select * from categorie WHERE categorie.nom = "+test;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                d = new categorie(rs.getInt(1), rs.getString(2));
                System.out.println(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return d;
    }

     public ObservableList<categorie> getall() {
        ObservableList<categorie> posts = FXCollections.observableArrayList();
        try {
            String req = "select distinct nom from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                categorie d = new categorie(rs.getString("nom"));
                
               posts.add(d);
            }
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
    }
     public ObservableList<String> getalls() {
        ObservableList<String> posts = FXCollections.observableArrayList();
        try {
            String req = "select distinct nom from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
            String d = (rs.getString("nom"));
                
               posts.add(d);
            }
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
    }
}
    
    

