package Server;

import Server.ClientComms.ServerCommands;
import Server.DataManipulation.ServerExchangeRates;

import java.net.*;
import java.io.*;

public class ServerMain extends Thread {
    private final ServerSocket serverSocket;
    static final private int PORT = 25565;

    public ServerMain(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server Socket Started");
        //serverSocket.setSoTimeout(10000); //No Timeout
    }

    public void run() {
        ServerCommands commands = new ServerCommands();
        while(true){
            try {
                Socket server = serverSocket.accept();
                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                String command = in.readUTF();
                System.out.println(command);
                commands.input(command, out);

                server.close();

            } catch (SocketException s) {
                System.out.println("A Socket Disconnected");
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String [] args) {
        //int port = Integer.parseInt(args[0]);
        try {
            ServerExchangeRates init = new ServerExchangeRates();
            init.initGetCurrencies();
            Thread t = new ServerMain(PORT);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
