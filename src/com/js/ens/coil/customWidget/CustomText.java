package com.js.ens.coil.customWidget;

import org.eclipse.swt.widgets.Text;

import com.js.ens.coil.core.MainController;
import com.js.ens.coil.core.Mediator;

public class CustomText implements ICommand {
	
	private Mediator med;
	private MainController MC;
	private String widgetName;
	private Text text;
	
	public CustomText(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstatnce();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(this.widgetName.equals(Mediator.TEXT_textCoilFilePath)){
			System.out.println("Text("+Mediator.TEXT_textCoilFilePath+")"+med.getTextCoilFilePath().getText());
		}
		
		else if(this.widgetName.equals(Mediator.TEXT_textProductName)){
			System.out.println("Text("+Mediator.TEXT_textProductName +")"+med.getTextProductName().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textLineDiameter)){
			System.out.println("Text("+Mediator.TEXT_textLineDiameter +")"+med.getTextLineDiameter().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textCenterDiameter)){
			System.out.println("Text("+Mediator.TEXT_textCenterDiameter +")"+med.getTextCenterDiameter().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textInnerDiameter)){
			System.out.println("Text("+Mediator.TEXT_textInnerDiameter +")"+med.getTextInnerDiameter().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textOuterDiameter)){
			System.out.println("Text("+Mediator.TEXT_textOuterDiameter +")"+med.getTextOuterDiameter().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textUpperInnerDiameter)){
			System.out.println("Text("+Mediator.TEXT_textUpperInnerDiameter +")"+med.getTextUpperInnerDiameter().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textLowerInnerDiameter)){
			System.out.println("Text("+Mediator.TEXT_textLowerInnerDiameter +")"+med.getTextLowerInnerDiameter().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textTotalNumber)){
			System.out.println("Text("+Mediator.TEXT_textTotalNumber +")"+med.getTextTotalNumber().getText());
		}
		
		else if(this.widgetName.equals(Mediator.TEXT_textHotSettingTemp)){
			System.out.println("Text("+Mediator.TEXT_textHotSettingTemp +")"+med.getTextHotSettingTemp().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textColdSettingTemp)){
			System.out.println("Text("+Mediator.TEXT_textColdSettingTemp +")"+med.getTextColdSettingTemp().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textHotSettingStrok)){
			System.out.println("Text("+Mediator.TEXT_textHotSettingStrok +")"+med.getTextHotSettingStrok().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textColdSettingStrok)){
			System.out.println("Text("+Mediator.TEXT_textColdSettingStrok +")"+med.getTextColdSettingStrok().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textSeatUInnerMargina)){
			System.out.println("Text("+Mediator.TEXT_textSeatUInnerMargina +")"+med.getTextSeatUInnerMargina().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textSeatLInnerMargina)){
			System.out.println("Text("+Mediator.TEXT_textSeatLInnerMargina +")"+med.getTextSeatLInnerMargina().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textSeatHeight)){
			System.out.println("Text("+Mediator.TEXT_textSeatHeight +")"+med.getTextSeatHeight().getText());
		}
		
		else if(this.widgetName.equals(Mediator.TEXT_textRadiusConditionerValue)){
			System.out.println("Text("+Mediator.TEXT_textRadiusConditionerValue +")"+med.getTextRadiusConditionerValue().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textRadiusConditionerPath)){
			System.out.println("Text("+Mediator.TEXT_textRadiusConditionerPath +")"+med.getTextRadiusConditionerPath().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textHeightConditionerValue)){
			System.out.println("Text("+Mediator.TEXT_textHeightConditionerValue +")"+med.getTextHeightConditionerValue().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textHeightConditionerPath)){
			System.out.println("Text("+Mediator.TEXT_textHeightConditionerPath +")"+med.getTextHeightConditionerPath().getText());
		}
		
		else if(this.widgetName.equals(Mediator.TEXT_textRadiusTolerance)){
			System.out.println("Text("+Mediator.TEXT_textRadiusTolerance +")"+med.getTextRadiusTolerance().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textHeightTolerance)){
			System.out.println("Text("+Mediator.TEXT_textHeightTolerance +")"+med.getTextHeightTolerance().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textMaximumIterationNumber)){
			System.out.println("Text("+Mediator.TEXT_textMaximumIterationNumber +")"+med.getTextMaximumIterationNumber().getText());
		}else if(this.widgetName.equals(Mediator.TEXT_textLogEditor)){
			System.out.println("Text("+Mediator.TEXT_textLogEditor +")"+med.getTextLogEditor().getText());
		}
		
		
		

	}
	

	////////////////////////////////////////////////
	////////////////////////////////////////////////
	//
	//
	public void setCustomWidget_textCoilFilePath(){
		this.text = med.getTextCoilFilePath();
	}
	public void setCustomWidget_textProductName(){
		this.text = med.getTextProductName();
	}
	public void setCustomWidget_textLineDiameter(){
		this.text = med.getTextLineDiameter();
	}
	public void setCustomWidget_textCenterDiameter(){
		this.text = med.getTextCenterDiameter();
	}
	public void setCustomWidget_textInnerDiameter(){
		this.text = med.getTextInnerDiameter();
	}
	public void setCustomWidget_textOuterDiameter(){
		this.text = med.getTextOuterDiameter();
	}
	public void setCustomWidget_textUpperInnerDiameter(){
		this.text = med.getTextUpperInnerDiameter();
	}
	public void setCustomWidget_textLowerInnerDiameter(){
		this.text = med.getTextLowerInnerDiameter();
	}
	public void setCustomWidget_textTotalNumber(){
		this.text = med.getTextTotalNumber();
	}
	
	public void setCustomWidget_textHotSettingTemp(){
		this.text = med.getTextHotSettingTemp();
	}
	public void setCustomWidget_textColdSettingTemp(){
		this.text = med.getTextColdSettingTemp();
	}
	public void setCustomWidget_textHotSettingStrok(){
		this.text = med.getTextHotSettingStrok();
	}
	public void setCustomWidget_textColdSettingStrok(){
		this.text = med.getTextColdSettingStrok();
	}
	public void setCustomWidget_textSeatUInnerMargina(){
		this.text = med.getTextSeatUInnerMargina();
	}
	public void setCustomWidget_textSeatLInnerMargina(){
		this.text = med.getTextSeatLInnerMargina();
	}
	public void setCustomWidget_textSeatHeight(){
		this.text = med.getTextSeatHeight();
	}
	
	public void setCustomWidget_textRadiusConditionerValue(){
		this.text =med.getTextRadiusConditionerValue();
	}
	public void setCustomWidget_textRadiusConditionerPath(){
		this.text =med.getTextRadiusConditionerPath();
	}
	public void setCustomWidget_textHeightConditionerValue(){
		this.text =med.getTextHeightConditionerValue();
	}
	public void setCustomWidget_textHeightConditionerPath(){
		this.text =med.getTextHeightConditionerPath();
	}

	public void setCustomWidget_textRadiusTolerance(){
		this.text = med.getTextRadiusTolerance();
	}
	public void setCustomWidget_textHeightTolerance(){
		this.text = med.getTextHeightTolerance();
	}public void setCustomWidget_textMaximumIterationNumber(){
		this.text = med.getTextMaximumIterationNumber();
	}public void setCustomWidget_textLogEditor(){
		this.text = med.getTextLogEditor();
	}
	
	
}
