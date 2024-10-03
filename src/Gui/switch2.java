
package Gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class switch2  {
    
    @FXML
    private Button btn_echanger;
    
     @FXML
    private Button btn_enregistrer;

    @FXML
    private Button btn_importer;

    @FXML
    private Label label0;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private TextField nom;
    
    @FXML
    private TextField email;

    @FXML
    private TextField numero;

    @FXML
    private TextField prenom;
    
   

    @FXML
    void clickbtn_importer(ActionEvent event) {

    }
     @FXML
    void clikbtn_enregistrer(ActionEvent event) {

    }

    @FXML
    void clikbtn_echanger(ActionEvent event) {
        
        try{
             
        Parent root = FXMLLoader.load(getClass().getResource("echange3.fxml"));
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }catch(Exception e){
        e.printStackTrace();
    }

    }

    @FXML
    void email(ActionEvent event) {

    }

    
}
