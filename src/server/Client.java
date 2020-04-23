package server;

import client.engine.GameContainer;
import client.game.GameManager;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {

    private String ip;
    private int portNumber = 12345;
    private String username;
    private String villageinfo = null;
    private PrintWriter p;

    public Client(String ip, String username) {
        this.ip = ip;
        this.username = username;
    }

    public void run() {
        try (
                Socket echoSocket = new Socket(ip, portNumber);
                PrintWriter p = new PrintWriter(new OutputStreamWriter(echoSocket.getOutputStream()),true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(echoSocket.getInputStream()));
        ) {
            this.p = p;
            JSONObject login = new JSONObject();
            login.put("username",username);
            p.println(login.toString()); // sending the username to the server for processing

            System.out.println(in.readLine());

            class ClientThread extends Thread {
                private Client client;
                public ClientThread(Client client) {
                    this.client = client;
                }

                public void run() {
                    GameManager game = new GameManager(client);
                    GameContainer gc = new GameContainer(game);
                    gc.start();
                }
            }
            Thread ok = new ClientThread(this);
            ok.start(); // starts the game client

            while((setVillageinfo(in.readLine())) != null) {
                //System.out.println(getVillageinfo());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + ip);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    ip);
            System.exit(1);
        }
    }

    public synchronized void sendMessageToServer(String str) {
        p.println(str);
    }

    public synchronized String getVillageinfo() {
        return villageinfo;
    }

    public synchronized String setVillageinfo(String villageinfo) {
        this.villageinfo = villageinfo;
        return getVillageinfo();
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Village Game");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Village Game");
        JTextField serverIP = new JTextField("Server IP");
        JTextField username = new JTextField("Username");
        JButton connectButton = new JButton("Connect");

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        serverIP.setAlignmentX(Component.CENTER_ALIGNMENT);
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        connectButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(serverIP);
        panel.add(username);
        panel.add(connectButton);
        frame.add(panel);
        frame.setSize(400,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        connectButton.addActionListener(e -> {
            Client c = new Client(serverIP.getText(), username.getText());
            c.start();
        });
    }
}
