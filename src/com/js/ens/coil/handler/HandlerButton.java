package com.js.ens.coil.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.js.ens.coil.core.Mediator;

public class HandlerButton implements Listener {
	private Mediator med = Mediator.getInstance();
	
	
	public HandlerButton() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getBtnStepSave()){
			med.getC_btnStepSave().execute();
		}else if(event.widget == med.getBtnExplorer()){
			med.getC_btnExplorer().execute();
		}else if(event.widget == med.getBtnInitialConditionerConstant()){
			med.getC_btnInitialConditionerConstant().execute();
		}else if(event.widget == med.getBtnInitialConditionerFile()){
			med.getC_btnInitialConditionerFile().execute();
		}else if(event.widget == med.getBtnInitialConditionerExplorer()){
			med.getC_btnInitialConditionerExplorer().execute();
		}else if(event.widget == med.getBtnMaterialDBExplorer()){
			med.getC_btnMaterialDBExplorer().execute();
		}else if(event.widget == med.getBtnStartSimulation()){
			med.getC_btnStartSimulation().execute();
		}else if(event.widget == med.getBtnReadLog()){
			med.getC_btnReadLog().execute();
		}else if(event.widget == med.getBtnShowGraphWindow()){
			med.getC_btnShowGraphWindow().execute();
		}else if(event.widget == med.getBtnShowImageWindow()){
			med.getC_btnShowImageWindow().execute();
		}else if(event.widget == med.getBtnShowTableData()){
			med.getC_btnShowTableData().execute();
		}
	}

}
