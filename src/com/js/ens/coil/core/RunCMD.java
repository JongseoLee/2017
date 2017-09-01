package com.js.ens.coil.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.js.ens.coil.db.CoilDB;
import com.js.ens.coil.dialog.MessageDlg;
import com.js.io.Writer;
import com.js.util.myUtil;

public class RunCMD implements Runnable{
	private MainController MC = MainController.getInstatnce();
	private Mediator med = Mediator.getInstance();
	private CoilDB CObj;
	private String simcosDataPath;
	private String batPath;
	private String preCMD;
	private String runProc;
	private ArrayList<String> outputDataList;
	
	public RunCMD() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(CoilDB coilDBObj){
		this.CObj = coilDBObj;
		this.outputDataList = new ArrayList<String>();
		this.simcosDataPath = myUtil.setPath(this.CObj.getProjectFolderPath(), AppFolder.SIMCOS_DATA);
		this.batPath = myUtil.setPath(this.simcosDataPath, "runSimcos.bat");
		this.preCMD = "rundll32 url.dll,FileProtocolHandler ";
		this.runProc = this.preCMD + myUtil.setPath(this.simcosDataPath, AppFolder.mainProcFileName);
		/*
		this.outputDataList = new ArrayList<String>();
		this.outputDataList.add("cd "+this.simcosDataPath);
		this.outputDataList.add(this.simcosDataPath.charAt(0)+":");
		this.outputDataList.add(this.runProc);
		//*/
		this.outputDataList = new ArrayList<String>();
		this.outputDataList.add("cd "+this.simcosDataPath);
		this.outputDataList.add(this.simcosDataPath.charAt(0)+":");
		
		String cmd = MC.getCommand(); 
		String ch_cmd1 = cmd.replace("{MentatPath}", MC.getMentatPath());
		String ch_cmd2 = ch_cmd1.replace("{SimcosDataPath}", this.simcosDataPath);
		this.outputDataList.add(ch_cmd2);
		System.out.println("\n\n\n\nCMCMCMCMCMCMDDDDDD : "+ch_cmd2+"\n\n\n");
		
		Writer writer = new Writer(this.batPath);
		writer.running(outputDataList);
		
		myUtil.CleareObj(outputDataList);
		myUtil.CleareObj(writer);
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
		Process p = null;
		try{
			/* */
			// mentat 이 실행되는 위치를 현재 프로젝트 폴더로 변경 해줘야 함
			p = Runtime.getRuntime().exec(batPath);
			//p.getErrorStream().close();
			//p.getInputStream().close();
			//p.getOutputStream().close();
			//p.waitFor();
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
