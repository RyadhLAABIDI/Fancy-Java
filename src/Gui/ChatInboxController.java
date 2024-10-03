package Gui;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import Services.ServiceConversation;
import Services.ServiceMessage;
import Entities.Conversation;
import Entities.Message;
import Services.ServiceChat;
import entities.User;
import static java.awt.SystemColor.text;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussema
 */
// image + api location + design + recherche conv message + admin view
public class ChatInboxController implements Initializable {

    private int id;
    @FXML
    private Button send_button;
    @FXML
    private TextField message_input;
    private DataInputStream dis;
    private ServiceChat sc = new ServiceChat();
    private DataOutputStream dos;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    ServiceMessage testt = new ServiceMessage();
    ServiceConversation convv = new ServiceConversation();
    Conversation Current_conv = new Conversation();
    @FXML
    private VBox inbox_list;
    @FXML
    private VBox message_box;
    @FXML
    private Label alertlabel;
    @FXML
    private TextField inputconver;
    @FXML
    private TextField inputmess;
    @FXML
    private Button upimage;
    @FXML
    private Button location;
    @FXML
    private ScrollPane inboxscroll;
    @FXML
    private ScrollPane messagescroll;
    @FXML
    private Pane messagepane;
    private User connected;
    @FXML
    private Button ExitButton;

    public void setConnectedUser(User connectedUser) {
        this.connected = connectedUser;
        System.out.println(connected + "client snet dadada");
    }
    @FXML
    public void exitScene(ActionEvent event) {
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }
    public void init() {
        System.out.println(connected + "client snet dadada");
        sc.paindesign(messagepane);
        sc.inputdesign(inputconver, inputmess, message_input);
        sc.scrolldesign(inboxscroll, messagescroll);
        sc.vboxdesign(inbox_list, message_box);
        sc.buttondesign(upimage, location, send_button);
        Image image = new Image("C:/Users/ousso/Documents/NetBeansProjects/Fancy_Trade_Messagerie/src/Services/map.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(40); // set the width to 200 pixels
        imageView.setFitHeight(40);
        location.setGraphic(imageView);
        Image image2 = new Image("C:/Users/ousso/Documents/NetBeansProjects/Fancy_Trade_Messagerie/src/Services/image.png");
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(40); // set the width to 200 pixels
        imageView2.setFitHeight(40);
        upimage.setGraphic(imageView2);
        Image image3 = new Image("C:/Users/ousso/Documents/NetBeansProjects/Fancy_Trade_Messagerie/src/Services/send.png");
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitWidth(40); // set the width to 200 pixels
        imageView3.setFitHeight(40);
        send_button.setGraphic(imageView3);
        inbox_list.setPadding(new Insets(10));
        inbox_list.setSpacing(10);
        alertlabel.setVisible(false);
        convv.GetConversations(Integer.toString(connected.getId()), inbox_list);
        try {
            Socket socket = new Socket("localhost", 9999);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(Integer.toString(connected.getId()));
            System.out.println("clientid sent to server");

        } catch (IOException ex) {
            Logger.getLogger(ChatInboxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    String readutf = dis.readUTF();

                    if (readutf != null) {
                        Platform.runLater(() -> {
                            String[] receivedData = readutf.split(";;");
                            String type = receivedData[0];
                            String senderID = receivedData[1];
                            String receiverID = receivedData[2];
                            String text = receivedData[3];
                            if (type.equals("0")) {
                                Message messagee = testt.messagefromserver(readutf, Current_conv);
                                System.out.println("hello");
                                if (messagee != null) {
                                    testt.msgtemplate(Integer.toString(connected.getId()), messagee, message_box);
                                }
                            } else if (type.equals("1")) {
                                for (Node child : message_box.getChildren()) {
                                    if (child.getId() != null && child.getId().equals(text)) {
                                        message_box.getChildren().remove(child);
                                        break;
                                    }
                                }
                            } else if (type.equals("2")) {
                                String[] contenu = text.split("--");
                                String idhbox = contenu[0];
                                String modifiedtext = contenu[1];
                                for (Node child : message_box.getChildren()) {
                                    if (child.getId() != null && child.getId().equals(idhbox)) {
                                        HBox hbox1 = (HBox) child;
                                        Text text1 = (Text) hbox1.getChildren().get(0);
                                        text1.setText(modifiedtext + " (modifié)");
                                        break;
                                    }
                                }

                            }

                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void Send_message(ActionEvent event) throws IOException {

        if (Current_conv.getId() != 0) {
            if (!testt.checkInput(message_input, alertlabel)) {
                try {
                    dos.writeUTF("0" + ";;" + Integer.toString(connected.getId()) + ";;" + Current_conv.getId() + ";;" + message_input.getText());
                    Message m = new Message(Integer.parseInt(Integer.toString(connected.getId())), Current_conv, message_input.getText());
                    testt.sendmessage(m, message_box);
                    message_input.clear();
                    alertlabel.setVisible(false);
                } catch (IOException ex) {
                    Logger.getLogger(ChatInboxController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    @FXML
    private void switch_conversation(MouseEvent event) {
        alertlabel.setVisible(false);
        int index = 0;
        Node target = (Node) event.getTarget();
        if (target instanceof Text) {
            while (index < inbox_list.getChildren().size()) {
                Node node = inbox_list.getChildren().get(index);
                node.setStyle("-fx-background-color: #808080; -fx-background-radius: 50px;");
                index++;
            }
            HBox clickedHbox = (HBox) target.getParent();
            clickedHbox.setStyle("-fx-background-color: #d7b14a; -fx-background-radius: 50px;");

            Text text = (Text) target;
            Current_conv = (Conversation) text.getUserData();
            testt.retrieveMessagesFromDB(Integer.toString(connected.getId()), message_box, Current_conv);
        } else if (target instanceof HBox) {
            while (index < inbox_list.getChildren().size()) {
                Node node = inbox_list.getChildren().get(index);
                node.setStyle("-fx-background-color: #808080; -fx-background-radius: 50px;");
                index++;
            }
            HBox clickedHbox = (HBox) target;
            clickedHbox.setStyle("-fx-background-color: #d7b14a; -fx-background-radius: 50px;");
            Conversation conv;
            Text text = new Text();
            for (Node child : ((HBox) target).getChildren()) {
                if (child instanceof Text) {
                    text = (Text) child;
                }
            }
            conv = (Conversation) text.getUserData();
            Current_conv = (Conversation) text.getUserData();
            testt.retrieveMessagesFromDB(Integer.toString(connected.getId()), message_box, Current_conv);

        }

    }

    @FXML
    private void message_option(MouseEvent event) {
        Node target = (Node) event.getTarget();
        if (target instanceof HBox) {

            if (event.getButton() == MouseButton.SECONDARY) {

                ContextMenu contextMenu = new ContextMenu();
                MenuItem option1 = new MenuItem("Supprimer pour tout le monde");
                MenuItem option2 = new MenuItem("Supprimer");
                MenuItem option3 = new MenuItem("Modifier");
                option1.setOnAction(e -> {
                    testt.deletemessage(target.getId());
                    message_box.getChildren().remove(target);
                    try {
                        dos.writeUTF("1" + ";;" + Integer.toString(connected.getId()) + ";;" + Current_conv.getId() + ";;" + target.getId());
                    } catch (IOException ex) {
                        Logger.getLogger(ChatInboxController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                option2.setOnAction(e -> {
                    message_box.getChildren().remove(target);
                });
                option3.setOnAction(e -> {
                    Dialog<String> dialog = new Dialog<>();
                    dialog.setTitle("Input Dialog");
                    dialog.setHeaderText("Enter a text");
                    Label label = new Label("Text:");
                    TextField textField = new TextField();
                    VBox content = new VBox(label, textField);
                    dialog.getDialogPane().setContent(content);
                    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
                    //  message_box.getChildren().remove(target);
                    dialog.setResultConverter(button -> {
                        if (button == ButtonType.OK) {
                            return textField.getText();
                        }
                        return null;
                    });
                    dialog.showAndWait();
                    if (!dialog.getResult().trim().isEmpty() && dialog.getResult() != null) {
                        testt.updatemessage(target.getId(), dialog.getResult());
                        HBox hbox = (HBox) target;
                        Text text = (Text) hbox.getChildren().get(0);
                        text.setText(dialog.getResult() + " (modifié)");
                        try {
                            dos.writeUTF("2" + ";;" + Integer.toString(connected.getId()) + ";;" + Current_conv.getId() + ";;" + target.getId() + "--" + dialog.getResult());
                        } catch (IOException ex) {
                            Logger.getLogger(ChatInboxController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("text : " + dialog.getResult());
                    }
                    // target.getChildren().get(0).setText(dialog.getResult);
                });
                if (!testt.messageowner(target.getId()).equals(Integer.toString(connected.getId()))) {
                    if (contextMenu.isShowing()) {
                        contextMenu.hide();
                    }
                    contextMenu.getItems().add(option2);
                    contextMenu.show(target, Side.BOTTOM, 0, 0);
                } else {
                    if (contextMenu.isShowing()) {
                        contextMenu.hide();
                    }
                    contextMenu.getItems().addAll(option1, option2, option3);
                    contextMenu.show(target, Side.BOTTOM, 0, 0);
                }

            }

        } else if (target instanceof Text) {
            Node targett = target.getParent();
            if (event.getButton() == MouseButton.SECONDARY) {
                ContextMenu contextMenu = new ContextMenu();
                MenuItem option1 = new MenuItem("Supprimer pour tout le monde");
                MenuItem option2 = new MenuItem("Supprimer");
                MenuItem option3 = new MenuItem("Modifier");
                option1.setOnAction(e -> {
                    testt.deletemessage(targett.getId());
                    message_box.getChildren().remove(targett);
                    try {
                        dos.writeUTF("1" + ";;" + Integer.toString(connected.getId()) + ";;" + Current_conv.getId() + ";;" + targett.getId());
                    } catch (IOException ex) {
                        Logger.getLogger(ChatInboxController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                option2.setOnAction(e -> {
                    message_box.getChildren().remove(targett);
                });
                option3.setOnAction(e -> {
                    Dialog<String> dialog = new Dialog<>();
                    dialog.setTitle("Input Dialog");
                    dialog.setHeaderText("Enter a text");

                    // Create a label and a text field
                    Label label = new Label("Text:");
                    TextField textField = new TextField();
                    VBox content = new VBox(label, textField);
                    dialog.getDialogPane().setContent(content);
                    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
                    dialog.setResultConverter(button -> {
                        if (button == ButtonType.OK) {
                            return textField.getText();
                        }
                        return null;
                    });
                    dialog.showAndWait();
                    if (!dialog.getResult().trim().isEmpty() && dialog.getResult() != null) {
                        testt.updatemessage(targett.getId(), dialog.getResult());
                        Text text = (Text) target;
                        if (!testt.messageowner(targett.getId()).equals(Integer.toString(connected.getId()))) {
                            text.setText(dialog.getResult() + " (modifié)");
                        } else {
                            text.setText(dialog.getResult() + " (modifié)");
                        }
                        try {
                            dos.writeUTF("2" + ";;" + Integer.toString(connected.getId()) + ";;" + Current_conv.getId() + ";;" + targett.getId() + "--" + dialog.getResult());
                        } catch (IOException ex) {
                            Logger.getLogger(ChatInboxController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    // target.getChildren().get(0).setText(dialog.getResult);
                });
                if (!testt.messageowner(targett.getId()).equals(Integer.toString(connected.getId()))) {
                    if (contextMenu.isShowing()) {
                        contextMenu.hide();
                    }
                    contextMenu.getItems().add(option2);
                    contextMenu.show(target, Side.BOTTOM, 0, 0);
                } else {
                    if (contextMenu.isShowing()) {
                        contextMenu.hide();
                    }
                    contextMenu.getItems().addAll(option1, option2, option3);
                    contextMenu.show(target, Side.BOTTOM, 0, 0);

                }

            }
        }

    }

    @FXML
    private void Search(KeyEvent event) {
        Current_conv.setId(0);
        message_box.getChildren().clear();
        convv.SearchConversations(inputconver.getText(), Integer.toString(connected.getId()), inbox_list);
    }

    @FXML
    private void searchmessage(KeyEvent event) {
        testt.searchMessagesFromDB(inputmess.getText(), Integer.toString(connected.getId()), message_box, Current_conv);
    }

    @FXML
    private void uploadimage(ActionEvent event) {
        if (Current_conv.getId() != 0) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image File");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
            );
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                ImageView imageView = new ImageView(image);
                double initialWidth = 100;
                double initialHeight = 100;
                imageView.setFitWidth(initialWidth); // set the width to 200 pixels
                imageView.setFitHeight(initialHeight); // set the height to 150 pixels
                imageView.setOnMouseClicked(eventt -> {
                    Stage stage = new Stage();
                    ImageView enlargedImageView = new ImageView();
                    enlargedImageView.setImage(imageView.getImage());
                    enlargedImageView.setFitWidth(800);
                    enlargedImageView.setFitHeight(600);
                    enlargedImageView.setPreserveRatio(true);
                    Scene scene = new Scene(new Group(enlargedImageView));
                    stage.setScene(scene);
                    stage.show();
                });
                try {
                    Message m = new Message(Integer.parseInt(Integer.toString(connected.getId())), Current_conv, selectedFile.toURI().toString());
                    testt.sendImage(m, message_box, imageView);
                    dos.writeUTF("0" + ";;" + Integer.toString(connected.getId()) + ";;" + Current_conv.getId() + ";;" + selectedFile.toURI().toString());
                } catch (IOException ex) {
                    Logger.getLogger(ChatInboxController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    @FXML
    private void sendLocation(ActionEvent event) {
        if (Current_conv.getId() != 0) {
            String Location = sc.getLocation();
            System.out.println(Location);
            String[] receivedData = Location.split("--");
            String Latitude = receivedData[0];
            String Longitude = receivedData[1];
            try {
                dos.writeUTF("0" + ";;" + Integer.toString(connected.getId()) + ";;" + Current_conv.getId() + ";;" + Location);
                Message m = new Message(Integer.parseInt(Integer.toString(connected.getId())), Current_conv, Location);
                testt.sendmessage(m, message_box);
                alertlabel.setVisible(false);
            } catch (IOException ex) {
                Logger.getLogger(ChatInboxController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
