package server;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {

    private static Server s = null;

    protected static ArrayList<String> connections = new ArrayList<>();
    private int port;
    private ServerSocket serverSocket = null;
    private boolean isRunning = true;
    protected static JLabel connectionLabel;
    private ExecutorService threadPool = Executors.newFixedThreadPool(3);

    public Server(int port) {
        this.port = port;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Can't create server!");
        }

        while(isRunning()) {
            Socket clientSocket = null;

            try {
                clientSocket = serverSocket.accept();
                clientSocket.connect(new InetSocketAddress(serverSocket.getInetAddress(),serverSocket.getLocalPort()),5000);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Client can't connect!");
            }
            if(clientSocket != null) {
                threadPool.execute(new Worker(clientSocket,this));
            }
        }
        threadPool.shutdown();
        System.out.println("Server stopped");
    }

    public synchronized boolean isRunning() {
        return isRunning;
    }

    public synchronized void end() {
        this.isRunning = false;
        try {
            serverSocket.close();
            serverSocket = null;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Server can't be closed!");
        }
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Village Server");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Village Server");
        JLabel ip = new JLabel("IP: " + InetAddress.getLocalHost().toString());
        JLabel port = new JLabel("Port: 12345");
        Server.connectionLabel = new JLabel("Connections: " + Server.connections);
        JButton startButton = new JButton("Start");

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        ip.setAlignmentX(Component.CENTER_ALIGNMENT);
        port.setAlignmentX(Component.CENTER_ALIGNMENT);
        Server.connectionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(ip);
        panel.add(port);
        panel.add(Server.connectionLabel);
        panel.add(startButton);
        frame.add(panel);
        frame.setSize(400,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        startButton.addActionListener(e -> {
            if(startButton.getText().equals("Start")) {
                Server.s = new Server(12345);
                Server.s.start();
                startButton.setText("Stop");
                System.out.println("Starting server");
            } else {
                Server.s.end();
                try {
                    Server.s.join();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                Server.s = null;
                startButton.setText("Start");
                System.out.println("Stopping server");
            }
        });
    }
}
