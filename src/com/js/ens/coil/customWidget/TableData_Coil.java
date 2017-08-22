package com.js.ens.coil.customWidget;

import java.util.ArrayList;

import com.js.util.myUtil;


public class TableData_Coil {
	
	private String x;
	private String y;
	private String Theta = "";
	private String Radius = "";
	private String Height = "";
	
	
	
	public TableData_Coil() {
		// TODO Auto-generated constructor stub
	}
	
	public String getSaveData(){
		return this.x+","+this.y+","+this.Height+","+this.Radius+","+this.Theta;
	}
	
	public void setCoilGeometry(String line){
		ArrayList<String> tokens = new ArrayList<String>();
		tokens = myUtil.splitData(line, ",");
		this.x = tokens.get(0);
		this.y = tokens.get(1);
		this.Height = tokens.get(2);
		this.Radius = tokens.get(3);
		this.Theta = tokens.get(4);
		myUtil.CleareObj(tokens);
	}
	
	public String getAllData(){
		return String.format("%8s%8s%8s", this.Theta,this.Radius,this.Height);
	}
	
	
	public String getTheta() {
		return Theta;
	}

	public void setTheta(String theta) {
		Theta = theta;
	}

	public String getRadius() {
		return Radius;
	}

	public void setRadius(String radius) {
		Radius = radius;
	}

	public String getHeight() {
		return Height;
	}

	public void setHeight(String height) {
		Height = height;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	
}
