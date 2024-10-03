/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.BadWords;

import Entities.Commentaire_forum;
import Entities.Post_forum;
import Services.CommentaireService;
import Services.PostServices;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import jdk.jshell.execution.LoaderDelegate;

/**
 * FXML Controller class
 *
 * @author salma
 */
public class CommentViewController implements Initializable {

    @FXML
    private TableView<Commentaire_forum> tvtabCom;
    @FXML
    private TableColumn<Commentaire_forum, String> colIdcom;
    @FXML
    private TableColumn<Commentaire_forum, String> ColNomuserc;
    @FXML
    private TableColumn<Commentaire_forum, String> ColDescriptionc;
    @FXML
    private ScrollPane ListCommentaires;
    @FXML
    private TableColumn<?, ?> ColDatec;
    public final ObservableList<Commentaire_forum> data = FXCollections.observableArrayList();
    Post_forum po = new Post_forum();
    @FXML
    private TextField tfnom_user;
    @FXML
    private TextArea tadescription;

    private Parent root;

    private VBox ForFuckSake;
    @FXML
    private TextFlow CommentsAndShit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.tfnom_user.setText("Current User");
            System.out.println("FIRST STOP");
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Etsports/commentView.fxml"));
            //this.root = loader.load();
            System.out.println("SECOND STOP");
            this.getComments();
        } catch (Exception ex) {
            System.out.println("THIRD STOP");
            System.out.println("Error Cause : " + ex.getMessage());
        }
    }

    @FXML
    private void selectCom(MouseEvent event) {
    }

    @FXML
    private void addcommentfront(ActionEvent event) throws SQLException, IOException {
        CommentaireService cer = new CommentaireService();

        String nom_user = tfnom_user.getText();
        String description = tadescription.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // p.getTest();
        if ((nom_user.equals("")) || (description.equals(""))) {
            //Alert saisie post :
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill all the fields first!");
            alert.showAndWait();
            //Alert saisie post !

        } else {

            BadWords.loadConfigs();

            if (BadWords.haveBad(description)) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING, "Votre Commentaire Contient des termes vulgaires!");
                alert2.showAndWait();
            } else {
                Commentaire_forum c = new Commentaire_forum(description, nom_user, po.getTest());

                try {
                    cer.ajouter(c);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Add comment");
                    alert.setHeaderText("Results:");
                    alert.setContentText("Post added successfully!");
                } catch (SQLException ex) {
                    //Alert Error jeux :
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Adding Error !! ");
                    alert.setContentText(ex.getMessage());
                    //Alert Error jeux !
                } finally {
                    alert.showAndWait();
                    CommentsAndShit.getChildren().clear();
                    tadescription.clear();
                    this.getComments();
                }
            }

        }
    }

    public void getComments() throws SQLException {

        //CommentaireService commentaireService = new CommentaireService();
        CommentaireService p1 = new CommentaireService();
        ObservableList<Commentaire_forum> comdata = FXCollections.observableArrayList();
        comdata.addAll(p1.postCommentaires(po.getTest()));
        List<Text> texts = new ArrayList<Text>();
        for (Commentaire_forum commentaire : comdata) {
            System.out.println(commentaire.toString());
            Text username = new Text(commentaire.getNomuser() + "\n");
            username.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            Text date = new Text(commentaire.getDate_c() + "\n");
            date.setFont(Font.font("Arial", FontWeight.LIGHT, 12));
            System.out.println("INFO" + commentaire.getDate_c() + "\n");
            Text comment = new Text(commentaire.getDescriptionc() + "\n\n");
            comment.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));

            texts.add(username);
            texts.add(date);
            texts.add(comment);
        }

        CommentsAndShit.getChildren().addAll(texts);

    }

    public void showPost() throws SQLException {
        CommentaireService p1 = new CommentaireService();
        ObservableList<Commentaire_forum> comdata = FXCollections.observableArrayList();
        // comdata.addAll(p1.afficher());
        comdata.addAll(p1.postCommentaires(po.getTest()));

        colIdcom.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColNomuserc.setCellValueFactory(new PropertyValueFactory<>("nomuser"));
        ColDescriptionc.setCellValueFactory(new PropertyValueFactory<>("descriptionc"));
        ColDatec.setCellValueFactory(new PropertyValueFactory<>("datec"));
        // System.out.println(id_post);

        tvtabCom.setItems(comdata);

    }

//    @FXML
//    private void deletcommentfront(ActionEvent event) {
//        
//    }
//    
//
//    @FXML
//    private void updatecommentfront(ActionEvent event) {
//    }
}
