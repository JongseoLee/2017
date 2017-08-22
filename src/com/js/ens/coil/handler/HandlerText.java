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
		}
		
		else if(event.widget == med.getTextProductName()){
			med.getC_textProductName().execute();
		}else if(event.widget == med.getTextWireDiameter()){
			med.getC_textWireDiameter().execute();
		}else if(event.widget == med.getTextCenterDiameter()){
			med.getC_textCenterDiameter().execute();
		}else if(event.widget == med.getTextInternalDiameter()){
			med.getC_textInternalDiameter().execute();
		}else if(event.widget == med.getTextExternalDiameter()){
			med.getC_textExternalDiameter().execute();
		}else if(event.widget == med.getTextUpperInnerDiameter()){
			med.getC_textUpperInnerDiameter().execute();
		}else if(event.widget == med.getTextLowerInnerDiameter()){
			med.getC_textLowerInnerDiameter().execute();
		}else if(event.widget == med.getTextTotalTurns()){
			med.getC_textTotalTurns().execute();
		}
		
		else if(event.widget == med.getTextHotSettingTemp()){
			med.getC_textHotSettingTemp().execute();
		}else if(event.widget == med.getTextColdSettingTemp()){
			med.getC_textColdSettingTemp().execute();
		}else if(event.widget == med.getTextHotSettingHeight()){
			med.getC_textHotSettingHeight().execute();
		}else if(event.widget == med.getTextColdSettingHeight()){
			med.getC_textColdSettingHeight().execute();
		}else if(event.widget == med.getTextSeatUInnerMargina()){
			med.getC_textSeatUInnerMargina().execute();
		}else if(event.widget == med.getTextSeatLInnerMargina()){
			med.getC_textSeatLInnerMargina().execute();
		}else if(event.widget == med.getTextSeatHeight()){
			med.getC_textSeatHeight().execute();
		}
		
		else if(event.widget == med.getTextInitialConditionerValue()){
			med.getC_textInitialConditionerValue().execute();
		}else if(event.widget == med.getTextInitialConditionerPath()){
			med.getC_textInitialConditionerPath().execute();
		}
		
		else if(event.widget == med.getTextMaterialDBPath()){
			med.getC_textMaterialDBPath().execute();
		}
		
		else if(event.widget == med.getTextRadiusTolerance()){
			med.getC_textRadiusTolerance().execute();
		}else if(event.widget == med.getTextHeightTolerance()){
			med.getC_textHeightTolerance().execute();
		}else if(event.widget == med.getTextMaximumIterationNumber()){
			med.getC_textMaximumIterationNumber().execute();
		}else if(event.widget == med.getTextLogEditor()){
			med.getC_textLogEditor().execute();
		}
		
		
	}

}
