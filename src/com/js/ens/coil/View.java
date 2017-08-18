package com.js.ens.coil;


import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.js.ens.coil.core.MainController;
import com.js.ens.coil.core.Mediator;
import com.js.ens.coil.core.UILabel;
import com.js.ens.coil.customWidget.CustomButton;
import com.js.ens.coil.customWidget.CustomComboViewer;
import com.js.ens.coil.customWidget.CustomLabel;
import com.js.ens.coil.customWidget.CustomProgressBar;
import com.js.ens.coil.customWidget.CustomTableViewer;
import com.js.ens.coil.customWidget.CustomText;
import com.js.ens.coil.customWidget.TableColumnLabel;
import com.js.ens.coil.handler.HandlerButton;
import com.js.ens.coil.handler.HandlerComboViewer;
import com.js.ens.coil.handler.HandlerLabel;
import com.js.ens.coil.handler.HandlerTableViewer;
import com.js.ens.coil.handler.HandlerText;
import com.js.util.myUtil;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;

public class View extends ViewPart {
	public static final String ID = "com.js.ens.coil.view";
	
	private MainController MC = MainController.getInstatnce();
	private UILabel LabelDatas = UILabel.getInstance();
	private Mediator med = Mediator.getInstance();
	
	private StackLayout stackLayout;
	private Composite compositeStep1;
	private Composite compositeStep2;
	private Composite compositeStep3;
	private Text textCoilFilePath;
	private Text textProductName;
	private Text textLineDiameter;
	private Text textCenterDiameter;
	private Text textInnerDiameter;
	private Text textOuterDiameter;
	private Text textUpperInnerDiameter;
	private Text textLowerInnerDiameter;
	private Text textTotalNumber;
	private Table tableCoilTable;
	private Text textHotSettingTemp;
	private Text textColdSettingTemp;
	private Text textHotSettingHeight;
	private Text textColdSettingHeight;
	private Text textSeatUInnerMargina;
	private Text textSeatLInnerMargina;
	private Text textSeatHeight;
	private Text textRadiusConditionerValue;
	private Text textRadiusConditionerPath;
	private Text textHeightConditionerValue;
	private Text textHeightConditionerPath;
	private Text textRadiusTolerance;
	private Text textHeightTolerance;
	private Text textMaximumIterationNumber;
	private Text textLogEditor;
	private Combo comboSelectGraph;
	private Combo comboSelectImage;
	private Combo comboSelectTableData;
	
	
	
	
	public View() {
		
	}
	

	

	@Override
	public void createPartControl(final Composite parent) {
		Composite parentView = parent;
		med.setParentView(parentView);
		parentView.setLayout(new FormLayout());
		
		//////////// TOP Area ///////////////
		//////////// TOP Area ///////////////
		//////////// TOP Area ///////////////
		//////////// TOP Area ///////////////
		//////////// TOP Area ///////////////
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
		if(myUtil.checkOS().equals("window")){
			lblProcessStep.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));	
		}else{
			lblProcessStep.setFont(SWTResourceManager.getFont(".SF NS Text", 12, SWT.BOLD));
		}
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
		fd_lblModeling.left = new FormAttachment(lblProcessStep, 120);
		fd_lblModeling.right = new FormAttachment(lblProcessStep,190, SWT.RIGHT);
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
		fd_btnStepSave.left = new FormAttachment(lblShowResult, 120,SWT.RIGHT);
		btnStepSave.setLayoutData(fd_btnStepSave);
		btnStepSave.setText(LabelDatas.getLabel(UILabel.StepSave));
		
		
		
		//////////// BOTTOM Area ///////////////
		//////////// BOTTOM Area ///////////////
		//////////// BOTTOM Area ///////////////
		//////////// BOTTOM Area ///////////////
		//////////// BOTTOM Area ///////////////
		//////////// BOTTOM Area ///////////////
		Composite compositeBottom = new Composite(parent, SWT.NONE);
		med.setCompositeBottom(compositeBottom);
		stackLayout = new StackLayout();
		med.setStackLayout(stackLayout);
		compositeBottom.setLayout(stackLayout);
		FormData fd_compositeBottom = new FormData();
		fd_compositeBottom.top = new FormAttachment(compositeTop, 0);
		fd_compositeBottom.left = new FormAttachment(compositeTop, 0, SWT.LEFT);
		fd_compositeBottom.right = new FormAttachment(compositeTop, 0, SWT.RIGHT);
		fd_compositeBottom.bottom = new FormAttachment(100,0);
		compositeBottom.setLayoutData(fd_compositeBottom);
		
		////////////=> Modeling
		////////////=> Modeling
		////////////=> Modeling
		compositeStep1 = new Composite(compositeBottom, SWT.NONE);
		med.setCompositeStep1(compositeStep1);
		compositeStep1.setLayout(new FormLayout());
		
		Group grpCoil = new Group(compositeStep1, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			grpCoil.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else{
			grpCoil.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		}
		FormLayout fl_grpCoil = new FormLayout();
		fl_grpCoil.marginTop = 5;
		fl_grpCoil.marginRight = 5;
		fl_grpCoil.marginLeft = 5;
		fl_grpCoil.marginBottom = 5;
		grpCoil.setLayout(fl_grpCoil);
		FormData fd_grpCoil = new FormData();
		fd_grpCoil.top = new FormAttachment(0, 10);
		fd_grpCoil.left = new FormAttachment(0, 10);
		fd_grpCoil.right = new FormAttachment(0, 400);
		fd_grpCoil.bottom = new FormAttachment(100,-10);
		grpCoil.setLayoutData(fd_grpCoil);
		grpCoil.setText(LabelDatas.getLabel(UILabel.Coil));
		
		Label lblSelectCoilData = new Label(grpCoil, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			lblSelectCoilData.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else{
			lblSelectCoilData.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		}
		FormData fd_lblSelectCoilData = new FormData();
		fd_lblSelectCoilData.top = new FormAttachment(0, 10);
		fd_lblSelectCoilData.left = new FormAttachment(0, 10);
		lblSelectCoilData.setLayoutData(fd_lblSelectCoilData);
		lblSelectCoilData.setText(LabelDatas.getLabel(UILabel.SelectCoilData));
		
		textCoilFilePath = new Text(grpCoil, SWT.BORDER);
		med.setTextCoilFilePath(textCoilFilePath);
		CustomText c_textCoilFilePath = new CustomText(Mediator.TEXT_textCoilFilePath,med);
		med.setC_textCoilFilePath(c_textCoilFilePath);
		c_textCoilFilePath.setCustomWidget_textCoilFilePath();
		FormData fd_textCoilFilePath = new FormData();
		fd_textCoilFilePath.top = new FormAttachment(lblSelectCoilData, 6);
		fd_textCoilFilePath.left = new FormAttachment(0, 10);
		fd_textCoilFilePath.right = new FormAttachment(100,-40);
		textCoilFilePath.setLayoutData(fd_textCoilFilePath);
		
		Button btnExplorer = new Button(grpCoil, SWT.NONE);
		med.setBtnExplorer(btnExplorer);
		CustomButton c_btnExplorer = new CustomButton(Mediator.BUTTON_btnExplorer,med);
		med.setC_btnExplorer(c_btnExplorer);
		c_btnExplorer.setCustomWidget_btnExplorer();
		FormData fd_btnExplorer = new FormData();
		fd_btnExplorer.top = new FormAttachment(textCoilFilePath, -2, SWT.TOP);
		fd_btnExplorer.left = new FormAttachment(textCoilFilePath, 5);
		fd_btnExplorer.right = new FormAttachment(100,0);
		btnExplorer.setLayoutData(fd_btnExplorer);
		btnExplorer.setText("...");
		
		
		
		
		Label lblCoilData = new Label(grpCoil, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			lblCoilData.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else{
			lblCoilData.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		}
		FormData fd_lblCoilData = new FormData();
		fd_lblCoilData.top = new FormAttachment(textCoilFilePath, 10);
		fd_lblCoilData.left = new FormAttachment(lblSelectCoilData, 0, SWT.LEFT);
		lblCoilData.setLayoutData(fd_lblCoilData);
		lblCoilData.setText(LabelDatas.getLabel(UILabel.CoilData));
		
		
		Label lblProductName = new Label(grpCoil, SWT.NONE);
		FormData fd_lblProductName = new FormData();
		fd_lblProductName.top = new FormAttachment(lblCoilData, 6);
		fd_lblProductName.left = new FormAttachment(lblCoilData, 0, SWT.LEFT);
		fd_lblProductName.right = new FormAttachment(0,110);
		lblProductName.setLayoutData(fd_lblProductName);
		lblProductName.setText(LabelDatas.getLabel(UILabel.ProductName));
		
		textProductName = new Text(grpCoil, SWT.BORDER);
		med.setTextProductName(textProductName);
		CustomText c_textProductName = new CustomText(Mediator.TEXT_textProductName,med);
		med.setC_textProductName(c_textProductName);
		c_textProductName.setCustomWidget_textProductName();
		FormData fd_textProductName = new FormData();
		fd_textProductName.top = new FormAttachment(lblProductName, -2, SWT.TOP);
		fd_textProductName.left = new FormAttachment(lblProductName, 0);
		fd_textProductName.right = new FormAttachment(lblProductName,75,SWT.RIGHT);
		textProductName.setLayoutData(fd_textProductName);
		
		Label lblLineDiameter = new Label(grpCoil, SWT.NONE);
		FormData fd_lblLineDiameter = new FormData();
		fd_lblLineDiameter.top = new FormAttachment(lblProductName, 0, SWT.TOP);
		fd_lblLineDiameter.left = new FormAttachment(textProductName, 10);
		fd_lblLineDiameter.right = new FormAttachment(textProductName,110,SWT.RIGHT);
		lblLineDiameter.setLayoutData(fd_lblLineDiameter);
		lblLineDiameter.setText(LabelDatas.getLabel(UILabel.LineDiameter));
		
		textLineDiameter = new Text(grpCoil, SWT.BORDER);
		med.setTextLineDiameter(textLineDiameter);
		CustomText c_textLineDiameter = new CustomText(Mediator.TEXT_textLineDiameter,med);
		med.setC_textLineDiameter(c_textLineDiameter);
		c_textLineDiameter.setCustomWidget_textLineDiameter();
		FormData fd_textLineDiameter = new FormData();
		fd_textLineDiameter.top = new FormAttachment(textProductName, 0, SWT.TOP);
		fd_textLineDiameter.left = new FormAttachment(lblLineDiameter, 0);
		fd_textLineDiameter.right = new FormAttachment(lblLineDiameter, 75, SWT.RIGHT);
		textLineDiameter.setLayoutData(fd_textLineDiameter);
		
		
		
		Label lblCenterDiameter = new Label(grpCoil, SWT.NONE);
		FormData fd_lblCenterDiameter = new FormData();
		fd_lblCenterDiameter.top = new FormAttachment(lblProductName, 8);
		fd_lblCenterDiameter.left = new FormAttachment(lblProductName, 0, SWT.LEFT);
		fd_lblCenterDiameter.right = new FormAttachment(lblProductName, 0, SWT.RIGHT);
		lblCenterDiameter.setLayoutData(fd_lblCenterDiameter);
		lblCenterDiameter.setText(LabelDatas.getLabel(UILabel.CenterDiameter));
	
		textCenterDiameter = new Text(grpCoil, SWT.BORDER);
		med.setTextCenterDiameter(textCenterDiameter);
		CustomText c_textCenterDiameter = new CustomText(Mediator.TEXT_textCenterDiameter,med);
		med.setC_textCenterDiameter(c_textCenterDiameter);
		c_textCenterDiameter.setCustomWidget_textCenterDiameter();
		FormData fd_textCenterDiameter = new FormData();
		fd_textCenterDiameter.top = new FormAttachment(lblCenterDiameter, -2, SWT.TOP);
		fd_textCenterDiameter.left = new FormAttachment(textProductName, 0, SWT.LEFT);
		fd_textCenterDiameter.right = new FormAttachment(textProductName, 0,SWT.RIGHT);
		textCenterDiameter.setLayoutData(fd_textCenterDiameter);
		
		Label lblInnerDiameter = new Label(grpCoil, SWT.NONE);
		FormData fd_lblInnerDiameter = new FormData();
		fd_lblInnerDiameter.top = new FormAttachment(lblCenterDiameter, 0, SWT.TOP);
		fd_lblInnerDiameter.left = new FormAttachment(lblLineDiameter, 0,SWT.LEFT);
		fd_lblInnerDiameter.right = new FormAttachment(lblLineDiameter, 0,SWT.RIGHT);
		lblInnerDiameter.setLayoutData(fd_lblInnerDiameter);
		lblInnerDiameter.setText(LabelDatas.getLabel(UILabel.InnerDiameter));
		
		textInnerDiameter = new Text(grpCoil, SWT.BORDER);
		med.setTextInnerDiameter(textInnerDiameter);
		CustomText c_textInnerDiameter = new CustomText(Mediator.TEXT_textInnerDiameter, med);
		med.setC_textInnerDiameter(c_textInnerDiameter);
		c_textInnerDiameter.setCustomWidget_textInnerDiameter();
		FormData fd_textInnerDiameter = new FormData();
		fd_textInnerDiameter.top = new FormAttachment(lblCenterDiameter, -2, SWT.TOP);
		fd_textInnerDiameter.left = new FormAttachment(textLineDiameter, 0, SWT.LEFT);
		fd_textInnerDiameter.right = new FormAttachment(textLineDiameter, 0,SWT.RIGHT);
		textInnerDiameter.setLayoutData(fd_textInnerDiameter);
		
		
		
		Label lblOuterDiameter = new Label(grpCoil, SWT.NONE);
		FormData fd_lblOuterDiameter = new FormData();
		fd_lblOuterDiameter.top = new FormAttachment(lblCenterDiameter, 8);
		fd_lblOuterDiameter.left = new FormAttachment(lblCenterDiameter, 0, SWT.LEFT);
		fd_lblOuterDiameter.right = new FormAttachment(lblCenterDiameter, 0, SWT.RIGHT);
		lblOuterDiameter.setLayoutData(fd_lblOuterDiameter);
		lblOuterDiameter.setText(LabelDatas.getLabel(UILabel.OuterDiameter));
		
		textOuterDiameter = new Text(grpCoil, SWT.BORDER);
		med.setTextOuterDiameter(textOuterDiameter);
		CustomText c_textOuterDiameter = new CustomText(Mediator.TEXT_textOuterDiameter,med);
		med.setC_textOuterDiameter(c_textOuterDiameter);
		c_textOuterDiameter.setCustomWidget_textOuterDiameter();
		FormData fd_textOuterDiameter = new FormData();
		fd_textOuterDiameter.top = new FormAttachment(lblOuterDiameter, -2, SWT.TOP);
		fd_textOuterDiameter.left = new FormAttachment(textProductName, 0, SWT.LEFT);
		fd_textOuterDiameter.right = new FormAttachment(textProductName, 0,SWT.RIGHT);
		textOuterDiameter.setLayoutData(fd_textOuterDiameter);
		
		Label lblUpperInnerDiameter = new Label(grpCoil, SWT.NONE);
		FormData fd_lblUpperInnerDiameter = new FormData();
		fd_lblUpperInnerDiameter.top = new FormAttachment(lblOuterDiameter, 0, SWT.TOP);
		fd_lblUpperInnerDiameter.left = new FormAttachment(lblLineDiameter, 0, SWT.LEFT);
		fd_lblUpperInnerDiameter.right = new FormAttachment(lblLineDiameter, 0,SWT.RIGHT);
		lblUpperInnerDiameter.setLayoutData(fd_lblUpperInnerDiameter);
		lblUpperInnerDiameter.setText(LabelDatas.getLabel(UILabel.UpperInnerDiameter));
		
		textUpperInnerDiameter = new Text(grpCoil, SWT.BORDER);
		med.setTextUpperInnerDiameter(textUpperInnerDiameter);
		CustomText c_textUpperInnerDiameter = new CustomText(Mediator.TEXT_textUpperInnerDiameter,med);
		med.setC_textUpperInnerDiameter(c_textUpperInnerDiameter);
		c_textUpperInnerDiameter.setCustomWidget_textUpperInnerDiameter();
		FormData fd_textUpperInnerDiameter = new FormData();
		fd_textUpperInnerDiameter.top = new FormAttachment(lblOuterDiameter, -2, SWT.TOP);
		fd_textUpperInnerDiameter.left = new FormAttachment(textLineDiameter, 0, SWT.LEFT);
		fd_textUpperInnerDiameter.right = new FormAttachment(textLineDiameter,0,SWT.RIGHT);
		textUpperInnerDiameter.setLayoutData(fd_textUpperInnerDiameter);
		
		
		
		Label lblLowerInnerDiameter = new Label(grpCoil, SWT.NONE);
		FormData fd_lblLowerInnerDiameter = new FormData();
		fd_lblLowerInnerDiameter.top = new FormAttachment(lblOuterDiameter, 8);
		fd_lblLowerInnerDiameter.left = new FormAttachment(lblOuterDiameter, 0, SWT.LEFT);
		fd_lblLowerInnerDiameter.right = new FormAttachment(lblOuterDiameter, 0, SWT.RIGHT);
		lblLowerInnerDiameter.setLayoutData(fd_lblLowerInnerDiameter);
		lblLowerInnerDiameter.setText(LabelDatas.getLabel(UILabel.LowerInnerDiameter));
		
		textLowerInnerDiameter = new Text(grpCoil, SWT.BORDER);
		med.setTextLowerInnerDiameter(textLowerInnerDiameter);
		CustomText c_textLowerInnerDiameter = new CustomText(Mediator.TEXT_textLowerInnerDiameter,med);
		med.setC_textLowerInnerDiameter(c_textLowerInnerDiameter);
		c_textLowerInnerDiameter.setCustomWidget_textLowerInnerDiameter();
		FormData fd_textLowerInnerDiameter = new FormData();
		fd_textLowerInnerDiameter.top = new FormAttachment(lblLowerInnerDiameter, -2, SWT.TOP);
		fd_textLowerInnerDiameter.left = new FormAttachment(textProductName, 0, SWT.LEFT);
		fd_textLowerInnerDiameter.right = new FormAttachment(textProductName, 0,SWT.RIGHT);
		textLowerInnerDiameter.setLayoutData(fd_textLowerInnerDiameter);
		
		Label lblTotalNumber = new Label(grpCoil, SWT.NONE);
		FormData fd_lblTotalNumber = new FormData();
		fd_lblTotalNumber.top = new FormAttachment(lblLowerInnerDiameter, 0, SWT.TOP);
		fd_lblTotalNumber.left = new FormAttachment(lblLineDiameter, 0, SWT.LEFT);
		fd_lblTotalNumber.right = new FormAttachment(lblLineDiameter, 0,SWT.RIGHT);
		lblTotalNumber.setLayoutData(fd_lblTotalNumber);
		lblTotalNumber.setText(LabelDatas.getLabel(UILabel.TotalNumber));
		
		textTotalNumber = new Text(grpCoil, SWT.BORDER);
		med.setTextTotalNumber(textTotalNumber);
		CustomText c_textTotalNumber = new CustomText(Mediator.TEXT_textTotalNumber,med);
		med.setC_textTotalNumber(c_textTotalNumber);
		c_textTotalNumber.setCustomWidget_textTotalNumber();
		FormData fd_textTotalNumber = new FormData();
		fd_textTotalNumber.top = new FormAttachment(lblLowerInnerDiameter, -2, SWT.TOP);
		fd_textTotalNumber.left = new FormAttachment(textLineDiameter, 0, SWT.LEFT);
		fd_textTotalNumber.right = new FormAttachment(textLineDiameter, 0,SWT.RIGHT);
		textTotalNumber.setLayoutData(fd_textTotalNumber);
		
		Label lblCoilGeoDataTable = new Label(grpCoil, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			lblCoilGeoDataTable.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else{
			lblCoilGeoDataTable.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		}
		FormData fd_lblCoilGeoDataTable = new FormData();
		fd_lblCoilGeoDataTable.top = new FormAttachment(lblLowerInnerDiameter, 20);
		fd_lblCoilGeoDataTable.left = new FormAttachment(lblSelectCoilData, 0, SWT.LEFT);
		lblCoilGeoDataTable.setLayoutData(fd_lblCoilGeoDataTable);
		lblCoilGeoDataTable.setText(LabelDatas.getLabel(UILabel.CoilGeoDataTable));
		
		TableViewer tableViewerCoilTable = new TableViewer(grpCoil, SWT.BORDER | SWT.FULL_SELECTION);
		med.setTableViewerCoilTable(tableViewerCoilTable);
		CustomTableViewer c_tableViewerCoilTable = new CustomTableViewer(Mediator.TABLEVIEWER_tableViewerCoilTable, med);
		med.setC_tableViewerCoilTable(c_tableViewerCoilTable);
		c_tableViewerCoilTable.setCustomWidget_tableViewerCoilTable();
		tableCoilTable = tableViewerCoilTable.getTable();
		tableCoilTable.setHeaderVisible(true);
		tableCoilTable.setLinesVisible(true);
		FormData fd_tableCoilTable = new FormData();
		fd_tableCoilTable.top = new FormAttachment(lblCoilGeoDataTable, 6);
		fd_tableCoilTable.left = new FormAttachment(lblCoilGeoDataTable, 0, SWT.LEFT);
		fd_tableCoilTable.right = new FormAttachment(btnExplorer, 0, SWT.RIGHT);
		fd_tableCoilTable.bottom = new FormAttachment(100,-10);
		tableCoilTable.setLayoutData(fd_tableCoilTable);
		this.init_tableCoumn();
		
		
		
		Group grpSettingProcessInformation = new Group(compositeStep1, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			grpSettingProcessInformation.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else {
			grpSettingProcessInformation.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		}
		FormLayout fl_grpSettingProcessInformation = new FormLayout();
		fl_grpSettingProcessInformation.marginTop = 5;
		fl_grpSettingProcessInformation.marginRight = 5;
		fl_grpSettingProcessInformation.marginLeft = 5;
		fl_grpSettingProcessInformation.marginBottom = 5;
		grpSettingProcessInformation.setLayout(fl_grpSettingProcessInformation);
		FormData fd_grpSettingProcessInformation = new FormData();
		fd_grpSettingProcessInformation.top = new FormAttachment(0, 10);
		fd_grpSettingProcessInformation.left = new FormAttachment(grpCoil, 6);
		fd_grpSettingProcessInformation.right = new FormAttachment(grpCoil, 410, SWT.RIGHT);
		fd_grpSettingProcessInformation.bottom = new FormAttachment(0,230);
		grpSettingProcessInformation.setLayoutData(fd_grpSettingProcessInformation);
		grpSettingProcessInformation.setText(LabelDatas.getLabel(UILabel.SettingProcessInformation));
		
		Label lblHotSettingTemp = new Label(grpSettingProcessInformation, SWT.NONE);
		FormData fd_lblHotSettingTemp = new FormData();
		fd_lblHotSettingTemp.top = new FormAttachment(0, 10);
		fd_lblHotSettingTemp.left = new FormAttachment(0, 10);
		fd_lblHotSettingTemp.right = new FormAttachment(0, 200);
		lblHotSettingTemp.setLayoutData(fd_lblHotSettingTemp);
		lblHotSettingTemp.setText(LabelDatas.getLabel(UILabel.HotSettingTemp));
		
		textHotSettingTemp = new Text(grpSettingProcessInformation, SWT.BORDER);
		med.setTextHotSettingTemp(textHotSettingTemp);
		CustomText c_textHotSettingTemp = new CustomText(Mediator.TEXT_textHotSettingTemp, med);
		med.setC_textHotSettingTemp(c_textHotSettingTemp);
		c_textHotSettingTemp.setCustomWidget_textHotSettingTemp();
		FormData fd_textHotSettingTemp = new FormData();
		fd_textHotSettingTemp.top = new FormAttachment(lblHotSettingTemp, -2, SWT.TOP);
		fd_textHotSettingTemp.left = new FormAttachment(lblHotSettingTemp, 6);
		fd_textHotSettingTemp.right = new FormAttachment(100,-10);
		textHotSettingTemp.setLayoutData(fd_textHotSettingTemp);
		
		Label lblColdSettingTemp = new Label(grpSettingProcessInformation, SWT.NONE);
		FormData fd_lblColdSettingTemp = new FormData();
		fd_lblColdSettingTemp.top = new FormAttachment(lblHotSettingTemp, 8);
		fd_lblColdSettingTemp.left = new FormAttachment(lblHotSettingTemp, 0, SWT.LEFT);
		fd_lblColdSettingTemp.right = new FormAttachment(lblHotSettingTemp, 0, SWT.RIGHT);
		lblColdSettingTemp.setLayoutData(fd_lblColdSettingTemp);
		lblColdSettingTemp.setText(LabelDatas.getLabel(UILabel.ColdSettingTemp));
		
		textColdSettingTemp = new Text(grpSettingProcessInformation, SWT.BORDER);
		med.setTextColdSettingTemp(textColdSettingTemp);
		CustomText c_textColdSettingTemp = new CustomText(Mediator.TEXT_textColdSettingTemp, med);
		med.setC_textColdSettingTemp(c_textColdSettingTemp);
		c_textColdSettingTemp.setCustomWidget_textColdSettingTemp();
		FormData fd_textColdSettingTemp = new FormData();
		fd_textColdSettingTemp.top = new FormAttachment(lblColdSettingTemp, -2, SWT.TOP);
		fd_textColdSettingTemp.left = new FormAttachment(textHotSettingTemp, 0, SWT.LEFT);
		fd_textColdSettingTemp.right = new FormAttachment(textHotSettingTemp, 0, SWT.RIGHT);
		textColdSettingTemp.setLayoutData(fd_textColdSettingTemp);
		
		Label lblHotSettingHeight = new Label(grpSettingProcessInformation, SWT.NONE);
		FormData fd_lblHotSettingHeight = new FormData();
		fd_lblHotSettingHeight.top = new FormAttachment(lblColdSettingTemp, 8);
		fd_lblHotSettingHeight.left = new FormAttachment(lblHotSettingTemp, 0, SWT.LEFT);
		fd_lblHotSettingHeight.right = new FormAttachment(lblHotSettingTemp, 0, SWT.RIGHT);
		lblHotSettingHeight.setLayoutData(fd_lblHotSettingHeight);
		lblHotSettingHeight.setText(LabelDatas.getLabel(UILabel.HotSettingHeight));
		
		textHotSettingHeight = new Text(grpSettingProcessInformation, SWT.BORDER);
		med.setTextHotSettingHeight(textHotSettingHeight);
		CustomText c_textHotSettingHeight = new CustomText(Mediator.TEXT_textHotSettingHeight,med);
		med.setC_textHotSettingHeight(c_textHotSettingHeight);
		c_textHotSettingHeight.setCustomWidget_textHotSettingHeight();
		FormData fd_textHotSettingHeight = new FormData();
		fd_textHotSettingHeight.top = new FormAttachment(lblHotSettingHeight, -2, SWT.TOP);
		fd_textHotSettingHeight.left = new FormAttachment(textHotSettingTemp, 0, SWT.LEFT);
		fd_textHotSettingHeight.right = new FormAttachment(textHotSettingTemp, 0, SWT.RIGHT);
		textHotSettingHeight.setLayoutData(fd_textHotSettingHeight);
		
		Label lblColdSettingHeight = new Label(grpSettingProcessInformation, SWT.NONE);
		FormData fd_lblColdSettingHeight = new FormData();
		fd_lblColdSettingHeight.top = new FormAttachment(lblHotSettingHeight, 8);
		fd_lblColdSettingHeight.left = new FormAttachment(lblHotSettingTemp, 0, SWT.LEFT);
		fd_lblColdSettingHeight.right = new FormAttachment(lblHotSettingTemp, 0, SWT.RIGHT);
		lblColdSettingHeight.setLayoutData(fd_lblColdSettingHeight);
		lblColdSettingHeight.setText(LabelDatas.getLabel(UILabel.ColdSettingHeight));
		
		textColdSettingHeight = new Text(grpSettingProcessInformation, SWT.BORDER);
		med.setTextColdSettingHeight(textColdSettingHeight);
		CustomText c_textColdSettingHeight = new CustomText(Mediator.TEXT_textColdSettingHeight,med);
		med.setC_textColdSettingHeight(c_textColdSettingHeight);
		c_textColdSettingHeight.setCustomWidget_textColdSettingHeight();
		FormData fd_textColdSettingHeight = new FormData();
		fd_textColdSettingHeight.top = new FormAttachment(lblColdSettingHeight, -2, SWT.TOP);
		fd_textColdSettingHeight.left = new FormAttachment(textHotSettingTemp, 0, SWT.LEFT);
		fd_textColdSettingHeight.right = new FormAttachment(textHotSettingTemp, 0, SWT.RIGHT);
		textColdSettingHeight.setLayoutData(fd_textColdSettingHeight);
		
		Label lblSeatUInnerMargina = new Label(grpSettingProcessInformation, SWT.NONE);
		FormData fd_lblSeatUInnerMargina = new FormData();
		fd_lblSeatUInnerMargina.top = new FormAttachment(lblColdSettingHeight, 8);
		fd_lblSeatUInnerMargina.left = new FormAttachment(lblHotSettingTemp, 0, SWT.LEFT);
		fd_lblSeatUInnerMargina.right = new FormAttachment(lblHotSettingTemp, 0, SWT.RIGHT);
		lblSeatUInnerMargina.setLayoutData(fd_lblSeatUInnerMargina);
		lblSeatUInnerMargina.setText(LabelDatas.getLabel(UILabel.SeatUInnerMargina));
		
		textSeatUInnerMargina = new Text(grpSettingProcessInformation, SWT.BORDER);
		med.setTextSeatUInnerMargina(textSeatUInnerMargina);
		CustomText c_textSeatUInnerMargina = new CustomText(Mediator.TEXT_textSeatUInnerMargina, med);
		med.setC_textSeatUInnerMargina(c_textSeatUInnerMargina);
		c_textSeatUInnerMargina.setCustomWidget_textSeatUInnerMargina();
		FormData fd_textSeatUInnerMargina = new FormData();
		fd_textSeatUInnerMargina.top = new FormAttachment(lblSeatUInnerMargina, -2, SWT.TOP);
		fd_textSeatUInnerMargina.left = new FormAttachment(textHotSettingTemp, 0, SWT.LEFT);
		fd_textSeatUInnerMargina.right = new FormAttachment(textHotSettingTemp, 0, SWT.RIGHT);
		textSeatUInnerMargina.setLayoutData(fd_textSeatUInnerMargina);
		
		Label lblSeatLInnerMargina = new Label(grpSettingProcessInformation, SWT.NONE);
		FormData fd_lblSeatLInnerMargina = new FormData();
		fd_lblSeatLInnerMargina.top = new FormAttachment(lblSeatUInnerMargina, 8);
		fd_lblSeatLInnerMargina.left = new FormAttachment(lblHotSettingTemp, 0, SWT.LEFT);
		fd_lblSeatLInnerMargina.right = new FormAttachment(lblHotSettingTemp, 0, SWT.RIGHT);
		lblSeatLInnerMargina.setLayoutData(fd_lblSeatLInnerMargina);
		lblSeatLInnerMargina.setText(LabelDatas.getLabel(UILabel.SeatLInnerMargina));
		
		textSeatLInnerMargina = new Text(grpSettingProcessInformation, SWT.BORDER);
		med.setTextSeatLInnerMargina(textSeatLInnerMargina);
		CustomText c_textSeatLInnerMargina = new CustomText(Mediator.TEXT_textSeatLInnerMargina, med);
		med.setC_textSeatLInnerMargina(c_textSeatLInnerMargina);
		c_textSeatLInnerMargina.setCustomWidget_textSeatLInnerMargina();
		FormData fd_textSeatLInnerMargina = new FormData();
		fd_textSeatLInnerMargina.top = new FormAttachment(lblSeatLInnerMargina, -2, SWT.TOP);
		fd_textSeatLInnerMargina.left = new FormAttachment(textHotSettingTemp, 0, SWT.LEFT);
		fd_textSeatLInnerMargina.right = new FormAttachment(textHotSettingTemp, 0, SWT.RIGHT);
		textSeatLInnerMargina.setLayoutData(fd_textSeatLInnerMargina);
		
		Label lblSeatHeight = new Label(grpSettingProcessInformation, SWT.NONE);
		FormData fd_lblSeatHeight = new FormData();
		fd_lblSeatHeight.top = new FormAttachment(lblSeatLInnerMargina, 8);
		fd_lblSeatHeight.left = new FormAttachment(lblHotSettingTemp, 0, SWT.LEFT);
		fd_lblSeatHeight.right = new FormAttachment(lblHotSettingTemp, 0, SWT.RIGHT);
		lblSeatHeight.setLayoutData(fd_lblSeatHeight);
		lblSeatHeight.setText(LabelDatas.getLabel(UILabel.SeatHeight));
		
		textSeatHeight = new Text(grpSettingProcessInformation, SWT.BORDER);
		med.setTextSeatHeight(textSeatHeight);
		CustomText c_textSeatHeight = new CustomText(Mediator.TEXT_textSeatHeight, med);
		med.setC_textSeatHeight(c_textSeatHeight);
		c_textSeatHeight.setCustomWidget_textSeatHeight();
		FormData fd_textSeatHeight = new FormData();
		fd_textSeatHeight.top = new FormAttachment(lblSeatHeight, -2, SWT.TOP);
		fd_textSeatHeight.left = new FormAttachment(textHotSettingTemp, 0, SWT.LEFT);
		fd_textSeatHeight.right = new FormAttachment(textHotSettingTemp, 0, SWT.RIGHT);
		textSeatHeight.setLayoutData(fd_textSeatHeight);
		
		Group grpInitialConditioner = new Group(compositeStep1, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			grpInitialConditioner.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else {
			grpInitialConditioner.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		}
		FormLayout fl_grpInitialConditioner = new FormLayout();
		fl_grpInitialConditioner.marginTop = 5;
		fl_grpInitialConditioner.marginLeft = 5;
		fl_grpInitialConditioner.marginRight = 5;
		fl_grpInitialConditioner.marginBottom = 5;
		grpInitialConditioner.setLayout(fl_grpInitialConditioner);
		FormData fd_grpInitialConditioner = new FormData();
		fd_grpInitialConditioner.top = new FormAttachment(grpSettingProcessInformation, 6);
		fd_grpInitialConditioner.left = new FormAttachment(grpSettingProcessInformation, 0,SWT.LEFT);
		fd_grpInitialConditioner.right = new FormAttachment(grpSettingProcessInformation, 0,SWT.RIGHT);
		fd_grpInitialConditioner.bottom = new FormAttachment(grpSettingProcessInformation, 260, SWT.BOTTOM);
		grpInitialConditioner.setLayoutData(fd_grpInitialConditioner);
		grpInitialConditioner.setText(LabelDatas.getLabel(UILabel.InitialConditioner));
		
		Label lblRadiusConditioner = new Label(grpInitialConditioner, SWT.NONE);
		FormData fd_lblRadiusConditioner = new FormData();
		fd_lblRadiusConditioner.top = new FormAttachment(0, 10);
		fd_lblRadiusConditioner.left = new FormAttachment(0, 10);
		fd_lblRadiusConditioner.right = new FormAttachment(0,170);
		lblRadiusConditioner.setLayoutData(fd_lblRadiusConditioner);
		lblRadiusConditioner.setText(LabelDatas.getLabel(UILabel.RadiusConditioner));
		
		Composite compositeRadiusConditioner = new Composite(grpInitialConditioner, SWT.NONE);
		compositeRadiusConditioner.setLayout(new FormLayout());
		FormData fd_compositeRadiusConditioner = new FormData();
		fd_compositeRadiusConditioner.top = new FormAttachment(lblRadiusConditioner, 0);
		fd_compositeRadiusConditioner.left = new FormAttachment(lblRadiusConditioner, 0, SWT.LEFT);
		fd_compositeRadiusConditioner.right = new FormAttachment(100, -10);
		fd_compositeRadiusConditioner.bottom = new FormAttachment(lblRadiusConditioner,80,SWT.BOTTOM);
		compositeRadiusConditioner.setLayoutData(fd_compositeRadiusConditioner);
		
		Button btnRadiusConditionerConstant = new Button(compositeRadiusConditioner, SWT.RADIO);
		med.setBtnRadiusConditionerConstant(btnRadiusConditionerConstant);
		CustomButton c_btnRadiusConditionerConstant = new CustomButton(Mediator.BUTTON_btnRadiusConditionerConstant, med);
		med.setC_btnRadiusConditionerConstant(c_btnRadiusConditionerConstant);
		c_btnRadiusConditionerConstant.setCustomWidget_btnRadiusConditionerConstant();
		btnRadiusConditionerConstant.setSelection(true);
		FormData fd_btnRadiusConditionerConstant = new FormData();
		fd_btnRadiusConditionerConstant.top = new FormAttachment(0, 6);
		fd_btnRadiusConditionerConstant.left = new FormAttachment(0, 10);
		fd_btnRadiusConditionerConstant.right = new FormAttachment(0,190);
		btnRadiusConditionerConstant.setLayoutData(fd_btnRadiusConditionerConstant);
		btnRadiusConditionerConstant.setText(LabelDatas.getLabel(UILabel.Constant));
		
		Button btnRadiusConditionerFile = new Button(compositeRadiusConditioner, SWT.RADIO);
		med.setBtnRadiusConditionerFile(btnRadiusConditionerFile);
		CustomButton c_btnRadiusConditionerFile = new CustomButton(Mediator.BUTTON_btnRadiusConditionerFile, med);
		med.setC_btnRadiusConditionerFile(c_btnRadiusConditionerFile);
		c_btnRadiusConditionerFile.setCustomWidget_btnRadiusConditionerFile();
		FormData fd_btnRadiusConditionerFile = new FormData();
		fd_btnRadiusConditionerFile.top = new FormAttachment(btnRadiusConditionerConstant, 6);
		fd_btnRadiusConditionerFile.left = new FormAttachment(btnRadiusConditionerConstant, 0, SWT.LEFT);
		fd_btnRadiusConditionerFile.right = new FormAttachment(btnRadiusConditionerConstant, 0, SWT.RIGHT);
		btnRadiusConditionerFile.setLayoutData(fd_btnRadiusConditionerFile);
		btnRadiusConditionerFile.setText(LabelDatas.getLabel(UILabel.File));
		
		textRadiusConditionerValue = new Text(compositeRadiusConditioner, SWT.BORDER);
		med.setTextRadiusConditionerValue(textRadiusConditionerValue);
		CustomText c_textRadiusConditionerValue = new CustomText(Mediator.TEXT_textRadiusConditionerValue, med);
		med.setC_textRadiusConditionerValue(c_textRadiusConditionerValue);
		c_textRadiusConditionerValue.setCustomWidget_textRadiusConditionerValue();
		FormData fd_textRadiusConditionerValue = new FormData();
		fd_textRadiusConditionerValue.top = new FormAttachment(btnRadiusConditionerConstant, -2, SWT.TOP);
		fd_textRadiusConditionerValue.left = new FormAttachment(btnRadiusConditionerConstant, 6);
		fd_textRadiusConditionerValue.right = new FormAttachment(100,0);
		textRadiusConditionerValue.setLayoutData(fd_textRadiusConditionerValue);
		
		textRadiusConditionerPath = new Text(compositeRadiusConditioner, SWT.BORDER);
		med.setTextRadiusConditionerPath(textRadiusConditionerPath);
		CustomText c_textRadiusConditionerPath = new CustomText(Mediator.TEXT_textRadiusConditionerPath, med);
		med.setC_textRadiusConditionerPath(c_textRadiusConditionerPath);
		c_textRadiusConditionerPath.setCustomWidget_textRadiusConditionerPath();
		FormData fd_textRadiusConditionerPath = new FormData();
		fd_textRadiusConditionerPath.top = new FormAttachment(btnRadiusConditionerFile, 2);
		fd_textRadiusConditionerPath.left = new FormAttachment(btnRadiusConditionerConstant, 0,SWT.LEFT);
		fd_textRadiusConditionerPath.right = new FormAttachment(100, -40);
		textRadiusConditionerPath.setLayoutData(fd_textRadiusConditionerPath);
		
		Button btnRadiusConditionerExplorer = new Button(compositeRadiusConditioner, SWT.NONE);
		med.setBtnRadiusConditionerExplorer(btnRadiusConditionerExplorer);
		CustomButton c_btnRadiusConditionerExplorer = new CustomButton(Mediator.BUTTON_btnRadiusConditionerExplorer, med);
		med.setC_btnRadiusConditionerExplorer(c_btnRadiusConditionerExplorer);
		c_btnRadiusConditionerExplorer.setCustomWidget_btnRadiusConditionerExplorer();
		FormData fd_btnRadiusConditionerExplorer = new FormData();
		fd_btnRadiusConditionerExplorer.top = new FormAttachment(textRadiusConditionerPath, -2, SWT.TOP);
		fd_btnRadiusConditionerExplorer.left = new FormAttachment(textRadiusConditionerPath, 5);
		fd_btnRadiusConditionerExplorer.right = new FormAttachment(textRadiusConditionerValue, 0, SWT.RIGHT);
		btnRadiusConditionerExplorer.setLayoutData(fd_btnRadiusConditionerExplorer);
		btnRadiusConditionerExplorer.setText("...");
		
		Label lblHeightConditioner = new Label(grpInitialConditioner, SWT.NONE);
		FormData fd_lblHeightConditioner = new FormData();
		fd_lblHeightConditioner.top = new FormAttachment(compositeRadiusConditioner, 6);
		fd_lblHeightConditioner.left = new FormAttachment(lblRadiusConditioner, 0, SWT.LEFT);
		lblHeightConditioner.setLayoutData(fd_lblHeightConditioner);
		lblHeightConditioner.setText(LabelDatas.getLabel(UILabel.HeightConditioner));
		
		Composite compositeHeightConditioner = new Composite(grpInitialConditioner, SWT.NONE);
		compositeHeightConditioner.setLayout(new FormLayout());
		FormData fd_compositeHeightConditioner = new FormData();
		fd_compositeHeightConditioner.top = new FormAttachment(lblHeightConditioner, 0);
		fd_compositeHeightConditioner.left = new FormAttachment(lblHeightConditioner, 0, SWT.LEFT);
		fd_compositeHeightConditioner.right = new FormAttachment(100, -10);
		fd_compositeHeightConditioner.bottom = new FormAttachment(lblHeightConditioner,80,SWT.BOTTOM);
		compositeHeightConditioner.setLayoutData(fd_compositeHeightConditioner);
		
		Button btnHeightConditionerConstant = new Button(compositeHeightConditioner, SWT.RADIO);
		med.setBtnHeightConditionerConstant(btnHeightConditionerConstant);
		CustomButton c_btnHeightConditionerConstant = new CustomButton(Mediator.BUTTON_btnHeightConditionerConstant, med);
		med.setC_btnHeightConditionerConstant(c_btnHeightConditionerConstant);
		c_btnHeightConditionerConstant.setCustomWidget_btnHeightConditionerConstant();
		btnHeightConditionerConstant.setSelection(true);
		FormData fd_btnHeightConditionerConstant = new FormData();
		fd_btnHeightConditionerConstant.top = new FormAttachment(0, 6);
		fd_btnHeightConditionerConstant.left = new FormAttachment(0, 10);
		fd_btnHeightConditionerConstant.right = new FormAttachment(0,190);
		btnHeightConditionerConstant.setLayoutData(fd_btnHeightConditionerConstant);
		btnHeightConditionerConstant.setText(LabelDatas.getLabel(UILabel.Constant));
		
		Button btnHeightConditionerFile = new Button(compositeHeightConditioner, SWT.RADIO);
		med.setBtnHeightConditionerFile(btnHeightConditionerFile);
		CustomButton c_btnHeightConditionerFile = new CustomButton(Mediator.BUTTON_btnHeightConditionerFile, med);
		med.setC_btnHeightConditionerFile(c_btnHeightConditionerFile);
		c_btnHeightConditionerFile.setCustomWidget_btnHeightConditionerFile();
		FormData fd_btnHeightConditionerFile = new FormData();
		fd_btnHeightConditionerFile.top = new FormAttachment(btnHeightConditionerConstant, 6);
		fd_btnHeightConditionerFile.left = new FormAttachment(btnHeightConditionerConstant, 0, SWT.LEFT);
		fd_btnHeightConditionerFile.right = new FormAttachment(btnHeightConditionerConstant, 0, SWT.RIGHT);
		btnHeightConditionerFile.setLayoutData(fd_btnHeightConditionerFile);
		btnHeightConditionerFile.setText(LabelDatas.getLabel(UILabel.File));
		
		textHeightConditionerValue = new Text(compositeHeightConditioner, SWT.BORDER);
		med.setTextHeightConditionerValue(textHeightConditionerValue);
		CustomText c_textHeightConditionerValue = new CustomText(Mediator.TEXT_textHeightConditionerValue,med);
		med.setC_textHeightConditionerValue(c_textHeightConditionerValue);
		c_textHeightConditionerValue.setCustomWidget_textHeightConditionerValue();
		FormData fd_textHeightConditionerValue = new FormData();
		fd_textHeightConditionerValue.top = new FormAttachment(btnHeightConditionerConstant, -2, SWT.TOP);
		fd_textHeightConditionerValue.left = new FormAttachment(btnHeightConditionerConstant, 6);
		fd_textHeightConditionerValue.right = new FormAttachment(100,0);
		textHeightConditionerValue.setLayoutData(fd_textHeightConditionerValue);
		
		textHeightConditionerPath = new Text(compositeHeightConditioner, SWT.BORDER);
		med.setTextHeightConditionerPath(textHeightConditionerPath);
		CustomText c_textHeightConditionerPath = new CustomText(Mediator.TEXT_textHeightConditionerPath,med);
		med.setC_textHeightConditionerPath(c_textHeightConditionerPath);
		c_textHeightConditionerPath.setCustomWidget_textHeightConditionerPath();
		FormData fd_textHeightConditionerPath = new FormData();
		fd_textHeightConditionerPath.top = new FormAttachment(btnHeightConditionerFile, 2);
		fd_textHeightConditionerPath.left = new FormAttachment(btnHeightConditionerConstant, 0,SWT.LEFT);
		fd_textHeightConditionerPath.right = new FormAttachment(100, -40);
		textHeightConditionerPath.setLayoutData(fd_textHeightConditionerPath);
		
		Button btnHeightConditionerExplorer = new Button(compositeHeightConditioner, SWT.NONE);
		med.setBtnHeightConditionerExplorer(btnHeightConditionerExplorer);
		CustomButton c_btnHeightConditionerExplorer = new CustomButton(Mediator.BUTTON_btnHeightConditionerExplorer,med);
		med.setC_btnHeightConditionerExplorer(c_btnHeightConditionerExplorer);
		c_btnHeightConditionerExplorer.setCustomWidget_btnHeightConditionerExplorer();
		FormData fd_btnHeightConditionerExplorer = new FormData();
		fd_btnHeightConditionerExplorer.top = new FormAttachment(textHeightConditionerPath, -2, SWT.TOP);
		fd_btnHeightConditionerExplorer.left = new FormAttachment(textHeightConditionerPath, 5);
		fd_btnHeightConditionerExplorer.right = new FormAttachment(textHeightConditionerValue, 0, SWT.RIGHT);
		btnHeightConditionerExplorer.setLayoutData(fd_btnHeightConditionerExplorer);
		btnHeightConditionerExplorer.setText("...");
		
		
		////////////=> Simulation and Export result
		////////////=> Simulation and Export result
		////////////=> Simulation and Export result
		compositeStep2 = new Composite(compositeBottom, SWT.NONE);
		med.setCompositeStep2(compositeStep2);
		compositeStep2.setLayout(new FormLayout());
		
		Group grpAnalysisOptions = new Group(compositeStep2, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			grpAnalysisOptions.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else {
			grpAnalysisOptions.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		}
		FormLayout fl_grpAnalysisOptions = new FormLayout();
		fl_grpAnalysisOptions.marginTop = 5;
		fl_grpAnalysisOptions.marginRight = 5;
		fl_grpAnalysisOptions.marginLeft = 5;
		fl_grpAnalysisOptions.marginBottom = 5;
		grpAnalysisOptions.setLayout(fl_grpAnalysisOptions);
		FormData fd_grpAnalysisOptions = new FormData();
		fd_grpAnalysisOptions.top = new FormAttachment(0, 10);
		fd_grpAnalysisOptions.left = new FormAttachment(0, 10);
		fd_grpAnalysisOptions.right = new FormAttachment(100, -10);
		fd_grpAnalysisOptions.bottom = new FormAttachment(100, -10);
		grpAnalysisOptions.setLayoutData(fd_grpAnalysisOptions);
		grpAnalysisOptions.setText(LabelDatas.getLabel(UILabel.AnalysisOptions));
		
		Label lblRadiusTolerance = new Label(grpAnalysisOptions, SWT.NONE);
		FormData fd_lblRadiusTolerance = new FormData();
		fd_lblRadiusTolerance.top = new FormAttachment(0, 10);
		fd_lblRadiusTolerance.left = new FormAttachment(0, 10);
		fd_lblRadiusTolerance.right = new FormAttachment(0,200);
		lblRadiusTolerance.setLayoutData(fd_lblRadiusTolerance);
		lblRadiusTolerance.setText(LabelDatas.getLabel(UILabel.RadiusTolerance));
		
		textRadiusTolerance = new Text(grpAnalysisOptions, SWT.BORDER);
		med.setTextRadiusTolerance(textRadiusTolerance);
		CustomText c_textRadiusTolerance = new CustomText(Mediator.TEXT_textRadiusTolerance,med);
		med.setC_textRadiusTolerance(c_textRadiusTolerance);
		c_textRadiusTolerance.setCustomWidget_textRadiusTolerance();
		FormData fd_textRadiusTolerance = new FormData();
		fd_textRadiusTolerance.top = new FormAttachment(lblRadiusTolerance, -2, SWT.TOP);
		fd_textRadiusTolerance.left = new FormAttachment(lblRadiusTolerance, 0);
		fd_textRadiusTolerance.right = new FormAttachment(lblRadiusTolerance,120,SWT.RIGHT);
		textRadiusTolerance.setLayoutData(fd_textRadiusTolerance);
		
		Label lblHeightTolerance = new Label(grpAnalysisOptions, SWT.NONE);
		FormData fd_lblHeightTolerance = new FormData();
		fd_lblHeightTolerance.top = new FormAttachment(lblRadiusTolerance, 12);
		fd_lblHeightTolerance.left = new FormAttachment(lblRadiusTolerance, 0, SWT.LEFT);
		fd_lblHeightTolerance.right = new FormAttachment(lblRadiusTolerance, 0, SWT.RIGHT);
		lblHeightTolerance.setLayoutData(fd_lblHeightTolerance);
		lblHeightTolerance.setText(LabelDatas.getLabel(UILabel.HeightTolerance));
		
		textHeightTolerance = new Text(grpAnalysisOptions, SWT.BORDER);
		med.setTextHeightTolerance(textHeightTolerance);
		CustomText c_textHeightTolerance = new CustomText(Mediator.TEXT_textHeightTolerance, med);
		med.setC_textHeightTolerance(c_textHeightTolerance);
		c_textHeightTolerance.setCustomWidget_textHeightTolerance();
		FormData fd_textHeightTolerance = new FormData();
		fd_textHeightTolerance.top = new FormAttachment(lblHeightTolerance, -2, SWT.TOP);
		fd_textHeightTolerance.left = new FormAttachment(textRadiusTolerance, 0, SWT.LEFT);
		fd_textHeightTolerance.right = new FormAttachment(textRadiusTolerance, 0,SWT.RIGHT);
		textHeightTolerance.setLayoutData(fd_textHeightTolerance);
		
		Label lblMaximumIterationNumber = new Label(grpAnalysisOptions, SWT.NONE);
		FormData fd_lblMaximumIterationNumber = new FormData();
		fd_lblMaximumIterationNumber.top = new FormAttachment(lblHeightTolerance, 12);
		fd_lblMaximumIterationNumber.left = new FormAttachment(lblRadiusTolerance, 0, SWT.LEFT);
		fd_lblMaximumIterationNumber.right = new FormAttachment(lblRadiusTolerance, 0, SWT.RIGHT);
		lblMaximumIterationNumber.setLayoutData(fd_lblMaximumIterationNumber);
		lblMaximumIterationNumber.setText(LabelDatas.getLabel(UILabel.MaximumIterationNumber));
		
		textMaximumIterationNumber = new Text(grpAnalysisOptions, SWT.BORDER);
		med.setTextMaximumIterationNumber(textMaximumIterationNumber);
		CustomText c_textMaximumIterationNumber = new CustomText(Mediator.TEXT_textMaximumIterationNumber,med);
		med.setC_textMaximumIterationNumber(c_textMaximumIterationNumber);
		c_textMaximumIterationNumber.setCustomWidget_textMaximumIterationNumber();
		FormData fd_textMaximumIterationNumber = new FormData();
		fd_textMaximumIterationNumber.top = new FormAttachment(lblMaximumIterationNumber, -2, SWT.TOP);
		fd_textMaximumIterationNumber.left = new FormAttachment(textRadiusTolerance, 0, SWT.LEFT);
		fd_textMaximumIterationNumber.right = new FormAttachment(textRadiusTolerance, 0,SWT.RIGHT);
		textMaximumIterationNumber.setLayoutData(fd_textMaximumIterationNumber);
		
		Label lblSimulationIteration = new Label(grpAnalysisOptions, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			lblSimulationIteration.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else {
			lblSimulationIteration.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		}
		FormData fd_lblSimulationIteration = new FormData();
		fd_lblSimulationIteration.top = new FormAttachment(lblMaximumIterationNumber, 40);
		fd_lblSimulationIteration.left = new FormAttachment(lblRadiusTolerance, 0, SWT.LEFT);
		fd_lblSimulationIteration.right = new FormAttachment(lblRadiusTolerance, 0, SWT.RIGHT);
		lblSimulationIteration.setLayoutData(fd_lblSimulationIteration);
		lblSimulationIteration.setText(LabelDatas.getLabel(UILabel.SimulationIteration));
		
		Button btnStartSimulation = new Button(grpAnalysisOptions, SWT.NONE);
		med.setBtnStartSimulation(btnStartSimulation);
		CustomButton c_btnStartSimulation = new CustomButton(Mediator.BUTTON_btnStartSimulation,med);
		med.setC_btnStartSimulation(c_btnStartSimulation);
		c_btnStartSimulation.setCustomWidget_btnStartSimulation();
		FormData fd_btnStartSimulation = new FormData();
		fd_btnStartSimulation.top = new FormAttachment(lblSimulationIteration, -2, SWT.TOP);
		//fd_btnStartSimulation.left = new FormAttachment(textRadiusTolerance, 0, SWT.LEFT);
		//fd_btnStartSimulation.right = new FormAttachment(textRadiusTolerance, 0, SWT.RIGHT);
		fd_btnStartSimulation.left = new FormAttachment(100,-140);
		fd_btnStartSimulation.right = new FormAttachment(100,-10);
		btnStartSimulation.setLayoutData(fd_btnStartSimulation);
		btnStartSimulation.setText(LabelDatas.getLabel(UILabel.StartSimulation));
		
		ProgressBar progressBarSimulationIteration = new ProgressBar(grpAnalysisOptions, SWT.NONE);
		med.setProgressBarSimulationIteration(progressBarSimulationIteration);
		CustomProgressBar c_progressBarSimulationIteration = new CustomProgressBar(Mediator.PROGRESSBAR_progressBarSimulationIteration, med);
		med.setC_progressBarSimulationIteration(c_progressBarSimulationIteration);
		c_progressBarSimulationIteration.setCustomWidget_progressBarSimulationIteration();
		FormData fd_progressBarSimulationIteration = new FormData();
		fd_progressBarSimulationIteration.top = new FormAttachment(lblSimulationIteration, 20);
		fd_progressBarSimulationIteration.left = new FormAttachment(0, 10);
		fd_progressBarSimulationIteration.right = new FormAttachment(100, -10);
		progressBarSimulationIteration.setLayoutData(fd_progressBarSimulationIteration);
		
		Label lblCheckError = new Label(grpAnalysisOptions, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			lblCheckError.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else {
			lblCheckError.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		}
		FormData fd_lblCheckError = new FormData();
		fd_lblCheckError.top = new FormAttachment(progressBarSimulationIteration, 40);
		fd_lblCheckError.left = new FormAttachment(lblRadiusTolerance, 0, SWT.LEFT);
		lblCheckError.setLayoutData(fd_lblCheckError);
		lblCheckError.setText(LabelDatas.getLabel(UILabel.CheckError));
		
		Button btnReadLog = new Button(grpAnalysisOptions, SWT.NONE);
		med.setBtnReadLog(btnReadLog);
		CustomButton c_btnReadLog = new CustomButton(Mediator.BUTTON_btnReadLog, med);
		med.setC_btnReadLog(c_btnReadLog);
		c_btnReadLog.setCustomWidget_btnReadLog();
		FormData fd_btnReadLog = new FormData();
		fd_btnReadLog.top = new FormAttachment(lblCheckError, 0, SWT.TOP);
		//fd_btnReadLog.left = new FormAttachment(textRadiusTolerance, 0, SWT.LEFT);
		//fd_btnReadLog.right = new FormAttachment(textRadiusTolerance, 0, SWT.RIGHT);
		fd_btnReadLog.left = new FormAttachment(btnStartSimulation, 0, SWT.LEFT);
		fd_btnReadLog.right = new FormAttachment(btnStartSimulation, 0, SWT.RIGHT);
		btnReadLog.setLayoutData(fd_btnReadLog);
		btnReadLog.setText(LabelDatas.getLabel(UILabel.ReadLog));
		
		textLogEditor = new Text(grpAnalysisOptions, SWT.BORDER | SWT.MULTI);
		med.setTextLogEditor(textLogEditor);
		CustomText c_textLogEditor = new CustomText(Mediator.TEXT_textLogEditor, med);
		med.setC_textLogEditor(c_textLogEditor);
		c_textLogEditor.setCustomWidget_textLogEditor();
		FormData fd_textLogEditor = new FormData();
		fd_textLogEditor.top = new FormAttachment(lblCheckError, 20);
		fd_textLogEditor.left = new FormAttachment(lblRadiusTolerance, 0, SWT.LEFT);
		fd_textLogEditor.right = new FormAttachment(100, -10);
		fd_textLogEditor.bottom = new FormAttachment(100, -10);
		textLogEditor.setLayoutData(fd_textLogEditor);
		
		
		////////////=> Show Result
		////////////=> Show Result
		////////////=> Show Result
		compositeStep3 = new Composite(compositeBottom, SWT.NONE);
		med.setCompositeStep3(compositeStep3);
		compositeStep3.setLayout(new FormLayout());
		
		Group grpDisplayResultCoil = new Group(compositeStep3, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			grpDisplayResultCoil.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}else {
			grpDisplayResultCoil.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD));
		}
		FormLayout fl_grpDisplayResultCoil = new FormLayout();
		fl_grpDisplayResultCoil.marginTop = 5;
		fl_grpDisplayResultCoil.marginRight = 5;
		fl_grpDisplayResultCoil.marginLeft = 5;
		fl_grpDisplayResultCoil.marginBottom = 5;
		grpDisplayResultCoil.setLayout(fl_grpDisplayResultCoil);
		FormData fd_grpDisplayResultCoil = new FormData();
		fd_grpDisplayResultCoil.top = new FormAttachment(0, 10);
		fd_grpDisplayResultCoil.left = new FormAttachment(0, 10);
		fd_grpDisplayResultCoil.right = new FormAttachment(100, -10);
		fd_grpDisplayResultCoil.bottom = new FormAttachment(100, -10);
		grpDisplayResultCoil.setLayoutData(fd_grpDisplayResultCoil);
		grpDisplayResultCoil.setText(LabelDatas.getLabel(UILabel.DisplayResultCoilData));
		
		Label lblSelectGraph = new Label(grpDisplayResultCoil, SWT.NONE);
		FormData fd_lblSelectGraph = new FormData();
		fd_lblSelectGraph.top = new FormAttachment(0, 10);
		fd_lblSelectGraph.left = new FormAttachment(0, 10);
		fd_lblSelectGraph.right = new FormAttachment(0,150);
		lblSelectGraph.setLayoutData(fd_lblSelectGraph);
		lblSelectGraph.setText(LabelDatas.getLabel(UILabel.SelectGraph));
		
		ComboViewer comboViewerSelectGraph = new ComboViewer(grpDisplayResultCoil, SWT.NONE | SWT.READ_ONLY);
		med.setComboViewerSelectGraph(comboViewerSelectGraph);
		CustomComboViewer c_comboViewerSelectGraph = new CustomComboViewer(Mediator.COMBOVIEWER_comboViewerSelectGraph,med);
		med.setC_comboViewerSelectGraph(c_comboViewerSelectGraph);
		c_comboViewerSelectGraph.setCustomWidget_comboViewerSelectGraph();
		comboSelectGraph = comboViewerSelectGraph.getCombo();
		FormData fd_comboViewerSelectGraph = new FormData();
		fd_comboViewerSelectGraph.top = new FormAttachment(lblSelectGraph, -2, SWT.TOP);
		fd_comboViewerSelectGraph.left = new FormAttachment(lblSelectGraph, 0);
		fd_comboViewerSelectGraph.right = new FormAttachment(lblSelectGraph, 400,SWT.RIGHT);
		comboSelectGraph.setLayoutData(fd_comboViewerSelectGraph);
		
		Button btnShowGraphWindow = new Button(grpDisplayResultCoil, SWT.NONE);
		med.setBtnShowGraphWindow(btnShowGraphWindow);
		CustomButton c_btnShowGraphWindow = new CustomButton(Mediator.BUTTON_btnShowGraphWindow,med);
		med.setC_btnShowGraphWindow(c_btnShowGraphWindow);
		c_btnShowGraphWindow.setCustomWidget_btnShowGraphWindow();
		FormData fd_btnShowGraphWindow = new FormData();
		fd_btnShowGraphWindow.top = new FormAttachment(comboSelectGraph, 0, SWT.TOP);
		fd_btnShowGraphWindow.left = new FormAttachment(comboSelectGraph, 10);
		fd_btnShowGraphWindow.right = new FormAttachment(comboSelectGraph, 150, SWT.RIGHT); 
		btnShowGraphWindow.setLayoutData(fd_btnShowGraphWindow);
		btnShowGraphWindow.setText(LabelDatas.getLabel(UILabel.ShowPopupWindow_1));
		
		Label lblSelectImage = new Label(grpDisplayResultCoil, SWT.NONE);
		FormData fd_lblSelectImage = new FormData();
		fd_lblSelectImage.top = new FormAttachment(lblSelectGraph, 12);
		fd_lblSelectImage.left = new FormAttachment(lblSelectGraph, 0, SWT.LEFT);
		fd_lblSelectImage.right = new FormAttachment(lblSelectGraph, 0, SWT.RIGHT);
		lblSelectImage.setLayoutData(fd_lblSelectImage);
		lblSelectImage.setText(LabelDatas.getLabel(UILabel.SelectImage));
		
		ComboViewer comboViewerSelectImage = new ComboViewer(grpDisplayResultCoil, SWT.NONE | SWT.READ_ONLY);
		med.setComboViewerSelectImage(comboViewerSelectImage);
		CustomComboViewer c_comboViewerSelectImage = new CustomComboViewer(Mediator.COMBOVIEWER_comboViewerSelectImage,med);
		med.setC_comboViewerSelectImage(c_comboViewerSelectImage);
		c_comboViewerSelectImage.setCustomWidget_comboViewerSelectImage();
		comboSelectImage = comboViewerSelectImage.getCombo();
		FormData fd_comboViewerSelectImage = new FormData();
		fd_comboViewerSelectImage.top = new FormAttachment(lblSelectImage, -2, SWT.TOP);
		fd_comboViewerSelectImage.left = new FormAttachment(comboSelectGraph, 0, SWT.LEFT);
		fd_comboViewerSelectImage.right = new FormAttachment(comboSelectGraph, 0,SWT.RIGHT);
		comboSelectImage.setLayoutData(fd_comboViewerSelectImage);
		
		Button btnShowImageWindow = new Button(grpDisplayResultCoil, SWT.NONE);
		med.setBtnShowImageWindow(btnShowImageWindow);
		CustomButton c_btnShowImageWindow = new CustomButton(Mediator.BUTTON_btnShowImageWindow,med);
		med.setC_btnShowImageWindow(c_btnShowImageWindow);
		c_btnShowImageWindow.setCustomWidget_btnShowImageWindow();
		FormData fd_btnShowImageWindow = new FormData();
		fd_btnShowImageWindow.top = new FormAttachment(comboSelectImage, 0, SWT.TOP);
		fd_btnShowImageWindow.left = new FormAttachment(btnShowGraphWindow, 0, SWT.LEFT);
		fd_btnShowImageWindow.right = new FormAttachment(btnShowGraphWindow, 0, SWT.RIGHT); 
		btnShowImageWindow.setLayoutData(fd_btnShowImageWindow);
		btnShowImageWindow.setText(LabelDatas.getLabel(UILabel.ShowPopupWindow_2));
		
		Label lblSelectTableData = new Label(grpDisplayResultCoil, SWT.NONE);
		FormData fd_lblSelectTableData = new FormData();
		fd_lblSelectTableData.top = new FormAttachment(lblSelectImage, 12);
		fd_lblSelectTableData.left = new FormAttachment(lblSelectGraph, 0, SWT.LEFT);
		fd_lblSelectTableData.right = new FormAttachment(lblSelectGraph, 0, SWT.RIGHT);
		lblSelectTableData.setLayoutData(fd_lblSelectTableData);
		lblSelectTableData.setText(LabelDatas.getLabel(UILabel.SelectTableData));
		
		ComboViewer comboViewerSelectTableData = new ComboViewer(grpDisplayResultCoil, SWT.NONE | SWT.READ_ONLY);
		med.setComboViewerSelectTableData(comboViewerSelectTableData);
		CustomComboViewer c_comboViewerSelectTableData = new CustomComboViewer(Mediator.COMBOVIEWER_comboViewerSelectTableData,med);
		med.setC_comboViewerSelectTableData(c_comboViewerSelectTableData);
		c_comboViewerSelectTableData.setCustomWidget_comboViewerSelectTableData();
		comboSelectTableData = comboViewerSelectTableData.getCombo();
		FormData fd_comboViewerSelectTableData = new FormData();
		fd_comboViewerSelectTableData.top = new FormAttachment(lblSelectTableData, -2, SWT.TOP);
		fd_comboViewerSelectTableData.left = new FormAttachment(comboSelectGraph, 0, SWT.LEFT);
		fd_comboViewerSelectTableData.right = new FormAttachment(comboSelectGraph, 0, SWT.RIGHT);
		comboSelectTableData.setLayoutData(fd_comboViewerSelectTableData);
		
		Button btnShowTableData = new Button(grpDisplayResultCoil, SWT.NONE);
		med.setBtnShowTableData(btnShowTableData);
		CustomButton c_btnShowTableData = new CustomButton(Mediator.BUTTON_btnShowTableData,med);
		med.setC_btnShowTableData(c_btnShowTableData);
		c_btnShowTableData.setCustomWidget_btnShowTableData();
		FormData fd_btnShowTableData = new FormData();
		fd_btnShowTableData.top = new FormAttachment(comboSelectTableData, 0, SWT.TOP);
		fd_btnShowTableData.left = new FormAttachment(btnShowGraphWindow, 0, SWT.LEFT);
		fd_btnShowTableData.right = new FormAttachment(btnShowGraphWindow, 0, SWT.RIGHT); 
		btnShowTableData.setLayoutData(fd_btnShowTableData);
		btnShowTableData.setText(LabelDatas.getLabel(UILabel.Show));
		
		
		// graph area
		Composite compositeGraph = new Composite(grpDisplayResultCoil, SWT.BORDER);
		compositeGraph.setLayout(new FormLayout());
		FormData fd_compositeGraph = new FormData();
		fd_compositeGraph.top = new FormAttachment(lblSelectTableData, 30);
		fd_compositeGraph.left = new FormAttachment(lblSelectGraph, 0, SWT.LEFT);
		fd_compositeGraph.right = new FormAttachment(0,390);
		fd_compositeGraph.bottom = new FormAttachment(100,-10);
		compositeGraph.setLayoutData(fd_compositeGraph);
		
		Label lblGraphArea = new Label(compositeGraph, SWT.NONE);
		FormData fd_lblGraphArea = new FormData();
		fd_lblGraphArea.top = new FormAttachment(0,10);
		fd_lblGraphArea.left = new FormAttachment(0,10);
		lblGraphArea.setLayoutData(fd_lblGraphArea);
		lblGraphArea.setText("Display Graph");
		
		Composite compositeImage = new Composite(grpDisplayResultCoil, SWT.BORDER);
		compositeImage.setLayout(new FormLayout());
		FormData fd_compositeImage = new FormData();
		fd_compositeImage.top = new FormAttachment(compositeGraph, 0,SWT.TOP);
		fd_compositeImage.left = new FormAttachment(compositeGraph, 5, SWT.RIGHT);
		fd_compositeImage.right = new FormAttachment(compositeGraph,395,SWT.RIGHT);
		fd_compositeImage.bottom = new FormAttachment(compositeGraph, 0, SWT.BOTTOM);
		compositeImage.setLayoutData(fd_compositeImage);
		
		Label lblImageArea = new Label(compositeImage, SWT.NONE);
		FormData fd_lblImageArea = new FormData();
		fd_lblImageArea.top = new FormAttachment(0,10);
		fd_lblImageArea.left = new FormAttachment(0,10);
		lblImageArea.setLayoutData(fd_lblImageArea);
		lblImageArea.setText("Display Image");
		
		
		this.init_WidgetSetting();
		this.init_WidgetEvent();
		
	}
	
	private void init_tableCoumn(){
		// For Edit Cell Editor
		String [] ColumnName = new String [] { TableColumnLabel.COL_0, 
												TableColumnLabel.COL_1,
												TableColumnLabel.COL_2};
		int [] ColumnWidth = new int [] { 100, 120, 120};
		int [] ColumnAligments = new int [] { SWT.LEFT, SWT.LEFT, SWT.LEFT };
		String [] ColumnProperty = { TableColumnLabel.COL_0, 
										TableColumnLabel.COL_1,
										TableColumnLabel.COL_2};
		
		for(int i=0; i<ColumnName.length ; i++){
			TableColumn tableColumn_Coil = new TableColumn(this.tableCoilTable,ColumnAligments[i]);
			tableColumn_Coil.setText(ColumnName[i]);
			tableColumn_Coil.setWidth(ColumnWidth[i]);
		}
		med.getTableViewerCoilTable().setColumnProperties(ColumnProperty);
	}
	
	private void init_WidgetSetting(){
		this.stackLayout.topControl = compositeStep1;
		med.getCompositeTop().setEnabled(false);
		med.getCompositeBottom().setEnabled(false);
		//med.getCompositeStep1().setEnabled(false);
		//med.getCompositeStep2().setEnabled(false);
		//med.getCompositeStep3().setEnabled(false);
	}
	
	private void init_WidgetEvent(){
		// Button 
		HandlerButton handlerButton = new HandlerButton();
		med.getBtnStepSave().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorer().addListener(SWT.Selection, handlerButton);		
		med.getBtnRadiusConditionerConstant().addListener(SWT.Selection, handlerButton);
		med.getBtnRadiusConditionerFile().addListener(SWT.Selection, handlerButton);
		med.getBtnRadiusConditionerExplorer().addListener(SWT.Selection, handlerButton);
		med.getBtnHeightConditionerConstant().addListener(SWT.Selection, handlerButton);
		med.getBtnHeightConditionerFile().addListener(SWT.Selection, handlerButton);
		med.getBtnHeightConditionerExplorer().addListener(SWT.Selection, handlerButton);
		med.getBtnStartSimulation().addListener(SWT.Selection, handlerButton);
		med.getBtnReadLog().addListener(SWT.Selection, handlerButton);
		med.getBtnShowGraphWindow().addListener(SWT.Selection, handlerButton);
		med.getBtnShowImageWindow().addListener(SWT.Selection, handlerButton);
		med.getBtnShowTableData().addListener(SWT.Selection, handlerButton);
		
		// Label 
		HandlerLabel handlerLabel = new HandlerLabel();
		//med.getLblProcessStep().addListener(SWT.MouseDoubleClick, handlerLabel);
		med.getLblModeling().addListener(SWT.MouseDoubleClick, handlerLabel);
		med.getLblSimulationAndExportResult().addListener(SWT.MouseDoubleClick, handlerLabel);
		med.getLblShowResult().addListener(SWT.MouseDoubleClick, handlerLabel);
		
		// Text 
		HandlerText handlerText = new HandlerText();
		med.getTextCoilFilePath().addListener(SWT.CHANGED, handlerText);
		med.getTextProductName().addListener(SWT.CHANGED, handlerText);
		med.getTextLineDiameter().addListener(SWT.CHANGED, handlerText);
		med.getTextCenterDiameter().addListener(SWT.CHANGED, handlerText);
		med.getTextInnerDiameter().addListener(SWT.CHANGED, handlerText);
		med.getTextOuterDiameter().addListener(SWT.CHANGED, handlerText);
		med.getTextUpperInnerDiameter().addListener(SWT.CHANGED, handlerText);
		med.getTextLowerInnerDiameter().addListener(SWT.CHANGED, handlerText);
		med.getTextTotalNumber().addListener(SWT.CHANGED, handlerText);
		med.getTextHotSettingTemp().addListener(SWT.CHANGED, handlerText);
		med.getTextColdSettingTemp().addListener(SWT.CHANGED, handlerText);
		med.getTextHotSettingHeight().addListener(SWT.CHANGED, handlerText);
		med.getTextColdSettingHeight().addListener(SWT.CHANGED, handlerText);
		med.getTextSeatUInnerMargina().addListener(SWT.CHANGED, handlerText);
		med.getTextSeatLInnerMargina().addListener(SWT.CHANGED, handlerText);
		med.getTextSeatHeight().addListener(SWT.CHANGED, handlerText);
		med.getTextRadiusConditionerValue().addListener(SWT.CHANGED, handlerText);
		med.getTextRadiusConditionerPath().addListener(SWT.CHANGED, handlerText);
		med.getTextHeightConditionerValue().addListener(SWT.CHANGED, handlerText);
		med.getTextHeightConditionerPath().addListener(SWT.CHANGED, handlerText);
		med.getTextRadiusTolerance().addListener(SWT.CHANGED, handlerText);
		med.getTextHeightTolerance().addListener(SWT.CHANGED, handlerText);
		med.getTextMaximumIterationNumber().addListener(SWT.CHANGED, handlerText);
		med.getTextLogEditor().addListener(SWT.CHANGED, handlerText);
		// TableViewer
		HandlerTableViewer handlerTableViewer = new HandlerTableViewer();
		med.getTableViewerCoilTable().addSelectionChangedListener(handlerTableViewer);
		// ComboViewer
		HandlerComboViewer handlerComboViewer = new HandlerComboViewer();
		med.getComboViewerSelectGraph().addSelectionChangedListener(handlerComboViewer);
		med.getComboViewerSelectImage().addSelectionChangedListener(handlerComboViewer);
		med.getComboViewerSelectTableData().addSelectionChangedListener(handlerComboViewer);
	}

	@Override
	public void setFocus() {
		
	}
}