package sysD_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

/*
 * Created on 2005/06/06
 *
 */

/**
 * @author mori
 *
 */
public class MainPanel extends JPanel implements Runnable, KeyListener {
	// パネルサイズ
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	// マップ
	private Map map;

	//ウインドウ
	private Inventory inventory;
	private static Rectangle WIND_RECT = new Rectangle(100, 450, 600,100);
	// テキスト
	private Text text;
	// プレイヤー
	private Character player;

	// キーの状態（押されているか、押されてないか）
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean upPressed;
	private boolean escape;
	// ゲームループ用スレッド
	private Thread gameLoop;

	public MainPanel() {
		// パネルの推奨サイズを設定、pack()するときに必要
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		// パネルがキー入力を受け付けるようにする
		setFocusable(true);

		// マップを作成
		map = new Map("map01.dat");

		// プレイヤーを作成
		player = new Character(192, 32, map);

		// キーイベントリスナーを登録
		addKeyListener(this);
		inventory = new Inventory(WIND_RECT);

		text = new Text(WIND_RECT);		//テキストはいずれキャラに埋めるかも
		// ゲームループ開始
		gameLoop = new Thread(this);
		gameLoop.start();
	}

	/**
	 * ゲームループ
	 */
	public void run() {
		while (true) {
			if(escape){
				inventory.show();
			} else if (!escape){
				inventory.hide();
			}

			if (leftPressed) {
				// 左キーが押されていれば左向きに加速
				player.accelerateLeft();
			} else if (rightPressed) {
				// 右キーが押されていれば右向きに加速
				player.accelerateRight();
			} else {
				// 何も押されてないときは停止
				player.stop();
			}

			if (upPressed) {
				// ジャンプする
				player.jump();
			}
			//


			// プレイヤーの状態を更新
			player.update();

			// 再描画
			repaint();

			// 休止
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 描画処理
	 *
	 * @param 描画オブジェクト
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 背景を黒で塗りつぶす
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		// X方向のオフセットを計算
		int offsetX = MainPanel.WIDTH / 2 - (int)player.getX();
		// マップの端ではスクロールしないようにする
		offsetX = Math.min(offsetX, 0);
		offsetX = Math.max(offsetX, MainPanel.WIDTH - map.getWidth());

		// Y方向のオフセットを計算
		int offsetY = MainPanel.HEIGHT / 2 - (int)player.getY();
		// マップの端ではスクロールしないようにする
		offsetY = Math.min(offsetY, 0);
		offsetY = Math.max(offsetY, MainPanel.HEIGHT - map.getHeight());

		// マップを描画
		map.draw(g, offsetX, offsetY);

		// プレイヤーを描画
		player.draw(g, offsetX, offsetY);

		inventory.draw(g);
		text.draw(g);
		
	}

	/**
	 * キーが押されたらキーの状態を「押された」に変える
	 *
	 * @param e キーイベント
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (key == KeyEvent.VK_I){
			if(escape){
			escape = false;
			}else if(!escape){
				escape = true;
			}
		}
		if (key == KeyEvent.VK_ESCAPE){
			escape = false;
		}

	}

	/**
	 * キーが離されたらキーの状態を「離された」に変える
	 *
	 * @param e キーイベント
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			upPressed = false;
		}

	}

	public void keyTyped(KeyEvent e) {
	}
}