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
	private String callBatPath;
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
		this.callBatPath = myUtil.setPath(this.simcosDataPath, "callRunSimcos.bat");
		this.preCMD = "rundll32 url.dll,FileProtocolHandler ";
		this.runProc = this.preCMD + myUtil.setPath(this.simcosDataPath, AppFolder.mainProcFileName);
		
		this.outputDataList = new ArrayList<String>();
		// Type1 RunScript : execute main_dwku.proc icon 
		/* /
		this.outputDataList.add("cd "+this.simcosDataPath);
		this.outputDataList.add(this.simcosDataPath.charAt(0)+":");
		this.outputDataList.add(this.runProc);
		//*/
		
		
		
		
		// Type2 RunScript : Run using mentat Path
		/* */
		String cmd = MC.getCommandSolving(); 
		String ch_cmd1 = cmd.replace("{MentatPath}", MC.getMentatPath());
		String ch_cmd2 = ch_cmd1.replace("{SimcosDataPath}", this.simcosDataPath);
		this.outputDataList.add("call "+ch_cmd2);
		
		String cmd_post = MC.getCommandPost();
		String ch_cmd_post1 = cmd_post.replace("{MentatPath}", MC.getMentatPath());
		String ch_cmd_post2 = ch_cmd_post1.replace("{SimcosDataPath}", this.simcosDataPath);
		this.outputDataList.add("call "+ch_cmd_post2);
		//System.out.println("\n\n\n\nCMCMCMCMCMCMDDDDDD : "+ch_cmd2+"\n\n\n");
		//*/
		
		// Type3 : Type2 + Type1 Style
		ArrayList<String> call_outputDataList = new ArrayList<String>();
		call_outputDataList.add("cd "+this.simcosDataPath);
		//call_outputDataList.add(this.simcosDataPath.charAt(0)+":");
		String callCMD = this.preCMD + this.batPath;
		call_outputDataList.add(callCMD);
		Writer w = new Writer(this.callBatPath);
		w.running(call_outputDataList);
		myUtil.CleareObj(call_outputDataList);
		myUtil.CleareObj(w);
		
		
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
			// Type1 , Type2 run 
			//p = Runtime.getRuntime().exec(batPath);
			// Type3 run
			p = Runtime.getRuntime().exec(callBatPath);
			
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
