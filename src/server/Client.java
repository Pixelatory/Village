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
    private JFrame frame;

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

            while((setVillageinfo(in.readLine())) != null) { // updating the village info here
                //System.out.println(getVillageinfo());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + ip);
            JFrame frame = new JFrame("Error");
            frame.setLayout(new FlowLayout());
            frame.add(new JLabel("I don't know the host: " + ip));
            JButton okayButton = new JButton("Okay");
            okayButton.addActionListener(event -> frame.dispose());
            frame.add(okayButton);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    ip);
            JFrame frame = new JFrame("Error");
            frame.setLayout(new FlowLayout());
            frame.add(new JLabel("Couldn't get a connection to " + ip));
            JButton okayButton = new JButton("Okay");
            okayButton.addActionListener(event -> frame.dispose());
            frame.add(okayButton);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
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
}
