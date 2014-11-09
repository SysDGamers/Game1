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
    Wrelease("released W", KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true)),
    A("A", KeyStroke.getKeyStroke(KeyEvent.VK_A, 0)),
    Arelease("released A", KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true)),
    S("S", KeyStroke.getKeyStroke(KeyEvent.VK_S, 0)),
    Srelease("released S", KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true)),
    D("D", KeyStroke.getKeyStroke(KeyEvent.VK_D, 0)),
    Drelease("released D", KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true)),
    Q("Q", KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0)),
    Qrelease("released Q", KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, true)),
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
