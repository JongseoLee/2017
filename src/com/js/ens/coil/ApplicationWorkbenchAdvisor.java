package com.js.ens.coil;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.js.ens.coil.core.Mediator;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "com.js.ens.coil.perspective";
	private Mediator med = Mediator.getInstance();

	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}
	
	@Override
	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

	@Override
	public boolean preShutdown() {
		// TODO Auto-generated method stub
		boolean result = MessageDialog.openConfirm(med.getParentView().getShell(), "Exit", "Do you want to quit?");
		if(result){
			return super.preShutdown();
		}else{
			return false;
		}
	}

}
