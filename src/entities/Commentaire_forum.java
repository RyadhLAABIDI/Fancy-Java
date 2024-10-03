/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import Entities.Post_forum;
import java.util.Date;

/**
 *
 * @author salma
 */
public class Commentaire_forum {
    private int id;
    private String descriptionc;
    private String nomuser;
    private String datec;
    private String imagec;
    private int post_id;

    public Commentaire_forum() {
    }

    public Commentaire_forum(int id, String descriptionc, String nomuser, String date_c, String image_c) {
        this.id = id;
        this.descriptionc = descriptionc;
        this.nomuser = nomuser;
        this.datec = date_c;
        this.imagec = image_c;
    }

    public Commentaire_forum(String descriptionc, String nomuser, String date_c, String image_c, int post) {
        this.descriptionc = descriptionc;
        this.nomuser = nomuser;
        this.datec = date_c;
        this.imagec = image_c;
        this.post_id = post;
    }

    public Commentaire_forum(int id, String descriptionc, String nomuser, String date_c, String image_c, int post) {
        this.id = id;
        this.descriptionc = descriptionc;
        this.nomuser = nomuser;
        this.datec = date_c;
        this.imagec = image_c;
        this.post_id = post;
    }

    public Commentaire_forum(String descriptionc, String nomuser, String imagec, int post_id) {
        this.descriptionc = descriptionc;
        this.nomuser = nomuser;
        this.imagec = imagec;
        this.post_id = post_id;
    }

    public Commentaire_forum(int id, String descriptionc, String nomuser, String datec) {
        this.id = id;
        this.descriptionc = descriptionc;
        this.nomuser = nomuser;
        this.datec = datec;
    }

    public Commentaire_forum(int id, String descriptionc, int post_id) {
        this.id = id;
        this.descriptionc = descriptionc;
        this.post_id = post_id;
    }

    public Commentaire_forum(int id, String descriptionc, String nomuser, int post_id) {
        this.id = id;
        this.descriptionc = descriptionc;
        this.nomuser = nomuser;
        this.post_id = post_id;
    }

    public Commentaire_forum(String descriptionc, String nomuser, int post_id) {
        this.descriptionc = descriptionc;
        this.nomuser = nomuser;
        this.post_id = post_id;
    }

    public Commentaire_forum(int id, String descriptionc, String nomuser, String datec, int post_id) {
        this.id = id;
        this.descriptionc = descriptionc;
        this.nomuser = nomuser;
        this.datec = datec;
        this.post_id = post_id;
    }

 
    

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescriptionc() {
        return descriptionc;
    }

    public void setDescriptionc(String descriptionc) {
        this.descriptionc = descriptionc;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public String getDate_c() {
        return datec;
    }

    public void setDate_c(String date_c) {
        this.datec = date_c;
    }

    public String getImage_c() {
        return imagec;
    }

    public void setImage_c(String image_c) {
        this.imagec = image_c;
    }

    public int getPost() {
        return post_id;
    }

    public void setPost(int post) {
        this.post_id = post;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", nomuser=" + nomuser + ", descriptionc=" + descriptionc + ", date_c=" + datec + ", image_c=" + imagec + ", post=" + post_id + '}';
    }

    
    
}


