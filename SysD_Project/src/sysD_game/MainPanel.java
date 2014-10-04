package sysD_game;

import java.awt.Color;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable{
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
	
	public void letsGetMovin() {
		chara.loadCharacter();
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		while (true) {
			this.chara.moveCharacter();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
