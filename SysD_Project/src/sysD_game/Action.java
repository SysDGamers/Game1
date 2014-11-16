package sysD_game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class Action extends Character{
    // スピード
    private static final int SPEED = 6;
    // ジャンプ力
    private static final int JUMP_SPEED = 12;
    // 方向
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    // 位置
    public double x;
    public double y;
    // 速度
    private double vx;
    private double vy;
    // 着地しているか
    private boolean onGround;
    // 向いている方向
    private int dir;
    // アニメーション用カウンタ
    private int count;
    // プレイヤー画像
    private Image image;
    // マップへの参照
    private Map map;
    // HP
    public int hp = 100;
    // アイテム
    public int item_no = 0;
    public int action_alive = 0;

	public Action(double x, double y, Map map, int item_no) {
        this.x = x;
        this.y = y;
        this.map = map;
        this.item_no = item_no;

        init();
	}

    public void init(){
        vx = 10;
        vy = 0;
        onGround = false;
        dir = RIGHT;
        count = 0;
        action_alive = 1;
        // イメージをロードする
        loadImage();

        // アニメーション用スレッドを開始
        AnimationThread thread = new AnimationThread();
        thread.start();
    }
    /**
     * 停止する
     */
    public void stop() {
        vx = 0;
    }

    /**
     * 左に加速する
     */
    public void accelerateLeft() {
        vx = -SPEED;
        dir = LEFT;
    }

    /**
     * 右に加速する
     */
    public void accelerateRight() {
        vx = SPEED;
        dir = RIGHT;
    }

    /**
     * ジャンプする
     */
    public void jump() {
        if (onGround) {
            // 上向きに速度を加える
            vy = -JUMP_SPEED;
            onGround = false;
        }
    }

    /**
     * プレイヤーの状態を更新する
     */
    public void update() {
        // 重力で下向きに加速度がかかる
        vy += Map.GRAVITY;

        // x方向の当たり判定
        // 移動先座標を求める
        double newX = x + vx;
        // 移動先座標で衝突するタイルの位置を取得
        // x方向だけ考えるのでy座標は変化しないと仮定
        Point tile = map.getTileCollision(this, newX, y);
        if (tile == null) {
            // 衝突するタイルがなければ移動
            x = newX;
        } else {
            // 衝突するタイルがある場合
            if (vx > 0) { // 右へ移動中なので右のブロックと衝突
                // ブロックにめりこむ or 隙間がないように位置調整
                x = Map.tilesToPixels(tile.x) - WIDTH;
            } else if (vx < 0) { // 左へ移動中なので左のブロックと衝突
                // 位置調整
                x = Map.tilesToPixels(tile.x + 1);
            }
            vx = 0;
        }

        // y方向の当たり判定
        // 移動先座標を求める
        double newY = y + vy;
        // 移動先座標で衝突するタイルの位置を取得
        // y方向だけ考えるのでx座標は変化しないと仮定
        tile = map.getTileCollision(this, x, newY);
        if (tile == null) {
            // 衝突するタイルがなければ移動
            y = newY;
            // 衝突してないということは空中
            onGround = false;
        } else {
            // 衝突するタイルがある場合
            if (vy > 0) { // 下へ移動中なので下のブロックと衝突（着地）
                // 位置調整
                y = Map.tilesToPixels(tile.y) - HEIGHT;
                // 着地したのでy方向速度を0に
                vy = 0;
                // 着地
                onGround = true;
            } else if (vy < 0) { // 上へ移動中なので上のブロックと衝突（天井ごん！）
                // 位置調整
                y = Map.tilesToPixels(tile.y + 1);
                // 天井にぶつかったのでy方向速度を0に
                vy = 0;
            }
        }
    }

    /**
     * プレイヤーを描画
     *
     * @param g 描画オブジェクト
     * @param offsetX X方向オフセット
     * @param offsetY Y方向オフセット
     */
    public void draw(Graphics g, int offsetX, int offsetY) {
        g.drawImage(image,
                (int) x + offsetX, (int) y + offsetY,
                (int) x + offsetX + WIDTH, (int) y + offsetY + HEIGHT,
                count * WIDTH, dir * HEIGHT,
                count * WIDTH + WIDTH, dir * HEIGHT + HEIGHT,
                null);
    }

    /**
     * @return Returns the x.
     */
    public double getX() {
        return x;
    }
    /**
     * @return Returns the y.
     */
    public double getY() {
        return y;
    }

    /**
     * イメージをロードする
     */
    public void loadImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "image/item_02.gif"));
        image = icon.getImage();
    }

    // アニメーション用スレッド
    public class AnimationThread extends Thread {
        public void run() {
            while (true) {
                // countを切り替える
                if (count == 0) {
                    count = 1;
                } else if (count == 1) {
                    count = 0;
                }

                // 300ミリ秒休止＝300ミリ秒おきに勇者の絵を切り替える
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int breakObject(double x, double y, Map map){
    	int tile_x = Map.pixelsToTiles(x - MainPanel.offsetX);
    	int tile_y = Map.pixelsToTiles(y - MainPanel.offsetY);
    	int block_no = 0;

    	block_no = map.map[tile_y][tile_x];

    	map.map[tile_y][tile_x] = 0;
		/*確認用
		 * System.out.println("X座標:" + x);
		System.out.println("Y座標:" + y);
		System.out.println("X座標:" + tile_x);
		System.out.println("Y座標:" + tile_y);*/
    	return block_no;
    }

}