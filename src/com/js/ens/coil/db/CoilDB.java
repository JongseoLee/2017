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
	
	// Base 
	private String projectName = "null";
	private String projectFolderPath = "null";
	private String dbFilePath = "null";
	
	// Step1. Modeling 
	private String ProductName = "null";
	private String WireDiameter = "null";
	private String CenterDiameter = "null";
	private String InternalDiameter = "null";
	private String ExternalDiameter = "null";
	private String UpperInnerDiameter = "null";
	private String LowerInnerDiameter = "null";
	private String TotalTurns = "null";
	
	private String CoilDesingFilePath = "null";
	private String CoilDesingUserFilePath = "null";
	private ArrayList<TableData_Coil> GeometryDataTableList;
	
	private String HotSettingTemp = "null";
	private String ColdSettingTemp = "null";
	private String HotSettingHeight = "null";
	private String ColdSettingHeight = "null";
	private String SeatUIneerMargina = "null";
	private String SeatLIneerMargina = "null";
	private String SeatHeight = "null";
	
	private String RadiusConditionerType = "null";	//constant, file
	private String RadiusConditionerConstant = "null";
	private String RadiusConditionerFile = "null";
	private String HeightConditionerType = "null";	//constant, file
	private String HeightConditionerConstant = "null";
	private String HeightConditionerFile = "null";
	
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
		System.out.println("CoilDesingFilePath     : "+CoilDesingFilePath);
		System.out.println("CoilDesingUserFilePath : "+CoilDesingUserFilePath);
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
		System.out.println("RadiusConditionerType       : "+RadiusConditionerType);
		System.out.println("RadiusConditionerConstant   : "+RadiusConditionerConstant);
		System.out.println("RadiusConditionerFile       : "+RadiusConditionerFile);
		System.out.println("HeightConditionerType       : "+HeightConditionerType);
		System.out.println("HeightConditionerConstant   : "+HeightConditionerConstant);
		System.out.println("HeightConditionerFile       : "+HeightConditionerFile);
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
		ProductName = productName;
	}


	public String getWireDiameter() {
		return WireDiameter;
	}


	public void setWireDiameter(String wireDiameter) {
		WireDiameter = wireDiameter;
	}


	public String getCenterDiameter() {
		return CenterDiameter;
	}


	public void setCenterDiameter(String centerDiameter) {
		CenterDiameter = centerDiameter;
	}


	public String getInternalDiameter() {
		return InternalDiameter;
	}


	public void setInternalDiameter(String internalDiameter) {
		InternalDiameter = internalDiameter;
	}


	public String getExternalDiameter() {
		return ExternalDiameter;
	}


	public void setExternalDiameter(String externalDiameter) {
		ExternalDiameter = externalDiameter;
	}


	public String getUpperInnerDiameter() {
		return UpperInnerDiameter;
	}


	public void setUpperInnerDiameter(String upperInnerDiameter) {
		UpperInnerDiameter = upperInnerDiameter;
	}


	public String getLowerInnerDiameter() {
		return LowerInnerDiameter;
	}


	public void setLowerInnerDiameter(String lowerInnerDiameter) {
		LowerInnerDiameter = lowerInnerDiameter;
	}


	public String getTotalTurns() {
		return TotalTurns;
	}


	public void setTotalTurns(String totalTurns) {
		TotalTurns = totalTurns;
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

	public String getCoilDesingFilePath() {
		return CoilDesingFilePath;
	}

	public void setCoilDesingFilePath(String coilDesingFilePath) {
		CoilDesingFilePath = coilDesingFilePath;
	}

	public String getCoilDesingUserFilePath() {
		return CoilDesingUserFilePath;
	}

	public void setCoilDesingUserFilePath(String coilDesingUserFilePath) {
		CoilDesingUserFilePath = coilDesingUserFilePath;
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

	public String getRadiusConditionerConstant() {
		return RadiusConditionerConstant;
	}

	public void setRadiusConditionerConstant(String radiusConditionerConstant) {
		RadiusConditionerConstant = radiusConditionerConstant;
	}

	public String getRadiusConditionerFile() {
		return RadiusConditionerFile;
	}

	public void setRadiusConditionerFile(String radiusConditionerFile) {
		RadiusConditionerFile = radiusConditionerFile;
	}

	public String getHeightConditionerConstant() {
		return HeightConditionerConstant;
	}

	public void setHeightConditionerConstant(String heightConditionerConstant) {
		HeightConditionerConstant = heightConditionerConstant;
	}

	public String getHeightConditionerFile() {
		return HeightConditionerFile;
	}

	public void setHeightConditionerFile(String heightConditionerFile) {
		HeightConditionerFile = heightConditionerFile;
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

	public String getRadiusConditionerType() {
		return RadiusConditionerType;
	}

	public void setRadiusConditionerType(String radiusConditionerType) {
		RadiusConditionerType = radiusConditionerType;
	}

	public String getHeightConditionerType() {
		return HeightConditionerType;
	}

	public void setHeightConditionerType(String heightConditionerType) {
		HeightConditionerType = heightConditionerType;
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

}
