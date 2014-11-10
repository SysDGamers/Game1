package createMap;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Createmap {

	public static void main(String args[]){

		int height = 30;
		int width = 1000;
		try{
			File file = new File("src/sysD_game/map/test.txt");

			if (checkBeforeWritefile(file)){
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

				pw.println(height);
				pw.println(width);
				for(int i=0;i < height; i++){
					for(int j=0;j < width; j ++){
						if(i==0){//天井
							pw.print(1);
							if(j == width-1) pw.println(1);
						} else if(i == height-1){//地面
							pw.print(1);
							if(j == width-1) pw.println(1);
						} else{//他の場所
							double d = Math.random();
							if(j== width-1){
								pw.println(0);
							}else{
								if(d<0.8) pw.print(0);
								else if(d<0.85)pw.print(1);
								else if(d<0.90)pw.print(2);
								else if(d<0.95)pw.print(3);
								else if(d<0.98)pw.print(4);
								else pw.print(5);
							}
						}
					}
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
