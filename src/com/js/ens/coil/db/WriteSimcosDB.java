package com.js.ens.coil.db;

import java.util.ArrayList;

import com.js.ens.coil.core.AppFolder;
import com.js.ens.coil.core.MainController;
import com.js.ens.coil.core.Preferences;
import com.js.ens.coil.core.UILabel;
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
	private final String WireDiameter="%WireDiameter%";
	private final String CenterDiameter="%CenterDiameter%";
	private final String InternalDiameter="%InternalDiameter%";
	private final String ExternalDiameter="%ExternalDiameter%";
	private final String UpperInnerDiameter="%UpperInnerDiameter%";
	private final String LowerInnerDiameter="%LowerInnerDiameter%";
	private final String TotalTurns="%TotalTurns%";
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
	private ArrayList<String> pythonInputDataList;
	
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
			}else if(line.contains(this.WireDiameter)){
				String newLine = line.replace(this.WireDiameter, this.CObj.getWireDiameter());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.CenterDiameter)){
				String newLine = line.replace(this.CenterDiameter, this.CObj.getCenterDiameter());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.InternalDiameter)){
				String newLine = line.replace(this.InternalDiameter, this.CObj.getInternalDiameter());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.ExternalDiameter)){
				String newLine = line.replace(this.ExternalDiameter, this.CObj.getExternalDiameter());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.UpperInnerDiameter)){
				String newLine = line.replace(this.UpperInnerDiameter, this.CObj.getUpperInnerDiameter());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.LowerInnerDiameter)){
				String newLine = line.replace(this.LowerInnerDiameter, this.CObj.getLowerInnerDiameter());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.TotalTurns)){
				String newLine = line.replace(this.TotalTurns, this.CObj.getTotalTurns());
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
	
	public void createPythonScriptInput(CoilDB obj){
		this.CObj = obj;
		this.pythonInputDataList = new ArrayList<String>();
		String outputFilePath = myUtil.setPath(myUtil.setPath(this.CObj.getProjectFolderPath(), AppFolder.SIMCOS_DATA),AppFolder.pythonScriptInputDataFileName);
		
		this.pythonInputDataList.add(UILabel.ProductName+"="+this.CObj.getProductName());
		this.pythonInputDataList.add(UILabel.WireDiameter+"="+this.CObj.getWireDiameter());
		this.pythonInputDataList.add(UILabel.CenterDiameter+"="+this.CObj.getCenterDiameter());
		this.pythonInputDataList.add(UILabel.InternalDiameter+"="+this.CObj.getInternalDiameter());
		this.pythonInputDataList.add(UILabel.ExternalDiameter+"="+this.CObj.getExternalDiameter());
		this.pythonInputDataList.add(UILabel.UpperInnerDiameter+"="+this.CObj.getUpperInnerDiameter());
		this.pythonInputDataList.add(UILabel.LowerInnerDiameter+"="+this.CObj.getLowerInnerDiameter());
		this.pythonInputDataList.add(UILabel.TotalTurns+"="+this.CObj.getTotalTurns());
		
		this.pythonInputDataList.add(UILabel.HotSettingTemp+"="+this.CObj.getHotSettingTemp());
		this.pythonInputDataList.add(UILabel.ColdSettingTemp+"="+this.CObj.getColdSettingTemp());
		this.pythonInputDataList.add(UILabel.HotSettingHeight+"="+this.CObj.getHotSettingHeight());
		this.pythonInputDataList.add(UILabel.ColdSettingHeight+"="+this.CObj.getColdSettingHeight());
		this.pythonInputDataList.add(UILabel.SeatUInnerMargina+"="+this.CObj.getSeatUIneerMargina());
		this.pythonInputDataList.add(UILabel.SeatLInnerMargina+"="+this.CObj.getSeatLIneerMargina());
		this.pythonInputDataList.add(UILabel.SeatHeight+"="+this.CObj.getSeatHeight());
		
		this.pythonInputDataList.add(UILabel.RadiusConditionerConstant+"="+this.CObj.getRadiusConditionerConstant());
		this.pythonInputDataList.add(UILabel.RadiusConditionerFile+"="+this.CObj.getRadiusConditionerFile());
		this.pythonInputDataList.add(UILabel.HeightConditionerConstant+"="+this.CObj.getHeightConditionerConstant());
		this.pythonInputDataList.add(UILabel.HeightConditionerFile+"="+this.CObj.getHeightConditionerFile());
		
		this.pythonInputDataList.add(UILabel.RadiusTolerance+"="+this.CObj.getRadiusTolerance());
		this.pythonInputDataList.add(UILabel.HeightTolerance+"="+this.CObj.getHeightTolerance());
		this.pythonInputDataList.add(UILabel.MaximumIterationNumber+"="+this.CObj.getMaximumIterationNumber());
		
		this.pythonInputDataList.add("ProjectFolderPath"+"="+this.CObj.getProjectFolderPath());
		this.pythonInputDataList.add("ProjectResultFolderPath"+"="+myUtil.setPath(this.CObj.getProjectFolderPath(), AppFolder.RESULT));
		this.pythonInputDataList.add("ProjectSolvingFolderPath"+"="+myUtil.setPath(this.CObj.getProjectFolderPath(),AppFolder.SIMCOS_DATA));
		
		this.pythonInputDataList.add("MarcPath"+"="+MC.getPreferencesObj().getPreferencesValue(Preferences.MarcPath));
		
		Writer writer = new Writer(outputFilePath);
		writer.running(this.pythonInputDataList);
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
