package com.js.ens.coil.customWidget;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Item;


import com.js.ens.coil.core.Mediator;

public class TableViewerModifier implements ICellModifier {

	private Mediator med;
	private Viewer tableViewer;
	private TableData_Coil CoilObj;
	
	
	public TableViewerModifier(Viewer viewer){
		System.out.println("1");
		this.med = Mediator.getInstance();
		this.tableViewer = viewer;
	}
	
	@Override
	public boolean canModify(Object element, String property) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object getValue(Object element, String property) {
		// TODO Auto-generated method stub
		try{
			if(this.tableViewer == med.getTableViewerCoilTable()){
				CoilObj = (TableData_Coil) element;
				
				if(TableColumnLabel.COL_0.equals(property)){
					return CoilObj.getTheta();
				}else if(TableColumnLabel.COL_1.equals(property)) {
					return CoilObj.getRadius();
				}else if(TableColumnLabel.COL_2.equals(property)) {
					return CoilObj.getHeight();
				}
			}
		}catch (Exception e){
		}
		
		this.tableViewer.refresh();
		
		return null;
	}

	@Override
	public void modify(Object element, String property, Object value) {
		// TODO Auto-generated method stub
		try{
			if(element instanceof Item){
				element = ((Item) element).getData();
			}
			
			if(this.tableViewer == med.getTableViewerCoilTable()){
				CoilObj = (TableData_Coil) element;
				
				if(TableColumnLabel.COL_0.equals(property)){
					CoilObj.setTheta((String) value);
				}else if(TableColumnLabel.COL_1.equals(property)) {
					CoilObj.setRadius((String) value);
				}else if(TableColumnLabel.COL_2.equals(property)) {
					CoilObj.setHeight((String) value);
				}
			}
		}catch (Exception e){
			//log.error("Table Data Save Error");
		}
		
		this.tableViewer.refresh();
	}

}
