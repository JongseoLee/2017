package com.js.ens.coil.core;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.wb.swt.SWTResourceManager;

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
		Reader reader = new Reader(this.csvFilePath);
		reader.running();
		ArrayList<String> fileDataList = new ArrayList<String>();
		fileDataList = reader.getFileDataList();
		
		this.coilDBObj = new CoilDB(); 

		for(String line : fileDataList){
			ArrayList<String> tokens = new ArrayList<String>();			
			tokens = myUtil.splitData(line, ",");
			//System.out.println("=>" + tokens.get(0));
			if(tokens.get(0).trim().equals(CoilDataLabel.ProductName)){
				this.coilDBObj.setProductName(tokens.get(1));
				//System.out.println("tokens :" + tokens.get(1));
				med.getTextProductName().setText(this.coilDBObj.getProductName());
			}else if(tokens.get(0).trim().equals(CoilDataLabel.LineDiameter)){
				this.coilDBObj.setLineDiameter(tokens.get(1));
				med.getTextLineDiameter().setText(this.coilDBObj.getLineDiameter());
			}else if(tokens.get(0).trim().equals(CoilDataLabel.CenterDiameter)){
				this.coilDBObj.setCenterDiameter(tokens.get(1));
				med.getTextCenterDiameter().setText(this.coilDBObj.getCenterDiameter());
			}else if(tokens.get(0).trim().equals(CoilDataLabel.InnerDiameter)){
				this.coilDBObj.setInnerDiameter(tokens.get(1));
				med.getTextInnerDiameter().setText(this.coilDBObj.getInnerDiameter());
			}else if(tokens.get(0).trim().equals(CoilDataLabel.OuterDiameter)){
				this.coilDBObj.setOuterDiameter(tokens.get(1));
				med.getTextOuterDiameter().setText(this.coilDBObj.getOuterDiameter());
			}else if(tokens.get(0).trim().equals(CoilDataLabel.UpperInnerDiameter)){
				this.coilDBObj.setUpperInnerDiameter(tokens.get(1));
				med.getTextUpperInnerDiameter().setText(this.coilDBObj.getUpperInnerDiameter());
			}else if(tokens.get(0).trim().equals(CoilDataLabel.LowerInnerDiameter)){
				this.coilDBObj.setLowerInnerDiameter(tokens.get(1));
				med.getTextLowerInnerDiameter().setText(this.coilDBObj.getLowerInnerDiameter());
			}else if(tokens.get(0).trim().equals(CoilDataLabel.TotalNumber)){
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
		//med.getStackLayout().topControl = med.getCompositeStep3();
		//med.getCompositeBottom().layout();
		
		med.getLblModeling().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblSimulationAndExportResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblShowResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
	}
	
}
