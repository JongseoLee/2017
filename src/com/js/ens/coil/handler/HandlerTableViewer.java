package com.js.ens.coil.handler;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Table;

import com.js.ens.coil.core.Mediator;

public class HandlerTableViewer implements ISelectionChangedListener {
	
	private Mediator med = Mediator.getInstance();
	private TableViewer tableViewer;
	
	public HandlerTableViewer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		try{
			tableViewer = (TableViewer) event.getSource();
			
			if(tableViewer == med.getTableViewerCoilTable()){
				med.getC_tableViewerCoilTable().execute();
				//Table table = tableViewer.getTable();
				//System.out.println("Handler - Coil Table");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
