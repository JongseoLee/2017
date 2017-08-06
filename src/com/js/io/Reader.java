package com.js.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Reader {
	private String filePath;
	private ArrayList<String> fileDataList;
	private BufferedReader reader;
	
	public Reader(String filePath) {
		// TODO Auto-generated constructor stub
		this.filePath = filePath;
		this.fileDataList = new ArrayList<String>();
	}
	
	public void running(){
		this.initReader();
		this.readFile();
	}
	
	private void initReader(){
		try {
			// UTF8 -> MS949 �ѱ� label ������ ����
			this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath),"MS949"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("Error - Encoding ");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error - File Not Found");
			e.printStackTrace();
		}
	}
	
	public void readFile(){
		String line = null;
		try {
			while((line=reader.readLine()) != null){
				this.fileDataList.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				this.reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error - Close reader");
				e.printStackTrace();
			}
		}
	}
	
	////////////////////////////////////////////////////////
	// get - set method
	public ArrayList<String> getFileDataList() {
		return this.fileDataList;
	}
	//
	////////////////////////////////////////////////////////
}
