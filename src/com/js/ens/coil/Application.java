package com.js.ens.coil;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.js.ens.coil.core.AppFolder;
import com.js.ens.coil.core.LicenseCheck;
import com.js.util.myUtil;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {
	private Logger log = Logger.getLogger(Application.class);
	@Override
	public Object start(IApplicationContext context) {
		Display display = PlatformUI.createDisplay();
		String AppPath = "";
		
		if(myUtil.checkOS().equals("window")){
			AppPath = System.getProperty("user.dir"); 
		}else{
			AppPath = "/Users/jslee/CodeFactory/Git/2017/2017";
		}
		String configFolder = myUtil.setPath(AppPath, AppFolder.CONFIG);
		String logFolder = myUtil.setPath(configFolder, AppFolder.LOG);
		String logPropertyFile = myUtil.setPath(configFolder, AppFolder.LogPropertyFile);
		System.setProperty("LogPath.ens",logFolder);
		PropertyConfigurator.configure(logPropertyFile);
		log.info("Start Simcos");
		
		/*
		LicenseCheck engine = new LicenseCheck();
		try {
			int returnCode = 0;
			if(engine.running()){
				//License OK
				log.info("License is OK..."+myUtil.getCurrentDate());
				returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			}else{
				//LIcense Error
				log.error("License is Error..."+myUtil.getCurrentDate());
				JOptionPane.showMessageDialog(null,"License Error" , "Simcos", JOptionPane.ERROR_MESSAGE);
				returnCode = IApplication.EXIT_OK;
			}
			
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}
			
			return IApplication.EXIT_OK;
		}catch(Exception e){
			log.error("License is Error..."+myUtil.getCurrentDate());
			JOptionPane.showMessageDialog(null, "License Error", "Leveller", JOptionPane.ERROR_MESSAGE);
			return IApplication.EXIT_OK;
		}finally {
			display.dispose();
		}
		// */
		/* */
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}
			return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
		//*/
	}

	@Override
	public void stop() {
		if (!PlatformUI.isWorkbenchRunning())
			return;
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}
}
