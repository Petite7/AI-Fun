package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import cell.*;
import field.*;

public class Maps {
	
	//Function : From File "path", converting a text file to a Field Map
	//Param : String 
	//Author : Petite7
	public static Field Map2Field(String path) {
		Field ret = null;
		
		File map = new File(path);
		BufferedReader bin = null;
		
		ArrayList<String> smap = new ArrayList<String>();
		
		try {
			bin = new BufferedReader(new FileReader(map));
			String now = null;
			while((now = bin.readLine()) != null)
				smap.add(now);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		int height = smap.size();
		int width = smap.get(0).length();
		
		ret = new Field(height, width);
		for(int i = 0; i < smap.size(); i ++) {
			String line = smap.get(i);
			for(int j = 0; j < line.length(); j ++) {
				ret.replace(i, j, new Cell(BlockType.values()[line.charAt(j) - '0']));
			}
		}
		
		return ret;
	}
}
