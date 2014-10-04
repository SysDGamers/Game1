package sysD_game;

import javax.swing.JLabel;

public class BackGround extends JLabel{
	
	int x;
	int y;
	int pic_width = 20;
	int pic_height = 20;
	int delta_x = 10;
	int delta_y = 10;
	
	public void load() {

	}
	
	public void move() {
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
