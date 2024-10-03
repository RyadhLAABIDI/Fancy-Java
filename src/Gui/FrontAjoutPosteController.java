/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;

import Entities.Post_forum;
import Services.BadWords;
import Services.PostServices;
import Services.SmS;
import entities.User;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FrontAjoutPosteController implements Initializable {

    @FXML
    private TextField nomSujet;
    @FXML
    private TextArea desc;
    @FXML
    private ComboBox nomCommunaute;
    @FXML
    private TextField imgUrl;
    @FXML
    private Button imgBrowse;
    @FXML
    private ImageView imgShow;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button addBtn;
    private User connected;
    @FXML
    private Label userName;
    public void setConnectedUser(User connectedUser) {
        this.connected = connectedUser;
        System.out.println(connected + "client snet dadada");
        userName.setText(connected.getNom());
    }

    PostServices postService = new PostServices();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.imgUrl.setDisable(true);
        this.imgUrl.setVisible(false);
        this.nomCommunaute.getItems().addAll(
                "Voitures",
                "Motos",
                "Yachts",
                "Villas",
                "Hotels",
                "Immeubles",
                "Terrains"
        );

    }

    @FXML
    public void close(ActionEvent event) {
        Stage stage = (Stage) this.cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void browseFiles(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Veuillez Choisir une Image...");
        File selectedFile = fileChooser.showOpenDialog(this.cancelBtn.getScene().getWindow());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            this.imgUrl.setText(selectedFile.getAbsolutePath().replace("C:\\git\\FancyTrade\\src\\Images\\", ""));
            this.imgShow.setImage(image);

        }
    }

    @FXML
    public void confirm(ActionEvent event) throws SQLException, MalformedURLException, IOException {
        Alert alert;
        if (this.nomSujet.getText().equals("")
                || this.nomCommunaute.getValue().toString().equals("")
                || this.desc.getText().equals("")
                || this.imgUrl.getText().equals("")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Un Problème et survenu");
            alert.setContentText("Vous devez renseigner tous les champs!");
            alert.showAndWait();
        } else {
            BadWords.loadConfigs();
            if (BadWords.filterText(this.nomSujet.getText()) || BadWords.filterText(this.desc.getText())) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING, "Les motes vulgaires ne sont pas tolérés!!!");
                alert2.showAndWait();
            } else {
                Post_forum p = new Post_forum();
                p.setNom_user(this.connected.getNom());
                p.setSujet(this.nomSujet.getText());
                p.setCommunaute(this.nomCommunaute.getValue().toString());
                p.setDescription(this.desc.getText());
                p.setImage(this.imgUrl.getText());

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirmation");
                alert.setContentText("Êtes-vous sûr de tous ces informations?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            this.postService.ajouter(p);
                            SmS s = new SmS();
                            s.send_sms("+21652532874", "post added ");
                            alert.close();
                            Alert secondAlert = new Alert(Alert.AlertType.INFORMATION);
                            secondAlert.setTitle("SUCCESS");
                            secondAlert.setHeaderText("Succées");
                            secondAlert.setContentText("Le Poste a été ajouté avec Succées");
                            secondAlert.showAndWait().ifPresent(response2 -> {
                                secondAlert.close();
                                Stage stage = (Stage) this.cancelBtn.getScene().getWindow();
                                stage.close();
                            });
                        } catch (SQLException ex) {
                            Logger.getLogger(FrontAjoutPosteController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }

        }
    }

}
