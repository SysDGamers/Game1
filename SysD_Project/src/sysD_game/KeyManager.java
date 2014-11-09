package sysD_game;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JWindow;

final class KeyManager {
	private KeyManager() {};
	private boolean isFirst = true;
	private JWindow jWindow;
	
	// Avoid multi-instatiation
	private static class KeyManagerHolder {
		private static final KeyManager keyManager = new KeyManager();
	}
	public static KeyManager getIsntance() {
		return KeyManagerHolder.keyManager;
	}

	// When initiatiated, must be called once.
	// JWindow could be frame, panel ...etc
	public void putContainer(JWindow jWindow) {
		if (!isFirst)
			return;
		
		isFirst = false;
		this.jWindow = jWindow;
		init();
	}
	
	private void init() {
		ActionMap actionMap = jWindow.getRootPane().getActionMap();
		InputMap inputMap = jWindow.getRootPane().getInputMap(JComponent.
				WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		
	}
	
	private class KeyBinding extends AbstractAction {
		public KeyBinding(String text) {
			super(text);
			putValue(ACTION_COMMAND_KEY, text);
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
		}
	}
	
}
