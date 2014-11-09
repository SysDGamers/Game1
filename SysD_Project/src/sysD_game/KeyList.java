package sysD_game;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

// List of keys that could be recognized
public enum KeyList {
	ESCAPE("Escape", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0)),
    CTRLC("Control-C", KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK)),
    CTRLP("Control-P", KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK)),
    UP("Up", KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0)),
    DOWN("Down", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0)),
    LEFT("Left", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0)),
    RIGHT("Right", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0)),
    W("W", KeyStroke.getKeyStroke(KeyEvent.VK_W, 0)),
    A("A", KeyStroke.getKeyStroke(KeyEvent.VK_A, 0)),
    S("S", KeyStroke.getKeyStroke(KeyEvent.VK_S, 0)),
    D("D", KeyStroke.getKeyStroke(KeyEvent.VK_D, 0))
	;
	
	private String text;
	private KeyStroke keyStroke;
	
	KeyList(String text, KeyStroke keyStroke) {
		this.text = text;
		this.keyStroke = keyStroke;
	}
	
	public String getText() {
		return text;
	}
	
	public KeyStroke getKeyStroke() {
		return keyStroke;
	}
	
	@Override
	public String toString() {
		return text;
	}
}
