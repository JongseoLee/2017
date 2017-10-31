package com.js.plot;

import java.util.ArrayList;

import com.js.ens.coil.core.MainController;
import com.js.ens.coil.core.Mediator;
import com.js.ens.coil.customWidget.ComboData_selectGraph;
import com.js.ens.coil.customWidget.ListData_selectedGraph;
import com.js.ens.coil.customWidget.ComboData_selectGraph.ColumnData;
import com.js.ens.coil.db.CoilDB;

public class CreatePlotData {
	private MainController MC = MainController.getInstatnce();
	private Mediator med = Mediator.getInstance();
	private CoilDB coilDBObj;
	
	/*
	private final String title_Condtioner = "Conditioner";
	private final String title_Error = "Error";
	private final String title_FormSetError = "Form set error";
	//*/
	
	private final String GRAPH_TYPE_Radius = "Radius";
	private final String GRAPH_TYPE_Height = "Height";
	
	
	
	private String graphType;
	
	private String title;
	private ArrayList<String> legendList;
	private String xAxisTitle;
	private String yAxisTitle;
	private ArrayList<ArrayList<Float>> xDataList;
	private ArrayList<ArrayList<Float>> yDataList;
	
	
	public CreatePlotData() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(CoilDB cobj){
		this.coilDBObj = cobj;
		this.legendList = new ArrayList<String>();
		this.xDataList = new ArrayList<ArrayList<Float>>();
		this.yDataList = new ArrayList<ArrayList<Float>>();
		if(med.getBtnMaximumError().getSelection()){
			this.xAxisTitle = "Iteration Number";
			this.title = "Maximum Iteration Error";
			this.yAxisTitle = "Maximum Error(mm)";
		}else{
			this.xAxisTitle = "Theta (turn)";
			if(med.getBtnRadius().getSelection()){
				//Radius
				this.graphType = GRAPH_TYPE_Radius;
				this.title = "Radial Error";
				this.yAxisTitle = "Radius (mm)";
			}else { 
				//Height
				this.graphType = GRAPH_TYPE_Height;
				this.title = "Height Error";
				this.yAxisTitle = "Height (mm)";
			}
		}
		
		
		this.setXData();
		this.setYData();
		
	}
	
	private void setXData(){
		// set xValue
		if(med.getBtnMaximumError().getSelection()){
			ArrayList<Float> tempValueList = new ArrayList<Float>();
			for(int i=0;i<this.coilDBObj.getSelectedGraphList().size();i++){
				
				for(int j=0;j<this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().size();j++){
					String strValue = this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().get(j);	
					float value = Float.parseFloat(strValue);
					tempValueList.add(value);
				}
				this.xDataList.add(tempValueList);
			}
			this.xDataList.add(tempValueList);
		}else{
			ArrayList<Float> tempValueList;
			for(int i=0;i<this.coilDBObj.getSelectedGraphList().size();i++){
				tempValueList = new ArrayList<Float>();
				for(int j=0;j<this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().size();j++){
					String strValue = this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().get(j);	
					float value = Float.parseFloat(strValue);
					tempValueList.add(value);
				}
				this.xDataList.add(tempValueList);
			}	
		}
	}
	
	private void setYData(){
		ArrayList<Float> tempValueList;
		ArrayList<Float> maxErrorRList;
		ArrayList<Float> maxErrorZList;
		
		for(ListData_selectedGraph obj : this.coilDBObj.getSelectedGraphList()){
			ComboData_selectGraph graphDataObj = obj.getGraph(); 
			tempValueList = new ArrayList<Float>();
			maxErrorRList = new ArrayList<Float>();
			maxErrorZList = new ArrayList<Float>();
			
			if(graphDataObj.getResultType().equals(GraphAllData.conditionerType)){
				if(this.graphType.equals(GRAPH_TYPE_Radius)){
					this.legendList.add(graphDataObj.getyLegend_Radius());
					for(Float f : graphDataObj.getyDataValue_Radius()){
						tempValueList.add(f);
					}
				}else {
					this.legendList.add(graphDataObj.getyLegend_Height());
					for(Float f : graphDataObj.getyDataValue_Height()){
						tempValueList.add(f);
					}
				}
				this.yDataList.add(tempValueList);
			}else if(graphDataObj.getResultType().equals(GraphAllData.errorType)){
				if(this.graphType.equals(GRAPH_TYPE_Radius)){
					this.legendList.add(graphDataObj.getyLegend_Radius());
					for(Float f : graphDataObj.getyDataValue_Radius()){
						tempValueList.add(f);
					}
				}else {
					this.legendList.add(graphDataObj.getyLegend_Height());
					for(Float f : graphDataObj.getyDataValue_Height()){
						tempValueList.add(f);
					}
				}
				this.yDataList.add(tempValueList);
			}else if(graphDataObj.getResultType().equals(GraphAllData.formSetErrorType)){
				if(this.graphType.equals(GRAPH_TYPE_Radius)){
					this.legendList.add(graphDataObj.getyLegend_Radius());
					for(Float f : graphDataObj.getyDataValue_Radius()){
						tempValueList.add(f);
					}
				}else {
					this.legendList.add(graphDataObj.getyLegend_Height());
					for(Float f : graphDataObj.getyDataValue_Height()){
						tempValueList.add(f);
					}
				}
				this.yDataList.add(tempValueList);
			}else if(graphDataObj.getResultType().equals(GraphAllData.maximumErrorType)){
				this.legendList.add(graphDataObj.getyLegend_MaxErrorR());
				this.legendList.add(graphDataObj.getyLegend_MaxErrorZ());
				for(Float f : graphDataObj.getyDataValue_MaxErrorR()){
					//System.out.println("Max R : " + f);
					maxErrorRList.add(f);
				}
				//System.out.println("=========================");
				for(Float f : graphDataObj.getyDataValue_MaxErrorZ()){
					//System.out.println("Max Z : " + f);
					maxErrorZList.add(f);
				}
				this.yDataList.add(maxErrorRList);
				this.yDataList.add(maxErrorZList);
			}
			//this.yDataList.add(tempValueList);
		}
	}

	public String getTitle() {
		return title;
	}

	public ArrayList<String> getLegendList() {
		return legendList;
	}

	public String getxAxisTitle() {
		return xAxisTitle;
	}

	public String getyAxisTitle() {
		return yAxisTitle;
	}

	public ArrayList<ArrayList<Float>> getxDataList() {
		return xDataList;
	}

	public ArrayList<ArrayList<Float>> getyDataList() {
		return yDataList;
	}
	
	
	
	

}
