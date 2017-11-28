package com.js.plot;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.js.ens.coil.core.Mediator;
import com.js.ens.coil.customWidget.TableData_Coil;
import com.js.ens.coil.db.CoilDB;
import com.js.util.myUtil;

public class SimcosGraphViewer {
	
	private Mediator med = Mediator.getInstance();
	
	private String chartTitle;
	private String xTitle;
	private String yTitle;
	private ArrayList<String> legendList;
	private ArrayList<ArrayList<Float>> xDataList;
	private ArrayList<ArrayList<Float>> yDataList;
	
	
	public SimcosGraphViewer() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void running(CoilDB coilDBObj){
		// SimcosGraphViewer -> CreatePlotData -> GraphPanel
		this.GenPlotData(coilDBObj);
		this.ShowPlotData();
	}
	
	public void running_geometryData_radius(CoilDB coilDBObj){
		this.GenPlotData_geometryData_radius(coilDBObj);
		this.ShowPlotData();
	}
	
	public void running_geometryData_pitch(CoilDB coilDBObj){
		this.GenPlotData_geometryData_pitch(coilDBObj);
		this.ShowPlotData();
	}
	
	private void GenPlotData_geometryData_radius(CoilDB coilDBObj){
		this.legendList = new ArrayList<String>();
		this.xDataList = new ArrayList<ArrayList<Float>>();
		this.yDataList = new ArrayList<ArrayList<Float>>();
		
		ArrayList<Float> xList = new ArrayList<Float>();
		ArrayList<Float> yList = new ArrayList<Float>();
		
		for(TableData_Coil obj : coilDBObj.getGeometryDataTableList()){
			xList.add(Float.parseFloat(obj.getTheta()));
			yList.add(Float.parseFloat(obj.getRadius()));
		}

		this.chartTitle = "Coil Geometry Radius";
		this.xTitle = "Theta";
		this.yTitle = "Radius";
		this.legendList.add("Radius Data");
		this.xDataList.add(xList);
		this.yDataList.add(yList);
	}
	
	private void GenPlotData_geometryData_pitch(CoilDB coilDBObj){
		this.legendList = new ArrayList<String>();
		this.xDataList = new ArrayList<ArrayList<Float>>();
		this.yDataList = new ArrayList<ArrayList<Float>>();
		
		ArrayList<Float> xList = new ArrayList<Float>();
		ArrayList<Float> yList = new ArrayList<Float>();
		
		for(TableData_Coil obj : coilDBObj.getGeometryDataTableList()){
			xList.add(Float.parseFloat(obj.getTheta()));
			yList.add(Float.parseFloat(obj.getHeight()));
		}

		this.chartTitle = "Coil Geometry Pitch";
		this.xTitle = "Theta";
		this.yTitle = "Pitch";
		this.legendList.add("Pitch Data");
		this.xDataList.add(xList);
		this.yDataList.add(yList);
	}

	private void GenPlotData(CoilDB coilDBObj){
		this.legendList = new ArrayList<String>();
		this.xDataList = new ArrayList<ArrayList<Float>>();
		this.yDataList = new ArrayList<ArrayList<Float>>();
		
		CreatePlotData obj = new CreatePlotData();
		obj.running(coilDBObj);
		
		this.chartTitle = obj.getTitle();
		this.xTitle = obj.getxAxisTitle();
		this.yTitle = obj.getyAxisTitle();
		this.legendList = obj.getLegendList();
		this.xDataList = obj.getxDataList();
		this.yDataList = obj.getyDataList();
		//System.out.println("####### Xdata size : "+this.xDataList.size());
		//System.out.println("####### Ydata size : "+this.yDataList.size());
	}
	
	private void ShowPlotData(){
		GraphPanel graph = new GraphPanel(this.chartTitle,this.xTitle,this.yTitle);
		graph.setLegendList(this.legendList);
		graph.initialDataSet(this.xDataList, this.yDataList);
		
		final JFrame frame = new JFrame("Simcos Graph");
		frame.setSize(600, 500);
		frame.getContentPane().add(graph.getChartPanel());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				frame.dispose();
			}
		});
		frame.setVisible(true);
	}
}
