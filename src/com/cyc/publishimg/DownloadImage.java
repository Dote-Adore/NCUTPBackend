package com.cyc.publishimg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadImage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		// TODO 自动生成的方法存根
		System.out.println("download");
		resp.setContentType("text/html;charset=UTF-8");
		String imagesrc = req.getParameter("imagesrc");
		resp.setHeader("content-Type", "application/octet-stream");//MIME文件为二进制
		resp.setHeader("content-Disposition", "attachment;filename="+imagesrc);
		
		String osname = System.getProperty("os.name").toLowerCase();
		if(osname.equals("windows 10"))
			imagesrc = imagesrc.replace('/','\\');
		
		File file = new File(imagesrc);
		System.out.println(imagesrc);
		try {
		InputStream in = new FileInputStream(file);
		//InputStream in = 
		ServletOutputStream out = resp.getOutputStream();
		byte[] bs = new byte[10];
		int len =-1;
		while((len=in.read(bs))!=-1) {
			out.write(bs,0,len);
			
		}
		in.close();
		out.close();
		}catch (IOException e) {
			System.out.println("file Not found!");
		}
	}
}
