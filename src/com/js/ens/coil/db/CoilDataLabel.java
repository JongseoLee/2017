package com.js.ens.coil.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.js.ens.coil.core.MainController;
import com.js.io.Reader;
import com.js.util.myUtil;

public class CoilDataLabel {
	public static String ProductName="ProductName";
	public static String LineDiameter="LineDiameter";
	public static String CenterDiameter="CenterDiameter";
	public static String InnerDiameter="InnerDiameter";
	public static String OuterDiameter="OuterDiameter";
	public static String UpperInnerDiameter="UpperInnerDiameter";
	public static String LowerInnerDiameter="LowerInnerDiameter";
	public static String TotalNumber="TotalNumber";
	
	private MainController MC = MainController.getInstatnce();
	
	private Map<String,String> CoilDesignIndexMap;
	private String CoilDesignIndexPath;
	
	public CoilDataLabel(){
		this.CoilDesignIndexMap = new HashMap<String,String>();
		this.CoilDesignIndexPath = myUtil.setPath(myUtil.setPath(MC.getAppPath(),"config"),"coil_design_index.ini");
		System.out.println(this.CoilDesignIndexPath);
		this.readIndexFile();
	}
	
	private void readIndexFile(){
		try{
			Reader reader = new Reader(this.CoilDesignIndexPath);
			reader.running();
			for(String line : reader.getFileDataList()){
				String data = line.trim();
				if(data.contains("=")){
					ArrayList<String> tokens = new ArrayList<String>();
					tokens = myUtil.splitData(line, "=");
					this.CoilDesignIndexMap.put(tokens.get(0), tokens.get(1));
				}
			}
		}catch(Exception e){
		}
		myUtil.printMapData(CoilDesignIndexMap);
	}
	
	public String getLabel(String key){
		if(this.CoilDesignIndexMap.containsKey(key)){
			return this.CoilDesignIndexMap.get(key);
		}else{
			return "no-index";
		}
	}
}
