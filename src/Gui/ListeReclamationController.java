/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Reclamation;
import Services.ServiceReclamation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.User;




/**
 * FXML Controller class
 *
 * @author fairouz
 */
public class ListeReclamationController implements Initializable {

    @FXML
    private Button Afihcher;
    @FXML
    private Button supprimer;
    @FXML
    private Button Retour;
    @FXML
    private TextField eChercher;
    @FXML
    private Button bchercher;
    @FXML
    private Button mail;
    @FXML
    private Button modifier1;
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private TableColumn<Reclamation, LocalDate> date;
    @FXML
    private TableColumn<Reclamation, String> type;
    @FXML
    private TableColumn<Reclamation, String> contenu;
    @FXML
    private TableColumn<Reclamation, String> objet;
    @FXML
    private TableColumn<Reclamation, Integer> categorie;
    @FXML
    private TextField typ;
    @FXML
    private TextField obj;
    @FXML
    private TextField cont;
    @FXML
    private DatePicker datepick;
    private User connected;

    public void setConnectedUser(User connectedUser) {
        this.connected = connectedUser;
        System.out.println(connected + "client snet dadada");
    }
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
    }    
public void init(){
          ActionEvent event = null;
afficherReclamation(event);
}
    @FXML
    private void modifierReclamation(ActionEvent event) {
          ServiceReclamation sr = new ServiceReclamation();

        String type = typ.getText();
        if (typ.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
         alert.setContentText(" vérifier vos informations ");
          
            alert.showAndWait();
        } else {
        
        Reclamation r =  (Reclamation) table.getSelectionModel().getSelectedItem();
         r.setReclamation_Date(datepick.getValue());
        r.setReclamation_type(typ.getText());
        r.setContenu(cont.getText());
        r.setObjet(obj.getText());
      
    JOptionPane.showMessageDialog(null, "Reclamation modifiee !");
        
        sr.modifier(r);
        sr.afficher();
         
    }
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
    private void imprimer(ActionEvent event) {
        try {

            OutputStream file = new FileOutputStream(new File("\\\\Mac\\Home\\Desktop\\PIDEV\\Reclamation.pdf"));
          com.itextpdf.text.Document document = new com.itextpdf.text.Document();
PdfWriter.getInstance(document, file);
            document.open();
            ServiceReclamation sm = new ServiceReclamation();
            //get from table
            Reclamation r = table.getSelectionModel().getSelectedItem();

          

            document.add(new Paragraph("************************Liste De RECLAMATION************************\n\n\n\n\n\n\n"));

            document.add(new Paragraph(" ___________________________________________________________________________\n"));
            document.add(new Paragraph(" Type de reclamation :  " + r.getReclamation_type() + "  \n"));
            document.add(new Paragraph(" date  :  " + r.getReclamation_Date() + "  \n"));
            document.add(new Paragraph(" contenu  :  " + r.getContenu() + "  \n"));
            document.add(new Paragraph(" objet :  " + r.getObjet() + "  \n"));
            


            document.add(new Paragraph(" _______________________________________________________________________"));

            document.close();
            file.close();

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());

        }

    
    }

    @FXML
    private void getSelect(MouseEvent event) {
               int index = table.getSelectionModel().getSelectedIndex();
    if (index <= -1){
   
        return;
    }

    typ.setText(type.getCellData(index).toString());
 
    cont.setText(contenu.getCellData(index).toString());
   obj.setText(objet.getCellData(index).toString());
    
    }

    @FXML
    private void afficherReclamation(ActionEvent event) {
         ServiceReclamation sr = new ServiceReclamation();
        ObservableList<Reclamation> list = FXCollections.observableList(sr.afficheruser(connected));
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
    
}
