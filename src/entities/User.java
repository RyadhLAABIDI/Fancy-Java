/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author user
 */
public class User {

    private int id; 
    private String nom;
    private String prenom;
    private String email;
    private int age;
    private String mdp;
    private String role;
    private String verificationCode;
    
    /**
     *
     * @param string
     * @param string1
     * @param string2
     * @param aInt
     */
    

    public User(int id) {      
        this.id = id;
        
        
    }

    public User(int id, String nom, String prenom, String email, String mdp, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
    }

    public User(String nom, String prenom, String email, int age, String mdp, String role, String verificationCode) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.mdp = mdp;
        this.role = role;
        this.verificationCode = verificationCode;
    }

    public User(int id, String nom, String prenom, String email, int age, String mdp, String role, String verificationCode) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.mdp = mdp;
        this.role = role;
        this.verificationCode = verificationCode;
    }

    public User(int id, String nom, String prenom, String email, int age, String mdp, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.mdp = mdp;
        this.role = role;
       
    }

    public User(int id,String nom, String prenom, String email, int age) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
    }
    


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * @param mdp the mdp to set
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    

    

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVerificationCode() {
        return verificationCode;        
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
    
    
    @Override
    public String toString() {
        return " nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", age=" + age + ", role=" + role + "";
    }


    
}