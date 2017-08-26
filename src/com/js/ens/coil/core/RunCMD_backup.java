package com.js.ens.coil.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.js.ens.coil.db.CoilDB;
import com.js.ens.coil.dialog.MessageDlg;
import com.js.io.Writer;
import com.js.util.myUtil;

public class RunCMD_backup implements Runnable{
	private MainController MC = MainController.getInstatnce();
	private Mediator med = Mediator.getInstance();
	private CoilDB CObj;
	private String cmd;
	private List<String> cmdList;
	private String simcosDataPath;
	private String batPath;
	private String preCMD;
	private String runProc;
	
	public RunCMD_backup() {
		// TODO Auto-generated constructor stub
	}

	
	//public void running(CoilDB coilDBObj,String cmd){
	//public void running(CoilDB coilDBObj,List<String> cmdList){
	public void running(CoilDB coilDBObj){
		this.CObj = coilDBObj;
		this.cmd = cmd;
		this.cmdList = cmdList;
		this.simcosDataPath = myUtil.setPath(this.CObj.getProjectFolderPath(), AppFolder.SIMCOS_DATA);
		this.batPath = myUtil.setPath(this.simcosDataPath, "run.bat");
		this.preCMD = "rundll32 url.dll,FileProtocolHandler ";
		this.runProc = this.preCMD + myUtil.setPath(this.simcosDataPath, AppFolder.mainProcFileName);
		ArrayList<String> outputDataList = new ArrayList<String>();
		outputDataList.add("cd "+this.simcosDataPath);
		outputDataList.add(this.simcosDataPath.charAt(0)+":");
		outputDataList.add(this.runProc);
		Writer writer = new Writer(this.batPath);
		writer.running(outputDataList);
		System.out.println(" CMD ==>");
		myUtil.printArrData(outputDataList);
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("START - CMD");
		//Process p = null;
		//ProcessBuilder p = new ProcessBuilder(this.cmdList);
		Process p = null;
		try{
			/* */
			// mentat 이 실행되는 위치를 현재 프로젝트 폴더로 변경 해줘야 함
			
			/*
			System.out.println("CMD : "+ this.cmdList);
			p.directory(new File(simcosDataPath));
			p.start();
			//*/
			/*
			p = Runtime.getRuntime().exec(this.cmd);
			p.getErrorStream().close();
			p.getInputStream().close();
			p.getOutputStream().close();
			p.waitFor();
			//*/
			p = Runtime.getRuntime().exec(batPath);
			/* 
			med.getParentView().getDisplay().asyncExec(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					String msg = "CMD : "+cmdList;
					MessageDlg msgDlg = new MessageDlg(med.getCompositeTop().getShell(),msg);
					msgDlg.open();
				}
				
			});
			//*/
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			System.out.println("END - CMD");
		}		
	}
	
}
