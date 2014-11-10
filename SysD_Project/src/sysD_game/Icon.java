package sysD_game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

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
    private Image image;
    private int[] hp_icon;
    
    public Icon() {
        init();
    }
    
    public void init(){
        count = 0;
        hp_icon = new int[10];
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
    }

    public void draw(Graphics g, int offsetX, int offsetY) {
    	for(int i=0; i<10; i++){
    		if(hp_icon[i] == HP_MAX){
    			g.drawImage(image, 5 + i*30, 5, 5 + i*30 + WIDTH, 5 + HEIGHT, WIDTH, 1 * HEIGHT, WIDTH + WIDTH, 1 * HEIGHT + HEIGHT, null);
    		}else if(hp_icon[i] == HP_HALF){
    			g.drawImage(image, 5 + i*30, 5, 5 + i*30 + WIDTH, 5 + HEIGHT, 0, HEIGHT, WIDTH, HEIGHT + HEIGHT, null);
    		}else if(hp_icon[i] == HP_ZERO){
    			g.drawImage(image, 5 + i*30, 5, 5 + i*30 + WIDTH, 5 + HEIGHT, 0, 0, WIDTH, HEIGHT, null);
    		}
    	}
        /*g.drawImage(image, 5, 5, 5 + WIDTH, 5 + HEIGHT, WIDTH, 1 * HEIGHT, WIDTH + WIDTH, 1 * HEIGHT + HEIGHT, null);	// マックス
        g.drawImage(image, 35, 5, 35 + WIDTH, 5 + HEIGHT, 0, 0, WIDTH, HEIGHT, null);	// ゼロ
        g.drawImage(image, 65, 5, 65 + WIDTH, 5 + HEIGHT, 0, HEIGHT, WIDTH, HEIGHT + HEIGHT, null);	// ハーフ*/
    }

    /**
     * イメージをロードする
     */
    private void loadImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "image/icon.gif"));
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
}