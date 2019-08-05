package com.cyc.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class toWxApi {
	
	
	public static StringBuilder getresult(String code) {
		String AppSecretID = "5019ef1cc14bc12794332c03098fce39";
		String AppID = "wx65de80923a5af28b";
		String ApiUrl = "https://api.weixin.qq.com/sns/jscode2session";
		try {
			URL url = new URL(ApiUrl+"?appid="+AppID+"&secret="+AppSecretID+"&js_code="+code+"&&grant_type=authorization_code");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");//设置请求方式
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line = null;
			StringBuilder result = new StringBuilder();
			while((line = br.readLine())!=null) {
				result.append(line+"\n");
			}
			connection.disconnect();
			System.out.print(result);
			return result;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
