package com.js.ens.coil.customWidget;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ComboViewerLabelProvider_SelectImage extends LabelProvider
		implements ILabelProvider {

	public ComboViewerLabelProvider_SelectImage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		ComboData_selectImage obj = (ComboData_selectImage) element;
		return obj.getName();
	}

}
