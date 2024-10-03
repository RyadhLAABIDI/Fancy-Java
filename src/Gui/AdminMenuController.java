/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class AdminMenuController implements Initializable {

    public User connected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void gotoadminchat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/AdminPanel.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:/Users/ousso/Downloads/lgooo.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoadminpost(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/AffichagePost.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:/Users/ousso/Downloads/lgooo.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoadminforum(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/Backcomments.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:/Users/ousso/Downloads/lgooo.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoadminuser(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/CRUDUser.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:/Users/ousso/Downloads/lgooo.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoadminreclamation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/Admin.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:/Users/ousso/Downloads/lgooo.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoadminechange(ActionEvent event) {
    }

}
