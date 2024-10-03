package Server;

import DB.Database;
import Services.ServiceMessage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chat_Server {

    private static Map<String, Socket> clients = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("Server started. Waiting for clients...");
        while (true) {
            Socket client = server.accept();
            DataInputStream dis = new DataInputStream(client.getInputStream());
            String clientID = dis.readUTF();
            clients.put(clientID, client);
            System.out.println("Client(" + clientID + ") connected: " + client.getInetAddress().getHostAddress());

            ClientHandler clientHandler = new ClientHandler(client, clientID);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }

    static class ClientHandler implements Runnable {

        private Socket client;
        private String clientID;
        private DataInputStream dis;
        private DataOutputStream dos;
      /*  private ObjectInputStream in;
        private ObjectOutputStream out;*/

        ClientHandler(Socket client, String clientID) throws IOException {
            this.client = client;
            this.clientID = clientID;
            this.dis = new DataInputStream(client.getInputStream());
            this.dos = new DataOutputStream(client.getOutputStream());
           /* this.in = new ObjectInputStream(client.getInputStream());
            this.out = new ObjectOutputStream(client.getOutputStream());*/
        }

        @Override
        public void run() {
            while (true) {
                try {
                    ServiceMessage testt = new ServiceMessage();
                    String message = dis.readUTF();
                    if (message != null) {
                        String[] receivedData = message.split(";;");
                        Connection connection;
                        connection = Database.getInstance().getCon();
                        String query = "SELECT prenom FROM user WHERE id_user = ?";
                        PreparedStatement statement;
                        try {
                            statement = connection.prepareStatement(query);
                            statement.setString(1, receivedData[1]);

                            ResultSet resultSet = statement.executeQuery();
                            String user_id = "not set";
                            while (resultSet.next()) {
                                user_id = resultSet.getString(1); //"zadaz";
                            };
                            String client_id = testt.GetReceiver(receivedData[1], receivedData[2]);
                            System.out.println(message);
                            sendMessageToClient(client_id, message);
                        } catch (SQLException ex) {
                            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } /*else if (msg != null) {
                            String client_id = testt.GetReceiver(msg.getSenderId(), msg.getReceiverId());
                            sendObjectToClient(client_id, msg);

                    }*/

                } catch (IOException e) {
                    System.out.println("Client disconnected");
                    break;
                }
            }
        }

        public void sendMessage(String message) {
            try {
                dos.writeUTF(message);
            } catch (IOException e) {
                System.out.println("Error sending message to " + clientID + ": " + e.getMessage());
                clients.remove(clientID);
            }
        }

        public void sendMessageToClient(String clientID, String message) {
            Socket socket = clients.get(clientID);
            if (socket != null) {
                try {
                    new ClientHandler(socket, clientID).sendMessage(message);
                    System.out.println("messenge sent to " + clientID);
                } catch (IOException ex) {
                    Logger.getLogger(Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Error: no such client with id " + clientID);
            }
        }
    }
}
/*                 public void sendObject(ServerMessage msg) {
            try {
                out.writeObject(msg);
            } catch (IOException e) {
                System.out.println("Error sending message to " + clientID + ": " + e.getMessage());
                clients.remove(clientID);
            }
        }
        public void sendObjectToClient(String clientID, ServerMessage msg) {
            Socket socket = clients.get(clientID);
            if (socket != null) {
                try {
                    new ClientHandler(socket, clientID).sendObject(msg);
                    System.out.println("messenge sent to " + clientID);
                } catch (IOException ex) {
                    Logger.getLogger(Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Error: no such client with id " + clientID);
            }
        } */
