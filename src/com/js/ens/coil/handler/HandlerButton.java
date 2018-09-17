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
		}
		
		else if(event.widget == med.getBtnShowRadiusGraph()){
			med.getC_btnShowRadiusGraph().execute();
		}else if(event.widget == med.getBtnShowPitchGraph()){
			med.getC_btnShowPitchGraph().execute();
		}
		
		else if(event.widget == med.getBtnStandard()){
			med.getC_btnStandard().execute();
		}else if(event.widget == med.getBtnRrCoil()){
			med.getC_btnRrCoil().execute();
		}
		/*
		else if(event.widget == med.getBtnInitialConditionerConstant()){
			med.getC_btnInitialConditionerConstant().execute();
		}else if(event.widget == med.getBtnInitialConditionerFile()){
			med.getC_btnInitialConditionerFile().execute();
		}else if(event.widget == med.getBtnInitialConditionerExplorer()){
			med.getC_btnInitialConditionerExplorer().execute();
		}
		// */
		else if(event.widget == med.getBtnRadiusConditionerConstant()){
			med.getC_btnRadiusConditionerConstant().execute();
		}else if(event.widget == med.getBtnRadiusConditionerFile()){
			med.getC_btnRadiusConditionerFile().execute();
		}else if(event.widget == med.getBtnRadiusConditionerExplorer()){
			med.getC_btnRadiusConditionerExplorer().execute();
		}
		else if(event.widget == med.getBtnHeightConditionerConstant()){
			med.getC_btnHeightConditionerConstant().execute();
		}else if(event.widget == med.getBtnHeightConditionerFile()){
			med.getC_btnHeightConditionerFile().execute();
		}else if(event.widget == med.getBtnHeightConditionerExplorer()){
			med.getC_btnHeightConditionerExplorer().execute();
		}
		else if(event.widget == med.getBtnMaterialDBExplorer()){
			med.getC_btnMaterialDBExplorer().execute();
		}
		else if(event.widget == med.getBtnFormedCoilDataInterp()){
			med.getC_btnFormedCoilDataInterp().execute();
		}
		
		else if(event.widget == med.getBtnStartSimulation()){
			med.getC_btnStartSimulation().execute();
		}else if(event.widget == med.getBtnResetSimulation()){
			med.getC_btnResetSimulation().execute();
		}else if(event.widget == med.getBtnReadLog()){
			med.getC_btnReadLog().execute();
		}
		
		else if(event.widget == med.getBtnRadius()){
			med.getC_btnRadius().execute();
		}else if(event.widget == med.getBtnHeight()){
			med.getC_btnHeight().execute();
		}else if(event.widget == med.getBtnShowGraphWindow()){
			med.getC_btnShowGraphWindow().execute();
		}else if(event.widget == med.getBtnAddGraph()){
			med.getC_btnAddGraph().execute();
		}else if(event.widget == med.getBtnDeleteGraph()){
			med.getC_btnDeleteGraph().execute();
		}else if(event.widget == med.getBtnShowImageWindow()){
			med.getC_btnShowImageWindow().execute();
		}else if(event.widget == med.getBtnShowTableData()){
			med.getC_btnShowTableData().execute();
		}else if(event.widget == med.getBtnConditioner()){
			med.getC_btnConditioner().execute();
		}else if(event.widget == med.getBtnError()){
			med.getC_btnError().execute();
		}else if(event.widget == med.getBtnFormSetError()){
			med.getC_btnFormSetError().execute();
		}else if(event.widget == med.getBtnPitch_IR()){
			med.getC_btnPitch_IR().execute();
		}else if(event.widget == med.getBtnRadius_IR()){
			med.getC_btnRadius_IR().execute();
		}else if(event.widget == med.getBtnMaximumError()){
			med.getC_btnMaximumError().execute();
		}else if(event.widget == med.getBtnFormDataTotal()){
			med.getC_btnFormDataTotal().execute();
		}
		/*
		// 2018.03_update
		else if(event.widget == med.getBtnEtc()){
			med.getC_btnEtc().execute();
		}else if(event.widget == med.getBtnEtc2()){
			med.getC_btnEtc2().execute();
		}
		//*/
	}
	

}
