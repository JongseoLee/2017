package com.js.ens.coil.customWidget;

import java.util.ArrayList;

import com.js.io.Reader;
import com.js.util.myUtil;

public class ComboData_selectGraph {
	private String name; // combo label

	private String resultType;
	private String filePath;

	private String graphTitle;
	private String iterationName;
	private ArrayList<String> fileDataList;
	private ColumnData xDataObj;
	private ArrayList<ColumnData> yDataObjList;
	
	public ComboData_selectGraph(String resultType) {
		// TODO Auto-generated constructor stub
		this.resultType = resultType;
	}
	
	public void running(){
		this.readResultCSV();
		this.parsing();
	}
	
	private void readResultCSV(){
		this.fileDataList = new ArrayList<String>();
		
		Reader reader = new Reader(this.filePath);
		reader.running();
		
		for(int i=0; i<reader.getFileDataList().size();i++){
			if(i==0){
				// title is first line of csv file
				this.graphTitle = reader.getFileDataList().get(0).trim();
			}else{
				this.fileDataList.add(reader.getFileDataList().get(i));
			}
		}
	}
	
	private void parsing(){
		this.yDataObjList = new ArrayList<ColumnData>();
		
		int dataSize = myUtil.splitData_csv(this.fileDataList.get(0),",").size();
		//create columnDataObj init
		this.xDataObj = new ColumnData();
		for(int i=1; i<dataSize;i++){
			ColumnData obj = new ColumnData();
			this.yDataObjList.add(obj);
		}
		
		// set value at columnDataObj
		for(int lineNum = 0; lineNum <this.fileDataList.size();lineNum++){
			String line = this.fileDataList.get(lineNum);
			ArrayList<String> tokens = new ArrayList<String>();
			tokens = myUtil.splitData_csv(line, ",");
			
			if(lineNum == 0){
				this.xDataObj.setDataName(tokens.get(0));
				for(int i=0;i<tokens.size()-1;i++){
					this.yDataObjList.get(i).setDataName(iterationName+"_"+tokens.get(i+1));
				}	
			}else {
				this.xDataObj.addValue(tokens.get(0));
				for(int i=0;i<tokens.size()-1;i++){
					this.yDataObjList.get(i).addValue(tokens.get(i+1));
				}
			}
			
			
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		this.iterationName = myUtil.getFileNameIncludeExtension(this.filePath).replace(this.resultType, "");
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public String getGraphTitle() {
		return graphTitle;
	}
	public void setGraphTitle(String graphTitle) {
		this.graphTitle = graphTitle;
	}
	public String getIterationName() {
		return iterationName;
	}
	public void setIterationName(String iterationName) {
		this.iterationName = iterationName;
	}

	public ColumnData getxDataObj() {
		// Theta data 
		return xDataObj;
	}

	public ArrayList<ColumnData> getyDataObjList() {
		return yDataObjList;
	}

	/////////////////////////////////////////////////////////////////////////////////////
	// inner class
	public class ColumnData{
		
		private String dataName;
		private ArrayList<String> valueList;
		 
		
		public ColumnData(){
			this.valueList = new ArrayList<String>();
		}
		
		public void setDataName(String name){
			this.dataName = name;
		}
		
		public void addValue(String value){
			this.valueList.add(value);
		}
		
		public ArrayList<String> getValueList(){
			// valuse
			return this.valueList;
		}

		public String getDataName() {
			// graph label name
			return dataName;
		}
		
	}

}
