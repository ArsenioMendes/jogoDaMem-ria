package Application;

import Entities.layout.Menu;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        
        

        SwingUtilities.invokeLater(() -> {
            Menu mainFrame = new Menu();
            mainFrame.setVisible(true);
        });
    }
}