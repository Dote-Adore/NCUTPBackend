package com.cyc.collect;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyc.dao.impl.CollectDAOImpl;
import com.cyc.dao.impl.PublishDetailDAOImpl;
import com.cyc.dao.impl.UserInfoDAOImpl;
import com.cyc.dao.impl.UserInfoDetailsDAOImpl;
import com.cyc.entity.UserInfo;
import com.cyc.entity.PublishDetail;

public class Collect extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		int userid = 0;
		int publishid = 0;
		String method;
		userid = Integer.parseInt(req.getParameter("userid"));

		method = req.getParameter("method");
		CollectDAOImpl CDI = new CollectDAOImpl();
		try {
			switch (method) {
			case "collect": {
				publishid = Integer.parseInt(req.getParameter("publishid"));
				CDI.collectAGoods(userid, publishid);
				out.print("success");
				updateUserInfoDetails(CDI, userid);
				break;
			}
			case "isCollected" :{
				publishid = Integer.parseInt(req.getParameter("publishid"));
				if(CDI.isCollectioned(userid, publishid))
					out.print(true);
				else 
					out.print(false);
				break;
			}
			case "getAllCollections":{
				List<Integer> collectList = CDI.getAllCollections(userid);
				JSONArray jsonArray = new JSONArray();
				PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
				for(int i = 0;i<collectList.size();i++) {
					JSONObject jsonObj = new JSONObject();
					if(PDDI.getpublDetailsByID(collectList.get(i))==null){//如果没有这个，删除此条记录
						CDI.cancelCollect(userid, collectList.get(i));
						continue;
					}
					PublishDetail pD = PDDI.getpublDetailsByID(collectList.get(i));//通过id获取到发布信息
					jsonObj = pD.toJSON();
					int publishUserid = pD.getUserid();//每个发布的userid
					UserInfoDAOImpl UIDI = new UserInfoDAOImpl();
					UserInfo UI = UIDI.getUserInfobyID(publishUserid);
					jsonObj.put("username", UI.getName());
					jsonObj.put("avatar", UI.getAvatar());
					jsonArray.add(jsonObj);
				}
				
				//重新获取所有的收藏数目
				int collectnum = CDI.getCount(userid);
				UserInfoDetailsDAOImpl UIDDI = new UserInfoDetailsDAOImpl();
				UIDDI.update(userid, "collectionnum", collectnum);
				
				out.print(jsonArray);
				break;
			}
			case "cancelCollect":{
				publishid = Integer.parseInt(req.getParameter("publishid"));
				CDI.cancelCollect(userid, publishid);
				out.print("success");
				updateUserInfoDetails(CDI, userid);
			}
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void updateUserInfoDetails(CollectDAOImpl CDI, int userid) {
		try {
			int count = CDI.getCount(userid);
			UserInfoDetailsDAOImpl UIDDI = new UserInfoDetailsDAOImpl();
			UIDDI.update(userid, "collectionnum", count);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
}
