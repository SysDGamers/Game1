package sysD_game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextPopUp extends JPanel{
	
	private static final int EDGE_WIDTH = 2;
	private Rectangle rect;
	private Rectangle innerRect;
	private boolean isVisible = false;
	private JTextArea text;
	
	public TextPopUp(Rectangle rect){
		this.rect = rect;
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.setBounds(rect);
		
		innerRect = new Rectangle(
				EDGE_WIDTH,
				EDGE_WIDTH,
				rect.width - EDGE_WIDTH * 2,
				rect.height - EDGE_WIDTH *2);
		
		this.drawText();
	}

	public void paintComponent () {
		if (!isVisible) {
			this.setVisible(false);
			return;
		}
		this.setVisible(true);
		text.setVisible(true);
	}
	
	private void drawText () {
		text = new JTextArea();
		text.setFont(new Font("Arial", Font.BOLD, 16));
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setText("THE IDOLM@STER");
		text.setForeground(Color.WHITE);
		text.setBackground(Color.BLACK);
		text.setBounds(innerRect);
		this.add(text);
	}

	public void changeText (String str) {
		text.setText(str);
	}
	
	public void show () {
		isVisible = true;
	}
	
	public void hide () {
		isVisible = false;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
}
