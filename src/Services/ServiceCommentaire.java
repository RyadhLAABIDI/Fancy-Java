/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.Database;
import Entities.Commentaire;
import Entities.Poste;
import entities.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author azyz
 */
public class ServiceCommentaire implements Iservice1<Commentaire> {
    
    Connection cnx = Database.getInstance().getCon();
     
    @Override
    public void ajouter(Commentaire c) {
        try {
            String req = "INSERT INTO Commentairee (description,date,id_poste,id_user) VALUES ('" + c.getDesc() + "', '" + c.getDate() + "','"+c.getPoste().getId()+"','" + c.getUser().getId()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commentaire created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM commentairee  WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commentaire deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @Override
    public void modifier(Commentaire c) {
        try {
            String req = "UPDATE Commentairee SET description = '" + c.getDesc() + "', date = '" + c.getDate() + "',id_poste = '" + c.getPoste().getId() + "',id_user='"+c.getUser().getId()+"' WHERE commentaire.id = " + c.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commentaire updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void modifier(int id,String c) {
        try {
            String req = "UPDATE Commentairee SET description = '" + c + "' WHERE commentairee.id = '"+id+"'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commentaire updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public List<Commentaire> getAll() {
        List<Commentaire> list = new ArrayList<>();
        try {
            String req = "Select * from Commentairee";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Poste d;
                User u;
                ServiceUser ss = new ServiceUser();
                ServicePoste dd = new ServicePoste();
                d = dd.getOneById(rs.getInt("id_poste"));
                u = ss.ChercherParId(rs.getInt(5));
                Commentaire w = new Commentaire(rs.getInt(1), rs.getString("description"), rs.getDate("date"),d ,u);
                list.add(w);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    
    
    
     public List<Commentaire> getAll(int id) {
        List<Commentaire> list = new ArrayList<>();
        try {
            String req = "Select * from Commentairee WHERE Commentairee.id_poste="+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Poste d;
                User u;
                ServiceUser ss = new ServiceUser();
                ServicePoste dd = new ServicePoste();
                d = dd.getOneById(id);
                u = ss.ChercherParId(rs.getInt(5));
                Commentaire w = new Commentaire(rs.getInt(1), rs.getString("description"), rs.getDate("date"),d ,u);
                list.add(w);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    public List<Commentaire> getAllm(int id,int idp) {
        List<Commentaire> list = new ArrayList<>();
        try {
            String req = "Select * from Commentairee WHERE Commentairee.id_user='"+id+"' AND Commentairee.id_poste='"+idp+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Poste d;
                User u;
                ServiceUser ss = new ServiceUser();
                ServicePoste dd = new ServicePoste();
                d = dd.getOneById(idp);
                u = ss.ChercherParId(rs.getInt(5));
                Commentaire w = new Commentaire(rs.getInt(1), rs.getString("description"), rs.getDate("date"),d ,u);
                list.add(w);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    @Override
    public Commentaire getOneById(int id) {
        Commentaire w = null;
        try {
            String req = "Select * from commentairee WHERE commentairee.id = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u;
                Poste d;
                ServicePoste dd = new ServicePoste();
                ServiceUser ss = new ServiceUser();
                d = dd.getOneById(rs.getInt("id_poste"));
                u = ss.ChercherParId(rs.getInt(5));
                   w = new Commentaire(rs.getInt(1), rs.getString("description"), rs.getDate("date"),d ,u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return w;
    }
    
    
}
