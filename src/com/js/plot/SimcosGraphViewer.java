package com.js.plot;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.js.ens.coil.db.CoilDB;

public class SimcosGraphViewer {
	
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
