package com.registe;

import java.util.Date;

public class test {

	private final static int MAXSIZE = 16;

	@SuppressWarnings("deprecation")
	public static boolean check(char[] registe_text) {
		char[] char_Time = new char[8];
		int[] int_time = new int[8];
		int k = 0;
		//System.out.println(registe_text);
		for (int i = 0; i < MAXSIZE; i += 4) {
			char_Time[k++] = registe_text[i];
		}
		for (int j = 13; j > 0; j -= 4) {
			char_Time[k++] = registe_text[j];
		}
		for (int t = 0; t < char_Time.length; t++) {
			int_time[t] = char_Time[t] - 'A';
			//System.out.print(int_time[t] + "  ");
		}
		if ((registe_text[MAXSIZE - 1] - 'A') < 0
				|| (registe_text[MAXSIZE - 1] - 'A') > 9) {
			//System.out.println("ÊäÈë´íÎó£¡£¡£¡");
			return false;
		}
		if (int_time[2] < 0 || int_time[2] > 12 || int_time[3] < 0
				|| int_time[3] > 3 || (int_time[3] == 3 && int_time[4] > 1)
				|| int_time[4] < 0 || int_time[4] > 9 || int_time[5] < 0
				|| int_time[5] > 23 || int_time[6] < 0 || int_time[6] > 5
				|| int_time[7] < 0 || int_time[7] > 9) {
			return false;
		}
		Date date_now = new Date();
		long Time = (date_now.getTime() / 1000) - 3600
				* (registe_text[MAXSIZE - 1] - 'A');
		Date date_after = new Date();
		date_after.setTime(Time * 1000);
		Date date_register = new Date();
		date_register.setYear(int_time[0] * 100 + int_time[1] - 1900);
		date_register.setMonth(int_time[2] - 1);
		date_register.setDate(int_time[3] * 10 + int_time[4]);
		date_register.setHours(int_time[5]);
		date_register.setMinutes(int_time[6] * 10 + int_time[7]);
		System.out.println("register:::"+date_register.toGMTString());
		System.out.println("now:::"+date_now.toGMTString());
		System.out.println("after:::"+date_after.toGMTString());
		System.out.println("register:"+date_register.getTime());
		System.out.println("now:"+date_now.getTime());
		System.out.println("after:"+date_after.getTime());
		if (date_register.getTime() > date_after.getTime()
				&& date_register.getTime() < date_now.getTime()) {
			//System.out.println("success!!!!!");
			return true;
		} else {
			//System.out.println("false!!!!");
			return false;
		}

	}

}
