package sysD_game;

import javax.swing.JPanel;

public class MainPanel extends JPanel{
	public Character chara = new Character();
	
	public MainPanel() {
		this.setOpaque(true);
	}
		
	public void drawCharacters() {
		chara.loadCharacter();
		this.add(chara);

	}
	
}
