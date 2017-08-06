package com.js.ens.coil.customWidget;

import org.eclipse.swt.widgets.Label;

import com.js.ens.coil.core.MainController;
import com.js.ens.coil.core.Mediator;

public class CustomLabel implements ICommand {
	
	private Mediator med;
	private MainController MC;
	private String widgetName;
	private Label label;
	
	
	public CustomLabel(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstatnce();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(this.widgetName.equals(Mediator.LABEL_lblProcessStep)){
			// Call MC method
			System.out.println("Double Click : "+ Mediator.LABEL_lblProcessStep);
		}else if(this.widgetName.equals(Mediator.LABEL_lblModeling)){
			// Call MC method
			System.out.println("Double Click : "+ Mediator.LABEL_lblModeling);
		}else if(this.widgetName.equals(Mediator.LABEL_lblSimulationAndExportResult)){
			// Call MC method
			System.out.println("Double Click : "+ Mediator.LABEL_lblSimulationAndExportResult);
		}else if(this.widgetName.equals(Mediator.LABEL_lblShowResult)){
			// Call MC method
			System.out.println("Double Click : "+Mediator.LABEL_lblShowResult);
		}
	}
	
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	//
	//
	public void setCustomeWidget_lblProcessStep(){
		this.label = med.getLblProcessStep();
	}
	
	public void setCustomWidget_lblModeling(){
		this.label = med.getLblModeling();
	}
	
	public void setCustomWidget_lblSimulationAndExportResult(){
		this.label = med.getLblSimulationAndExportResult();
	}
	
	public void setCustomWidget_lblShowResult(){
		this.label = med.getLblShowResult();
	}
}
