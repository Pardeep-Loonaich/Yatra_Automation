package com.Yatra.Pages;

import com.Yatra.Utils.Utils;

public class ClassMethod {
	
	static Utils utils;
	public static void main(String[] args) {
		
		
		String adult = "2,2";
		String child = "2,2";
		String infant = "2,2";
		
		
		String[] arrayAdult = adult.split(",");
		String[] childAdult = child.split(",");
		String[] InfantAdult = infant.split(",");
		
		int adul = arrayAdult.length;
		System.out.println("");
		
		
			for (int i = 0; i < InfantAdult.length; i++) {			
			
			int iDay = Integer.parseInt(InfantAdult[i]);
			String date = utils.dateGenerator("yyyy_M_d", iDay);
			int month = Integer.parseInt(date.split("_")[1]);
		}
	}

}