package sysD_game;

import javax.swing.JFrame;

import InputManagers.KeyManager;

public class Main extends JFrame {
    public static void main(String[] args) {
    	
    	MainFrame mainFrame = new MainFrame();
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame.setVisible(true);

    	KeyManager keyManager = KeyManager.getIsntance();
    	keyManager.putContainer(mainFrame);
    }
}
