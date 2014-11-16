package sysD_game;

import javax.swing.ImageIcon;

public class Enemy extends Character{
    public int talk = 0;

    private String chara_data;

    public Enemy(double x, double y, Map map, String chara_data) {
    	super(x, y, map);
        this.chara_data = chara_data;
        // イメージをロードする
        loadImage();
    }

    /**
     * イメージをロードする
     */
    public void loadImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "image/" + chara_data + ".gif"));
        image = icon.getImage();
    }

    public void getCollision(Player player){
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
    	if ((player.x-this.x)*(player.x-this.x) + (player.y-this.y)*(player.y-this.y) <= 400){
    		talk = 1;
    		player.hp -= 5;
    	}else {
    		talk = 0;
    	}
    }
}