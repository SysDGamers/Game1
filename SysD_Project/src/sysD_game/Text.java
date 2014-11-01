package sysD_game;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Text {
	public Text(){	
	}
	public void draw(Graphics g) {
		Font f=new Font("TimesRoman",Font.ITALIC,30);
		g.setFont(f);
		g.setColor(Color.red);
		g.drawString("Hello Java2D", 0, 100);
	}
}
