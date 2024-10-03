/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.json.JSONObject;

/**
 *
 * @author oussema
 */
public class ServiceChat {

    public String getLocation() {
        String url = "http://ip-api.com/json/?fields=lat,lon";
        float lat = 0, lon = 0;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            lat = json.getFloat("lat");
            lon = json.getFloat("lon");
            System.out.println("Latitude: " + lat);
            System.out.println("Longitude: " + lon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Float.toString(lat) + "--" + Float.toString(lon);
    }

    public void inputdesign(TextField... inputs) {
        for (TextField input : inputs) {
            input.setStyle("-fx-background-color: #d7b14a; -fx-text-inner-color: black; -fx-prompt-text-fill: black;");
        }
    }

    public void scrolldesign(ScrollPane... scrolls) {
        for (ScrollPane scroll : scrolls) {
            scroll.setStyle("-fx-background-color: transparent;-fx-border-color: #808080;-fx-border-width: 4;");
            scroll.skinProperty().addListener((obs, oldSkin, newSkin) -> {
                if (newSkin != null) {
                    scroll.lookup(".scroll-bar:vertical").setOpacity(0.5);
                    scroll.lookup(".scroll-bar:vertical").setStyle("-fx-background-color: black;");
                }
            });
            scroll.setFitToWidth(true);
        }
    }

    public void paindesign(Pane... panes) {
        for (Pane pain : panes) {
            pain.setStyle("-fx-background-color: black;");
        }
    }

    public void vboxdesign(VBox... vboxes) {
        for (VBox vbox : vboxes) {
            vbox.setStyle("-fx-background-color: black;");

        }
    }

    public void buttondesign(Button... buttons) {
        for (Button button : buttons) {
            button.setStyle("-fx-background-color: #d7b14a; -fx-text-fill: black;");

        }
    }

    public void textdesign(Text... texts) {
        for (Text text : texts) {
            text.setStyle("-fx-text-fill: black;");
            text.setFont(Font.font("Arial", 15));

        }
    }

    public void myhboxdesign(HBox... hboxes) {
        for (HBox hbox : hboxes) {
            hbox.setStyle("-fx-background-color: #d7b14a; -fx-background-radius: 0px;");
            hbox.setAlignment(Pos.CENTER_RIGHT);
            hbox.setPadding(new Insets(0, 20, 0, 0));
        }
    }

    public void hishboxdesign(HBox... hboxes) {
        for (HBox hbox : hboxes) {
            hbox.setStyle("-fx-background-color: #808080; -fx-background-radius: 0px;");
            hbox.setPadding(new Insets(0, 0, 0, 10));

        }
    }

}
