package com.js.ens.coil;

import java.util.*;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
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
import com.js.ens.coil.customWidget.CustomTableViewer;
import com.js.ens.coil.customWidget.CustomText;
import com.js.ens.coil.customWidget.TableColumnLabel;
import com.js.ens.coil.handler.HandlerButton;
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
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.TableColumn;

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
		compositeStep1 = new Composite(compositeBottom, SWT.NONE);
		med.setCompositeStep1(compositeStep1);
		compositeStep1.setLayout(new FormLayout());
		
		Group grpCoil = new Group(compositeStep1, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			grpCoil.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}
		grpCoil.setLayout(new FormLayout());
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
		c_textCoilFilePath.setCustomWidte_textCoilFilePath();
		FormData fd_textCoilFilePath = new FormData();
		fd_textCoilFilePath.top = new FormAttachment(lblSelectCoilData, 6);
		fd_textCoilFilePath.left = new FormAttachment(0, 10);
		fd_textCoilFilePath.right = new FormAttachment(100,-30);
		textCoilFilePath.setLayoutData(fd_textCoilFilePath);
		
		Button btnExplorer = new Button(grpCoil, SWT.NONE);
		med.setBtnExplorer(btnExplorer);
		CustomButton c_btnExplorer = new CustomButton(Mediator.BUTTON_btnExplorer,med);
		med.setC_btnExplorer(c_btnExplorer);
		c_btnExplorer.setCustomWidget_btnExplorer();
		FormData fd_btnExplorer = new FormData();
		fd_btnExplorer.top = new FormAttachment(textCoilFilePath, -2, SWT.TOP);
		fd_btnExplorer.left = new FormAttachment(textCoilFilePath, 5);
		btnExplorer.setLayoutData(fd_btnExplorer);
		btnExplorer.setText("...");
		
		
		
		
		Label lblCoilData = new Label(grpCoil, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			lblCoilData.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		}
		FormData fd_lblCoilData = new FormData();
		fd_lblCoilData.top = new FormAttachment(textCoilFilePath, 10);
		fd_lblCoilData.left = new FormAttachment(lblSelectCoilData, 0, SWT.LEFT);
		lblCoilData.setLayoutData(fd_lblCoilData);
		lblCoilData.setText(LabelDatas.getLabel(UILabel.CoilData));
		
		
		Label lblProductName = new Label(grpCoil, SWT.NONE);
		FormData fd_lblProductName = new FormData();
		fd_lblProductName.top = new FormAttachment(lblCoilData, 6);
		fd_lblProductName.left = new FormAttachment(lblCoilData, 5, SWT.LEFT);
		fd_lblProductName.right = new FormAttachment(0,110);
		lblProductName.setLayoutData(fd_lblProductName);
		lblProductName.setText(LabelDatas.getLabel(UILabel.ProductName));
		
		textProductName = new Text(grpCoil, SWT.BORDER);
		med.setTextProductName(textProductName);
		CustomText c_textProductName = new CustomText(Mediator.TEXT_textProductName,med);
		med.setC_textProductName(c_textProductName);
		c_textProductName.setCustomWidte_textProductName();
		FormData fd_textProductName = new FormData();
		fd_textProductName.top = new FormAttachment(lblProductName, -2, SWT.TOP);
		fd_textProductName.left = new FormAttachment(lblProductName, 6);
		fd_textProductName.right = new FormAttachment(lblProductName,80,SWT.RIGHT);
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
		c_textLineDiameter.setCustomWidte_textLineDiameter();
		FormData fd_textLineDiameter = new FormData();
		fd_textLineDiameter.top = new FormAttachment(textProductName, 0, SWT.TOP);
		fd_textLineDiameter.left = new FormAttachment(lblLineDiameter, 6);
		fd_textLineDiameter.right = new FormAttachment(lblLineDiameter, 80, SWT.RIGHT);
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
		c_textCenterDiameter.setCustomWidte_textCenterDiameter();
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
		c_textInnerDiameter.setCustomWidte_textInnerDiameter();
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
		c_textOuterDiameter.setCustomWidte_textOuterDiameter();
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
		c_textUpperInnerDiameter.setCustomWidte_textUpperInnerDiameter();
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
		c_textLowerInnerDiameter.setCustomWidte_textLowerInnerDiameter();
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
		c_textTotalNumber.setCustomWidte_textTotalNumber();
		FormData fd_textTotalNumber = new FormData();
		fd_textTotalNumber.top = new FormAttachment(lblLowerInnerDiameter, -2, SWT.TOP);
		fd_textTotalNumber.left = new FormAttachment(textLineDiameter, 0, SWT.LEFT);
		fd_textTotalNumber.right = new FormAttachment(textLineDiameter, 0,SWT.RIGHT);
		textTotalNumber.setLayoutData(fd_textTotalNumber);
		
		Label lblCoilGeoDataTable = new Label(grpCoil, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			lblCoilGeoDataTable.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
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
		
		
		
		
		
		
		
		
		
		this.init_WidgetSetting();
		this.init_WidgetEvent();
		
	}
	
	private void init_tableCoumn(){
		// Editor Cell을 위한 property 선언
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
	}
	
	private void init_WidgetEvent(){
		// Button 
		HandlerButton handlerButton = new HandlerButton();
		med.getBtnStepSave().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorer().addListener(SWT.Selection, handlerButton);		
		
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
		
		// TableViewer
		HandlerTableViewer handlerTableViewer = new HandlerTableViewer();
		med.getTableViewerCoilTable().addSelectionChangedListener(handlerTableViewer);
		
	}

	@Override
	public void setFocus() {
		
	}
}