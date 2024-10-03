/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import Entities.Reclamation;
import Entities.ReclamationCategory;
import Services.ServiceReclamation;
import Services.ServiceReclamationCategory;
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author fairouz
 */
public class MainProg {
    public static void main(String[] args) throws IOException {
     
        ServiceReclamation r=new ServiceReclamation();
        //r.ajouter(new Reclamation(LocalDate.of(2023, 2, 25),1,"sousse kantaoui","fairouz","fairouz")); 
        // r.modifier(new Reclamation(1,LocalDate.of(2027, 2, 25),1,"sousse kantaoui","fairouz","fairouz")); 
         System.out.println(r.afficher());
        //r.supprimer(new Reclamation(3));
         ServiceReclamationCategory rc=new ServiceReclamationCategory();
       //rc.ajouter(new ReclamationCategory("maison Mario","hhh")); 
        //rc.modifier(new ReclamationCategory(1,"maisonghaus","hhh")); 
        // System.out.println(rc.afficher());
        //rc.supprimer(new ReclamationCategory(2));
    }
    
}
