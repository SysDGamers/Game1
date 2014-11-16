package sysD_game;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

import InputManagers.MouseManager;

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
        
        Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
        cursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("src/sysD_game/image/turuhashi.gif"), new Point(0,0), "mouseCursor");
        setCursor(cursor);
        
        // パネルサイズに合わせてフレームサイズを自動設定
        pack();
    }
}
