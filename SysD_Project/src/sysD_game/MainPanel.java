package sysD_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

//import createMap.Createmap3;

public class MainPanel extends JPanel implements Runnable{
	// パネルサイズ
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	// アイテム最大表示数
	public static final int ITEM_MAX = 30;
	// アクション最大表示数
	public static final int ACTION_MAX = 30;
	// 立ち絵最大表示数
	public static final int STAND_MAX = 4;
	// マップ
	private Map map;
	// マップのオフセット
	public static int offsetX;
	public static int offsetY;
	//ウインドウ
	private Inventory inventory;
	static Rectangle WIND_RECT = new Rectangle(100, 450, 600,100);
	// テキスト
	private TextPopUp textpop;
	// アイコン
	private Icon icon;
	// プレイヤー
	private Player player;
	private Enemy enemy;
	private Enemy enemy2;
	private Enemy enemy3;
	// オブジェクト
	private Item[] item;
	private int item_no = 0;
	public int item_count = 0;
	public int item_draw_count = 0;
	// アクション
	private Action[] action;
	private int action_no = 0;
	public int action_count = 0;
	public int action_draw_count = 0;
	// キーの状態（押されているか、押されてないか）
	final KeyState keyState = KeyState.getInstance();
	// テキスト表示の状態
	private boolean quote = false;
	// マウスの状態
	public boolean mousepressed;
	final MouseManager mouseManager = MouseManager.getInstance();
	// 座標をまとめる
	public Point point;
	// ゲームループ用スレッド
	private Thread gameLoop;

	public MainPanel() {
		// パネルの推奨サイズを設定、pack()するときに必要
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		// パネルがキー入力を受け付けるようにする
		setFocusable(true);
		setLayout(null);

		//Createmap3 createmap = new Createmap3();
		//createmap.createMap();
		
		// マップを作成
		map = new Map("test.txt");
		// プレイヤーを作成
		player = new Player(192, 32, map);
		// 敵を作成
		enemy = new Enemy(400, 32, map, "char_02", Enemy.SABER);
		enemy2 = new Enemy(140, 32, map, "char_06", Enemy.LIN);
		enemy3 = new Enemy(500, 32, map, "angela", Enemy.ANGELA);
		// アイテム作成（準備）
		item = new Item[ITEM_MAX];
		// アクション作成（準備）
		action = new Action[ACTION_MAX];
		// アイコン作成
		icon = new Icon();

		// キーイベントリスナーを登録
		addMouseListener(mouseManager);
		addMouseMotionListener(mouseManager);
		// インベントリ作成
		inventory = new Inventory(WIND_RECT);
		textpop = new TextPopUp(WIND_RECT);
		this.add(textpop);

		// ゲームループ開始
		gameLoop = new Thread(this);
		gameLoop.start();	// run()内が実行される
	}

	/**
	 * ゲームループ
	 */
	public void run() {
		while (true) {
			// 各キーアクションの設定
			keyAction(player, inventory);
			// ブロック掘る+アイテム生成
			if (mouseManager.isPressed() == true){
				genItem(map, item);
			}
			// プレイヤーの状態を更新
			enemy.update();
			enemy2.update();
			enemy.getCollision(player);	// プレイヤーとの当たり判定
			enemy2.getCollision(player);
			enemy3.update();
			enemy3.getCollision(player);
			if(item_draw_count != 0){	// 何個アイテムあるか
				for(int i = 0; i < item_draw_count; i++){
					if(item[i].item_alive == 1){	// そのアイテムは取得済みでないか
						item[i].update();
						if(item[i].getCollision(player)){	// プレイヤーとの当たり判定
							player.getItem(item[i]);	// プレイヤーによるアイテム取得
						}
					}
				}
			}
			if(action_draw_count != 0){	// 何個アクションあるか
				for(int i = 0; i < action_draw_count; i++){
					if(action[i].action_alive == 1){
						action[i].update();
					}
				}
			}
			player.update();
			icon.update(player);
			
			// 再描画
			repaint();	// paintComponent()内が実行される

			// 休止（描画スピードを一定に）
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
	public void paintComponent(Graphics g) {	// 画面への描き込み
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
		enemy3.draw(g, offsetX, offsetY);
		if(item_draw_count != 0){
			for(int i = 0; i < item_draw_count; i++){
				if(item[i].item_alive == 1){
					item[i].draw(g, offsetX, offsetY);
				}
			}
		}
		if(action_draw_count != 0){
			for(int i = 0; i < action_draw_count; i++){
				if(action[i].action_alive == 1){
					action[i].draw(g, offsetX, offsetY);
				}
			}
		}
		if (enemy.talk == 1){
			enemy.draw_stand(g, offsetX, offsetY);
			enemy.draw_text(g);
		}
		if (enemy2.talk == 1){
			enemy2.draw_stand(g, offsetX, offsetY);
			enemy2.draw_text(g);
		}
		if (enemy3.talk == 1){
			enemy3.draw_stand(g, offsetX, offsetY);
			enemy3.draw_text(g);
		}
		inventory.draw(g);
		icon.draw(g, offsetX, offsetY, player);
	}

	public void genItem(Map map, Item[] item){
		double buf_x, buf_y;
		int block_no;
		buf_x = mouseManager.point.getX();
		buf_y = mouseManager.point.getY();
		int p_x = (int)player.getX();
		int p_y = (int)player.getY();
		if ((p_x + offsetX - buf_x)*(p_x + offsetX - buf_x) + (p_y + offsetY - buf_y)*(p_y + offsetY - buf_y) < 10000){
			int buf_bx = Map.pixelsToTiles(buf_x);
			int buf_by = Map.pixelsToTiles(buf_y);
			block_no = player.digObject(buf_x, buf_y, map);
			if (block_no > 0){
		        // ランダム
				double d = Math.random();
				if(d<0.5){
					item_no = Item.NAN_J_MIN;
				}else if(d<1.0){
					item_no = Item.BALL;
				}
				item[item_count] = new Item(buf_bx * Map.TILE_SIZE - offsetX, buf_by * Map.TILE_SIZE - offsetY, map, item_no);
				item_count++;
				if (item_draw_count < ITEM_MAX){
					item_draw_count++;
				}
			}
			if (item_count >= ITEM_MAX){
				item_count = 0;
			}
		}

	}
	
	public void keyAction(Player player, Inventory inventory){
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
			if (keyState.ENTER) {
				textpop.getNextText();
			}
		} else {
			textpop.hide();
			this.setFocusable(true);
			this.requestFocus(true);
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
		
		if(keyState.Q){
			action[action_count] = new Action(player.x, player.y, map, action_no);
			action_count++;
			if (action_draw_count < ACTION_MAX){
				action_draw_count++;
			}
			if (action_count >= ACTION_MAX){
				action_count = 0;
			}
		}
	}
}
