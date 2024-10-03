
package Gui;


import Entities.produit1;
import gestion_echange.user1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class switch3  {
       @FXML
    private TableColumn<produit1, String> Col_categorie;

    @FXML
    private TableColumn<user1, String> Col_email;

    @FXML
    private TableColumn<produit1, String> Col_idproduit;

    @FXML
    private TableColumn<user1, String> Col_iduser;

    @FXML
    private TableColumn<user1, String> Col_nom;

    @FXML
    private TableColumn<user1, String> Col_numero;

    @FXML
    private TableColumn<user1, String> Col_prenom;

    @FXML
    private TableColumn<produit1, String> Col_valeur;

    @FXML
    private Button btn_ajouter;

    @FXML
    private Button btn_confirmer;

    @FXML
    private Button btn_consulter;

    @FXML
    private TextField categorie;

    @FXML
    private Label label0;

    @FXML
    private Label label6;

    @FXML
    private Label label7;

    @FXML
    private Label label8;

    @FXML
    private Label label9;

    @FXML
    private TableView<produit1> tab_prod;

    @FXML
    private TableView<user1> tab_user;

    @FXML
    private TextField valeur;

    @FXML
    void clickbtn_ajouter(ActionEvent event) {
        
}
    @FXML
    void clikbtn_confirmer(ActionEvent event) {

    }

    @FXML
    void clikbtn_consulter(ActionEvent event) {
         try{
             
        Parent root = FXMLLoader.load(getClass().getResource("TableEchange.fxml"));
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
