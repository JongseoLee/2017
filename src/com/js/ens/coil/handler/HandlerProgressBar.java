package com.js.ens.coil.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.js.ens.coil.core.Mediator;

public class HandlerProgressBar implements Listener {
	private Mediator med = Mediator.getInstance();
	
	public HandlerProgressBar() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getProgressBarSimulationIteration()){
			med.getC_progressBarSimulationIteration().execute();
		}
	}

}
