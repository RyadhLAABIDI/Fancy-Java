/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import Entities.Poste;
import Services.ServicePoste;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azyz
 */
public class AffichagePostController implements Initializable {

    @FXML
    private TableView<Poste> messageTable;
    private ServicePoste test = new ServicePoste();
    @FXML
    private Button ExitButton;

    @FXML
    public void exitScene(ActionEvent event) {
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TableColumn<Poste, Number> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()));

        TableColumn<Poste, String> titreColumn = new TableColumn<>("TITRE");
        titreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitre()));
        TableColumn<Poste, String> descriptionColumn = new TableColumn<>("DESCRIPTION");
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDesc()));
        TableColumn<Poste, Date> dateColumn = new TableColumn<>("DATE");
        dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>(cellData.getValue().getDate()));
        TableColumn<Poste, Number> userColumn = new TableColumn<>("ID");
        userColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUser().getId()));
        messageTable.getColumns().addAll(idColumn, titreColumn, descriptionColumn, dateColumn,userColumn);
        messageTable.getItems().addAll(test.getAll());

    }

    @FXML
    private void refreshtable(ActionEvent event) {
        messageTable.getItems().clear();

        messageTable.getItems().addAll(test.getAll());
    }

}
