package sysD_game;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Empty extends BackGround{
	public Empty(int startX, int startY) {
		x = startX;
		y = startY;
	}
	
	@Override
	public void load() {
		// Adding Images
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("src/empty.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setIcon(new ImageIcon(image));
		this.setPreferredSize(new Dimension(20, 20));
		this.setBounds(x, y, pic_width, pic_height);
	}
	

}
