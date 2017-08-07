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
		if(this.widgetName.equals(Mediator.BUTTON_btnStepSave)){
			// Call MC method
			System.out.println("Click btn : " + Mediator.BUTTON_btnStepSave);
		}else if(this.widgetName.equals(Mediator.BUTTON_btnExplorer)){
			// Call MC method
			System.out.println("Click btn : " + Mediator.BUTTON_btnExplorer);
			MC.Button_StepSave();
		}
	}
	
	////////////////////////////////////////////////
	////////////////////////////////////////////////
	//
	//
	
	public void setCustomWidget_btnStepSave(){
		this.button = med.getBtnStepSave();
	}
	
	public void setCustomWidget_btnExplorer(){
		this.button = med.getBtnExplorer();
	}
}
