
package gestion_echange;

import javafx.beans.property.StringProperty;


public class user1 {
    private int ID_user;
    private String Nom;
    private String Prenom;
    private String Email;
    private String Numero;

    public user1() {
    }
    
    

    public int getID_user() {
        return ID_user;
    }

    public String getNom() {
        return Nom;
    }
    public String nomProperty() { return Nom; }

    public String getPrenom() {
        return Prenom;
    }
public String prenomProperty() { return Prenom; }
    public String getEmail() {
        return Email;
    }

    public String getNumero() {
        return Numero;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public int iduserProperty() { return ID_user; }
    public String emailProperty() { return Email; }
    public String numeroProperty() { return Numero; }

    void setId_user(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
