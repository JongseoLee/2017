package com.js.ens.coil.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.js.io.Reader;
import com.js.util.myUtil;

public class UILabel {
	private static UILabel instance = new UILabel();
	public static UILabel getInstance(){
		return instance;
	}
	
	private MainController MC = MainController.getInstatnce();
	
	private Map<String,String> UILabelMap;
	private Map<String,String> BaseUILabelMap;
	private String UILabelPath;
	
	////////////////////////////////////////////////////////////////////////////
	// Common Label
	//
	// Top composite
	public static String ProcessStep = "ProcessStep";
	private String base_ProcessStep = "Process step";
	public static String Modeling = "Modeling";
	private String base_Modeling = "Modeling";
	public static String SimulationAndExportResult = "SimulationAndExportResult";
	private String base_SimulationAndExportResult = "Simulation and Export Result";
	public static String ShowResult = "ShowResult";
	private String base_ShowResult = "Show result";
	public static String StepSave = "StepSave";
	private String base_StepSave = "Step save";
	
	
	
	//
	//
	////////////////////////////////////////////////////////////////////////////	
	
	
	public UILabel(){
		this.UILabelMap = new HashMap<String,String>();
		this.BaseUILabelMap = new HashMap<String,String>();
		this.UILabelPath = myUtil.setPath(myUtil.setPath(MC.getAppPath(),"config"),"label.ini");
		//System.out.println(this.UILabelPath);
		this.readUILabelFile();
		this.readUILabel_base();
	}
	
	private void readUILabel_base(){
		this.BaseUILabelMap.put(UILabel.ProcessStep, this.base_ProcessStep);
		this.BaseUILabelMap.put(UILabel.Modeling, this.base_Modeling);
		this.BaseUILabelMap.put(UILabel.SimulationAndExportResult,this.base_SimulationAndExportResult);
		this.BaseUILabelMap.put(UILabel.ShowResult, this.base_ShowResult);
		this.BaseUILabelMap.put(UILabel.StepSave, this.base_StepSave);
		
		
		
	}
	
	private void readUILabelFile(){
		Reader reader = new Reader(this.UILabelPath);
		reader.running();
		for(String line : reader.getFileDataList()){
			String data = line.trim();
			if(data.contains("=")){
				ArrayList<String> tokens = new ArrayList<String>();
				tokens = myUtil.splitData(line, "=");
				this.UILabelMap.put(tokens.get(0), tokens.get(1));
			}
		}
	}
	
	public String getLabel(String key){
		if(this.UILabelMap.containsKey(key)){
			return this.UILabelMap.get(key);
		}else{
			return this.BaseUILabelMap.get(key);
		}
	}
	
	
}
