package com.js.ens.coil.core;

import com.js.ens.coil.db.CoilDB;
import com.js.ens.coil.dialog.MessageDlg;

public class RunCMD implements Runnable{
	private MainController MC = MainController.getInstatnce();
	private Mediator med = Mediator.getInstance();
	private CoilDB CObj;
	private String cmd; 
	
	public RunCMD() {
		// TODO Auto-generated constructor stub
	}

	public void running(CoilDB coilDBObj,String cmd){
		this.CObj = coilDBObj;
		this.cmd = cmd;
		
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("START - CMD");
		Process p = null;
		try{
			/*
			System.out.println("CMD : "+ this.cmd);
			p = Runtime.getRuntime().exec(this.cmd);
			p.getErrorStream().close();
			p.getInputStream().close();
			p.getOutputStream().close();
			p.waitFor();
			//*/
			med.getParentView().getDisplay().asyncExec(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					String msg = "CMD : "+cmd;
					MessageDlg msgDlg = new MessageDlg(med.getCompositeTop().getShell(),msg);
					msgDlg.open();
				}
				
			});
		}catch(Exception e){
			
		}finally{
			System.out.println("END - CMD");
		}
		
	}
	
}
