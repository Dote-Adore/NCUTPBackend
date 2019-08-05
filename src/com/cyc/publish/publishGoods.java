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
		// TODO �Զ����ɵķ������
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

		System.out.println("�û������ϴ�");
		boolean isMutipart = ServletFileUpload.isMultipartContent(req);

		if (isMutipart) {// ������ں�ͼƬ������
			System.out.println("ͼƬ��Ϣ");
			try {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				// ͨ��parseRequest����form�е����������ֶΣ������浽items�ļ�����
				List<FileItem> items = upload.parseRequest(req);
				// ����items�е�����
				Iterator<FileItem> iter = items.iterator();
				String picName = null;
				FileItem imgItem = null;
				int userid = -1;
				int publishid = -1;
				int index = -1;
				while (iter.hasNext()) {
					FileItem item = iter.next();
					String itemName = item.getFieldName();
					if (itemName.equals("userid")) {// ����name�����ж�item��ʲô
						userid = Integer.parseInt(item.getString("utf-8"));
						System.out.println(userid);
					} else if (itemName.equals("publishid")) {
						publishid = Integer.parseInt(item.getString("utf-8"));
						System.out.println(publishid);
					} else if (itemName.equals("index")) {
						index = Integer.parseInt(item.getString("utf-8"));
						System.out.println(publishid);
					} else {// picture���ļ��ϴ�
							// �ļ���getname()
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
				System.out.println("�ϴ��ɹ�!!: "+picURL);
				if(index==0) {//����ǵ�һ��ͼƬ����������Ϊ��ҪͼƬ
					PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
					
					PDDI.setMainImg(publishid, picURL);
					System.out.print("�ɹ�������ͼƬ��ַ��");
				}
				PublishImgDAOImpl PIDI = new PublishImgDAOImpl();
				PIDI.insert(publishid,picURL,index);//����ͼƬ��ַ
				System.out.println("ͼƬ���浽ͼƬ�б�ɹ�");
				return;

			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		} else {// ����Ǵ��ı�����
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
				// TODO �Զ����ɵ� catch ��
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
