package com.js.plot;

import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraphPanel {
	
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private XYPlot plot;
	
	private ArrayList<XYDataset> dataSetList;
	private ArrayList<String> legendList;
	//private ArrayList<XYLineAndShapeRenderer> lineShapeList;
	
	private String chartTitle;
	private String xTitle;
	private String yTitle;
	
	public GraphPanel(String chartTitle, String xTitle, String yTitle) {
		// TODO Auto-generated constructor stub
		this.chartTitle = chartTitle;
		this.xTitle = xTitle;
		this.yTitle = yTitle;
		this.dataSetList = new ArrayList<XYDataset>();
		this.legendList = new ArrayList<String>();
	}
	
	public void setLegendList(ArrayList<String> legendList){
		this.legendList = legendList;
	}

	public void initialDataSet(ArrayList<ArrayList<Float>> xDataList, ArrayList<ArrayList<Float>> yDataList){
		//Create dataset 
		for(int i=0;i<xDataList.size();i++){
			XYSeries series = new XYSeries((i+1)+"."+this.legendList.get(i));
			for(int j=0;j<yDataList.get(i).size();j++){
				series.add(xDataList.get(i).get(j), yDataList.get(i).get(j));
			}
			XYDataset dataset = new XYSeriesCollection(series);
			this.dataSetList.add(dataset);
		}
		// init chart
		this.chart = ChartFactory.createXYLineChart(this.chartTitle, this.xTitle, this.yTitle, null, PlotOrientation.VERTICAL, true, false, false);
		this.chart.setBackgroundPaint(Color.WHITE);
		this.plot = this.chart.getXYPlot();
		
		// set dataset at plot
		ColorData color = new ColorData();
		int index = 0;
		for(XYDataset ds : this.dataSetList){
			this.plot.setDataset(index, ds);
			XYLineAndShapeRenderer obj = new XYLineAndShapeRenderer();
			this.plot.setRenderer(index,obj);
			this.plot.getRendererForDataset(this.plot.getDataset(index)).setSeriesPaint(0,color.getColorList().get(index));
			index++;
		}
		
		// set plot env
		this.plot.setDomainGridlinesVisible(true);
		this.plot.setDomainCrosshairVisible(true);
		
		// init panel // panel <=== chart
		this.chartPanel = new ChartPanel(this.chart);
		this.chartPanel.getInitialDelay();
		this.chartPanel.setInitialDelay(0);
	}
	
	public JFreeChart getChart() {
		return this.chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public ChartPanel getChartPanel() {
		return this.chartPanel;
	}

	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}
}
