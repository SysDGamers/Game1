package sysD_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JTextArea;

public class Text {

	private static final int EDGE_WIDTH = 2;
	private Rectangle rect;
	private Rectangle innerRect;
	private boolean isVisible = true;

	public Text(Rectangle rect){
		this.rect = rect;

		innerRect = new Rectangle(
				rect.x + EDGE_WIDTH,
				rect.y + EDGE_WIDTH,
				rect.width - EDGE_WIDTH * 2,
				rect.height - EDGE_WIDTH *2);
	}

	public void draw(Graphics g) {
		if (isVisible == false) return;

		g.setColor(Color.WHITE);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);

		g.setColor(Color.BLACK);
		g.fillRect(innerRect.x, innerRect.y, innerRect.width, innerRect.height);
		

		
		Font f=new Font("TimesRoman",Font.ITALIC,20);
		g.setFont(f);
		g.setColor(Color.white);
		g.drawString("アイドルマスター", innerRect.x + 20, innerRect.y + 30);
	}

	public void show(){
		isVisible = true;
	}
	public void hide(){
		isVisible = false;
	}

	public boolean isVisible(){
		return isVisible;
	}
}
