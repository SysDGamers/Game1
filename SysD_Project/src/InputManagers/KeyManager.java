package InputManagers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class KeyManager {
	private KeyManager() {};
	private boolean isFirst = true;
	private JFrame frame;
	
	// Avoid multi-instatiation
	private static class KeyManagerHolder {
		private static final KeyManager keyManager = new KeyManager();
	}
	public static KeyManager getIsntance() {
		return KeyManagerHolder.keyManager;
	}

	// When initiatiated, must be called once.
	// JWindow could be frame, panel ...etc
	public void putContainer(JFrame frame) {
		if (!isFirst)
			return;
		
		isFirst = false;
		this.frame = frame;
		init();
	}
	
	private void init() {
		ActionMap actionMap = frame.getRootPane().getActionMap();
		InputMap inputMap = frame.getRootPane().getInputMap(JComponent.
				WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		
		for (KeyList key : KeyList.values()) {
			actionMap.put(key.getText(), new KeyBinding(key.getText()));
			inputMap.put(key.getKeyStroke(), key.getText());
		}
		frame.getRootPane().setActionMap(actionMap);
		frame.getRootPane().setInputMap(JComponent.
				WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, inputMap);
		frame.setVisible(true);
	}
	
	private class KeyBinding extends AbstractAction {
		private KeyState keyState = KeyState.getInstance();
		public KeyBinding(String text) {
			super(text);
			putValue(ACTION_COMMAND_KEY, text);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			
			if (action == "W") {
				keyState.W = true;
			}
			if (action == "released W") {
				keyState.W = false;
			}
			if (action == "A") {
				keyState.A = true;
			}
			if (action == "released A") {
				keyState.A = false;
			}
			if (action == "D") {
				keyState.D = true;
			}
			if (action == "released D") {
				keyState.D = false;
			}
			if (action == "Q") {
				keyState.Q = true;
			}
			if (action == "released Q") {
				keyState.Q = false;
			}
			if (action == "F") {
				keyState.F = true;
			}
			if (action == "released F") {
				keyState.F = false;
			}
			if (action == "ENTER") {
				keyState.ENTER = true;
			}
			if (action == "released ENTER") {
				keyState.ENTER = false;
			}
			if (action == "O") {
				keyState.O = true;
			}
			if (action == "released O") {
				keyState.O = false;
			}
			if (action == "L") {
				keyState.L = true;
			}
			if (action == "released L") {
				keyState.L = false;
			}
			
			
			
		}
	}
	
}
