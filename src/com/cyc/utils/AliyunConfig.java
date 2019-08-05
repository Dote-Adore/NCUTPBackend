package com.cyc.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
public class AliyunConfig {
	private String BucketName;
	private String URL;
	public AliyunConfig(){
		BucketName = "ncutradingplatform";
		URL = "https://ncutradingplatform.oss-cn-shanghai.aliyuncs.com";
	}
	
	public String getURL() {
		return URL;
	}

	public String getBucketName() {
		return BucketName;
	}

	public OSS ossClient() {
		String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
		String accessKeyId = "LTAIuoRRV0aKSFYk";
		String accessKeySecret = "FBGB60zbXtSrpVfnepmFeVNbybduh9";
		return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
	}
}
