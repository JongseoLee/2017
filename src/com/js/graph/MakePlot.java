package com.js.graph;

import java.util.ArrayList;

import com.js.ens.coil.core.AppFolder;
import com.js.ens.coil.core.MainController;
import com.js.ens.coil.customWidget.ListData_selectedGraph;
import com.js.ens.coil.customWidget.ComboData_selectGraph.ColumnData;
import com.js.ens.coil.db.CoilDB;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class MakePlot {
	private MainController MC = MainController.getInstatnce();
	private CoilDB coilDBObj; 
	
	private final String title_Condtioner = "Conditioner";
	private final String title_Error = "Error";
	private final String title_FormSetError = "Form set error";
	
	// change Var
	private final String graphLibPath = "%graphLibPath%";
	private final String mainTitle = "%mainTitle%";
	private final String xLabel = "%xLabel%";
	private final String xData = "%xData%";
	private final String yLabel = "%yLabel%";
	private final String ySeriesDatas = "%ySeriesDatas%";
	
	// common var 
	
	private String GraphHtmFilePath;
	private ArrayList<String> fileDataList;
	private ArrayList<String> outputDatatList;
	// replace var 
	//private String LibPath;
	private String title;
	private String xAxisTitle;
	private String yAxisTitle;
	private String xValue;
	private String yValue;

	
	
	public MakePlot() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(CoilDB cobj){
		this.coilDBObj = cobj;
		/* 
		System.out.println("##################################################");
		for(ListData_selectedGraph obj : this.coilDBObj.getSelectedGraphList()){
			System.out.println(obj.getName());
			System.out.println("X 축 : "+obj.getGraph().getxDataObj().getDataName());
			System.out.println("Data Size : "+obj.getGraph().getxDataObj().getValueList().size());
			System.out.println("Data : "+obj.getGraph().getxDataObj().getValueList());
			for(ColumnData cdObj : obj.getGraph().getyDataObjList()){
				System.out.println("Y 축 : "+cdObj.getDataName());
				System.out.println("Data Size : "+cdObj.getValueList().size());
				System.out.println("Data : "+cdObj.getValueList());
			}
		}
		System.out.println("##################################################");
		// */
		
		// init var 
		this.copyLib();
		
		String graphFileName = this.coilDBObj.getProductName()+"_"+myUtil.getCurrentTime()+".html";
		this.GraphHtmFilePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.coilDBObj.getProjectFolderPath(), AppFolder.SIMCOS_DATA),AppFolder.PLOT),graphFileName);
		// read template file 
		this.readTemplate();
		// make plot data
		this.makeAllData();
		// writer plot file
		this.makePlotFile();
	}
	
	private void copyLib(){
		String graphLibPath = myUtil.setPath(myUtil.setPath(MC.getAppPath(), AppFolder.CONFIG),AppFolder.GRAPH_LIB);
		
		String highchartFile = myUtil.setPath(graphLibPath, AppFolder.highchartsFile);
		String exportFile = myUtil.setPath(graphLibPath, AppFolder.exportingFile);
		String jqueryFile = myUtil.setPath(graphLibPath, AppFolder.jqueryFile);
		
		String plotFolder = myUtil.setPath(myUtil.setPath(this.coilDBObj.getProjectFolderPath(), AppFolder.SIMCOS_DATA),AppFolder.PLOT);
		String destHighchartFile = myUtil.setPath(plotFolder,  AppFolder.highchartsFile);
		String destExportFile = myUtil.setPath(plotFolder, AppFolder.exportingFile);
		String destJqueryFile = myUtil.setPath(plotFolder, AppFolder.jqueryFile);
		
		if(!myUtil.checkPath(destHighchartFile)){
			myUtil.fileCopy(highchartFile, destHighchartFile);
		}
		if(!myUtil.checkPath(destExportFile)){
			myUtil.fileCopy(exportFile, destExportFile);
		}
		if(!myUtil.checkPath(destJqueryFile)){
			myUtil.fileCopy(jqueryFile, destJqueryFile);
		}
		
	}
	
	private void readTemplate(){
		this.fileDataList = new ArrayList<String>();
		String graphLibPath = myUtil.setPath(myUtil.setPath(MC.getAppPath(), AppFolder.CONFIG),AppFolder.GRAPH_LIB);
		String tmpFilePath = myUtil.setPath(graphLibPath, AppFolder.PlotTmpFile);
		Reader reader = new Reader(tmpFilePath);
		reader.running();
		this.fileDataList = reader.getFileDataList();
	}
	
	private void makeAllData(){
		this.title = this.getTitle();
		this.xAxisTitle = "Theta";
		this.yAxisTitle = "Value";
		
		// set xValue
		String xdata = "";
		for(String value : this.coilDBObj.getSelectedGraphList().get(0).getGraph().getxDataObj().getValueList()){
			xdata = xdata+value+",";
		}
		this.xValue = xdata.substring(0, xdata.length()-1);
		
		// set yValue
		String ydata = "";
		for(ListData_selectedGraph obj : this.coilDBObj.getSelectedGraphList()){
			for(ColumnData cdObj : obj.getGraph().getyDataObjList()){
				ydata = ydata + 
						"{"+"\n"+
						"\t"+"name : '"+cdObj.getDataName()+"',"+"\n"+
						"\t"+"data : ["+this.yValues(cdObj.getValueList()) +"]"+"\n"+
						"},"+"\n";
			}
		}
		this.yValue = ydata.substring(0,ydata.length()-2);
		
	
	}
	
	private String getTitle(){
		String result = "";
		ArrayList<ListData_selectedGraph> ls_graph_list = this.coilDBObj.getSelectedGraphList();

		int conditionerType = 0;
		int errorType = 0;
		int formSetErrorType = 0;

		for(int i=0 ; i < ls_graph_list.size();i++){
			String type = ls_graph_list.get(i).getGraph().getResultType();
			if(type.equals(GraphData.conditionerType)){
				conditionerType++;
			}else if(type.equals(GraphData.errorType)){
				errorType++;
			}else if(type.equals(GraphData.formSetErrorType)){
				formSetErrorType++;
			}
		}
		
		if(conditionerType != 0 && errorType !=0 && formSetErrorType !=0){
			result = this.title_Condtioner+" "+this.title_Error+" "+this.title_FormSetError;
			
		}else if(conditionerType == 0 && errorType !=0 && formSetErrorType !=0){
			result = this.title_Error+" "+this.title_FormSetError;
			
		}else if(conditionerType != 0 && errorType ==0 && formSetErrorType !=0){
			result = this.title_Condtioner+" "+this.title_FormSetError;
			
		}else if(conditionerType != 0 && errorType !=0 && formSetErrorType ==0){
			result = this.title_Condtioner+" "+this.title_Error;
			
		}else if(conditionerType == 0 && errorType ==0 && formSetErrorType !=0){
			result = this.title_FormSetError;
			
		}else if(conditionerType != 0 && errorType ==0 && formSetErrorType ==0){
			result = this.title_Condtioner;
			
		}else if(conditionerType == 0 && errorType !=0 && formSetErrorType ==0){
			result = this.title_Error;
			
		}else if(conditionerType == 0 && errorType ==0 && formSetErrorType ==0){
			result = "no data";
		}
		return result;
	}
	
	private String yValues(ArrayList<String> valueList){
		String line = "";
		for(String value : valueList){
			line = line + value + ",";
		}
		return line.substring(0, line.length()-1);
	}
	
	
	private void makePlotFile(){
		this.outputDatatList = new ArrayList<String>();
		for(String line : this.fileDataList){
			/*
			if(line.contains(this.graphLibPath)){
				String newLine = line.replace(this.graphLibPath, this.LibPath);
				this.outputDatatList.add(newLine);
			}else// */ 
			if(line.contains(this.mainTitle)){
				String newLine = line.replace(this.mainTitle, this.title);
				this.outputDatatList.add(newLine);
			}else if(line.contains(this.xLabel)){
				String newLine = line.replace(this.xLabel, this.xAxisTitle);
				this.outputDatatList.add(newLine);
			}else if(line.contains(this.xData)){
				String newLine = line.replace(this.xData, this.xValue);
				this.outputDatatList.add(newLine);
			}else if(line.contains(this.yLabel)){
				String newLine = line.replace(this.yLabel, this.yAxisTitle);
				this.outputDatatList.add(newLine);
			}else if(line.contains(this.ySeriesDatas)){
				String newLine = line.replace(this.ySeriesDatas, this.yValue);
				this.outputDatatList.add(newLine);
			}else {
				this.outputDatatList.add(line);
			}
		}
		
		Writer writer = new Writer(this.GraphHtmFilePath);
		writer.running(this.outputDatatList);
	}
	
	public String getGraphHtmFilePath() {
		return GraphHtmFilePath;
	}
	
	
	

}
