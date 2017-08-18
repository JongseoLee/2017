package com.js.ens.coil.db;

import java.util.ArrayList;

import com.js.ens.coil.core.AppFolder;
import com.js.ens.coil.core.MainController;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class WriteSimcosDB {
	private MainController MC = MainController.getInstatnce();
	
	// Base 
	private final String Date = "%date%";
	private final String ProjectName = "%projectName%";
	private final String ProjectFolderPath="%projectFolderPath%";
	// Coil data
	private final String ProductName="%ProductName%";
	private final String LineDiameter="%LineDiameter%";
	private final String CenterDiameter="%CenterDiameter%";
	private final String InnerDiameter="%InnerDiameter%";
	private final String OuterDiameter="%OuterDiameter%";
	private final String UpperInnerDiameter="%UpperInnerDiameter%";
	private final String LowerInnerDiameter="%LowerInnerDiameter%";
	private final String TotalNumber="%TotalNumber%";
	// coil_design.csv
	private final String CoilDesingFilePath="%CoilDesingFilePath%";
	private final String CoilDesingUserFilePath="%CoilDesingUserFilePath%";
	// Setting Process Information
	private final String HotSettingTemp="%HotSettingTemp%";
	private final String ColdSettingTemp="%ColdSettingTemp%";
	private final String HotSettingHeight="%HotSettingHeight%";
	private final String ColdSettingHeight="%ColdSettingHeight%";
	private final String SeatUIneerMargina="%SeatUIneerMargina%";
	private final String SeatLIneerMargina="%SeatLIneerMargina%";
	private final String SeatHeight="%SeatHeight%";
	// Initial conditioner
	private final String RadiusConditionerType="%RadiusConditionerType%";
	private final String RadiusConditionerConstant="%RadiusConditionerConstant%";
	private final String RadiusConditionerFile="%RadiusConditionerFile%";
	private final String HeightConditionerType="%HeightConditionerType%";
	private final String HeightConditionerConstant="%HeightConditionerConstant%";
	private final String HeightConditionerFile="%HeightConditionerFile%";
	// Simulation Data
	private final String RadiusTolerance="%RadiusTolerance%";
	private final String HeightTolerance="%HeightTolerance%";
	private final String MaximumIterationNumber="%MaximumIterationNumber%"; 
	
	private CoilDB CObj;
	private ArrayList<String> dbTemplateFileDataList;
	private ArrayList<String> outputDataList;
	
	public WriteSimcosDB() {
		// TODO Auto-generated constructor stub
	}
	
	public void CreateDBFile(CoilDB obj){
		// only Call MC.File_New_Run(projectName, workspace);
		this.CObj = obj;
		this.outputDataList = new ArrayList<String>();
		this.readTemplateFile();
		
		for(String line : this.dbTemplateFileDataList){
			//System.out.println(line);
			if(line.contains(this.Date)){
				String newLine = line.replace(this.Date, myUtil.getCurrentTime());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.ProjectName)){
				String newLine = line.replace(this.ProjectName, this.CObj.getProjectName());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.ProjectFolderPath)){
				String newLine = line.replace(this.ProjectFolderPath, this.CObj.getProjectFolderPath());
				this.outputDataList.add(newLine);
			}else {
				this.outputDataList.add(line);
			}
		}
		this.writeDBFile();
	}
	
	public void saveDBFile(CoilDB obj){
		this.CObj = obj;
		this.outputDataList = new ArrayList<String>();
		this.readTemplateFile();
		
		for(String line : this.dbTemplateFileDataList){
			//System.out.println(line);
			if(line.contains(this.Date)){
				String newLine = line.replace(this.Date, myUtil.getCurrentTime());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.ProjectName)){
				String newLine = line.replace(this.ProjectName, this.CObj.getProjectName());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.ProjectFolderPath)){
				String newLine = line.replace(this.ProjectFolderPath, this.CObj.getProjectFolderPath());
				this.outputDataList.add(newLine);
			}
			
			else if(line.contains(this.ProductName)){
				String newLine = line.replace(this.ProductName, this.CObj.getProductName());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.LineDiameter)){
				String newLine = line.replace(this.LineDiameter, this.CObj.getLineDiameter());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.CenterDiameter)){
				String newLine = line.replace(this.CenterDiameter, this.CObj.getCenterDiameter());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.InnerDiameter)){
				String newLine = line.replace(this.InnerDiameter, this.CObj.getInnerDiameter());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.OuterDiameter)){
				String newLine = line.replace(this.OuterDiameter, this.CObj.getOuterDiameter());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.UpperInnerDiameter)){
				String newLine = line.replace(this.UpperInnerDiameter, this.CObj.getUpperInnerDiameter());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.LowerInnerDiameter)){
				String newLine = line.replace(this.LowerInnerDiameter, this.CObj.getLowerInnerDiameter());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.TotalNumber)){
				String newLine = line.replace(this.TotalNumber, this.CObj.getTotalNumber());
				this.outputDataList.add(newLine);
			}
			
			else if(line.contains(this.CoilDesingFilePath)){
				String newLine = line.replace(this.CoilDesingFilePath, this.CObj.getCoilDesingFilePath());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.CoilDesingUserFilePath)){
				String newLine = line.replace(this.CoilDesingUserFilePath, this.CObj.getCoilDesingUserFilePath());
				this.outputDataList.add(newLine);
			}
			
			else if(line.contains(this.HotSettingTemp)){
				String newLine = line.replace(this.HotSettingTemp, this.CObj.getHotSettingTemp());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.ColdSettingTemp)){
				String newLine = line.replace(this.ColdSettingTemp, this.CObj.getColdSettingTemp());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.HotSettingHeight)){
				String newLine = line.replace(this.HotSettingHeight, this.CObj.getHotSettingHeight());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.ColdSettingHeight)){
				String newLine = line.replace(this.ColdSettingHeight, this.CObj.getColdSettingHeight());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.SeatUIneerMargina)){
				String newLine = line.replace(this.SeatUIneerMargina, this.CObj.getSeatUIneerMargina());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.SeatLIneerMargina)){
				String newLine = line.replace(this.SeatLIneerMargina, this.CObj.getSeatLIneerMargina());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.SeatHeight)){
				String newLine = line.replace(this.SeatHeight, this.CObj.getSeatHeight());
				this.outputDataList.add(newLine);
			}
			
			else if(line.contains(this.RadiusConditionerType)){
				String newLine = line.replace(this.RadiusConditionerType, this.CObj.getRadiusConditionerType());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.RadiusConditionerConstant)){
				String newLine = line.replace(this.RadiusConditionerConstant, this.CObj.getRadiusConditionerConstant());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.RadiusConditionerFile)){
				String newLine = line.replace(this.RadiusConditionerFile, this.CObj.getRadiusConditionerFile());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.HeightConditionerType)){
				String newLine = line.replace(this.HeightConditionerType, this.CObj.getHeightConditionerType());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.HeightConditionerConstant)){
				String newLine = line.replace(this.HeightConditionerConstant, this.CObj.getHeightConditionerConstant());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.HeightConditionerFile)){
				String newLine = line.replace(this.HeightConditionerFile, this.CObj.getHeightConditionerFile());
				this.outputDataList.add(newLine);
			}
			
			else if(line.contains(this.RadiusTolerance)){
				String newLine = line.replace(this.RadiusTolerance, this.CObj.getRadiusTolerance());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.HeightTolerance)){
				String newLine = line.replace(this.HeightTolerance, this.CObj.getHeightTolerance());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.MaximumIterationNumber)){
				String newLine = line.replace(this.MaximumIterationNumber, this.CObj.getMaximumIterationNumber());
				this.outputDataList.add(newLine);
			}
			
			else {
				this.outputDataList.add(line);
			}
		}
		this.writeDBFile();
	}
	
	
	
	
	
	
	private void readTemplateFile(){
		String configFolder = myUtil.setPath(MC.getAppPath(),AppFolder.CONFIG);
		String templateFilePath = myUtil.setPath(configFolder, AppFolder.DbTemplateFile);
		this.dbTemplateFileDataList = new ArrayList<String>();
		
		Reader reader = new Reader(templateFilePath);
		reader.running();
		this.dbTemplateFileDataList = reader.getFileDataList();
	}
	
	private void writeDBFile(){
		String dbFilePath = myUtil.setPath(this.CObj.getProjectFolderPath(), AppFolder.dbFileName);
		Writer writer = new Writer(dbFilePath);
		writer.running(this.outputDataList);
		
		myUtil.CleareObj(this.dbTemplateFileDataList);
		myUtil.CleareObj(this.outputDataList);
	}

}
