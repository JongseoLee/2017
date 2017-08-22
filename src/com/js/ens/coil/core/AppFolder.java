package com.js.ens.coil.core;

import com.js.util.myUtil;

public class AppFolder {
	private MainController MC = MainController.getInstatnce();
	
	// FolderName
	public static String CONFIG = "Config";
	public static String SCRIPT = "Script";
	public static String MATERIALDB = "MaterialDB";
	public static String SIMCOS_WORKSPACE = "Simcos_Workspace";
	public static String SIMCOS_DATA = "SimcosData";
	
	
	// Config file Name
	public static String LabelFile = "label.ini";
	public static String CoilDesignIndexFile = "coil_design_index.ini";
	public static String InitValueFile = "InitValue.ini";
	public static String PreferencesFile = "preferences.ini";
	public static String DbTemplateFile = "SimcosDbTemplate.ini";
	public static String dbFileName = "SimcosDB.ens";
	
	// input files in SimcosData folder for Simulation
	public static String coilParamCSVFileName = "coil_param.csv";
	public static String coilDesignCSVFileName = "coil_design.csv";
	public static String mainProcFileName = "main_dwku.proc";
	public static String pythonScriptFileName = "iterative_reverse_setting_0721.py";
	public static String materialFileName = "posh1s125.mud";
	//public static String pythonScriptInputDataFileName = "init.dat";
	
	// member variable
	private String userWorkspace;
	
	public AppFolder() {
		// TODO Auto-generated constructor stub
	}
	
	public void makeUserWorkspace(){
		this.userWorkspace = MC.getCoilDBObj().getProjectFolderPath();
		//String resultFolderPath = myUtil.setPath(this.userWorkspace, RESULT);
		String simcosDataFolderPath = myUtil.setPath(this.userWorkspace, SIMCOS_DATA);
		
		//myUtil.makeDirectory(resultFolderPath);
		myUtil.makeDirectory(simcosDataFolderPath);
	}
	
	

	
}
