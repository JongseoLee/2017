package com.js.util;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.js.plot.GraphAllData;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		String fileName1 = "FS_1_conditioner.csv";
		String fileName2 = "FS_1_error.csv";
		String fileName3 = "FS_1_form_set_error.csv";
		
		String file = fileName2;
		Pattern p = Pattern.compile("[0-9]+(_error.csv)$");
		Matcher m = p.matcher(file);
		if(m.find()){
			System.out.println("match - " + file);
		}else {
			System.out.println("no match - "+file);
		}
		*/
		/*
		String str = "D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\Config\\GraphLib/exporting.js";
		System.out.println(str);
		System.out.println(str.replaceAll("\\\\", "/"));
		//*/
		
		String fileName = "HGsdf1_err_hist.csv";
		Pattern p = Pattern.compile("("+GraphAllData.maximumErrorType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			System.out.println("Y");
		}else{
			System.out.println("N");
		}
		
		
	}

}
