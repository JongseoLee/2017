package com.js.ens.coil.handler;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.js.ens.coil.core.Mediator;

public class HandlerComboViewer implements ISelectionChangedListener {
	private Mediator med = Mediator.getInstance();
	private ComboViewer comboViewer;
	
	public HandlerComboViewer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		try{
			comboViewer = (ComboViewer) event.getSource();
			if(comboViewer == med.getComboViewerSelectGraph()){
				med.getC_comboViewerSelectGraph().execute();
			}else if(comboViewer == med.getComboViewerSelectImage()){
				med.getC_comboViewerSelectImage().execute();
			}else if(comboViewer == med.getComboViewerSelectTableData()){
				med.getC_comboViewerSelectTableData().execute();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
}
