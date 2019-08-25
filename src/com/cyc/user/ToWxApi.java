package com.cyc.user;

import com.cyc.utils.WX_API;

public class ToWxApi {
	
	
	public static StringBuilder getresult(String code) {
		try {
			String api = WX_API.GetOpenIDUrl+"?appid="+WX_API.APPID+"&secret="+WX_API.AppSecretID+"&js_code="+code+"&&grant_type=authorization_code";
			return WX_API.getResult(api, "GET");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
