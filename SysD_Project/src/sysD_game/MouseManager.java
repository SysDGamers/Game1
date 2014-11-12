package sysD_game;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MouseManager implements MouseListener{

	public boolean mousepressed = false;
	public Point point;
	public JFrame frame;
	
	private MouseManager() {}
	private static class MouseManagerHolder {
		private static final MouseManager mouseManager = new MouseManager();
	}
	
	public static MouseManager getInstance() {
		return MouseManagerHolder.mouseManager;
	}
	
	public void registerFrame(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void mouseEntered(MouseEvent e){
	}

	@Override
	public void mouseExited(MouseEvent e){
	}

	@Override
	public void mousePressed(MouseEvent e){
		mousepressed = true;
		point = e.getLocationOnScreen();
		point.x = point.x - frame.getLocationOnScreen().x;
		point.y = point.y - frame.getLocationOnScreen().y;
	}

	@Override
	public void mouseReleased(MouseEvent e){
		mousepressed = false;
	}

	@Override
	public void mouseClicked(MouseEvent e){
	}
	


}
