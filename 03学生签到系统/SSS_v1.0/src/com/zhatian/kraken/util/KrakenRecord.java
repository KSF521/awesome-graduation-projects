package com.zhatian.kraken.util;

public class KrakenRecord {
	public static final int ENABLE = 1;
	public static final int DISABLE = 0;
	
	private static int flag = 1;
	
	public static void log(String string){
		if (flag == 1) {
			System.out.println(string);
		}else if (flag == 2) {
			
		}
	}
	
	public static void log(int flagIn,String string){
		if (flagIn == 1) {
			System.out.println(string);
		}else if (flag == 2) {
			
		}
	}
	
	public static void setLogEnable(int number){
		KrakenRecord.flag = number;
	}
}
