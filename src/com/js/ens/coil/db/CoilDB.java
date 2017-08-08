package com.js.ens.coil.db;

import java.util.ArrayList;

import com.js.ens.coil.customWidget.ComboData_selectGraph;
import com.js.ens.coil.customWidget.ComboData_selectImage;
import com.js.ens.coil.customWidget.ComboData_selectTableData;
import com.js.ens.coil.customWidget.TableData_Coil;

public class CoilDB {
	
	private String ProductName;
	private String LineDiameter;
	private String CenterDiameter;
	private String InnerDiameter;
	private String OuterDiameter;
	private String UpperInnerDiameter;
	private String LowerInnerDiameter;
	private String TotalNumber;
	
	private ArrayList<TableData_Coil> GeometryDataTableList;
	private ArrayList<ComboData_selectGraph> graphDataList;
	private ArrayList<ComboData_selectImage> imageDataList;
	private ArrayList<ComboData_selectTableData> tabelDataList;
	
	
	public CoilDB() {
		// TODO Auto-generated constructor stub
		this.GeometryDataTableList = new ArrayList<TableData_Coil>();
		this.graphDataList = new ArrayList<ComboData_selectGraph>();
		this.imageDataList = new ArrayList<ComboData_selectImage>();
		this.tabelDataList = new ArrayList<ComboData_selectTableData>();
	}
	
	public void add_CoilDataTable(TableData_Coil obj){
		this.GeometryDataTableList.add(obj);
	}
	
	public void add_GraphDataCombo(ComboData_selectGraph obj){
		this.graphDataList.add(obj);
	}
	
	public void add_ImageDataCombo(ComboData_selectImage obj){
		this.imageDataList.add(obj);
	}
	
	public void add_TableDataCombo(ComboData_selectTableData obj){
		this.tabelDataList.add(obj);
	}
	
	
	
	
	
	
	
	
	public String getProductName() {
		return ProductName;
	}


	public void setProductName(String productName) {
		ProductName = productName;
	}


	public String getLineDiameter() {
		return LineDiameter;
	}


	public void setLineDiameter(String lineDiameter) {
		LineDiameter = lineDiameter;
	}


	public String getCenterDiameter() {
		return CenterDiameter;
	}


	public void setCenterDiameter(String centerDiameter) {
		CenterDiameter = centerDiameter;
	}


	public String getInnerDiameter() {
		return InnerDiameter;
	}


	public void setInnerDiameter(String innerDiameter) {
		InnerDiameter = innerDiameter;
	}


	public String getOuterDiameter() {
		return OuterDiameter;
	}


	public void setOuterDiameter(String outerDiameter) {
		OuterDiameter = outerDiameter;
	}


	public String getUpperInnerDiameter() {
		return UpperInnerDiameter;
	}


	public void setUpperInnerDiameter(String upperInnerDiameter) {
		UpperInnerDiameter = upperInnerDiameter;
	}


	public String getLowerInnerDiameter() {
		return LowerInnerDiameter;
	}


	public void setLowerInnerDiameter(String lowerInnerDiameter) {
		LowerInnerDiameter = lowerInnerDiameter;
	}


	public String getTotalNumber() {
		return TotalNumber;
	}


	public void setTotalNumber(String totalNumber) {
		TotalNumber = totalNumber;
	}



	public ArrayList<TableData_Coil> getGeometryDataTableList() {
		return GeometryDataTableList;
	}


	public void setGeometryDataTableList(ArrayList<TableData_Coil> geometryDataTableList) {
		GeometryDataTableList = geometryDataTableList;
	}

	public ArrayList<ComboData_selectGraph> getGraphDataList() {
		return graphDataList;
	}

	public void setGraphDataList(ArrayList<ComboData_selectGraph> graphDataList) {
		this.graphDataList = graphDataList;
	}

	public ArrayList<ComboData_selectImage> getImageDataList() {
		return imageDataList;
	}

	public void setImageDataList(ArrayList<ComboData_selectImage> imageDataList) {
		this.imageDataList = imageDataList;
	}

	public ArrayList<ComboData_selectTableData> getTabelDataList() {
		return tabelDataList;
	}

	public void setTabelDataList(ArrayList<ComboData_selectTableData> tabelDataList) {
		this.tabelDataList = tabelDataList;
	}

}
