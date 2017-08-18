package com.js.ens.coil.core;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
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
import com.js.ens.coil.db.WriteSimcosDB;
import com.js.ens.coil.dialog.MessageDlg;
import com.js.ens.coil.dialog.NewDlg;
import com.js.ens.coil.dialog.OpenDlg;
import com.js.ens.coil.dialog.PreferencesDlg;
import com.js.ens.coil.dialog.SaveAsDlg;
import com.js.ens.coil.dialog.SaveDlg;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class MainController {
	
	
	private static MainController instance = new MainController();
	public static MainController getInstatnce(){
		return instance;
	}
	
	private String AppPath;			// ~appPath/
	private String BaseWorkspace;	// ~appPath/Simcos_Workspace
	private String projectName;
	private String UserWorkspace;
	private String CurrentStep = CoilDB.STEP1;
	
	// Preferences values
	private String MarcPath;
	private String TextEditorPath;
	private String ExcelPath;
	private String Command;

	private Mediator med = Mediator.getInstance();
	
	
	////////////////////////////////////////////
	private CoilDB coilDBObj;
	private InitValue initValueObj;
	private AppFolder appFolderObj;
	private Preferences preferencesObj;
	////////////////////////////////////////////
	
	
	public MainController(){
		if(myUtil.checkOS().equals("window")){
			this.AppPath = System.getProperty("user.dir"); 
		}else{
			this.AppPath = "/Users/jslee/CodeFactory/Git/2017/2017";
		}
		this.BaseWorkspace = myUtil.setPath(this.AppPath, "Simcos_Workspace");
		
	}
		
	///////////////////////////////////////////
	// 
	// File, tool, setting menu event
	//
	public void File_New(){
		NewDlg newDlg = new NewDlg(Display.getCurrent().getActiveShell());
		newDlg.open();
	}
	
	public void File_Open(){
		OpenDlg openDlg = new OpenDlg(Display.getCurrent().getActiveShell());
		openDlg.open();
	}

	public void File_Save(){
		SaveDlg saveDlg = new SaveDlg(Display.getCurrent().getActiveShell());
		saveDlg.open();
	}
	
	public void File_SaveAs(){
		SaveAsDlg saveAsDlg = new SaveAsDlg(Display.getCurrent().getActiveShell());
		saveAsDlg.open();
	}
	
	public void Tool_TextEditor(){
		if(this.preferencesObj != null){
			this.Tool_TextEditor();
		}else{
			String msg = "If you want to use this menu, you have to create a project.\n\n  File->new \t or \t File->Open";
			MessageDlg msgDlg = new MessageDlg(med.getCompositeTop().getShell(),msg);
			msgDlg.open();
		}
		
	}
	
	public void Tool_Mentat(){
		if(this.preferencesObj != null){
			this.Tool_Mentat_Run();
		}else{
			String msg = "If you want to use this menu, you have to create a project.\n\n  File->new \t or \t File->Open";
			MessageDlg msgDlg = new MessageDlg(med.getCompositeTop().getShell(),msg);
			msgDlg.open();
		}
	}
	
	public void Setting_Preferences(){
		if(this.preferencesObj != null){
			PreferencesDlg preferencesDlg = new PreferencesDlg(Display.getCurrent().getActiveShell());
			preferencesDlg.open();
		}else{
			String msg = "If you want to use this menu, you have to create a project.\n\n  File->new \t or \t File->Open";
			MessageDlg msgDlg = new MessageDlg(med.getCompositeTop().getShell(),msg);
			msgDlg.open();
		}
		
	}
	//
	//
	///////////////////////////////////////////
	
	
	
	
	///////////////////////////////////////////
	// 
	// Button event
	//
	public void Button_StepSave(){
		if(this.CurrentStep.equals(CoilDB.STEP1)){
			this.SaveStep1();	
		}else if(this.CurrentStep.equals(CoilDB.STEP2)){
			this.SaveStep2();
		}
		this.SaveSimcosDB();
	}
	
	public void Button_FileExplorer_CoilData(){
		// Read Coil_design.csv process Start
		String CoilDesignFilePath = this.FileExplorer_CoilData();
		if(myUtil.checkPath(CoilDesignFilePath)){
			med.getTextCoilFilePath().setText(CoilDesignFilePath);
			this.coilDBObj.setCoilDesingFilePath(CoilDesignFilePath);
			this.readCoilDataFile();
			this.UpdateCoilGeometryData();
		}
	}
	
	public void Button_RadiusConditionerConstant(){
		// radio button
		if(med.getBtnRadiusConditionerConstant().getSelection()){
			// Select Constant type
			this.coilDBObj.setRadiusConditionerType(CoilDB.CONSTANT_TYPE);
			med.getTextRadiusConditionerValue().setEnabled(true);
			med.getTextRadiusConditionerPath().setEnabled(false);
			med.getBtnRadiusConditionerExplorer().setEnabled(false);
		}else{
			// Select File type
			this.coilDBObj.setRadiusConditionerType(CoilDB.FILE_TYPE);
			med.getTextRadiusConditionerValue().setEnabled(false);
			med.getTextRadiusConditionerPath().setEnabled(true);
			med.getBtnRadiusConditionerExplorer().setEnabled(true);
		}
	}
	
	public void Button_RadiusConditionerFile(){
		// radio button
		if(med.getBtnRadiusConditionerFile().getSelection()){
			// Select File Type
			this.coilDBObj.setRadiusConditionerType(CoilDB.FILE_TYPE);
			med.getTextRadiusConditionerValue().setEnabled(false);
			med.getTextRadiusConditionerPath().setEnabled(true);
			med.getBtnRadiusConditionerExplorer().setEnabled(true);
		}else{
			// Select Constant Type
			this.coilDBObj.setRadiusConditionerType(CoilDB.CONSTANT_TYPE);
			med.getTextRadiusConditionerValue().setEnabled(true);
			med.getTextRadiusConditionerPath().setEnabled(false);
			med.getBtnRadiusConditionerExplorer().setEnabled(false);
		}
	}
	
	public void Button_RadiusConditioner_FileExplorer(){
		String RadiusConditionerFilePath = this.FileExplorer_RadiusConditioner();
		if(myUtil.checkPath(RadiusConditionerFilePath)){
			med.getTextRadiusConditionerPath().setText(RadiusConditionerFilePath);
			this.coilDBObj.setRadiusConditionerFile(RadiusConditionerFilePath);
		}
	}
	
	public void Button_HeightConditionerConstant(){
		// radio button
		if(med.getBtnHeightConditionerConstant().getSelection()){
			// Select Constant Type
			this.coilDBObj.setHeightConditionerType(CoilDB.CONSTANT_TYPE);
			med.getTextHeightConditionerValue().setEnabled(true);
			med.getTextHeightConditionerPath().setEnabled(false);
			med.getBtnHeightConditionerExplorer().setEnabled(false);
		}else{
			// Select File Type
			this.coilDBObj.setHeightConditionerType(CoilDB.FILE_TYPE);
			med.getTextHeightConditionerValue().setEnabled(false);
			med.getTextHeightConditionerPath().setEnabled(true);
			med.getBtnHeightConditionerExplorer().setEnabled(true);
		}
	}
	
	public void Button_HeightConditonerFile(){
		// radio button
		if(med.getBtnHeightConditionerFile().getSelection()){
			// Select File Type
			this.coilDBObj.setHeightConditionerType(CoilDB.FILE_TYPE);
			med.getTextHeightConditionerValue().setEnabled(false);
			med.getTextHeightConditionerPath().setEnabled(true);
			med.getBtnHeightConditionerExplorer().setEnabled(true);
		}else{
			// Select Constant Type
			this.coilDBObj.setHeightConditionerType(CoilDB.CONSTANT_TYPE);
			med.getTextHeightConditionerValue().setEnabled(true);
			med.getTextHeightConditionerPath().setEnabled(false);
			med.getBtnHeightConditionerExplorer().setEnabled(false);
		}
	}
	
	public void Button_HeightConditioner_FileExplorer(){
		String HeightConditionerFilePath = this.FileExplorer_HeightConditioner();
		if(myUtil.checkPath(HeightConditionerFilePath)){
			med.getTextHeightConditionerPath().setText(HeightConditionerFilePath);
			this.coilDBObj.setHeightConditionerFile(HeightConditionerFilePath);
		}
	}
	
	public void Button_StartSimulation(){
		
	}
	
	public void Button_ReadLog(){
		
	}
	
	public void Button_ShowGraphWindow(){
		
	}
	
	public void Button_ShowImageWindow(){
		
	}
	
	public void Button_ShowTableData(){
		
	}
	//
	//
	///////////////////////////////////////////
	
	
	
	///////////////////////////////////////////
	//
	// Text event (modify text)
	//
	public void Text_Modify_CoilFilePath(){
		String data = med.getTextCoilFilePath().getText().trim();
		this.coilDBObj.setCoilDesingFilePath(data);
	}
	
	public void Text_Modify_ProductName(){
		String data = med.getTextProductName().getText().trim();
		this.coilDBObj.setProductName(data);
	}
	
	public void Text_Modify_LineDiameter(){
		String data = med.getTextLineDiameter().getText().trim();
		this.coilDBObj.setLineDiameter(data);
	}
	
	public void Text_Modify_CenterDiameter(){
		String data = med.getTextCenterDiameter().getText().trim();
		this.coilDBObj.setCenterDiameter(data);
	}
	
	public void Text_Modify_InnerDiameter(){
		String data = med.getTextInnerDiameter().getText().trim();
		this.coilDBObj.setInnerDiameter(data);
	}
	
	public void Text_Modify_OuterDiameter(){
		String data = med.getTextOuterDiameter().getText().trim();
		this.coilDBObj.setOuterDiameter(data);
		
	}
	
	public void Text_Modify_UpperInnerDiameter(){
		String data = med.getTextUpperInnerDiameter().getText().trim();
		this.coilDBObj.setUpperInnerDiameter(data);
	}
	
	public void Text_Modify_LowerInnerDiameter(){
		String data = med.getTextLowerInnerDiameter().getText().trim();
		this.coilDBObj.setLowerInnerDiameter(data);
	}
	
	public void Text_Modify_TotalNumer(){
		String data = med.getTextTotalNumber().getText().trim();
		this.coilDBObj.setTotalNumber(data);
	}
	
	public void Text_Modify_HotSettingTemp(){
		String data = med.getTextHotSettingTemp().getText().trim();
		this.coilDBObj.setHotSettingTemp(data);
	}
	
	public void Text_Modify_ColdSettingTemp(){
		String data = med.getTextColdSettingTemp().getText().trim();
		this.coilDBObj.setColdSettingTemp(data);
	}
	
	public void Text_Modify_HotSettingHeight(){
		String data = med.getTextHotSettingHeight().getText().trim();
		this.coilDBObj.setHotSettingHeight(data);
	}
	
	public void Text_Modify_ColdSettingHeight(){
		String data = med.getTextColdSettingHeight().getText().trim();
		this.coilDBObj.setColdSettingHeight(data);
	}
	
	public void Text_Modify_SeatUInnerMargina(){
		String data = med.getTextSeatUInnerMargina().getText().trim();
		this.coilDBObj.setSeatUIneerMargina(data);
	}
	
	public void Text_Modify_SeatLInnerMargina(){
		String data = med.getTextSeatLInnerMargina().getText().trim();
		this.coilDBObj.setSeatLIneerMargina(data);
	}
	
	public void Text_Modify_SeatHeight(){
		String data = med.getTextSeatHeight().getText().trim();
		this.coilDBObj.setSeatHeight(data);
	}
	
	public void Text_Modify_RadiusConditionerValue(){
		String data = med.getTextRadiusConditionerValue().getText().trim();
		this.coilDBObj.setRadiusConditionerConstant(data);
	}
	
	public void Text_Modify_RadiusConditionerPath(){
		String data = med.getTextRadiusConditionerPath().getText().trim();
		this.coilDBObj.setRadiusConditionerFile(data);
	}
	
	public void Text_Modify_HeightConditionerValue(){
		String data = med.getTextHeightConditionerValue().getText().trim();
		this.coilDBObj.setHeightConditionerConstant(data);
	}
	
	public void Text_Modify_HeightConditionerPath(){
		String data = med.getTextHeightConditionerPath().getText().trim();
		this.coilDBObj.setHeightConditionerFile(data);
	}
	
	public void Text_Modify_RadiusTolerance(){
		String data = med.getTextRadiusTolerance().getText().trim();
		this.coilDBObj.setRadiusTolerance(data);
	}
	
	public void Text_Modify_HeightTolerance(){
		String data = med.getTextHeightTolerance().getText().trim();
		this.coilDBObj.setHeightTolerance(data);
	}
	
	public void Text_Modify_MaximumIterationNumber(){
		String data = med.getTextMaximumIterationNumber().getText().trim();
		this.coilDBObj.setMaximumIterationNumber(data);
	}
	
	public void Text_Modify_LogEditor(){
		String data =med.getTextLogEditor().getText().trim();
		this.coilDBObj.setLogEditor(data);
	}
	
	//
	//
	///////////////////////////////////////////
	
	///////////////////////////////////////////
	// 
	// Label event
	//
	
	public void Label_Change_ProcessStep1() {
		// TODO Auto-generated method stub
		med.getStackLayout().topControl = med.getCompositeStep1();
		med.getCompositeBottom().layout();
		
		med.getLblModeling().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		med.getLblSimulationAndExportResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblShowResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		
		this.CurrentStep=CoilDB.STEP1;
		med.getBtnStepSave().setVisible(true);
	}


	public void Label_Change_ProcessStep2() {
		// TODO Auto-generated method stub
		med.getStackLayout().topControl = med.getCompositeStep2();
		med.getCompositeBottom().layout();
		
		med.getLblModeling().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblSimulationAndExportResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		med.getLblShowResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		
		this.CurrentStep=CoilDB.STEP2;
		med.getBtnStepSave().setVisible(true);
	}


	public void Label_Change_ProcessStep3() {
		// TODO Auto-generated method stub
		med.getStackLayout().topControl = med.getCompositeStep3();
		med.getCompositeBottom().layout();
		
		med.getLblModeling().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblSimulationAndExportResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblShowResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		
		this.CurrentStep=CoilDB.STEP3;
		med.getBtnStepSave().setVisible(false);
		
		
		// load combo data - graph
		this.UpdateSelectGrpahData();
		// load combo data - image
		this.UpdateSelectImageData();
		// load combo data - tableData
		this.UpdateSelectTableData();
		
	}
	
	//
	//
	///////////////////////////////////////////
	
	///////////////////////////////////////////
	// 
	// Combo event
	//
	
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
	
	//
	//
	///////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	//==================================================================================
	//==================================================================================
	private void _________seprerator________(){}
	//==================================================================================
	//==================================================================================
	
	public void File_New_Run(String projectName, String workspace){
		this.coilDBObj = new CoilDB();
		this.initValueObj = new InitValue(this.AppPath);
		this.appFolderObj = new AppFolder();
		this.preferencesObj = new Preferences();
		this.CurrentStep = CoilDB.STEP1;
		
		this.projectName = projectName;
		this.UserWorkspace = myUtil.setPath(workspace, projectName); 
		this.coilDBObj.setProjectName(this.projectName);
		this.coilDBObj.setProjectFolderPath(this.UserWorkspace);
		
		// Load Preferences data 
		this.getPreferencesData();
		
		
		// Create UserWorkspace structure
		myUtil.makeDirectory(this.UserWorkspace);
		this.appFolderObj.makeUserWorkspace();
		// Copy Script Files - python Script, main proc
		this.copyScriptFile();
		// Activation Widget
		this.WidgetEnable();
		// Create SimcosDB File
		this.CreateSimcosDB();
		// Setup InitValue 
		this.SetupInitValue();
		// Turn on Step1 Process Label
		med.getLblModeling().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		med.getLblSimulationAndExportResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		med.getLblShowResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		
	}
	
	public void File_Open_Run(){
		this.preferencesObj = new Preferences();
		this.getPreferencesData();
	}

	public void File_Save_Run(){
		this.SaveStep1();	
		this.SaveStep2();
		this.SaveSimcosDB();
	}
	
	public void File_SaveAs_Run(String newProjectName, String newWorkspace){
		
		this.projectName = newProjectName;
		this.UserWorkspace = myUtil.setPath(newWorkspace, newProjectName); 
		this.coilDBObj.setProjectName(this.projectName);
		this.coilDBObj.setProjectFolderPath(this.UserWorkspace);
		
		// Create UserWorkspace structure
		myUtil.makeDirectory(this.UserWorkspace);
		this.appFolderObj.makeUserWorkspace();
		// Copy Script Files - python Script, main proc
		this.copyScriptFile();
		// Create SimcosDB File
		this.SaveSimcosDB();
	}
	
	public void Tool_TextEditor_Run(){
		
	}
	
	public void Tool_Mentat_Run(){
		
	}
	
	public void Setting_Preferences_Run(){
		this.MarcPath = this.getPreferencesObj().getPreferencesValue(Preferences.MarcPath);
		this.TextEditorPath = this.getPreferencesObj().getPreferencesValue(Preferences.TextEditorPath);
		this.ExcelPath = this.getPreferencesObj().getPreferencesValue(Preferences.ExcelPath);
		this.Command = this.getPreferencesObj().getPreferencesValue(Preferences.Command);
		// write new data  in preference file
		this.preferencesObj.writePreferenceValue();
		
	}
	
	//==================================================================================
	private void copyScriptFile(){
		String configFolder = myUtil.setPath(this.AppPath, AppFolder.CONFIG);
		String scriptFolder = myUtil.setPath(configFolder, AppFolder.SCRIPT);
		
		String pyScript = myUtil.setPath(scriptFolder, AppFolder.pythonScriptFileName);
		String mainProc = myUtil.setPath(scriptFolder, AppFolder.mainProcFileName);
		
		String SimcosDataFolder = myUtil.setPath(this.coilDBObj.getProjectFolderPath(),AppFolder.SIMCOS_DATA);
		String destPyScriptPath = myUtil.setPath(SimcosDataFolder, AppFolder.pythonScriptFileName);
		String destMainProcPath = myUtil.setPath(SimcosDataFolder, AppFolder.mainProcFileName);
		
		myUtil.fileCopy(pyScript, destPyScriptPath);
		myUtil.fileCopy(mainProc, destMainProcPath);
	}
	//==================================================================================
	private void getPreferencesData(){
		// Load Preferences data 
		this.MarcPath = this.getPreferencesObj().getPreferencesValue(Preferences.MarcPath);
		this.TextEditorPath = this.getPreferencesObj().getPreferencesValue(Preferences.TextEditorPath);
		this.ExcelPath = this.getPreferencesObj().getPreferencesValue(Preferences.ExcelPath);
		this.Command = this.getPreferencesObj().getPreferencesValue(Preferences.Command);
		
		System.out.println(this.MarcPath);
		System.out.println(this.TextEditorPath);
		System.out.println(this.ExcelPath);
		System.out.println(this.Command);
	}
	
	private String FileExplorer_CoilData(){
		// Read Coil_design.csv process 1 
		FileDialog dlg = new FileDialog(med.getBtnExplorer().getShell(),SWT.OPEN);
		dlg.setText("Select Coil data CSV file.");
		
		String [] extNames = {"CSV(*.csv)"};
		String [] extType = {"*.csv"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		// Open ProjectFolder.
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
		// Read Coil_design.csv process 2
		CoilDataLabelfromCSV coilDataIndeObj = new CoilDataLabelfromCSV();
		Reader reader = new Reader(this.coilDBObj.getCoilDesingFilePath());
		reader.running();
		ArrayList<String> fileDataList = new ArrayList<String>();
		fileDataList = reader.getFileDataList();

		for(String line : fileDataList){
			ArrayList<String> tokens = new ArrayList<String>();			
			tokens = myUtil.splitData(line, ",");
			if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.ProductName))){
				this.coilDBObj.setProductName(tokens.get(1));
				med.getTextProductName().setText(this.coilDBObj.getProductName());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.LineDiameter))){
				this.coilDBObj.setLineDiameter(tokens.get(1));
				med.getTextLineDiameter().setText(this.coilDBObj.getLineDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.CenterDiameter))){
				this.coilDBObj.setCenterDiameter(tokens.get(1));
				med.getTextCenterDiameter().setText(this.coilDBObj.getCenterDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.InnerDiameter))){
				this.coilDBObj.setInnerDiameter(tokens.get(1));
				med.getTextInnerDiameter().setText(this.coilDBObj.getInnerDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.OuterDiameter))){
				this.coilDBObj.setOuterDiameter(tokens.get(1));
				med.getTextOuterDiameter().setText(this.coilDBObj.getOuterDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.UpperInnerDiameter))){
				this.coilDBObj.setUpperInnerDiameter(tokens.get(1));
				med.getTextUpperInnerDiameter().setText(this.coilDBObj.getUpperInnerDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.LowerInnerDiameter))){
				this.coilDBObj.setLowerInnerDiameter(tokens.get(1));
				med.getTextLowerInnerDiameter().setText(this.coilDBObj.getLowerInnerDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.TotalNumber))){
				this.coilDBObj.setTotalNumber(tokens.get(1));
				med.getTextTotalNumber().setText(this.coilDBObj.getTotalNumber());
				
			}else if(tokens.size() == 5 && myUtil.CheckFloatValue(tokens.get(0))){
				TableData_Coil obj = new TableData_Coil();
				obj.setX(tokens.get(0));
				obj.setY(tokens.get(1));
				obj.setTheta(tokens.get(4));
				obj.setRadius(tokens.get(3));
				obj.setHeight(tokens.get(2));
				this.coilDBObj.add_CoilDataTable(obj);
			}
		}
		// Delete object
		myUtil.CleareObj(coilDataIndeObj);
	}
	
	private void UpdateCoilGeometryData(){
		// Read Coil_design.csv process 3 
		try{
			med.getTableViewerCoilTable().setLabelProvider(new TableViewerLabelProvider_Coil());
			med.getTableViewerCoilTable().setContentProvider(new ArrayContentProvider());
			med.getTableViewerCoilTable().setInput(this.coilDBObj.getGeometryDataTableList());
			
		}catch(Exception e){
			System.out.println("ERROR - Coil data table");
		}
	}
	//==================================================================================
	private void WidgetEnable(){
		med.getCompositeTop().setEnabled(true);
		med.getCompositeBottom().setEnabled(true);
	}
	
	private void WidgetDisable(){
		med.getCompositeTop().setEnabled(false);
		med.getCompositeBottom().setEnabled(false);
	}
	
	private void WidgetStep1Enable(){
		med.getCompositeStep1().setEnabled(true);
	}
	
	private void WidgetStep2Enable(){
		med.getCompositeStep2().setEnabled(true);
	}
	
	private void WidgetStep3Enable(){
		med.getCompositeStep3().setEnabled(true);
	}
	//==================================================================================
	private void CreateSimcosDB(){
		WriteSimcosDB dbObj = new WriteSimcosDB();
		dbObj.CreateDBFile(this.coilDBObj);
		myUtil.CleareObj(dbObj);
	}
	
	private void SaveSimcosDB(){
		this.SaveCoilGeometryData();
		
		WriteSimcosDB dbObj = new WriteSimcosDB();
		dbObj.saveDBFile(this.coilDBObj);
		myUtil.CleareObj(dbObj);
		this.coilDBObj.PrintAllData();
	}
	
	private void SaveCoilGeometryData(){
		String SimcosFolder = myUtil.setPath(this.coilDBObj.getProjectFolderPath(), AppFolder.SIMCOS_DATA);
		String coilDesingUserFilePath = myUtil.setPath(SimcosFolder,AppFolder.coilDesignCSVFileName);
		this.coilDBObj.setCoilDesingUserFilePath(coilDesingUserFilePath);
		
		CoilDataLabelfromCSV coilDataIndeObj = new CoilDataLabelfromCSV();
		coilDataIndeObj.getLabel(CoilDataLabelfromCSV.ProductName);
		
		
		ArrayList<String> outputDataList = new ArrayList<String>();
		outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.ProductName)+","+this.coilDBObj.getProductName());
		outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.LineDiameter)+","+this.coilDBObj.getLineDiameter());
		outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.CenterDiameter)+","+this.coilDBObj.getCenterDiameter());
		outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.InnerDiameter)+","+this.coilDBObj.getInnerDiameter());
		outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.OuterDiameter)+","+this.coilDBObj.getOuterDiameter());
		outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.UpperInnerDiameter)+","+this.coilDBObj.getUpperInnerDiameter());
		outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.LowerInnerDiameter)+","+this.coilDBObj.getLowerInnerDiameter());
		outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.TotalNumber)+","+this.coilDBObj.getTotalNumber());
		outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.CompletedProduct));
		outputDataList.add("X,Y,Z,R,THETA(TURN)");
		
		for(TableData_Coil obj : this.coilDBObj.getGeometryDataTableList()){
			outputDataList.add(obj.getSaveData());
		}
		
		Writer writer = new Writer(this.coilDBObj.getCoilDesingUserFilePath());
		writer.running(outputDataList);
		
		// Delete object
		myUtil.CleareObj(coilDataIndeObj);
	}
	
	//==================================================================================
	private void SetupInitValue(){
		// Setting Process Information 
		med.getTextHotSettingTemp().setText(this.initValueObj.getInitValue(InitValue.HotSettingTemp));
		med.getTextColdSettingTemp().setText(this.initValueObj.getInitValue(InitValue.ColdSettingTemp));
		med.getTextHotSettingHeight().setText(this.initValueObj.getInitValue(InitValue.HotSettingHeight));
		med.getTextColdSettingHeight().setText(this.initValueObj.getInitValue(InitValue.ColdSettingHeight));
		med.getTextSeatUInnerMargina().setText(this.initValueObj.getInitValue(InitValue.SeatUInnerMargina));
		med.getTextSeatLInnerMargina().setText(this.initValueObj.getInitValue(InitValue.SeatLInnerMargina));
		med.getTextSeatHeight().setText(this.initValueObj.getInitValue(InitValue.SeatHeight));
		// Initial Conditioner
		if(this.initValueObj.getInitValue(InitValue.RadiusConditionerType).equals(CoilDB.CONSTANT_TYPE)){
			med.getBtnRadiusConditionerConstant().setSelection(true);
			med.getBtnRadiusConditionerFile().setSelection(false);
			med.getTextRadiusConditionerValue().setText(this.initValueObj.getInitValue(InitValue.RadiusConditionerConstant));
			med.getTextRadiusConditionerValue().setEnabled(true);
			med.getTextRadiusConditionerPath().setText(this.initValueObj.getInitValue(InitValue.RadiusConditionerFile));
			med.getTextRadiusConditionerPath().setEnabled(false);
			med.getBtnRadiusConditionerExplorer().setEnabled(false);
		}else{
			med.getBtnRadiusConditionerConstant().setSelection(false);
			med.getBtnRadiusConditionerFile().setSelection(true);
			med.getTextRadiusConditionerValue().setText(this.initValueObj.getInitValue(InitValue.RadiusConditionerConstant));
			med.getTextRadiusConditionerValue().setEnabled(false);
			med.getTextRadiusConditionerPath().setText(this.initValueObj.getInitValue(InitValue.RadiusConditionerFile));
			med.getTextRadiusConditionerPath().setEnabled(true);
			med.getBtnRadiusConditionerExplorer().setEnabled(true);			
		}
		if(this.initValueObj.getInitValue(InitValue.HeightConditionerType).equals(CoilDB.CONSTANT_TYPE)){
			med.getBtnHeightConditionerConstant().setSelection(true);
			med.getBtnHeightConditionerFile().setSelection(false);
			med.getTextHeightConditionerValue().setText(this.initValueObj.getInitValue(InitValue.HeightConditionerConstant));
			med.getTextHeightConditionerValue().setEnabled(true);
			med.getTextHeightConditionerPath().setText(this.initValueObj.getInitValue(InitValue.HeightConditionerFile));
			med.getTextHeightConditionerPath().setEnabled(false);
			med.getBtnHeightConditionerExplorer().setEnabled(false);
		}else{
			med.getBtnHeightConditionerConstant().setSelection(false);
			med.getBtnHeightConditionerFile().setSelection(true);
			med.getTextHeightConditionerValue().setText(this.initValueObj.getInitValue(InitValue.HeightConditionerConstant));
			med.getTextHeightConditionerValue().setEnabled(false);
			med.getTextHeightConditionerPath().setText(this.initValueObj.getInitValue(InitValue.HeightConditionerFile));
			med.getTextHeightConditionerPath().setEnabled(true);
			med.getBtnHeightConditionerExplorer().setEnabled(true);
		}
		// Analysis Options
		med.getTextRadiusTolerance().setText(this.initValueObj.getInitValue(InitValue.RadiusTolerance));
		med.getTextHeightTolerance().setText(this.initValueObj.getInitValue(InitValue.HeightTolerance));
		med.getTextMaximumIterationNumber().setText(this.initValueObj.getInitValue(InitValue.MaximumIterationNumber));
	}
	//==================================================================================
	private String FileExplorer_RadiusConditioner(){
		FileDialog dlg = new FileDialog(med.getBtnExplorer().getShell(),SWT.OPEN);
		dlg.setText("Select Radius Conditioner File.");
		
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		// Open ProjectFolder.
		//dlg.setFilterPath(this.getAppPath());
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return "Retry";
		}else {
			return path;
		}
	}
	
	private String FileExplorer_HeightConditioner(){
		FileDialog dlg = new FileDialog(med.getBtnExplorer().getShell(),SWT.OPEN);
		dlg.setText("Select Height Conditioner File.");
		
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		// Open ProjectFolder.
		//dlg.setFilterPath(this.getAppPath());
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return "Retry";
		}else {
			return path;
		}
	}
	//==================================================================================
	private void SaveStep1(){
		// Coil data 
		this.coilDBObj.setProductName(med.getTextProductName().getText().trim());
		this.coilDBObj.setInnerDiameter(med.getTextInnerDiameter().getText().trim());
		this.coilDBObj.setCenterDiameter(med.getTextCenterDiameter().getText().trim());
		this.coilDBObj.setInnerDiameter(med.getTextInnerDiameter().getText().trim());
		this.coilDBObj.setOuterDiameter(med.getTextOuterDiameter().getText().trim());
		this.coilDBObj.setUpperInnerDiameter(med.getTextUpperInnerDiameter().getText().trim());
		this.coilDBObj.setLowerInnerDiameter(med.getTextLowerInnerDiameter().getText().trim());
		this.coilDBObj.setTotalNumber(med.getTextTotalNumber().getText().trim());
		// Setting Process Information 
		this.coilDBObj.setHotSettingTemp(med.getTextHotSettingTemp().getText().trim());
		this.coilDBObj.setColdSettingTemp(med.getTextColdSettingTemp().getText().trim());
		this.coilDBObj.setHotSettingHeight(med.getTextHotSettingHeight().getText().trim());
		this.coilDBObj.setColdSettingHeight(med.getTextColdSettingHeight().getText().trim());
		this.coilDBObj.setSeatUIneerMargina(med.getTextSeatUInnerMargina().getText().trim());
		this.coilDBObj.setSeatLIneerMargina(med.getTextSeatLInnerMargina().getText().trim());
		this.coilDBObj.setSeatHeight(med.getTextSeatHeight().getText().trim());
		// Initial Conditioner
		if(med.getBtnRadiusConditionerConstant().getSelection()){
			this.coilDBObj.setRadiusConditionerType(CoilDB.CONSTANT_TYPE);
		}else{
			this.coilDBObj.setRadiusConditionerType(CoilDB.FILE_TYPE);
		}
		this.coilDBObj.setRadiusConditionerConstant(med.getTextRadiusConditionerValue().getText().trim());
		this.coilDBObj.setRadiusConditionerFile(med.getTextRadiusConditionerPath().getText().trim());
		if(med.getBtnHeightConditionerConstant().getSelection()){
			this.coilDBObj.setHeightConditionerType(CoilDB.CONSTANT_TYPE);
		}else{
			this.coilDBObj.setHeightConditionerType(CoilDB.FILE_TYPE);
		}
		this.coilDBObj.setHeightConditionerConstant(med.getTextHeightConditionerValue().getText().trim());
		this.coilDBObj.setHeightConditionerFile(med.getTextHeightConditionerPath().getText().trim());
	}
	
	private void SaveStep2(){
		this.coilDBObj.setRadiusTolerance(med.getTextRadiusTolerance().getText().trim());
		this.coilDBObj.setHeightTolerance(med.getTextHeightTolerance().getText().trim());
		this.coilDBObj.setMaximumIterationNumber(med.getTextMaximumIterationNumber().getText().trim());
	}
	
	private void SaveStep3(){
		
	}
	//==================================================================================
	private void UpdateSelectGrpahData(){
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
	
	
	private void UpdateSelectImageData(){
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
	
	private void UpdateSelectTableData(){
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
	
	//==================================================================================
	//==================================================================================
	private void _________seprerator2________(){}
	//==================================================================================
	//==================================================================================
		
	
	// get-set method
	
	public CoilDB getCoilDBObj() {
		return coilDBObj;
	}
	
	public String getAppPath(){
		return this.AppPath;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getBaseWorkspace() {
		return BaseWorkspace;
	}

	public Preferences getPreferencesObj() {
		return preferencesObj;
	}
	
	/*
	public String getSimcosWorkspace() {
		return SimcosWorkspace;
	}

	public void setSimcosWorkspace(String simcosWorkspace) {
		SimcosWorkspace = simcosWorkspace;
	}
	*/
	


	
	
}
