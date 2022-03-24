package Client.ServerComms;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class ClientToServer {
    static final private String SERVERIP = "127.0.0.1"; //Change to the hosting server's Public IP
    static final private int PORT = 25565;
    Socket client;
    // Used to connect to the server
    public void connectToServer() throws IOException{
        try {
            //System.out.println("Connecting to " + SERVERIP + " on port " + PORT);
            client = new Socket(SERVERIP, PORT); // Try to connect to the server
            //System.out.println("Just connected to " + client.getRemoteSocketAddress());
        } catch (IOException e) {
            System.out.println("Exception caught: " + e.getMessage() + " (Server might not be active)");
        }
    }

    // Used to send command/data to the server
    public void sendToServer(String message) throws NullPointerException {
        try {
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF(message); // Sends the message to the server via output stream
        }
        catch(IOException | NullPointerException e){
            //e.printStackTrace();
        }
    }

    // Used to get data from the server
    public String getFromServer() throws NullPointerException{
        String message = null;
        try {
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            //System.out.println("Server says " + in.readUTF());
            message = in.readUTF();
        }
        catch(IOException | NullPointerException e){
            //e.printStackTrace();
        }
        return String.valueOf(message);
    }

    // Disconnects from the server
    public void disconnectFromServer() throws NullPointerException {
        try {
            client.close();
            //System.out.println("Disconnecting From " + client.getRemoteSocketAddress());
        }
        catch(IOException | NullPointerException e){
            //e.printStackTrace();
        }
    }
}
