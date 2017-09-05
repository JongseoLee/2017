package com.js.plot;

import java.awt.Color;
import java.awt.Paint;
import java.awt.color.*;
import java.util.ArrayList;

public class ColorData {
	
	
	private ArrayList<Color> ColorList;
	
	
	public ColorData() {
		// TODO Auto-generated constructor stub
		this.ColorList = new ArrayList<Color>();
		this.initColor();
	}
	
	private void initColor(){
		this.ColorList.add(new Color(0,0,0));
		this.ColorList.add(new Color(255,0,0));
		this.ColorList.add(new Color(0,0,255));
		this.ColorList.add(new Color(0,255,0));
		this.ColorList.add(new Color(255,0,255));
		this.ColorList.add(new Color(255,0,255));
		this.ColorList.add(new Color(128,0,0));
		this.ColorList.add(new Color(0,0,128));
		this.ColorList.add(new Color(0,128,0));
		this.ColorList.add(new Color(128,128,0));
		this.ColorList.add(new Color(128,0,128));
		this.ColorList.add(new Color(0,128,128));
		this.ColorList.add(new Color(153,153,255));
		this.ColorList.add(new Color(153,51,102));
		this.ColorList.add(new Color(255,128,128));
		this.ColorList.add(new Color(0,102,204));
		this.ColorList.add(new Color(102,0,102));
		this.ColorList.add(new Color(204,153,255));
		this.ColorList.add(new Color(255,204,153));
		this.ColorList.add(new Color(51,102,255));
		this.ColorList.add(new Color(255,153,204));
		this.ColorList.add(new Color(255,204,153));
		this.ColorList.add(new Color(255,153,204));
		this.ColorList.add(new Color(153,204,0));
		this.ColorList.add(new Color(153,51,102));
		this.ColorList.add(new Color(51,51,153));
		this.ColorList.add(new Color(0,51,0));
		this.ColorList.add(new Color(102,102,153));
		this.ColorList.add(new Color(153,51,0));
		this.ColorList.add(new Color(0,51,102));
		this.ColorList.add(new Color(255,255,0));
		this.ColorList.add(new Color(192,192,192));
		this.ColorList.add(new Color(128,128,128));
		this.ColorList.add(new Color(255,255,204));
		this.ColorList.add(new Color(204,255,255));
		this.ColorList.add(new Color(204,204,255));
		this.ColorList.add(new Color(0,204,255));
		this.ColorList.add(new Color(204,255,204));
		this.ColorList.add(new Color(255,255,153));
		this.ColorList.add(new Color(153,204,255));
		this.ColorList.add(new Color(255,204,0));
		this.ColorList.add(new Color(255,153,0));
		this.ColorList.add(new Color(255,102,0));
		this.ColorList.add(new Color(150,150,150));
		this.ColorList.add(new Color(51,153,102));
		this.ColorList.add(new Color(51,51,0));
		this.ColorList.add(new Color(51,51,51));
		this.ColorList.add(new Color(255,153,204));
		this.ColorList.add(new Color(255,255,255));
		
		
		
		/*
		//this.ColorList.add(new Color(0,0,0));
		//this.ColorList.add(new Color(255,255,255));
		this.ColorList.add(new Color(255,0,0));
		this.ColorList.add(new Color(0,255,0));
		this.ColorList.add(new Color(0,0,255));			//5
		
		this.ColorList.add(new Color(255,255,0));
		this.ColorList.add(new Color(255,0,255));
		this.ColorList.add(new Color(255,0,255));
		this.ColorList.add(new Color(128,0,0));
		this.ColorList.add(new Color(0,128,0));			//10
		
		this.ColorList.add(new Color(0,0,128));
		this.ColorList.add(new Color(128,128,0));
		this.ColorList.add(new Color(128,0,128));
		this.ColorList.add(new Color(0,128,128));
		this.ColorList.add(new Color(192,192,192));		//15
		
		this.ColorList.add(new Color(128,128,128));
		this.ColorList.add(new Color(153,153,255));
		this.ColorList.add(new Color(153,51,102));
		this.ColorList.add(new Color(255,255,204));
		this.ColorList.add(new Color(204,255,255));		//20
		
		this.ColorList.add(new Color(102,0,102));
		this.ColorList.add(new Color(255,128,128));
		this.ColorList.add(new Color(0,102,204));
		this.ColorList.add(new Color(204,204,255));
		this.ColorList.add(new Color(0,204,255));		//25
		
		this.ColorList.add(new Color(204,255,204));
		this.ColorList.add(new Color(255,255,153));
		this.ColorList.add(new Color(153,204,255));
		this.ColorList.add(new Color(255,153,204));
		this.ColorList.add(new Color(204,153,255));		//30
		
		this.ColorList.add(new Color(255,204,153));
		this.ColorList.add(new Color(51,102,255));
		this.ColorList.add(new Color(255,153,204));
		this.ColorList.add(new Color(51,204,204));
		this.ColorList.add(new Color(153,204,0));		//35
		
		this.ColorList.add(new Color(255,204,0));
		this.ColorList.add(new Color(255,153,0));
		this.ColorList.add(new Color(255,102,0));
		this.ColorList.add(new Color(102,102,153));
		this.ColorList.add(new Color(150,150,150));		//40
		
		this.ColorList.add(new Color(0,51,102));
		this.ColorList.add(new Color(51,153,102));
		this.ColorList.add(new Color(0,51,0));
		this.ColorList.add(new Color(51,51,0));
		this.ColorList.add(new Color(153,51,0));		//45
		
		this.ColorList.add(new Color(153,51,102));
		this.ColorList.add(new Color(51,51,153));
		this.ColorList.add(new Color(51,51,51));		//48
		//*/
	}

	public ArrayList<Color> getColorList() {
		return ColorList;
	}

}
