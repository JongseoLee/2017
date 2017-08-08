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
		}else if(this.widgetName.equals(Mediator.BUTTON_btnRadiusConditionerConstant)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnRadiusConditionerConstant);
		}else if(this.widgetName.equals(Mediator.BUTTON_btnRadiusConditionerFile)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnRadiusConditionerFile);
		}else if(this.widgetName.equals(Mediator.BUTTON_btnRadiusConditionerExplorer)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnRadiusConditionerExplorer);
		}else if(this.widgetName.equals(Mediator.BUTTON_btnHeightConditionerConstant)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnHeightConditionerConstant);
		}else if(this.widgetName.equals(Mediator.BUTTON_btnHeightConditionerFile)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnHeightConditionerFile);
		}else if(this.widgetName.equals(Mediator.BUTTON_btnHeightConditionerExplorer)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnHeightConditionerExplorer);
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
	
	public void setCustomWidget_btnRadiusConditionerConstant(){
		this.button = med.getBtnRadiusConditionerConstant();
	}
	
	public void setCustomWidget_btnRadiusConditionerFile(){
		this.button = med.getBtnRadiusConditionerFile();
	}
	
	public void setCustomWidget_btnRadiusConditionerExplorer(){
		this.button = med.getBtnRadiusConditionerExplorer();
	}
	
	public void setCustomWidget_btnHeightConditionerConstant(){
		this.button = med.getBtnHeightConditionerConstant();
	}
	
	public void setCustomWidget_btnHeightConditionerFile(){
		this.button = med.getBtnHeightConditionerFile();
	}
	
	public void setCustomWidget_btnHeightConditionerExplorer(){
		this.button = med.getBtnHeightConditionerExplorer();
	}
}
