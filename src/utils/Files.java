package utils;

import java.io.File;
import java.io.FilenameFilter;

public class Files {

	public static File[] getFiles(String path, String suffix) {
		File[] files = null;
		
		try {
			File dir = new File(path);
			if(dir.isDirectory()) {
				files = dir.listFiles(new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						return new File(dir, name).isFile() && name.endsWith(suffix);
					}
				});
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return files;
	}
	
}
