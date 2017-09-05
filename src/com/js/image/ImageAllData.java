package com.js.image;

import java.io.File;

import com.js.ens.coil.core.MainController;
import com.js.ens.coil.customWidget.ComboData_selectImage;
import com.js.ens.coil.db.CoilDB;
import com.js.util.myUtil;

public class ImageAllData {

	private MainController MC = MainController.getInstatnce();
	private CoilDB coilDBObj;
	
	public ImageAllData(CoilDB coilDBObj) {
		// TODO Auto-generated constructor stub
		this.coilDBObj = coilDBObj;
	}
	
	public void LoadingResult(String resultFolder){
		for(File f : myUtil.getDirFileList(resultFolder)){
			String extension = myUtil.getExtensions(f.getAbsolutePath());
			if(extension.toLowerCase().equals("png") || extension.toLowerCase().equals("jpg")){
				ComboData_selectImage obj = new ComboData_selectImage();
				obj.setName(f.getName());
				obj.setFilePath(f.getAbsolutePath());
				this.coilDBObj.add_ImageDataCombo(obj);
			}
		}
	}
	
	public ComboData_selectImage getImageObj(String fileName){
		ComboData_selectImage returnObj = null;
		for(ComboData_selectImage obj : this.coilDBObj.getImageDataList()){
			if(obj.getName().equals(fileName)){
				returnObj = obj;
				break;
			}
		}
		return returnObj;
	}

}
