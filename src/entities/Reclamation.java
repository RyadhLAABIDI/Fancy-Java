/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import entities.User;
import java.time.LocalDate;

/**
 *
 * @author fairouz
 */
public class Reclamation {
     private int id;
    private LocalDate reclamation_Date;
    private int reclamation_category_id;
   // private String user_id;
    private String reclamation_type;
    private String contenu;
    private String objet;
    private User user;

    public Reclamation(LocalDate reclamation_Date, int reclamation_category_id, String reclamation_type, String contenu, String objet, User user) {
        this.reclamation_Date = reclamation_Date;
        this.reclamation_category_id = reclamation_category_id;
        this.reclamation_type = reclamation_type;
        this.contenu = contenu;
        this.objet = objet;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    public Reclamation(int id) {
        this.id = id;
    }

    public Reclamation(LocalDate reclamation_Date, int reclamation_category_id, String reclamation_type, String contenu, String objet) {
        this.reclamation_Date = reclamation_Date;
        this.reclamation_category_id = reclamation_category_id;
        this.reclamation_type = reclamation_type;
        this.contenu = contenu;
        this.objet = objet;
    }

    public Reclamation(int id, LocalDate reclamation_Date, int reclamation_category_id, String reclamation_type, String contenu, String objet) {
        this.id = id;
        this.reclamation_Date = reclamation_Date;
        this.reclamation_category_id = reclamation_category_id;
        this.reclamation_type = reclamation_type;
        this.contenu = contenu;
        this.objet = objet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getReclamation_Date() {
        return reclamation_Date;
    }

    public void setReclamation_Date(LocalDate reclamation_Date) {
        this.reclamation_Date = reclamation_Date;
    }

    public int getReclamation_category_id() {
        return reclamation_category_id;
    }

    public void setReclamation_category_id(int reclamation_category_id) {
        this.reclamation_category_id = reclamation_category_id;
    }

    public String getReclamation_type() {
        return reclamation_type;
    }

    public void setReclamation_type(String reclamation_type) {
        this.reclamation_type = reclamation_type;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Reclamation other = (Reclamation) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", reclamation_Date=" + reclamation_Date + ", reclamation_category_id=" + reclamation_category_id + ", reclamation_type=" + reclamation_type + ", contenu=" + contenu + ", objet=" + objet + '}';
    }
    
}
