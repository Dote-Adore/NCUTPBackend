package com.cyc.publish;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.aliyun.oss.OSS;
import com.cyc.dao.impl.PublishDetailDAOImpl;
import com.cyc.dao.impl.PublishImgDAOImpl;
import com.cyc.dao.impl.UserInfoDetailsDAOImpl;
import com.cyc.entity.PublishDetail;
import com.cyc.utils.AliyunConfig;


public class publishGoods extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		doGet(req, resp);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		System.out.println("用户请求上传");
		boolean isMutipart = ServletFileUpload.isMultipartContent(req);

		if (isMutipart) {// 如果是内含图片的数据
			System.out.println("图片信息");
			try {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 通过parseRequest解析form中的所有请求字段，并保存到items的集合中
				List<FileItem> items = upload.parseRequest(req);
				// 遍历items中的数据
				Iterator<FileItem> iter = items.iterator();
				String picName = null;
				FileItem imgItem = null;
				int userid = -1;
				int publishid = -1;
				int index = -1;
				while (iter.hasNext()) {
					FileItem item = iter.next();
					String itemName = item.getFieldName();
					if (itemName.equals("userid")) {// 根据name属性判断item是什么
						userid = Integer.parseInt(item.getString("utf-8"));
						System.out.println(userid);
					} else if (itemName.equals("publishid")) {
						publishid = Integer.parseInt(item.getString("utf-8"));
						System.out.println(publishid);
					} else if (itemName.equals("index")) {
						index = Integer.parseInt(item.getString("utf-8"));
						System.out.println(publishid);
					} else {// picture，文件上传
							// 文件名getname()
						imgItem = item;
						System.out.println("img");
					}
				}
				picName = "publishedimg_userid=" + userid + "_publishid=" + publishid + "_index=" + System.currentTimeMillis() + ".jpg";
				
				AliyunConfig  AC= new AliyunConfig();
				OSS ossClient = AC.ossClient();
				InputStream inputStream = imgItem.getInputStream();
				
				
				String dateSrc = createDateSrc();
				String picSrc = new String("publishimg"+dateSrc+picName);
				ossClient.putObject(AC.getBucketName(),picSrc,inputStream);
				String picURL = new String(AC.getURL()+"/"+picSrc);
				System.out.println("上传成功!!: "+picURL);
				if(index==0) {//如果是第一张图片，将他设置为主要图片
					PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
					
					PDDI.setMainImg(publishid, picURL);
					System.out.print("成功设置主图片地址！");
				}
				PublishImgDAOImpl PIDI = new PublishImgDAOImpl();
				PIDI.insert(publishid,picURL,index);//保存图片地址
				System.out.println("图片保存到图片列表成功");
				return;

			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		} else {// 如果是纯文本数据
			try {
				int userid = Integer.parseInt(req.getParameter("userid"));
				System.out.println("user:" + userid + " start to publish a goods...");
				
				PublishDetail PD = new PublishDetail();
				PD.setUserid(userid);
				PD.setCategoryid(Integer.parseInt(req.getParameter("category")));
				PD.setCommontags(req.getParameter("commontags"));
				PD.setIntroduction(req.getParameter("introduction"));
				PD.setPrice(Integer.parseInt(req.getParameter("price")));
				PD.setPublishtime(new Timestamp(System.currentTimeMillis()));

				PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();

				int publishid = PDDI.createAPublishinfo(PD);
				out.print(publishid);
				
				int publishNum = PDDI.getNumOfPublish(userid);
				UserInfoDetailsDAOImpl UIDDI = new UserInfoDetailsDAOImpl();
				UIDDI.update(userid, "havepublishednum", publishNum);
				return;
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	public String createDateSrc() {
		Date date  = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd/");
		String dateString = simpleDateFormat.format(date);
		return dateString;
	}
}
