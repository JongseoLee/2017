package com.js.ens.coil;

import java.util.*;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public View() {
	}
	public static final String ID = "com.js.ens.coil.view";

	
	
	@Override
	public void createPartControl(Composite parent) {
		
	}


	@Override
	public void setFocus() {
		
	}
	
	private List<String> createInitialDataModel() {
		return Arrays.asList("One", "Two", "Three");
	}
}