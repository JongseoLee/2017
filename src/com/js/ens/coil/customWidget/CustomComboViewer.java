package com.js.ens.coil.customWidget;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Combo;

import com.js.ens.coil.core.MainController;
import com.js.ens.coil.core.Mediator;

public class CustomComboViewer implements ICommand {
	private Mediator med;
	private MainController MC;
	private String widgetName;
	private ComboViewer comboViewer;
	private Combo combo;
	
	public CustomComboViewer(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.MC = MainController.getInstatnce();
		this.widgetName = widgetName;
		this.med = med;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(widgetName.equals(Mediator.COMBOVIEWER_comboViewerSelectGraph)){
			System.out.println("Click combo(" + Mediator.COMBOVIEWER_comboViewerSelectGraph +")"+med.getComboViewerSelectGraph().getCombo().getText());
			this.MC.Combo_selectGraph();
		}else if(widgetName.equals(Mediator.COMBOVIEWER_comboViewerSelectImage)){
			System.out.println("Click combo(" + Mediator.COMBOVIEWER_comboViewerSelectImage +")"+med.getComboViewerSelectImage().getCombo().getText());
			this.MC.Combo_selectImage();
		}else if(widgetName.equals(Mediator.COMBOVIEWER_comboViewerSelectTableData)){
			System.out.println("Click combo(" + Mediator.COMBOVIEWER_comboViewerSelectTableData +")"+med.getComboViewerSelectTableData().getCombo().getText());
			this.MC.Combo_selectTableData();
		}
	}
	
	public void setCustomWidget_comboViewerSelectGraph(){
		this.comboViewer = med.getComboViewerSelectGraph();
		this.combo = this.comboViewer.getCombo();
	}
	
	public void setCustomWidget_comboViewerSelectImage(){
		this.comboViewer = med.getComboViewerSelectImage();
		this.combo = this.comboViewer.getCombo();
	}
	
	public void setCustomWidget_comboViewerSelectTableData(){
		this.comboViewer = med.getComboViewerSelectTableData();
		this.combo = this.comboViewer.getCombo();
	}
}
