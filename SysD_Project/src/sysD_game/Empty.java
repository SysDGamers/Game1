package sysD_game;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Empty extends BackGround{
	public void Empty(int startX, int startY) {
		x = startX;
		y = startY;
	}
	
	public void loadObstacle() {
		// Adding Images
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("src/empty.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setIcon(new ImageIcon(image));
		this.setPreferredSize(new Dimension(100, 100));
		this.setBounds(x, y, pic_width, pic_height);
	}
}
