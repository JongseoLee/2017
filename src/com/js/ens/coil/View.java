package com.js.ens.coil;

import java.util.*;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.js.ens.coil.core.MainController;
import com.js.ens.coil.core.Mediator;
import com.js.ens.coil.core.UILabel;
import com.js.ens.coil.customWidget.CustomButton;
import com.js.ens.coil.customWidget.CustomLabel;
import com.js.ens.coil.handler.HandlerButton;
import com.js.ens.coil.handler.HandlerLabel;
import com.js.util.myUtil;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

public class View extends ViewPart {
	public static final String ID = "com.js.ens.coil.view";
	
	private MainController MC = MainController.getInstatnce();
	private UILabel LabelDatas = UILabel.getInstance();
	private Mediator med = Mediator.getInstance();
	
	
	
	
	
	public View() {
		
	}
	

	

	@Override
	public void createPartControl(final Composite parent) {
		Composite parentView = parent;
		med.setParentView(parentView);
		parentView.setLayout(new FormLayout());
		
		//////////// TOP Area ///////////////
		Composite compositeTop = new Composite(parent, SWT.NONE);
		med.setCompositeTop(compositeTop);
		compositeTop.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		compositeTop.setLayout(new FormLayout());
		FormData fd_compositeTop = new FormData();
		fd_compositeTop.top = new FormAttachment(0);
		fd_compositeTop.left = new FormAttachment(0);
		fd_compositeTop.right = new FormAttachment(100,0);
		fd_compositeTop.bottom = new FormAttachment(0,45);
		compositeTop.setLayoutData(fd_compositeTop);
		
		Label lblProcessStep = new Label(compositeTop, SWT.NONE);
		med.setLblProcessStep(lblProcessStep);
		CustomLabel c_lblProcessStep = new CustomLabel(Mediator.LABEL_lblProcessStep, med);
		med.setC_lblProcessStep(c_lblProcessStep);
		c_lblProcessStep.setCustomeWidget_lblProcessStep();
		FormData fd_lblProcessStep = new FormData();
		fd_lblProcessStep.top = new FormAttachment(0, 15);
		fd_lblProcessStep.left = new FormAttachment(0, 10);
		lblProcessStep.setLayoutData(fd_lblProcessStep);
		lblProcessStep.setText(LabelDatas.getLabel(UILabel.ProcessStep));
		
		Label lblModeling = new Label(compositeTop, SWT.NONE);
		med.setLblModeling(lblModeling);
		CustomLabel c_lblModeling = new CustomLabel(Mediator.LABEL_lblModeling, med);
		med.setC_lblModeling(c_lblModeling);
		c_lblModeling.setCustomWidget_lblModeling();
		FormData fd_lblModeling = new FormData();
		fd_lblModeling.top = new FormAttachment(lblProcessStep, 0, SWT.TOP);
		fd_lblModeling.left = new FormAttachment(lblProcessStep, 60);
		fd_lblModeling.right = new FormAttachment(lblProcessStep,130, SWT.RIGHT);
		lblModeling.setLayoutData(fd_lblModeling);
		lblModeling.setText(LabelDatas.getLabel(UILabel.Modeling));
		
		Label lblNext1 = new Label(compositeTop, SWT.NONE);
		FormData fd_lblNext1 = new FormData();
		fd_lblNext1.top = new FormAttachment(lblProcessStep, 0, SWT.TOP);
		fd_lblNext1.left = new FormAttachment(lblModeling, 0);
		lblNext1.setLayoutData(fd_lblNext1);
		lblNext1.setText(">");
		
		Label lblSimulationAndExportResult = new Label(compositeTop, SWT.NONE);
		med.setLblSimulationAndExportResult(lblSimulationAndExportResult);
		CustomLabel c_lblSimulationAndExportResult = new CustomLabel(Mediator.LABEL_lblSimulationAndExportResult, med);
		med.setC_lblSimulationAndExportResult(c_lblSimulationAndExportResult);
		c_lblSimulationAndExportResult.setCustomWidget_lblSimulationAndExportResult();
		FormData fd_lblSimulationAndExportResult = new FormData();
		fd_lblSimulationAndExportResult.top = new FormAttachment(lblProcessStep, 0, SWT.TOP);
		fd_lblSimulationAndExportResult.left = new FormAttachment(lblNext1, 20);
		fd_lblSimulationAndExportResult.right = new FormAttachment(lblNext1, 200, SWT.RIGHT);
		lblSimulationAndExportResult.setLayoutData(fd_lblSimulationAndExportResult);
		lblSimulationAndExportResult.setText(LabelDatas.getLabel(UILabel.SimulationAndExportResult));
		
		Label lblNext2 = new Label(compositeTop, SWT.NONE);
		FormData fd_lblNext2 = new FormData();
		fd_lblNext2.top = new FormAttachment(lblProcessStep, 0, SWT.TOP);
		fd_lblNext2.left = new FormAttachment(lblSimulationAndExportResult, 0);
		lblNext2.setLayoutData(fd_lblNext2);
		lblNext2.setText(">");
		
		Label lblShowResult = new Label(compositeTop, SWT.NONE);
		med.setLblShowResult(lblShowResult);
		CustomLabel c_lblShowResult = new CustomLabel(Mediator.LABEL_lblShowResult, med);
		med.setC_lblShowResult(c_lblShowResult);
		c_lblShowResult.setCustomWidget_lblShowResult();
		FormData fd_lblShowResult = new FormData();
		fd_lblShowResult.top = new FormAttachment(lblProcessStep, 0, SWT.TOP);
		fd_lblShowResult.left = new FormAttachment(lblNext2, 20);
		fd_lblShowResult.right = new FormAttachment(lblNext2, 100, SWT.RIGHT);
		lblShowResult.setLayoutData(fd_lblShowResult);
		lblShowResult.setText(LabelDatas.getLabel(UILabel.ShowResult));
		
		Button btnStepSave = new Button(compositeTop, SWT.NONE);
		med.setBtnStepSave(btnStepSave);
		CustomButton c_btnStepSave = new CustomButton(Mediator.BUTTON_btnStepSave, med);
		med.setC_btnStepSave(c_btnStepSave);
		c_btnStepSave.setCustomWidget_btnStepSave();
		FormData fd_btnStepSave = new FormData();
		fd_btnStepSave.top = new FormAttachment(lblProcessStep,-5,SWT.TOP);
		fd_btnStepSave.left = new FormAttachment(lblShowResult, 60,SWT.RIGHT);
		btnStepSave.setLayoutData(fd_btnStepSave);
		btnStepSave.setText(LabelDatas.getLabel(UILabel.StepSave));
		
		
		
		this.init_WidgetSetting();
		this.init_WidgetEvent();
		
	}
	private void init_WidgetSetting(){
		
	}
	
	private void init_WidgetEvent(){
		// Button 
		HandlerButton handlerButton = new HandlerButton();
		med.getBtnStepSave().addListener(SWT.Selection, handlerButton);
		
		// Label 
		HandlerLabel handlerLabel = new HandlerLabel();
		med.getLblProcessStep().addListener(SWT.MouseDoubleClick, handlerLabel);
		med.getLblModeling().addListener(SWT.MouseDoubleClick, handlerLabel);
		med.getLblSimulationAndExportResult().addListener(SWT.MouseDoubleClick, handlerLabel);
		med.getLblShowResult().addListener(SWT.MouseDoubleClick, handlerLabel);
	}

	@Override
	public void setFocus() {
		
	}
}