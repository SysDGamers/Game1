package sysD_game;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Obstacle extends JLabel{
	
	int x = 10;
	int y = 10;
	int pic_width = 10;
	int pic_height = 10;
	int delta_x = 10;
	int delta_y = 10;
	
	public void loadObstacle() {
		// Adding Images
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("src/obs.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setIcon(new ImageIcon(image));
		this.setPreferredSize(new Dimension(100, 100));
		this.setBounds(x, y, pic_width, pic_height);
	}
	
	public void moveObstacle() {
		this.setBounds(x, y, pic_width, pic_height);
	}
	
	public void goLeft() {
		x += delta_x;
	}
	
	public void goRight() {
		x -= delta_x;
	}
	
	public void goUp() {
		y += delta_y;
	}
	
	public void goDown() {
		y -= delta_y;
	}
	
	public void goLeftUp() {
		x += delta_x;
		y += delta_y;
	}
	
	public void goLeftDown() {
		x += delta_x;
		y -= delta_y;
	}
	
	public void goRightUp() {
		x -= delta_x;
		y += delta_y;
	}
	
	public void goRightDown() {
		x -= delta_x;
		y -= delta_y;
	}
}
