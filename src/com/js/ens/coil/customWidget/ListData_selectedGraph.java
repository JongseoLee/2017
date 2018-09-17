package com.js.ens.coil.customWidget;


public class ListData_selectedGraph {
	private String name;
	
	private ComboData_selectGraph obj;
	public ListData_selectedGraph() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void setGraph(ComboData_selectGraph obj){
		this.obj = obj;
	}
	
	public ComboData_selectGraph getGraph(){
		// CSV file obj
		return this.obj;
	}
	
	
	public String getName(){
		// Label - fileName
		return this.obj.getName();
	}
	
	public String getResultType(){
		return this.obj.getResultType();
	}
}
