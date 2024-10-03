/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.Database;
import Entities.Post_forum;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salma
 */
public class PostServices implements Iserviceforum<Post_forum> {

    Connection connexion;
    Statement stm;

    public PostServices() {
        connexion = Database.getInstance().getCon();
    }

    @Override
    public void ajouter(Post_forum p) throws SQLException {
        String req;
        req = "INSERT INTO `Post`(`nom_user`, `sujet`, `description`, `communaute`,  `nbr_jaime`, `image`  ) "
                + "VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement pst = connexion.prepareStatement(req);
            pst.setString(1, p.getNom_user());
            pst.setString(2, p.getSujet());
            pst.setString(3, p.getDescription());
            pst.setString(4, p.getCommunaute());

            pst.setInt(5, 0);
            pst.setString(6, p.getImage());

            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifier(Post_forum p) throws SQLException {

        stm = connexion.createStatement();
        PreparedStatement ps = connexion.prepareStatement("UPDATE `post` SET  `nom_user`=? ,`sujet`=?,`description`=?,`communaute`=? ,`image`=? WHERE id =?");
        ps.setString(1, p.getNom_user());
        ps.setString(2, p.getSujet());
        ps.setString(3, p.getDescription());
        ps.setString(4, p.getCommunaute());

        ps.setString(5, p.getImage());
        ps.setInt(6, p.getIdPost());
        ps.executeUpdate();

    }

    @Override
    public void supprimer(int id) throws SQLException {
        try {
            PreparedStatement pre = connexion.prepareStatement("Delete from post where id=? ;");
            pre.setInt(1, id);
            if (pre.executeUpdate() != 0) {
                System.out.println("post Deleted");

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public int getNumberOfComments(int id) throws SQLException {
        stm = connexion.createStatement();
        String req = "SELECT COUNT(*) as cnt FROM commentaire WHERE post_id = " + id;
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        int nb = 0;
        while (rst.next()) {
            nb = rst.getInt("cnt");
        }
        return nb;
    }

    public List<Post_forum> affichageAvancee(String key) {
        List<Post_forum> postes = new ArrayList<>();
        try {

            String req = "SELECT * FROM POST WHERE sujet LIKE '%" + key + "%' OR description LIKE '%" + key + "%' OR nom_user LIKE '%" + key + "%' OR communaute LIKE '%" + key + "%'";
            stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                Post_forum p;
                p = new Post_forum(
                        rst.getInt("id"),//or rst.getInt(1)
                        rst.getString("nom_user"),
                        rst.getString("sujet"),
                        rst.getString("description"),
                        rst.getString("communaute"),
                        rst.getDate("date_p"),
                        rst.getString("image"),
                        rst.getInt("nbr_jaime")
                );
                postes.add(p);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return postes;
    }
    
    public List<Post_forum> getBCommunaute(String communaute) throws SQLException {
        List<Post_forum> equipes = new ArrayList<>();
        String req = "select * from post WHERE communaute = '"+communaute+"' ORDER BY date_p desc";
        stm = connexion.createStatement();
        
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Post_forum p;
            p = new Post_forum(
                    rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom_user"),
                    rst.getString("sujet"),
                    rst.getString("description"),
                    rst.getString("communaute"),
                    rst.getDate("date_p"),
                    rst.getString("image"),
                    rst.getInt("nbr_jaime")
            );
            equipes.add(p);
        }
        return equipes;
    }

    @Override
    public List<Post_forum> afficher() throws SQLException {
        List<Post_forum> equipes = new ArrayList<>();
        String req = "select * from post ORDER BY date_p desc";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Post_forum p;
            p = new Post_forum(
                    rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom_user"),
                    rst.getString("sujet"),
                    rst.getString("description"),
                    rst.getString("communaute"),
                    rst.getDate("date_p"),
                    rst.getString("image"),
                    rst.getInt("nbr_jaime")
            );
            equipes.add(p);
        }
        return equipes;
    }

    public boolean UpdateF(Post_forum post) {
        try {
            PreparedStatement pre = connexion.prepareStatement("UPDATE `post` SET  `nom_user`=? ,`sujet`=?,`description`=?,`communaute`=? ,`image`=? WHERE id =?");
            pre.setString(1, post.getNom_user());
            pre.setString(2, post.getSujet());
            pre.setString(3, post.getDescription());
            pre.setString(4, post.getCommunaute());
            pre.setString(5, post.getImage());

            pre.setInt(6, post.getIdPost());

            if (pre.executeUpdate() != 0) {
                System.out.println(" post updated");
            } else {
                System.out.println("non");
            }
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}