package com.js.ens.coil.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.js.ens.coil.core.Mediator;

public class HandlerText implements Listener {
	private Mediator med = Mediator.getInstance();

	public HandlerText() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getTextCoilFilePath()){
			med.getC_textCoilFilePath().execute();
		}else if(event.widget == med.getTextProductName()){
			med.getC_textProductName().execute();
		}else if(event.widget == med.getTextLineDiameter()){
			med.getC_textLineDiameter().execute();
		}else if(event.widget == med.getTextCenterDiameter()){
			med.getC_textCenterDiameter().execute();
		}else if(event.widget == med.getTextInnerDiameter()){
			med.getC_textInnerDiameter().execute();
		}else if(event.widget == med.getTextOuterDiameter()){
			med.getC_textOuterDiameter().execute();
		}else if(event.widget == med.getTextUpperInnerDiameter()){
			med.getC_textUpperInnerDiameter().execute();
		}else if(event.widget == med.getTextLowerInnerDiameter()){
			med.getC_textLowerInnerDiameter().execute();
		}else if(event.widget == med.getTextTotalNumber()){
			med.getC_textTotalNumber().execute();
		}
	}

}
