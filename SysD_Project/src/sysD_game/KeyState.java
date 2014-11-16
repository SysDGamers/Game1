package sysD_game;

final class KeyState {
	public boolean W;
	public boolean A;
	public boolean S;
	public boolean D;
	public boolean ESC;
	public boolean Q;
	public boolean F;
	public boolean ENTER;
	
	private KeyState() {}
	private static class KeyStateHolder {
		private static final KeyState keyState = new KeyState();
	}
	
	public static KeyState getInstance() {
		return KeyStateHolder.keyState;
	}
}
