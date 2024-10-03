/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import entities.User;
import java.sql.Date;


/**
 *
 * @author azyz
 */
public class Poste {
    
    private int id;
    private String titre;
    private String desc;
    private String img;
    private categorie categorie;
    private Date date;
    private User user;

    public Poste() {
    }

    public Poste(int id, String titre, String desc, String img, categorie categorie, Date date, User user) {
        this.id = id;
        this.titre = titre;
        this.desc = desc;
        this.img = img;
        this.categorie = categorie;
        this.date = date;
        this.user = user;
    }

    public Poste(String titre, String desc, String img, categorie categorie, Date date, User user) {
        this.titre = titre;
        this.desc = desc;
        this.img = img;
        this.categorie = categorie;
        this.date = date;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDesc() {
        return desc;
    }

    public String getImg() {
        return img;
    }

    public categorie getCategorie() {
        return categorie;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setCategorie(categorie categorie) {
        this.categorie = categorie;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Poste other = (Poste) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Poste{" + "id=" + id + ", titre=" + titre + ", desc=" + desc + ", img=" + img + ", categorie=" + categorie + ", date=" + date + ", user=" + user + '}';
    }
    
    
    
}
