package com.js.ens.coil.core;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.wb.swt.SWTResourceManager;

import com.js.ens.coil.customWidget.ComboData_selectGraph;
import com.js.ens.coil.customWidget.ComboData_selectImage;
import com.js.ens.coil.customWidget.ComboData_selectTableData;
import com.js.ens.coil.customWidget.ComboViewerLabelProvider_SelectGraph;
import com.js.ens.coil.customWidget.ComboViewerLabelProvider_SelectImage;
import com.js.ens.coil.customWidget.ComboViewerLabelProvider_SelectTableData;
import com.js.ens.coil.customWidget.TableData_Coil;
import com.js.ens.coil.customWidget.TableViewerLabelProvider_Coil;
import com.js.ens.coil.db.CoilDB;
import com.js.ens.coil.db.CoilDataLabel;
import com.js.io.Reader;
import com.js.util.myUtil;

public class MainController {
	private static MainController instance = new MainController();
	public static MainController getInstatnce(){
		return instance;
	}
	
	private String AppPath; 
	public String getAppPath(){
		if(myUtil.checkOS().equals("window")){
			this.AppPath = System.getProperty("user.dir"); 
		}else{
			this.AppPath = "/Users/jslee/CodeFactory/Git/2017/2017";
		}
		return this.AppPath;
	}
	
	private Mediator med = Mediator.getInstance();
	
	
	////////////////////////////////////////////
	private String csvFilePath = "";
	private CoilDB coilDBObj;
	////////////////////////////////////////////
	
	
	public MainController(){
		this.coilDBObj = new CoilDB(); 
	}
	
	
	public void Button_StepSave(){
		this.csvFilePath = this.FileExplorer_CoilData();
		while(true){
			if(myUtil.checkPath(this.csvFilePath)){
				med.getTextCoilFilePath().setText(this.csvFilePath);
				break;
			}else{
				this.csvFilePath = this.FileExplorer_CoilData();
			}
		}
		
		this.readCoilDataFile();
		this.UpdateCoilGeometryData();
	}
	
	private String FileExplorer_CoilData(){
		FileDialog dlg = new FileDialog(med.getBtnExplorer().getShell(),SWT.OPEN);
		dlg.setText("Select Coil data CSV file.");
		
		String [] extNames = {"CSV(*.csv)"};
		String [] extType = {"*.csv"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		// ������ ��ġ���� ���� ������ ���µ�
		//dlg.setFilterPath(this.getAppPath());
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return "Retry";
		}else {
			return path;
		}
	}
	
	private void readCoilDataFile(){
		
		CoilDataLabel coilDataIndeObj = new CoilDataLabel();
		
		Reader reader = new Reader(this.csvFilePath);
		reader.running();
		ArrayList<String> fileDataList = new ArrayList<String>();
		fileDataList = reader.getFileDataList();
		
		

		for(String line : fileDataList){
			ArrayList<String> tokens = new ArrayList<String>();			
			tokens = myUtil.splitData(line, ",");
			//System.out.println("=>" + tokens.get(0));
			//System.out.println(coilDataIndeObj.getLabel(CoilDataLabel.ProductName));
			if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabel.ProductName))){
				this.coilDBObj.setProductName(tokens.get(1));
				//System.out.println("tokens :" + tokens.get(1));
				med.getTextProductName().setText(this.coilDBObj.getProductName());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabel.LineDiameter))){
				this.coilDBObj.setLineDiameter(tokens.get(1));
				med.getTextLineDiameter().setText(this.coilDBObj.getLineDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabel.CenterDiameter))){
				this.coilDBObj.setCenterDiameter(tokens.get(1));
				med.getTextCenterDiameter().setText(this.coilDBObj.getCenterDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabel.InnerDiameter))){
				this.coilDBObj.setInnerDiameter(tokens.get(1));
				med.getTextInnerDiameter().setText(this.coilDBObj.getInnerDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabel.OuterDiameter))){
				this.coilDBObj.setOuterDiameter(tokens.get(1));
				med.getTextOuterDiameter().setText(this.coilDBObj.getOuterDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabel.UpperInnerDiameter))){
				this.coilDBObj.setUpperInnerDiameter(tokens.get(1));
				med.getTextUpperInnerDiameter().setText(this.coilDBObj.getUpperInnerDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabel.LowerInnerDiameter))){
				this.coilDBObj.setLowerInnerDiameter(tokens.get(1));
				med.getTextLowerInnerDiameter().setText(this.coilDBObj.getLowerInnerDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabel.TotalNumber))){
				this.coilDBObj.setTotalNumber(tokens.get(1));
				med.getTextTotalNumber().setText(this.coilDBObj.getTotalNumber());
				
			}else if(tokens.size() == 5 && myUtil.CheckFloatValue(tokens.get(0))){
				TableData_Coil obj = new TableData_Coil();
				obj.setTheta(tokens.get(4));
				obj.setRadius(tokens.get(3));
				obj.setHeight(tokens.get(2));
				this.coilDBObj.add_CoilDataTable(obj);
			}
		}
	}
	
	private void UpdateCoilGeometryData(){
		try{
			med.getTableViewerCoilTable().setLabelProvider(new TableViewerLabelProvider_Coil());
			med.getTableViewerCoilTable().setContentProvider(new ArrayContentProvider());
			med.getTableViewerCoilTable().setInput(this.coilDBObj.getGeometryDataTableList());
			
		}catch(Exception e){
			System.out.println("ERROR - Coil data table");
		}
	}

	public void UpdateSelectGrpahData(){
		///////////////////////////////////////////////////////////////
		// DEMO Data --> todo... access graph data folder
		if(!this.coilDBObj.getGraphDataList().isEmpty()){
			this.coilDBObj.getGraphDataList().clear();
		}
		for(int i = 0; i<10 ;i++){
			ComboData_selectGraph obj = new ComboData_selectGraph();
			obj.setName("Graph - "+(i+1));
			this.coilDBObj.add_GraphDataCombo(obj);
		}
		//
		///////////////////////////////////////////////////////////////
		try{
			med.getComboViewerSelectGraph().setLabelProvider(new ComboViewerLabelProvider_SelectGraph());
			med.getComboViewerSelectGraph().setContentProvider(new ArrayContentProvider());
			med.getComboViewerSelectGraph().setInput(this.coilDBObj.getGraphDataList());
		}catch(Exception e){
			System.out.println("ERROR - Graph data");
		}
	}
	
	public void UpdateSelectImageData(){
		///////////////////////////////////////////////////////////////
		// DEMO Data --> todo... access Image data folder
		if(!this.coilDBObj.getImageDataList().isEmpty()){
			this.coilDBObj.getImageDataList().clear();
		}
		for(int i = 0; i<10 ;i++){
			ComboData_selectImage obj = new ComboData_selectImage();
			obj.setName("image - "+(i+1));
			this.coilDBObj.add_ImageDataCombo(obj);
		}
		//
		///////////////////////////////////////////////////////////////
		try{
			med.getComboViewerSelectImage().setLabelProvider(new ComboViewerLabelProvider_SelectImage());
			med.getComboViewerSelectImage().setContentProvider(new ArrayContentProvider());
			med.getComboViewerSelectImage().setInput(this.coilDBObj.getImageDataList());
		}catch(Exception e){
			System.out.println("ERROR - Image data");
		}
	}
	
	public void UpdateSelectTableData(){
		///////////////////////////////////////////////////////////////
		// DEMO Data --> todo... access Image data folder
		if(!this.coilDBObj.getTabelDataList().isEmpty()){
			this.coilDBObj.getTabelDataList().clear();
		}
		for(int i = 0; i<10 ;i++){
			ComboData_selectTableData obj = new ComboData_selectTableData();
			obj.setName("table Data - "+(i+1));
			this.coilDBObj.add_TableDataCombo(obj);
		}
		//
		///////////////////////////////////////////////////////////////
		try{
			med.getComboViewerSelectTableData().setLabelProvider(new ComboViewerLabelProvider_SelectTableData());
			med.getComboViewerSelectTableData().setContentProvider(new ArrayContentProvider());
			med.getComboViewerSelectTableData().setInput(this.coilDBObj.getTabelDataList());
		}catch(Exception e){
			System.out.println("ERROR - Table data select Table Data");
		}
	}
	
	
	public void ChangeProcessStep1() {
		// TODO Auto-generated method stub
		med.getStackLayout().topControl = med.getCompositeStep1();
		med.getCompositeBottom().layout();
		
		med.getLblModeling().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		med.getLblSimulationAndExportResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblShowResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		
	}


	public void ChangeProcessStep2() {
		// TODO Auto-generated method stub
		med.getStackLayout().topControl = med.getCompositeStep2();
		med.getCompositeBottom().layout();
		
		med.getLblModeling().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblSimulationAndExportResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		med.getLblShowResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
	}


	public void ChangeProcessStep3() {
		// TODO Auto-generated method stub
		med.getStackLayout().topControl = med.getCompositeStep3();
		med.getCompositeBottom().layout();
		
		med.getLblModeling().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblSimulationAndExportResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblShowResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		// load combo data - graph
		this.UpdateSelectGrpahData();
		// load combo data - image
		this.UpdateSelectImageData();
		// load combo data - tableData
		this.UpdateSelectTableData();
		
	}


	public void Combo_selectGraph() {
		// TODO Auto-generated method stub
		System.out.println(med.getComboViewerSelectGraph().getCombo().getText());
	}


	public void Combo_selectImage() {
		// TODO Auto-generated method stub
		System.out.println(med.getComboViewerSelectImage().getCombo().getText());
	}


	public void Combo_selectTableData() {
		// TODO Auto-generated method stub
		System.out.println(med.getComboViewerSelectTableData().getCombo().getText());
	}
	
}
