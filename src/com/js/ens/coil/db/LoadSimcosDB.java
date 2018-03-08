package com.js.ens.coil.db;

import java.util.ArrayList;

import com.js.ens.coil.core.MainController;
import com.js.ens.coil.customWidget.TableData_Coil;
import com.js.io.Reader;
import com.js.util.myUtil;

public class LoadSimcosDB {
private MainController MC = MainController.getInstatnce();
	
	// Base 
	public final String Date = "Save Time";
	public final String ProjectName = "ProjectName";
	public final static String ProjectNameKey = "ProjectName";
	public final String ProjectFolderPath="ProjectFolderPath";
	public final String CenterBeamNodeStart="CenterBeamNodeStart";
	// Coil data
	public final String ProductName="ProductName";
	public final String WireDiameter="WireDiameter";
	public final String CenterDiameter="CenterDiameter";
	public final String InternalDiameter="InternalDiameter";
	public final String ExternalDiameter="ExternalDiameter";
	public final String UpperInnerDiameter="UpperInnerDiameter";
	public final String LowerInnerDiameter="LowerInnerDiameter";
	public final String TotalTurns="TotalTurns";
	// coil_design.csv
	public final String CoilDesignFilePath="CoilDesignFilePath";
	public final String CoilDesignUserFilePath="CoilDesignUserFilePath";
	public final String CoilGeometryData_start="CoilGeometryData_start";
	public final String CoilGeometryData_end="CoilGeometryData_end";
	// Setting Process Information
	public final String HotSettingTemp="HotSettingTemp";
	public final String ColdSettingTemp="ColdSettingTemp";
	public final String HotSettingHeight="HotSettingHeight";
	public final String ColdSettingHeight="ColdSettingHeight";
	public final String SeatType="SeatType";
	public final String SeatUIneerMargina="SeatUIneerMargina";
	public final String SeatLIneerMargina="SeatLIneerMargina";
	public final String SeatHeight="SeatHeight";
	public final String SeatUStepRotationHeight = "SeatUStepRotationHeight";
	public final String SeatLStepRotationHeight = "SeatLStepRotationHeight";
	public final String SeatURotationAngle = "SeatURotationAngle";
	public final String SeatLRotationAngle = "SeatLRotationAngle";
	// Initial conditioner
	/*
	public final String InitialConditionerType="InitialConditionerType";
	public final String InitialConditionerConstant="InitialConditionerConstant";
	public final String InitialConditionerFile="InitialConditionerFile";
	//*/
	public final String RadiusConditionerType="RadiusConditionerType";
	public final String RadiusConditionerConstant="RadiusConditionerConstant";
	public final String RadiusConditionerFile="RadiusConditionerFile";
	public final String HeightConditionerType="HeightConditionerType";
	public final String HeightConditionerConstant="HeightConditionerConstant";
	public final String HeightConditionerFile="HeightConditionerFile";
	
	// Material Database
	public final String MaterialDB="MaterialDB";
	// Parallel CPU Number
	public final String ParallelCPUNumber="ParallelCPUNumber";
	// Formed Coil Data Interpolation File
	public final String FormedCoilDataInterpolationFile = "FormedCoilDataInterpolationFile";
	// Simulation Data
	public final String RadiusTolerance="RadiusTolerance";
	public final String HeightTolerance="HeightTolerance";
	public final String MaximumIterationNumber="MaximumIterationNumber"; 
	
	private CoilDB CObj;
	private String DBFilePath;
	private ArrayList<String> dbFiledataList;
	private ArrayList<TableData_Coil> GeometryDataTableList;
	
	
	public LoadSimcosDB() {
		// TODO Auto-generated constructor stub
	}
	
	public void readDBFile(CoilDB obj, String simcosDBFilePath){
		this.dbFiledataList = new ArrayList<String>();
		this.GeometryDataTableList = new ArrayList<TableData_Coil>();
		this.DBFilePath = simcosDBFilePath;
		this.CObj = obj;
		
		// Read db file 
		Reader reader = new Reader(this.DBFilePath);
		reader.running();
		this.dbFiledataList = reader.getFileDataList();
		
		// set db data
		this.parsingData();
		
		
	}
	
	private void parsingData(){
		for(String line : this.dbFiledataList){
			if(line.contains(this.ProjectName)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setProjectName("null");
				}else{
					this.CObj.setProjectName(value);
				}
			}else if(line.contains(this.ProjectFolderPath)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setProjectFolderPath("null");
				}else{
					this.CObj.setProjectFolderPath(value);
				}
			}else if(line.contains(this.CenterBeamNodeStart)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setCenterBeamNodeStart("null");
				}else{
					this.CObj.setCenterBeamNodeStart(value);
				}
			}
			
			else if(line.contains(this.ProductName)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setProductName("null");
				}else{
					this.CObj.setProductName(value);	
				}
				
			}else if(line.contains(this.WireDiameter)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setWireDiameter("null");
				}else{
					this.CObj.setWireDiameter(value);	
				}
				
			}else if(line.contains(this.CenterDiameter)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setCenterDiameter("null");
				}else{
					this.CObj.setCenterDiameter(value);	
				}
				
			}else if(line.contains(this.InternalDiameter)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setInternalDiameter("null");
				}else{
					this.CObj.setInternalDiameter(value);	
				}
				
			}else if(line.contains(this.ExternalDiameter)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setExternalDiameter("null");
				}else{
					this.CObj.setExternalDiameter(value);
				}
				
			}else if(line.contains(this.UpperInnerDiameter)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setUpperInnerDiameter("null");
				}else{
					this.CObj.setUpperInnerDiameter(value);	
				}
				
			}else if(line.contains(this.LowerInnerDiameter)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setLowerInnerDiameter("null");
				}else{
					this.CObj.setLowerInnerDiameter(value);	
				}
				
			}else if(line.contains(this.TotalTurns)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setTotalTurns("null");
				}else{
					this.CObj.setTotalTurns(value);	
				}
				
			}
			
			else if(line.contains(this.CoilDesignFilePath)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setCoilDesignFilePath("null");
				}else{
					this.CObj.setCoilDesignFilePath(value);
				}
				
			}else if(line.contains(this.CoilDesignUserFilePath)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setCoilDesignUserFilePath("null");
				}else{
					this.CObj.setCoilDesignUserFilePath(value);	
				}
				
			}
			
			else if(line.contains(this.HotSettingTemp)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setHotSettingTemp("null");
				}else{
					this.CObj.setHotSettingTemp(value);	
				}
				
			}else if(line.contains(this.ColdSettingTemp)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setColdSettingTemp("null");
				}else{
					this.CObj.setColdSettingTemp(value);
				}
				
			}else if(line.contains(this.HotSettingHeight)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setHotSettingHeight("null");
				}else{
					this.CObj.setHotSettingHeight(value);
				}
				
			}else if(line.contains(this.ColdSettingHeight)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setColdSettingHeight("null");
				}else{
					this.CObj.setColdSettingHeight(value);
				}
				
			}else if(line.contains(this.SeatType)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setSeatType("null");
				}else{
					this.CObj.setSeatType(value);
				}
			}else if(line.contains(this.SeatUIneerMargina)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setSeatUIneerMargina("null");
				}else{
					this.CObj.setSeatUIneerMargina(value);
				}
				
			}else if(line.contains(this.SeatLIneerMargina)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setSeatLIneerMargina("null");
				}else{
					this.CObj.setSeatLIneerMargina(value);	
				}
				
			}else if(line.contains(this.SeatHeight)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setSeatHeight("null");
				}else{
					this.CObj.setSeatHeight(value);
				}
				
			}else if(line.contains(this.SeatUStepRotationHeight)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setSeatUStepRotationHeight("null");
				}else{
					this.CObj.setSeatUStepRotationHeight(value);
				}
			}else if(line.contains(this.SeatLStepRotationHeight)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setSeatLStepRotationHeight("null");
				}else{
					this.CObj.setSeatLStepRotationHeight(value);
				}
			}else if(line.contains(this.SeatURotationAngle)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setSeatURotationAngle("null");
				}else{
					this.CObj.setSeatURotationAngle(value);
				}
			}else if(line.contains(this.SeatLRotationAngle)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setSeatLRotationAngle("null");
				}else{
					this.CObj.setSeatLRotationAngle(value);
				}
			}
			/*
			else if(line.contains(this.InitialConditionerType)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setInitialConditionerType("null");
				}else{
					this.CObj.setInitialConditionerType(value);
				}
				
			}else if(line.contains(this.InitialConditionerConstant)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setInitialConditionerConstant("null");
				}else{
					this.CObj.setInitialConditionerConstant(value);
				}
				
			}else if(line.contains(this.InitialConditionerFile)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setInitialConditionerFile("null");
				}else{
					this.CObj.setInitialConditionerFile(value);
				}
			}
			//*/
			else if(line.contains(this.RadiusConditionerType)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setRadiusConditionerType("null");
				}else{
					this.CObj.setRadiusConditionerType(value);
				}
			}else if(line.contains(this.RadiusConditionerConstant)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setRadiusConditionerConstant("null");
				}else{
					this.CObj.setRadiusConditionerConstant(value);
				}
			}else if(line.contains(this.RadiusConditionerFile)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setRadiusConditionerFile("null");
				}else{
					this.CObj.setRadiusConditionerFile(value);
				}
			}else if(line.contains(this.HeightConditionerType)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setHeightConditionerType("null");
				}else{
					this.CObj.setHeightConditionerType(value);
				}
			}else if(line.contains(this.HeightConditionerConstant)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setHeightConditionerConstant("null");
				}else{
					this.CObj.setHeightConditionerConstant(value);
				}
			}else if(line.contains(this.HeightConditionerFile)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setHeightConditionerFile("null");
				}else{
					this.CObj.setHeightConditionerFile(value);
				}
			}
			
			
			else if(line.contains(this.MaterialDB)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setMaterialDB("null");
				}else{
					this.CObj.setMaterialDB(value);
				}
			}
			
			else if(line.contains(this.ParallelCPUNumber)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setParallerCpuNmber("null");
				}else{
					this.CObj.setParallerCpuNmber(value);
				}
			}
			
			else if(line.contains(this.FormedCoilDataInterpolationFile)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setFormedCoilDataInterpolationFile("null");
				}else{
					this.CObj.setFormedCoilDataInterpolationFile(value);
				}
			}
			
			else if(line.contains(this.RadiusTolerance)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setRadiusTolerance("null");
				}else{
					this.CObj.setRadiusTolerance(value);
				}
				
			}else if(line.contains(this.HeightTolerance)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setHeightTolerance("null");
				}else{
					this.CObj.setHeightTolerance(value);
				}
				
			}else if(line.contains(this.MaximumIterationNumber)){
				String value = myUtil.splitData(line, "=").get(1);
				if(value.contains("%")){
					this.CObj.setMaximumIterationNumber("null");
				}else{
					this.CObj.setMaximumIterationNumber(value);
				}
				
			}
		}
	}
	

}
