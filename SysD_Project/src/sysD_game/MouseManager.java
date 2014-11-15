package sysD_game;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class MouseManager implements MouseListener, MouseMotionListener{

	private boolean mousepressed = false;
	public Point point;
	private JFrame frame;
	private boolean first = true;
	
	private MouseManager() {}
	private static class MouseManagerHolder {
		private static final MouseManager mouseManager = new MouseManager();
	}
	
	public static MouseManager getInstance() {
		return MouseManagerHolder.mouseManager;
	}
	
	public void registerFrame(JFrame frame) {
		if (first == true) {
			this.frame = frame;
			first = false;
		}
	}
	
	public boolean isPressed() {
		return this.mousepressed;
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

	@Override
	public void mouseDragged(MouseEvent e) {
		point = e.getLocationOnScreen();
		point.x = point.x - frame.getLocationOnScreen().x;
		point.y = point.y - frame.getLocationOnScreen().y;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
	


}
