package sysD_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;

public class Enemy extends Character{
    public int talk = 0;
    
    private String chara_data;
    
	public static final int SABER = 0;
	public static final int LIN = 1;
	public static final int ANGELA = 2;
    // プレイヤー画像
    private Image image_s;
    private int img_no;
    
	private static final int EDGE_WIDTH = 2;
	private Rectangle rect;
	private Rectangle innerRect;
	private boolean isVisible = true;
    
    public Enemy(double x, double y, Map map, String chara_data, int char_no) {
    	super(x, y, map);
        this.chara_data = chara_data;
        img_no = char_no;
		this.rect = MainPanel.WIND_RECT;
		innerRect = new Rectangle(
				rect.x + EDGE_WIDTH,
				rect.y + EDGE_WIDTH,
				rect.width - EDGE_WIDTH * 2,
				rect.height - EDGE_WIDTH *2);
        // イメージをロードする
        loadImage();
        loadImage_stand();
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
        }else if(chara_data == "angela"){
	    	g.drawImage(image,
	                (int) x + offsetX, (int) y + offsetY, 
	                (int) x + offsetX + WIDTH, (int) y + offsetY + HEIGHT,
	                0, 0,
	                WIDTH*8, HEIGHT*8,
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

	
    public void draw_stand(Graphics g, int offsetX, int offsetY) {
    	switch(img_no){
	    	case 0:
		        g.drawImage(image_s,
		                (int) 0 , (int) 100, 
		                (int) 0 + 400, (int) 100 + 400,
		                0, 0,
		                400, 400,
		                null);    	
	    		break;
	    	case 1:
		    	g.drawImage(image_s,
		    	(int) 400 , (int) 100, 
		    	(int) 400 + 400, (int) 100 + 400,
		    	0, 0,
		    	400, 400,
		    	null);    	
		        break;
	    	case 2:
		    	g.drawImage(image_s,
		    	(int) 400 , (int) 100, 
		    	(int) 400 + 400, (int) 100 + 400,
		    	0, 0,
		    	800, 800,
		    	null);    	
		        break;
	    }
    }
    
    private void loadImage_stand() {
    	String a = "";
    	switch(img_no){
	    	case 0:
	    		a = "big_01.gif";
	    		break;
	    	case 1:
	    		a = "big_02.gif";
	    		break;
	    	case 2:
	    		a = "angela_big.gif";
	    		break;
	    	}
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "image/" + a));
        image_s = icon.getImage();
    }


	public void draw_text(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (isVisible == false) return;

		g.setColor(Color.WHITE);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);

		g.setColor(Color.BLACK);
		g.fillRect(innerRect.x, innerRect.y, innerRect.width, innerRect.height);
		
		Font f=new Font(null,Font.BOLD,20);
		g.setFont(f);
		g.setColor(Color.white);
    	switch(img_no){
    	case 0:
    		g.drawString("問おう 貴方が私のマスターか", innerRect.x + 15, innerRect.y + 25);
    		g.drawString("やっと気づいた。シロウは、私の鞘だったのですね", innerRect.x + 15, innerRect.y + 55);
    		g.drawString("判らぬか、下郎。そのような物より、私はシロウが欲しいと言ったのだ", innerRect.x + 15, innerRect.y + 85);
    		break;
    	case 1:
    		g.drawString("私はこれから何が起きるかわかってるし、覚悟もできてる。十年前からね", innerRect.x + 15, innerRect.y + 25);
    		g.drawString("世界なんてとっくに私の物じゃない。", innerRect.x + 15, innerRect.y + 55);
    		g.drawString("心の贅肉ね", innerRect.x + 15, innerRect.y + 85);
	        break;
    	case 2:
    		g.drawString("これもあなたの言ってた『仁義』ってやつ？", innerRect.x + 15, innerRect.y + 25);
    		g.drawString("楽園を追放されたアダムとイヴの気持ちがわかるわ", innerRect.x + 15, innerRect.y + 55);
    		g.drawString("まだこの世界をろくに知らない", innerRect.x + 15, innerRect.y + 85);
	        break;
    }

	}

	public void show(){
		isVisible = true;
	}
	public void hide(){
		isVisible = false;
	}

	public boolean isVisible(){
		return isVisible;
	}
	
}