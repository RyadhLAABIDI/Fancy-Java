/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commentaire_forum;
import DB.Database;
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
public class CommentaireService {

    Connection connexion;
    Statement stm;

    public CommentaireService() {
        connexion = Database.getInstance().getCon();
    }

    public void ajouter(Commentaire_forum p) throws SQLException {
        String req;
        req = "INSERT INTO `Commentaire`(`nomuser`, `descriptionc`, `imagec`, `post_id`  ) "
                + "VALUES (?,?,?,?)";

        try {
            PreparedStatement pst = connexion.prepareStatement(req);
            pst.setString(1, p.getNomuser());
            pst.setString(2, p.getDescriptionc());
            pst.setString(3, p.getImage_c());
            pst.setInt(4, p.getPost());

            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void modifier(Commentaire_forum p) throws SQLException {

        stm = connexion.createStatement();
        PreparedStatement ps = connexion.prepareStatement("UPDATE `Commentaire` SET  `nomuser`=? ,`descriptionc`=? ,`imagec`=? WHERE id =?");
        ps.setString(1, p.getNomuser());
        ps.setString(2, p.getDescriptionc());

        ps.setString(3, p.getImage_c());

        ps.setInt(4, p.getId());

        ps.executeUpdate();

    }

    public void supprimer(int id) throws SQLException {
        try {
            PreparedStatement pre = connexion.prepareStatement("Delete from commentaire where id=? ;");
            pre.setInt(1, id);
            if (pre.executeUpdate() != 0) {
                System.out.println("commentaire Deleted");

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
    

    public List<Commentaire_forum> afficherCommentairesDuPost(int idPost) throws SQLException {
        List<Commentaire_forum> commentaires = new ArrayList<>();
        String req = "SELECT * FROM Commentaire WHERE post_id = " + idPost;
        ResultSet rst = connexion.createStatement().executeQuery(req);
        while (rst.next()) {
            Commentaire_forum c = new Commentaire_forum();
            c.setId(rst.getInt("id"));
            c.setDescriptionc(rst.getString("descriptionc"));
            c.setDate_c(rst.getString("datec"));
            c.setNomuser(rst.getString("nomuser"));
            commentaires.add(c);
        }
        return commentaires;
    }

    public List<Commentaire_forum> afficher() throws SQLException {
        List<Commentaire_forum> commentaires = new ArrayList<>();
        String req = "select * from Commentaire ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Commentaire_forum p;
            p = new Commentaire_forum(
                    rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("descriptionc"),
                    rst.getString("nomuser"),
                    rst.getString("datec"),
                    rst.getString("imagec"),
                    rst.getInt("post_id")
            );
            commentaires.add(p);
        }
        return commentaires;
    }

    public List<Commentaire_forum> getCombyPost(int id_post) {
        List<Commentaire_forum> arr = new ArrayList<>();
        try {
            PreparedStatement pre = connexion.prepareStatement("SELECT c.id , c.nomuser , c.descriptionc , c.datec FROM commentaire c , post p WHERE c.id=p.id and c.id=? "); //ORDER BY P asc
            pre.setInt(1, id_post);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int idC = rs.getInt("c.id");

                String nomuser = rs.getString("c.nomuser");
                String description = rs.getString("c.descriptionc");
                String Datecom = rs.getString("c.datec");

                Commentaire_forum m = new Commentaire_forum(idC, description, nomuser, Datecom);
                arr.add(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return arr;

    }

    public boolean delete(Commentaire_forum a) {
        Connection cnx = null;
        Statement st = null;
        String requette = "DELETE FROM commentaire WHERE id=" + a.getId() + "";
        try {
            cnx = Database.getInstance().getCon();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* Ignored */
                }
            }
        }

    }

    public boolean update(Commentaire_forum a) {
        Connection cnx = null;
        Statement st = null;

        String requette = "UPDATE commentaire SET descriptionc='" + a.getDescriptionc() + "' WHERE id=" + a.getId() + "";

        try {
            cnx = Database.getInstance().getCon();
            st = cnx.createStatement();
            st.executeUpdate(requette);
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* Ignored */
                }
            }
        }
    }

    public List<Commentaire_forum> postCommentaires(int id_poste) throws SQLException {

        List<Commentaire_forum> lu = new ArrayList<>();
        stm = connexion.createStatement();

        ResultSet rs = stm.executeQuery("SELECT commentaire.* from commentaire WHERE commentaire.post_id=" + id_poste + ";");
        while (rs.next()) {

            int id_com = rs.getInt("commentaire.id");
            String nom_user = rs.getString("commentaire.nomuser");
            String description = rs.getString("commentaire.descriptionc");
            // System.out.println(description);
            
            int id_post = rs.getInt("commentaire.post_id");

            Commentaire_forum c = new Commentaire_forum(id_com, description, nom_user, id_post);
            c.setDate_c(rs.getString("commentaire.datec"));
            System.out.println(id_poste);
            lu.add(c);
        }
        return lu;
    }

    public List<Commentaire_forum> postCommentaires1(int id_poste) throws SQLException {

        List<Commentaire_forum> lu = new ArrayList<>();
        stm = connexion.createStatement();

        ResultSet rs = stm.executeQuery("SELECT commentaire.* from commentaire WHERE commentaire.post_id=" + id_poste + ";");
        while (rs.next()) {

            int id_com = rs.getInt("commentaire.id");
            String nom_user = rs.getString("commentaire.nomuser");
            String description = rs.getString("commentaire.description");
            // System.out.println(description);

            int id_post = rs.getInt("commentaire.post_id");

            Commentaire_forum c = new Commentaire_forum(id_com, description, nom_user, id_post);
            System.out.println(id_poste);
            lu.add(c);
        }
        return lu;
    }

}
