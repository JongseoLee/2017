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
	// Step1
	public static String Coil = "Coil";
	private String base_Coil = "Coil";
	public static String SelectCoilData = "SelectCoilData";
	private String base_SelectCoilData = "Select Coil data csv file.";
	public static String CoilData = "CoilData";
	private String base_CoilData = "Coil data";
	public static String ProductName = "ProductName";
	private String base_ProductName = "제품명";
	public static String LineDiameter = "LineDiameter";
	private String base_LineDiameter = "선경";
	public static String CenterDiameter = "CenterDiameter";
	private String base_CenterDiameter = "코일 중심경";
	public static String InnerDiameter = "InnerDiameter";
	private String base_InnerDiameter = "코일 내경";
	public static String OuterDiameter = "OuterDiameter";
	private String base_OuterDiameter = "코일 외경";
	public static String UpperInnerDiameter = "UpperInnerDiameter";
	private String base_UpperInnerDiameter = "어퍼 내경";
	public static String LowerInnerDiameter = "LowerInnerDiameter";
	private String base_LowerInnerDiameter = "로워 내경";
	public static String TotalNumber = "TotalNumber";
	private String base_TotalNumber = "총 권 수";
	public static String CoilGeoDataTable = "CoilGeoDataTable";
	private String base_CoilGeoDataTable = "Coil geometry data table";
	public static String ColumnTheta = "ColumnTheta";
	private String base_ColumnTheta = "Theta";
	public static String ColumnRadius = "ColumnRadius";
	private String base_ColumnRadius = "Radius";
	public static String ColumnHeight = "ColumnHeight";
	private String base_ColumnHeight = "Height";
	//
	//
	////////////////////////////////////////////////////////////////////////////	
	
	public UILabel(){
		this.UILabelMap = new HashMap<String,String>();
		this.BaseUILabelMap = new HashMap<String,String>();
		this.UILabelPath = myUtil.setPath(myUtil.setPath(MC.getAppPath(),"config"),"label.ini");
		this.readUILabel_base();
		this.readUILabelFile();
	}
	
	private void readUILabel_base(){
		this.BaseUILabelMap.put("ProcessStep", this.base_ProcessStep);
		this.BaseUILabelMap.put("Modeling", this.base_Modeling);
		this.BaseUILabelMap.put("SimulationAndExportResult",this.base_SimulationAndExportResult);
		this.BaseUILabelMap.put("ShowResult", this.base_ShowResult);
		this.BaseUILabelMap.put("StepSave", this.base_StepSave);
		
		this.BaseUILabelMap.put("Coil", this.base_Coil);
		this.BaseUILabelMap.put("SelectCoilData", this.base_SelectCoilData);
		this.BaseUILabelMap.put("CoilData", this.base_CoilData);
		this.BaseUILabelMap.put("ProductName",this.base_ProductName);
		this.BaseUILabelMap.put("LineDiameter",this.base_LineDiameter);
		this.BaseUILabelMap.put("CenterDiameter",this.base_CenterDiameter);
		this.BaseUILabelMap.put("InnerDiameter",this.base_InnerDiameter);
		this.BaseUILabelMap.put("OuterDiameter",this.base_OuterDiameter);
		this.BaseUILabelMap.put("UpperInnerDiameter",this.base_UpperInnerDiameter);
		this.BaseUILabelMap.put("LowerInnerDiameter",this.base_LowerInnerDiameter);
		this.BaseUILabelMap.put("TotalNumber",this.base_TotalNumber);
		
		this.BaseUILabelMap.put("CoilGeoDataTable",this.base_CoilGeoDataTable);
		this.BaseUILabelMap.put("ColumnTheta",this.base_ColumnTheta);
		this.BaseUILabelMap.put("ColumnRadius",this.base_ColumnRadius);
		this.BaseUILabelMap.put("ColumnHeight",this.base_ColumnHeight);
		
	}
	
	private void readUILabelFile(){
		try{
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
		}catch(Exception e){
		}
	}
	
	public String getLabel(String key){
		if(this.UILabelMap.containsKey(key)){
			return this.UILabelMap.get(key);
		}else{
			if(this.BaseUILabelMap.containsKey(key)){
				return this.BaseUILabelMap.get(key);
			}
			return "no-label";
		}
	}
	
	
}
