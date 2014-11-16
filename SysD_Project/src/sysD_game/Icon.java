package sysD_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import character_package.Player;

public class Icon {
	// 幅
    public static final int WIDTH = 32;
    // 高さ
    public static final int HEIGHT = 32;
    // アイコン定数
    public static final int HP_MAX = 1;
    public static final int HP_HALF = 2;
    public static final int HP_ZERO = 3;
    // アニメーション用カウンタ
    private int count;
    // プレイヤー画像
    private Image image_life;
    private Image image_equip;
    private Image image_equip2;
    private int[] hp_icon;
    private int[] equip_icon;
    
	private static final int EDGE_WIDTH = 2;
	private Rectangle equip_01a, equip_01b, equip_02a, equip_02b, equip_03a, equip_03b, equip_04a, equip_04b, equip_05a, equip_05b;
    
    public Icon() {
        init();
    }
    
    public void init(){
        count = 0;
        hp_icon = new int[10];
        equip_icon = new int[6];
        // イメージをロードする
        loadImage();
        
        // アニメーション用スレッドを開始
        AnimationThread thread = new AnimationThread();
        thread.start();
    }

    /**
     * プレイヤーの状態を更新する
     */
    public void update(Player player) {
    	if (player.hp >= 100){
    		for(int i=0; i<10; i++){
    			hp_icon[i] = HP_MAX;
    		}
    	}else if(player.hp >= 95){
    		hp_icon[9] = HP_HALF;
    	}else if(player.hp >= 90){
    		hp_icon[9] = HP_ZERO;
    	}else if(player.hp >= 85){
    		hp_icon[8] = HP_HALF;
    	}else if(player.hp >= 80){
    		hp_icon[8] = HP_ZERO;
    	}else if(player.hp >= 75){
    		hp_icon[7] = HP_HALF;
    	}else if(player.hp >= 70){
    		hp_icon[7] = HP_ZERO;
    	}else if(player.hp >= 65){
    		hp_icon[6] = HP_HALF;
    	}else if(player.hp >= 60){
    		hp_icon[6] = HP_ZERO;
    	}else if(player.hp >= 55){
    		hp_icon[5] = HP_HALF;
    	}else if(player.hp >= 50){
    		hp_icon[5] = HP_ZERO;
    	}else if(player.hp >= 45){
    		hp_icon[4] = HP_HALF;
    	}else if(player.hp >= 40){
    		hp_icon[4] = HP_ZERO;
    	}else if(player.hp >= 35){
    		hp_icon[3] = HP_HALF;
    	}else if(player.hp >= 30){
    		hp_icon[3] = HP_ZERO;
    	}else if(player.hp >= 25){
    		hp_icon[2] = HP_HALF;
    	}else if(player.hp >= 20){
    		hp_icon[2] = HP_ZERO;
    	}else if(player.hp >= 15){
    		hp_icon[1] = HP_HALF;
    	}else if(player.hp >= 10){
    		hp_icon[1] = HP_ZERO;
    	}else if(player.hp >= 5){
    		hp_icon[0] = HP_HALF;
    	}else if(player.hp == 0){
    		hp_icon[0] = HP_ZERO;
    	}
    	
    	equip_01a = new Rectangle(398, 3, WIDTH + 2, HEIGHT + 2);
    	equip_01b = new Rectangle(equip_01a.x + EDGE_WIDTH, equip_01a.y + EDGE_WIDTH, equip_01a.width - EDGE_WIDTH * 2, equip_01a.height - EDGE_WIDTH *2);
    	equip_02a = new Rectangle(458, 3, WIDTH + 2, HEIGHT + 2);
    	equip_02b = new Rectangle(equip_02a.x + EDGE_WIDTH, equip_02a.y + EDGE_WIDTH, equip_02a.width - EDGE_WIDTH * 2, equip_02a.height - EDGE_WIDTH *2);
    	equip_03a = new Rectangle(498, 3, WIDTH + 2, HEIGHT + 2);
    	equip_03b = new Rectangle(equip_03a.x + EDGE_WIDTH, equip_03a.y + EDGE_WIDTH, equip_03a.width - EDGE_WIDTH * 2, equip_03a.height - EDGE_WIDTH *2);
    	equip_04a = new Rectangle(538, 3, WIDTH + 2, HEIGHT + 2);
    	equip_04b = new Rectangle(equip_04a.x + EDGE_WIDTH, equip_04a.y + EDGE_WIDTH, equip_04a.width - EDGE_WIDTH * 2, equip_04a.height - EDGE_WIDTH *2);
    	equip_05a = new Rectangle(578, 3, WIDTH + 2, HEIGHT + 2);
    	equip_05b = new Rectangle(equip_05a.x + EDGE_WIDTH, equip_05a.y + EDGE_WIDTH, equip_05a.width - EDGE_WIDTH * 2, equip_05a.height - EDGE_WIDTH *2);
    }

    public void draw(Graphics g, int offsetX, int offsetY, Player player) {
    	for(int i=0; i<10; i++){
    		if(hp_icon[i] == HP_MAX){
    			g.drawImage(image_life, 5 + i*30, 5, 5 + i*30 + WIDTH, 5 + HEIGHT, WIDTH, 1 * HEIGHT, WIDTH + WIDTH, 1 * HEIGHT + HEIGHT, null);
    		}else if(hp_icon[i] == HP_HALF){
    			g.drawImage(image_life, 5 + i*30, 5, 5 + i*30 + WIDTH, 5 + HEIGHT, 0, HEIGHT, WIDTH, HEIGHT + HEIGHT, null);
    		}else if(hp_icon[i] == HP_ZERO){
    			g.drawImage(image_life, 5 + i*30, 5, 5 + i*30 + WIDTH, 5 + HEIGHT, 0, 0, WIDTH, HEIGHT, null);
    		}
    	}
        /*g.drawImage(image, 5, 5, 5 + WIDTH, 5 + HEIGHT, WIDTH, 1 * HEIGHT, WIDTH + WIDTH, 1 * HEIGHT + HEIGHT, null);	// マックス
        g.drawImage(image, 35, 5, 35 + WIDTH, 5 + HEIGHT, 0, 0, WIDTH, HEIGHT, null);	// ゼロ
        g.drawImage(image, 65, 5, 65 + WIDTH, 5 + HEIGHT, 0, HEIGHT, WIDTH, HEIGHT + HEIGHT, null);	// ハーフ*/
    	
    	g.setColor(Color.WHITE);	g.fillRect(equip_01a.x, equip_01a.y, equip_01a.width, equip_01a.height);
		g.setColor(Color.BLACK);	g.fillRect(equip_01b.x, equip_01b.y, equip_01b.width, equip_01b.height);
		g.setColor(Color.WHITE);	g.fillRect(equip_02a.x, equip_02a.y, equip_02a.width, equip_02a.height);
		g.setColor(Color.BLACK);	g.fillRect(equip_02b.x, equip_02b.y, equip_02b.width, equip_02b.height);
		g.setColor(Color.WHITE);	g.fillRect(equip_03a.x, equip_03a.y, equip_03a.width, equip_03a.height);
		g.setColor(Color.BLACK);	g.fillRect(equip_03b.x, equip_03b.y, equip_03b.width, equip_03b.height);
		g.setColor(Color.WHITE);	g.fillRect(equip_04a.x, equip_04a.y, equip_04a.width, equip_04a.height);
		g.setColor(Color.BLACK);	g.fillRect(equip_04b.x, equip_04b.y, equip_04b.width, equip_04b.height);
		g.setColor(Color.WHITE);	g.fillRect(equip_05a.x, equip_05a.y, equip_05a.width, equip_05a.height);
		g.setColor(Color.BLACK);	g.fillRect(equip_05b.x, equip_05b.y, equip_05b.width, equip_05b.height);
    	
    	g.drawImage(image_equip, equip_01a.x, equip_01a.y, equip_01a.x + WIDTH, equip_01a.y + HEIGHT, WIDTH, 1 * HEIGHT, WIDTH + WIDTH, 1 * HEIGHT + HEIGHT, null);
    	Font f=new Font(null,Font.BOLD,15);
		g.setFont(f);
		g.setColor(Color.red);
		String a = Integer.toString(player.item[Item.BALL]);
		g.drawString(a, equip_01a.x + 28, equip_01a.y + 32);
    	g.drawImage(image_equip2, equip_02a.x, equip_02a.y, equip_02a.x + WIDTH, equip_02a.y + HEIGHT, WIDTH, 1 * HEIGHT, WIDTH + WIDTH, 1 * HEIGHT + HEIGHT, null);
		String a1 = Integer.toString(player.item[Item.NAN_J_MIN]);
		g.drawString(a1, equip_02a.x + 28, equip_02a.y + 32);
    }

    /**
     * イメージをロードする
     */
    private void loadImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource("image/icon.gif"));
        image_life = icon.getImage();
        ImageIcon icon_equip = new ImageIcon(getClass().getResource("image/item_02.gif"));
        image_equip = icon_equip.getImage();
        ImageIcon icon_equip2 = new ImageIcon(getClass().getResource("image/char_05.gif"));
        image_equip2 = icon_equip2.getImage();
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
}