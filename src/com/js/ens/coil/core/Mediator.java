package com.js.ens.coil.core;


import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
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
	
	private Text textWireDiameter;
	public ICommand C_textWireDiameter;
	public static String TEXT_textWireDiameter = "textWireDiameter";
	
	private Text textCenterDiameter;
	public ICommand C_textCenterDiameter;
	public static String TEXT_textCenterDiameter = "textCenterDiameter";
	
	private Text textInternalDiameter;
	public ICommand C_textInternalDiameter;
	public static String TEXT_textInternalDiameter = "textInternalDiameter";
	
	private Text textExternalDiameter;
	public ICommand C_textExternalDiameter;
	public static String TEXT_textExternalDiameter = "textExternalDiameter";
	
	private Text textUpperInnerDiameter;
	public ICommand C_textUpperInnerDiameter;
	public static String TEXT_textUpperInnerDiameter = "textUpperInnerDiameter";
	
	private Text textLowerInnerDiameter;
	public ICommand C_textLowerInnerDiameter;
	public static String TEXT_textLowerInnerDiameter = "textLowerInnerDiameter";
	
	private Text textTotalTurns;
	public ICommand C_textTotalTurns;
	public static String TEXT_textTotalTurns = "textTotalTurns";
	
	private TableViewer tableViewerCoilTable;
	public ICommand C_tableViewerCoilTable;
	public static String TABLEVIEWER_tableViewerCoilTable = "tableViewerCoilTable";
	
	private Text textHotSettingTemp;
	public ICommand C_textHotSettingTemp;
	public static String TEXT_textHotSettingTemp = "textHotSettingTemp";
	
	private Text textColdSettingTemp;
	public ICommand C_textColdSettingTemp;
	public static String TEXT_textColdSettingTemp = "textColdSettingTemp";

	private Text textHotSettingHeight;
	public ICommand C_textHotSettingHeight;
	public static String TEXT_textHotSettingHeight = "textHotSettingHeight";

	private Text textColdSettingHeight;
	public ICommand C_textColdSettingHeight;
	public static String TEXT_textColdSettingHeight = "textColdSettingHeight";
		
	private Text textSeatUInnerMargina;
	public ICommand C_textSeatUInnerMargina;
	public static String TEXT_textSeatUInnerMargina = "textSeatUInnerMargina";
	
	private Text textSeatLInnerMargina;
	public ICommand C_textSeatLInnerMargina;
	public static String TEXT_textSeatLInnerMargina = "textSeatLInnerMargina";
	
	private Text textSeatHeight;
	public ICommand C_textSeatHeight;
	public static String TEXT_textSeatHeight = "textSeatHeight";
	
	/*
	private Button btnInitialConditionerConstant;
	public ICommand C_btnInitialConditionerConstant;
	public static String BUTTON_btnInitialConditionerConstant = "btnInitialConditionerConstant";
	
	private Button btnInitialConditionerFile;
	public ICommand C_btnInitialConditionerFile;
	public static String BUTTON_btnInitialConditionerFile = "btnInitialConditionerFile";
	
	private Text textInitialConditionerValue;
	public ICommand C_textInitialConditionerValue;
	public static String TEXT_textInitialConditionerValue = "textInitialConditionerValue";
	
	private Text textInitialConditionerPath;
	public ICommand C_textInitialConditionerPath;
	public static String TEXT_textInitialConditionerPath = "textInitialConditionerPath";
	
	private Button btnInitialConditionerExplorer;
	public ICommand C_btnInitialConditionerExplorer;
	public static String BUTTON_btnInitialConditionerExplorer = "btnInitialConditionerExplorer";
	// */
	
	private Button btnRadiusConditionerConstant;
 	public ICommand C_btnRadiusConditionerConstant;
 	public static String BUTTON_btnRadiusConditionerConstant = "btnRadiusConditionerConstant";
 	
 	private Button btnRadiusConditionerFile;
 	public ICommand C_btnRadiusConditionerFile;
 	public static String BUTTON_btnRadiusConditionerFile = "btnRadiusConditionerFile";
 	
 	private Text textRadiusConditionerValue;
 	public ICommand C_textRadiusConditionerValue;
 	public static String TEXT_textRadiusConditionerValue = "textRadiusConditionerValue";
 	
 	private Text textRadiusConditionerPath; 
 	public ICommand C_textRadiusConditionerPath;
 	public static String TEXT_textRadiusConditionerPath = "textRadiusConditionerPath";
 	
 	private Button btnRadiusConditionerExplorer;
 	public ICommand C_btnRadiusConditionerExplorer;
 	public static String BUTTON_btnRadiusConditionerExplorer = "btnRadiusConditionerExplorer";
 	
 	
 	private Button btnHeightConditionerConstant;
 	public ICommand C_btnHeightConditionerConstant;
 	public static String BUTTON_btnHeightConditionerConstant = "btnHeightConditionerConstant";
 	
 	private Button btnHeightConditionerFile;
 	public ICommand C_btnHeightConditionerFile;
 	public static String BUTTON_btnHeightConditionerFile = "btnHeightConditionerFile";
 	
 	private Text textHeightConditionerValue;
 	public ICommand C_textHeightConditionerValue;
 	public static String TEXT_textHeightConditionerValue = "textHeightConditionerValue";
 	
 	private Text textHeightConditionerPath; 
 	public ICommand C_textHeightConditionerPath;
 	public static String TEXT_textHeightConditionerPath = "textHeightConditionerPath";
 	
 	private Button btnHeightConditionerExplorer;
 	public ICommand C_btnHeightConditionerExplorer;
 	public static String BUTTON_btnHeightConditionerExplorer = "btnHeightConditionerExplorer";
	
	private Text textMaterialDBPath;
	public ICommand C_textMaterialDBPath;
	public static String TEXT_textMaterialDBPath = "textMaterialDBPath";
	
	private Button btnMaterialDBExplorer;
	public ICommand C_btnMaterialDBExplorer;
	public static String BUTTON_btnMaterialDBExplorer = "btnMaterialDBExplorer";
	
	//////////////////////////////////////////////////////////////////////////
	// step2
	private Text textRadiusTolerance;
	public ICommand C_textRadiusTolerance;
	public static String TEXT_textRadiusTolerance = "textRadiusTolerance";
	
	private Text textHeightTolerance;
	public ICommand C_textHeightTolerance;
	public static String TEXT_textHeightTolerance = "textHeightTolerance";
	
	private Text textMaximumIterationNumber;
	public ICommand C_textMaximumIterationNumber;
	public static String TEXT_textMaximumIterationNumber = "textMaximumIterationNumber";
	
	private Label lblSimulationStatus;
	private Label lblIterationNumber;
	
	private Button btnStartSimulation;
	public ICommand C_btnStartSimulation;
	public static String BUTTON_btnStartSimulation = "btnStartSimulation";
	
	private ProgressBar progressBarSimulationIteration;
	public ICommand C_progressBarSimulationIteration;
	public static String PROGRESSBAR_progressBarSimulationIteration = "progressBarSimulationIteration";
	
	private Button btnReadLog;
	public ICommand C_btnReadLog;
	public static String BUTTON_btnReadLog = "btnReadLog";
	
	private Text textLogEditor;
	public ICommand C_textLogEditor;
	public static String TEXT_textLogEditor = "textLogEditor";
	
	//////////////////////////////////////////////////////////////////////////
	// step3
	private ComboViewer comboViewerSelectGraph;
	public ICommand C_comboViewerSelectGraph;
	public static String COMBOVIEWER_comboViewerSelectGraph = "comboViewerSelectGraph";
	
	private Button btnShowGraphWindow;
	public ICommand C_btnShowGraphWindow;
	public static String BUTTON_btnShowGraphWindow = "btnShowGraphWindow";
	
	private ComboViewer comboViewerSelectImage;
	public ICommand C_comboViewerSelectImage;
	public static String COMBOVIEWER_comboViewerSelectImage = "comboViewerSelectImage";
	
	private Button btnShowImageWindow;
	public ICommand C_btnShowImageWindow;
	public static String BUTTON_btnShowImageWindow = "btnShowImageWindow";
	
	private ComboViewer comboViewerSelectTableData;
	public ICommand C_comboViewerSelectTableData;
	public static String COMBOVIEWER_comboViewerSelectTableData = "comboViewerSelectTableData";
	
	private Button btnShowTableData;
	public ICommand C_btnShowTableData;
	public static String BUTTON_btnShowTableData = "btnShowTableData";
	
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

	/*
	public static String getBUTTON_btnExplorer() {
		return BUTTON_btnExplorer;
	}

	public static void setBUTTON_btnExplorer(String bUTTON_btnExplorer) {
		BUTTON_btnExplorer = bUTTON_btnExplorer;
	}
	//*/
	 
	 
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

	public Text getTextWireDiameter() {
		return textWireDiameter;
	}

	public void setTextWireDiameter(Text textWireDiameter) {
		this.textWireDiameter = textWireDiameter;
	}

	public ICommand getC_textWireDiameter() {
		return C_textWireDiameter;
	}

	public void setC_textWireDiameter(ICommand c_textWireDiameter) {
		C_textWireDiameter = c_textWireDiameter;
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

	public Text getTextInternalDiameter() {
		return textInternalDiameter;
	}

	public void setTextInternalDiameter(Text textInternalDiameter) {
		this.textInternalDiameter = textInternalDiameter;
	}

	public ICommand getC_textInternalDiameter() {
		return C_textInternalDiameter;
	}

	public void setC_textInternalDiameter(ICommand c_textInternalDiameter) {
		C_textInternalDiameter = c_textInternalDiameter;
	}

	public Text getTextExternalDiameter() {
		return textExternalDiameter;
	}

	public void setTextExternalDiameter(Text textExternalDiameter) {
		this.textExternalDiameter = textExternalDiameter;
	}

	public ICommand getC_textExternalDiameter() {
		return C_textExternalDiameter;
	}

	public void setC_textExternalDiameter(ICommand c_textExternalDiameter) {
		C_textExternalDiameter = c_textExternalDiameter;
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

	public Text getTextTotalTurns() {
		return textTotalTurns;
	}

	public void setTextTotalTurns(Text textTotalTurns) {
		this.textTotalTurns = textTotalTurns;
	}

	public ICommand getC_textTotalTurns() {
		return C_textTotalTurns;
	}

	public void setC_textTotalTurns(ICommand c_textTotalTurns) {
		C_textTotalTurns = c_textTotalTurns;
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

	public Text getTextHotSettingTemp() {
		return textHotSettingTemp;
	}

	public void setTextHotSettingTemp(Text textHotSettingTemp) {
		this.textHotSettingTemp = textHotSettingTemp;
	}

	public ICommand getC_textHotSettingTemp() {
		return C_textHotSettingTemp;
	}

	public void setC_textHotSettingTemp(ICommand c_textHotSettingTemp) {
		C_textHotSettingTemp = c_textHotSettingTemp;
	}

	public Text getTextColdSettingTemp() {
		return textColdSettingTemp;
	}

	public void setTextColdSettingTemp(Text textColdSettingTemp) {
		this.textColdSettingTemp = textColdSettingTemp;
	}

	public ICommand getC_textColdSettingTemp() {
		return C_textColdSettingTemp;
	}

	public void setC_textColdSettingTemp(ICommand c_textColdSettingTemp) {
		C_textColdSettingTemp = c_textColdSettingTemp;
	}

	public Text getTextHotSettingHeight() {
		return textHotSettingHeight;
	}

	public void setTextHotSettingHeight(Text textHotSettingHeight) {
		this.textHotSettingHeight = textHotSettingHeight;
	}

	public ICommand getC_textHotSettingHeight() {
		return C_textHotSettingHeight;
	}

	public void setC_textHotSettingHeight(ICommand c_textHotSettingHeight) {
		C_textHotSettingHeight = c_textHotSettingHeight;
	}

	public Text getTextColdSettingHeight() {
		return textColdSettingHeight;
	}

	public void setTextColdSettingHeight(Text textColdSettingHeight) {
		this.textColdSettingHeight = textColdSettingHeight;
	}

	public ICommand getC_textColdSettingHeight() {
		return C_textColdSettingHeight;
	}

	public void setC_textColdSettingHeight(ICommand c_textColdSettingHeight) {
		C_textColdSettingHeight = c_textColdSettingHeight;
	}

	public Text getTextSeatUInnerMargina() {
		return textSeatUInnerMargina;
	}

	public void setTextSeatUInnerMargina(Text textSeatUInnerMargina) {
		this.textSeatUInnerMargina = textSeatUInnerMargina;
	}

	public ICommand getC_textSeatUInnerMargina() {
		return C_textSeatUInnerMargina;
	}

	public void setC_textSeatUInnerMargina(ICommand c_textSeatUInnerMargina) {
		C_textSeatUInnerMargina = c_textSeatUInnerMargina;
	}

	public Text getTextSeatLInnerMargina() {
		return textSeatLInnerMargina;
	}

	public void setTextSeatLInnerMargina(Text textSeatLInnerMargina) {
		this.textSeatLInnerMargina = textSeatLInnerMargina;
	}

	public ICommand getC_textSeatLInnerMargina() {
		return C_textSeatLInnerMargina;
	}

	public void setC_textSeatLInnerMargina(ICommand c_textSeatLInnerMargina) {
		C_textSeatLInnerMargina = c_textSeatLInnerMargina;
	}

	public Text getTextSeatHeight() {
		return textSeatHeight;
	}

	public void setTextSeatHeight(Text textSeatHeight) {
		this.textSeatHeight = textSeatHeight;
	}

	public ICommand getC_textSeatHeight() {
		return C_textSeatHeight;
	}

	public void setC_textSeatHeight(ICommand c_textSeatHeight) {
		C_textSeatHeight = c_textSeatHeight;
	}

	public Button getBtnRadiusConditionerConstant() {
		return btnRadiusConditionerConstant;
	}

	public void setBtnRadiusConditionerConstant(Button btnRadiusConditionerConstant) {
		this.btnRadiusConditionerConstant = btnRadiusConditionerConstant;
	}

	public ICommand getC_btnRadiusConditionerConstant() {
		return C_btnRadiusConditionerConstant;
	}

	public void setC_btnRadiusConditionerConstant(ICommand c_btnRadiusConditionerConstant) {
		C_btnRadiusConditionerConstant = c_btnRadiusConditionerConstant;
	}

	public Button getBtnRadiusConditionerFile() {
		return btnRadiusConditionerFile;
	}

	public void setBtnRadiusConditionerFile(Button btnRadiusConditionerFile) {
		this.btnRadiusConditionerFile = btnRadiusConditionerFile;
	}

	public ICommand getC_btnRadiusConditionerFile() {
		return C_btnRadiusConditionerFile;
	}

	public void setC_btnRadiusConditionerFile(ICommand c_btnRadiusConditionerFile) {
		C_btnRadiusConditionerFile = c_btnRadiusConditionerFile;
	}

	public Text getTextRadiusConditionerValue() {
		return textRadiusConditionerValue;
	}

	public void setTextRadiusConditionerValue(Text textRadiusConditionerValue) {
		this.textRadiusConditionerValue = textRadiusConditionerValue;
	}

	public ICommand getC_textRadiusConditionerValue() {
		return C_textRadiusConditionerValue;
	}

	public void setC_textRadiusConditionerValue(ICommand c_textRadiusConditionerValue) {
		C_textRadiusConditionerValue = c_textRadiusConditionerValue;
	}

	public Text getTextRadiusConditionerPath() {
		return textRadiusConditionerPath;
	}

	public void setTextRadiusConditionerPath(Text textRadiusConditionerPath) {
		this.textRadiusConditionerPath = textRadiusConditionerPath;
	}

	public ICommand getC_textRadiusConditionerPath() {
		return C_textRadiusConditionerPath;
	}

	public void setC_textRadiusConditionerPath(ICommand c_textRadiusConditionerPath) {
		C_textRadiusConditionerPath = c_textRadiusConditionerPath;
	}

	public Button getBtnRadiusConditionerExplorer() {
		return btnRadiusConditionerExplorer;
	}

	public void setBtnRadiusConditionerExplorer(Button btnRadiusConditionerExplorer) {
		this.btnRadiusConditionerExplorer = btnRadiusConditionerExplorer;
	}

	public ICommand getC_btnRadiusConditionerExplorer() {
		return C_btnRadiusConditionerExplorer;
	}

	public void setC_btnRadiusConditionerExplorer(ICommand c_btnRadiusConditionerExplorer) {
		C_btnRadiusConditionerExplorer = c_btnRadiusConditionerExplorer;
	}

	public Button getBtnHeightConditionerConstant() {
		return btnHeightConditionerConstant;
	}

	public void setBtnHeightConditionerConstant(Button btnHeightConditionerConstant) {
		this.btnHeightConditionerConstant = btnHeightConditionerConstant;
	}

	public ICommand getC_btnHeightConditionerConstant() {
		return C_btnHeightConditionerConstant;
	}

	public void setC_btnHeightConditionerConstant(ICommand c_btnHeightConditionerConstant) {
		C_btnHeightConditionerConstant = c_btnHeightConditionerConstant;
	}

	public Button getBtnHeightConditionerFile() {
		return btnHeightConditionerFile;
	}

	public void setBtnHeightConditionerFile(Button btnHeightConditionerFile) {
		this.btnHeightConditionerFile = btnHeightConditionerFile;
	}

	public ICommand getC_btnHeightConditionerFile() {
		return C_btnHeightConditionerFile;
	}

	public void setC_btnHeightConditionerFile(ICommand c_btnHeightConditionerFile) {
		C_btnHeightConditionerFile = c_btnHeightConditionerFile;
	}

	public Text getTextHeightConditionerValue() {
		return textHeightConditionerValue;
	}

	public void setTextHeightConditionerValue(Text textHeightConditionerValue) {
		this.textHeightConditionerValue = textHeightConditionerValue;
	}

	public ICommand getC_textHeightConditionerValue() {
		return C_textHeightConditionerValue;
	}

	public void setC_textHeightConditionerValue(ICommand c_textHeightConditionerValue) {
		C_textHeightConditionerValue = c_textHeightConditionerValue;
	}

	public Text getTextHeightConditionerPath() {
		return textHeightConditionerPath;
	}

	public void setTextHeightConditionerPath(Text textHeightConditionerPath) {
		this.textHeightConditionerPath = textHeightConditionerPath;
	}

	public ICommand getC_textHeightConditionerPath() {
		return C_textHeightConditionerPath;
	}

	public void setC_textHeightConditionerPath(ICommand c_textHeightConditionerPath) {
		C_textHeightConditionerPath = c_textHeightConditionerPath;
	}

	public Button getBtnHeightConditionerExplorer() {
		return btnHeightConditionerExplorer;
	}

	public void setBtnHeightConditionerExplorer(Button btnHeightConditionerExplorer) {
		this.btnHeightConditionerExplorer = btnHeightConditionerExplorer;
	}

	public ICommand getC_btnHeightConditionerExplorer() {
		return C_btnHeightConditionerExplorer;
	}

	public void setC_btnHeightConditionerExplorer(ICommand c_btnHeightConditionerExplorer) {
		C_btnHeightConditionerExplorer = c_btnHeightConditionerExplorer;
	}
	/*
	public Button getBtnInitialConditionerConstant() {
		return btnInitialConditionerConstant;
	}

	public void setBtnInitialConditionerConstant(
			Button btnInitialConditionerConstant) {
		this.btnInitialConditionerConstant = btnInitialConditionerConstant;
	}

	public ICommand getC_btnInitialConditionerConstant() {
		return C_btnInitialConditionerConstant;
	}

	public void setC_btnInitialConditionerConstant(
			ICommand c_btnInitialConditionerConstant) {
		C_btnInitialConditionerConstant = c_btnInitialConditionerConstant;
	}

	public Button getBtnInitialConditionerFile() {
		return btnInitialConditionerFile;
	}

	public void setBtnInitialConditionerFile(Button btnInitialConditionerFile) {
		this.btnInitialConditionerFile = btnInitialConditionerFile;
	}

	public ICommand getC_btnInitialConditionerFile() {
		return C_btnInitialConditionerFile;
	}

	public void setC_btnInitialConditionerFile(ICommand c_btnInitialConditionerFile) {
		C_btnInitialConditionerFile = c_btnInitialConditionerFile;
	}
	
	public Text getTextInitialConditionerValue() {
		return textInitialConditionerValue;
	}

	public void setTextInitialConditionerValue(Text textInitialConditionerValue) {
		this.textInitialConditionerValue = textInitialConditionerValue;
	}

	public ICommand getC_textInitialConditionerValue() {
		return C_textInitialConditionerValue;
	}

	public void setC_textInitialConditionerValue(
			ICommand c_textInitialConditionerValue) {
		C_textInitialConditionerValue = c_textInitialConditionerValue;
	}

	public Text getTextInitialConditionerPath() {
		return textInitialConditionerPath;
	}

	public void setTextInitialConditionerPath(Text textInitialConditionerPath) {
		this.textInitialConditionerPath = textInitialConditionerPath;
	}

	public ICommand getC_textInitialConditionerPath() {
		return C_textInitialConditionerPath;
	}

	public void setC_textInitialConditionerPath(
			ICommand c_textInitialConditionerPath) {
		C_textInitialConditionerPath = c_textInitialConditionerPath;
	}

	public Button getBtnInitialConditionerExplorer() {
		return btnInitialConditionerExplorer;
	}

	public void setBtnInitialConditionerExplorer(
			Button btnInitialConditionerExplorer) {
		this.btnInitialConditionerExplorer = btnInitialConditionerExplorer;
	}

	public ICommand getC_btnInitialConditionerExplorer() {
		return C_btnInitialConditionerExplorer;
	}

	public void setC_btnInitialConditionerExplorer(
			ICommand c_btnInitialConditionerExplorer) {
		C_btnInitialConditionerExplorer = c_btnInitialConditionerExplorer;
	}
	// */
	public Text getTextMaterialDBPath() {
		return textMaterialDBPath;
	}

	public void setTextMaterialDBPath(Text textMaterialDBPath) {
		this.textMaterialDBPath = textMaterialDBPath;
	}

	public ICommand getC_textMaterialDBPath() {
		return C_textMaterialDBPath;
	}

	public void setC_textMaterialDBPath(ICommand c_textMaterialDBPath) {
		C_textMaterialDBPath = c_textMaterialDBPath;
	}

	public Button getBtnMaterialDBExplorer() {
		return btnMaterialDBExplorer;
	}

	public void setBtnMaterialDBExplorer(Button btnMaterialDBExplorer) {
		this.btnMaterialDBExplorer = btnMaterialDBExplorer;
	}

	public ICommand getC_btnMaterialDBExplorer() {
		return C_btnMaterialDBExplorer;
	}

	public void setC_btnMaterialDBExplorer(ICommand c_btnMaterialDBExplorer) {
		C_btnMaterialDBExplorer = c_btnMaterialDBExplorer;
	}

	public Text getTextRadiusTolerance() {
		return textRadiusTolerance;
	}

	public void setTextRadiusTolerance(Text textRadiusTolerance) {
		this.textRadiusTolerance = textRadiusTolerance;
	}

	public ICommand getC_textRadiusTolerance() {
		return C_textRadiusTolerance;
	}

	public void setC_textRadiusTolerance(ICommand c_textRadiusTolerance) {
		C_textRadiusTolerance = c_textRadiusTolerance;
	}

	public Text getTextHeightTolerance() {
		return textHeightTolerance;
	}

	public void setTextHeightTolerance(Text textHeightTolerance) {
		this.textHeightTolerance = textHeightTolerance;
	}

	public ICommand getC_textHeightTolerance() {
		return C_textHeightTolerance;
	}

	public void setC_textHeightTolerance(ICommand c_textHeightTolerance) {
		C_textHeightTolerance = c_textHeightTolerance;
	}

	public Text getTextMaximumIterationNumber() {
		return textMaximumIterationNumber;
	}

	public void setTextMaximumIterationNumber(Text textMaximumIterationNumber) {
		this.textMaximumIterationNumber = textMaximumIterationNumber;
	}

	public ICommand getC_textMaximumIterationNumber() {
		return C_textMaximumIterationNumber;
	}

	public void setC_textMaximumIterationNumber(
			ICommand c_textMaximumIterationNumber) {
		C_textMaximumIterationNumber = c_textMaximumIterationNumber;
	}

	public Label getLblSimulationStatus() {
		return lblSimulationStatus;
	}

	public void setLblSimulationStatus(Label lblSimulationStatus) {
		this.lblSimulationStatus = lblSimulationStatus;
	}

	public Label getLblIterationNumber() {
		return lblIterationNumber;
	}

	public void setLblIterationNumber(Label lblIterationNumber) {
		this.lblIterationNumber = lblIterationNumber;
	}

	public Button getBtnStartSimulation() {
		return btnStartSimulation;
	}

	public void setBtnStartSimulation(Button btnStartSimulation) {
		this.btnStartSimulation = btnStartSimulation;
	}

	public ICommand getC_btnStartSimulation() {
		return C_btnStartSimulation;
	}

	public void setC_btnStartSimulation(ICommand c_btnStartSimulation) {
		C_btnStartSimulation = c_btnStartSimulation;
	}

	public ProgressBar getProgressBarSimulationIteration() {
		return progressBarSimulationIteration;
	}

	public void setProgressBarSimulationIteration(
			ProgressBar progressBarSimulationIteration) {
		this.progressBarSimulationIteration = progressBarSimulationIteration;
	}

	public ICommand getC_progressBarSimulationIteration() {
		return C_progressBarSimulationIteration;
	}

	public void setC_progressBarSimulationIteration(
			ICommand c_progressBarSimulationIteration) {
		C_progressBarSimulationIteration = c_progressBarSimulationIteration;
	}

	public Button getBtnReadLog() {
		return btnReadLog;
	}

	public void setBtnReadLog(Button btnReadLog) {
		this.btnReadLog = btnReadLog;
	}

	public ICommand getC_btnReadLog() {
		return C_btnReadLog;
	}

	public void setC_btnReadLog(ICommand c_btnReadLog) {
		C_btnReadLog = c_btnReadLog;
	}

	public Text getTextLogEditor() {
		return textLogEditor;
	}

	public void setTextLogEditor(Text textLogEditor) {
		this.textLogEditor = textLogEditor;
	}

	public ICommand getC_textLogEditor() {
		return C_textLogEditor;
	}

	public void setC_textLogEditor(ICommand c_textLogEditor) {
		C_textLogEditor = c_textLogEditor;
	}

	public ComboViewer getComboViewerSelectGraph() {
		return comboViewerSelectGraph;
	}

	public void setComboViewerSelectGraph(ComboViewer comboViewerSelectGraph) {
		this.comboViewerSelectGraph = comboViewerSelectGraph;
	}

	public ICommand getC_comboViewerSelectGraph() {
		return C_comboViewerSelectGraph;
	}

	public void setC_comboViewerSelectGraph(ICommand c_comboViewerSelectGraph) {
		C_comboViewerSelectGraph = c_comboViewerSelectGraph;
	}

	public Button getBtnShowGraphWindow() {
		return btnShowGraphWindow;
	}

	public void setBtnShowGraphWindow(Button btnShowGraphWindow) {
		this.btnShowGraphWindow = btnShowGraphWindow;
	}

	public ICommand getC_btnShowGraphWindow() {
		return C_btnShowGraphWindow;
	}

	public void setC_btnShowGraphWindow(ICommand c_btnShowGraphWindow) {
		C_btnShowGraphWindow = c_btnShowGraphWindow;
	}

	public ComboViewer getComboViewerSelectImage() {
		return comboViewerSelectImage;
	}

	public void setComboViewerSelectImage(ComboViewer comboViewerSelectImage) {
		this.comboViewerSelectImage = comboViewerSelectImage;
	}

	public ICommand getC_comboViewerSelectImage() {
		return C_comboViewerSelectImage;
	}

	public void setC_comboViewerSelectImage(ICommand c_comboViewerSelectImage) {
		C_comboViewerSelectImage = c_comboViewerSelectImage;
	}

	public Button getBtnShowImageWindow() {
		return btnShowImageWindow;
	}

	public void setBtnShowImageWindow(Button btnShowImageWindow) {
		this.btnShowImageWindow = btnShowImageWindow;
	}

	public ICommand getC_btnShowImageWindow() {
		return C_btnShowImageWindow;
	}

	public void setC_btnShowImageWindow(ICommand c_btnShowImageWindow) {
		C_btnShowImageWindow = c_btnShowImageWindow;
	}

	public ComboViewer getComboViewerSelectTableData() {
		return comboViewerSelectTableData;
	}

	public void setComboViewerSelectTableData(ComboViewer comboViewerSelectTableData) {
		this.comboViewerSelectTableData = comboViewerSelectTableData;
	}

	public ICommand getC_comboViewerSelectTableData() {
		return C_comboViewerSelectTableData;
	}

	public void setC_comboViewerSelectTableData(
			ICommand c_comboViewerSelectTableData) {
		C_comboViewerSelectTableData = c_comboViewerSelectTableData;
	}

	public Button getBtnShowTableData() {
		return btnShowTableData;
	}

	public void setBtnShowTableData(Button btnShowTableData) {
		this.btnShowTableData = btnShowTableData;
	}

	public ICommand getC_btnShowTableData() {
		return C_btnShowTableData;
	}

	public void setC_btnShowTableData(ICommand c_btnShowTableData) {
		C_btnShowTableData = c_btnShowTableData;
	}

}
