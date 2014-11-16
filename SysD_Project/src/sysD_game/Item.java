package sysD_game;
import character_package.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

import character_package.Player;

public class Item {
	// アイテム定数
	public static final int BLANK = 0;
	public static final int BLOCK_01 = 1;
	public static final int BLOCK_02 = 2;
	public static final int BLOCK_03 = 3;
	public static final int BLOCK_04 = 4;
	public static final int BLOCK_05 = 5;
	public static final int BLOCK_06 = 6;
	public static final int BLOCK_07 = 7;
	public static final int BLOCK_08 = 8;
	public static final int BLOCK_09 = 9;
	public static final int BLOCK_10 = 10;
	public static final int NAN_J_MIN = 11;
	public static final int GEN_JU_MIN = 12;
	public static final int BALL = 13;
	// 幅
    public static final int WIDTH = 32;
    // 高さ
    public static final int HEIGHT = 32;
    // スピードA
    private static int SPEED = 6;
    // ジャンプ力
    private static int JUMP_SPEED = 12;
    // 方向
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    // 位置
    private double x;
    private double y;
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
    private Map map;

    public int item_alive = 0;
    public int item_no = 0;

    public Item() {
    }

    public Item(double x, double y, Map map, int item_no) {
        this.x = x;
        this.y = y;
        this.map = map;
        this.item_no = item_no;

        init();
    }

    public void init(){
        vx = 0;
        vy = 0;
        onGround = false;
        dir = RIGHT;
        count = 0;
        item_alive = 1;
        // イメージをロードする
        loadImage();
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
        vx = -SPEED*0.3;
        dir = LEFT;
    }

    /**
     * 右に加速する
     */
    public void accelerateRight() {
        vx = SPEED*0.3;
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
        // ランダムな動き
		double d = Math.random();
		if(d<0.8){
		}else if(d<0.85){
			accelerateLeft();
		}else if(d<0.90){
			accelerateRight();
		}else if(d<0.93){
			jump();
		}
        // x方向の当たり判定
        // 移動先座標を求める
        double newX = x + vx;
        // 移動先座標で衝突するタイルの位置を取得
        // x方向だけ考えるのでy座標は変化しないと仮定
        Point tile = map.getTileCollision2(this, newX, y);
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
        tile = map.getTileCollision2(this, x, newY);
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
    private void loadImage() {
    	String i_name = "";
    	if(item_no == BALL){
    		i_name = "image/item_02.gif";
    	}else if(item_no == NAN_J_MIN){
    		i_name = "image/char_05.gif";
    	}
        ImageIcon icon = new ImageIcon(getClass().getResource(i_name));
        image = icon.getImage();
    }

    public boolean getCollision(Player player){
    	if ((player.getX()-this.x)*(player.getX()-this.x) + (player.getY()-this.y)*(player.getY()-this.y) <= 400){
    		return true;
    	}else {
    		return false;
    	}
    }
}