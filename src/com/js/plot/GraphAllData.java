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
	//2018.03_update
	/*
	 * 성형품 / 핫세팅 / 완제품의 누적 피치 선도와 반경그래프 항목 추가
	 * FS_1_cset_rezoned_align_erad
	 * FS_1_cset_rezoned_align_ptich
	 * FS_1_formed_rezoned_align_erad
	 * FS_1_formed_rezoned_align_ptich
	 * FS_1_hset_rezoned_align_erad
	 * FS_1_hset_rezoned_align_ptich
	 * 
	 * 성형 데이터 피치 및 외경 값 그래프 항목 추가 
	 * M_formdata_total
	 */
	public static String etcType1 = "etcType1";
	public static String etcType2 = "etcType2";
	public static String csetRezonedAlignEradType = "_cset_rezoned_align_erad.csv";
	public static String csetRezonedAlignPtichType = "_cset_rezoned_align_ptich.csv";
	public static String formedRezonedAlignEradType = "_formed_rezoned_align_erad.csv";
	public static String formedRezonedAlignPtichType = "_formed_rezoned_align_ptich.csv";
	public static String hsetRezonedAlignErad = "_hset_rezoned_align_erad.csv";
	public static String hsetRezonedAlignPtich = "_hset_rezoned_align_ptich.csv";
	
	public static String mFormdataTotal = "M_formdata_total.csv";
	
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
				// 2018.03_update
				else if(this.isMatch_csetRezonedAlignEradType(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					//ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.etcType1);
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.csetRezonedAlignEradType);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.running("etc1");
					this.CObj.add_GraphDataCombo_etc(obj);
				}else if(this.isMatch_csetRezonedAlignPtichType(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					//ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.etcType1);
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.csetRezonedAlignPtichType);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.running("etc1");
					this.CObj.add_GraphDataCombo_etc(obj);
				}else if(this.isMatch_formedRezonedAlignEradType(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					//ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.etcType1);
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.formedRezonedAlignEradType);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.running("etc1");
					this.CObj.add_GraphDataCombo_etc(obj);
				}else if(this.isMatch_formedRezonedAlignPtichType(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					//ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.etcType1);
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.formedRezonedAlignPtichType);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.running("etc1");
					this.CObj.add_GraphDataCombo_etc(obj);
				}else if(this.isMatch_hsetRezonedAlignErad(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					//ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.etcType1);
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.hsetRezonedAlignErad);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.running("etc1");
					this.CObj.add_GraphDataCombo_etc(obj);
				}else if(this.isMatch_hsetRezonedAlignPtich(fileName)){
					this.csvFilePathList.add(f.getAbsolutePath());
					//ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.etcType1);
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.hsetRezonedAlignPtich);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.running("etc1");
					this.CObj.add_GraphDataCombo_etc(obj);
				}
				else if(this.isMatch_mFormdataTotal(fileName)){
					//System.out.println("find !!! "+fileName);
					this.csvFilePathList.add(f.getAbsolutePath());
					//ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.etcType2);
					ComboData_selectGraph obj = new ComboData_selectGraph(GraphAllData.mFormdataTotal);
					obj.setName(f.getName());
					obj.setFilePath(f.getAbsolutePath());
					obj.running("etc2");
					this.CObj.add_GraphDataCombo_etc2(obj);
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
	
	public ComboData_selectGraph getGraphObj_etc(String fileName){
		ComboData_selectGraph returnObj = null;
		for(ComboData_selectGraph obj : this.CObj.getGraphDataList_etc()){
			if(obj.getName().equals(fileName)){
				returnObj = obj;
				break;
			}
		}
		return returnObj;
	}
	
	public ComboData_selectGraph getGraphObj_etc2(String fileName){
		ComboData_selectGraph returnObj = null;
		for(ComboData_selectGraph obj : this.CObj.getGraphDataList_etc2()){
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
	
	// 2018.03_update
	public boolean isMatch_csetRezonedAlignEradType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("("+GraphAllData.csetRezonedAlignEradType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
		}else{
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_csetRezonedAlignPtichType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("("+GraphAllData.csetRezonedAlignPtichType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
		}else{
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_formedRezonedAlignEradType(String fileName){
		boolean result = false;                      
		Pattern p = Pattern.compile("("+GraphAllData.formedRezonedAlignEradType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
		}else{
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_formedRezonedAlignPtichType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("("+GraphAllData.formedRezonedAlignPtichType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
		}else{
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_hsetRezonedAlignErad(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("("+GraphAllData.hsetRezonedAlignErad+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
		}else{
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_hsetRezonedAlignPtich(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("("+GraphAllData.hsetRezonedAlignPtich+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
		}else{
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_mFormdataTotal(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("("+GraphAllData.mFormdataTotal+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
		}else{
			result = false;
		}
		return result;
	}
	
	
}
