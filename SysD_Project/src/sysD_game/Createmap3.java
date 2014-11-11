package sysD_game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Createmap3 {

	public void createMap(){
		int width = 500;
		int height = 50;
		boolean up_down_flag= true;
		int map[][] = new int[width][height];
		//境界線
		for(int i=0;i < height; i++){
			for(int j=0;j < width; j++){
				if(i < height/2){
					map[j][i]=0;
				}else{
					map[j][i]=1;
				}
			}
		}
		//山作成
		for(int j=0; j<width-1; j++){
			int sum_height=0;
			for(int i=0; i<height; i++){
				sum_height += map[j][i];
			}
			if(up_down_flag==true){
				double d = Math.random();
				if(d<0.5){
					sum_height += 0;
				}else{
					sum_height += 1;
				}
				for(int k=height-sum_height; k<height; k++){
					map[j+1][k]=1;
				}
				if(sum_height> height/5 * 3){
					up_down_flag = false;
				}
			}else if(up_down_flag == false){
				double d = Math.random();
				if(d<0.5){
					sum_height += 0;
				}else{
					sum_height -= 1;
				}
				for(int k=height-sum_height; k<height; k++){
					map[j+1][k]=1;
				}
				if(sum_height< height/2){
					up_down_flag = true;
				}
			}

		}
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				System.out.print(map[j][i]);
			}
			System.out.println();
		}



		try{
			File file = new File("src/sysD_game/map/test.txt");

			if (checkBeforeWritefile(file)){
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
				pw.println(height);
				pw.println(width);

				for(int i=0;i<height; i++){
					for(int j=0; j<width;j++){
						pw.print(map[j][i]);
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
