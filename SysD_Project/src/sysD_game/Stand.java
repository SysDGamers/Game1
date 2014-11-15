package sysD_game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Stand{
	public static final int SABER = 0;
	public static final int LIN = 1;
    // プレイヤー画像
    private Image image;
    private int img_no;
	
    public Stand(int char_no) {
        img_no = char_no;
        // イメージをロードする
        loadImage();
    }
    
    public void draw(Graphics g, int offsetX, int offsetY) {
    	switch(img_no){
	    	case 0:
		        g.drawImage(image,
		                (int) 0 , (int) 100, 
		                (int) 0 + 400, (int) 100 + 400,
		                0, 0,
		                400, 400,
		                null);    	
	    		break;
	    	case 1:
		    	g.drawImage(image,
		    	(int) 400 , (int) 100, 
		    	(int) 400 + 400, (int) 100 + 400,
		    	0, 0,
		    	400, 400,
		    	null);    	
		        break;
	    }
    }
    
    private void loadImage() {
    	String a = "";
    	switch(img_no){
	    	case 0:
	    		a = "big_01.gif";
	    		break;
	    	case 1:
	    		a = "big_02.gif";
	    		break;
	    	}
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "image/" + a));
        image = icon.getImage();
    }
	
}