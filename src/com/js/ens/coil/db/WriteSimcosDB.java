package com.js.ens.coil.db;

import java.util.ArrayList;

import com.js.ens.coil.core.AppFolder;
import com.js.ens.coil.core.MainController;
import com.js.ens.coil.customWidget.TableData_Coil;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class WriteSimcosDB {
	private MainController MC = MainController.getInstatnce();
	
	// Base 
	private final String Date = "%date%";
	private final String ProjectName = "%projectName%";
	private final String ProjectFolderPath="%projectFolderPath%";
	private final String CenterBeamNodeStart="%CenterBeamNodeStart%";
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
	private final String CoilDesignFilePath="%CoilDesignFilePath%";
	private final String CoilDesignUserFilePath="%CoilDesignUserFilePath%";
	private final String CoilGeometryData_start="CoilGeometryData_start";
	private final String CoilGeometryData_end="CoilGeometryData_end";
	// Setting Process Information
	private final String HotSettingTemp="%HotSettingTemp%";
	private final String ColdSettingTemp="%ColdSettingTemp%";
	private final String HotSettingHeight="%HotSettingHeight%";
	private final String ColdSettingHeight="%ColdSettingHeight%";
	private final String SeatUIneerMargina="%SeatUIneerMargina%";
	private final String SeatLIneerMargina="%SeatLIneerMargina%";
	private final String SeatHeight="%SeatHeight%";
	// Initial conditioner
	private final String InitialConditionerType="%InitialConditionerType%";
	private final String InitialConditionerConstant="%InitialConditionerConstant%";
	private final String InitialConditionerFile="%InitialConditionerFile%";
	// Material Database
	private final String MaterialDB ="%MaterialDB%";
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
			}
			else {
				if(line.contains("%")){
					
				}else{
					this.outputDataList.add(line);
				}
				
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
			}else if(line.contains(this.CenterBeamNodeStart)){
				String newLine = line.replace(this.CenterBeamNodeStart, this.CObj.getCenterBeamNodeStart());
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
			
			else if(line.contains(this.CoilDesignFilePath)){
				String newLine = line.replace(this.CoilDesignFilePath, this.CObj.getCoilDesignFilePath());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.CoilDesignUserFilePath)){
				String newLine = line.replace(this.CoilDesignUserFilePath, this.CObj.getCoilDesignUserFilePath());
				this.outputDataList.add(newLine);
				if(!this.CObj.getCoilDesignUserFilePath().equals("null")){
					this.outputDataList.add(this.CoilGeometryData_start);
					for(String data : this.getCoilGeometryData()){
						this.outputDataList.add(data);
					}
					this.outputDataList.add(this.CoilGeometryData_end);
				}
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
			
			else if(line.contains(this.InitialConditionerType)){
				String newLine = line.replace(this.InitialConditionerType, this.CObj.getInitialConditionerType());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.InitialConditionerConstant)){
				String newLine = line.replace(this.InitialConditionerConstant, this.CObj.getInitialConditionerConstant());
				this.outputDataList.add(newLine);
			}else if(line.contains(this.InitialConditionerFile)){
				String newLine = line.replace(this.InitialConditionerFile, this.CObj.getInitialConditionerFile());
				this.outputDataList.add(newLine);
			}
			
			else if(line.contains(this.MaterialDB)){
				String newLine = line.replace(this.MaterialDB, this.CObj.getMaterialDB());
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
	
	private ArrayList<String> getCoilGeometryData(){
		ArrayList<String> result = new ArrayList<String>(); 
		for(TableData_Coil obj : this.CObj.getGeometryDataTableList()){
			result.add(obj.getSaveData());
		}
		return result;
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
