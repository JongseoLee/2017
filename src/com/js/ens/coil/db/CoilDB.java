package com.js.ens.coil.db;

import java.util.ArrayList;

import com.js.ens.coil.core.AppFolder;
import com.js.ens.coil.customWidget.ComboData_selectGraph;
import com.js.ens.coil.customWidget.ComboData_selectImage;
import com.js.ens.coil.customWidget.ComboData_selectTableData;
import com.js.ens.coil.customWidget.TableData_Coil;
import com.js.util.myUtil;

public class CoilDB {
	public static String CONSTANT_TYPE = "Constant";
	public static String FILE_TYPE = "File";
	public static String STEP1 = "STEP1";
	public static String STEP2 = "STEP2";
	public static String STEP3 = "STEP3";
	public static String COIL_GEOMETRY_TYPE_OPEN = "open";
	public static String COIL_GEOMETRY_TYPE_NEW = "new";
	
	// Base 
	private String projectName = "null";
	private String projectFolderPath = "null";
	private String dbFilePath = "null";
	private String CenterBeamNodeStart = "null";
	// Step1. Modeling 
	private String ProductName = "null";
	private String WireDiameter = "null";
	private String CenterDiameter = "null";
	private String InternalDiameter = "null";
	private String ExternalDiameter = "null";
	private String UpperInnerDiameter = "null";
	private String LowerInnerDiameter = "null";
	private String TotalTurns = "null";
	
	private String CoilDesignFilePath = "null";
	private String CoilDesignUserFilePath = "null";
	private ArrayList<TableData_Coil> GeometryDataTableList;
	
	private String HotSettingTemp = "null";
	private String ColdSettingTemp = "null";
	private String HotSettingHeight = "null";
	private String ColdSettingHeight = "null";
	private String SeatUIneerMargina = "null";
	private String SeatLIneerMargina = "null";
	private String SeatHeight = "null";
	
	private String InitialConditionerType = "null";	//constant, file
	private String InitialConditionerConstant = "null";
	private String InitialConditionerFile = "null";
	
	private String MaterialDB = "null";
	
	/*
	private String RadiusConditionerType = "null";	//constant, file
	private String RadiusConditionerConstant = "null";
	private String RadiusConditionerFile = "null";
	private String HeightConditionerType = "null";	//constant, file
	private String HeightConditionerConstant = "null";
	private String HeightConditionerFile = "null";
	// */
	
	
	// Step2. Simulation and Export Result
	private String RadiusTolerance = "null";
	private String HeightTolerance = "null";
	private String MaximumIterationNumber = "null";
	
	
	// Step3. Show result
	private ArrayList<ComboData_selectGraph> graphDataList;
	private ArrayList<ComboData_selectImage> imageDataList;
	private ArrayList<ComboData_selectTableData> tabelDataList;
	private String LogEditor;
	
	public CoilDB() {
		// TODO Auto-generated constructor stub
		this.GeometryDataTableList = new ArrayList<TableData_Coil>();
		this.graphDataList = new ArrayList<ComboData_selectGraph>();
		this.imageDataList = new ArrayList<ComboData_selectImage>();
		this.tabelDataList = new ArrayList<ComboData_selectTableData>();
	}
	
	public void PrintAllData(){
		System.out.println("=======================================================");
		System.out.println("projectName         : "+projectName);
		System.out.println("projectFolderPath   : "+projectFolderPath);
		System.out.println("dbFilePath          : "+dbFilePath);
		System.out.println("CenterBeamNodeStart : "+CenterBeamNodeStart);
		System.out.println("-------------------------------------------------------");
		System.out.println("ProductName         : "+ProductName);
		System.out.println("WireDiameter        : "+WireDiameter);
		System.out.println("CenterDiameter      : "+CenterDiameter);
		System.out.println("InternalDiameter    : "+InternalDiameter);
		System.out.println("ExternalDiameter    : "+ExternalDiameter);
		System.out.println("UpperInnerDiameter  : "+UpperInnerDiameter);
		System.out.println("LowerInnerDiameter  : "+LowerInnerDiameter);
		System.out.println("TotalTurns          : "+TotalTurns);
		System.out.println("-------------------------------------------------------");
		System.out.println("CoilDesignFilePath     : "+CoilDesignFilePath);
		System.out.println("CoilDesignUserFilePath : "+CoilDesignUserFilePath);
		if(this.GeometryDataTableList.size() > 0){
			System.out.println("Geometry Data");
			System.out.println(String.format("%-8s%-8s%-8s", "Theta","Radius", "Height"));
			for(TableData_Coil obj : this.GeometryDataTableList){
				System.out.println(obj.getAllData());
			}
		}
		System.out.println("-------------------------------------------------------");
		System.out.println("HotSettingTemp      : "+HotSettingTemp);
		System.out.println("ColdSettingTemp     : "+ColdSettingTemp);
		System.out.println("HotSettingHeight    : "+HotSettingHeight);
		System.out.println("ColdSettingHeight   : "+ColdSettingHeight);
		System.out.println("SeatUIneerMargina   : "+SeatUIneerMargina);
		System.out.println("SeatLIneerMargina   : "+SeatLIneerMargina);
		System.out.println("SeatHeight          : "+SeatHeight);
		System.out.println("-------------------------------------------------------");
		System.out.println("InitialConditionerType       : "+InitialConditionerType);
		System.out.println("InitialConditionerConstant   : "+InitialConditionerConstant);
		System.out.println("InitialConditionerFile       : "+InitialConditionerFile);
		System.out.println("-------------------------------------------------------");
		System.out.println("MaterialDB           : "+MaterialDB);
		System.out.println("-------------------------------------------------------");
		System.out.println("RadiusTolerance         : "+RadiusTolerance);
		System.out.println("HeightTolerance         : "+HeightTolerance);
		System.out.println("MaximumIterationNumber  : "+MaximumIterationNumber);
		System.out.println("=======================================================");
	}
	
	public void add_CoilDataTable(TableData_Coil obj){
		this.GeometryDataTableList.add(obj);
	}
	
	public void add_GraphDataCombo(ComboData_selectGraph obj){
		this.graphDataList.add(obj);
	}
	
	public void add_ImageDataCombo(ComboData_selectImage obj){
		this.imageDataList.add(obj);
	}
	
	public void add_TableDataCombo(ComboData_selectTableData obj){
		this.tabelDataList.add(obj);
	}
	
	
	
	
	
	
	
	
	public String getProductName() {
		return ProductName;
	}


	public void setProductName(String productName) {
		if(productName.length() == 0){
			ProductName = "null";
		}else{
			ProductName = productName;
		}
		
	}


	public String getWireDiameter() {
		return WireDiameter;
	}


	public void setWireDiameter(String wireDiameter) {
		if(wireDiameter.length() == 0){
			WireDiameter = "null";
		}else{
			WireDiameter = wireDiameter;
		}
	}


	public String getCenterDiameter() {
		return CenterDiameter;
	}


	public void setCenterDiameter(String centerDiameter) {
		if(centerDiameter.length() == 0){
			CenterDiameter = "null";
		}else{
			CenterDiameter = centerDiameter;
		}
	}


	public String getInternalDiameter() {
		return InternalDiameter;
	}


	public void setInternalDiameter(String internalDiameter) {
		if(internalDiameter.length() == 0){
			InternalDiameter = "null";
		}else{
			InternalDiameter = internalDiameter;
		}
	}


	public String getExternalDiameter() {
		return ExternalDiameter;
	}


	public void setExternalDiameter(String externalDiameter) {
		if(externalDiameter.length() == 0){
			ExternalDiameter = "null";
		}else{
			ExternalDiameter = externalDiameter;
		}
	}


	public String getUpperInnerDiameter() {
		return UpperInnerDiameter;
	}


	public void setUpperInnerDiameter(String upperInnerDiameter) {
		if(upperInnerDiameter.length() == 0){
			UpperInnerDiameter = "null";
		}else{
			UpperInnerDiameter = upperInnerDiameter;
		}
	}


	public String getLowerInnerDiameter() {
		return LowerInnerDiameter;
	}


	public void setLowerInnerDiameter(String lowerInnerDiameter) {
		if(lowerInnerDiameter.length() == 0){
			LowerInnerDiameter = "null";
		}else {
			LowerInnerDiameter = lowerInnerDiameter;
		}
	}


	public String getTotalTurns() {
		return TotalTurns;
	}


	public void setTotalTurns(String totalTurns) {
		if(totalTurns.length() == 0){
			TotalTurns = "null";
		}else {
			TotalTurns = totalTurns;
		}
	}



	public ArrayList<TableData_Coil> getGeometryDataTableList() {
		return GeometryDataTableList;
	}


	public void setGeometryDataTableList(ArrayList<TableData_Coil> geometryDataTableList) {
		GeometryDataTableList = geometryDataTableList;
	}

	public ArrayList<ComboData_selectGraph> getGraphDataList() {
		return graphDataList;
	}

	public void setGraphDataList(ArrayList<ComboData_selectGraph> graphDataList) {
		this.graphDataList = graphDataList;
	}

	public ArrayList<ComboData_selectImage> getImageDataList() {
		return imageDataList;
	}

	public void setImageDataList(ArrayList<ComboData_selectImage> imageDataList) {
		this.imageDataList = imageDataList;
	}

	public ArrayList<ComboData_selectTableData> getTabelDataList() {
		return tabelDataList;
	}

	public void setTabelDataList(ArrayList<ComboData_selectTableData> tabelDataList) {
		this.tabelDataList = tabelDataList;
	}

	public String getCoilDesignFilePath() {
		return CoilDesignFilePath;
	}

	public void setCoilDesignFilePath(String coilDesignFilePath) {
		CoilDesignFilePath = coilDesignFilePath;
	}

	public String getCoilDesignUserFilePath() {
		return CoilDesignUserFilePath;
	}

	public void setCoilDesignUserFilePath(String coilDesignUserFilePath) {
		CoilDesignUserFilePath = coilDesignUserFilePath;
	}

	public String getHotSettingTemp() {
		return HotSettingTemp;
	}

	public void setHotSettingTemp(String hotSettingTemp) {
		HotSettingTemp = hotSettingTemp;
	}

	public String getColdSettingTemp() {
		return ColdSettingTemp;
	}

	public void setColdSettingTemp(String coldSettingTemp) {
		ColdSettingTemp = coldSettingTemp;
	}

	public String getHotSettingHeight() {
		return HotSettingHeight;
	}

	public void setHotSettingHeight(String hotSettingHeight) {
		HotSettingHeight = hotSettingHeight;
	}

	public String getColdSettingHeight() {
		return ColdSettingHeight;
	}

	public void setColdSettingHeight(String coldSettingHeight) {
		ColdSettingHeight = coldSettingHeight;
	}

	public String getSeatUIneerMargina() {
		return SeatUIneerMargina;
	}

	public void setSeatUIneerMargina(String seatUIneerMargina) {
		SeatUIneerMargina = seatUIneerMargina;
	}

	public String getSeatLIneerMargina() {
		return SeatLIneerMargina;
	}

	public void setSeatLIneerMargina(String seatLIneerMargina) {
		SeatLIneerMargina = seatLIneerMargina;
	}

	public String getSeatHeight() {
		return SeatHeight;
	}

	public void setSeatHeight(String seatHeight) {
		SeatHeight = seatHeight;
	}

	public String getInitialConditionerType() {
		return InitialConditionerType;
	}

	public void setInitialConditionerType(String initialConditionerType) {
		InitialConditionerType = initialConditionerType;
	}

	public String getInitialConditionerConstant() {
		return InitialConditionerConstant;
	}

	public void setInitialConditionerConstant(String initialConditionerConstant) {
		InitialConditionerConstant = initialConditionerConstant;
	}

	public String getInitialConditionerFile() {
		return InitialConditionerFile;
	}

	public void setInitialConditionerFile(String initialConditionerFile) {
		InitialConditionerFile = initialConditionerFile;
	}
	
	public String getRadiusTolerance() {
		return RadiusTolerance;
	}

	public void setRadiusTolerance(String radiusTolerance) {
		RadiusTolerance = radiusTolerance;
	}

	public String getHeightTolerance() {
		return HeightTolerance;
	}

	public void setHeightTolerance(String heightTolerance) {
		HeightTolerance = heightTolerance;
	}

	public String getMaximumIterationNumber() {
		return MaximumIterationNumber;
	}

	public void setMaximumIterationNumber(String maximumIterationNumber) {
		MaximumIterationNumber = maximumIterationNumber;
	}

	public String getProjectFolderPath() {
		return projectFolderPath;
	}

	public void setProjectFolderPath(String projectFolderPath) {
		this.projectFolderPath = projectFolderPath;
		this.dbFilePath = myUtil.setPath(this.projectFolderPath, AppFolder.dbFileName);
	}

	public String getLogEditor() {
		return LogEditor;
	}

	public void setLogEditor(String logEditor) {
		LogEditor = logEditor;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDbFilePath() {
		return dbFilePath;
	}

	public String getCenterBeamNodeStart() {
		return CenterBeamNodeStart;
	}

	public void setCenterBeamNodeStart(String centerBeamNodeStart) {
		CenterBeamNodeStart = centerBeamNodeStart;
	}

	public String getMaterialDB() {
		return MaterialDB;
	}

	public void setMaterialDB(String materialDB) {
		MaterialDB = materialDB;
	}

}
