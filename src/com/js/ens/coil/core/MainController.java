package com.js.ens.coil.core;

public class MainController {
	private static MainController instance = new MainController();
	public static MainController getInstatnce(){
		return instance;
	}
	
	private String AppPath = System.getProperty("user.dir");
	public String getAppPath(){
		return AppPath;
	}
	
	private Mediator med = Mediator.getInstance();
	
	
	public MainController(){
		
	}
	
	
}
