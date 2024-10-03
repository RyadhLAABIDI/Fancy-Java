/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;

import DB.Database;
import Services.ServiceMessage;
import entities.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class profileUserController implements Initializable{
    
    @FXML
    public ComboBox<?> ageUs;

    @FXML
    private Button btnModif;

    @FXML
    public Label codVUs;

    @FXML
    public TextField emailUs;

    @FXML
    public TextField motPasseUs;

    @FXML
    public TextField nomUs;

    @FXML
    public TextField prenomUs;
    

    @FXML
    public Label roleUs;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Alert alert;
    private final int ageList[] ={22,23,24,25,26,27,28};
        private User connected;
    @FXML
    private Button ExitButton;
    @FXML
    public void exitScene(ActionEvent event) {
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }
    public void setConnectedUser(User connectedUser) {
        this.connected = connectedUser;
        System.out.println(connected + "client snet dadada");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userAgeList();
      
    }
    public void init(){
    nomUs.setText(connected.getNom());
    prenomUs.setText(connected.getPrenom());
     emailUs.setText(connected.getEmail());
     motPasseUs.setText(connected.getMdp());
    }
    
    
    public void userAgeList(){
        List<Integer> aList = new ArrayList<>();
        for(Integer data: ageList ){
            aList.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(aList);
        ageUs.setItems(listData);
    }
        public void updatemessage() {

    }

    @FXML
    private void profili(ActionEvent event) {
        
                Connection connection;
        connection = Database.getInstance().getCon();
        String query2 = "UPDATE utilisateur SET nom = ? , prenom = ? , email = ? , age = ? , mdp = ? WHERE id = ?";
        PreparedStatement statement2;
        try {
            statement2 = connection.prepareStatement(query2);
            statement2.setString(1, nomUs.getText());
            statement2.setString(2, prenomUs.getText());
            statement2.setString(3, emailUs.getText());
            statement2.setString(5, motPasseUs.getText());
            int selectedItem = (int) ageUs.getValue();
            statement2.setInt(4, selectedItem);
            statement2.setInt(6, connected.getId());
            
            statement2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
