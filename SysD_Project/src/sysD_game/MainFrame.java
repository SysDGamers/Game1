package sysD_game;

import java.awt.Container;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	public static MainFrame mainFrame = new MainFrame();
    public MainFrame() {
        // タイトルを設定
        setTitle("SYS-D PROJECT");
        // サイズ変更不可
        setResizable(false);

        // メインパネルを作成してフレームに追加
        MainPanel panel = new MainPanel();
        Container contentPane = getContentPane();
        contentPane.add(panel);
        
        // Inorder to receive position of the frame on screen
        MouseManager mouseManager = MouseManager.getInstance();
        mouseManager.registerFrame(this);
        
        // パネルサイズに合わせてフレームサイズを自動設定
        pack();
    }
}
