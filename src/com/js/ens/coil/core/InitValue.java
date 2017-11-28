package com.js.ens.coil.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.js.io.Reader;
import com.js.util.myUtil;

public class InitValue {
	
	private Map<String,String> InitValueMap;
	private String InitValuePath;
	
	public static String CenterBeamNodeStart="CenterBeamNodeStart";
	
	public static String HotSettingTemp="HotSettingTemp";
	public static String ColdSettingTemp="ColdSettingTemp";
	public static String HotSettingHeight="HotSettingHeight";
	public static String ColdSettingHeight="ColdSettingHeight";
	public static String SeatType="SeatType";
	public static String SeatUInnerMargina="SeatUInnerMargina";
	public static String SeatLInnerMargina="SeatLInnerMargina";
	public static String SeatHeight="SeatHeight";
	public static String SeatUStepRotationHeight="SeatUStepRotationHeight";
	public static String SeatLStepRotationHeight="SeatLStepRotationHeight";
	public static String SeatURotationAngle="SeatURotationAngle";
	public static String SeatLRotationAngle="SeatLRotationAngle";
	/*
	public static String InitialConditionerType="InitialConditionerType";
	public static String InitialConditionerConstant="InitialConditionerConstant";
	public static String InitialConditionerFile="InitialConditionerFile";
	//*/
	public static String RadiusConditionerType = "RadiusConditionerType";
	public static String RadiusConditionerConstant = "RadiusConditionerConstant";
	public static String RadiusConditionerFile = "RadiusConditionerFile";
	public static String HeightConditionerType = "HeightConditionerType";
	public static String HeightConditionerConstant = "HeightConditionerConstant";
	public static String HeightConditionerFile = "HeightConditionerFile";
	
	
	public static String MaterialDatabase="MaterialDatabase";
	
	public static String ParallelCPUNumber="ParallelCPUNumber";
	
	public static String RadiusTolerance="RadiusTolerance";
	public static String HeightTolerance="HeightTolerance";
	public static String MaximumIterationNumber="MaximumIterationNumber";
	
	public InitValue(String appPath) {
		// TODO Auto-generated constructor stub
		this.InitValueMap = new HashMap<String,String>();
		this.InitValuePath = myUtil.setPath(myUtil.setPath(appPath, AppFolder.CONFIG), AppFolder.InitValueFile);
		this.readInitValueFile();
	}
	
	private void readInitValueFile(){
		try{
			Reader reader = new Reader(this.InitValuePath);
			reader.running();
			for(String line : reader.getFileDataList()){
				String data = line.trim();
				if(data.contains("=")){
					ArrayList<String> tokens = new ArrayList<String>();
					tokens = myUtil.splitData(line, "=");
					this.InitValueMap.put(tokens.get(0), tokens.get(1));
				}
			}
		}catch(Exception e){
		}
	}
	
	public String getInitValue(String key){
		if(this.InitValueMap.containsKey(key)){
			return this.InitValueMap.get(key);
		}else{
			return "no-Data";
		}
	}
	
	

}
