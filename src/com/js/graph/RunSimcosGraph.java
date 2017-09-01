package com.js.graph;

import java.io.IOException;

import com.js.ens.coil.core.AppFolder;
import com.js.ens.coil.core.MainController;
import com.js.ens.coil.db.CoilDB;
import com.js.util.myUtil;

public class RunSimcosGraph implements Runnable {
	private MainController MC = MainController.getInstatnce();
	private String plotPath;
	private CoilDB coilDBObj;
	public RunSimcosGraph() {
		// TODO Auto-generated constructor stub
	}

	public void running(CoilDB coilDBObj,String path){
		this.coilDBObj = coilDBObj;
		this.plotPath = path;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String exePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getAppPath(),AppFolder.CONFIG),AppFolder.GRAPH_LIB),AppFolder.SimcosGraphExeFile);
		
		Process p = null;
		String runScript = exePath + " " +this.plotPath;
		//System.out.println("START : "+runScript);
		try {
			p = Runtime.getRuntime().exec(runScript);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("END : "+runScript);
	}

}
