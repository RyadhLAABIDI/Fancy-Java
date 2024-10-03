/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Reclamation;
import Services.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fairouz
 */
public class AdminController implements Initializable {

    @FXML
    private Button Afficher;
    @FXML
    private Button supprimer;
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private Button Retour;
    @FXML
    private TextField eChercher;
    @FXML
    private Button bchercher;
    @FXML
    private TableColumn<Reclamation, LocalDate> date;
    @FXML
    private TableColumn<Reclamation, String> type;
    @FXML
    private TableColumn<Reclamation, String> contenu;
    @FXML
    private TableColumn<Reclamation, String> objet;
    @FXML
    private TableColumn<?, ?> categorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    private void supprimerHotel(ActionEvent event) {
        
    }

    @FXML
    private void getSelect(MouseEvent event) {
    }

    @FXML
    private void RetourMenu(ActionEvent event) throws IOException {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceReclamation.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) Retour.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    @FXML
    private void Chercherevent(ActionEvent event) {
         ServiceReclamation sr = new ServiceReclamation();
        ObservableList<Reclamation> list = FXCollections.observableList(sr.afficher());
       date.setCellValueFactory(new PropertyValueFactory<>("reclamation_Date"));
        type.setCellValueFactory(new PropertyValueFactory<>("reclamation_type"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("reclamation_category_id"));
           table.setItems(list);

        sr.afficher();
      table.setItems(list);

        FilteredList<Reclamation> filteredData;
        filteredData = new FilteredList<>(list, b -> true);
        eChercher.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Evenement -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Evenement.getReclamation_type().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                
                }  else {
                    return false;
                }

            });

        }));
        SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

    @FXML
    private void afficherReclamation(ActionEvent event) {
          ServiceReclamation sr = new ServiceReclamation();
        ObservableList<Reclamation> list = FXCollections.observableList(sr.afficher());
       date.setCellValueFactory(new PropertyValueFactory<>("reclamation_Date"));
        type.setCellValueFactory(new PropertyValueFactory<>("reclamation_type"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("reclamation_category_id"));
        
        //table.setItems(list);
        // Créez une instance de SortedList en utilisant votre liste observable
    SortedList<Reclamation> sortedList = new SortedList<>(list);

// Définissez le comparateur pour trier les éléments par ordre alphabétique du titre
    sortedList.setComparator((Reclamation r1, Reclamation r2) -> r1.getReclamation_type().compareTo(r2.getReclamation_type()));

// Utilisez la liste triée pour afficher les éléments dans votre table
    table.setItems(sortedList);
    }

    @FXML
    private void supprimerReclamation(ActionEvent event) {
         ServiceReclamation sr = new ServiceReclamation();
                Reclamation r = table.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);// alert de confirmation suppression
        alert.setTitle("ALERT suppression");
        alert.setHeaderText(null);
        alert.setContentText(" VOULEZ VOUS SUPPRIMER la Reclamation ?");
         
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            sr.supprimer(r);

            JOptionPane.showMessageDialog(null, " Reclamation supprimer ");
        } else {
            JOptionPane.showMessageDialog(null, " Reclamtion non supprimer ");
        }
        sr.afficher();
    }
    }

   
    

