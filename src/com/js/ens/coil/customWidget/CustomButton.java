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
			System.out.println("Click btn : " + Mediator.BUTTON_btnStepSave);
			MC.Button_StepSave();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnExplorer)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnExplorer);
			MC.Button_FileExplorer_CoilData();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnRadiusConditionerConstant)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnRadiusConditionerConstant);
			MC.Button_RadiusConditionerConstant();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnRadiusConditionerFile)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnRadiusConditionerFile);
			MC.Button_RadiusConditionerFile();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnRadiusConditionerExplorer)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnRadiusConditionerExplorer);
			MC.Button_RadiusConditioner_FileExplorer();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnHeightConditionerConstant)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnHeightConditionerConstant);
			MC.Button_HeightConditionerConstant();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnHeightConditionerFile)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnHeightConditionerFile);
			MC.Button_HeightConditonerFile();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnHeightConditionerExplorer)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnHeightConditionerExplorer);
			MC.Button_HeightConditioner_FileExplorer();
		}
		
		else if(this.widgetName.equals(Mediator.BUTTON_btnStartSimulation)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnStartSimulation);
			MC.Button_StartSimulation();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnReadLog)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnReadLog);
			MC.Button_ReadLog();
		}
		
		else if(this.widgetName.equals(Mediator.BUTTON_btnShowGraphWindow)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnShowGraphWindow);
			MC.Button_ShowGraphWindow();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnShowImageWindow)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnShowImageWindow);
			MC.Button_ShowImageWindow();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnShowTableData)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnShowTableData);
			MC.Button_ShowTableData();
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
	
	public void setCustomWidget_btnStartSimulation(){
		this.button = med.getBtnStartSimulation();
	}
	
	public void setCustomWidget_btnReadLog(){
		this.button = med.getBtnReadLog();
	}
	
	public void setCustomWidget_btnShowGraphWindow(){
		this.button = med.getBtnShowGraphWindow();
	}
	
	public void setCustomWidget_btnShowImageWindow(){
		this.button = med.getBtnShowImageWindow();
	}
	
	public void setCustomWidget_btnShowTableData(){
		this.button = med.getBtnShowTableData();
	}
}
