package com.eduSol.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.eduSol.base.Comman_Method;

public class PropertyReader {
	
	public static String getPropValue(String key) {
		Properties prop = new Properties();
		File file = new File(Comman_Method.homepath+"\\src\\test\\resources\\Config.properties");
		FileInputStream fis;
		try {
			fis=new FileInputStream(file);
			prop.load(fis);
		}catch(IOException e){
			e.printStackTrace();
		}
		String result=prop.getProperty(key);
		return result;
	}

}
