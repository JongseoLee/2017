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
		}
		
		else if(this.widgetName.equals(Mediator.BUTTON_btnShowRadiusGraph)){
			System.out.println("Click btn : "+ Mediator.BUTTON_btnShowRadiusGraph);
			MC.Button_ShowRadiusGraph();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnShowPitchGraph)){
			System.out.println("Click btn : "+Mediator.BUTTON_btnShowPitchGraph);
			MC.Button_ShowPitchGraph();
		}
		
		else if(this.widgetName.equals(Mediator.BUTTON_btnStandard)){
			System.out.println("Click btn : "+Mediator.BUTTON_btnStandard);
			MC.Button_Standard();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnRrCoil)){
			System.out.println("Click btn : "+Mediator.BUTTON_btnRrCoil);
			MC.Button_RrCoil();
		}
		
		/*
		else if(this.widgetName.equals(Mediator.BUTTON_btnInitialConditionerConstant)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnInitialConditionerConstant);
			MC.Button_InitialConditionerConstant();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnInitialConditionerFile)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnInitialConditionerFile);
			MC.Button_InitialConditionerFile();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnInitialConditionerExplorer)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnInitialConditionerExplorer);
			MC.Button_InitialConditioner_FileExplorer();
		}
		// */
		else if(this.widgetName.equals(Mediator.BUTTON_btnRadiusConditionerConstant)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnRadiusConditionerConstant);
			MC.Button_RadiusConditionerConstant();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnRadiusConditionerFile)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnRadiusConditionerFile);
			MC.Button_RadiusConditionerFile();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnRadiusConditionerExplorer)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnRadiusConditionerExplorer);
			MC.Button_RadiusConditioner_FileExplorer();
		}
		else if(this.widgetName.equals(Mediator.BUTTON_btnHeightConditionerConstant)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnHeightConditionerConstant);
			MC.Button_HeightConditionerConstant();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnHeightConditionerFile)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnHeightConditionerFile);
			MC.Button_HeightConditionerFile();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnHeightConditionerExplorer)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnHeightConditionerExplorer);
			MC.Button_HeightConditioner_FileExplorer();
		}
		
		else if(this.widgetName.equals(Mediator.BUTTON_btnMaterialDBExplorer)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnMaterialDBExplorer);
			MC.Button_MaterialDB_FileExplorer();
		}
		
		else if(this.widgetName.equals(Mediator.BUTTON_btnStartSimulation)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnStartSimulation);
			MC.Button_StartSimulation();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnResetSimulation)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnResetSimulation);
			MC.Button_ResetSimulation();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnReadLog)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnReadLog);
			MC.Button_ReadLog();
		}
		
		else if(this.widgetName.equals(Mediator.BUTTON_btnShowGraphWindow)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnShowGraphWindow);
			MC.Button_ShowGraphWindow();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnRadius)){
			System.out.println("Click btn : "+Mediator.BUTTON_btnRadius);
			MC.Button_Radius();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnHeight)){
			System.out.println("Click btn : "+Mediator.BUTTON_btnHeight);
			MC.Button_Height();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnAddGraph)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnAddGraph);
			MC.Button_AddGraph();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnDeleteGraph)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnDeleteGraph);
			MC.Button_DeleteGraph();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnShowImageWindow)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnShowImageWindow);
			MC.Button_ShowImageWindow();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnShowTableData)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnShowTableData);
			MC.Button_ShowTableData();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnConditioner)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnConditioner);
			MC.Button_Conditioner();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnError)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnError);
			MC.Button_Error();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnFormSetError)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnFormSetError);
			MC.Button_FormSetError();
		}else if(this.widgetName.equals(Mediator.BUTTON_btnMaximumError)){
			System.out.println("Click btn : " + Mediator.BUTTON_btnMaximumError);
			MC.Button_MaximumError();
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
	
	/*
	public void setCustomWidget_btnInitialConditionerConstant(){
		this.button = med.getBtnInitialConditionerConstant();
	}
	
	public void setCustomWidget_btnInitialConditionerFile(){
		this.button = med.getBtnInitialConditionerFile();
	}
	
	public void setCustomWidget_btnInitialConditionerExplorer(){
		this.button = med.getBtnInitialConditionerExplorer();
	}
	// */
	public void setCustomWidget_btnRadiusConditionerConstant(){
		this.button = med.getBtnRadiusConditionerConstant();
	}
	
	public void setCustomWidget_btnRadiusConditionerFile(){
		this.button = med.getBtnRadiusConditionerFile();
	}
	
	public void setCustomWidget_btnShowRadiusGraph(){
		this.button = med.getBtnShowRadiusGraph();
	}
	
	public void setCustomWidget_btnShowPitchGraph(){
		this.button = med.getBtnShowPitchGraph();
	}
	
	public void setCustomWidget_btnStandard(){
		this.button = med.getBtnStandard();
	}
	
	public void setCustomWidget_btnRrCoil(){
		this.button = med.getBtnRrCoil();
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
	
	public void setCustomWidget_btnMaterialDBExplorer(){
		this.button = med.getBtnMaterialDBExplorer();
	}
	
	public void setCustomWidget_btnStartSimulation(){
		this.button = med.getBtnStartSimulation();
	}
	
	public void setCustomWidget_btnResetSimulation(){
		this.button = med.getBtnResetSimulation();
	}
	
	public void setCustomWidget_btnReadLog(){
		this.button = med.getBtnReadLog();
	}
	
	public void SetCustomWidget_btnRadius(){
		this.button = med.getBtnRadius();
	}
	
	public void SetCustomWidget_btnHeihght(){
		this.button = med.getBtnHeight();
	}
	
	public void setCustomWidget_btnShowGraphWindow(){
		this.button = med.getBtnShowGraphWindow();
	}
	
	public void setCustomWidget_btnAddGraph(){
		this.button = med.getBtnAddGraph();
	}
	
	public void setCustomWidget_btnDeleteGraph(){
		this.button = med.getBtnDeleteGraph();
	}
	
	public void setCustomWidget_btnShowImageWindow(){
		this.button = med.getBtnShowImageWindow();
	}
	
	public void setCustomWidget_btnShowTableData(){
		this.button = med.getBtnShowTableData();
	}
	
	public void setCustomWidget_btnConditioner(){
		this.button = med.getBtnConditioner();
	}
	
	public void setCustomWidget_btnError(){
		this.button = med.getBtnError();
	}
	
	public void setCustomWidget_btnFormSetError(){
		this.button = med.getBtnFormSetError();
	}
	
	public void setCustomWidget_btnMaximumError(){
		this.button = med.getBtnMaximumError();
	}
	
}
