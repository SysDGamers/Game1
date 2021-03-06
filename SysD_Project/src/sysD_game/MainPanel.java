package sysD_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable, MouseListener{
	// パネルサイズ
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	// アイテム最大表示数
	public static final int ITEM_MAX = 30;

	// マップ
	private Map map;
	public static int offsetX;
	public static int offsetY;
	//ウインドウ
	private Inventory inventory;
	private static Rectangle WIND_RECT = new Rectangle(100, 450, 600,100);
	// テキスト
	private TextPopUp textpop;
	private Text text;
	// プレイヤー
	private Player player;
	private Enemy enemy;
	private Enemy enemy2;
	// オブジェクト
	private Item[] item;
	public int item_count = 0;
	public int item_draw_count = 0;
	// キーの状態（押されているか、押されてないか）
	final KeyState keyState = KeyState.getInstance();
	// テキスト表示の状態
	private boolean quote = false;
	// マウスの状態
	public boolean mousepressed;
	public Point point;
	// ゲームループ用スレッド
	private Thread gameLoop;

	public MainPanel() {
		// パネルの推奨サイズを設定、pack()するときに必要
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		// パネルがキー入力を受け付けるようにする
		setFocusable(true);
		
		setLayout(null);

		// マップを作成
		map = new Map("map01.dat");

		// プレイヤーを作成
		player = new Player(192, 32, map);
		enemy = new Enemy(400, 32, map, "char_02");
		enemy2 = new Enemy(140, 32, map, "char_03");
		item = new Item[ITEM_MAX];
		// キーイベントリスナーを登録
		addMouseListener(this);
		inventory = new Inventory(WIND_RECT);
		textpop = new TextPopUp(WIND_RECT);
		this.add(textpop);
		
		addMouseListener((MouseListener) this);
		
		// ゲームループ開始
		gameLoop = new Thread(this);
		gameLoop.start();
	}

	/**
	 * ゲームループ
	 */
	public void run() {
		while (true) {
			if(keyState.ESC){
				inventory.show();
			} else if (!keyState.ESC){
				inventory.hide();
			}
			if (keyState.Q) {
				if (quote)
					quote = false;
				else
					quote = true;
			}
			if (quote) {
				textpop.show();
			} else {
				textpop.hide();
			}

			if (keyState.A) {
				// 左キーが押されていれば左向きに加速
				player.accelerateLeft();
			} else if (keyState.D) {
				// 右キーが押されていれば右向きに加速
				player.accelerateRight();
			} else {
				// 何も押されてないときは停止
				player.stop();
			}

			if (keyState.W) {
				// ジャンプする
				player.jump();
			}
			
			if (mousepressed == true){
				double buf_x, buf_y;
				int block_no;
				buf_x = point.x;
				buf_y = point.y;
				block_no = player.digObject(buf_x, buf_y, map);
				if (block_no > 0){
					item[item_count] = new Item(buf_x - offsetX, buf_y - offsetY, map, block_no);
					item_count++;
					if (item_draw_count < ITEM_MAX){
						item_draw_count++;
					}
				}
				if (item_count >= ITEM_MAX){
					item_count = 0;
				}
				mousepressed = false;
			}
			//


			// プレイヤーの状態を更新
			player.update();
			enemy.update(player);
			if(item_count != 0){
				for(int i = 0; i < item_count; i++){
					if(item[i].item_alive == 1){
						item[i].update();
					}
				}
			}
			enemy2.update(player);
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
		offsetX = MainPanel.WIDTH / 2 - (int)player.getX();
		// マップの端ではスクロールしないようにする
		offsetX = Math.min(offsetX, 0);
		offsetX = Math.max(offsetX, MainPanel.WIDTH - map.getWidth());

		// Y方向のオフセットを計算
		offsetY = MainPanel.HEIGHT / 2 - (int)player.getY();
		// マップの端ではスクロールしないようにする
		offsetY = Math.min(offsetY, 0);
		offsetY = Math.max(offsetY, MainPanel.HEIGHT - map.getHeight());

		// マップを描画
		map.draw(g, offsetX, offsetY);

		// プレイヤーを描画
		player.draw(g, offsetX, offsetY);
		enemy.draw(g, offsetX, offsetY);
		enemy2.draw(g, offsetX, offsetY);
		if(item_draw_count != 0){
			for(int i = 0; i < item_draw_count; i++){
				if(item[i].item_alive == 1){
					item[i].draw(g, offsetX, offsetY);
				}
			}
		}
		if (enemy.talk == 1){
			text.draw(g);
		}
		inventory.draw(g);
	}
	
	

	
	public void mouseEntered(MouseEvent e){
	}

	public void mouseExited(MouseEvent e){
	}

	public void mousePressed(MouseEvent e){
		mousepressed = true;
		point = e.getPoint();
	}

	public void mouseReleased(MouseEvent e){
		mousepressed = false;
	}

	public void mouseClicked(MouseEvent e){
	}
}
