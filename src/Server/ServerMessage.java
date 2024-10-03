package Server;

import java.io.Serializable;
import javafx.scene.layout.HBox;

public class ServerMessage implements Serializable {

    private HBox hbox;
    private String senderId;
    private String receiverId;

    public ServerMessage(HBox hbox, String senderId, String receiverId) {
        this.hbox = hbox;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public HBox getHbox() {
        return hbox;
    }

    public void setHbox(HBox hbox) {
        this.hbox = hbox;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

}
