package com.js.ens.coil.customWidget;

import org.eclipse.swt.widgets.List;

import com.js.ens.coil.core.MainController;
import com.js.ens.coil.core.Mediator;

public class CustomList implements ICommand {
	private Mediator med;
	private MainController MC;
	private String widgetName;
	private List list;
	
	public CustomList(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstatnce();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(this.widgetName.equals(Mediator.LIST_listSelectedGraph)){
			System.out.println("Select list "+Mediator.LIST_listSelectedGraph);
			MC.List_SelectedGraph();
		}
	}
	
	public void setCustomWidget_listSelectedGraph(){
		this.list = med.getListSelectedGraph();
	}

}
