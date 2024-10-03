/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author azyz
 */
public class categorie {
    private int id;
    private String nom;

    public categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public categorie(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.nom);
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
        final categorie other = (categorie) obj;
        return true;
    }

    @Override
    public String toString() {
        return "categorie{" + "id=" + id + ", nom=" + nom + '}';
    }

   


    
   
    
    
    
    
}
