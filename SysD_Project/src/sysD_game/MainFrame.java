package sysD_game;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class MainFrame implements KeyListener, Runnable{
	FrameInfo frameInfo = new FrameInfo();
	int FRAME_WIDTH = frameInfo.MainFrameWidth;
	int FRAME_HEIGHT = frameInfo.MainFrameHeight;
	MainPanel p1;
	BackGroundPanel bgp;
	Thread mainThread;
	// Flag for pressed keys
	boolean left = false;
	boolean right = false;
	boolean up = false;
	boolean down = false;
	
	public MainFrame() {
		JFrame frame = new JFrame();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Game Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bgp = new BackGroundPanel();
		bgp.drawObstacles();	
		
		p1 = new MainPanel();
		p1.drawCharacters();
		p1.setBounds(230, 230, 40, 40);
		//bgp.add(p1);
		
		Container content = frame.getContentPane();
		content.add(bgp);
		
		frame.addKeyListener(this);
		// Calls for the main Thread
		startMovin();
		
		// For Mac this must be stated after adding panels to the frame
		frame.setVisible(true);
	}
	

	private void startMovin() {
		mainThread = new Thread(this);
		mainThread.start();
	}
	
	// Main Thread
	public void run() {
		while (true) {
			this.bgp.moveObstacles();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_LEFT) {
			left = true;
		}		
		if (keyCode == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if (keyCode == KeyEvent.VK_UP) {
			up = true;
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			down = true;
		}
		
		if (left == true && up == true) {
			bgp.goLeftUp();
		} else if (left == true && down == true) {
			bgp.goLeftDown();
		} else if (right == true && up == true) {
			bgp.goRightUp();
		} else if (right == true && down == true) {
			bgp.goRightDown();
		} else if (left == true) {
			bgp.goLeft();
		} else if (right == true) {
			bgp.goRight();
		} else if (up == true) {
			bgp.goUp();
		} else if (down == true) {
			bgp.goDown();
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_LEFT) {
			left = false;
		}		
		if (keyCode == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if (keyCode == KeyEvent.VK_UP) {
			up = false;
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			down = false;
		}
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
}
