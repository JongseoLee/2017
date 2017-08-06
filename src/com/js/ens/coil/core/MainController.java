package com.js.ens.coil.core;

import com.js.util.myUtil;

public class MainController {
	private static MainController instance = new MainController();
	public static MainController getInstatnce(){
		return instance;
	}
	
	private String AppPath; 
	public String getAppPath(){
		if(myUtil.checkOS().equals("window")){
			this.AppPath = System.getProperty("user.dir"); 
		}else{
			this.AppPath = "/Users/jslee/CodeFactory/Git/2017/2017";
		}
		return this.AppPath;
	}
	
	private Mediator med = Mediator.getInstance();
	
	
	public MainController(){
		
	}
	
	
}
