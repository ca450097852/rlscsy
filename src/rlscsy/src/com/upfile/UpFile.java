package com.upfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UpFile {

	public String getFile(InputStream in) {

		FileOutputStream fos = null;
		long fileTempPath = System.currentTimeMillis();
		String name = fileTempPath + ".jpg";
		File file = new File("/www/Tomcat/webapps/ROOT/uploadFiles/", name);
		try {
			int len = 0;
			byte[] buffer = new byte[1024];
			fos = new FileOutputStream(file);

			while ((len = in.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
			}
		}
		return file.getName();
	}
}