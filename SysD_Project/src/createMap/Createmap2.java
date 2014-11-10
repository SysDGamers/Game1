package createMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Createmap2 {

	public static void main(String args[]){

		int height = 30;
		int width = 100;
		int map[][] = new int[height][width];
		for(int i=0;i < height; i++){
			for(int j=0;j < width; j++){
				if(i==0){//天井
					map[i][j] = 1;
				}else if(i==height-1){//地面
					map[i][j] = 1;
				}else if(i<=7){
					map[i][j] = 0;//陸上
				}else{
					Random rnd = new Random();
					int ran = rnd.nextInt(2);
					switch(map[i-1][j]){
					case 0:
						map[i][j] = ran;
						break;
					case 1:
						map[i][j] = ran + 1;
						break;
					case 2:
						map[i][j] = ran + 2;
						break;
					case 3:
						map[i][j] = ran + 3;
						break;
					case 4:
						map[i][j] = ran + 4;
						break;
					case 5:
						map[i][j] = ran + 5;
						break;
					case 6:
						map[i][j] = ran + 5;
						break;
					default:
						map[i][j] = ran;
					}
				}
			}
		}

		try{
			File file = new File("src/sysD_game/map/test.txt");

			if (checkBeforeWritefile(file)){
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
				pw.println(height);
				pw.println(width);

				for(int i=0;i<height; i++){
					for(int j=0; j<width;j++){
						pw.print(map[i][j]);
					}
					pw.println();
				}

				pw.close();
			}else{
				System.out.println("ファイルに書き込めません");
			}
		}catch(IOException e){
			System.out.println(e);
		}
	}

	private static boolean checkBeforeWritefile(File file){
		if (file.exists()){
			if (file.isFile() && file.canWrite()){
				return true;
			}
		}

		return false;
	}
}
