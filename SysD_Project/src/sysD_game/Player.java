package sysD_game;

import javax.swing.ImageIcon;

public class Player extends Character{
    // HP
    public int hp = 100;
    // アイテム
    public int[] item;

	public Player(double x, double y, Map map) {
		super(x, y, map);
        item = new int[50];
        // イメージをロードする
        loadImage();
	}

    /**
     * @return Returns the x.
     */
    //public double getX() {
    //    return x;
    //}
    /**
     * @return Returns the y.
     */
//   public double getY() {
    //    return y;
    //}

    /**
     * イメージをロードする
     */
    private void loadImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "image/char_04.gif"));
        image = icon.getImage();
    }

    public int digObject(double x, double y, Map map){
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

    public void getItem(Item item_c){
    	item[item_c.item_no] += 1;
    	item_c.item_alive = 0;
    	//System.out.println(item[item_c.item_no]);
    }

}