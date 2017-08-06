package com.js.ens.coil.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;

import com.js.ens.coil.core.MainController;



public class File_HandlerNew extends AbstractHandler implements IHandler {
	private MainController MC = MainController.getInstatnce();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		//MC.executeFileNew();
		return null;
	}

}
