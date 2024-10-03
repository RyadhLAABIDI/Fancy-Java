
package Gui;

import java.util.Date;


public class echange1 {
    
    private int ID_produit;
    private int ID_user;
    private int ID_echange;
    private String Statut;
    private Date date;

    public echange1() {
    }

    public int getID_produit() {
        return ID_produit;
    }

    public void setID_produit(int ID_produit) {
        this.ID_produit = ID_produit;
    }

    public int getID_user() {
        return ID_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public int getID_echange() {
        return ID_echange;
    }

    public void setID_echange(int ID_echange) {
        this.ID_echange = ID_echange;
    }

    public String getStatut() {
        return Statut;
    }

    public void setStatut(String Statut) {
        this.Statut = Statut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
