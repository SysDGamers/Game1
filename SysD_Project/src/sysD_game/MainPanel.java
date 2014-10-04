package sysD_game;

import java.awt.Color;

import javax.swing.JPanel;

public class MainPanel extends JPanel{
	private Thread thread;
	public Character chara = new Character();
	
	public MainPanel() {
		this.setOpaque(true);
	}
		
	public void drawCharacters() {
		chara.loadCharacter();
		this.add(chara);
	}
	
}
