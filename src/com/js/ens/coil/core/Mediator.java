package com.js.ens.coil.core;


import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

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
	// Bottom
	//
	private Composite compositeBottom;
	public static String COMPOSITE_compositeBottom = "compositeBottom";
	
	private StackLayout stackLayout;
	
	private Composite compositeStep1;
	public static String COMPOSITE_compositeStep1 = "compositeStep1";
	
	private Composite compositeStep2;
	public static String COMPOSITE_compositeStep2 = "compositeStep2";
	
	private Composite compositeStep3;
	public static String COMPOSITE_compositeStep3 = "compositeStep3";
	
	
	//////////////////////////////////////////////////////////////////////////
	// step1
	private Text textCoilFilePath;
	public ICommand C_textCoilFilePath;
	public static String TEXT_textCoilFilePath = "textCoilFilePath";
	
	private Button btnExplorer;
	public ICommand C_btnExplorer;
	public static String BUTTON_btnExplorer = "btnExplorer";
	
	
	private Text textProductName;
	public ICommand C_textProductName;
	public static String TEXT_textProductName = "textProductName";
	
	private Text textLineDiameter;
	public ICommand C_textLineDiameter;
	public static String TEXT_textLineDiameter = "textLineDiameter";
	
	private Text textCenterDiameter;
	public ICommand C_textCenterDiameter;
	public static String TEXT_textCenterDiameter = "textCenterDiameter";
	
	private Text textInnerDiameter;
	public ICommand C_textInnerDiameter;
	public static String TEXT_textInnerDiameter = "textInnerDiameter";
	
	private Text textOuterDiameter;
	public ICommand C_textOuterDiameter;
	public static String TEXT_textOuterDiameter = "textOuterDiameter";
	
	private Text textUpperInnerDiameter;
	public ICommand C_textUpperInnerDiameter;
	public static String TEXT_textUpperInnerDiameter = "textUpperInnerDiameter";
	
	private Text textLowerInnerDiameter;
	public ICommand C_textLowerInnerDiameter;
	public static String TEXT_textLowerInnerDiameter = "textLowerInnerDiameter";
	
	private Text textTotalNumber;
	public ICommand C_textTotalNumber;
	public static String TEXT_textTotalNumber = "textTotalNumber";
	
	private TableViewer tableViewerCoilTable;
	public ICommand C_tableViewerCoilTable;
	public static String TABLEVIEWER_tableViewerCoilTable = "tableViewerCoilTable";
	
	
	
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
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
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
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	// Bottom
	public Composite getCompositeBottom() {
		return compositeBottom;
	}

	public void setCompositeBottom(Composite compositeBottom) {
		this.compositeBottom = compositeBottom;
	}

	public StackLayout getStackLayout() {
		return stackLayout;
	}

	public void setStackLayout(StackLayout stackLayout) {
		this.stackLayout = stackLayout;
	}

	public Composite getCompositeStep1() {
		return compositeStep1;
	}

	public void setCompositeStep1(Composite compositeStep1) {
		this.compositeStep1 = compositeStep1;
	}

	public Composite getCompositeStep2() {
		return compositeStep2;
	}

	public void setCompositeStep2(Composite compositeStep2) {
		this.compositeStep2 = compositeStep2;
	}

	public Composite getCompositeStep3() {
		return compositeStep3;
	}

	public void setCompositeStep3(Composite compositeStep3) {
		this.compositeStep3 = compositeStep3;
	}
	/////////////////////////////////////////////////////
	// Step1
	public Text getTextCoilFilePath() {
		return textCoilFilePath;
	}

	public void setTextCoilFilePath(Text textCoilFilePath) {
		this.textCoilFilePath = textCoilFilePath;
	}

	public ICommand getC_textCoilFilePath() {
		return C_textCoilFilePath;
	}

	public void setC_textCoilFilePath(ICommand c_textCoilFilePath) {
		C_textCoilFilePath = c_textCoilFilePath;
	}

	public Button getBtnExplorer() {
		return btnExplorer;
	}

	public void setBtnExplorer(Button btnExplorer) {
		this.btnExplorer = btnExplorer;
	}

	public ICommand getC_btnExplorer() {
		return C_btnExplorer;
	}

	public void setC_btnExplorer(ICommand c_btnExplorer) {
		C_btnExplorer = c_btnExplorer;
	}

	public static String getBUTTON_btnExplorer() {
		return BUTTON_btnExplorer;
	}

	public static void setBUTTON_btnExplorer(String bUTTON_btnExplorer) {
		BUTTON_btnExplorer = bUTTON_btnExplorer;
	}

	public Text getTextProductName() {
		return textProductName;
	}

	public void setTextProductName(Text textProductName) {
		this.textProductName = textProductName;
	}

	public ICommand getC_textProductName() {
		return C_textProductName;
	}

	public void setC_textProductName(ICommand c_textProductName) {
		C_textProductName = c_textProductName;
	}

	public Text getTextLineDiameter() {
		return textLineDiameter;
	}

	public void setTextLineDiameter(Text textLineDiameter) {
		this.textLineDiameter = textLineDiameter;
	}

	public ICommand getC_textLineDiameter() {
		return C_textLineDiameter;
	}

	public void setC_textLineDiameter(ICommand c_textLineDiameter) {
		C_textLineDiameter = c_textLineDiameter;
	}

	public Text getTextCenterDiameter() {
		return textCenterDiameter;
	}

	public void setTextCenterDiameter(Text textCenterDiameter) {
		this.textCenterDiameter = textCenterDiameter;
	}

	public ICommand getC_textCenterDiameter() {
		return C_textCenterDiameter;
	}

	public void setC_textCenterDiameter(ICommand c_textCenterDiameter) {
		C_textCenterDiameter = c_textCenterDiameter;
	}

	public Text getTextInnerDiameter() {
		return textInnerDiameter;
	}

	public void setTextInnerDiameter(Text textInnerDiameter) {
		this.textInnerDiameter = textInnerDiameter;
	}

	public ICommand getC_textInnerDiameter() {
		return C_textInnerDiameter;
	}

	public void setC_textInnerDiameter(ICommand c_textInnerDiameter) {
		C_textInnerDiameter = c_textInnerDiameter;
	}

	public Text getTextOuterDiameter() {
		return textOuterDiameter;
	}

	public void setTextOuterDiameter(Text textOuterDiameter) {
		this.textOuterDiameter = textOuterDiameter;
	}

	public ICommand getC_textOuterDiameter() {
		return C_textOuterDiameter;
	}

	public void setC_textOuterDiameter(ICommand c_textOuterDiameter) {
		C_textOuterDiameter = c_textOuterDiameter;
	}

	public Text getTextUpperInnerDiameter() {
		return textUpperInnerDiameter;
	}

	public void setTextUpperInnerDiameter(Text textUpperInnerDiameter) {
		this.textUpperInnerDiameter = textUpperInnerDiameter;
	}

	public ICommand getC_textUpperInnerDiameter() {
		return C_textUpperInnerDiameter;
	}

	public void setC_textUpperInnerDiameter(ICommand c_textUpperInnerDiameter) {
		C_textUpperInnerDiameter = c_textUpperInnerDiameter;
	}

	public Text getTextLowerInnerDiameter() {
		return textLowerInnerDiameter;
	}

	public void setTextLowerInnerDiameter(Text textLowerInnerDiameter) {
		this.textLowerInnerDiameter = textLowerInnerDiameter;
	}

	public ICommand getC_textLowerInnerDiameter() {
		return C_textLowerInnerDiameter;
	}

	public void setC_textLowerInnerDiameter(ICommand c_textLowerInnerDiameter) {
		C_textLowerInnerDiameter = c_textLowerInnerDiameter;
	}

	public Text getTextTotalNumber() {
		return textTotalNumber;
	}

	public void setTextTotalNumber(Text textTotalNumber) {
		this.textTotalNumber = textTotalNumber;
	}

	public ICommand getC_textTotalNumber() {
		return C_textTotalNumber;
	}

	public void setC_textTotalNumber(ICommand c_textTotalNumber) {
		C_textTotalNumber = c_textTotalNumber;
	}

	public TableViewer getTableViewerCoilTable() {
		return tableViewerCoilTable;
	}

	public void setTableViewerCoilTable(TableViewer tableViewerCoilTable) {
		this.tableViewerCoilTable = tableViewerCoilTable;
	}

	public ICommand getC_tableViewerCoilTable() {
		return C_tableViewerCoilTable;
	}

	public void setC_tableViewerCoilTable(ICommand c_tableViewerCoilTable) {
		C_tableViewerCoilTable = c_tableViewerCoilTable;
	}

}
