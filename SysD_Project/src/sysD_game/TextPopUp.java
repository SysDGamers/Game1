package sysD_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPopUp extends JPanel{
	
	private static final int EDGE_WIDTH = 2;
	private Rectangle rect;
	private Rectangle innerRect;
	private boolean isVisible = false;
	private JTextArea textArea;
	private String str;
	final MouseManager mouseManager = MouseManager.getInstance();
	
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
		textArea.setVisible(true);
	}
	
	private void drawText () {
		textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.BOLD, 16));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setText(" Clash \n of \n Clans \n Rocks \n Stitch \n is \n sooooooooooooooooo \n cute! \n");
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		textArea.setBounds(innerRect);
		textArea.addMouseListener(mouseManager);
		this.add(textArea);
	}

	public void changeText (String str) {
		textArea.setText(str);
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
