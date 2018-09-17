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
	
	public static final String Type_conditioner = "Conditioner";
	public static String conditionerType = "_conditioner.csv";
	
	public static final String Type_error = "Error";
	public static String errorType = "_error.csv";
	public static final String Type_formSetError = "FormSetError";
	public static String formSetErrorType = "_form_set_error.csv";	
	public static final String Type_pitch = "pitch";
	public static String csetRezonedAlignPitchType = "_cset_rezoned_align_pitch.csv";
	public static String hsetRezonedAlignPitchType = "_hset_rezoned_align_pitch.csv";
	public static String formedRezonedAlignPitchType = "_formed_rezoned_align_pitch.csv";
	public static final String Type_radius = "radius";
	public static String csetRezonedAlignEradType = "_cset_rezoned_align_erad.csv";
	public static String hsetRezonedAlignEradType = "_hset_rezoned_align_erad.csv";
	public static String formedRezonedAlignEradType = "_formed_rezoned_align_erad.csv";
	
	public static final String Type_maximumError = "MaximumError";
	public static String maximumErrorType = "_err_hist.csv";
	public static final String Type_formDataTotal = "FormDataTotal";
	public static String FormdataTotal = "_formdata_total.csv";
	
	private ArrayList<String> csvFilePathList; 
	
	public GraphAllData(CoilDB coilDBObj) {
		// TODO Auto-generated constructor stub
		this.CObj = coilDBObj;
		this.csvFilePathList = new ArrayList<String>();
		
	}
	
	public void LoadingResult(String resultFolder){
		// SimCos_Workspace/name/SimcosData 
		
		for(File f : myUtil.getDirFileList(resultFolder)){
			if(f.isFile() && myUtil.getExtensions(f.getName()).equals("csv")){
				String fileName = f.getName();
				if(this.isMatch_conditionerType(fileName)){
					//System.out.println(f.getName());
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.Type_conditioner);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.setPatternFileName(GraphAllData.conditionerType);
					obj.running();
					this.CObj.add_GraphDataCombo_conditioner(obj);
				}
				
				else if(this.isMatch_errorType(fileName)){
					//System.out.println(f.getName());
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.Type_error);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.setPatternFileName(GraphAllData.errorType);
					obj.running();
					this.CObj.add_GraphDataCombo_error(obj);
				}else if(this.isMatch_formSetErrorType(fileName)){
					//System.out.println(f.getName());
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.Type_formSetError);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.setPatternFileName(GraphAllData.formSetErrorType);
					obj.running();
					this.CObj.add_GraphDataCombo_formSetError(obj);
				}
				
				else if(this.isMatch_csetRezonedAlignPitchType(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.Type_pitch);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.setPatternFileName(GraphAllData.csetRezonedAlignPitchType);
					obj.running();
					this.CObj.add_GraphDataCombo_pitch_IR(obj);
				}else if(this.isMatch_hsetRezonedAlignPitchType(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.Type_pitch);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.setPatternFileName(GraphAllData.hsetRezonedAlignPitchType);
					obj.running();
					this.CObj.add_GraphDataCombo_pitch_IR(obj);
				}else if(this.isMatch_formedRezonedAlignPitchType(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.Type_pitch);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.setPatternFileName(GraphAllData.formedRezonedAlignPitchType);
					obj.running();
					this.CObj.add_GraphDataCombo_pitch_IR(obj);
				}
				
				else if(this.isMatch_csetRezonedAlignEradType(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.Type_radius);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.setPatternFileName(GraphAllData.csetRezonedAlignEradType);
					obj.running();
					this.CObj.add_GraphDataCombo_radius_IR(obj);
				}else if(this.isMatch_hsetRezonedAlignEradType(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.Type_radius);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.setPatternFileName(GraphAllData.hsetRezonedAlignEradType);
					obj.running();
					this.CObj.add_GraphDataCombo_radius_IR(obj);
				}else if(this.isMatch_formedRezonedAlignEradType(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.Type_radius);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.setPatternFileName(GraphAllData.formedRezonedAlignEradType);
					obj.running();
					this.CObj.add_GraphDataCombo_radius_IR(obj);
				}
				
				else if(this.isMatch_MaximumErrorType(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.Type_maximumError);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.setPatternFileName(GraphAllData.maximumErrorType);
					obj.running();
					this.CObj.add_GraphDataCombo_maximumError(obj);
				}else if(this.isMatch_FormdataTotal(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.Type_formDataTotal);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.setPatternFileName(GraphAllData.FormdataTotal);
					obj.running();
					this.CObj.add_GraphDataCombo_formDataTotal(obj);
				}
				
			}
		}
		//myUtil.printArrData(csvFilePathList);
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
	
	public ComboData_selectGraph getGraphObj_pitch_IR(String fileName){
		ComboData_selectGraph returnObj = null;
		for(ComboData_selectGraph obj : this.CObj.getGraphDataList_pitch_IR()){
			if(obj.getName().equals(fileName)){
				returnObj = obj;
				break;
			}
		}
		return returnObj;
	}
	
	public ComboData_selectGraph getGraphObj_radius_IR(String fileName){
		ComboData_selectGraph returnObj = null;
		for(ComboData_selectGraph obj : this.CObj.getGraphDataList_radius_IR()){
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
	
	public ComboData_selectGraph getGraphObj_formDataTotal(String fileName){
		ComboData_selectGraph returnObj = null;
		for(ComboData_selectGraph obj : this.CObj.getGraphDataList_formDataTotal()){
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
			//System.out.println("Conditioner file : "+fileName);
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
			//System.out.println("Error file : "+fileName);
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
			//System.out.println("FormSetError : "+fileName);
		}else {
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_csetRezonedAlignPitchType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("[0-9]+("+GraphAllData.csetRezonedAlignPitchType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
			//System.out.println("Pitch : "+fileName);
		}else{
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_hsetRezonedAlignPitchType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("[0-9]+("+GraphAllData.hsetRezonedAlignPitchType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
			//System.out.println("Pitch : "+fileName);
		}else{
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_formedRezonedAlignPitchType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("[0-9]+("+GraphAllData.formedRezonedAlignPitchType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
			//System.out.println("Pitch : "+fileName);
		}else{
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_csetRezonedAlignEradType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("[0-9]+("+GraphAllData.csetRezonedAlignEradType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
			//System.out.println("Radius : "+fileName);
		}else{
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_hsetRezonedAlignEradType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("[0-9]+("+GraphAllData.hsetRezonedAlignEradType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
			//System.out.println("Radius : "+fileName);
		}else{
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_formedRezonedAlignEradType(String fileName){
		boolean result = false;                      
		Pattern p = Pattern.compile("[0-9]+("+GraphAllData.formedRezonedAlignEradType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
			//System.out.println("Radius : "+fileName);
		}else{
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
			//System.out.println("Max Err : "+fileName);
		}else{
			result = false;
		}
		
		return result;
	}
	
	public boolean isMatch_FormdataTotal(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("("+GraphAllData.FormdataTotal+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
			//System.out.println("FomeDataTotal : " + fileName);
		}else{
			result = false;
		}
		return result;
	}
	
	
}
