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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import GUI.AffichagePController;
import GUI.InterfaceReclamationController;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class UserMenuController implements Initializable {

    private User connected;

    public void setConnectedUser(User connectedUser) {
        this.connected = connectedUser;
        System.out.println(connected + "client snet dadada");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void gotochat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/Chat Inbox.fxml"));
        Parent root = loader.load();
        ChatInboxController controller = loader.getController();
        controller.setConnectedUser(connected);
        controller.init();
        Stage stage = new Stage();
                stage.getIcons().add(new Image("C:/Users/ousso/Downloads/lgooo.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotopost(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/AffichageP.fxml"));
        Parent root = loader.load();
        AffichagePController controller = loader.getController();
        controller.setConnectedUser(connected);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:/Users/ousso/Downloads/lgooo.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoforum(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/listdeposts.fxml"));
        Parent root = loader.load();
        ListdepostsController controller = loader.getController();
        controller.setConnectedUser(connected);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:/Users/ousso/Downloads/lgooo.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoprofile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/profileUser.fxml"));
        Parent root = loader.load();
                profileUserController controller = loader.getController();
        controller.setConnectedUser(connected);
        controller.init();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:/Users/ousso/Downloads/lgooo.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoechange(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/echange3.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:/Users/ousso/Downloads/lgooo.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoreclamation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/InterfaceReclamation.fxml"));
        Parent root = loader.load();
        InterfaceReclamationController controller = loader.getController();
        controller.setConnectedUser(connected);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("C:/Users/ousso/Downloads/lgooo.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
