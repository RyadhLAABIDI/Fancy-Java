
package Gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class switch1 {
    
    @FXML
    private Button btn_echange;

    @FXML
    private Button btn_poste;

    @FXML
    void clickbtnechange(ActionEvent event) {

         try{    
        Parent root = FXMLLoader.load(getClass().getResource("echange2.fxml"));
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show(); 
    }catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    void clickbtnposte(ActionEvent event) {

    }
    
}
