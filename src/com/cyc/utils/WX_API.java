package com.cyc.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WX_API {
	public static final String APPID = "wx65de80923a5af28b";
	public static final String AppSecretID = "5019ef1cc14bc12794332c03098fce39";
	public static final String GetOpenIDUrl = "https://api.weixin.qq.com/sns/jscode2session";
	public static final String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
	// 违规处罚模板消息
	public static final String violationTemplateMessage = "mnJqUsQeiJ7wwIDMOy2IV3jNvq6ibC00KuiHeksGROY";
	
	public static String getAccessTokenUrl() {
		return getAccessTokenUrl+"?grant_type=client_credential&appid="+APPID+"&secret="+AppSecretID;
	}
	
	
	public static StringBuilder getResult(String api, String method) {
		try {
		URL url = new URL(api);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod(method);//设置请求方式
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		String line = null;
		StringBuilder result = new StringBuilder();
		while((line = br.readLine())!=null) {
			result.append(line+"\n");
		}
		connection.disconnect();
		return result;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
