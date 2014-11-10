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
		int width = 100;
		try{
			File file = new File("src/sysD_game/map/test.txt");

			if (checkBeforeWritefile(file)){
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

				pw.println(height);
				pw.println(width);
				for(int i=0;i < height; i++){
					for(int j=0;j < width; j ++){
						if(i==0){
							pw.print(1);
							if(j == width-1) pw.println(1);
						} else if(i == height-1){
							pw.print(1);
							if(j == width-1) pw.println(1);
						} else{
							pw.print(0);

							if(j== width-1
									) pw.println(0);
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
