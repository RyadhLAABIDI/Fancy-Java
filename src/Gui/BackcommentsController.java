/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Commentaire_forum;
import Entities.Post_forum;
import Services.CommentaireService;
import Services.PostServices;
import Services.SmS;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author salma
 */
public class BackcommentsController implements Initializable {

    @FXML
    private TableView<Post_forum> tabPost;
    @FXML
    private TableColumn<Post_forum, Integer> ColId;
    @FXML
    private TableColumn<Post_forum, String> colnom_user;
    @FXML
    private TableColumn<Post_forum, String> Colsujet;
    @FXML
    private TableColumn<Post_forum, String> Coldescription;
    @FXML
    private TableColumn<Post_forum, String> Colcommunaute;
    @FXML
    private TableColumn<Post_forum, String> ColImage;
    @FXML
    private TableColumn<Post_forum, Integer> ColNbrj;
    @FXML
    private TableColumn<Post_forum, Date> ColDatep;
    @FXML
    private TableView<Commentaire_forum> tvtabCom;
    @FXML
    private TableColumn<Commentaire_forum, Integer> colIdcom;
    @FXML
    private TableColumn<Commentaire_forum, String> ColNomuserc;
    @FXML
    private TableColumn<Commentaire_forum, String> ColDescriptionc;
    @FXML
    private TableColumn<Commentaire_forum, Date> ColDatec;
    @FXML
    private TextField mod_descriptionc;
    public ObservableList<Post_forum> data = FXCollections.observableArrayList();
    public ObservableList<Commentaire_forum> comdata = FXCollections.observableArrayList();
    PostServices p = new PostServices();
    CommentaireService c = new CommentaireService();
    @FXML
    private TextField tfsujet;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfcommunaute;
    @FXML
    private TextField tfnomuser;
    @FXML
    private Button buttonupload;
    @FXML
    private TextField tfimagep;
    @FXML
    private TextArea txtArea;
    @FXML
    private TextField searchBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            afficherEvent();

        } catch (SQLException ex) {
            Logger.getLogger(BackcommentsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void please_work(){
        try {
            if (this.searchBar.getText().length() != 0) {
                System.err.println(this.searchBar.getText());
                data.clear();
                data.addAll(p.affichageAvancee(this.searchBar.getText().toString()));

                ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
                colnom_user.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
                Colsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
                Coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                Colcommunaute.setCellValueFactory(new PropertyValueFactory<>("communaute"));
                ColDatep.setCellValueFactory(new PropertyValueFactory<>("date_p"));
                ColImage.setCellValueFactory(new PropertyValueFactory<>("image"));
                ColNbrj.setCellValueFactory(new PropertyValueFactory<>("nbr_jaime"));

                tabPost.setItems(data);

            } else {
                System.err.println("NOT");
                this.afficherEvent();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void selectCom(MouseEvent event) {

        Commentaire_forum A = tvtabCom.getSelectionModel().getSelectedItem();
        this.mod_descriptionc.setText(A.getDescriptionc());
        this.txtArea.setText(A.getDescriptionc());

        //ColDescriptionc.setText(A.getDescriptionc());
    }

    public String controlSaisie() {
        String nom = tfnomuser.getText();
        if (nom.equals("")) {
            return "You must type a name !";
        }
        String error = "";
        Pattern pattern = Pattern.compile("[\\d]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nom);
        boolean matchFound = matcher.find();
        if (matchFound) {
            error += "Name cant contain a number";
        } else {
            error = "";
        }
        return error;
    }

    @FXML
    private void handlepost(MouseEvent event) throws SQLException {
        comdata.clear();
        Post_forum post = tabPost.getSelectionModel().getSelectedItem();
        List<Commentaire_forum> coment = c.afficherCommentairesDuPost(post.getIdPost());

        tfnomuser.setText(post.getNom_user());
        tfsujet.setText(post.getSujet());
        tfdescription.setText(post.getDescription());
        tfcommunaute.setText(post.getCommunaute());
        tfimagep.setText(post.getImage());

        comdata.addAll(coment);
        System.out.println(comdata);
        colIdcom.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColNomuserc.setCellValueFactory(new PropertyValueFactory<>("nomuser"));
        ColDescriptionc.setCellValueFactory(new PropertyValueFactory<>("descriptionc"));
        ColDatec.setCellValueFactory(new PropertyValueFactory<>("datec"));
        tvtabCom.setItems(comdata);

    }

    @FXML
    private void selectPostcom(ActionEvent event) throws SQLException {
        //  Post_forum T = tabPost.getSelectionModel().getSelectedItem();
        //     int id_post= T.getIdPost();

        comdata.clear();
        this.mod_descriptionc.setText("");
        //  List<Commentaire> coment = c.getCombyPost(id_post);
        Post_forum post = tabPost.getSelectionModel().getSelectedItem();
        List<Commentaire_forum> coment = c.afficherCommentairesDuPost(post.getIdPost());

        comdata.addAll(coment);
        System.out.println(comdata);
        colIdcom.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColNomuserc.setCellValueFactory(new PropertyValueFactory<>("nomuser"));
        ColDescriptionc.setCellValueFactory(new PropertyValueFactory<>("descriptionc"));
        ColDatec.setCellValueFactory(new PropertyValueFactory<>("datec"));
        System.out.println("hh");

        tvtabCom.setItems(comdata);
    }

    public void ExitScene(ActionEvent event) {
        Stage stage = (Stage) tfsujet.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void deletecom(ActionEvent event) {

        Commentaire_forum c = tvtabCom.getSelectionModel().getSelectedItem();
        CommentaireService aS = new CommentaireService();
        if (aS.delete(c)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succees");
            alert.setHeaderText(null);
            alert.setContentText("La suppression d'event a été effectué avec succées");
            alert.showAndWait();
            tvtabCom.refresh();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La supression d'event n'a pas été effectué!");
            alert.showAndWait();
            tvtabCom.setItems(comdata);

        }
    }

    @FXML
    private void updatecom(ActionEvent event) {
        {
            Commentaire_forum A = tvtabCom.getSelectionModel().getSelectedItem();

            String desc = txtArea.getText();

            A.setDescriptionc(desc);

            CommentaireService aS = new CommentaireService();
            if (aS.update(A)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succées");
                alert.setHeaderText(null);
                alert.setContentText("La modification d'event a été effectué avec succées");
                alert.showAndWait();
                tvtabCom.refresh();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("La modufication d'event n'a pas été effectué!");
                alert.showAndWait();
                tvtabCom.setItems(comdata);
            }
        }
    }

    private void afficherEvent() throws SQLException {
        PostServices c = new PostServices();
        data.clear();
        data.addAll(p.afficher());
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom_user.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
        Colsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        Coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        Colcommunaute.setCellValueFactory(new PropertyValueFactory<>("communaute"));
        ColDatep.setCellValueFactory(new PropertyValueFactory<>("date_p"));
        ColImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        ColNbrj.setCellValueFactory(new PropertyValueFactory<>("nbr_jaime"));

        tabPost.setItems(data);

    }

    @FXML
    private void upload(ActionEvent event) {
        FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getAbsolutePath();
            String newfile = imageFile.replace("C:\\git\\FancyTrade\\src\\Images", "");
            tfimagep.setText(newfile);
        }
    }

    @FXML
    private void addpostback(ActionEvent event) throws SQLException, MalformedURLException, IOException {

        Post_forum P;
        String nom_user = tfnomuser.getText();
        String sujet = tfsujet.getText();
        String description = tfdescription.getText();
        String communaute = tfcommunaute.getText();
        String image = tfimagep.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String error = controlSaisie();

        if (sujet.equals("") || nom_user.equals("") || description.equals("") || communaute.equals("") || image.equals("")) {
            //Alert Saisie Tournois :
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill all the fields first!");
            alert.showAndWait();
        } else if (sujet.length() > 10) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
            alert.setHeaderText(null);
            alert.setContentText("sujet must be under 10 letters");
            alert.showAndWait();
        } else if (description.length() < 10) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
            alert.setHeaderText(null);
            alert.setContentText("description must be over 10 letters");
            alert.showAndWait();
        } else if (error != "") {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
            alert.setHeaderText(null);
            alert.setContentText("nom user must be only letters");
            alert.showAndWait();
        } //Alert Saisie Tournois !
        else {
            P = new Post_forum(sujet, description, communaute, nom_user, image);
            try {
                p.ajouter(P);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Add Post");
                alert.setHeaderText("Results:");
                alert.setContentText("post added successfully!");
            } catch (SQLException ex) {
                //Alert Error Tournois :
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Adding Error !! ");
                alert.setContentText(ex.getMessage());
                //Alert Error Tournois !
            } finally {
                alert.showAndWait();
                SmS s = new SmS();
                s.send_sms("+21652532874", "post added ");
            }
        }
        data.clear();
        try {
            data.addAll(p.afficher());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @FXML
    private void updatepostback(ActionEvent event) {
        Post_forum post = new Post_forum();
        if (tabPost.getSelectionModel().getSelectedItem() != null) {

            Post_forum P = tabPost.getSelectionModel().getSelectedItem();
            System.out.println("Sujet:" + tfsujet.getText());
            System.out.println("description:" + tfdescription.getText());
            System.out.println("communaute:" + tfcommunaute.getText());
            System.out.println("nomuser:" + tfnomuser.getText());
            post.setIdPost(P.getIdPost());
            post.setNom_user(tfnomuser.getText());
            post.setCommunaute(tfcommunaute.getText());
            post.setSujet(tfsujet.getText());
            post.setDescription(tfdescription.getText());
            post.setImage(tfimagep.getText());
            
            p.UpdateF(post);

            Alert EditeJeuxAlert = new Alert(Alert.AlertType.INFORMATION);
            EditeJeuxAlert.setTitle("edit");
            EditeJeuxAlert.setHeaderText(null);
            EditeJeuxAlert.setContentText("post was succfuly Updated");
            EditeJeuxAlert.showAndWait();

        } else {
            //Alert Select jeux :
            Alert selectMealAlert = new Alert(Alert.AlertType.WARNING);
            selectMealAlert.setTitle("Select a post");
            selectMealAlert.setHeaderText(null);
            selectMealAlert.setContentText("You need to select post first!");
            selectMealAlert.showAndWait();
            //Alert Select jeux !
        }

        data.clear();
        try {
            data.addAll(p.afficher());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @FXML
    private void deletepostback(ActionEvent event)
            throws SQLException {

        if (tabPost.getSelectionModel().getSelectedItem() != null) {
            Alert deleteTournoislert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteTournoislert.setTitle("Delete post");
            deleteTournoislert.setHeaderText(null);
            deleteTournoislert.setContentText("Are you sure want to delete this post ?");
            Optional<ButtonType> optiondeleteTournoisAlert = deleteTournoislert.showAndWait();
            if (optiondeleteTournoisAlert.get() == ButtonType.OK) {
                Post_forum P = tabPost.getSelectionModel().getSelectedItem();
                System.out.println(P.getIdPost());
                p.supprimer(P.getIdPost());

                //Alert Delete Blog :
                Alert succDeleteMealAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteMealAlert.setTitle("Delete post");
                succDeleteMealAlert.setHeaderText("Results:");
                succDeleteMealAlert.setContentText("post deleted successfully!");

                succDeleteMealAlert.showAndWait();
            } else if (optiondeleteTournoisAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            Alert selectMealAlert = new Alert(Alert.AlertType.WARNING);
            selectMealAlert.setTitle("Select a post");
            selectMealAlert.setHeaderText(null);
            selectMealAlert.setContentText("You need to select post first!");
            selectMealAlert.showAndWait();

        }

        data.clear();
        try {
            data.addAll(p.afficher());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

}
