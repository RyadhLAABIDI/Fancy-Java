/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author fairouz
 */
public class ReclamationCategory {
     private int id;
    private String name;
    private String description;

    public ReclamationCategory(int id) {
        this.id = id;
    }

    public ReclamationCategory() {
    }

    public ReclamationCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ReclamationCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ReclamationCategory(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final ReclamationCategory other = (ReclamationCategory) obj;
        return Objects.equals(this.id, other.id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reclamation_category{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }
    
    
}
