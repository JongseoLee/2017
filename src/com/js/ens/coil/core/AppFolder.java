package com.js.ens.coil.core;

import com.js.util.myUtil;

public class AppFolder {
	private MainController MC = MainController.getInstatnce();
	
	// FolderName
	public static String CONFIG = "Config";
	public static String SCRIPT = "Script";
	public static String MATERIAL_DB = "MaterialDB";
	public static String SIMCOS_WORKSPACE = "Simcos_Workspace";
	public static String SIMCOS_DATA = "SimcosData";
	
	
	// Config file Name
	public static String LabelFile = "label.ini";
	public static String CoilDesignIndexFile = "coil_design_index.ini";
	public static String InitValueFile = "InitValue.ini";
	public static String PreferencesFile = "preferences.ini";
	public static String DbTemplateFile = "SimcosDbTemplate.ini";
	public static String dbFileName = "SimcosDB.sdb";
	
	// input files in SimcosData folder for Simulation
	public static String coilParamCSVFileName = "coil_param.csv";
	public static String coilDesignCSVFileName = "coil_design.csv";
	public static String mainProcFileName = "main_dwku.proc";
	public static String pythonScriptFileName = "iterative_reverse_setting_0721.py";
	public static String coilItrLogFileName = "coil_itr.log";
	
	// member variable
	private String userWorkspace;
	
	public AppFolder() {
		// TODO Auto-generated constructor stub
	}
	
	public void makeUserWorkspace(){
		this.userWorkspace = MC.getCoilDBObj().getProjectFolderPath();
		String simcosDataFolderPath = myUtil.setPath(this.userWorkspace, SIMCOS_DATA);
		myUtil.makeDirectory(simcosDataFolderPath);
	}
	
}
