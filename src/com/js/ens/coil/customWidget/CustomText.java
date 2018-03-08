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
			MC.Text_Modify_CoilFilePath();
		}
		
		else if(this.widgetName.equals(Mediator.TEXT_textProductName)){
			System.out.println("Text("+Mediator.TEXT_textProductName +")"+med.getTextProductName().getText());
			MC.Text_Modify_ProductName();
		}else if(this.widgetName.equals(Mediator.TEXT_textWireDiameter)){
			System.out.println("Text("+Mediator.TEXT_textWireDiameter +")"+med.getTextWireDiameter().getText());
			MC.Text_Modify_WireDiameter();
		}else if(this.widgetName.equals(Mediator.TEXT_textCenterDiameter)){
			System.out.println("Text("+Mediator.TEXT_textCenterDiameter +")"+med.getTextCenterDiameter().getText());
			MC.Text_Modify_CenterDiameter();
		}else if(this.widgetName.equals(Mediator.TEXT_textInternalDiameter)){
			System.out.println("Text("+Mediator.TEXT_textInternalDiameter +")"+med.getTextInternalDiameter().getText());
			MC.Text_Modify_InternalDiameter();
		}else if(this.widgetName.equals(Mediator.TEXT_textExternalDiameter)){
			System.out.println("Text("+Mediator.TEXT_textExternalDiameter +")"+med.getTextExternalDiameter().getText());
			MC.Text_Modify_ExternalDiameter();
		}else if(this.widgetName.equals(Mediator.TEXT_textUpperInnerDiameter)){
			System.out.println("Text("+Mediator.TEXT_textUpperInnerDiameter +")"+med.getTextUpperInnerDiameter().getText());
			MC.Text_Modify_UpperInnerDiameter();
		}else if(this.widgetName.equals(Mediator.TEXT_textLowerInnerDiameter)){
			System.out.println("Text("+Mediator.TEXT_textLowerInnerDiameter +")"+med.getTextLowerInnerDiameter().getText());
			MC.Text_Modify_LowerInnerDiameter();
		}else if(this.widgetName.equals(Mediator.TEXT_textTotalTurns)){
			System.out.println("Text("+Mediator.TEXT_textTotalTurns +")"+med.getTextTotalTurns().getText());
			MC.Text_Modify_TotalTurns();
		}
		
		else if(this.widgetName.equals(Mediator.TEXT_textHotSettingTemp)){
			System.out.println("Text("+Mediator.TEXT_textHotSettingTemp +")"+med.getTextHotSettingTemp().getText());
			MC.Text_Modify_HotSettingTemp();
		}else if(this.widgetName.equals(Mediator.TEXT_textColdSettingTemp)){
			System.out.println("Text("+Mediator.TEXT_textColdSettingTemp +")"+med.getTextColdSettingTemp().getText());
			MC.Text_Modify_ColdSettingTemp();
		}else if(this.widgetName.equals(Mediator.TEXT_textHotSettingHeight)){
			System.out.println("Text("+Mediator.TEXT_textHotSettingHeight +")"+med.getTextHotSettingHeight().getText());
			MC.Text_Modify_HotSettingHeight();
		}else if(this.widgetName.equals(Mediator.TEXT_textColdSettingHeight)){
			System.out.println("Text("+Mediator.TEXT_textColdSettingHeight +")"+med.getTextColdSettingHeight().getText());
			MC.Text_Modify_ColdSettingHeight();
		}else if(this.widgetName.equals(Mediator.TEXT_textSeatUInnerMargina)){
			System.out.println("Text("+Mediator.TEXT_textSeatUInnerMargina +")"+med.getTextSeatUInnerMargina().getText());
			MC.Text_Modify_SeatUInnerMargina();
		}else if(this.widgetName.equals(Mediator.TEXT_textSeatLInnerMargina)){
			System.out.println("Text("+Mediator.TEXT_textSeatLInnerMargina +")"+med.getTextSeatLInnerMargina().getText());
			MC.Text_Modify_SeatLInnerMargina();
		}else if(this.widgetName.equals(Mediator.TEXT_textSeatHeight)){
			System.out.println("Text("+Mediator.TEXT_textSeatHeight +")"+med.getTextSeatHeight().getText());
			MC.Text_Modify_SeatHeight();
		}else if(this.widgetName.equals(Mediator.TEXT_textSeatUStepRotationHeight)){
			System.out.println("Text("+Mediator.TEXT_textSeatUStepRotationHeight+")"+med.getTextSeatUStepRotationHeight().getText());
			MC.Text_Modify_SeatUStepRotationHeight();
		}else if(this.widgetName.equals(Mediator.TEXT_textSeatLStepRotationHeight)){
			System.out.println("Text("+Mediator.TEXT_textSeatLStepRotationHeight+")"+med.getTextSeatLStepRotationHeight().getText());
			MC.Text_Modify_SeatLStepRotationHeight();
		}else if(this.widgetName.equals(Mediator.TEXT_textSeatURotationAngle)){
			System.out.println("Text("+Mediator.TEXT_textSeatURotationAngle+")"+med.getTextSeatURotationAngle().getText());
			MC.Text_Modify_SeatURotationAngle();
		}else if(this.widgetName.equals(Mediator.TEXT_textSeatLRotationAngle)){
			System.out.println("Text("+Mediator.TEXT_textSeatLRotationAngle+")"+med.getTextSeatLRotationAngle().getText());
			MC.Text_Modify_SeatLRotationAngle();
		}
		
		
		else if(this.widgetName.equals(Mediator.TEXT_textRadiusConditionerValue)){
			System.out.println("Text("+Mediator.TEXT_textRadiusConditionerValue +")"+med.getTextRadiusConditionerValue().getText());
			MC.Text_Modify_RadiusConditionerValue();
		}else if(this.widgetName.equals(Mediator.TEXT_textHeightConditionerValue)){
			System.out.println("Text("+Mediator.TEXT_textHeightConditionerValue +")"+med.getTextHeightConditionerValue().getText());
			MC.Text_Modify_HeightConditionerValue();
		}
		else if(this.widgetName.equals(Mediator.TEXT_textRadiusConditionerPath)){
			System.out.println("Text("+Mediator.TEXT_textRadiusConditionerPath +")"+med.getTextRadiusConditionerPath().getText());
			MC.Text_Modify_RadiusConditionerValue();
		}else if(this.widgetName.equals(Mediator.TEXT_textHeightConditionerPath)){
			System.out.println("Text("+Mediator.TEXT_textHeightConditionerPath +")"+med.getTextHeightConditionerPath().getText());
			MC.Text_Modify_HeightConditionerValue();
		}
		
		else if(this.widgetName.equals(Mediator.TEXT_textMaterialDBPath)){
			System.out.println("Text("+Mediator.TEXT_textMaterialDBPath +")"+med.getTextMaterialDBPath().getText());
			MC.Text_Modify_MaterialDBPath();
		}
		
		else if(this.widgetName.equals(Mediator.TEXT_textParallelCpuNumber)){
			System.out.println("Text("+Mediator.TEXT_textParallelCpuNumber+")"+med.getTextParallelCpuNumber().getText());
			MC.Text_Modify_ParallelCpuNmber();
		}
		
		else if(this.widgetName.equals(Mediator.TEXT_textFormedCoilDataInterp)){
			System.out.println("Text("+Mediator.TEXT_textFormedCoilDataInterp+")"+med.getTextFormedCoilDataInterp().getText());
			MC.Text_Modify_FormedCoildDataInterp();
		}
			
		
		
		else if(this.widgetName.equals(Mediator.TEXT_textRadiusTolerance)){
			System.out.println("Text("+Mediator.TEXT_textRadiusTolerance +")"+med.getTextRadiusTolerance().getText());
			MC.Text_Modify_RadiusTolerance();
		}else if(this.widgetName.equals(Mediator.TEXT_textHeightTolerance)){
			System.out.println("Text("+Mediator.TEXT_textHeightTolerance +")"+med.getTextHeightTolerance().getText());
			MC.Text_Modify_HeightTolerance();
		}else if(this.widgetName.equals(Mediator.TEXT_textMaximumIterationNumber)){
			System.out.println("Text("+Mediator.TEXT_textMaximumIterationNumber +")"+med.getTextMaximumIterationNumber().getText());
			MC.Text_Modify_MaximumIterationNumber();
		}else if(this.widgetName.equals(Mediator.TEXT_textLogEditor)){
			System.out.println("Text("+Mediator.TEXT_textLogEditor +")"+med.getTextLogEditor().getText());
			MC.Text_Modify_LogEditor();
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
	public void setCustomWidget_textWireDiameter(){
		this.text = med.getTextWireDiameter();
	}
	public void setCustomWidget_textCenterDiameter(){
		this.text = med.getTextCenterDiameter();
	}
	public void setCustomWidget_textInternalDiameter(){
		this.text = med.getTextInternalDiameter();
	}
	public void setCustomWidget_textExternalDiameter(){
		this.text = med.getTextExternalDiameter();
	}
	public void setCustomWidget_textUpperInnerDiameter(){
		this.text = med.getTextUpperInnerDiameter();
	}
	public void setCustomWidget_textLowerInnerDiameter(){
		this.text = med.getTextLowerInnerDiameter();
	}
	public void setCustomWidget_textTotalTurns(){
		this.text = med.getTextTotalTurns();
	}
	
	public void setCustomWidget_textHotSettingTemp(){
		this.text = med.getTextHotSettingTemp();
	}
	public void setCustomWidget_textColdSettingTemp(){
		this.text = med.getTextColdSettingTemp();
	}
	public void setCustomWidget_textHotSettingHeight(){
		this.text = med.getTextHotSettingHeight();
	}
	public void setCustomWidget_textColdSettingHeight(){
		this.text = med.getTextColdSettingHeight();
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
	public void setCustomWidget_textSeatUStepRotationHeight(){
		this.text = med.getTextSeatUStepRotationHeight();
	}
	public void setCustomWidget_textSeatLStepRotationHeight(){
		this.text = med.getTextSeatLStepRotationHeight();
	}
	public void setCustomWidget_textSeatURotationAngle(){
		this.text = med.getTextSeatURotationAngle();
	}
	public void setCustomWidget_textSeatLRotationAngle(){
		this.text = med.getTextSeatLRotationAngle();
	}
	
	
	public void setCustomWidget_textRadiusConditionerPath(){
		this.text =med.getTextRadiusConditionerPath();
	}
	public void setCustomWidget_textHeightConditionerPath(){
		this.text =med.getTextHeightConditionerPath();
	}

	public void setCustomWidget_textRadiusConditionerValue(){
		this.text =med.getTextRadiusConditionerValue();
	}
	public void setCustomWidget_textHeightConditionerValue(){
		this.text =med.getTextHeightConditionerValue();
	}
	
	public void setCustomWidget_textMaterialDBPath(){
		this.text = med.getTextMaterialDBPath();
	}
	
	public void setCustomWidget_textParallelCpuNumber(){
		this.text = med.getTextParallelCpuNumber();
	}
	
	public void setCustomWidte_textFormedCoilDataInterp(){
		this.text = med.getTextFormedCoilDataInterp();
	}
	
	public void setCustomWidget_textRadiusTolerance(){
		this.text = med.getTextRadiusTolerance();
	}
	public void setCustomWidget_textHeightTolerance(){
		this.text = med.getTextHeightTolerance();
	}
	public void setCustomWidget_textMaximumIterationNumber(){
		this.text = med.getTextMaximumIterationNumber();
	}
	public void setCustomWidget_textLogEditor(){
		this.text = med.getTextLogEditor();
	}
	
	
}
