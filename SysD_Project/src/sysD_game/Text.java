package sysD_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class Text {

	private static final int EDGE_WIDTH = 2;
	private Rectangle rect;
	private Rectangle innerRect;
	private boolean isVisible = true;
	private int img_no;

	public Text(Rectangle rect, int char_no){
		this.rect = rect;
		img_no = char_no;
		innerRect = new Rectangle(
				rect.x + EDGE_WIDTH,
				rect.y + EDGE_WIDTH,
				rect.width - EDGE_WIDTH * 2,
				rect.height - EDGE_WIDTH *2);
	}

	public void draw(Graphics g) {
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
