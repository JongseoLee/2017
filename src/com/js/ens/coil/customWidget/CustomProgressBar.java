package com.js.ens.coil.customWidget;

import org.eclipse.swt.widgets.ProgressBar;

import com.js.ens.coil.core.MainController;
import com.js.ens.coil.core.Mediator;

public class CustomProgressBar implements ICommand {

	private Mediator med;
	private MainController MC;
	private String widgetName;
	private ProgressBar progressBar;
	
	public CustomProgressBar(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstatnce();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(this.widgetName.equals(Mediator.PROGRESSBAR_progressBarSimulationIteration)){
			System.out.println("ProgressBar("+Mediator.PROGRESSBAR_progressBarSimulationIteration+")");
		}

	}
	
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	//
	//
	public void setCustomWidget_progressBarSimulationIteration(){
		this.progressBar = med.getProgressBarSimulationIteration();
	}

}
