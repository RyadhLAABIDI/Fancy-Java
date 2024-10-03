/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Entities.Message;
import Services.ServiceMessage;
import java.net.URL;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class AdminPanelController implements Initializable {

    @FXML
    private TableView<Message> messageTable;
    private ServiceMessage test = new ServiceMessage();
    @FXML
    private Button ExitButton;

    @FXML
    public void exitScene(ActionEvent event) {
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TableColumn<Message, Number> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_message()));

        TableColumn<Message, Number> senderColumn = new TableColumn<>("SENDER");
        senderColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFrom_user()));

        TableColumn<Message, Number> recipentColumn = new TableColumn<>("CONVER ID");
        recipentColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTo_conv().getId()));
      //  TableColumn<Message, String> textColumn = new TableColumn<>("text");
       // textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
        TableColumn<Message, String> timeColumn = new TableColumn<>("Date");
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getDate_time())));
        messageTable.getColumns().addAll(idColumn, senderColumn, recipentColumn, timeColumn);
        messageTable.getItems().addAll(test.showmessage());

    }

    @FXML
    private void refreshtable(ActionEvent event) {
        messageTable.getItems().clear();

        messageTable.getItems().addAll(test.showmessage());
    }

}
