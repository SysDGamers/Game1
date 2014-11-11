package createMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Createmap3 {

	public static void main(String[] args){
		int width = 300;
		int height = 30;
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
				if(d<0.6){
					sum_height += 0;
				}else{
					sum_height += 1;
				}
				for(int k=height-sum_height; k<height; k++){
					map[j+1][k]=1;
				}
				if(sum_height> height/3 * 2){
					up_down_flag = false;
				}
			}else if(up_down_flag == false){
				double d = Math.random();

				if(d<0.6){
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
		//洞窟作成
		for(int k = 0; k<3; k++){
			double d_doukutu_x = Math.random() * 200;
			int doukutu_x = (int) d_doukutu_x;
			int doukutu_y = height/2;
			for(int i=0; i < 70; i++){
				int hole = 3;
				int hole_x = doukutu_x;
				int hole_y = doukutu_y;
				double d = Math.random();

				for(int j=0; j< hole; j++){
					map[hole_x][hole_y] = 0;
					hole_x += 1;
				}
				if(d<0.2){
					doukutu_y += 1;
				}else if(d <0.55){
					doukutu_x +=3;
				}else if(d<0.9){
					doukutu_x -=3;
				}else{
					doukutu_y -= 1;
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
