/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Post_forum;
import Services.CommentaireService;
import Services.PostServices;
import entities.User;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author salma
 *
 */
public class ListdepostsController implements Initializable {

    @FXML
    private Label totalcom;
    @FXML
    private Label totalpost;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button ExitButton;
    @FXML
    private Button ajoutPoste;
    @FXML
    private ComboBox communautes;
    private User connected;
    public void setConnectedUser(User connectedUser) {
        this.connected = connectedUser;
        System.out.println(connected + "client snet dadada");
    }
    public void exitScene(ActionEvent event) {
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }

    public void filter(ActionEvent event) throws SQLException {
        if (this.communautes.getValue().equals("Afficher Tous")) {
            grid.getChildren().clear();
            this.refreshData();
        } else {
            grid.getChildren().clear();
            Postdata.clear();
            Postdata.addAll(po.getBCommunaute(this.communautes.getValue().toString()));

            int column = 0;
            int row = 3;
            for (int i = 0; i < Postdata.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Gui/Postcard.fxml"));

                try {

                    AnchorPane anchorPane = fxmlLoader.load();

                    PostcardController itemController = fxmlLoader.getController();
                    itemController.setData(Postdata.get(i).getIdPost(), Postdata.get(i).getSujet(), Postdata.get(i).getDescription(), Postdata.get(i).getCommunaute(), Postdata.get(i).getNom_user(), Postdata.get(i).getDate_p(), Postdata.get(i).getImage(), Postdata.get(i).getNbr_jaime(), po.getNumberOfComments(Postdata.get(i).getIdPost()));
                    if (column == 2) {
                        column = 0;
                        row++;
                    }
                    //Node anchorPane;
                    grid.add(anchorPane, column++, row); //Child , column , row

                    //Set Item Grid Width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    //Set Item Grid Height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(10));
                } catch (IOException ex) {
                    Logger.getLogger(ListdepostsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    public void openAjoutPoste(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/Gui/FrontAjoutPoste.fxml"));
        Parent root = (Parent) fXMLLoader.load();
                FrontAjoutPosteController controller = fXMLLoader.getController();
        controller.setConnectedUser(connected);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setOnCloseRequest(event2 -> {
            try {
                this.refreshData();
            } catch (SQLException ex) {
                Logger.getLogger(ListdepostsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public ObservableList<Post_forum> Postdata = FXCollections.observableArrayList();
    PostServices po = new PostServices();
    private List<Post_forum> Post = new ArrayList<>();
    CommentaireService co = new CommentaireService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            this.refreshData();
            this.communautes.getItems().addAll(
                    "Afficher Tous",
                    "Voitures",
                    "Motos",
                    "Yachts",
                    "Villas",
                    "Hotels",
                    "Immeubles",
                    "Terrains"
            );
            this.communautes.setValue("Afficher Tous");
        } catch (Exception ex) {
            Logger.getLogger(ListdepostsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refreshData() throws SQLException {
        try {
            grid.getChildren().clear();
            Postdata.clear();
            Postdata.addAll(po.afficher());
        } catch (SQLException ex) {
            Logger.getLogger(PostcardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        int column = 0;
        int row = 3;
        for (int i = 0; i < Postdata.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Gui/Postcard.fxml"));

            try {

                AnchorPane anchorPane = fxmlLoader.load();

                PostcardController itemController = fxmlLoader.getController();
                itemController.setData(Postdata.get(i).getIdPost(), Postdata.get(i).getSujet(), Postdata.get(i).getDescription(), Postdata.get(i).getCommunaute(), Postdata.get(i).getNom_user(), Postdata.get(i).getDate_p(), Postdata.get(i).getImage(), Postdata.get(i).getNbr_jaime(), po.getNumberOfComments(Postdata.get(i).getIdPost()));
//          itemController.setData(11,"ghj","jnui","hhhnu","hhuhuh","huiu",5);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                //Node anchorPane;
                grid.add(anchorPane, column++, row); //Child , column , row

                //Set Item Grid Width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //Set Item Grid Height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            } catch (IOException ex) {
                Logger.getLogger(ListdepostsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
