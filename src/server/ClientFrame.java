package server;

import javax.swing.*;
import java.awt.*;

public class ClientFrame {
    public ClientFrame() {
        JFrame frame = new JFrame("Village Game");
        frame.setLayout(new FlowLayout());
        JLabel title = new JLabel("Village Game");
        JTextField serverIPField = new JTextField("Server IP");
        JTextField usernameField = new JTextField("Username");
        JButton connectButton = new JButton("Connect");

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        serverIPField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        connectButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.add(title);
        frame.add(serverIPField);
        frame.add(usernameField);
        frame.add(connectButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        connectButton.addActionListener(e -> {
            Client c = new Client(serverIPField.getText(),usernameField.getText());
            c.start();
            frame.dispose();
            try {
                c.join();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            frame.pack();
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        ClientFrame cf = new ClientFrame();
    }
}
