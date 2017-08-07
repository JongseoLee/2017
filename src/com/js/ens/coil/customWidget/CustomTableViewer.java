package com.js.ens.coil.customWidget;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Table;

import com.js.ens.coil.core.Mediator;


public class CustomTableViewer implements ICommand {
	private Mediator med;
	private String widgetName;
	private TableViewer tableViewer;
	private Table table;
		
	public CustomTableViewer(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(widgetName.equals(Mediator.TABLEVIEWER_tableViewerCoilTable)){
			edit_tableViewerCoilTable();
			//System.out.println("Custom-table_coil table");
		}
	}
	
	public void setCustomWidget_tableViewerCoilTable(){
		this.tableViewer = med.getTableViewerCoilTable();
		this.table = this.tableViewer.getTable();
	}
	
	public void edit_tableViewerCoilTable(){
		//System.out.println("coil Table edit");
		CellEditor [] editor = new CellEditor[3];
		editor[0] = new TextCellEditor(this.table);
		editor[1] = new TextCellEditor(this.table);
		editor[2] = new TextCellEditor(this.table);
		
		this.tableViewer.setCellModifier(new TableViewerModifier(this.tableViewer));
		this.tableViewer.setCellEditors(editor);
	}
	
	
	
	
	
}
