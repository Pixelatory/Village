package server;

import client.model.village.Village;
import client.utility.GameState;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

public class Worker extends Thread {
    private final Socket clientSocket;
    private final Server server;
    private String username;
    private boolean end = false;

    public Worker(Socket clientSocket, Server server) {
        Server.connectionLabel.setText("Connections: " + Server.connections.size());
        this.clientSocket = clientSocket;
        this.server = server;
    }

    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject loginInfo = new JSONObject(in.readLine());
            username = loginInfo.get("username").toString();

            for(String s : Server.connections) { // checking if this username is already connected
                if (s.equals(username)) {
                    out.println("Can't connect you to server, this username is already logged in!");
                    end = true;
                }
            }

            if(!end) {
                Server.connections.add(loginInfo.get("username").toString()); // updating server GUI
                Server.connectionLabel.setText("Connections: " + Server.connections.size());
                out.println("Connected to server!");
                File file = new File(username + ".json");
                if(file.exists()) {
                    String gameData = new String(Files.readAllBytes(file.toPath()));
                    JSONObject obj = new JSONObject(gameData);
                    out.println(obj.toString());
                } else {
                    Village village = new Village();
                    GameState.save(village,file.getPath());
                    String gameData = GameState.load(new String(Files.readAllBytes(file.toPath()))).toString();
                    out.print(gameData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String message;

        try {
            while (server.isRunning() && clientSocket.isConnected() && !end && (message = in.readLine()) != null) {
                GameState.save(message, username + ".json");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!end) {
            Server.connections.remove(username);
            Server.connectionLabel.setText("Connections: " + Server.connections.size());
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
