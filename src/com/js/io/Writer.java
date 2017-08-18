package com.js.io;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class Writer {
	private String outputFilePath;
	private ArrayList<String> outputDataList;
	private BufferedWriter writer;
	
	public Writer(String outputFilePath) {
		// TODO Auto-generated constructor stub
		this.outputFilePath = outputFilePath;
		this.outputDataList = new ArrayList<String>();
	}
	
	public void running(ArrayList<String> outputDataList){
		this.outputDataList = outputDataList;
		this.initWriter();
		this.writeFile();
	}
	
	public void running_utf8(ArrayList<String> outputDataList){
		this.outputDataList = outputDataList;
		this.initWriter_UTF8();
		this.writeFile();
	}
	private void initWriter(){
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.outputFilePath),"MS949"));
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
	
	private void initWriter_UTF8(){
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.outputFilePath),"UTF8"));
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
	
	private void writeFile() {
		try {
			for(int i=0;i<this.outputDataList.size();i++){
				writer.write(this.outputDataList.get(i));
				writer.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error - Writing ");
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error - Writer Close");
				e.printStackTrace();
			}
		}
	}
		
}
