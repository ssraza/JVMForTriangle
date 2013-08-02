package com.gannon.jvm.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtility {

	public static void saveFile(String fileName, String content) {

		File file = new File(fileName);

		// if file doesn't exists, then create it
		if (!file.exists()) {
			FileWriter fw;
			try {
				fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
