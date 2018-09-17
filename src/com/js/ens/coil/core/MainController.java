package com.js.ens.coil.core;


import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.wb.swt.SWTResourceManager;

import com.js.ens.coil.Application;
import com.js.ens.coil.customWidget.ComboData_selectGraph;
import com.js.ens.coil.customWidget.ComboData_selectImage;
import com.js.ens.coil.customWidget.ComboData_selectTableData;
import com.js.ens.coil.customWidget.ComboViewerLabelProvider_SelectGraph;
import com.js.ens.coil.customWidget.ComboViewerLabelProvider_SelectImage;
import com.js.ens.coil.customWidget.ComboViewerLabelProvider_SelectTableData;
import com.js.ens.coil.customWidget.ListData_selectedGraph;
import com.js.ens.coil.customWidget.ListLabelProvider_SelectedGraph;
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
import com.js.image.ImageAllData;
import com.js.image.SimcosImageViewer;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.plot.GraphAllData;
import com.js.plot.SimcosGraphViewer;
import com.js.util.myUtil;

public class MainController {
	private Logger log = Logger.getLogger(Application.class);
	
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
	//private String cPath_initialConditioner;
	private String cPath_radiusConditioner;
	private String cPath_heightConditioner;
	private String cPath_materialDB;
	private String cPath_formedCoilDataInterp;
	
	
	// Preferences values
	private String MarcPath;
	private String MentatPath;
	private String TextEditorPath;
	private String ExcelPath;
	private String CommandSolving;
	private String CommandPost;

	public String getMentatPath() {
		return MentatPath;
	}

	public String getCommandSolving() {
		return CommandSolving;
	}
	
	public String getCommandPost(){
		return CommandPost;
	}

	private Mediator med = Mediator.getInstance();
	
	
	////////////////////////////////////////////
	private CoilDB coilDBObj;
	private InitValue initValueObj;
	private AppFolder appFolderObj;
	private Preferences preferencesObj;
	private Thread t_readLog;
	private Thread t_runCmd;
	private Thread t_runGraph;
	////////////////////////////////////////////
	
	
	public MainController(){
		if(myUtil.checkOS().equals("window")){
			this.AppPath = System.getProperty("user.dir"); 
		}else{
			this.AppPath = "/Users/jslee/CodeFactory/Git/2017/2017";
		}
		this.BaseWorkspace = myUtil.setPath(this.AppPath, AppFolder.SIMCOS_WORKSPACE);
		
		this.cPath_coilDesignData = myUtil.setPath(this.AppPath, AppFolder.CONFIG);
		//this.cPath_initialConditioner = myUtil.setPath(this.AppPath, AppFolder.CONFIG);
		this.cPath_radiusConditioner = myUtil.setPath(this.AppPath, AppFolder.CONFIG);
		this.cPath_heightConditioner = myUtil.setPath(this.AppPath, AppFolder.CONFIG);
		this.cPath_materialDB = myUtil.setPath(myUtil.setPath(this.AppPath, AppFolder.CONFIG),AppFolder.MATERIAL_DB);
		this.cPath_formedCoilDataInterp = myUtil.setPath(this.AppPath, AppFolder.CONFIG);
		
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
			this.Tool_TextEditor_Run();
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
			log.info("Step Save Button - save complete");
		}catch(Exception e){
			String msg = "Error - save step ";
			MessageDlg msgDlg = new MessageDlg(med.getCompositeTop().getShell(),msg);
			msgDlg.open();
			log.error("Step Save Button - "+e.getMessage());
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
	
	public void Button_ShowRadiusGraph(){
		System.out.println("Show Radius Graph.... MC");
		if(this.coilDBObj.getGeometryDataTableList().size() !=0){
			System.out.println("Size > 0");
			SimcosGraphViewer viewer = new SimcosGraphViewer();
			viewer.running_geometryData_radius(this.coilDBObj);
		}else{
			System.out.println("Size = 0");
		}
	}
	
	public void Button_ShowPitchGraph(){
		System.out.println("Show Pitch Graph.... MC");
		if(this.coilDBObj.getGeometryDataTableList().size() != 0){
			System.out.println("Size > 0");
			SimcosGraphViewer viewer = new SimcosGraphViewer();
			viewer.running_geometryData_pitch(this.coilDBObj);
		}else{
			System.out.println("Size = 0");
		}
	}
	
	public void Button_Standard(){
		System.out.println("Standard...MC");
		this.coilDBObj.setSeatType(CoilDB.SEAT_TYPE_STANDARD);
		med.getTextSeatUStepRotationHeight().setEnabled(false);
		med.getTextSeatLStepRotationHeight().setEnabled(false);
		med.getTextSeatURotationAngle().setEnabled(false);
		med.getTextSeatLRotationAngle().setEnabled(false);
		if(med.getBtnStandard().getSelection()){
			med.getTextSeatUStepRotationHeight().setText("0.0");
			med.getTextSeatLStepRotationHeight().setText("0.0");
			med.getTextSeatURotationAngle().setText("0.0");
			med.getTextSeatLRotationAngle().setText("0.0");
			
			this.coilDBObj.setSeatUStepRotationHeight("0.0");
			this.coilDBObj.setSeatLStepRotationHeight("0.0");
			this.coilDBObj.setSeatURotationAngle("0.0");
			this.coilDBObj.setSeatLRotationAngle("0.0");
		}
	}
	
	public void Button_RrCoil(){
		System.out.println("RR Coil...MC");
		this.coilDBObj.setSeatType(CoilDB.SEAT_TYPE_RRCoil);
		med.getTextSeatUStepRotationHeight().setEnabled(true);
		med.getTextSeatLStepRotationHeight().setEnabled(true);
		med.getTextSeatURotationAngle().setEnabled(true);
		med.getTextSeatLRotationAngle().setEnabled(true);
	}
	/*
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
	//*/
	public void Button_RadiusConditionerConstant(){
		// radio button - Radius
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
		// radio button - Radius
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
			String fileName = myUtil.getFileNameIncludeExtension(RadiusConditionerFilePath);
			med.getTextRadiusConditionerPath().setText(fileName);
			this.coilDBObj.setRadiusConditionerFile(RadiusConditionerFilePath);
		}
	}
	
	public void Button_HeightConditionerConstant(){
		// radio button - height
		if(med.getBtnHeightConditionerConstant().getSelection()){
			// Select Constant type
			this.coilDBObj.setHeightConditionerType(CoilDB.CONSTANT_TYPE);
			med.getTextHeightConditionerValue().setEnabled(true);
			med.getTextHeightConditionerPath().setEnabled(false);
			med.getBtnHeightConditionerExplorer().setEnabled(false);
		}else{
			// Select File type
			this.coilDBObj.setHeightConditionerType(CoilDB.FILE_TYPE);
			med.getTextHeightConditionerValue().setEnabled(false);
			med.getTextHeightConditionerPath().setEnabled(true);
			med.getBtnHeightConditionerExplorer().setEnabled(true);
		}
	}
	
	public void Button_HeightConditionerFile(){
		// radio button - height
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
			String fileName = myUtil.getFileNameIncludeExtension(HeightConditionerFilePath);
			med.getTextHeightConditionerPath().setText(fileName);
			this.coilDBObj.setHeightConditionerFile(HeightConditionerFilePath);
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
	
	public void Button_FormedCoilDataInterp_FileExplorer(){
		String FormedCoilDataInterpPath = this.FileExplorer_FormedCoilDataInterp();
		if(myUtil.checkPath(FormedCoilDataInterpPath)){
			String fileName = myUtil.getFileNameIncludeExtension(FormedCoilDataInterpPath);
			med.getTextFormedCoilDataInterp().setText(fileName);
			this.coilDBObj.setFormedCoilDataInterpolationFile(FormedCoilDataInterpPath);
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
			
			
			
			
			// Read log File 
			String logFileName = this.coilDBObj.getProductName()+AppFolder.coilItrLogFileName;
			String logFilePath = myUtil.setPath(myUtil.setPath(this.coilDBObj.getProjectFolderPath(), AppFolder.SIMCOS_DATA),logFileName);
			if(myUtil.checkPath(logFilePath)){
				myUtil.fileDelete(logFilePath);
			}
			String interruptFilePath = myUtil.setPath(myUtil.setPath(this.coilDBObj.getProjectFolderPath(),AppFolder.SIMCOS_DATA),"itr");
			
			
			ReadLog readLogThread = new ReadLog();
			readLogThread.running(logFilePath, this.coilDBObj.getMaximumIterationNumber(),interruptFilePath);
			Runnable r_readLog = readLogThread;
			this.t_readLog = new Thread(r_readLog);
			this.t_readLog.start();
			
			
			
			// run mentat - pr main_dwku.proc
			RunCMD runCmdThread = new RunCMD();
			runCmdThread.running(this.coilDBObj);
			Runnable r_runCmd = runCmdThread;
			this.t_runCmd = new Thread(r_runCmd);
			this.t_runCmd.start();
			
			 
			
			/*
			// make dummyLogFile Delete!!!!!!!!!
			String fakelogFileName = "FS"+AppFolder.coilItrLogFileName;
			String fakeLogFilePath = myUtil.setPath(myUtil.setPath(this.AppPath, AppFolder.CONFIG),fakelogFileName);
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
			log.warn("Start Simulation button - Check input data ");
			
		}
	}
	
	public void Button_ResetSimulation(){
		String interruptFilePath = myUtil.setPath(myUtil.setPath(this.coilDBObj.getProjectFolderPath(),AppFolder.SIMCOS_DATA),"itr");
		ArrayList<String> endDataList = new ArrayList<String>();
		endDataList.add("interrupt read log thread");
		Writer writer = new Writer(interruptFilePath);
		writer.running(endDataList);
		
		String itrLogFile = myUtil.setPath(myUtil.setPath(this.coilDBObj.getProjectFolderPath(), AppFolder.SIMCOS_DATA), this.coilDBObj.getProductName()+AppFolder.coilItrLogFileName);
		try{
			Thread.sleep(3000);
			if(myUtil.checkPath(interruptFilePath)){
				myUtil.fileDelete(interruptFilePath);
				//myUtil.fileDelete(itrLogFile);
				//System.out.println("delete simcos.log file");
			}
			
			
			med.getBtnStartSimulation().setEnabled(true);
			med.getProgressBarSimulationIteration().setSelection(0);
			med.getTextLogEditor().setText("");
			med.getLblSimulationStatus().setText("Ready");
			med.getLblIterationNumber().setText("    ");
			
		}catch(Exception e){
			System.out.println("Read log thread is null : "+ e.getMessage());
			log.error("Read log thread is null - "+e.getMessage());
		}
	}
	
	public void Button_ReadLog(){
		String logFileName = this.coilDBObj.getProductName()+AppFolder.coilItrLogFileName;
		String logFilePath = myUtil.setPath(myUtil.setPath(this.coilDBObj.getProjectFolderPath(), AppFolder.SIMCOS_DATA),logFileName);
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
	
	
	
	public void Button_Conditioner(){
		med.getBtnRadius().setEnabled(true);
		med.getBtnHeight().setEnabled(true);
		
		med.getBtnConditioner().setSelection(true);
		med.getBtnError().setSelection(false);
		med.getBtnFormSetError().setSelection(false);
		med.getBtnPitch_IR().setSelection(false);
		med.getBtnRadius_IR().setSelection(false);
		med.getBtnMaximumError().setSelection(false);
		med.getBtnFormDataTotal().setSelection(false);
		med.getBtnRadius().setSelection(true);
		med.getBtnHeight().setSelection(false);
		
		this.UpdateSelectGraphData();
	}
	
	public void Button_Error(){
		med.getBtnRadius().setEnabled(true);
		med.getBtnHeight().setEnabled(true);
		
		med.getBtnConditioner().setSelection(false);
		med.getBtnError().setSelection(true);
		med.getBtnFormSetError().setSelection(false);
		med.getBtnPitch_IR().setSelection(false);
		med.getBtnRadius_IR().setSelection(false);
		med.getBtnMaximumError().setSelection(false);
		med.getBtnFormDataTotal().setSelection(false);
		med.getBtnRadius().setSelection(true);
		med.getBtnHeight().setSelection(false);
		
		this.UpdateSelectGraphData();
	}
	
	public void Button_FormSetError(){
		med.getBtnRadius().setEnabled(true);
		med.getBtnHeight().setEnabled(true);
		
		med.getBtnConditioner().setSelection(false);
		med.getBtnError().setSelection(false);
		med.getBtnFormSetError().setSelection(true);
		med.getBtnPitch_IR().setSelection(false);
		med.getBtnRadius_IR().setSelection(false);
		med.getBtnMaximumError().setSelection(false);
		med.getBtnFormDataTotal().setSelection(false);
		med.getBtnRadius().setSelection(true);
		med.getBtnHeight().setSelection(false);
		
		this.UpdateSelectGraphData();
	}
	
	public void Button_Pitch_IR(){
		// to do 
		med.getBtnRadius().setEnabled(false);
		med.getBtnHeight().setEnabled(false);
		
		med.getBtnConditioner().setSelection(false);
		med.getBtnError().setSelection(false);
		med.getBtnFormSetError().setSelection(false);
		med.getBtnPitch_IR().setSelection(true);
		med.getBtnRadius_IR().setSelection(false);
		med.getBtnMaximumError().setSelection(false);
		med.getBtnFormDataTotal().setSelection(false);
		med.getBtnRadius().setSelection(false);
		med.getBtnHeight().setSelection(false);
		
		this.UpdateSelectGraphData();
	}
	
	public void Button_Radius_IR(){
		// to do 
		med.getBtnRadius().setEnabled(false);
		med.getBtnHeight().setEnabled(false);
		
		med.getBtnConditioner().setSelection(false);
		med.getBtnError().setSelection(false);
		med.getBtnFormSetError().setSelection(false);
		med.getBtnPitch_IR().setSelection(false);
		med.getBtnRadius_IR().setSelection(true);
		med.getBtnMaximumError().setSelection(false);
		med.getBtnFormDataTotal().setSelection(false);
		med.getBtnRadius().setSelection(false);
		med.getBtnHeight().setSelection(false);
		
		this.UpdateSelectGraphData();
	}
	
	public void Button_MaximumError(){
		med.getBtnRadius().setEnabled(false);
		med.getBtnHeight().setEnabled(false);
		
		med.getBtnConditioner().setSelection(false);
		med.getBtnError().setSelection(false);
		med.getBtnFormSetError().setSelection(false);
		med.getBtnPitch_IR().setSelection(false);
		med.getBtnRadius_IR().setSelection(false);
		med.getBtnMaximumError().setSelection(true);
		med.getBtnFormDataTotal().setSelection(false);
		med.getBtnRadius().setSelection(false);
		med.getBtnHeight().setSelection(false);
		
		this.UpdateSelectGraphData();
	}
	
	public void Button_FormDataTotal(){
		// to do 
		med.getBtnRadius().setEnabled(false);
		med.getBtnHeight().setEnabled(false);
		
		med.getBtnConditioner().setSelection(false);
		med.getBtnError().setSelection(false);
		med.getBtnFormSetError().setSelection(false);
		med.getBtnPitch_IR().setSelection(false);
		med.getBtnRadius_IR().setSelection(false);
		med.getBtnMaximumError().setSelection(false);
		med.getBtnFormDataTotal().setSelection(true);
		med.getBtnRadius().setSelection(false);
		med.getBtnHeight().setSelection(false);
		
		this.UpdateSelectGraphData();
	}
	
	public void Button_Radius(){
		
	}
	
	public void Button_Height(){
		
	}
	
	public void Button_AddGraph(){
		String fileName = med.getComboViewerSelectGraph().getCombo().getText();
		
		String fileType = this.getfileType(fileName);
		System.out.println("Add btn select graph : "+ fileName);
		if(fileName.length() == 0){
			//System.out.println("null obj: "+fileName);
		}else {
			ComboData_selectGraph obj = null;
			if(med.getBtnConditioner().getSelection()){
				obj = this.coilDBObj.getGraphDataObj().getGraphObj_conditioner(fileName);
			}else if(med.getBtnError().getSelection()){
				obj = this.coilDBObj.getGraphDataObj().getGraphObj_error(fileName);
			}else if(med.getBtnFormSetError().getSelection()){
				obj = this.coilDBObj.getGraphDataObj().getGraphObj_formSetError(fileName);
			}else if(med.getBtnPitch_IR().getSelection()){
				obj = this.coilDBObj.getGraphDataObj().getGraphObj_pitch_IR(fileName);
			}else if(med.getBtnRadius_IR().getSelection()){
				obj = this.coilDBObj.getGraphDataObj().getGraphObj_radius_IR(fileName);
			}else if(med.getBtnMaximumError().getSelection()){
				obj = this.coilDBObj.getGraphDataObj().getGraphObj_maximumError(fileName);
			}else if(med.getBtnFormDataTotal().getSelection()){
				obj = this.coilDBObj.getGraphDataObj().getGraphObj_formDataTotal(fileName);
			}
			
			
			/*
			System.out.println("Graph Title : " + obj.getGraphTitle());
			System.out.println("Iteration Name : " + obj.getIterationName());
			System.out.println("File Path : "+obj.getFilePath());
			System.out.println("result Type : "+ obj.getResultType());
			// */
			
			ListData_selectedGraph listGraphObj = new ListData_selectedGraph();
			listGraphObj.setGraph(obj);
			
			
			
			boolean isDuplicated = false;
			/*
			if(this.coilDBObj.getSelectedGraphList().size() != 0){
				System.out.println("1 Selected list num : "+ this.coilDBObj.getSelectedGraphList().size());
			}else {
				//System.out.println("Selected list num : "+ this.coilDBObj.getSelectedGraphList().size());
				System.out.println("1 Selected list num : 0");
			}
			//*/
			if(this.coilDBObj.getSelectedGraphList().size() != 0){
				for(ListData_selectedGraph lObj : this.coilDBObj.getSelectedGraphList()){
					//System.out.println("===>"+fileType);
					//System.out.println("====>"+lObj.getName());
					if(this.getfileType(lObj.getName()).equals(fileType)){
						if(lObj.getName().equals(listGraphObj.getName())){
							isDuplicated = true;
							break;
						}
					}else{
						isDuplicated = true;
						break;
					}
				}
			}
			
			
			if(!isDuplicated){
				this.coilDBObj.add_SelectedGraph(listGraphObj);
			}
			/*
			if(this.coilDBObj.getSelectedGraphList().size() != 0){
				System.out.println("2 Selected list num : "+ this.coilDBObj.getSelectedGraphList().size());
			}else {
				//System.out.println("Selected list num : "+ this.coilDBObj.getSelectedGraphList().size());
				System.out.println("2 Selected list num : 0");
			}
			//*/
		}
		
		med.getListViewerSelectedGraph().refresh();
	}
	
	private String getfileType(String fileName){
		String type = null;
		if(this.isMatch_conditionerType(fileName)){
			type = "conditioner";
		}
		
		if(this.isMatch_errorType(fileName)){
			type ="error";
		}
		
		if(this.isMatch_formSetErrorType(fileName)){
			type = "fromSetError";
		}
		
		if(this.isMatch_MaximumErrorType(fileName)){
			type = "MaxError";
		}
		
		if(this.isMatch_FormdataTotal(fileName)){
			type = "FormDataTotal";
		}
		
		if(this.isMatch_csetRezonedAlignPitchType(fileName)){
			type =  "cset_pitch";
		}
		
		if(this.isMatch_hsetRezonedAlignPitchType(fileName)){
			type =  "hset_pitch";
		}
		
		if(this.isMatch_formedRezonedAlignPitchType(fileName)){
			type =  "formed_pitch";
		}
		
		if(this.isMatch_csetRezonedAlignEradType(fileName)){
			type =  "cset_radius";
		}
		
		if(this.isMatch_hsetRezonedAlignEradType(fileName)){
			type =  "hset_radius";
		}
		
		if(this.isMatch_formedRezonedAlignEradType(fileName)){
			type =  "formed_radius";
		}
		
		return type;
	}
	
	
	public void Button_DeleteGraph(){
		try{
			IStructuredSelection selectionList =(IStructuredSelection)med.getListViewerSelectedGraph().getSelection();
			ListData_selectedGraph obj = (ListData_selectedGraph) selectionList.getFirstElement();
			System.out.println(obj.getName());
			
			this.coilDBObj.delete_SelectedGraph(obj);
			
			med.getListSelectedGraph().deselectAll();
			med.getListViewerSelectedGraph().refresh();
			
		}catch(Exception e){
		}
		
		/*
		System.out.println("+++++++++++++++++++++++++++");
		for(ListData_selectedGraph obj : this.coilDBObj.getSelectedGraphList()){
			System.out.println(obj.getName());
		}
		System.out.println("+++++++++++++++++++++++++++");
		//*/
	}
	
	public void Button_ShowGraphWindow(){
		if(this.coilDBObj.getSelectedGraphList().size() != 0){
			SimcosGraphViewer viewer = new SimcosGraphViewer();
			viewer.running(this.coilDBObj);
		}
	}
	
	public void Button_ShowImageWindow(){
		
		try{
			String selectImageName = med.getComboViewerSelectImage().getCombo().getText();
			ComboData_selectImage obj = this.coilDBObj.getImageAllDataObj().getImageObj(selectImageName);
			SimcosImageViewer viewer = new SimcosImageViewer();
			viewer.running(obj.getFilePath());
			System.out.println(selectImageName);
			System.out.println(obj.getFilePath());
		}catch(Exception e){
			
		}
	}
	
	public void Button_ShowTableData(){
		
	}
	//
	//
	///////////////////////////////////////////
	
	///////////////////////////////////////////
	//
	// List event (select list)
	//
	
	
	public void List_SelectedGraph(){
		/*
		IStructuredSelection selectionList =(IStructuredSelection)med.getListViewerSelectedGraph().getSelection();
		ListData_selectedGraph obj = (ListData_selectedGraph) selectionList.getFirstElement();
		System.out.println(obj.getName());
		*/
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
	
	public void Text_Modify_SeatUStepRotationHeight(){
		String data = med.getTextSeatUStepRotationHeight().getText().trim();
		this.coilDBObj.setSeatUStepRotationHeight(data);
	}
	
	public void Text_Modify_SeatLStepRotationHeight(){
		String data = med.getTextSeatLStepRotationHeight().getText().trim();
		this.coilDBObj.setSeatLStepRotationHeight(data);
	}
	
	public void Text_Modify_SeatURotationAngle(){
		String data = med.getTextSeatURotationAngle().getText().trim();
		this.coilDBObj.setSeatURotationAngle(data);
	}
	
	public void Text_Modify_SeatLRotationAngle(){
		String data = med.getTextSeatLRotationAngle().getText().trim();
		this.coilDBObj.setSeatLRotationAngle(data);
	}
	
	public void Text_Modify_RadiusConditionerValue(){
		String data =med.getTextRadiusConditionerValue().getText().trim();
		this.coilDBObj.setRadiusConditionerConstant(data);
	}
	
	public void Text_Modify_HeightConditionerValue(){
		String data = med.getTextHeightConditionerValue().getText().trim();
		this.coilDBObj.setHeightConditionerConstant(data);
		
	}
	
	public void Text_Modify_MaterialDBPath(){
		String data = med.getTextMaterialDBPath().getText().trim();
		this.coilDBObj.setMaterialDB(data);
	}
	
	public void Text_Modify_ParallelCpuNmber(){
		String data = med.getTextParallelCpuNumber().getText().trim();
		this.coilDBObj.setParallerCpuNmber(data);
	}
	
	public void Text_Modify_FormedCoildDataInterp(){
		String data = med.getTextFormedCoilDataInterp().getText().trim();
		this.coilDBObj.setFormedCoilDataInterpolationFile(data);
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
		this.UpdateSelectGraphData();
		// load combo data - image
		this.UpdateSelectImageData();
		// load combo data - tableData
		//this.UpdateSelectTableData();
		
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
		//System.out.println(med.getComboViewerSelectGraph().getCombo().getText());
		
	}


	public void Combo_selectImage() {
		// TODO Auto-generated method stub
		//System.out.println(med.getComboViewerSelectImage().getCombo().getText());
	}


	public void Combo_selectTableData() {
		// TODO Auto-generated method stub
		//System.out.println(med.getComboViewerSelectTableData().getCombo().getText());
		
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
		
		med.getLblModelnameValue().setText(projectName);
		med.getLblModelnameValue().setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
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
			this.projectName = this.coilDBObj.getProjectName();
			med.getLblModelnameValue().setText(this.projectName);
			med.getLblModelnameValue().setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		}else{
			
			String msg = "Check Project Folder.";
			MessageDlg msgDlg = new MessageDlg(med.getCompositeTop().getShell(),msg);
			msgDlg.open();
			log.error("File->Open - Check Project Folder");
		}
		
	}

	public void File_Save_Run(){
		System.out.println("File - > save In");
		this.SaveStep1();	
		this.SaveStep2();
		this.SaveSimcosDB();
		System.out.println("File - > save Out");
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
		
		med.getLblModelnameValue().setText(this.projectName);
		med.getLblModelnameValue().setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
	}
	
	public void Tool_TextEditor_Run(){
		//this.t_readLog.interrupt();
		try {
			Process p = Runtime.getRuntime().exec(this.TextEditorPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error - Run TextEditor");
			String msg = "Check TextEditor execution path";
			MessageDlg msgDlg = new MessageDlg(med.getCompositeTop().getShell(),msg);
			msgDlg.open();
			log.error("Check TextEditor execution path - "+e.getMessage());
		}
		
	}
	
	public void Tool_Mentat_Run(){
		//this.t_runCmd.interrupt();
		try {
			Process p = Runtime.getRuntime().exec(this.MentatPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error - Run Mentat");
			String msg = "Check Mentat execution path";
			MessageDlg msgDlg = new MessageDlg(med.getCompositeTop().getShell(),msg);
			msgDlg.open();
			log.error("Check Mentat execution path - "+e.getMessage());
		}
	}
	
	public void Setting_Preferences_Run(){
		this.MarcPath = this.getPreferencesObj().getPreferencesValue(Preferences.MarcPath);
		this.MentatPath = this.getPreferencesObj().getPreferencesValue(Preferences.MentatPath);
		this.TextEditorPath = this.getPreferencesObj().getPreferencesValue(Preferences.TextEditorPath);
		this.ExcelPath = this.getPreferencesObj().getPreferencesValue(Preferences.ExcelPath);
		this.CommandSolving = this.getPreferencesObj().getPreferencesValue(Preferences.CommandSolving);
		this.CommandPost = this.getPreferencesObj().getPreferencesValue(Preferences.CommandPost);
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
		//System.out.println(projectName);
		//System.out.println(parentPath);
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
		String postProc = myUtil.setPath(scriptFolder, AppFolder.postProcFileName);
		
		String SimcosDataFolder = myUtil.setPath(this.coilDBObj.getProjectFolderPath(),AppFolder.SIMCOS_DATA);
		String destPyScriptPath = myUtil.setPath(SimcosDataFolder, AppFolder.pythonScriptFileName);
		String destMainProcPath = myUtil.setPath(SimcosDataFolder, AppFolder.mainProcFileName);
		String destPostProcPath = myUtil.setPath(SimcosDataFolder, AppFolder.postProcFileName);
		
		myUtil.fileCopy(pyScript, destPyScriptPath);
		myUtil.fileCopy(mainProc, destMainProcPath);
		myUtil.fileCopy(postProc, destPostProcPath);
	}
	
	private void copySourceFileForModeling(){
		// MaterialDB, Initial Conditioner 
		String SimcosDataFolder = myUtil.setPath(this.coilDBObj.getProjectFolderPath(),AppFolder.SIMCOS_DATA);
		
		String destMaterialDBFile = "";
		//String destInitialConditionerFile = "";
		String destRadiusConditionerFile = "";
		String destHeightConditionerFile = "";
		String destFormdCoilDataInterpolationFile = "";
		/*
		if(this.coilDBObj.getInitialConditionerType().equals(CoilDB.FILE_TYPE)){
			String InitialConditionerFileName = myUtil.getFileNameIncludeExtension(this.coilDBObj.getInitialConditionerFile());
			destInitialConditionerFile = myUtil.setPath(SimcosDataFolder, InitialConditionerFileName);
			myUtil.fileCopy(this.coilDBObj.getInitialConditionerFile(), destInitialConditionerFile);
		}
		// */
		if(this.coilDBObj.getRadiusConditionerType().equals(CoilDB.FILE_TYPE)){
			String RadiusConditionerFileName = myUtil.getFileNameIncludeExtension(this.coilDBObj.getRadiusConditionerFile());
			destRadiusConditionerFile = myUtil.setPath(SimcosDataFolder, RadiusConditionerFileName);
			myUtil.fileCopy(this.coilDBObj.getRadiusConditionerFile(), destRadiusConditionerFile);
		}
		
		if(this.coilDBObj.getHeightConditionerType().equals(CoilDB.FILE_TYPE)){
			String HeightConditionerFileName = myUtil.getFileNameIncludeExtension(this.coilDBObj.getHeightConditionerFile());
			destHeightConditionerFile = myUtil.setPath(SimcosDataFolder, HeightConditionerFileName);
			myUtil.fileCopy(this.coilDBObj.getHeightConditionerFile(), destHeightConditionerFile);
		}
		
		if(!this.coilDBObj.getMaterialDB().equals("null")){
			String MaterialDBFileName = myUtil.getFileNameIncludeExtension(this.coilDBObj.getMaterialDB());
			destMaterialDBFile = myUtil.setPath(SimcosDataFolder, MaterialDBFileName);
			myUtil.fileCopy(this.coilDBObj.getMaterialDB(), destMaterialDBFile);
		}
		
		if(!this.coilDBObj.getFormedCoilDataInterpolationFile().equals("null")){
			String FormedCoilDataInterpolationFileName = myUtil.getFileNameIncludeExtension(this.coilDBObj.getFormedCoilDataInterpolationFile());
			destFormdCoilDataInterpolationFile = myUtil.setPath(SimcosDataFolder, FormedCoilDataInterpolationFileName);
			myUtil.fileCopy(this.coilDBObj.getFormedCoilDataInterpolationFile(), destFormdCoilDataInterpolationFile);
		}
	}
	//==================================================================================
	private void getPreferencesData(){
		// Load Preferences data 
		this.MarcPath = this.getPreferencesObj().getPreferencesValue(Preferences.MarcPath);
		this.MentatPath = this.getPreferencesObj().getPreferencesValue(Preferences.MentatPath);
		this.TextEditorPath = this.getPreferencesObj().getPreferencesValue(Preferences.TextEditorPath);
		this.ExcelPath = this.getPreferencesObj().getPreferencesValue(Preferences.ExcelPath);
		this.CommandSolving = this.getPreferencesObj().getPreferencesValue(Preferences.CommandSolving);
		this.CommandPost = this.getPreferencesObj().getPreferencesValue(Preferences.CommandPost);
		
		/*
		System.out.println(this.MarcPath);
		System.out.println(this.MentatPath);
		System.out.println(this.TextEditorPath);
		System.out.println(this.ExcelPath);
		System.out.println(this.CommandSovling);
		System.out.println(this.CommandPost);
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
			}else{
				//System.out.println(line);
				//myUtil.printArrData(tokens);
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
			log.error("Coil data table - "+e.getMessage());
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
		// Setting Coil Information
		med.getTextCoilFilePath().setText("");
		med.getTextProductName().setText("");
		med.getTextWireDiameter().setText("");
		med.getTextCenterDiameter().setText("");
		med.getTextInternalDiameter().setText("");
		med.getTextExternalDiameter().setText("");
		med.getTextUpperInnerDiameter().setText("");
		med.getTextLowerInnerDiameter().setText("");
		med.getTextTotalTurns().setText("");
		this.coilDBObj.getGeometryDataTableList().clear();
		this.UpdateCoilGeometryData();
		
		
		// Setting Process Information 
		med.getTextHotSettingTemp().setText(this.initValueObj.getInitValue(InitValue.HotSettingTemp));
		med.getTextColdSettingTemp().setText(this.initValueObj.getInitValue(InitValue.ColdSettingTemp));
		med.getTextHotSettingHeight().setText(this.initValueObj.getInitValue(InitValue.HotSettingHeight));
		med.getTextColdSettingHeight().setText(this.initValueObj.getInitValue(InitValue.ColdSettingHeight));
		if(this.initValueObj.getInitValue(InitValue.SeatType).equals(CoilDB.SEAT_TYPE_STANDARD)){
			med.getBtnStandard().setSelection(true);
			med.getBtnRrCoil().setSelection(false);
			med.getTextSeatUStepRotationHeight().setEnabled(false);
			med.getTextSeatLStepRotationHeight().setEnabled(false);
			med.getTextSeatURotationAngle().setEnabled(false);
			med.getTextSeatLRotationAngle().setEnabled(false);
		}else{
			med.getBtnStandard().setSelection(false);
			med.getBtnRrCoil().setSelection(true);
			med.getTextSeatUStepRotationHeight().setEnabled(true);
			med.getTextSeatLStepRotationHeight().setEnabled(true);
			med.getTextSeatURotationAngle().setEnabled(true);
			med.getTextSeatLRotationAngle().setEnabled(true);
		}
		med.getTextSeatUInnerMargina().setText(this.initValueObj.getInitValue(InitValue.SeatUInnerMargina));
		med.getTextSeatLInnerMargina().setText(this.initValueObj.getInitValue(InitValue.SeatLInnerMargina));
		med.getTextSeatHeight().setText(this.initValueObj.getInitValue(InitValue.SeatHeight));
		med.getTextSeatUStepRotationHeight().setText(this.initValueObj.getInitValue(InitValue.SeatUStepRotationHeight));
		med.getTextSeatLStepRotationHeight().setText(this.initValueObj.getInitValue(InitValue.SeatLStepRotationHeight));
		med.getTextSeatURotationAngle().setText(this.initValueObj.getInitValue(InitValue.SeatURotationAngle));
		med.getTextSeatLRotationAngle().setText(this.initValueObj.getInitValue(InitValue.SeatLRotationAngle));
		// Initial Conditioner
		/*
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
		// */
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
		// Material Database 
		med.getTextMaterialDBPath().setText(this.initValueObj.getInitValue(InitValue.MaterialDatabase));
		// Parallel CPU Number 
		med.getTextParallelCpuNumber().setText(this.initValueObj.getInitValue(InitValue.ParallelCPUNumber));
		// Formed Coil Data Interpolation File
		med.getTextFormedCoilDataInterp().setText(this.initValueObj.getInitValue(InitValue.FormedCoilDataInterpolationFile));
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
		if(this.coilDBObj.getSeatType().equals(CoilDB.SEAT_TYPE_STANDARD)){
			med.getBtnStandard().setSelection(true);
			med.getBtnRrCoil().setSelection(false);
			med.getTextSeatUStepRotationHeight().setEnabled(false);
			med.getTextSeatLStepRotationHeight().setEnabled(false);
			med.getTextSeatURotationAngle().setEnabled(false);
			med.getTextSeatLRotationAngle().setEnabled(false);
		}else{
			med.getBtnStandard().setSelection(false);
			med.getBtnRrCoil().setSelection(true);
			med.getTextSeatUStepRotationHeight().setEnabled(true);
			med.getTextSeatLStepRotationHeight().setEnabled(true);
			med.getTextSeatURotationAngle().setEnabled(true);
			med.getTextSeatLRotationAngle().setEnabled(true);
		}
		med.getTextSeatUInnerMargina().setText(this.coilDBObj.getSeatUIneerMargina());
		med.getTextSeatLInnerMargina().setText(this.coilDBObj.getSeatLIneerMargina());
		med.getTextSeatHeight().setText(this.coilDBObj.getSeatHeight());
		med.getTextSeatUStepRotationHeight().setText(this.coilDBObj.getSeatUStepRotationHeight());
		med.getTextSeatLStepRotationHeight().setText(this.coilDBObj.getSeatLStepRotationHeight());
		med.getTextSeatURotationAngle().setText(this.coilDBObj.getSeatURotationAngle());
		med.getTextSeatLRotationAngle().setText(this.coilDBObj.getSeatLRotationAngle());
		// Initial Conditioner
		/*
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
		// */
		if(this.coilDBObj.getRadiusConditionerType().equals(CoilDB.CONSTANT_TYPE)){
			med.getBtnRadiusConditionerConstant().setSelection(true);
			med.getBtnRadiusConditionerFile().setSelection(false);
			med.getTextRadiusConditionerValue().setText(this.coilDBObj.getRadiusConditionerConstant());
			med.getTextRadiusConditionerValue().setEnabled(true);
			med.getTextRadiusConditionerPath().setText(myUtil.getFileNameIncludeExtension(this.coilDBObj.getRadiusConditionerFile()));
			med.getTextRadiusConditionerPath().setEnabled(false);
			med.getBtnRadiusConditionerExplorer().setEnabled(false);
		}else{
			med.getBtnRadiusConditionerConstant().setSelection(false);
			med.getBtnRadiusConditionerFile().setSelection(true);
			med.getTextRadiusConditionerValue().setText(this.coilDBObj.getRadiusConditionerConstant());
			med.getTextRadiusConditionerValue().setEnabled(false);
			med.getTextRadiusConditionerPath().setText(myUtil.getFileNameIncludeExtension(this.coilDBObj.getRadiusConditionerFile()));
			med.getTextRadiusConditionerPath().setEnabled(true);
			med.getBtnRadiusConditionerExplorer().setEnabled(true);			
		}
		if(this.coilDBObj.getHeightConditionerType().equals(CoilDB.CONSTANT_TYPE)){
			med.getBtnHeightConditionerConstant().setSelection(true);
			med.getBtnHeightConditionerFile().setSelection(false);
			med.getTextHeightConditionerValue().setText(this.coilDBObj.getHeightConditionerConstant());
			med.getTextHeightConditionerValue().setEnabled(true);
			med.getTextHeightConditionerPath().setText(myUtil.getFileNameIncludeExtension(this.coilDBObj.getHeightConditionerFile()));
			med.getTextHeightConditionerPath().setEnabled(false);
			med.getBtnHeightConditionerExplorer().setEnabled(false);
		}else{
			med.getBtnHeightConditionerConstant().setSelection(false);
			med.getBtnHeightConditionerFile().setSelection(true);
			med.getTextHeightConditionerValue().setText(this.coilDBObj.getHeightConditionerConstant());
			med.getTextHeightConditionerValue().setEnabled(false);
			med.getTextHeightConditionerPath().setText(myUtil.getFileNameIncludeExtension(this.coilDBObj.getHeightConditionerFile()));
			med.getTextHeightConditionerPath().setEnabled(true);
			med.getBtnHeightConditionerExplorer().setEnabled(true);			
		}
		
		
		// Material Datatbase
		med.getTextMaterialDBPath().setText(myUtil.getFileName(this.coilDBObj.getMaterialDB()));
		// Parallel CPU Number
		med.getTextParallelCpuNumber().setText(this.coilDBObj.getParallerCpuNmber());
		// Formed Coil Data Interpolation File
		med.getTextFormedCoilDataInterp().setText(this.coilDBObj.getFormedCoilDataInterpolationFile());
		// Analysis Options
		med.getTextRadiusTolerance().setText(this.coilDBObj.getRadiusTolerance());
		med.getTextHeightTolerance().setText(this.coilDBObj.getHeightTolerance());
		med.getTextMaximumIterationNumber().setText(this.coilDBObj.getMaximumIterationNumber());
	}
	
	
	//==================================================================================
	private String FileExplorer_RadiusConditioner(){
		FileDialog dlg = new FileDialog(med.getBtnRadiusConditionerExplorer().getShell(),SWT.OPEN);
		dlg.setText("Select Radius Conditioner File.");
		
		String [] extNames = {"CSV(*.CSV)"};
		String [] extType = {"*.csv"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		// Open ProjectFolder.
		dlg.setFilterPath(this.cPath_radiusConditioner);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return "Retry";
		}else {
			this.cPath_radiusConditioner = myUtil.getParentPath(path);
			return path;
		}
	}
	
	private String FileExplorer_HeightConditioner(){
		FileDialog dlg = new FileDialog(med.getBtnHeightConditionerExplorer().getShell(),SWT.OPEN);
		dlg.setText("Select Height Conditioner File.");
		
		String [] extNames = {"CSV(*.CSV)"};
		String [] extType = {"*.csv"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		// Open ProjectFolder.
		dlg.setFilterPath(this.cPath_heightConditioner);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return "Retry";
		}else {
			this.cPath_heightConditioner = myUtil.getParentPath(path);
			return path;
		}
	}
	
	private String FileExplorer_MaterialDB(){
		FileDialog dlg = new FileDialog(med.getBtnMaterialDBExplorer().getShell(),SWT.OPEN);
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
	
	private String FileExplorer_FormedCoilDataInterp(){
		FileDialog dlg = new FileDialog(med.getBtnFormedCoilDataInterp().getShell(),SWT.OPEN);
		dlg.setText("Select Formed Coil Data Interpolation File.");
		
		String [] extNames = {"CSV(*.CSV)"};
		String [] extType = {"*.csv"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		// Open ProjectFolder.
		dlg.setFilterPath(this.cPath_formedCoilDataInterp);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return "Retry";
		}else {
			this.cPath_heightConditioner = myUtil.getParentPath(path);
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
		if(med.getBtnStandard().getSelection()){
			this.coilDBObj.setSeatType(CoilDB.SEAT_TYPE_STANDARD);
		}else if(med.getBtnRrCoil().getSelection()){
			this.coilDBObj.setSeatType(CoilDB.SEAT_TYPE_RRCoil);
		}
		this.coilDBObj.setSeatUIneerMargina(med.getTextSeatUInnerMargina().getText().trim());
		this.coilDBObj.setSeatLIneerMargina(med.getTextSeatLInnerMargina().getText().trim());
		this.coilDBObj.setSeatHeight(med.getTextSeatHeight().getText().trim());
		this.coilDBObj.setSeatUStepRotationHeight(med.getTextSeatUStepRotationHeight().getText().trim());
		this.coilDBObj.setSeatLStepRotationHeight(med.getTextSeatLStepRotationHeight().getText().trim());
		this.coilDBObj.setSeatURotationAngle(med.getTextSeatURotationAngle().getText().trim());
		this.coilDBObj.setSeatLRotationAngle(med.getTextSeatLRotationAngle().getText().trim());
		// Initial Conditioner
		/*
		if(med.getBtnInitialConditionerConstant().getSelection()){
			this.coilDBObj.setInitialConditionerType(CoilDB.CONSTANT_TYPE);
		}else{
			this.coilDBObj.setInitialConditionerType(CoilDB.FILE_TYPE);
		}
		// */
		if(med.getBtnRadiusConditionerConstant().getSelection()){
			this.coilDBObj.setRadiusConditionerType(CoilDB.CONSTANT_TYPE);
		}else{
			this.coilDBObj.setRadiusConditionerType(CoilDB.FILE_TYPE);
		}
		if(med.getBtnHeightConditionerConstant().getSelection()){
			this.coilDBObj.setHeightConditionerType(CoilDB.CONSTANT_TYPE);
		}else{
			this.coilDBObj.setHeightConditionerType(CoilDB.FILE_TYPE);
		}
		
		//this.coilDBObj.setInitialConditionerConstant(med.getTextInitialConditionerValue().getText().trim());
		this.coilDBObj.setRadiusConditionerConstant(med.getTextRadiusConditionerValue().getText().trim());
		this.coilDBObj.setHeightConditionerConstant(med.getTextHeightConditionerValue().getText().trim());
		
		this.coilDBObj.setParallerCpuNmber(med.getTextParallelCpuNumber().getText().trim());
		
		//this.coilDBObj.setFormedCoilDataInterpolationFile(med.getTextFormedCoilDataInterp().getText().trim());
	}
	
	private void SaveStep2(){
		this.coilDBObj.setRadiusTolerance(med.getTextRadiusTolerance().getText().trim());
		this.coilDBObj.setHeightTolerance(med.getTextHeightTolerance().getText().trim());
		this.coilDBObj.setMaximumIterationNumber(med.getTextMaximumIterationNumber().getText().trim());
	}
	
	private void SaveStep3(){
		
	}
	//==================================================================================
	private void UpdateSelectGraphData(){
		///////////////////////////////////////////////////////////////
		if(!this.coilDBObj.getGraphDataList_conditioner().isEmpty()){
			this.coilDBObj.getGraphDataList_conditioner().clear();
			this.coilDBObj.getSelectedGraphList().clear();
			myUtil.CleareObj(this.coilDBObj.getGraphDataObj());
		}
		if(!this.coilDBObj.getGraphDataList_error().isEmpty()){
			this.coilDBObj.getGraphDataList_error().clear();
			this.coilDBObj.getSelectedGraphList().clear();
			myUtil.CleareObj(this.coilDBObj.getGraphDataObj());
		}
		if(!this.coilDBObj.getGraphDataList_formSetError().isEmpty()){
			this.coilDBObj.getGraphDataList_formSetError().clear();
			this.coilDBObj.getSelectedGraphList().clear();
			myUtil.CleareObj(this.coilDBObj.getGraphDataObj());
		}
		if(!this.coilDBObj.getGraphDataList_pitch_IR().isEmpty()){
			this.coilDBObj.getGraphDataList_pitch_IR().clear();
			this.coilDBObj.getSelectedGraphList().clear();
			myUtil.CleareObj(this.coilDBObj.getGraphDataObj());
		}
		if(!this.coilDBObj.getGraphDataList_radius_IR().isEmpty()){
			this.coilDBObj.getGraphDataList_radius_IR().clear();
			this.coilDBObj.getSelectedGraphList().clear();
			myUtil.CleareObj(this.coilDBObj.getGraphDataObj());
		}
		if(!this.coilDBObj.getGraphDataList_maximumError().isEmpty()){
			this.coilDBObj.getGraphDataList_maximumError().clear();
			this.coilDBObj.getSelectedGraphList().clear();
			myUtil.CleareObj(this.coilDBObj.getGraphDataObj());
		}
		if(!this.coilDBObj.getGraphDataList_formDataTotal().isEmpty()){
			this.coilDBObj.getGraphDataList_formDataTotal().clear();
			this.coilDBObj.getSelectedGraphList().clear();
			myUtil.CleareObj(this.coilDBObj.getGraphDataObj());
		}
		
		GraphAllData obj = new GraphAllData(this.coilDBObj);
		this.coilDBObj.setGraphAllDataObj(obj);
		obj.LoadingResult(myUtil.setPath(this.coilDBObj.getProjectFolderPath(),AppFolder.SIMCOS_DATA));
		
		//
		///////////////////////////////////////////////////////////////
		try{

			med.getComboViewerSelectGraph().setLabelProvider(new ComboViewerLabelProvider_SelectGraph());
			med.getComboViewerSelectGraph().setContentProvider(new ArrayContentProvider());
			if(med.getBtnConditioner().getSelection()){
				med.getComboViewerSelectGraph().setInput(this.coilDBObj.getGraphDataList_conditioner());	
			}else if(med.getBtnError().getSelection()){
				med.getComboViewerSelectGraph().setInput(this.coilDBObj.getGraphDataList_error());
			}else if(med.getBtnFormSetError().getSelection()){
				med.getComboViewerSelectGraph().setInput(this.coilDBObj.getGraphDataList_formSetError());
			}else if(med.getBtnPitch_IR().getSelection()){
				med.getComboViewerSelectGraph().setInput(this.coilDBObj.getGraphDataList_pitch_IR());
			}else if(med.getBtnRadius_IR().getSelection()){
				med.getComboViewerSelectGraph().setInput(this.coilDBObj.getGraphDataList_radius_IR());
			}else if(med.getBtnMaximumError().getSelection()){
				med.getComboViewerSelectGraph().setInput(this.coilDBObj.getGraphDataList_maximumError());
			}else if(med.getBtnFormDataTotal().getSelection()){
				med.getComboViewerSelectGraph().setInput(this.coilDBObj.getGraphDataList_formDataTotal());
			}
			
			med.getListViewerSelectedGraph().setLabelProvider(new ListLabelProvider_SelectedGraph());
			med.getListViewerSelectedGraph().setContentProvider(new ArrayContentProvider());
			med.getListViewerSelectedGraph().setInput(this.coilDBObj.getSelectedGraphList());
		}catch(Exception e){
			System.out.println("ERROR - Graph data");
			log.error("Graph data - "+e.getMessage());
		}
	}
	
	
	private void UpdateSelectImageData(){
		///////////////////////////////////////////////////////////////
		// DEMO Data --> todo... access Image data folder
		if(!this.coilDBObj.getImageDataList().isEmpty()){
			this.coilDBObj.getImageDataList().clear();
		}
		
		/*
		for(int i = 0; i<10 ;i++){
			ComboData_selectImage obj = new ComboData_selectImage();
			obj.setName("image - "+(i+1));
			this.coilDBObj.add_ImageDataCombo(obj);
		}
		//*/
		ImageAllData obj = new ImageAllData(this.coilDBObj);
		this.coilDBObj.setImageAllDataObj(obj);
		obj.LoadingResult(myUtil.setPath(this.coilDBObj.getProjectFolderPath(),AppFolder.SIMCOS_DATA));
		
		//
		///////////////////////////////////////////////////////////////
		try{
			med.getComboViewerSelectImage().setLabelProvider(new ComboViewerLabelProvider_SelectImage());
			med.getComboViewerSelectImage().setContentProvider(new ArrayContentProvider());
			med.getComboViewerSelectImage().setInput(this.coilDBObj.getImageDataList());
		}catch(Exception e){
			System.out.println("ERROR - Image data");
			log.error("Image data - "+e.getMessage());
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
			log.error("Table data select Table Data - "+e.getMessage());
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
	
	public boolean isMatch_conditionerType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("[0-9]+("+GraphAllData.conditionerType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
			//System.out.println("Conditioner file : "+fileName);
		}else {
			result = false;
		}
		return result;
	}
	
	public boolean isMatch_errorType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("[0-9]+("+GraphAllData.errorType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
			//System.out.println("Error file : "+fileName);
		}else {
			result = false;
		}
		return result;	
	}
	
	public boolean isMatch_formSetErrorType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("[0-9]+("+GraphAllData.formSetErrorType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
			//System.out.println("FormSetError : "+fileName);
		}else {
			result = false;
		}
		return result;
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

	public boolean isMatch_MaximumErrorType(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("("+GraphAllData.maximumErrorType+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
			//System.out.println("Max Err : "+fileName);
		}else{
			result = false;
		}
		
		return result;
	}
	
	public boolean isMatch_FormdataTotal(String fileName){
		boolean result = false;
		Pattern p = Pattern.compile("("+GraphAllData.FormdataTotal+")$");
		Matcher m = p.matcher(fileName);
		if(m.find()){
			result = true;
			//System.out.println("FomeDataTotal : " + fileName);
		}else{
			result = false;
		}
		return result;
	}
	
}
