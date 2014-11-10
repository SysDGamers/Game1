package sysD_game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;

public class Map {
    // タイルサイズ
    public static final int TILE_SIZE = 32;
    // 重力
    public static final double GRAVITY = 0.6;
    // ブロック
    public static final int BLOCK_01_X = 0;
    public static final int BLOCK_01_Y = 0;
    public static final int BLOCK_02_X = 32;
    public static final int BLOCK_02_Y = 0;
    public static final int BLOCK_03_X = 64;
    public static final int BLOCK_03_Y = 0;
    public static final int BLOCK_04_X = 96;
    public static final int BLOCK_04_Y = 0;
    public static final int BLOCK_05_X = 128;
    public static final int BLOCK_05_Y = 0;
    public static final int BLOCK_06_X = 160;
    public static final int BLOCK_06_Y = 0;
    public static final int BLOCK_07_X = 192;
    public static final int BLOCK_07_Y = 0;
    public static final int BLOCK_08_X = 224;
    public static final int BLOCK_08_Y = 0;
    public static final int BLOCK_09_X = 256;
    public static final int BLOCK_09_Y = 0;
    public static final int BLOCK_10_X = 288;
    public static final int BLOCK_10_Y = 0;

    // マップ
    public int[][] map;

    // 行数
    private int row;
    // 列数
    private int col;
    // 幅
    private int width;
    // 高さ
    private int height;

    // ブロックの画像
    private Image blockImage;

    public Map(String filename) {
        // マップをロードする
        load(filename);

        width = TILE_SIZE * col;
        height = TILE_SIZE * row;

        // イメージをロードする
        loadImage();
    }

    /**
     * マップを描画する
     *
     * @param g 描画オブジェクト
     * @param offsetX X方向オフセット
     * @param offsetY Y方向オフセット
     */
    public void draw(Graphics g, int offsetX, int offsetY) {
    	int buf_x, buf_y = 0;
        // オフセットを元に描画範囲を求める
        int firstTileX = pixelsToTiles(-offsetX);
        int lastTileX = firstTileX + pixelsToTiles(MainPanel.WIDTH) + 1;
        // 描画範囲がマップの大きさより大きくならないように調整
        lastTileX = Math.min(lastTileX, col);

        int firstTileY = pixelsToTiles(-offsetY);
        int lastTileY = firstTileY + pixelsToTiles(MainPanel.HEIGHT) + 1;
        // 描画範囲がマップの大きさより大きくならないように調整
        lastTileY = Math.min(lastTileY, row);

        for (int i = firstTileY; i < lastTileY; i++) {
            for (int j = firstTileX; j < lastTileX; j++) {
                // mapの値に応じて画像を描く
                switch (map[i][j]) {
                    case 1 : // ブロック
                    	buf_x = BLOCK_01_X; buf_y = BLOCK_01_Y;
                        g.drawImage(blockImage, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, tilesToPixels(j) + offsetX + 32, tilesToPixels(i) + offsetY + 32, buf_x, buf_y, buf_x + 32, buf_y + 32, null);
                        break;
                    case 2 : // ブロック
                    	buf_x = BLOCK_02_X; buf_y = BLOCK_02_Y;
                        g.drawImage(blockImage, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, tilesToPixels(j) + offsetX + 32, tilesToPixels(i) + offsetY + 32, buf_x, buf_y, buf_x + 32, buf_y + 32, null);
                        break;
                    case 3 : // ブロック
                    	buf_x = BLOCK_03_X; buf_y = BLOCK_03_Y;
                        g.drawImage(blockImage, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, tilesToPixels(j) + offsetX + 32, tilesToPixels(i) + offsetY + 32, buf_x, buf_y, buf_x + 32, buf_y + 32, null);
                        break;
                    case 4 : // ブロック
                    	buf_x = BLOCK_04_X; buf_y = BLOCK_04_Y;
                        g.drawImage(blockImage, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, tilesToPixels(j) + offsetX + 32, tilesToPixels(i) + offsetY + 32, buf_x, buf_y, buf_x + 32, buf_y + 32, null);
                        break;
                    case 5 : // ブロック
                    	buf_x = BLOCK_05_X; buf_y = BLOCK_05_Y;
                        g.drawImage(blockImage, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, tilesToPixels(j) + offsetX + 32, tilesToPixels(i) + offsetY + 32, buf_x, buf_y, buf_x + 32, buf_y + 32, null);
                        break;
                    case 6 : // ブロック
                    	buf_x = BLOCK_06_X; buf_y = BLOCK_06_Y;
                        g.drawImage(blockImage, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, tilesToPixels(j) + offsetX + 32, tilesToPixels(i) + offsetY + 32, buf_x, buf_y, buf_x + 32, buf_y + 32, null);
                        break;
                    case 7 : // ブロック
                    	buf_x = BLOCK_07_X; buf_y = BLOCK_07_Y;
                        g.drawImage(blockImage, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, tilesToPixels(j) + offsetX + 32, tilesToPixels(i) + offsetY + 32, buf_x, buf_y, buf_x + 32, buf_y + 32, null);
                        break;
                    case 8 : // ブロック
                    	buf_x = BLOCK_08_X; buf_y = BLOCK_08_Y;
                        g.drawImage(blockImage, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, tilesToPixels(j) + offsetX + 32, tilesToPixels(i) + offsetY + 32, buf_x, buf_y, buf_x + 32, buf_y + 32, null);
                        break;
                    case 9 : // ブロック
                    	buf_x = BLOCK_09_X; buf_y = BLOCK_09_Y;
                        g.drawImage(blockImage, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, tilesToPixels(j) + offsetX + 32, tilesToPixels(i) + offsetY + 32, buf_x, buf_y, buf_x + 32, buf_y + 32, null);
                        break;
                    case 10 : // ブロック
                    	buf_x = BLOCK_10_X; buf_y = BLOCK_10_Y;
                        g.drawImage(blockImage, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY, tilesToPixels(j) + offsetX + 32, tilesToPixels(i) + offsetY + 32, buf_x, buf_y, buf_x + 32, buf_y + 32, null);
                        break;
                }
            }
        }
    }

    /**
     * (newX, newY)で衝突するブロックの座標を返す
     * @param player プレイヤーへの参照
     * @param newX X座標
     * @param newY Y座標
     * @return 衝突するブロックの座標
     */
    public Point getTileCollision(Character player, double newX, double newY) {
        // 小数点以下切り上げ
        // 浮動小数点の関係で切り上げしないと衝突してないと判定される場合がある
        newX = Math.ceil(newX);
        newY = Math.ceil(newY);

        double fromX = Math.min(player.getX(), newX);
        double fromY = Math.min(player.getY(), newY);
        double toX = Math.max(player.getX(), newX);
        double toY = Math.max(player.getY(), newY);

        int fromTileX = pixelsToTiles(fromX);
        int fromTileY = pixelsToTiles(fromY);
        int toTileX = pixelsToTiles(toX + Character.WIDTH - 1);
        int toTileY = pixelsToTiles(toY + Character.HEIGHT - 1);

        // 衝突しているか調べる
        for (int x = fromTileX; x <= toTileX; x++) {
            for (int y = fromTileY; y <= toTileY; y++) {
                // 画面外は衝突
                if (x < 0 || x >= col) {
                    return new Point(x, y);
                }
                if (y < 0 || y >= row) {
                    return new Point(x, y);
                }
                // ブロックがあったら衝突
                if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 3 || map[y][x] == 4 || map[y][x] == 5 || map[y][x] == 6 || map[y][x] == 7 || map[y][x] == 8 || map[y][x] == 9 || map[y][x] == 10) {
                    return new Point(x, y);
                }
            }
        }

        return null;
    }

    public Point getTileCollision2(Item player, double newX, double newY) {
        // 小数点以下切り上げ
        // 浮動小数点の関係で切り上げしないと衝突してないと判定される場合がある
        newX = Math.ceil(newX);
        newY = Math.ceil(newY);

        double fromX = Math.min(player.getX(), newX);
        double fromY = Math.min(player.getY(), newY);
        double toX = Math.max(player.getX(), newX);
        double toY = Math.max(player.getY(), newY);

        int fromTileX = pixelsToTiles(fromX);
        int fromTileY = pixelsToTiles(fromY);
        int toTileX = pixelsToTiles(toX + Character.WIDTH - 1);
        int toTileY = pixelsToTiles(toY + Character.HEIGHT - 1);

        // 衝突しているか調べる
        for (int x = fromTileX; x <= toTileX; x++) {
            for (int y = fromTileY; y <= toTileY; y++) {
                // 画面外は衝突
                if (x < 0 || x >= col) {
                    return new Point(x, y);
                }
                if (y < 0 || y >= row) {
                    return new Point(x, y);
                }
                // ブロックがあったら衝突
                if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 3 || map[y][x] == 4 || map[y][x] == 5 || map[y][x] == 6 || map[y][x] == 7 || map[y][x] == 8 || map[y][x] == 9 || map[y][x] == 10) {
                    return new Point(x, y);
                }
            }
        }

        return null;
    }

    /**
     * ピクセル単位をタイル単位に変更する
     * @param pixels ピクセル単位
     * @return タイル単位
     */
    public static int pixelsToTiles(double pixels) {
        return (int)Math.floor(pixels / TILE_SIZE);
    }

    /**
     * タイル単位をピクセル単位に変更する
     * @param tiles タイル単位
     * @return ピクセル単位
     */
    public static int tilesToPixels(int tiles) {
        return tiles * TILE_SIZE;
    }

    /**
     * イメージをロードする
     */
    private void loadImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource("image/block.gif"));
        blockImage = icon.getImage();
    }

    /**
     * マップをロードする
     *
     * @param filename マップファイル
     */
    private void load(String filename) {
        try {
            // ファイルを開く
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("map/" + filename)));

            // 行数を読み込む
            String line = br.readLine();
            row = Integer.parseInt(line);
            // 列数を読み込む
            line = br.readLine();
            col = Integer.parseInt(line);
            // マップを作成
            map = new int[row][col];
            for (int i = 0; i < row; i++) {
                line = br.readLine();
                for (int j = 0; j < col; j++) {
                    map[i][j] = Integer.parseInt(line.charAt(j) + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Returns the width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return Returns the height.
     */
    public int getHeight() {
        return height;
    }
}
