package sysD_game;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;

public abstract class PopUp extends JPanel{
	private static final int EDGE_WIDTH = 2;
	protected Rectangle outerRect;
	protected Rectangle innerRect;
	private boolean isVisible = false;
	protected final MouseManager mouseManager = MouseManager.getInstance();
	
	public PopUp(Rectangle rect){
		this.outerRect = rect;
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.setBounds(rect);
		this.setFocusable(true);
		innerRect = new Rectangle(
				EDGE_WIDTH,
				EDGE_WIDTH,
				rect.width - EDGE_WIDTH * 2,
				rect.height - EDGE_WIDTH *2);
		
		drawComponent();
	}

	public void update () {
		if (!isVisible) {
			this.setVisible(false);
			return;
		}
		this.setVisible(true);
	}
	
	protected abstract void drawComponent ();
	
	public void show () {
		isVisible = true;
		this.requestFocus(true);
	}
	
	public void hide () {
		isVisible = false;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
}
