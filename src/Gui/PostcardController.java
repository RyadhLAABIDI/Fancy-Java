/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Post_forum;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author salma
 */
public class PostcardController implements Initializable {

    @FXML
    private ImageView imagepost;
    @FXML
    private Label tfnom_user;
    @FXML
    private Label tfsujet;
    @FXML
    private Label tfdescription;
    @FXML
    private Label tfdatepost;
    @FXML
    private Label tfnbr_jaime;
    @FXML
    private Label idpostlabel;
    Post_forum po = new Post_forum();
    @FXML
    private Label tfcommunaute;
    @FXML
    private Label labelimage;
    @FXML
    private Button commentsButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setData(int id, String sujet, String description, String communaute, String nom_user, Date date_p, String image, int nbr_jaime , int nb) {

        tfsujet.setText(sujet);
        tfdescription.setText(description);
        tfnom_user.setText(nom_user);
        labelimage.setVisible(false);
        tfcommunaute.setText(communaute);
        this.commentsButton.setText(nb+" "+commentsButton.getText());
//    Image imgp = new Image(getClass().getResourceAsStream("/ressources/"+ image +""));
//    imagepost.setImage(imgp);
        File file = new File("C:/git/FancyTrade/src/Images" + image);
        //System.out.println(("C:/Users/DELL/Desktop/ProjetPi/Esports-Application/blog/src/Images/" + image));
        Image img = new Image(file.toURI().toString());
        imagepost.setImage(img);

        //labelimage.setText(image);
        tfdatepost.setText(String.valueOf(date_p));
        tfnbr_jaime.setText(String.valueOf(nbr_jaime));
        idpostlabel.setText(String.valueOf(id));

    }

    @FXML
    private void comentpostbtn(ActionEvent event) {
        po.setTest(Integer.parseInt(idpostlabel.getText()));
        try {
            Parent root = FXMLLoader.load(getClass().getResource("commentView.fxml"));
            Stage stage = new Stage();
            stage.setUserData(po.getIdPost());
            stage.setTitle("comentaire");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ex) {
            System.out.println("can't load comrnt window");
        }
    }

}
