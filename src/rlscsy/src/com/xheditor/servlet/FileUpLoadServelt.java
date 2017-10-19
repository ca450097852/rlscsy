package com.xheditor.servlet;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUpLoadServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileUpLoadServelt() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filename = "";
		String type = "";// 文件类型
		String fileTempPath = System.currentTimeMillis() + ""; // 防止文件冲突用毫秒数作为
		String savaPath = request.getParameter("savaPath"); // 不同类型的文件存放在不同的文件夹
		savaPath = savaPath == null ? "defaultUploadPath" : savaPath;
		// 文件路径形如
		// X:/apache-tomcat/webapps/bsp-web/uploadFiles/images/8788888888/my.img
		String filepath = this.getServletContext().getRealPath("")
				+ java.io.File.separator + "uploadFiles"
				+ java.io.File.separator + savaPath + java.io.File.separator
				+ fileTempPath;
		File filepathDir = new File(filepath);
		if (!filepathDir.exists()) {
			filepathDir.mkdirs();
		}

		ServletInputStream in = request.getInputStream();
		byte[] buf = new byte[4048];
		int len = in.readLine(buf, 0, buf.length);
		String f = new String(buf, 0, len - 1);
		while ((len = in.readLine(buf, 0, buf.length)) != -1) {
			filename = new String(buf, 0, len);
		
			int j = filename.lastIndexOf("\"");
			int p = filename.lastIndexOf(".");
			type = filename.substring(p, j);
			filename = System.currentTimeMillis() + type;
			
			System.out.println("3 file path =" + filepath + java.io.File.separator + filename);
			DataOutputStream fileStream = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(filepath
							+ java.io.File.separator + filename)));
			len = in.readLine(buf, 0, buf.length);
			len = in.readLine(buf, 0, buf.length);
			while ((len = in.readLine(buf, 0, buf.length)) != -1) {
				String tempf = new String(buf, 0, len - 1);
				if (tempf.equals(f) || tempf.equals(f + "--")) {
					break;
				} else {
					fileStream.write(buf, 0, len);
				}
			}
			fileStream.close();
		}
		PrintWriter out = response.getWriter();

		// 返回的结果
		String result = savaPath + java.io.File.separator + fileTempPath+ java.io.File.separator + filename;
		request.getSession().setAttribute("appenURl", result);
		out.print("<pre>" + result + "</pre>");
		out.close();
		in.close();
	}
}
