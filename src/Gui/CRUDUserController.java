/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import DB.Database;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import entities.User;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author user
 */
public class CRUDUserController implements Initializable {
    @FXML
    private ComboBox<?> ageUs;
    
    @FXML
    private ComboBox<?> roleUser;


    @FXML
    private TableColumn<User, Integer> ageUserCol;
    
    @FXML
    private TableColumn<User, Integer> idCol;

    
    
    @FXML
     private Button btnRecherche;


    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;


    @FXML
    private Label roleL;
    @FXML
    private TextField emailUs;
    
    @FXML
    private TextField motPasse;

    @FXML
    private TextField emailRech;
    
    @FXML
    private TableColumn<User, String> emailUserCol;
    
    @FXML
    private TableColumn<User, String> mdpCol;
    
    @FXML
    private TableColumn<User, String> roleCol;



    @FXML
    private TextField idUs;

    @FXML
    private TextField nomUs;

    @FXML
    private TableColumn<User, String> nomUserCol;
   
    @FXML
    private TableColumn<User, String> prenUserCol;

    @FXML
    private TextField prenomUs;


   

    @FXML
    private TableView<User> usersTable;
    
    private Connection connect;
    private PreparedStatement prepare,prepare1,prepare2;
    private ResultSet result;
    private Alert alert;
    private final int ageList[] ={22,23,24,25,26,27,28};
    private final String roleList[] ={"ADMINISTRATOR","USER"};
    @FXML
    private Button ExitButton;
    @FXML
        public void exitScene(ActionEvent event) {
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }
    public void userAddBtn(){
        String insertData = "INSERT INTO utilisateur(nom, prenom, email, age,mdp,role,verification_code) VALUES (?,?,?,?,?,?,?)";
        connect = Database.getInstance().getCon();
        try{
           
                if(emailUs.getText().isEmpty() || nomUs.getText().isEmpty() || prenomUs.getText().isEmpty() || ageUs.getSelectionModel().getSelectedItem()==null)
                {
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreurs");
                    alert.setHeaderText(null);
                    alert.setContentText(emailUs.getText()+" Veuillez remplir les champs obligatoires");
                    alert.showAndWait();
                }else{
                    String checkData = "SELECT * FROM utilisateur  WHERE email = '"+emailUs.getText()+"'";
                    prepare = connect.prepareStatement(checkData);
                    result=prepare.executeQuery();
                    if(result.next()){
                       alert=new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("Message d'erreurs");
                       alert.setHeaderText(null);
                       alert.setContentText(emailUs.getText()+" Existe déjà");
                       alert.showAndWait();
                    }else{
                        prepare1 = connect.prepareStatement(insertData);
                        prepare1.setString(1, nomUs.getText());
                        prepare1.setString(2, prenomUs.getText());
                        prepare1.setString(3, emailUs.getText());
                        String str=ageUs.getSelectionModel().getSelectedItem().toString();
                        prepare1.setInt(4, Integer.parseInt(str)); 
                        prepare1.setString(5, ""); 
                        prepare1.setString(6, ""); 
                        prepare1.setString(7, ""); 
                        prepare1.executeUpdate();
                        userShowData();
                        
                        // pour modifier les données du tableView
                        
                    }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void userAgeList(){
        List<Integer> aList = new ArrayList<>();
        for(Integer data: ageList ){
            aList.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(aList);
        ageUs.setItems(listData);
    }
    
    
    public void userRoleList(){
        List<String> rList = new ArrayList<>();
        for(String data: roleList ){
            rList.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(rList);
        roleUser.setItems(listData);
    }
    
    public ObservableList<User> getUsersList(){
        ObservableList<User> userList = FXCollections.observableArrayList();
        connect = Database.getInstance().getCon();
        String query = "SELECT * FROM utilisateur";
        Statement st;
        ResultSet rs;
        
        try{
            st = connect.createStatement();
            rs = st.executeQuery(query);
            User users;
            while(rs.next()){
                users = new User(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getInt("age"),rs.getString("mdp"),rs.getString("role"));
                userList.add(users);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return userList;
    }
    public void userShowData(){
        ObservableList<User> list = getUsersList();
        idCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        nomUserCol.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        prenUserCol.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        emailUserCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        ageUserCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("age"));
        mdpCol.setCellValueFactory(new PropertyValueFactory<User, String>("mdp"));
        roleCol.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        usersTable.setItems(list);
    }
    
    
    @FXML
    public void userSelectData(){
        User user = usersTable.getSelectionModel().getSelectedItem();
        int num = usersTable.getSelectionModel().getSelectedIndex();
        if((num-1) < -1) return;
        idUs.setText(String.valueOf((char) user.getId()));
        nomUs.setText(String.valueOf(user.getNom()));
        prenomUs.setText(String.valueOf(user.getPrenom()));
        emailUs.setText(String.valueOf(user.getEmail()));
        //ageUs.setValue(String.valueOf(user.getEmail()));
        motPasse.setText(String.valueOf(user.getMdp()));
    }
    
    @FXML
    public void modifierBtn(){
        String updateData = "UPDATE  utilisateur SET nom = ? , prenom = ? , email = ?  , mdp = ? WHERE id = ? ";
        connect = Database.getInstance().getCon();
        
        try {
            prepare2 = connect.prepareStatement(updateData);
            prepare2.setString(1, nomUs.getText());
            prepare2.setString(2, prenomUs.getText());
            prepare2.setString(3, emailUs.getText());
            prepare2.setString(4, motPasse.getText());
            prepare2.setInt(5, Integer.parseInt(idUs.getText()));
            prepare2.executeUpdate();
            userShowData();
            alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message d'information");
            alert.setHeaderText(null);
            alert.setContentText("Un utilisateur est supprimé avec succès");
            alert.showAndWait();
            
             
            
        } catch (SQLException ex) {
            Logger.getLogger(CRUDUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @FXML
     public void rechercherBtn(){
        String updateData = "SELECT * FROM  utilisateur  WHERE email = ? ";
        connect = Database.getInstance().getCon();
        
        try {
            prepare2 = connect.prepareStatement(updateData);
            prepare2.setString(1, emailRech.getText());
            result=prepare2.executeQuery();
            if(result.next())
            {
                idUs.setText(result.getString("id"));
                nomUs.setText(result.getString("nom"));
                prenomUs.setText(result.getString("prenom"));
                emailUs.setText(result.getString("email"));
                motPasse.setText(result.getString("mdp"));
                //ageUs.setText(result.getString("age"));
                roleL.setText(result.getString("role"));
                
            }else{
                 alert=new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Message d'information");
                 alert.setHeaderText(null);
                 alert.setContentText("Cet Email n'existe pas !!!");
                 alert.showAndWait();
            }
           
            
             
            
        } catch (SQLException ex) {
            Logger.getLogger(CRUDUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
    
    
    @FXML
    public void supprimerBtn(){
        String updateData = "DELETE FROM  utilisateur  WHERE id = ? ";
        connect = Database.getInstance().getCon();
        
        try {
            prepare2 = connect.prepareStatement(updateData);
       
            prepare2.setInt(1, Integer.parseInt(idUs.getText()));
            prepare2.executeUpdate();
            userShowData();
            alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message d'information");
            alert.setHeaderText(null);
            alert.setContentText("Un utilisateur est supprimé avec succès");
            alert.showAndWait();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(CRUDUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
      

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userAgeList();
        userRoleList();
        userShowData();
    }    
    
}
