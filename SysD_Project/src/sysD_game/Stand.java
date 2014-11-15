package sysD_game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Stand{
    // プレイヤー画像
    private Image image;
	
    public Stand() {
        // イメージをロードする
        loadImage();
    }
    
    public void draw(Graphics g, int offsetX, int offsetY) {
        g.drawImage(image,
                (int) 0 , (int) 100, 
                (int) 0 + 400, (int) 100 + 400,
                0, 0,
                400, 400,
                null);
    }
    private void loadImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "image/big_01.gif"));
        image = icon.getImage();
    }
	
}