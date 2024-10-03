package Services;

import DB.Database;
import Entities.Conversation;
import Entities.Message;
import Server.ServerMessage;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author oussema
 */
public class ServiceMessage {

    private ServiceChat sc = new ServiceChat();

    public String prenom(String id) {
        Connection connection;
        connection = Database.getInstance().getCon();
        String query = "SELECT prenom FROM utilisateur WHERE id = ?";
        PreparedStatement statement;
        String user_id = "not set";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user_id = resultSet.getString(1); //"zadaz";
            }

        } catch (SQLException ex) {

        }
        return user_id;
    }

    public String GetReceiver(String id, String convid) {
        Connection connection;
        connection = Database.getInstance().getCon();
        String query = "SELECT idconv_user FROM conv WHERE idconv_user2 = ? AND id = ?";
        PreparedStatement statement;
        String user_id = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, convid);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user_id = resultSet.getString(1); //"zadaz";
            }

        } catch (SQLException ex) {

        }
        if (user_id == null) {
            System.out.println("nullllllll");
            query = "SELECT idconv_user2 FROM conv WHERE idconv_user = ? AND id = ?";
            try {
                statement = connection.prepareStatement(query);
                statement.setString(1, id);
                statement.setString(2, convid);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    user_id = resultSet.getString(1); //"zadaz";
                }

            } catch (SQLException ex) {

            }
        } else {
            System.out.println(" not nullllllll");
        }
        return user_id;
    }

    public void retrieveMessagesFromDB(String clientID, VBox messages, Conversation conv) {
        messages.getChildren().clear();
        Connection connection;
        connection = Database.getInstance().getCon();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM message where to_conv = ? ORDER BY date_time");
            preparedStatement.setString(1, Integer.toString(conv.getId()));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Message m = new Message(resultSet.getInt("id_message"), resultSet.getInt("from_user"), resultSet.getString("message_text"));

                this.msgtemplate(clientID, m, messages);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean checkImage(String Message) {

        if (Message.matches("[A-Za-z]+:/[A-Za-z]+:/.*\\.[A-Za-z]+")) {
            return true;
        } else {
            return false;
        }

    }

    public Boolean checkLocation(String Message) {

        if (Message.matches("[0-9]*\\.[0-9]+--[0-9]*\\.[0-9]+")) {
            return true;
        } else {
            return false;
        }

    }

    public void sendImage(Message m, VBox message_box, ImageView imageView) {
        Connection connection;
        connection = Database.getInstance().getCon();
        String query2 = "INSERT INTO message(from_user,to_conv,message_text) VALUES(?,?,?)";
        PreparedStatement statement2;
        int id_message = -1;
        try {
            statement2 = connection.prepareStatement(query2, PreparedStatement.RETURN_GENERATED_KEYS);
            statement2.setInt(1, m.getFrom_user());

            statement2.setInt(2, m.getTo_conv().getId());
            statement2.setString(3, m.getText());
            int affectedRows = statement2.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating record failed, no rows affected.");
            }
            try ( ResultSet rs = statement2.getGeneratedKeys()) {
                if (rs.next()) {
                    id_message = rs.getInt(1);
                    m.setId_message(id_message);
                } else {
                    throw new SQLException("Creating record failed, no ID obtained.");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceMessage.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.msgtemplate(Integer.toString(m.getFrom_user()), m, message_box);
    }

    public void searchMessagesFromDB(String search, String clientID, VBox messages, Conversation conv) {
        messages.getChildren().clear();
        Connection connection;
        connection = Database.getInstance().getCon();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM message where to_conv = ? AND message_text LIKE ? ORDER BY date_time");
            preparedStatement.setString(1, Integer.toString(conv.getId()));
            preparedStatement.setString(2, "%" + search + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Message m = new Message(resultSet.getInt("id_message"), resultSet.getInt("from_user"), resultSet.getString("message_text"));
                this.msgtemplate(clientID, m, messages);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void msgtemplate(String clientID, Message m, VBox messages) {
        HBox messageContainer = new HBox();
        HBox.setHgrow(messageContainer, Priority.NEVER);
        messageContainer.setId(Integer.toString(m.getId_message()));
        if (clientID.equals(Integer.toString(m.getFrom_user()))) {
            if (this.checkImage(m.getText())) {
                Image image = new Image(m.getText());
                ImageView imageView = new ImageView(image);
                sc.myhboxdesign(messageContainer);
                messageContainer.getChildren().add(imageView);
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
            } else {
                if (this.checkLocation(m.getText())) {
                    Text clientMessage = new Text(m.getText());
                    sc.textdesign(clientMessage);
                    clientMessage.setOnMouseClicked(eventt -> {
                        String[] receivedData = m.getText().split("--");
                        String lat = receivedData[0];
                        String lng = receivedData[1];
                        String url = "https://www.google.com/maps/search/?api=1&query=" + lat + "," + lng;

                        Desktop desktop = Desktop.getDesktop();
                        try {
                            try {
                                desktop.browse(new URI(url));
                            } catch (URISyntaxException ex) {
                                Logger.getLogger(ServiceMessage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ServiceMessage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    sc.myhboxdesign(messageContainer);
                    messageContainer.getChildren().add(clientMessage);
                } else {
                    Text clientMessage = new Text(m.getText());
                    sc.textdesign(clientMessage);
                    sc.myhboxdesign(messageContainer);
                    messageContainer.getChildren().add(clientMessage);
                }
            }
        } else {
            if (this.checkImage(m.getText())) {
                Image image = new Image(m.getText());
                ImageView imageView = new ImageView(image);
                sc.hishboxdesign(messageContainer);
                messageContainer.getChildren().add(imageView);
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
            } else {
                if (this.checkLocation(m.getText())) {
                    Text senderMessage = new Text(prenom(Integer.toString(m.getFrom_user())) + " : " + m.getText());
                    sc.textdesign(senderMessage);
                    senderMessage.setOnMouseClicked(eventt -> {
                        String[] receivedData = m.getText().split("--");
                        String lat = receivedData[0];
                        String lng = receivedData[1];
                        String url = "https://www.google.com/maps/search/?api=1&query=" + lat + "," + lng;

                        Desktop desktop = Desktop.getDesktop();
                        try {
                            try {
                                desktop.browse(new URI(url));
                            } catch (URISyntaxException ex) {
                                Logger.getLogger(ServiceMessage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ServiceMessage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    sc.hishboxdesign(messageContainer);
                    messageContainer.getChildren().add(senderMessage);
                } else {
                    Text senderMessage = new Text(prenom(Integer.toString(m.getFrom_user())) + " : " + m.getText());
                    sc.textdesign(senderMessage);
                    sc.hishboxdesign(messageContainer);
                    messageContainer.getChildren().add(senderMessage);
                }
            }
        }
        messages.getChildren().add(messageContainer);
    }

    public List<Message> showmessage() {
        try {
            List<Message> messageList = new ArrayList<>();
            Connection connection;
            connection = Database.getInstance().getCon();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM message");

            while (rs.next()) {
                int id = rs.getInt("id_message");
                int sender = rs.getInt("from_user");
                int recipient = rs.getInt("to_conv");
                String text = rs.getString("message_text");
                java.sql.Timestamp datee = rs.getTimestamp("date_time");
                Conversation conv = new Conversation(recipient);
                Message message = new Message(id, sender, conv, text, datee);
                messageList.add(message);
            }
            return messageList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void sendmessage(Message m, VBox messages) {
        Connection connection;
        connection = Database.getInstance().getCon();
        String query2 = "INSERT INTO message(from_user,to_conv,message_text) VALUES(?,?,?)";
        PreparedStatement statement2;
        int id_message = -1;
        try {
            statement2 = connection.prepareStatement(query2, PreparedStatement.RETURN_GENERATED_KEYS);
            statement2.setInt(1, m.getFrom_user());

            statement2.setInt(2, m.getTo_conv().getId());
            statement2.setString(3, m.getText());
            int affectedRows = statement2.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating record failed, no rows affected.");
            }
            try ( ResultSet rs = statement2.getGeneratedKeys()) {
                if (rs.next()) {
                    id_message = rs.getInt(1);
                    m.setId_message(id_message);
                } else {
                    throw new SQLException("Creating record failed, no ID obtained.");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceMessage.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.msgtemplate(Integer.toString(m.getFrom_user()), m, messages);

    }

    public Message messagefromserver(String message, Conversation Current_conv) {
        Message m = null;
        String[] receivedData = message.split(";;");
        System.out.println("hello");
        String senderID = receivedData[1];
        String receiverID = receivedData[2];
        String text = receivedData[3];
        if (Current_conv.getId() == Integer.parseInt(receiverID)) {
            Connection connection;
            connection = Database.getInstance().getCon();
            String query = "SELECT * FROM message WHERE from_user = ? AND to_conv = ? AND message_text = ?";
            PreparedStatement statement;
            try {
                statement = connection.prepareStatement(query);
                statement.setString(1, senderID);
                statement.setString(2, receiverID);
                statement.setString(3, text);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Conversation c = new Conversation(resultSet.getInt(3));
                    m = new Message(resultSet.getInt(1), resultSet.getInt(2), c, resultSet.getString(4));
                }
                return m;
            } catch (SQLException ex) {
                return null;
            }
        }
        return null;
    }

    public ServerMessage Objectfromserver(Object msg, Conversation Current_conv) {
        if (msg instanceof ServerMessage) {
            ServerMessage nmsg = (ServerMessage) msg;
            if (Current_conv.getId() == Integer.parseInt(nmsg.getReceiverId())) {
                return nmsg;
            } else {
                return null;
            }
        }
        return null;
    }

    public String messageowner(String id) {
        Connection connection;
        connection = Database.getInstance().getCon();
        String from = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT from_user FROM message where id_message = ? ");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                from = resultSet.getString("from_user");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return from;

    }

    public void deletemessage(String id) {
        Connection connection;
        connection = Database.getInstance().getCon();
        String query2 = "DELETE FROM message WHERE id_message = ?";
        PreparedStatement statement2;
        try {
            statement2 = connection.prepareStatement(query2);
            statement2.setString(1, id);
            statement2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMessage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updatemessage(String id, String message) {
        Connection connection;
        connection = Database.getInstance().getCon();
        String query2 = "UPDATE message SET message_text = ? WHERE id_message = ?";
        PreparedStatement statement2;
        try {
            statement2 = connection.prepareStatement(query2);
            statement2.setString(2, id);
            statement2.setString(1, message + " (modifié)");
            statement2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMessage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Set<String> badwordslist() throws FileNotFoundException, IOException {
        Set<String> set = new HashSet<>();
        String fileName = "C:/Users/ousso/Documents/NetBeansProjects/Fancy_Trade_Messagerie/src/Services/badwords.txt"; // Replace with the name of your file

        try ( BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                set.add(line.trim());
            }
        }
        return set;
    }

    public boolean checkInput(TextField input, Label alertlabel) throws IOException {
        String inputt = input.getText().toLowerCase();
        boolean containsBadWord = badwordslist().stream().anyMatch(inputt::contains);
        if (containsBadWord || input.getText().length() > 25) {
            input.clear();
            alertlabel.setVisible(true);
            alertlabel.setText("Le message ne doit pas dépasser 25 caractères et ne doit pas contenir de mauvais mot.");
            alertlabel.setTextFill(Color.RED);
            Font font = Font.font("Verdana", FontWeight.BOLD, 16);
            alertlabel.setFont(font);
            alertlabel.setWrapText(true);

            return true;
        } else {
            return false;
        }

    }

}
