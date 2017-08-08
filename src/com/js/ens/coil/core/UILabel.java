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
	private String base_ProductName = "��ǰ��";
	public static String LineDiameter = "LineDiameter";
	private String base_LineDiameter = "����";
	public static String CenterDiameter = "CenterDiameter";
	private String base_CenterDiameter = "�����߽ɰ�(mm)";
	public static String InnerDiameter = "InnerDiameter";
	private String base_InnerDiameter = "���� ����(mm)";
	public static String OuterDiameter = "OuterDiameter";
	private String base_OuterDiameter = "���� �ܰ�(mm)";
	public static String UpperInnerDiameter = "UpperInnerDiameter";
	private String base_UpperInnerDiameter = "���� ����(mm)";
	public static String LowerInnerDiameter = "LowerInnerDiameter";
	private String base_LowerInnerDiameter = "�ο� ����(mm)";
	public static String TotalNumber = "TotalNumber";
	private String base_TotalNumber = "�ѱǼ�";
	public static String CoilGeoDataTable = "CoilGeoDataTable";
	private String base_CoilGeoDataTable = "Coil geometry data table";
	public static String ColumnTheta = "ColumnTheta";
	private String base_ColumnTheta = "Theta";
	public static String ColumnRadius = "ColumnRadius";
	private String base_ColumnRadius = "Radius";
	public static String ColumnHeight = "ColumnHeight";
	private String base_ColumnHeight = "Height";
	
	public static String SettingProcessInformation = "SettingProcessInformation";
	private String base_SettingProcessInformation = "Setting Process Information";
	public static String HotSettingTemp = "HotSettingTemp";
	private String base_HotSettingTemp = "Hot Setting Temp.(C)";
	public static String ColdSettingTemp = "ColdSettingTemp";
	private String base_ColdSettingTemp = "Cold Setting Temp.(C)";
	public static String HotSettingStrok = "HotSettingStrok";
	private String base_HotSettingStrok = "Hot Setting Strok(C)";
	public static String ColdSettingStrok = "ColdSettingStrok";
	private String base_ColdSettingStrok = "Cold Setting Strok(C)";
	public static String SeatUInnerMargina = "SeatUInnerMargina";
	private String base_SeatUInnerMargina = "Seat U. Inner Margina(mm)";
	public static String SeatLInnerMargina = "SeatLInnerMargina";
	private String base_SeatLInnerMargina = "Seat L. Inner Margina(mm)";
	public static String SeatHeight = "SeatHeight";
	private String base_SeatHeight = "Seat Height(mm)";
	
	public static String InitialConditioner = "InitialConditioner";
	private String base_InitialConditioner = "Initial conditioner";
	public static String RadiusConditioner = "RadiusConditioner";
	private String base_RadiusConditioner = "Radius Conditioner(%)";
	public static String Constant = "Constant";
	private String base_Constant = "Constant";
	public static String File = "File";
	private String base_File ="File";
	public static String HeightConditioner = "HeightConditioner";
	private String base_HeightConditioner = "Height Conditioner(%)";
	
	// Step2
	public static String AnalysisOptions = "AnalysisOptions";
	private String base_AnalysisOptions = "Analysis Options";
	public static String RadiusTolerance = "RadiusTolerance";
	private String base_RadiusTolerance = "Radius Tolerance(mm)";
	public static String HeightTolerance = "HeightTolerance";
	private String base_HeightTolerance = "Height Tolerance(mm)";
	public static String MaximumIterationNumber = "MaximumIterationNumber";
	private String base_MaximumIterationNumber = "Maximum Iteration Number";
	public static String SimulationIteration = "SimulationIteration";
	private String base_SimulationIteration = "Simulation Iteration";
	public static String StartSimulation = "StartSimulation";
	private String base_StartSimulation = "Start Simulation";
	public static String CheckError = "CheckError";
	private String base_CheckError = "Check Error";
	public static String ReadLog = "ReadLog";
	private String base_ReadLog = "Read Log";	
	
	// Step3
	public static String DisplayResultCoilData = "DisplayResultCoilData";
	private String base_DisplayResultCoilData = "Display Result Coil Data";
	public static String SelectGraph = "SelectGraph";
	private String base_SelectGraph = "Select Graph";
	public static String ShowPopupWindow_1 = "ShowPopupWindow_1";
	private String base_ShowPopupWindow_1 = "Show popup window";
	public static String SelectImage = "SelectImage";
	private String base_SelectImage = "Select Image";	
	public static String ShowPopupWindow_2 = "ShowPopupWindow_2";
	private String base_ShowPopupWindow_2 = "Show popup window";
	public static String ShowTableData = "ShowTableData";
	private String base_ShowTableData = "Show table Data";
	public static String Show = "Show";
	private String base_Show = "Show";
	
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
		
		this.BaseUILabelMap.put("SettingProcessInformation", this.base_SettingProcessInformation);
		this.BaseUILabelMap.put("HotSettingTemp", this.base_HotSettingTemp);
		this.BaseUILabelMap.put("ColdSettingTemp", this.base_ColdSettingTemp);
		this.BaseUILabelMap.put("HotSettingStrok", this.base_HotSettingStrok);
		this.BaseUILabelMap.put("ColdSettingStrok", this.base_ColdSettingStrok);
		this.BaseUILabelMap.put("SeatUInnerMargina", this.base_SeatUInnerMargina);
		this.BaseUILabelMap.put("SeatLInnerMargina", this.base_SeatLInnerMargina);
		this.BaseUILabelMap.put("SeatHeight", this.base_SeatHeight);
	
		this.BaseUILabelMap.put("InitialConditioner", this.base_InitialConditioner);
		this.BaseUILabelMap.put("RadiusConditioner", this.base_RadiusConditioner);
		this.BaseUILabelMap.put("Constant", this.base_Constant);
		this.BaseUILabelMap.put("File",this.base_File);
		this.BaseUILabelMap.put("HeightConditioner", this.base_HeightConditioner);
		
		this.BaseUILabelMap.put("AnalysisOptions", this.base_AnalysisOptions);
		this.BaseUILabelMap.put("RadiusTolerance", this.base_RadiusTolerance);
		this.BaseUILabelMap.put("HeightTolerance", this.base_HeightTolerance);
		this.BaseUILabelMap.put("MaximumIterationNumber", this.base_MaximumIterationNumber);
		this.BaseUILabelMap.put("SimulationIteration", this.base_SimulationIteration);
		this.BaseUILabelMap.put("StartSimulation", this.base_StartSimulation);
		this.BaseUILabelMap.put("CheckError", this.base_CheckError);
		this.BaseUILabelMap.put("ReadLog", this.base_ReadLog);
		
		this.BaseUILabelMap.put("DisplayResultCoilData", this.base_DisplayResultCoilData);
		this.BaseUILabelMap.put("SelectGraph", this.base_SelectGraph);
		this.BaseUILabelMap.put("ShowPopupWindow_1", this.base_ShowPopupWindow_1);
		this.BaseUILabelMap.put("SelectImage", this.base_SelectImage);
		this.BaseUILabelMap.put("ShowPopupWindow_2", this.base_ShowPopupWindow_2);
		this.BaseUILabelMap.put("ShowTableData", this.base_ShowTableData);
		this.BaseUILabelMap.put("Show", this.base_Show);
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
