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
		}else if(this.widgetName.equals(Mediator.TEXT_textProductName)){
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

	}
	

	////////////////////////////////////////////////
	////////////////////////////////////////////////
	//
	//
	public void setCustomWidte_textCoilFilePath(){
		this.text = med.getTextCoilFilePath();
	}
	public void setCustomWidte_textProductName(){
		this.text = med.getTextProductName();
	}
	public void setCustomWidte_textLineDiameter(){
		this.text = med.getTextLineDiameter();
	}
	public void setCustomWidte_textCenterDiameter(){
		this.text = med.getTextCenterDiameter();
	}
	public void setCustomWidte_textInnerDiameter(){
		this.text = med.getTextInnerDiameter();
	}
	public void setCustomWidte_textOuterDiameter(){
		this.text = med.getTextOuterDiameter();
	}
	public void setCustomWidte_textUpperInnerDiameter(){
		this.text = med.getTextUpperInnerDiameter();
	}
	public void setCustomWidte_textLowerInnerDiameter(){
		this.text = med.getTextLowerInnerDiameter();
	}
	public void setCustomWidte_textTotalNumber(){
		this.text = med.getTextTotalNumber();
	}


	
	
}
