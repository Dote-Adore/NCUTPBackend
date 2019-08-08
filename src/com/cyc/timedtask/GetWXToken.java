package com.cyc.timedtask;


import java.util.TimerTask;

import javax.servlet.ServletContext;

import com.alibaba.fastjson.JSONObject;
import com.cyc.dao.impl.PublicDataDAOImpl;
//import com.cyc.utils.DButils;
import com.cyc.utils.WX_API;

public class GetWXToken extends TimerTask {
//	private DButils DB = null;
	private ServletContext sc = null;
	private static boolean running = false;
	public GetWXToken(ServletContext sc) {
		this.sc = sc;
	//	DB = new DButils();
	}
	public GetWXToken() {
		
	}
	
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		if(!running) {
			running = true;
			this.sc.log("��ȡ΢��token...");
			toGetToken();
			running = false;
			this.sc.log("��ȡ�ɹ�...");
		}

	}
	
	private void toGetToken() {
		String api = WX_API.getAccessTokenUrl();
		try {
			StringBuilder result = WX_API.getResult(api, "GET");
			JSONObject resultjson = JSONObject.parseObject(result.toString());
			String token = (String)resultjson.get("access_token");
			PublicDataDAOImpl PDDI = new PublicDataDAOImpl();
			PDDI.UpdateToken(token);
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}

}
