package com.js.ens.coil.core;

import java.util.ArrayList;



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
import com.js.ens.coil.db.LoadSimcosDB;
import com.js.ens.coil.db.WriteCoilParam;
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
	
	//File Explorer current Directory
	private String cPath_coilDesignData;
	private String cPath_initialConditioner;
	private String cPath_materialDB;
	
	
	// Preferences values
	private String MarcPath;
	private String MentatPath;
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
		this.BaseWorkspace = myUtil.setPath(this.AppPath, AppFolder.SIMCOS_WORKSPACE);
		
		this.cPath_coilDesignData = myUtil.setPath(this.AppPath, AppFolder.CONFIG);
		this.cPath_initialConditioner = myUtil.setPath(this.AppPath, AppFolder.CONFIG);
		this.cPath_materialDB = myUtil.setPath(myUtil.setPath(this.AppPath, AppFolder.CONFIG),AppFolder.MATERIAL_DB);
		
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
		try{
			if(this.CurrentStep.equals(CoilDB.STEP1)){
				this.SaveStep1();	
			}else if(this.CurrentStep.equals(CoilDB.STEP2)){
				this.SaveStep2();
			}
			this.SaveSimcosDB();
			String msg = "Success - save step ";
			MessageDlg msgDlg = new MessageDlg(med.getCompositeTop().getShell(),msg);
			msgDlg.open();
		}catch(Exception e){
			String msg = "Error - save step ";
			MessageDlg msgDlg = new MessageDlg(med.getCompositeTop().getShell(),msg);
			msgDlg.open();
		}
		
	}
	
	public void Button_FileExplorer_CoilData(){
		// Read Coil_design.csv process Start
		String CoilDesignFilePath = this.FileExplorer_CoilData();
		if(myUtil.checkPath(CoilDesignFilePath)){
			String fileName = myUtil.getFileNameIncludeExtension(CoilDesignFilePath);
			med.getTextCoilFilePath().setText(fileName);
			this.coilDBObj.setCoilDesignFilePath(CoilDesignFilePath);
			this.readCoilDataFile(CoilDB.COIL_GEOMETRY_TYPE_NEW);
			this.UpdateCoilGeometryData();
		}
	}
	
	public void Button_InitialConditionerConstant(){
		// radio button
		if(med.getBtnInitialConditionerConstant().getSelection()){
			// Select Constant type
			this.coilDBObj.setInitialConditionerType(CoilDB.CONSTANT_TYPE);
			med.getTextInitialConditionerValue().setEnabled(true);
			med.getTextInitialConditionerPath().setEnabled(false);
			med.getBtnInitialConditionerExplorer().setEnabled(false);
		}else{
			// Select File type
			this.coilDBObj.setInitialConditionerType(CoilDB.FILE_TYPE);
			med.getTextInitialConditionerValue().setEnabled(false);
			med.getTextInitialConditionerPath().setEnabled(true);
			med.getBtnInitialConditionerExplorer().setEnabled(true);
		}
	}
	
	public void Button_InitialConditionerFile(){
		// radio button
		if(med.getBtnInitialConditionerFile().getSelection()){
			// Select File Type
			this.coilDBObj.setInitialConditionerType(CoilDB.FILE_TYPE);
			med.getTextInitialConditionerValue().setEnabled(false);
			med.getTextInitialConditionerPath().setEnabled(true);
			med.getBtnInitialConditionerExplorer().setEnabled(true);
		}else{
			// Select Constant Type
			this.coilDBObj.setInitialConditionerType(CoilDB.CONSTANT_TYPE);
			med.getTextInitialConditionerValue().setEnabled(true);
			med.getTextInitialConditionerPath().setEnabled(false);
			med.getBtnInitialConditionerExplorer().setEnabled(false);
		}

		
	}
	
	public void Button_InitialConditioner_FileExplorer(){
		String InitialConditionerFilePath = this.FileExplorer_InitialConditioner();
		if(myUtil.checkPath(InitialConditionerFilePath)){
			String fileName = myUtil.getFileNameIncludeExtension(InitialConditionerFilePath);
			med.getTextInitialConditionerPath().setText(fileName);
			this.coilDBObj.setInitialConditionerFile(InitialConditionerFilePath);
		}
	}
	
	public void Button_MaterialDB_FileExplorer(){
		String MaterialDBFilePath = this.FileExplorer_MaterialDB();
		if(myUtil.checkPath(MaterialDBFilePath)){
			String fileName = myUtil.getFileName(MaterialDBFilePath);
			med.getTextMaterialDBPath().setText(fileName);
			this.coilDBObj.setMaterialDB(MaterialDBFilePath);
		}
	}
	
	
	public void Button_StartSimulation(){
		// Step1. Save All data -> include coil_design.csv
		this.File_Save_Run();
		// Step2. Check input data
		if(this.coilDBObj.isReadySolving()){
			med.getLblSimulationStatus().setText("Ready");
			med.getProgressBarSimulationIteration().setSelection(0);
			med.getTextLogEditor().setText("");
			med.getLblIterationNumber().setText(" ");
			med.getBtnStartSimulation().setEnabled(false);
			
			
			WriteSimcosDB dbObj = new WriteSimcosDB();
			// Write SimcosDB.sdb 
			dbObj.saveDBFile(this.coilDBObj);
			myUtil.CleareObj(dbObj);
			// create coil_param.csv
			this.writeCoilParam();
			// copy select source files - MaterialDB, initial conditioner file
			this.copySourceFileForModeling();
			
			// run mentat - pr main_dwku.proc
			String cmd = this.Command.replace("{MentatPath}", this.MentatPath);
			RunCMD runCmdThread = new RunCMD();
			runCmdThread.running(this.coilDBObj,cmd);
			Runnable r_runCmd = runCmdThread;
			Thread t_runCmd = new Thread(r_runCmd);
			t_runCmd.start();
			
			 
			// Read log File 
			String logFilePath = myUtil.setPath(myUtil.setPath(this.coilDBObj.getProjectFolderPath(), AppFolder.SIMCOS_DATA),AppFolder.coilItrLogFileName);
			if(myUtil.checkPath(logFilePath)){
				myUtil.fileDelete(logFilePath);
			}
			ReadLog readLogThread = new ReadLog();
			readLogThread.running(logFilePath, this.coilDBObj.getMaximumIterationNumber());
			Runnable r_readLog = readLogThread;
			Thread t_readLog = new Thread(r_readLog);
			t_readLog.start();
			/* */
			// make dummyLogFile Delete!!!!!!!!!
			String fakeLogFilePath = myUtil.setPath(myUtil.setPath(this.AppPath, AppFolder.CONFIG),AppFolder.coilItrLogFileName);
			FakeLogWriter fakeLogThread = new FakeLogWriter();
			fakeLogThread.running(this.coilDBObj, fakeLogFilePath);
			Runnable r_fakeLog = fakeLogThread;
			Thread t_fakeLog = new Thread(r_fakeLog);
			t_fakeLog.start();
			// */
		}else{
			String msg = "";
			for(String line : this.coilDBObj.getPrintAllData()){
				msg = msg+line+ "\n";
			}
			msg = msg +"\n\n Error - Check input data ";
			MessageDlg msgDlg = new MessageDlg(med.getCompositeTop().getShell(),msg);
			msgDlg.open();
		}
	}
	
	public void Button_ReadLog(){
		String logFilePath = myUtil.setPath(myUtil.setPath(this.coilDBObj.getProjectFolderPath(), AppFolder.SIMCOS_DATA),AppFolder.coilItrLogFileName);
		if(myUtil.checkPath(logFilePath)){
			Reader reader = new Reader(logFilePath);
			reader.running();
			String data = "";
			for(String line : reader.getFileDataList()){
				data += line +"\n";
			}
			med.getTextLogEditor().setText(data);
			med.getTextLogEditor().setSelection(data.length());
		}
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
		this.coilDBObj.setCoilDesignFilePath(data);
	}
	
	public void Text_Modify_ProductName(){
		String data = med.getTextProductName().getText().trim();
		this.coilDBObj.setProductName(data);
	}
	
	public void Text_Modify_WireDiameter(){
		String data = med.getTextWireDiameter().getText().trim();
		this.coilDBObj.setWireDiameter(data);
	}
	
	public void Text_Modify_CenterDiameter(){
		String data = med.getTextCenterDiameter().getText().trim();
		this.coilDBObj.setCenterDiameter(data);
	}
	
	public void Text_Modify_InternalDiameter(){
		String data = med.getTextInternalDiameter().getText().trim();
		this.coilDBObj.setInternalDiameter(data);
	}
	
	public void Text_Modify_ExternalDiameter(){
		String data = med.getTextExternalDiameter().getText().trim();
		this.coilDBObj.setExternalDiameter(data);
		
	}
	
	public void Text_Modify_UpperInnerDiameter(){
		String data = med.getTextUpperInnerDiameter().getText().trim();
		this.coilDBObj.setUpperInnerDiameter(data);
	}
	
	public void Text_Modify_LowerInnerDiameter(){
		String data = med.getTextLowerInnerDiameter().getText().trim();
		this.coilDBObj.setLowerInnerDiameter(data);
	}
	
	public void Text_Modify_TotalTurns(){
		String data = med.getTextTotalTurns().getText().trim();
		this.coilDBObj.setTotalTurns(data);
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
	
	public void Text_Modify_InitialConditionerValue(){
		String data =med.getTextInitialConditionerValue().getText().trim();
		this.coilDBObj.setInitialConditionerConstant(data);
	}
	
	public void Text_Modify_InitialConditionerPath(){
		String data = med.getTextInitialConditionerPath().getText().trim();
		this.coilDBObj.setInitialConditionerFile(data);
		
	}
	
	public void Text_Modify_MaterialDBPath(){
		String data = med.getTextMaterialDBPath().getText().trim();
		this.coilDBObj.setMaterialDB(data);
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
	
	public void File_Open_Run(String SimcosDBFilePath){
		if(this.isExistedProject(SimcosDBFilePath)){
			// CoilDB init
			if(this.coilDBObj != null){
				myUtil.CleareObj(this.coilDBObj);
				myUtil.CleareObj(this.initValueObj);
				myUtil.CleareObj(this.appFolderObj);
				myUtil.CleareObj(this.preferencesObj);
				//System.out.println("DB Path : "+SimcosDBFilePath);
			}
			this.coilDBObj = new CoilDB();
			this.initValueObj = new InitValue(this.AppPath);
			this.appFolderObj = new AppFolder();
			this.preferencesObj = new Preferences();
			this.CurrentStep = CoilDB.STEP1;
			
			// Load SimcosDB File and setup value at UI
			LoadSimcosDB loadDBObj = new LoadSimcosDB();
			loadDBObj.readDBFile(this.coilDBObj, SimcosDBFilePath);
			this.LoadCoilDB_UI();
			
			// load preferences data 
			this.getPreferencesData();
			
			// Activation Widget
			this.WidgetEnable();
			
			// Turn on Step1 Process Label
			this.Label_Change_ProcessStep1(); 
			/*
			med.getLblModeling().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			med.getLblSimulationAndExportResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			med.getLblShowResult().setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			*/
		}else{
			
			String msg = "Check Project Folder.";
			MessageDlg msgDlg = new MessageDlg(med.getCompositeTop().getShell(),msg);
			msgDlg.open();
		}
		
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
		this.MentatPath = this.getPreferencesObj().getPreferencesValue(Preferences.MentatPath);
		this.TextEditorPath = this.getPreferencesObj().getPreferencesValue(Preferences.TextEditorPath);
		this.ExcelPath = this.getPreferencesObj().getPreferencesValue(Preferences.ExcelPath);
		this.Command = this.getPreferencesObj().getPreferencesValue(Preferences.Command);
		// write new data  in preference file
		this.preferencesObj.writePreferenceValue();
		
	}
	
	//==================================================================================
	private boolean isExistedProject(String dbFilePath){
		// call file -> open 
		//System.out.println("open db path : "+dbFilePath);
		boolean result = false;
		String parentPath = myUtil.getParentPath(dbFilePath);
		String projectName = "null";
		
		Reader reader = new Reader(dbFilePath);
		reader.running();
		for(String line : reader.getFileDataList()){
			if(line.contains(LoadSimcosDB.ProjectNameKey)){
				ArrayList<String> tokens = new ArrayList<String>();
				tokens = myUtil.splitData(line, "=");
				projectName = tokens.get(1);
				break;
			}
		}
		
		//String projectFolder = myUtil.setPath(parentPath, projectName);
		System.out.println(projectName);
		System.out.println(parentPath);
		if(myUtil.checkPath(parentPath)){
			// exist
			result = true;
		}else{
			// not exist
			result = false;
		}
		
		return result;
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
	
	private void copySourceFileForModeling(){
		// MaterialDB, Initial Conditioner 
		String SimcosDataFolder = myUtil.setPath(this.coilDBObj.getProjectFolderPath(),AppFolder.SIMCOS_DATA);
		
		String destMaterialDBFile = "";
		String destInitialConditionerFile = "";
		
		if(this.coilDBObj.getInitialConditionerType().equals(CoilDB.FILE_TYPE)){
			String InitialConditionerFileName = myUtil.getFileNameIncludeExtension(this.coilDBObj.getInitialConditionerFile());
			destInitialConditionerFile = myUtil.setPath(SimcosDataFolder, InitialConditionerFileName);
			myUtil.fileCopy(this.coilDBObj.getInitialConditionerFile(), destInitialConditionerFile);
		}
		
		if(!this.coilDBObj.getMaterialDB().equals("null")){
			String MaterialDBFileName = myUtil.getFileNameIncludeExtension(this.coilDBObj.getMaterialDB());
			destMaterialDBFile = myUtil.setPath(SimcosDataFolder, MaterialDBFileName);
			myUtil.fileCopy(this.coilDBObj.getMaterialDB(), destMaterialDBFile);
		}
		
	}
	//==================================================================================
	private void getPreferencesData(){
		// Load Preferences data 
		this.MarcPath = this.getPreferencesObj().getPreferencesValue(Preferences.MarcPath);
		this.MentatPath = this.getPreferencesObj().getPreferencesValue(Preferences.MentatPath);
		this.TextEditorPath = this.getPreferencesObj().getPreferencesValue(Preferences.TextEditorPath);
		this.ExcelPath = this.getPreferencesObj().getPreferencesValue(Preferences.ExcelPath);
		this.Command = this.getPreferencesObj().getPreferencesValue(Preferences.Command);
		
		/*
		System.out.println(this.MarcPath);
		System.out.println(this.MentatPath);
		System.out.println(this.TextEditorPath);
		System.out.println(this.ExcelPath);
		System.out.println(this.Command);
		*/
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
		dlg.setFilterPath(this.cPath_coilDesignData);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return "Retry";
		}else {
			this.cPath_coilDesignData = myUtil.getParentPath(path);
			return path;
		}
	}
	
	private void readCoilDataFile(String type){
		// Read Coil_design.csv process 2
		CoilDataLabelfromCSV coilDataIndeObj = new CoilDataLabelfromCSV();
		String sourceFile = null;
		if(type.equals(CoilDB.COIL_GEOMETRY_TYPE_OPEN)){
			sourceFile = this.coilDBObj.getCoilDesignUserFilePath();
		}else if(type.equals(CoilDB.COIL_GEOMETRY_TYPE_NEW)){
			sourceFile = this.coilDBObj.getCoilDesignFilePath();
		}
		Reader reader = new Reader(sourceFile);
		reader.running();
		ArrayList<String> fileDataList = new ArrayList<String>();
		fileDataList = reader.getFileDataList();

		for(String line : fileDataList){
			ArrayList<String> tokens = new ArrayList<String>();			
			tokens = myUtil.splitData(line, ",");
			if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.ProductName))){
				this.coilDBObj.setProductName(tokens.get(1));
				med.getTextProductName().setText(this.coilDBObj.getProductName());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.WireDiameter))){
				this.coilDBObj.setWireDiameter(tokens.get(1));
				med.getTextWireDiameter().setText(this.coilDBObj.getWireDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.CenterDiameter))){
				this.coilDBObj.setCenterDiameter(tokens.get(1));
				med.getTextCenterDiameter().setText(this.coilDBObj.getCenterDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.InternalDiameter))){
				this.coilDBObj.setInternalDiameter(tokens.get(1));
				med.getTextInternalDiameter().setText(this.coilDBObj.getInternalDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.ExternalDiameter))){
				this.coilDBObj.setExternalDiameter(tokens.get(1));
				med.getTextExternalDiameter().setText(this.coilDBObj.getExternalDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.UpperInnerDiameter))){
				this.coilDBObj.setUpperInnerDiameter(tokens.get(1));
				med.getTextUpperInnerDiameter().setText(this.coilDBObj.getUpperInnerDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.LowerInnerDiameter))){
				this.coilDBObj.setLowerInnerDiameter(tokens.get(1));
				med.getTextLowerInnerDiameter().setText(this.coilDBObj.getLowerInnerDiameter());
			}else if(tokens.get(0).trim().equals(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.TotalTurns))){
				this.coilDBObj.setTotalTurns(tokens.get(1));
				med.getTextTotalTurns().setText(this.coilDBObj.getTotalTurns());
				
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
		// create coil_design.csv 
		if(!this.coilDBObj.getCoilDesignFilePath().equals("null")){
			String SimcosFolder = myUtil.setPath(this.coilDBObj.getProjectFolderPath(), AppFolder.SIMCOS_DATA);
			String coilDesingUserFilePath = myUtil.setPath(SimcosFolder,AppFolder.coilDesignCSVFileName);
			this.coilDBObj.setCoilDesignUserFilePath(coilDesingUserFilePath);
			
			CoilDataLabelfromCSV coilDataIndeObj = new CoilDataLabelfromCSV();
			coilDataIndeObj.getLabel(CoilDataLabelfromCSV.ProductName);
			
			
			ArrayList<String> outputDataList = new ArrayList<String>();
			outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.ProductName)+","+this.coilDBObj.getProductName());
			outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.WireDiameter)+","+this.coilDBObj.getWireDiameter());
			outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.CenterDiameter)+","+this.coilDBObj.getCenterDiameter());
			outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.InternalDiameter)+","+this.coilDBObj.getInternalDiameter());
			outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.ExternalDiameter)+","+this.coilDBObj.getExternalDiameter());
			outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.UpperInnerDiameter)+","+this.coilDBObj.getUpperInnerDiameter());
			outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.LowerInnerDiameter)+","+this.coilDBObj.getLowerInnerDiameter());
			outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.TotalTurns)+","+this.coilDBObj.getTotalTurns());
			outputDataList.add(coilDataIndeObj.getLabel(CoilDataLabelfromCSV.CompletedProduct));
			outputDataList.add("X,Y,Z,R,THETA(TURN)");
			
			for(TableData_Coil obj : this.coilDBObj.getGeometryDataTableList()){
				outputDataList.add(obj.getSaveData());
			}
			
			Writer writer = new Writer(this.coilDBObj.getCoilDesignUserFilePath());
			writer.running(outputDataList);
			
			// Delete object
			myUtil.CleareObj(coilDataIndeObj);
		}
	}
	
	private void writeCoilParam(){
		WriteCoilParam obj = new WriteCoilParam();
		obj.writeParamData(this.coilDBObj);
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
		if(this.initValueObj.getInitValue(InitValue.InitialConditionerType).equals(CoilDB.CONSTANT_TYPE)){
			med.getBtnInitialConditionerConstant().setSelection(true);
			med.getBtnInitialConditionerFile().setSelection(false);
			med.getTextInitialConditionerValue().setText(this.initValueObj.getInitValue(InitValue.InitialConditionerConstant));
			med.getTextInitialConditionerValue().setEnabled(true);
			med.getTextInitialConditionerPath().setText(this.initValueObj.getInitValue(InitValue.InitialConditionerFile));
			med.getTextInitialConditionerPath().setEnabled(false);
			med.getBtnInitialConditionerExplorer().setEnabled(false);
		}else{
			med.getBtnInitialConditionerConstant().setSelection(false);
			med.getBtnInitialConditionerFile().setSelection(true);
			med.getTextInitialConditionerValue().setText(this.initValueObj.getInitValue(InitValue.InitialConditionerConstant));
			med.getTextInitialConditionerValue().setEnabled(false);
			med.getTextInitialConditionerPath().setText(this.initValueObj.getInitValue(InitValue.InitialConditionerFile));
			med.getTextInitialConditionerPath().setEnabled(true);
			med.getBtnInitialConditionerExplorer().setEnabled(true);			
		}
		// Material Database 
		med.getTextMaterialDBPath().setText(this.initValueObj.getInitValue(InitValue.MaterialDatabase));
		// Analysis Options
		med.getTextRadiusTolerance().setText(this.initValueObj.getInitValue(InitValue.RadiusTolerance));
		med.getTextHeightTolerance().setText(this.initValueObj.getInitValue(InitValue.HeightTolerance));
		med.getTextMaximumIterationNumber().setText(this.initValueObj.getInitValue(InitValue.MaximumIterationNumber));
	}
	
	private void LoadCoilDB_UI(){
		// Coil_design 
		if(!this.coilDBObj.getCoilDesignUserFilePath().equals("null")){
			this.readCoilDataFile(CoilDB.COIL_GEOMETRY_TYPE_OPEN);
			this.UpdateCoilGeometryData();
			med.getTextCoilFilePath().setText(myUtil.getFileNameIncludeExtension(this.coilDBObj.getCoilDesignFilePath()));
		}
		// Setting Process Information 
		med.getTextHotSettingTemp().setText(this.coilDBObj.getHotSettingTemp());
		med.getTextColdSettingTemp().setText(this.coilDBObj.getColdSettingTemp());
		med.getTextHotSettingHeight().setText(this.coilDBObj.getHotSettingHeight());
		med.getTextColdSettingHeight().setText(this.coilDBObj.getColdSettingHeight());
		med.getTextSeatUInnerMargina().setText(this.coilDBObj.getSeatUIneerMargina());
		med.getTextSeatLInnerMargina().setText(this.coilDBObj.getSeatLIneerMargina());
		med.getTextSeatHeight().setText(this.coilDBObj.getSeatHeight());
		// Initial Conditioner
		if(this.coilDBObj.getInitialConditionerType().equals(CoilDB.CONSTANT_TYPE)){
			med.getBtnInitialConditionerConstant().setSelection(true);
			med.getBtnInitialConditionerFile().setSelection(false);
			med.getTextInitialConditionerValue().setText(this.coilDBObj.getInitialConditionerConstant());
			med.getTextInitialConditionerValue().setEnabled(true);
			med.getTextInitialConditionerPath().setText(myUtil.getFileNameIncludeExtension(this.coilDBObj.getInitialConditionerFile()));
			med.getTextInitialConditionerPath().setEnabled(false);
			med.getBtnInitialConditionerExplorer().setEnabled(false);
		}else{
			med.getBtnInitialConditionerConstant().setSelection(false);
			med.getBtnInitialConditionerFile().setSelection(true);
			med.getTextInitialConditionerValue().setText(this.coilDBObj.getInitialConditionerConstant());
			med.getTextInitialConditionerValue().setEnabled(false);
			med.getTextInitialConditionerPath().setText(myUtil.getFileNameIncludeExtension(this.coilDBObj.getInitialConditionerFile()));
			med.getTextInitialConditionerPath().setEnabled(true);
			med.getBtnInitialConditionerExplorer().setEnabled(true);			
		}
		
		med.getTextMaterialDBPath().setText(myUtil.getFileName(this.coilDBObj.getMaterialDB()));
		
		// Analysis Options
		med.getTextRadiusTolerance().setText(this.coilDBObj.getRadiusTolerance());
		med.getTextHeightTolerance().setText(this.coilDBObj.getHeightTolerance());
		med.getTextMaximumIterationNumber().setText(this.coilDBObj.getMaximumIterationNumber());
	}
	
	
	//==================================================================================
	private String FileExplorer_InitialConditioner(){
		FileDialog dlg = new FileDialog(med.getBtnExplorer().getShell(),SWT.OPEN);
		dlg.setText("Select Initial Conditioner File.");
		
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		// Open ProjectFolder.
		dlg.setFilterPath(this.cPath_initialConditioner);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return "Retry";
		}else {
			this.cPath_initialConditioner = myUtil.getParentPath(path);
			return path;
		}
	}
	
	private String FileExplorer_MaterialDB(){
		FileDialog dlg = new FileDialog(med.getBtnExplorer().getShell(),SWT.OPEN);
		dlg.setText("Select Materila Database File.");
		
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		// Open ProjectFolder.
		dlg.setFilterPath(this.cPath_materialDB);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return "Retry";
		}else {
			this.cPath_materialDB = myUtil.getParentPath(path);
			return path;
		}
	}
	
	//==================================================================================
	private void SaveStep1(){
		// CenterBeamNodeStart
		this.coilDBObj.setCenterBeamNodeStart(this.initValueObj.getInitValue(InitValue.CenterBeamNodeStart));
		// Coil data 
		this.coilDBObj.setProductName(med.getTextProductName().getText().trim());
		this.coilDBObj.setWireDiameter(med.getTextWireDiameter().getText().trim());
		this.coilDBObj.setCenterDiameter(med.getTextCenterDiameter().getText().trim());
		this.coilDBObj.setInternalDiameter(med.getTextInternalDiameter().getText().trim());
		this.coilDBObj.setExternalDiameter(med.getTextExternalDiameter().getText().trim());
		this.coilDBObj.setUpperInnerDiameter(med.getTextUpperInnerDiameter().getText().trim());
		this.coilDBObj.setLowerInnerDiameter(med.getTextLowerInnerDiameter().getText().trim());
		this.coilDBObj.setTotalTurns(med.getTextTotalTurns().getText().trim());
		// Setting Process Information 
		this.coilDBObj.setHotSettingTemp(med.getTextHotSettingTemp().getText().trim());
		this.coilDBObj.setColdSettingTemp(med.getTextColdSettingTemp().getText().trim());
		this.coilDBObj.setHotSettingHeight(med.getTextHotSettingHeight().getText().trim());
		this.coilDBObj.setColdSettingHeight(med.getTextColdSettingHeight().getText().trim());
		this.coilDBObj.setSeatUIneerMargina(med.getTextSeatUInnerMargina().getText().trim());
		this.coilDBObj.setSeatLIneerMargina(med.getTextSeatLInnerMargina().getText().trim());
		this.coilDBObj.setSeatHeight(med.getTextSeatHeight().getText().trim());
		// Initial Conditioner
		if(med.getBtnInitialConditionerConstant().getSelection()){
			this.coilDBObj.setInitialConditionerType(CoilDB.CONSTANT_TYPE);
		}else{
			this.coilDBObj.setInitialConditionerType(CoilDB.FILE_TYPE);
		}
		this.coilDBObj.setInitialConditionerConstant(med.getTextInitialConditionerValue().getText().trim());
		
		
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
