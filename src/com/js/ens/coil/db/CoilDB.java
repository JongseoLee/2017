package com.js.ens.coil.db;

import java.util.ArrayList;

import com.js.ens.coil.core.AppFolder;
import com.js.ens.coil.customWidget.ComboData_selectGraph;
import com.js.ens.coil.customWidget.ComboData_selectImage;
import com.js.ens.coil.customWidget.ComboData_selectTableData;
import com.js.ens.coil.customWidget.ListData_selectedGraph;
import com.js.ens.coil.customWidget.TableData_Coil;
import com.js.image.ImageAllData;
import com.js.plot.GraphAllData;
import com.js.util.myUtil;

public class CoilDB {
	public static String CONSTANT_TYPE = "Constant";
	public static String FILE_TYPE = "File";
	public static String SEAT_TYPE_STANDARD = "Standard";
	public static String SEAT_TYPE_RRCoil = "RR Coil";
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
	private String SeatType = "null"; // 'Standard', 'RR Coil'
	private String SeatUIneerMargina = "null";
	private String SeatLIneerMargina = "null";
	private String SeatHeight = "null";
	private String SeatUStepRotationHeight = "null";
	private String SeatLStepRotationHeight = "null";
	private String SeatURotationAngle = "null";
	private String SeatLRotationAngle = "null";
	
	
	/*
	private String InitialConditionerType = "null";	
	private String InitialConditionerConstant = "null";
	private String InitialConditionerFile = "null";
	//*/
	
	private String RadiusConditionerType = "null";	//'Constant', 'File'
	private String RadiusConditionerConstant = "null";
	private String RadiusConditionerFile = "null";
	private String HeightConditionerType = "null";	//'Constant', 'File'
	private String HeightConditionerConstant = "null";
	private String HeightConditionerFile = "null";
		
	
	private String MaterialDB = "null";
	
	private String ParallerCpuNmber = "null";
	
	private String FormedCoilDataInterpolationFile = "null";
	
	// Step2. Simulation and Export Result
	private String RadiusTolerance = "null";
	private String HeightTolerance = "null";
	private String MaximumIterationNumber = "null";
	
	
	// Step3. Show result
	private GraphAllData graphAllDataObj;
	private ImageAllData imageAllDataObj;
	private ArrayList<ComboData_selectGraph> graphDataList;
	private ArrayList<ComboData_selectGraph> graphDataList_conditioner;
	private ArrayList<ComboData_selectGraph> graphDataList_error;
	private ArrayList<ComboData_selectGraph> graphDataList_formSetError;
	private ArrayList<ComboData_selectGraph> graphDataList_maximumError;
	private ArrayList<ListData_selectedGraph> selectedGraphList;
	private ArrayList<ComboData_selectImage> imageDataList;
	private ArrayList<ComboData_selectTableData> tabelDataList;
	private String LogEditor;
	
	public CoilDB() {
		// TODO Auto-generated constructor stub
		this.GeometryDataTableList = new ArrayList<TableData_Coil>();
		this.graphDataList = new ArrayList<ComboData_selectGraph>();
		this.graphDataList_conditioner = new ArrayList<ComboData_selectGraph>();
		this.graphDataList_error = new ArrayList<ComboData_selectGraph>();
		this.graphDataList_formSetError = new ArrayList<ComboData_selectGraph>();
		this.graphDataList_maximumError = new ArrayList<ComboData_selectGraph>();
	
		this.selectedGraphList = new ArrayList<ListData_selectedGraph>();
		this.imageDataList = new ArrayList<ComboData_selectImage>();
		this.tabelDataList = new ArrayList<ComboData_selectTableData>();
	}
	
	public boolean isReadySolving(){
		boolean result = false;
		try{
			if(projectName.equals("null")){
				result = false;
				return result;
			}
			if(projectFolderPath.equals("null")){
				result = false;
				return result;
			}
			if(dbFilePath.equals("null")){
				result = false;
				return result;
			}
			if(CenterBeamNodeStart.equals("null")){
				result = false;
				return result;
			}
			// Step1. Modeling 
			if(ProductName.equals("null")){
				result = false;
				return result;
			}
			if(WireDiameter.equals("null")){
				result = false;
				return result;
			}
			if(CenterDiameter.equals("null")){
				result = false;
				return result;
			}
				
		
			if(InternalDiameter.equals("null")){
				result = false;
				return result;
			}
			if(ExternalDiameter.equals("null")){
				result = false;
				return result;
			}
			if(UpperInnerDiameter.equals("null")){
				result = false;
				return result;
			}
			if(LowerInnerDiameter.equals("null")){
				result = false;
				return result;
			}
			if(TotalTurns.equals("null")){
				result = false;
				return result;
			}
			
			if(CoilDesignFilePath.equals("null")){
				result = false;
				return result;
			}
			if(CoilDesignUserFilePath.equals("null")){
				result = false;
				return result;
			}
			if(GeometryDataTableList.size()==0){
				result = false;
				return result;
			}
			
			if(HotSettingTemp.equals("null")){
				result = false;
				return result;
			}
			if(ColdSettingTemp.equals("null")){
				result = false;
				return result;
			}
			if(HotSettingHeight.equals("null")){
				result = false;
				return result;
			}
			if(ColdSettingHeight.equals("null")){
				result = false;
				return result;
			}
			if(SeatType.equals("null")){	
				result = false;
				return result;
			}else if(SeatType.equals(CoilDB.SEAT_TYPE_STANDARD)){
				if(RadiusConditionerConstant.equals("null")){
					result = false;
					return result;
				}
			}else if(RadiusConditionerType.equals(CoilDB.SEAT_TYPE_RRCoil)){
				if(RadiusConditionerFile.equals("null")){
					result = false;
					return result;
				}
			}
			if(SeatUIneerMargina.equals("null")){
				result = false;
				return result;
			}
			if(SeatLIneerMargina.equals("null")){
				result = false;
				return result;
			}
			if(SeatHeight.equals("null")){
				result = false;
				return result;
			}
			if(SeatUStepRotationHeight.equals("null")){
				result = false;
				return result;
			}
			if(SeatLStepRotationHeight.equals("null")){
				result = false;
				return result;
			}
			if(SeatURotationAngle.equals("null")){
				result = false;
				return result;
			}
			if(SeatLRotationAngle.equals("null")){
				result = false;
				return result;
			}
			/*
			if(InitialConditionerType.equals("null")){	
				result = false;
				return result;
			}
			if(InitialConditionerConstant.equals("null")){
				result = false;
				return result;
			}
			if(InitialConditionerFile.equals("null")){
				result = false;
				return result;
			}
			// */
			if(RadiusConditionerType.equals("null")){	
				result = false;
				return result;
			}else if(RadiusConditionerType.equals(CoilDB.CONSTANT_TYPE)){
				if(RadiusConditionerConstant.equals("null")){
					result = false;
					return result;
				}
			}else if(RadiusConditionerType.equals(CoilDB.FILE_TYPE)){
				if(RadiusConditionerFile.equals("null")){
					result = false;
					return result;
				}
			}
			
			
			if(HeightConditionerType.equals("null")){	
				result = false;
				return result;
			}else if(HeightConditionerType.equals(CoilDB.CONSTANT_TYPE)){
				if(HeightConditionerConstant.equals("null")){
					result = false;
					return result;
				}
			}else if(HeightConditionerType.equals(CoilDB.FILE_TYPE)){
				if(HeightConditionerFile.equals("null")){
					result = false;
					return result;
				}
			}
			
			
			if(MaterialDB.equals("null")){
				result = false;
				return result;
			}
			
			if(ParallerCpuNmber.equals("null")){
				result = false;
				return result;
			}
			
			if(FormedCoilDataInterpolationFile.equals("null")){
				result = false;
				return result;
			}
			// Step2. Simulation and Export Result
			if(RadiusTolerance.equals("null")){
				result = false;
				return result;
			}
			if(HeightTolerance.equals("null")){
				result = false;
				return result;
			}
			if(MaximumIterationNumber.equals("null")){
				result = false;
				return result;
			}
			
			result = true;
			return result;
		}catch(Exception e){
			result = false;
		}
		return result;
	}
	public ArrayList<String> getPrintAllData(){
		ArrayList<String> resultList = new ArrayList<String>();
		
		resultList.add("=======================================================");
		resultList.add("projectName         : "+projectName);
		resultList.add("projectFolderPath   : "+projectFolderPath);
		resultList.add("dbFilePath          : "+dbFilePath);
		resultList.add("CenterBeamNodeStart : "+CenterBeamNodeStart);
		resultList.add("-------------------------------------------------------");
		resultList.add("ProductName         : "+ProductName);
		resultList.add("WireDiameter        : "+WireDiameter);
		resultList.add("CenterDiameter      : "+CenterDiameter);
		resultList.add("InternalDiameter    : "+InternalDiameter);
		resultList.add("ExternalDiameter    : "+ExternalDiameter);
		resultList.add("UpperInnerDiameter  : "+UpperInnerDiameter);
		resultList.add("LowerInnerDiameter  : "+LowerInnerDiameter);
		resultList.add("TotalTurns          : "+TotalTurns);
		resultList.add("-------------------------------------------------------");
		resultList.add("CoilDesignFilePath     : "+CoilDesignFilePath);
		resultList.add("CoilDesignUserFilePath : "+CoilDesignUserFilePath);
		if(this.GeometryDataTableList.size() > 0){
			resultList.add("Geometry Data");
			resultList.add(String.format("%-8s%-8s%-8s", "Theta","Radius", "Height"));
			for(TableData_Coil obj : this.GeometryDataTableList){
				resultList.add(obj.getAllData());
			}
		}
		resultList.add("-------------------------------------------------------");
		resultList.add("HotSettingHeight    : "+HotSettingHeight);
		resultList.add("HotSettingTemp      : "+HotSettingTemp);
		resultList.add("ColdSettingHeight   : "+ColdSettingHeight);
		resultList.add("ColdSettingTemp     : "+ColdSettingTemp);
		resultList.add("SeatType            : "+SeatType);
		resultList.add("SeatUIneerMargina   : "+SeatUIneerMargina);
		resultList.add("SeatLIneerMargina   : "+SeatLIneerMargina);
		resultList.add("SeatHeight          : "+SeatHeight);
		if(SeatType.equals(CoilDB.SEAT_TYPE_RRCoil)){
			resultList.add("SeatUStepRotationHeight : "+SeatUStepRotationHeight);
			resultList.add("SeatLStepRotationHeight : "+SeatLStepRotationHeight);
			resultList.add("SeatURotationAngle : "+SeatURotationAngle);
			resultList.add("SeatLRotationAngle : "+SeatLRotationAngle);
		}
		resultList.add("-------------------------------------------------------");
		/*
		resultList.add("InitialConditionerType       : "+InitialConditionerType);
		resultList.add("InitialConditionerConstant   : "+InitialConditionerConstant);
		resultList.add("InitialConditionerFile       : "+InitialConditionerFile);
		// */
		resultList.add("RadiusConditionerType       : "+RadiusConditionerType);
		if(RadiusConditionerType.equals(CoilDB.CONSTANT_TYPE)){
			resultList.add("RadiusConditionerConstant   : "+RadiusConditionerConstant);
		}else if (RadiusConditionerType.equals(CoilDB.FILE_TYPE)){
			resultList.add("RadiusConditionerFile       : "+RadiusConditionerFile);
		}
		resultList.add("HeightConditionerType       : "+HeightConditionerType);
		if(HeightConditionerType.equals(CoilDB.CONSTANT_TYPE)){
			resultList.add("HeightConditionerConstant   : "+HeightConditionerConstant);
		}else if(HeightConditionerType.equals(CoilDB.FILE_TYPE)){
			resultList.add("HeightConditionerFile       : "+HeightConditionerFile);
		}
		resultList.add("-------------------------------------------------------");
		resultList.add("MaterialDB           : "+MaterialDB);
		resultList.add("-------------------------------------------------------");
		resultList.add("ParallelCpuNumber    : "+ParallerCpuNmber);
		resultList.add("-------------------------------------------------------");
		resultList.add("FormedCoilDataInterpolationFile : "+FormedCoilDataInterpolationFile);
		resultList.add("-------------------------------------------------------");
		resultList.add("RadiusTolerance         : "+RadiusTolerance);
		resultList.add("HeightTolerance         : "+HeightTolerance);
		resultList.add("MaximumIterationNumber  : "+MaximumIterationNumber);
		resultList.add("=======================================================");
		
		return resultList;
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
		System.out.println("HotSettingHeight    : "+HotSettingHeight);
		System.out.println("HotSettingTemp      : "+HotSettingTemp);
		System.out.println("ColdSettingHeight   : "+ColdSettingHeight);
		System.out.println("ColdSettingTemp     : "+ColdSettingTemp);
		System.out.println("SeatType            : "+SeatType);
		System.out.println("SeatUIneerMargina   : "+SeatUIneerMargina);
		System.out.println("SeatLIneerMargina   : "+SeatLIneerMargina);
		System.out.println("SeatHeight          : "+SeatHeight);
		System.out.println("SeatUStepRotationHeight : "+SeatUStepRotationHeight);
		System.out.println("SeatLStepRotationHeight : "+SeatLStepRotationHeight);
		System.out.println("SeatURotationAngle : "+SeatURotationAngle);
		System.out.println("SeatLRotationAngle : "+SeatLRotationAngle);
		System.out.println("-------------------------------------------------------");
		/*
		System.out.println("InitialConditionerType       : "+InitialConditionerType);
		System.out.println("InitialConditionerConstant   : "+InitialConditionerConstant);
		System.out.println("InitialConditionerFile       : "+InitialConditionerFile);
		// */
		System.out.println("RadiusConditionerType       : "+RadiusConditionerType);
		System.out.println("RadiusConditionerConstant   : "+RadiusConditionerConstant);
		System.out.println("RadiusConditionerFile       : "+RadiusConditionerFile);
		System.out.println("HeightConditionerType       : "+HeightConditionerType);
		System.out.println("HeightConditionerConstant   : "+HeightConditionerConstant);
		System.out.println("HeightConditionerFile       : "+HeightConditionerFile);
		System.out.println("-------------------------------------------------------");
		System.out.println("MaterialDB           : "+MaterialDB);
		System.out.println("-------------------------------------------------------");
		System.out.println("ParallelCpuNumber    : "+ParallerCpuNmber);
		System.out.println("-------------------------------------------------------");
		System.out.println("FormedCoilDataInterpolationFile : "+FormedCoilDataInterpolationFile);
		System.out.println("-------------------------------------------------------");
		System.out.println("RadiusTolerance         : "+RadiusTolerance);
		System.out.println("HeightTolerance         : "+HeightTolerance);
		System.out.println("MaximumIterationNumber  : "+MaximumIterationNumber);
		System.out.println("=======================================================");
	}
	
	public void add_CoilDataTable(TableData_Coil obj){
		this.GeometryDataTableList.add(obj);
	}
	
	/*
	public void add_GraphDataCombo(ComboData_selectGraph obj){
		this.graphDataList.add(obj);
	}
	//*/
	public void add_GraphDataCombo_conditioner(ComboData_selectGraph obj){
		this.graphDataList_conditioner.add(obj);
	}
	
	public void add_GraphDataCombo_error(ComboData_selectGraph obj){
		this.graphDataList_error.add(obj);
	}
	
	public void add_GraphDataCombo_formSetError(ComboData_selectGraph obj){
		this.graphDataList_formSetError.add(obj);
	}
	
	public void add_GraphDataCombo_maximumError(ComboData_selectGraph obj){
		this.graphDataList_maximumError.add(obj);
	}
	
	public void add_SelectedGraph(ListData_selectedGraph obj){
		this.selectedGraphList.add(obj);
	}
	
	
	public void delete_SelectedGraph(ListData_selectedGraph obj){
		this.selectedGraphList.remove(obj);
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
	
	/*
	public ArrayList<ComboData_selectGraph> getGraphDataList() {
		return graphDataList;
	}

	public void setGraphDataList(ArrayList<ComboData_selectGraph> graphDataList) {
		this.graphDataList = graphDataList;
	}
	//*/
	public ArrayList<ComboData_selectGraph> getGraphDataList_conditioner() {
		return graphDataList_conditioner;
	}

	public void setGraphDataList_conditioner(ArrayList<ComboData_selectGraph> graphDataList) {
		this.graphDataList_conditioner = graphDataList;
	}
	
	public ArrayList<ComboData_selectGraph> getGraphDataList_error() {
		return graphDataList_error;
	}

	public void setGraphDataList_error(ArrayList<ComboData_selectGraph> graphDataList) {
		this.graphDataList_error = graphDataList;
	}
	
	public ArrayList<ComboData_selectGraph> getGraphDataList_formSetError() {
		return graphDataList_formSetError;
	}

	public void setGraphDataList_formSetError(ArrayList<ComboData_selectGraph> graphDataList) {
		this.graphDataList_formSetError = graphDataList;
	}
	
	public ArrayList<ComboData_selectGraph> getGraphDataList_maximumError() {
		return graphDataList_maximumError;
	}

	public void setGraphDataList_maximumError(ArrayList<ComboData_selectGraph> graphDataList) {
		this.graphDataList_maximumError = graphDataList;
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

	public String getSeatType() {
		return SeatType;
	}

	public void setSeatType(String seatType) {
		SeatType = seatType;
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
	
	public String getSeatUStepRotationHeight() {
		return SeatUStepRotationHeight;
	}

	public void setSeatUStepRotationHeight(String seatUStepRotationHeight) {
		SeatUStepRotationHeight = seatUStepRotationHeight;
	}

	public String getSeatLStepRotationHeight() {
		return SeatLStepRotationHeight;
	}

	public void setSeatLStepRotationHeight(String seatLStepRotationHeight) {
		SeatLStepRotationHeight = seatLStepRotationHeight;
	}

	public String getSeatURotationAngle() {
		return SeatURotationAngle;
	}

	public void setSeatURotationAngle(String seatURotationAngle) {
		SeatURotationAngle = seatURotationAngle;
	}

	public String getSeatLRotationAngle() {
		return SeatLRotationAngle;
	}

	public void setSeatLRotationAngle(String seatLRotationAngle) {
		SeatLRotationAngle = seatLRotationAngle;
	}

	/*
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
	// */
	public String getRadiusConditionerType() {
		return RadiusConditionerType;
	}

	public void setRadiusConditionerType(String radiusConditionerType) {
		RadiusConditionerType = radiusConditionerType;
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

	public String getHeightConditionerType() {
		return HeightConditionerType;
	}

	public void setHeightConditionerType(String heightConditionerType) {
		HeightConditionerType = heightConditionerType;
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
		this.dbFilePath = myUtil.setPath(this.projectFolderPath, this.getProjectName()+"_"+AppFolder.dbFileName);
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

	public String getParallerCpuNmber() {
		return ParallerCpuNmber;
	}

	public void setParallerCpuNmber(String parallerCpuNmber) {
		ParallerCpuNmber = parallerCpuNmber;
	}

	
	public String getFormedCoilDataInterpolationFile() {
		return FormedCoilDataInterpolationFile;
	}

	public void setFormedCoilDataInterpolationFile(
			String formedCoilDataInterpolationFile) {
		FormedCoilDataInterpolationFile = formedCoilDataInterpolationFile;
	}

	public GraphAllData getGraphDataObj() {
		return graphAllDataObj;
	}

	public void setGraphAllDataObj(GraphAllData graphAllDataObj) {
		this.graphAllDataObj = graphAllDataObj;
	}

	public ArrayList<ListData_selectedGraph> getSelectedGraphList() {
		return selectedGraphList;
	}

	public void setSelectedGraphList(ArrayList<ListData_selectedGraph> selectedGraphList) {
		this.selectedGraphList = selectedGraphList;
	}

	public ImageAllData getImageAllDataObj() {
		return imageAllDataObj;
	}

	public void setImageAllDataObj(ImageAllData imageAllDataObj) {
		this.imageAllDataObj = imageAllDataObj;
	}

}
