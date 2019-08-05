package com.cyc.publish;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.cyc.dao.impl.CommentsDAOImpl;
import com.cyc.dao.impl.PublishDetailDAOImpl;
import com.cyc.dao.impl.PublishImgDAOImpl;
import com.cyc.dao.impl.UserInfoDetailsDAOImpl;
import com.cyc.entity.PublishImg;
import com.cyc.utils.AliyunConfig;

public class DeletePublish extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ɾ��������Ϣ");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		int publishid = Integer.parseInt(req.getParameter("publishid"));
		int userid = Integer.parseInt(req.getParameter("userid"));
		try {
			
			//ɾ����¼
			PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
			PDDI.delete(publishid);
			
			//ɾ��ͼƬ��¼
			PublishImgDAOImpl PIDI = new PublishImgDAOImpl();
			List<PublishImg> publishImgs = PIDI.selectAll(publishid);
			List<String> imgList = new ArrayList<String>();
			for (int i= 0;i<publishImgs.size();i++)
				imgList.add(publishImgs.get(i).getSrc());
			PIDI.deleteAll(publishid);
			
			//ɾ��������Ϣ
			CommentsDAOImpl CDI = new CommentsDAOImpl();
			CDI.deletebypublishid(publishid);
			
			
			//ɾ��ͼƬ�ļ�
			AliyunConfig AC = new AliyunConfig();
			OSS ossClient = AC.ossClient();
			String imgurl = null;
			List<String> imgurlList = new ArrayList<String>();
			if(imgList.size()>1) {
			for(int i = 0;i<imgList.size();i++) {
				imgurl = imgList.get(i).substring(AC.getURL().length()+1);
				imgurlList.add(imgurl);
				System.out.println("imgurl:"+imgurl);
			}
			ossClient.deleteObjects(new DeleteObjectsRequest(AC.getBucketName()).withKeys(imgurlList));
			}else {
				imgurl  = imgList.get(0).substring(AC.getURL().length()+1);//��ȡ��ͼƬ��oss�е�����
				ossClient.deleteObject(AC.getBucketName(), imgurl);//ɾ��
			}
			ossClient.shutdown();
			
			
			//�����û���������
			UserInfoDetailsDAOImpl UIDI = new UserInfoDetailsDAOImpl();
			int publishnum = PDDI.getNumOfPublish(userid);
			UIDI.update(userid, "havepublishednum", publishnum);
			
			out.print("successDelete");
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}

	}
}
