package sysD_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPopUp extends JPanel{
	
	private static final int EDGE_WIDTH = 2;
	private final String LAST_PHRASE = "THIS_IS_END_OF_TEXT\n";
	private Rectangle rect;
	private Rectangle innerRect;
	private boolean isVisible = false;
	private JTextArea textArea;
	private String str;
	private String[] strs = new String[10000];
	private int lineStart;
	private int lineEnd;
	private int lastLine;
	private final MouseManager mouseManager = MouseManager.getInstance();
	
	// 行の数
	private int numRow;
	
	// 一度に表示する行数
	private final int MAX_LETTER_PER_ROW = 5; 
	
	
	public TextPopUp(Rectangle rect){
		this.rect = rect;
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.setBounds(rect);
		
		innerRect = new Rectangle(
				EDGE_WIDTH,
				EDGE_WIDTH,
				rect.width - EDGE_WIDTH * 2,
				rect.height - EDGE_WIDTH *2);
		
		this.drawText();
	}

	public void paintComponent () {
		if (!isVisible) {
			this.setVisible(false);
			return;
		}
		this.setVisible(true);
		textArea.setVisible(true);
	}
	
	private void drawText () {
		textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.BOLD, 16));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		textArea.setBounds(innerRect);
		textArea.addMouseListener(mouseManager);
		this.add(textArea);
		
		// 適当な初期化
		this.str = "クイーンズ・ギャンビット、シシリアン・ディフェンス、"
				+ "フレンチ・ディフェンス、KIA、ドラゴン・ユーゴスラビア・アタック、カタラン\n"
				+ "カロカン、ラトビアン・ギャンビット\n"
				+ "フィリドール・ディフェンス\n"
				+ "ツークツワンク\n"
				+ "ピン\n"
				+ "ステイルメイト\n"
				+ "バックランクメイト\n";
		this.setText(str);
	}
	
	// テキストを一列に表示できる文字数ごとに配列に格納
	private void textOrganizer() {
		int wordCount = 0;
		int row = 0;
		StringBuilder temp = new StringBuilder("");
		for (int i = 0; i < str.length(); i++ ) {
			if (wordCount >= 20) {
				wordCount = 0;
				temp.append("\n");
				strs[row] = temp.toString();
				row++;
				temp.setLength(0);
				temp.append(str.charAt(i));
				continue;
			}
			if (str.charAt(i) == '\n') {
				temp.append("\n");
				strs[row] = temp.toString();
				row++;
				temp.setLength(0);
			} else {
				temp = temp.append(str.charAt(i));
				wordCount++;
			}
		}
		strs[row] = this.LAST_PHRASE;
		
	}
	
	private void getFirstText() {
		lineStart = 0;
		lineEnd = this.MAX_LETTER_PER_ROW;
		StringBuilder temp = new StringBuilder("");
		for (int i = lineStart; i < lineEnd; i++) {
			if (strs[i].equals(this.LAST_PHRASE))
				break;
			temp.append(strs[i]);
		}
		textArea.setText(temp.toString());
	}
	
	public void getNextText() {
		lineStart += this.MAX_LETTER_PER_ROW;
		lineEnd += this.MAX_LETTER_PER_ROW;
		StringBuilder temp = new StringBuilder("");
		for (int i = lineStart; i < lineEnd; i++) {
			if (strs[i].equals(this.LAST_PHRASE))
				break;
			temp.append(strs[i]);
		}
		textArea.setText(temp.toString());
	}	

	public void setText (String str) {
		this.str = str;
		this.textOrganizer();
		this.getFirstText();
	}
	
	public void show () {
		isVisible = true;
	}
	
	public void hide () {
		isVisible = false;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
}
