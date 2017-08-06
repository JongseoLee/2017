package com.js.ens.coil.customWidget;

import org.eclipse.swt.widgets.Button;

import com.js.ens.coil.core.MainController;
import com.js.ens.coil.core.Mediator;

public class CustomButton implements ICommand {

	private Mediator med;
	private MainController MC;
	private String widgetName;
	private Button button;
	
	
	public CustomButton(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstatnce();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		/*
		if(this.widgetName.equals()){
			// TODO call MC method
		}
		// */
	}
	
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	//
	//
	/*
	public void setCustomWidget_btnPrevious(){
		this.button = med.getBtnPrevious();
	}
	//*/
	
}
