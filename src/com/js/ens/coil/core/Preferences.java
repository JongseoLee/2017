package com.js.ens.coil.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class Preferences {
	/*
	private static Preferences instance = new Preferences();
	public static Preferences getInstance(){
		return instance;
	}
	// */
	private MainController MC = MainController.getInstatnce();
	
	private Map<String,String> preferencesMap;
	private String preferencePath;
	
	public static String MarcPath = "MarcPath";
	public static String MentatPath ="MentatPath";
	public static String TextEditorPath = "TextEditorPath";
	public static String ExcelPath = "ExcelPath";
	public static String CommandSolving = "CommandSolving";
	public static String CommandPost = "CommandPost";
	
	public Preferences() {
		// TODO Auto-generated constructor stub
		this.preferencesMap = new HashMap<String,String>();
		this.preferencePath = myUtil.setPath(myUtil.setPath(MC.getAppPath(),AppFolder.CONFIG),AppFolder.PreferencesFile);
		
		this.readPreference();
	}
	
	private void readPreference(){
		try{
			Reader reader = new Reader(this.preferencePath);
			reader.running_utf8();
			for(String line : reader.getFileDataList()){
				String data = line.trim();
				if(data.contains("=")){
					ArrayList<String> tokens = new ArrayList<String>();
					tokens = myUtil.splitData(line, "=");
					this.preferencesMap.put(tokens.get(0), tokens.get(1));
				}
			}
		}catch(Exception e){
		}
	}
	
	public String getPreferencesValue(String key){
		if(this.preferencesMap.containsKey(key)){
			return this.preferencesMap.get(key);
		}else{
			return "no - preferences value";
		}
	}
	
	public void setPreferencesValue(String key, String value){
		if(key.equals(MarcPath)){
			this.preferencesMap.put(MarcPath, value);
		}else if(key.equals(MentatPath)){
			this.preferencesMap.put(MentatPath, value);
		}else if(key.equals(TextEditorPath)){
			this.preferencesMap.put(TextEditorPath, value);
		}else if(key.equals(ExcelPath)){
			this.preferencesMap.put(ExcelPath, value);
		}else if(key.equals(CommandSolving)){
			this.preferencesMap.put(CommandSolving, value);
		}else if(key.equals(CommandPost)){
			this.preferencesMap.put(CommandPost, value);
		}
	}
	
	public void writePreferenceValue(){
		ArrayList<String> outputDataList = new ArrayList<String>();
		outputDataList.add("#######################################################");
		outputDataList.add("#");
		outputDataList.add("# Preferences data File ");
		outputDataList.add("#");
		outputDataList.add("#");
		outputDataList.add("#######################################################");
		outputDataList.add("MarcPath="+this.preferencesMap.get(Preferences.MarcPath));
		outputDataList.add("MentatPath="+this.preferencesMap.get(Preferences.MentatPath));
		outputDataList.add("TextEditorPath="+this.preferencesMap.get(Preferences.TextEditorPath));
		outputDataList.add("ExcelPath="+this.preferencesMap.get(Preferences.ExcelPath));
		outputDataList.add("CommandSolving="+this.preferencesMap.get(Preferences.CommandSolving));
		outputDataList.add("CommandPost="+this.preferencesMap.get(Preferences.CommandPost));
		
		Writer writer = new Writer(this.preferencePath);
		writer.running_utf8(outputDataList);
		
		myUtil.CleareObj(outputDataList);
	}

}
