package sysD_game;

import java.awt.Graphics;

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
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "image/" + chara_data + ".gif"));
        image = icon.getImage();
    }
    
    public void draw(Graphics g, int offsetX, int offsetY) {
        if(chara_data == "char_06"){
	    	g.drawImage(image,
	                (int) x + offsetX, (int) y + offsetY, 
	                (int) x + offsetX + WIDTH, (int) y + offsetY + HEIGHT,
	                super.count * WIDTH*2, super.dir * HEIGHT*2,
	                super.count * WIDTH*2 + WIDTH*2, super.dir * HEIGHT*2 + HEIGHT*2,
	                null);
        }else{
	    	g.drawImage(image,
	                (int) x + offsetX, (int) y + offsetY, 
	                (int) x + offsetX + WIDTH, (int) y + offsetY + HEIGHT,
	                super.count * WIDTH, super.dir * HEIGHT,
	                super.count * WIDTH + WIDTH, super.dir * HEIGHT + HEIGHT,
	                null);
        }
    }
    
    public void getCollision(Player player){
        /*// ランダムな動き
		double d = Math.random();
		if(d<0.8){
		}else if(d<0.85){
			accelerateLeft();
		}else if(d<0.90){
			accelerateRight();
		}else if(d<0.93){
			jump();
		}*/
    	if ((player.x-this.x)*(player.x-this.x) + (player.y-this.y)*(player.y-this.y) <= 400){
    		talk = 1;
    		player.hp -= 5;
    	}else {
    		talk = 0;
    	}
    }
}