package utils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Loaders {
	
	@SuppressWarnings("deprecation")
	public static void load(String path) {

		File jarFile = new File(path);
	    Method method = null;
	    try {
	        method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
	    } catch (NoSuchMethodException | SecurityException e1) {
	        e1.printStackTrace();
	    }
	    
	    boolean accessible = method.isAccessible();
	    
	    try {
	        method.setAccessible(true);
	        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
	        URL url = jarFile.toURI().toURL();
	        method.invoke(classLoader, url);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        method.setAccessible(accessible);
	    }
	}
	
	public static void main(String[] args) {
		load("usr/PlayerModule.jar");
	}
	
}
