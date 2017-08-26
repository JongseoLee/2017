package com.js.ens.coil.db;

import java.util.ArrayList;

import com.js.ens.coil.core.AppFolder;
import com.js.ens.coil.core.MainController;
import com.js.ens.coil.core.Preferences;
import com.js.io.Writer;
import com.js.util.myUtil;

public class WriteCoilParam {
	private MainController MC = MainController.getInstatnce();
	
	private final String Model = "Model";
	private final String SpringDesingDataFile = "Spring Design Data File";
	//private final String InitialConditionerFile = "Initial Conditioner File";
	private final String InitialConditionerRadius = "Initial Conditioner - Radius";
	private final String InitialConditionerHeight = "Initial Conditioner - Height";
	private final String HotSettingHeight = "Hot setting height";
	private final String HotSettingTemperature = "Hot setting temperature";
	private final String ColdSettingHeight = "Cold setting height";
	private final String ColdSettingTemperature = "Cold setting temperature";
	private final String CenterBeamNodeStart = "Center Beam Node start";
	private final String ErrorRadiusTolerance = "Error Radius Tolerance";
	private final String ErrorHeightTolerance = "Error Height Tolerance";
	private final String MaxIterationNo = "Max. Iteration No.";
	private final String MaterialName = "Material Name";
	private final String SeatUInnerMargina = "Seat U. Inner Margina";
	private final String SeatLInnerMargina = "Seat L. Inner Margina";
	private final String SeatHeight = "Seat Height";
	private final String MarcPath = "Marc Path";
	
	private ArrayList<String> outputDataList;
	private CoilDB CObj;
	
	public WriteCoilParam() {
		// TODO Auto-generated constructor stub
	}
	
	public void writeParamData(CoilDB obj){
		this.outputDataList = new ArrayList<String>();
		this.CObj = obj;
		
		this.outputDataList.add(this.Model+","+this.CObj.getProductName());
		this.outputDataList.add(this.SpringDesingDataFile+","+myUtil.getFileNameIncludeExtension(this.CObj.getCoilDesignUserFilePath()));
		/*
		if(this.CObj.getInitialConditionerType().equals(CoilDB.CONSTANT_TYPE)){
			this.outputDataList.add(this.InitialConditionerFile+","+this.CObj.getInitialConditionerConstant());
		}else{
			this.outputDataList.add(this.InitialConditionerFile+","+myUtil.getFileNameIncludeExtension(this.CObj.getInitialConditionerFile()));
		}
		// */
		if(this.CObj.getHeightConditionerType().equals(CoilDB.CONSTANT_TYPE)){
			this.outputDataList.add(this.InitialConditionerHeight+","+this.CObj.getHeightConditionerConstant());
		}else{
			this.outputDataList.add(this.InitialConditionerHeight+","+myUtil.getFileNameIncludeExtension(this.CObj.getHeightConditionerFile()));
		}
		
		if(this.CObj.getRadiusConditionerType().equals(CoilDB.CONSTANT_TYPE)){
			this.outputDataList.add(this.InitialConditionerRadius+","+this.CObj.getRadiusConditionerConstant());
		}else{
			this.outputDataList.add(this.InitialConditionerRadius+","+myUtil.getFileNameIncludeExtension(this.CObj.getRadiusConditionerFile()));
		}
		
		this.outputDataList.add(this.HotSettingHeight+","+this.CObj.getHotSettingHeight());
		this.outputDataList.add(this.HotSettingTemperature+","+this.CObj.getHotSettingTemp());
		this.outputDataList.add(this.ColdSettingHeight+","+this.CObj.getColdSettingHeight());
		this.outputDataList.add(this.ColdSettingTemperature+","+this.CObj.getColdSettingTemp());
		this.outputDataList.add(this.CenterBeamNodeStart+","+this.CObj.getCenterBeamNodeStart());
		this.outputDataList.add(this.ErrorRadiusTolerance+","+this.CObj.getRadiusTolerance());
		this.outputDataList.add(this.ErrorHeightTolerance+","+this.CObj.getHeightTolerance());
		this.outputDataList.add(this.MaxIterationNo+","+this.CObj.getMaximumIterationNumber());
		this.outputDataList.add(this.MaterialName+","+myUtil.getFileName(this.CObj.getMaterialDB()));
		this.outputDataList.add(this.SeatUInnerMargina+","+this.CObj.getSeatUIneerMargina());
		this.outputDataList.add(this.SeatLInnerMargina+","+this.CObj.getSeatLIneerMargina());
		this.outputDataList.add(this.SeatHeight+","+this.CObj.getSeatHeight());
		this.outputDataList.add(this.MarcPath+","+MC.getPreferencesObj().getPreferencesValue(Preferences.MarcPath));
		
		String coilParamFilePath = myUtil.setPath(myUtil.setPath(this.CObj.getProjectFolderPath(), AppFolder.SIMCOS_DATA),AppFolder.coilParamCSVFileName);
		Writer writer = new Writer(coilParamFilePath);
		writer.running(this.outputDataList);
		
		myUtil.CleareObj(this.outputDataList);
	}
}
