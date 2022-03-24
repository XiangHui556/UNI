package Client.ServerComms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ClientToServerComms{
    // Client only wants to receive one message/data
    public String simpleCommand(String command) throws IOException {
        ClientToServer client = new ClientToServer();
        client.connectToServer();
        client.sendToServer(command);
        String message = client.getFromServer();
        client.disconnectFromServer();
        return message;
    }

    // Client wants to receive multiple lines of message/data
    public ArrayList<String> arrayReturnCommand(String command) throws IOException {
        ArrayList<String> data = new ArrayList<String>();
        ClientToServer client = new ClientToServer();
        client.connectToServer();
        client.sendToServer(command);
        String message = "";
        while(!Objects.equals(message, "EndOfTransmission")) {
            message = client.getFromServer();
            data.add(message);
        }
        data.remove(data.size() - 1);
        client.disconnectFromServer();
        return data;
    }
}
