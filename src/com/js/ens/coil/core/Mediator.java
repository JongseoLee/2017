package com.js.ens.coil.core;


import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.js.ens.coil.customWidget.ICommand;



public class Mediator {
	private static Mediator instance = new Mediator();
	public static Mediator getInstance(){
		return instance;
	}
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	// 
	// Common
	//
	private Composite parentView;
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	// 
	// Top
	//
	private Composite compositeTop;
	public static String COMPOSITE_compositeTop = "compositeTop"; 
	
	private Label lblProcessStep;
	public ICommand C_lblProcessStep;
	public static String LABEL_lblProcessStep = "lblProcessStep";
	
	private Label lblModeling;
	public ICommand C_lblModeling;
	public static String LABEL_lblModeling = "lblModeling";
	
	private Label lblSimulationAndExportResult;
	public ICommand C_lblSimulationAndExportResult;
	public static String LABEL_lblSimulationAndExportResult = "lblSimulationAndExportResult";
	
	private Label lblShowResult;
	public ICommand C_lblShowResult;
	public static String LABEL_lblShowResult = "lblShowResult";
	
	private Button btnStepSave;
	public ICommand C_btnStepSave;
	public static String BUTTON_btnStepSave = "btnStepSave";
	
		
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	// 
	// get set method
	//
	// Common
	public Composite getParentView() {
		return parentView;
	}

	public void setParentView(Composite parentView) {
		this.parentView = parentView;
	}
//	////////////////////////////////////////////////////////////////////////
//	////////////////////////////////////////////////////////////////////////
	// Top Area 
	public Composite getCompositeTop() {
		return compositeTop;
	}

	public void setCompositeTop(Composite compositeTop) {
		this.compositeTop = compositeTop;
	}

	public Label getLblProcessStep() {
		return lblProcessStep;
	}

	public void setLblProcessStep(Label lblProcessStep) {
		this.lblProcessStep = lblProcessStep;
	}

	public ICommand getC_lblProcessStep() {
		return C_lblProcessStep;
	}

	public void setC_lblProcessStep(ICommand c_lblProcessStep) {
		C_lblProcessStep = c_lblProcessStep;
	}

	public Label getLblModeling() {
		return lblModeling;
	}

	public void setLblModeling(Label lblModeling) {
		this.lblModeling = lblModeling;
	}

	public ICommand getC_lblModeling() {
		return C_lblModeling;
	}

	public void setC_lblModeling(ICommand c_lblModeling) {
		C_lblModeling = c_lblModeling;
	}

	public Label getLblSimulationAndExportResult() {
		return lblSimulationAndExportResult;
	}

	public void setLblSimulationAndExportResult(Label lblSimulationAndExportResult) {
		this.lblSimulationAndExportResult = lblSimulationAndExportResult;
	}

	public ICommand getC_lblSimulationAndExportResult() {
		return C_lblSimulationAndExportResult;
	}

	public void setC_lblSimulationAndExportResult(ICommand c_lblSimulationAndExportResult) {
		C_lblSimulationAndExportResult = c_lblSimulationAndExportResult;
	}

	public Label getLblShowResult() {
		return lblShowResult;
	}

	public void setLblShowResult(Label lblShowResult) {
		this.lblShowResult = lblShowResult;
	}

	public ICommand getC_lblShowResult() {
		return C_lblShowResult;
	}

	public void setC_lblShowResult(ICommand c_lblShowResult) {
		C_lblShowResult = c_lblShowResult;
	}

	public Button getBtnStepSave() {
		return btnStepSave;
	}

	public void setBtnStepSave(Button btnStepSave) {
		this.btnStepSave = btnStepSave;
	}

	public ICommand getC_btnStepSave() {
		return C_btnStepSave;
	}

	public void setC_btnStepSave(ICommand c_btnStepSave) {
		C_btnStepSave = c_btnStepSave;
	}

}
