package com.js.plot;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	private final String GRAPH_TYPE_Radius = "Radius";
	private final String GRAPH_TYPE_Height = "Height";
	
	
	
	private String graphType;
	
	private String title;
	private String xAxisTitle;
	private String yAxisTitle;
	private ArrayList<String> legendList;
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
		
		
		if(med.getBtnConditioner().getSelection()){
			if(med.getBtnRadius().getSelection()){
				this.graphType = GRAPH_TYPE_Radius;
				this.title = "Conditioner Error(Radial)";
				this.xAxisTitle = "Theta(Turn)";
				this.yAxisTitle = "Radius(mm)";
			}else if(med.getBtnHeight().getSelection()){
				this.graphType = GRAPH_TYPE_Height;
				this.title = "Conditioner Error(Height)";
				this.xAxisTitle = "Theta(Turn)";
				this.yAxisTitle = "Height(mm)";
			}
		}
		
		else if(med.getBtnError().getSelection()){
			if(med.getBtnRadius().getSelection()){
				this.graphType = GRAPH_TYPE_Radius;
				this.title = "Design-Set Error(Radial)";
				this.xAxisTitle = "Theta(Turn)";
				this.yAxisTitle = "Radius(mm)";
			}else if(med.getBtnHeight().getSelection()){
				this.graphType = GRAPH_TYPE_Height;
				this.title = "Design-Set Error(Height)";
				this.xAxisTitle = "Theta(Turn)";
				this.yAxisTitle = "Height(mm)";
			}
		}else if(med.getBtnFormSetError().getSelection()){
			if(med.getBtnRadius().getSelection()){
				this.graphType = GRAPH_TYPE_Radius;
				this.title = "Form-Set Error(Radial)";
				this.xAxisTitle = "Theta(Turn)";
				this.yAxisTitle = "Radius(mm)";
			}else if(med.getBtnHeight().getSelection()){
				this.graphType = GRAPH_TYPE_Height;
				this.title = "Form-Set Error(Height)";
				this.xAxisTitle = "Theta(Turn)";
				this.yAxisTitle = "Height(mm)";
			}
		}
		
		else if(med.getBtnPitch_IR().getSelection()){
			//System.out.println(this.coilDBObj.getSelectedGraphList().get(0).getName());
			this.title = this.getfileType(this.coilDBObj.getSelectedGraphList().get(0).getName());
			this.xAxisTitle = "Theta(Turn)";
			this.yAxisTitle = "Height(mm)";
		}else if(med.getBtnRadius_IR().getSelection()){
			this.title = this.getfileType(this.coilDBObj.getSelectedGraphList().get(0).getName());
			this.xAxisTitle = "Theta(Turn)";
			this.yAxisTitle = "Radius(mm)";
		}
		
		else if(med.getBtnMaximumError().getSelection()){
			this.title = "Maximum Iteration Error";
			this.xAxisTitle = "Iteration Number";
			this.yAxisTitle = "Maximum Error(mm)";
		}else if(med.getBtnFormDataTotal().getSelection()){
			this.title = "Coil Geometry - Formdata Total";
			this.xAxisTitle = "Theta(turn)";
			this.yAxisTitle = "Outer Dia / Height(mm)";
		}
		
		this.setXData();
		this.setYData();
		
		/*
		// 2018.03_update
		if(med.getBtnEtc().getSelection()){
			this.xAxisTitle = "Theta(Turn)";
			
			String type = this.coilDBObj.getSelectedGraphList().get(0).getGraph().getResultType();
			if(type.equals(GraphAllData.csetRezonedAlignEradType)){
				this.title = "Coil Geometry Radius - Cold setting";
				this.yAxisTitle = "Radius(mm)";
			}else if(type.equals(GraphAllData.csetRezonedAlignPtichType)){
				this.title = "Coil Geometry Pitch - Cold setting";
				this.yAxisTitle = "Height(mm)";
			}else if(type.equals(GraphAllData.formedRezonedAlignEradType)){
				this.title = "Coil Geometry Radius -  Formdata";
				this.yAxisTitle = "Radius(mm)";
			}else if(type.equals(GraphAllData.formedRezonedAlignPtichType)){
				this.title = "Coil Geometry Pitch - Formdata";
				this.yAxisTitle = "Height(mm)";
			}else if(type.equals(GraphAllData.hsetRezonedAlignErad)){
				this.title = "Coil Geometry Radius - Hot setting";
				this.yAxisTitle = "Radius(mm)";
			}else if(type.equals(GraphAllData.hsetRezonedAlignPtich)){
				this.title = "Coil Geometry Pitch - Hot setting";
				this.yAxisTitle = "Height(mm)";
			}else{
				this.title = "temp title";
				this.yAxisTitle = "temp y axis";				
			}
			
		}else if(med.getBtnEtc2().getSelection()){
			this.xAxisTitle = "Theta(Turn)";
			this.title = "Coil Geometry Formdata";					
			this.yAxisTitle = "Outer Dia/Height(mm)";
		}
		
		else if(med.getBtnMaximumError().getSelection()){
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
		//*/
	}
	
	private void setXData(){
		// set xValue
		// 2018.03_update
		
		if(med.getBtnConditioner().getSelection()){
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
		
		else if(med.getBtnError().getSelection()){
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
		}else if(med.getBtnFormSetError().getSelection()){
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
		
		else if(med.getBtnPitch_IR().getSelection()){
			ArrayList<Float> tempValueList =new ArrayList<Float>();
			//System.out.println("etc1 selected graph size : "+this.coilDBObj.getSelectedGraphList().size());
			for(int i=0; i<this.coilDBObj.getSelectedGraphList().size(); i++){
				//this.coilDBObj.getSelectedGraphList().get(0).getGraph().getResultType()
				for(int j=0; j<this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().size();j++){
					String strValue = this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().get(j);
					float value = Float.parseFloat(strValue);
					tempValueList.add(value);
				}
				this.xDataList.add(tempValueList);
				//this.xDataList.add(tempValueList);
			}
		}else if(med.getBtnRadius_IR().getSelection()){
			ArrayList<Float> tempValueList =new ArrayList<Float>();
			//System.out.println("etc1 selected graph size : "+this.coilDBObj.getSelectedGraphList().size());
			for(int i=0; i<this.coilDBObj.getSelectedGraphList().size(); i++){
				//this.coilDBObj.getSelectedGraphList().get(0).getGraph().getResultType()
				for(int j=0; j<this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().size();j++){
					String strValue = this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().get(j);
					float value = Float.parseFloat(strValue);
					tempValueList.add(value);
				}
				this.xDataList.add(tempValueList);
				//this.xDataList.add(tempValueList);
			}
		}
		
		else if(med.getBtnMaximumError().getSelection()){
			ArrayList<Float> tempValueList = new ArrayList<Float>();
			for(int i=0;i<this.coilDBObj.getSelectedGraphList().size();i++){
				
				for(int j=0;j<this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().size();j++){
					String strValue = this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().get(j);	
					float value = Float.parseFloat(strValue);
					tempValueList.add(value);
				}
				this.xDataList.add(tempValueList);
				this.xDataList.add(tempValueList);
			}
			
		}else if(med.getBtnFormDataTotal().getSelection()){
			ArrayList<Float> tempValueList =new ArrayList<Float>();
			//System.out.println("etc2 selected graph size : "+this.coilDBObj.getSelectedGraphList().size());
			for(int i=0; i<this.coilDBObj.getSelectedGraphList().size(); i++){
				
				for(int j=0; j<this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().size();j++){
					String strValue = this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().get(j);
					//System.out.println(strValue);
					float value = Float.parseFloat(strValue);
					tempValueList.add(value);
				}
				this.xDataList.add(tempValueList);
				this.xDataList.add(tempValueList);
			}
		}
		
		/*
		if(med.getBtnEtc().getSelection()){
			ArrayList<Float> tempValueList =new ArrayList<Float>();
			//System.out.println("etc1 selected graph size : "+this.coilDBObj.getSelectedGraphList().size());
			for(int i=0; i<this.coilDBObj.getSelectedGraphList().size(); i++){
				//this.coilDBObj.getSelectedGraphList().get(0).getGraph().getResultType()
				for(int j=0; j<this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().size();j++){
					String strValue = this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().get(j);
					float value = Float.parseFloat(strValue);
					tempValueList.add(value);
				}
				this.xDataList.add(tempValueList);
				this.xDataList.add(tempValueList);
			}
			
		}else if(med.getBtnEtc2().getSelection()){
			ArrayList<Float> tempValueList =new ArrayList<Float>();
			//System.out.println("etc2 selected graph size : "+this.coilDBObj.getSelectedGraphList().size());
			for(int i=0; i<this.coilDBObj.getSelectedGraphList().size(); i++){
				
				for(int j=0; j<this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().size();j++){
					String strValue = this.coilDBObj.getSelectedGraphList().get(i).getGraph().getxDataObj().getValueList().get(j);
					//System.out.println(strValue);
					float value = Float.parseFloat(strValue);
					tempValueList.add(value);
				}
				this.xDataList.add(tempValueList);
				this.xDataList.add(tempValueList);
			}
			
		}
		
		else if(med.getBtnMaximumError().getSelection()){
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
		//*/
		
	}
	
	private void setYData(){
		ArrayList<Float> normalValueList;
		ArrayList<Float> pitch_radiusList;
		ArrayList<Float> maxErrorRList;
		ArrayList<Float> maxErrorZList;
		//ArrayList<Float> formDataTotalList;
		ArrayList<Float> formDataTotal_externalRadiusList;
		ArrayList<Float> formDataTotal_externalPitchList;
		
		for(ListData_selectedGraph obj : this.coilDBObj.getSelectedGraphList()){
			ComboData_selectGraph graphDataObj = obj.getGraph(); 
			//System.out.println("Type : "+graphDataObj.getResultType());
			
			normalValueList = new ArrayList<Float>();
			maxErrorRList = new ArrayList<Float>();
			maxErrorZList = new ArrayList<Float>();
			pitch_radiusList = new ArrayList<Float>();
			//formDataTotalList = new ArrayList<Float>();
			formDataTotal_externalRadiusList = new ArrayList<Float>();
			formDataTotal_externalPitchList = new ArrayList<Float>();
			
			if(graphDataObj.getResultType().equals(GraphAllData.Type_conditioner)){
				if(this.graphType.equals(GRAPH_TYPE_Radius)){
					this.legendList.add(graphDataObj.getyLegend_Radius());
					for(Float f : graphDataObj.getyDataValue_Radius()){
						normalValueList.add(f);
					}
				}else {
					this.legendList.add(graphDataObj.getyLegend_Height());
					for(Float f : graphDataObj.getyDataValue_Height()){
						normalValueList.add(f);
					}
				}
				this.yDataList.add(normalValueList);
			}else if(graphDataObj.getResultType().equals(GraphAllData.Type_error)){
				if(this.graphType.equals(GRAPH_TYPE_Radius)){
					this.legendList.add(graphDataObj.getyLegend_Radius());
					for(Float f : graphDataObj.getyDataValue_Radius()){
						normalValueList.add(f);
					}
				}else {
					this.legendList.add(graphDataObj.getyLegend_Height());
					for(Float f : graphDataObj.getyDataValue_Height()){
						normalValueList.add(f);
					}
				}
				this.yDataList.add(normalValueList);
			}else if(graphDataObj.getResultType().equals(GraphAllData.Type_formSetError)){
				if(this.graphType.equals(GRAPH_TYPE_Radius)){
					this.legendList.add(graphDataObj.getyLegend_Radius());
					for(Float f : graphDataObj.getyDataValue_Radius()){
						normalValueList.add(f);
					}
				}else {
					this.legendList.add(graphDataObj.getyLegend_Height());
					for(Float f : graphDataObj.getyDataValue_Height()){
						normalValueList.add(f);
					}
				}
				this.yDataList.add(normalValueList);
			}
			
			
			else if(graphDataObj.getResultType().equals(GraphAllData.Type_pitch)){
				this.legendList.add(graphDataObj.getyLegend_pitch_radius());
				//this.legendList.add(graphDataObj.getyLegend_etcC3());
				for(Float f : graphDataObj.getyDataValue_pitch_radius()){
					pitch_radiusList.add(f);
				}
				/*
				for(Float f : graphDataObj.getyDataValue_etcC3()){
					formDataTotalList.add(f);
				}
				//*/
				this.yDataList.add(pitch_radiusList);
				//this.yDataList.add(formDataTotalList);
			}
			
			/*
			else if(graphDataObj.getResultType().equals(GraphAllData.Type_pitch)){
				this.legendList.add(graphDataObj.getyLegend_pitch_radius());
				//this.legendList.add(graphDataObj.getyLegend_etcC3());
				for(Float f : graphDataObj.getyDataValue_pitch_radius()){
					pitch_radiusList.add(f);
				}
				
				this.yDataList.add(pitch_radiusList);
				//this.yDataList.add(formDataTotalList);
			}else if(graphDataObj.getResultType().equals(GraphAllData.Type_pitch)){
				this.legendList.add(graphDataObj.getyLegend_pitch_radius());
				//this.legendList.add(graphDataObj.getyLegend_etcC3());
				for(Float f : graphDataObj.getyDataValue_pitch_radius()){
					pitch_radiusList.add(f);
				}
				
				this.yDataList.add(pitch_radiusList);
				//this.yDataList.add(formDataTotalList);
			}
			//*/
			
			else if(graphDataObj.getResultType().equals(GraphAllData.Type_radius)){
				this.legendList.add(graphDataObj.getyLegend_pitch_radius());
				//this.legendList.add(graphDataObj.getyLegend_etcC3());
				for(Float f : graphDataObj.getyDataValue_pitch_radius()){
					pitch_radiusList.add(f);
				}
				/*
				for(Float f : graphDataObj.getyDataValue_etcC3()){
					formDataTotalList.add(f);
				}
				//*/
				this.yDataList.add(pitch_radiusList);
				//this.yDataList.add(formDataTotalList);
			}
			/*
			else if(graphDataObj.getResultType().equals(GraphAllData.Type_radius)){
				this.legendList.add(graphDataObj.getyLegend_pitch_radius());
				for(Float f : graphDataObj.getyDataValue_pitch_radius()){
					pitch_radiusList.add(f);
				}
				this.yDataList.add(pitch_radiusList);
			}else if(graphDataObj.getResultType().equals(GraphAllData.Type_radius)){
				this.legendList.add(graphDataObj.getyLegend_pitch_radius());
				for(Float f : graphDataObj.getyDataValue_pitch_radius()){
					pitch_radiusList.add(f);
				}
				this.yDataList.add(pitch_radiusList);
			}
			// */
			
			else if(graphDataObj.getResultType().equals(GraphAllData.Type_maximumError)){
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
			
			else if(graphDataObj.getResultType().equals(GraphAllData.Type_formDataTotal)){
				this.legendList.add(graphDataObj.getyLegend_externalRadius());
				this.legendList.add(graphDataObj.getyLegend_externalPitch());
				for(Float f : graphDataObj.getyDataValue_externalRadius()){
					//System.out.println("C2 : "+f);
					formDataTotal_externalRadiusList.add(f);
				}
				for(Float f : graphDataObj.getyDataValue_externalPitch()){
					//System.out.println("C3 : "+f);
					formDataTotal_externalPitchList.add(f);
				}
				this.yDataList.add(formDataTotal_externalRadiusList);
				this.yDataList.add(formDataTotal_externalPitchList);
			}
			
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
	
	
	
	
	private String getfileType(String fileName){
		String type = null;
		
		if(this.isMatch_csetRezonedAlignPitchType(fileName)){
			type =  "Coil Geometry Pitch - Cold Setting";
		}
		
		if(this.isMatch_hsetRezonedAlignPitchType(fileName)){
			type =  "Coil Geometry Pitch - Hot Setting";
		}
		
		if(this.isMatch_formedRezonedAlignPitchType(fileName)){
			type =  "Coil Geometry Pitch - Form data";
		}
		
		if(this.isMatch_csetRezonedAlignEradType(fileName)){
			type =  "Coil Geometry Radius - Cold Setting";
		}
		
		if(this.isMatch_hsetRezonedAlignEradType(fileName)){
			type =  "Coil Geometry Radius - Hot Setting";
		}
		
		if(this.isMatch_formedRezonedAlignEradType(fileName)){
			type =  "Coil Geometry Radius - Form data";
		}
		
		return type;
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
	
	

}
