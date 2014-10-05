package sysD_game;

import java.awt.Dimension;

import javax.swing.JPanel;

public class MainPanel extends JPanel{
	public Character chara = new Character();
	
	public MainPanel() {
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(40, 40));
	}
		
	public void drawCharacters() {
		chara.loadCharacter();
		this.add(chara);

	}
	
}
