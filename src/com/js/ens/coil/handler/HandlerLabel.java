package com.js.ens.coil.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.js.ens.coil.core.Mediator;

public class HandlerLabel implements Listener {
	private Mediator med = Mediator.getInstance();
	
	public HandlerLabel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getLblProcessStep()){
			med.getC_lblProcessStep().execute();
		}else if(event.widget == med.getLblModeling()){
			med.getC_lblModeling().execute();
		}else if(event.widget == med.getLblSimulationAndExportResult()){
			med.getC_lblSimulationAndExportResult().execute();
		}else if(event.widget == med.getLblShowResult()){
			med.getC_lblShowResult().execute();
		}
	}

}
