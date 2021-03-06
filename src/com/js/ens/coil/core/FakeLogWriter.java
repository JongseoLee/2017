package com.js.ens.coil.core;

import java.util.ArrayList;

import com.js.ens.coil.db.CoilDB;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class FakeLogWriter implements Runnable {
	
	
	private String filePath;
	private ArrayList<String> dummyFileDataList;
	private CoilDB CObj;
	
	
	public FakeLogWriter() {
		// TODO Auto-generated constructor stub
	}
	
	public void running(CoilDB obj,String path){
		this.filePath = path;
		this.CObj = obj;
	}
	
	private void readDummyFile(){
		this.dummyFileDataList = new ArrayList<String>();
		Reader reader = new Reader(this.filePath);
		reader.running();
		this.dummyFileDataList = reader.getFileDataList();
		myUtil.CleareObj(reader);
	}
	
	private void writeDymmyFile(){
		String fakelogFileName = "FS"+AppFolder.coilItrLogFileName;
		String outputFilePath = myUtil.setPath(myUtil.setPath(this.CObj.getProjectFolderPath(), AppFolder.SIMCOS_DATA),fakelogFileName); 
		ArrayList<String> tempDataList = new ArrayList<String>();
		for(int i=0;i<this.dummyFileDataList.size();i++){
			tempDataList.add(this.dummyFileDataList.get(i));
			try{
				//int sleepTime = (int) ((Math.random()*10000)+1000)%2;
				//Thread.sleep(sleepTime*1000);
				Thread.sleep(500);
				
				Writer writer = new Writer(outputFilePath);
				writer.running(tempDataList);
				myUtil.CleareObj(writer);
			}catch(Exception e){
				System.out.println("SLEEP - ERROR \n"+e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("START - FakeLog");
		this.readDummyFile();
		this.writeDymmyFile();
		Thread.interrupted();
		System.out.println("END - FakeLog");
	}

}
