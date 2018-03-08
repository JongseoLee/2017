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
	//private String base_ProductName = "제품명";
	private String base_ProductName = "Product Name";
	public static String WireDiameter = "WireDiameter";
	//private String base_WireDiameter = "선경(mm)";
	private String base_WireDiameter = "Wire Diameter";
	public static String CenterDiameter = "CenterDiameter";
	//private String base_CenterDiameter = "코일중심경(mm)";
	private String base_CenterDiameter = "Center Diameter(mm)";
	public static String InternalDiameter = "InternalDiameter";
	//private String base_InternalDiameter = "코일 내경(mm)";
	private String base_InternalDiameter = "Internal Diameter(mm)";
	public static String ExternalDiameter = "ExternalDiameter";
	//private String base_ExternalDiameter = "코일 외경(mm)";
	private String base_ExternalDiameter = "External Diameter(mm)";
	public static String UpperInnerDiameter = "UpperInnerDiameter";
	//private String base_UpperInnerDiameter = "어퍼 내경(mm)";
	private String base_UpperInnerDiameter = "U. Inner Diameter(mm)";
	public static String LowerInnerDiameter = "LowerInnerDiameter";
	//private String base_LowerInnerDiameter = "로워 내경(mm)";
	private String base_LowerInnerDiameter = "L. Inner Diameter(mm)";
	public static String TotalTurns = "TotalTurns";
	//private String base_TotalTurns = "총권수";
	private String base_TotalTurns = "Total Turns";
	public static String CoilGeoDataTable = "CoilGeoDataTable";
	private String base_CoilGeoDataTable = "Coil geometry data table";
	public static String ColumnTheta = "ColumnTheta";
	private String base_ColumnTheta = "Theta";
	public static String ColumnRadius = "ColumnRadius";
	private String base_ColumnRadius = "Radius";
	public static String ColumnHeight = "ColumnHeight";
	private String base_ColumnHeight = "Pitch";
	
	public static String ShowRadiusGraph = "ShowRadiusGraph";
	private String base_ShowRadiusGraph = "Show Radius Graph";
	public static String ShowPitchGraph = "ShowPitchGraph";
	private String base_ShowPitchGraph = "Show Pitch Graph";
	
	public static String SettingProcessInformation = "SettingProcessInformation";
	private String base_SettingProcessInformation = "Setting Process Information";
	public static String HotSettingTemp = "HotSettingTemp";
	private String base_HotSettingTemp = "Hot Setting Temp.(C)";
	public static String ColdSettingTemp = "ColdSettingTemp";
	private String base_ColdSettingTemp = "Cold Setting Temp.(C)";
	public static String HotSettingHeight = "HotSettingHeight";
	private String base_HotSettingHeight = "Hot Setting Height(mm)";
	public static String ColdSettingHeight = "ColdSettingHeight";
	private String base_ColdSettingHeight = "Cold Setting Height(mm)";
	
	public static String Standard = "Standard";
	private String base_Standard = "Starndard";
	public static String RRCoil = "RRCoil";
	private String base_RRCoil = "RR Coil";
	
	public static String SeatUInnerMargina = "SeatUInnerMargina";
	private String base_SeatUInnerMargina = "Seat U. Inner Margina(mm)";
	public static String SeatLInnerMargina = "SeatLInnerMargina";
	private String base_SeatLInnerMargina = "Seat L. Inner Margina(mm)";
	public static String SeatHeight = "SeatHeight";
	private String base_SeatHeight = "Seat Height(mm)";
	
	
	public static String SeatUStepRotationHeight = "SeatUStepRotationHeight";
	private String base_SeatUStepRotationHeight = "Seat U. Step Rotation Height(mm)";
	public static String SeatLStepRotationHeight = "SeatLStepRotationHeight";
	private String base_SeatLStepRotationHeight = "Seat L. Step Rotation Height(mm)";
	public static String SeatURotationAngle = "SeatURotationAngle";
	private String base_SeatURotationAngle = "Seat U. Rotation Angle(Turn)";
	public static String SeatLRotationAngle = "SeatLRotationAngle";
	private String base_SeatLRotationAngle = "Seat L. Rotation Angle(Turn)";

	
	
	public static String InitialConditioner = "InitialConditioner";
	private String base_InitialConditioner = "Initial conditioner";
	public static String RadiusConditioner = "RadiusConditioner";
	public String base_RadiusConditioner = "Radius Conditioner(%)";
	public static String HeightConditioner = "HeightConditioner";
	public String base_HeightConditioner = "Height Conditioner(%)";
	public static String Constant = "Constant";
	private String base_Constant = "Constant";
	public static String File = "File";
	private String base_File ="File";
	
	public static String MaterialDatabase = "MaterialDatabase";
	private String base_MaterialDatabase="Material Database";

	public static String ParallelCPUNumber = "ParallelCPUNumber";
	private String base_ParallelCPUNumber = "Parallel CPU Number";
	
	public static String FormedCoilDataInterpolationFile = "FormedCoilDataInterpolationFile";
	private String base_FormedCoilDataInterpolationFile = "Select Formed Coil DataInterpolation File(CSV)";
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
	public static String SimulationStatus = "SimulationStatus";
	private String base_SimulationStatus = "Ready";
	public static String StartSimulation = "StartSimulation";
	private String base_StartSimulation = "Start Simulation";
	public static String ResetSimulation = "ResetSimulation";
	private String base_ResetSimulation = "Reset";
	public static String CheckStatus = "CheckStatus";
	private String base_CheckStatus = "Check Status";
	public static String ReadLog = "ReadLog";
	private String base_ReadLog = "Read Log";	
	
	// Step3
	public static String DisplayResultCoilData = "DisplayResultCoilData";
	private String base_DisplayResultCoilData = "Display Result Coil Data";
	public static String Conditioner ="Conditioner";
	private String base_Conditioner = "Conditioner";
	public static String Error = "Error";
	private String base_Error = "Error";
	public static String FormSetError = "FormSetError";
	private String base_FormSetError = "Form set Error";
	public static String MaximumError = "MaximumError";
	private String base_MaximumError = "Maximum error";
	public static String Radius = "Radius";
	private String base_Radius = "Radius";
	public static String Height = "Height";
	private String base_Height = "Height";
	public static String SelectGraph = "SelectGraph";
	private String base_SelectGraph = "Select Graph";
	public static String AddGraph = "AddGraph";
	private String base_AddGraph = "Add Graph";
	public static String DeleteGraph ="DeleteGraph";
	private String base_DeleteGraph ="Delete Graph";
	public static String ShowPopupWindow_1 = "ShowPopupWindow_1";
	private String base_ShowPopupWindow_1 = "Show Graph";
	public static String SelectImage = "SelectImage";
	private String base_SelectImage = "Select Image";	
	public static String ShowPopupWindow_2 = "ShowPopupWindow_2";
	private String base_ShowPopupWindow_2 = "Show Image";
	public static String SelectTableData = "SelectTableData";
	private String base_SelectTableData = "Select table Data";
	public static String Show = "Show";
	private String base_Show = "Show";
	
	//
	//
	////////////////////////////////////////////////////////////////////////////	
	
	public UILabel(){
		this.UILabelMap = new HashMap<String,String>();
		this.BaseUILabelMap = new HashMap<String,String>();
		this.UILabelPath = myUtil.setPath(myUtil.setPath(MC.getAppPath(),AppFolder.CONFIG),AppFolder.LabelFile);
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
		this.BaseUILabelMap.put("WireDiameter",this.base_WireDiameter);
		this.BaseUILabelMap.put("CenterDiameter",this.base_CenterDiameter);
		this.BaseUILabelMap.put("InternalDiameter",this.base_InternalDiameter);
		this.BaseUILabelMap.put("ExternalDiameter",this.base_ExternalDiameter);
		this.BaseUILabelMap.put("UpperInnerDiameter",this.base_UpperInnerDiameter);
		this.BaseUILabelMap.put("LowerInnerDiameter",this.base_LowerInnerDiameter);
		this.BaseUILabelMap.put("TotalTurns",this.base_TotalTurns);
		
		this.BaseUILabelMap.put("CoilGeoDataTable",this.base_CoilGeoDataTable);
		this.BaseUILabelMap.put("ColumnTheta",this.base_ColumnTheta);
		this.BaseUILabelMap.put("ColumnRadius",this.base_ColumnRadius);
		this.BaseUILabelMap.put("ColumnHeight",this.base_ColumnHeight);
		this.BaseUILabelMap.put("ShowRadiusGraph",this.base_ShowRadiusGraph);
		this.BaseUILabelMap.put("ShowPitchGraph", this.base_ShowPitchGraph);
		
		this.BaseUILabelMap.put("SettingProcessInformation", this.base_SettingProcessInformation);
		this.BaseUILabelMap.put("HotSettingTemp", this.base_HotSettingTemp);
		this.BaseUILabelMap.put("ColdSettingTemp", this.base_ColdSettingTemp);
		this.BaseUILabelMap.put("HotSettingHeight", this.base_HotSettingHeight);
		this.BaseUILabelMap.put("ColdSettingHeight", this.base_ColdSettingHeight);
		this.BaseUILabelMap.put("Standard", this.base_Standard);
		this.BaseUILabelMap.put("RRCoil", this.base_RRCoil);
		this.BaseUILabelMap.put("SeatUInnerMargina", this.base_SeatUInnerMargina);
		this.BaseUILabelMap.put("SeatLInnerMargina", this.base_SeatLInnerMargina);
		this.BaseUILabelMap.put("SeatHeight", this.base_SeatHeight);
		this.BaseUILabelMap.put("SeatUStepRotationHeight", this.base_SeatUStepRotationHeight);
		this.BaseUILabelMap.put("SeatLStepRotationHeight", this.base_SeatLStepRotationHeight);
		this.BaseUILabelMap.put("SeatURotationAngle", this.base_SeatURotationAngle);
		this.BaseUILabelMap.put("SeatLRotationAngle", this.base_SeatLRotationAngle);
	
		this.BaseUILabelMap.put("InitialConditioner", this.base_InitialConditioner);
		this.BaseUILabelMap.put("RadiusConditioner", this.base_RadiusConditioner);
		this.BaseUILabelMap.put("HeightConditioner", this.base_HeightConditioner);
		this.BaseUILabelMap.put("Constant", this.base_Constant);
		this.BaseUILabelMap.put("File",this.base_File);

		this.BaseUILabelMap.put("MaterialDatabase", this.base_MaterialDatabase);
		
		this.BaseUILabelMap.put("ParallelCPUNumber", this.base_ParallelCPUNumber);
		
		this.BaseUILabelMap.put("FormedCoilDataInterpolationFile", this.base_FormedCoilDataInterpolationFile);
		
		this.BaseUILabelMap.put("AnalysisOptions", this.base_AnalysisOptions);
		this.BaseUILabelMap.put("RadiusTolerance", this.base_RadiusTolerance);
		this.BaseUILabelMap.put("HeightTolerance", this.base_HeightTolerance);
		this.BaseUILabelMap.put("MaximumIterationNumber", this.base_MaximumIterationNumber);
		this.BaseUILabelMap.put("SimulationIteration", this.base_SimulationIteration);
		this.BaseUILabelMap.put("SimulationStatus", this.base_SimulationStatus);
		this.BaseUILabelMap.put("StartSimulation", this.base_StartSimulation);
		this.BaseUILabelMap.put("ResetSimulation",this.base_ResetSimulation);
		this.BaseUILabelMap.put("CheckStatus", this.base_CheckStatus);
		this.BaseUILabelMap.put("ReadLog", this.base_ReadLog);
		
		this.BaseUILabelMap.put("DisplayResultCoilData", this.base_DisplayResultCoilData);
		this.BaseUILabelMap.put("Conditioner", this.base_Conditioner);
		this.BaseUILabelMap.put("Error", this.base_Error);
		this.BaseUILabelMap.put("FormSetError", this.base_FormSetError);
		this.BaseUILabelMap.put("MaximumError", this.base_MaximumError);
		this.BaseUILabelMap.put("Radius", this.base_Radius);
		this.BaseUILabelMap.put("Height", this.base_Height);
		this.BaseUILabelMap.put("SelectGraph", this.base_SelectGraph);
		this.BaseUILabelMap.put("AddGraph", this.base_AddGraph);
		this.BaseUILabelMap.put("DeleteGraph", this.base_DeleteGraph);
		this.BaseUILabelMap.put("ShowPopupWindow_1", this.base_ShowPopupWindow_1);
		this.BaseUILabelMap.put("SelectImage", this.base_SelectImage);
		this.BaseUILabelMap.put("ShowPopupWindow_2", this.base_ShowPopupWindow_2);
		this.BaseUILabelMap.put("SelectTableData", this.base_SelectTableData);
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
