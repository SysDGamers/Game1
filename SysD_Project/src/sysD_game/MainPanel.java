package sysD_game;

import java.awt.Color;

import javax.swing.JPanel;

public class MainPanel extends JPanel{
	private Thread thread;
	public Character chara = new Character();
	
	public MainPanel() {
		this.setBackground(Color.black);
		this.setOpaque(true);
	}
		
	public void drawCharacter() {
		chara.loadCharacter();
		this.add(chara);
	}
	
}
