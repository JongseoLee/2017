package com.js.ens.coil.core;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import com.js.util.myUtil;

public class ReadLog_backup implements Runnable{
	private MainController MC = MainController.getInstatnce();
	private Mediator med = Mediator.getInstance();
	
	private String logFilePath;
	private Double maxItrNum;
	private int progressbarValue = 0;
	private int readFilePeriod = 2000;
	private RandomAccessFile rf;
	private ArrayList<String> logDataList;
	private int currentItrNum;
	private int currentMaxItrNum;
	
	// log Status value
	private final String READY = "Ready";
	private final String MODELING = "Modeling";
	private final String MODELING_NUM = "0";
	private final String SOLVING = "Solving";
	private final String DONE = "Done";
	private final String DONE_NUM = "-99";
	private final String ERROR = "Error";
	private final String ERROR_NUM = "-1";
	private final String SKIP_LINE = "Skip Line";
	private final String SKIP_LINE_NUM = "-2";
	// log file key
	private final String LogStatus_modelingStart = "*** Start Modeling";
	private final String LogStatus_modelingEnd = "*** End Modeling";
	private final String LogStatus_solvingStart = "*** Start Marc Run =";
	private final String LogStatus_solvingEnd = "*** End Marc Run =";
	private final String LogStatus_error ="*** Error";
	private final String LogStatus_endAllProcess = "*** End Process";
	
	public ReadLog_backup() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(String logFilePath, String maxItrNum){
		this.logFilePath = logFilePath;
		this.maxItrNum = Double.valueOf(maxItrNum);
		this.currentMaxItrNum = Integer.valueOf(maxItrNum);
		this.currentItrNum = 1;
		this.logDataList = new ArrayList<String>();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("START - ReadLog");
		long linePosition = 0;
		boolean doing = true;
		ArrayList<String> resultList = new ArrayList<String>();
		
		long fileSize = 0;
		
		
		// [filePointer, log status, iteration num]
		while(doing){
			
			File logFile = new File(this.logFilePath);
			if(!logFile.exists()){
				try{
					System.out.println("no File in");
					Thread.sleep(5000);
					System.out.println("no File out");
				}catch(InterruptedException e){
					//System.out.println("Read log(Thread Sleep) : "+e.getMessage());
					e.printStackTrace();
				}
			}else{
				long currentFileSize = logFile.length();
				if(fileSize == currentFileSize){
					try{
						System.out.println("sleep in - fileSize equal");
						Thread.sleep(this.readFilePeriod);
						System.out.println("sleep out");
					}catch(InterruptedException e){
						//System.out.println("Read log(Thread Sleep) : "+e.getMessage());
						e.printStackTrace();
					}
				}else{
					fileSize = currentFileSize;
					System.out.println("File Size : "+fileSize);
					if(myUtil.fileIsLive(logFilePath)){
						try{
							rf = new RandomAccessFile(this.logFilePath,"r");
							rf.seek(linePosition);
							resultList = readLines(rf);
							 
							
							linePosition = Long.parseLong(resultList.get(0));
							final String simStatus = resultList.get(1);
							double itrNum = Double.valueOf(resultList.get(2));
							
							if(itrNum > 0.0){
								progressbarValue = (int) Math.round(( itrNum/(this.maxItrNum+1) )* 100.0);
								currentItrNum = (int) itrNum;
							}
							
							if(simStatus.equals(this.DONE)){
								progressbarValue = 100;
								doing = false;
							}
							
							
							rf.close();
							myUtil.CleareObj(rf);
							
							
							
							
							med.getParentView().getDisplay().asyncExec(new Runnable(){
								@Override
								public void run() {
									// TODO Auto-generated method stub
									try{
										if(!simStatus.equals(SKIP_LINE)){
											med.getLblSimulationStatus().setText(simStatus);
										}
										med.getProgressBarSimulationIteration().setSelection(progressbarValue);
										String logData = "";
										for(String line : logDataList){
											logData = logData + line+"\n";
										}
										med.getTextLogEditor().setText(logData);
										med.getTextLogEditor().setSelection(logData.length());
										
										if(simStatus.equals(SOLVING)){
											med.getLblIterationNumber().setText( "Iteration step : "+ String.valueOf(currentItrNum) + " / " +String.valueOf(currentMaxItrNum));
										}
										
										if(simStatus.equals(DONE)){
											med.getBtnStartSimulation().setEnabled(true);
										}
									}catch(Exception e){
										
									}
								}
							});
							
							
							
							
							
						}catch(Exception e){
							System.out.println("Read log : "+e.getMessage());
							//e.printStackTrace();
						}finally{
							System.out.println("BYEBYE - readLog");
						}
					}
					
				}
			}
			
			/* */
			
			//*/
			
		}
		System.out.println("END - ReadLog");
	}
	
	public ArrayList<String> readLines(RandomAccessFile rf) throws IOException{
		ArrayList<String> resultList = new ArrayList<String>();
		ArrayList<String> parsingDataList = new ArrayList<String>();
		String filePointer;
		String line;
		
		while((line = rf.readLine())!= null){
			logDataList.add(line);
			parsingDataList = parsing(line);
			if(parsingDataList.get(0).equals(this.LogStatus_endAllProcess)){
				//System.out.println("Line data contains Job End");
				break;
			}
		}
		
		filePointer = String.valueOf(rf.getFilePointer());
		resultList.add(filePointer);
		resultList.add(parsingDataList.get(0));
		resultList.add(parsingDataList.get(1));
		
		myUtil.CleareObj(parsingDataList);
		return resultList;
	}
	
	private ArrayList<String> parsing(String line){
		ArrayList<String> resultTokens = new ArrayList<String>();
		// [log status, iteration num]
		// index 0 : log status 
		/* 
		<log status> 
		READY = "Ready";
		MODELING = "Modeling";
		SOLVING = "Solving";
		DONE = "Done";
		ERROR = "Error";
		//*/
		// index 1 : iteration num  
		/*
		<iteration num>
		modeling... : 0
		end process... : -1
		num : 1 ~ maximum itr num 
		 */
		
		if(line.contains(this.LogStatus_modelingStart)){
			resultTokens.add(this.MODELING);
			resultTokens.add(this.MODELING_NUM); // 0
		}else if(line.contains(this.LogStatus_modelingEnd)){
			resultTokens.add(this.MODELING);
			resultTokens.add(this.MODELING_NUM); // 0
		}else if(line.contains(this.LogStatus_solvingStart)){
			String itrNum = myUtil.splitData(line.trim(), "=").get(1);
			resultTokens.add(this.SOLVING);
			resultTokens.add(itrNum);
		}else if(line.contains(this.LogStatus_solvingEnd)){
			String itrNum = myUtil.splitData(line.trim(), "=").get(1);
			resultTokens.add(this.SOLVING);
			resultTokens.add(itrNum);
		}else if(line.contains(this.LogStatus_error)){
			resultTokens.add(this.ERROR);
			resultTokens.add(this.ERROR_NUM); // -1
		}else if(line.contains(this.LogStatus_endAllProcess)){
			resultTokens.add(this.DONE);
			resultTokens.add(this.DONE_NUM); // -99
		}else{
			resultTokens.add(this.SKIP_LINE);
			resultTokens.add(this.SKIP_LINE_NUM); // -2
		}
		return resultTokens;
	}
}
