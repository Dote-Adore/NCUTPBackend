package com.cyc.utils;

import java.sql.Timestamp;

public class TimeDifference {
	public static String Timediff(Timestamp time) { // 得到与当前时间的时间差

		String result;
		long time1 = System.currentTimeMillis();
		long timedifference = time1 - time.getTime();
		int hours = (int) ((timedifference) / (1000 * 60 * 60));
		int minutes = (int) (((timedifference) / 1000 - hours * (60 * 60)) / 60);
		int second = (int) ((timedifference) / 1000 - hours * (60 * 60) - minutes * 60);
		if (hours >= 24) {
			result = (int) hours / 24 + "天前";
			return result;
		}
		if (hours >= 1) {
			result = hours + "小时前";
			return result;
		}
		if (minutes > 1) {
			result = minutes + "分钟前";
			return result;
		} else
			return second + "秒前";

	}
}
