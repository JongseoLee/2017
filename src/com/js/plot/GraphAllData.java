package com.js.plot;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.js.ens.coil.core.MainController;
import com.js.ens.coil.customWidget.ComboData_selectGraph;
import com.js.ens.coil.db.CoilDB;
import com.js.util.myUtil;

public class GraphAllData {
	private MainController MC = MainController.getInstatnce();
	private CoilDB CObj;
	
	public static String conditionerType = "_conditioner.csv";
	public static String errorType = "_error.csv";
	public static String formSetErrorType = "_form_set_error.csv";
	public static String maximumErrorType = "_err_hist.csv";
	
	private ArrayList<String> csvFilePathList; 
	
	public GraphAllData(CoilDB coilDBObj) {
		// TODO Auto-generated constructor stub
		this.CObj = coilDBObj;
		this.csvFilePathList = new ArrayList<String>();
		
	}
	
	public void LoadingResult(String resultFolder){
		
		for(File f : myUtil.getDirFileList(resultFolder)){
			if(f.isFile() && myUtil.getExtensions(f.getName()).equals("csv")){
				String fileName = f.getName();
				if(this.isMatch_conditionerType(fileName)){
					//System.out.println(f.getName());
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.conditionerType);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.running("normal");
					this.CObj.add_GraphDataCombo_conditioner(obj);
				}else if(this.isMatch_errorType(fileName)){
					//System.out.println(f.getName());
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.errorType);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.running("normal");
					this.CObj.add_GraphDataCombo_error(obj);
				}else if(this.isMatch_formSetErrorType(fileName)){
					//System.out.println(f.getName());
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.formSetErrorType);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.running("normal");
					this.CObj.add_GraphDataCombo_formSetError(obj);
				}else if(this.isMatch_MaximumErrorType(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.maximumErrorType);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.running("err");
					this.CObj.add_GraphDataCombo_maximumError(obj);
				}
			}
		}
	}
	
	public ComboData_selectGraph getGraphObj_conditioner(String fileName){
		ComboData_selectGraph returnObj = null;
		for(ComboData_selectGraph obj : this.CObj.getGraphDataList_conditioner()){
			if(obj.getName().equals(fileName)){
				returnObj = obj;
				break;
			}
		}
		return returnObj;
	}
	
	public ComboData_selectGraph getGraphObj_error(String fileName){
		ComboData_selectGraph returnObj = null;
		for(ComboData_selectGraph obj : this.CObj.getGraphDataList_error()){
			if(obj.getName().equals(fileName)){
				returnObj = obj;
				break;
			}
		}
		return returnObj;
	}
	
	public ComboData_selectGraph getGraphObj_formSetError(String fileName){
		ComboData_selectGraph returnObj = null;
		for(ComboData_selectGraph obj : this.CObj.getGraphDataList_formSetError()){
			if(obj.getName().equals(fileName)){
				returnObj = obj;
				break;
			}
		}
		return returnObj;
	}
	
	public ComboData_selectGraph getGraphObj_maximumError(String fileName){
		ComboData_selectGraph returnObj = null;
		for(ComboData_selectGraph obj : this.CObj.getGraphDataList_maximumError()){
			if(obj.getName().equals(fileName)){
				returnObj = obj;
				break;
			}
		}
		return returnObj;
	}
	
	
	public boolean isMatch_conditionerType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("[0-9]+("+GraphAllData.conditionerType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
		}else {
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_errorType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("[0-9]+("+GraphAllData.errorType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
		}else {
			result = false;
		}
		return result;	
	}
	
	public boolean isMatch_formSetErrorType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("[0-9]+("+GraphAllData.formSetErrorType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
		}else {
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_MaximumErrorType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("("+GraphAllData.maximumErrorType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
		}else{
			result = false;
		}
		
		return result;
	}
}
