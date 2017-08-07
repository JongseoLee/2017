package com.js.ens.coil.customWidget;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


public class TableViewerLabelProvider_Coil extends LabelProvider implements
		ITableLabelProvider {

	public TableViewerLabelProvider_Coil() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		TableData_Coil obj = (TableData_Coil) element;
		
		switch(columnIndex){
		case 0:
			return obj.getTheta();
		case 1:
			return obj.getRadius();
		case 2:
			return obj.getHeight();
		}
		return "";
	}

}
