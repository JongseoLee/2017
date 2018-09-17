package com.js.ens.coil.customWidget;

import java.util.ArrayList;

import com.js.io.Reader;
import com.js.plot.GraphAllData;
import com.js.util.myUtil;

public class ComboData_selectGraph {
	private String name; // combo label

	private String resultType;
	private String patternFileName;
	private String filePath;
	private ArrayList<String> fileDataList;
	private String iterationName;
	
	
	private ColumnData xDataObj;	// Iteration No <--MaximumError만 해당, Theta(turn)나머지 모두  
	private ArrayList<ColumnData> yDataObjList;	// 2번째 컬럼부터 데이터 저장 
	//------------------------------------------------------------------
	// conditioner, error, form set error
	/*
	 1) conditioner : 
	    1번째 컬럼 : Theta(turn) 
	    2번째 컬럼 : Radius(mm)
	    3번째 컬럼 : Height(mm)
	 2) error, form set error 
	    1번째 컬럼 : Theta(turn)
	    2번째 컬럼 : Skip
	    3번째 컬럼 : Radius(mm)
	    4번째 컬럼 : Height(mm)
	 */
	private ArrayList<Float> xDataValue;
	private ArrayList<Float> yDataValue_Radius;	
	private ArrayList<Float> yDataValue_Height;
	private String yLegend_Radius;
	private String yLegend_Height;
	//------------------------------------------------------------------
	// pitch, radius 
	/*
	 1) pitch 
	   => FS_1_cset_rezoned_align_ptich.csv, FS_1_hset_rezoned_align_ptich.csv, FS_1_formed_rezoned_align_ptich.csv
	 	1번째 컬럼 : point id
	 	2번째 컬럼 : Theta(turn)
	 	3번째 컬럼 : Height(mm)
	 2) radius 
	   => FS_1_cset_rezoned_align_erad.csv, FS_1_hset_rezoned_align_erad.csv, FS_1_formed_rezoned_align_erad.csv
	    1번째 컬럼 : point id
	    2번째 컬럼 : Theta(turn)
	    3번째 컬럼 : Radius(mm)
	 */
	//private ArrayList<Foloat>xDataValue; // 동일하게 사용 
	private ArrayList<Float> yDataValue_pitch_radius;
	private String yLegend_pitch_radius;

	//------------------------------------------------------------------
	// Maximum Error
	/*
	 1) Maximum Error
	   1번째 컬럼 : Iteration Number
	   2번째 컬럼 : Z max error ==> Maximum Error(mm)
	   3번째 컬럼 : R max error ==> Maximum Error(mm)
	 */
	private ArrayList<Float> yDataValue_MaxErrorZ;
	private ArrayList<Float> yDataValue_MaxErrorR;
	private String yLegend_MaxErrorZ;
	private String yLegend_MaxErrorR;
	
	//------------------------------------------------------------------
	// Form Data Total
	/*
	 1) Form Data Total
	   1번쩨 컬럼 : Theta(turn)
	   2번쨰 컬럼 : External Radius
	   3번째 컬럼 : External Pitch
	 */
	private ArrayList<Float> yDataValue_externalRadius;
	private ArrayList<Float> yDataValue_externalPitch;
	private String yLegend_externalRadius;
	private String yLegend_externalPitch;
	
	
	//private String graphTitle;
	
	
	//private String yLegend_MaxErrorR;
	//private ArrayList<Float> yDataValue_MaxErrorR;
	
	//private String yLegend_MaxErrorZ;
	//private ArrayList<Float> yDataValue_MaxErrorZ;
	
	// 2018.03_update -> etc column
	/* 
	private String yLegend_etcC2;
	private ArrayList<Float> yDataValue_etcC2;
	
	private String yLegend_etcC3;
	private ArrayList<Float> yDataValue_etcC3;
	//*/
	
	public ComboData_selectGraph(String resultType) {
		// TODO Auto-generated constructor stub
		// Type 
		// GraphAllData.Type_conditioner
		// GraphAllData.Type_error
		// GraphAllData.Type_formSetError
		// GraphAllData.Type_pitch
		// GraphAllData.Type_radius
		// GraphAllData.Type_maximumError
		// GraphAllData.Type_formDataTotal
		
		this.resultType = resultType;
		
	}
	
	public void running(){
		if(this.resultType.equals(GraphAllData.Type_conditioner)){
			this.readResultCSV();
			this.parsingResultCSV();
			/*
			System.out.println("========== parsing Data===========");
			System.out.println("File Name : " + myUtil.getFileNameIncludeExtension(this.filePath));
			System.out.println("Legend Radius : " + this.yLegend_Radius);
			System.out.println("Legend Height : " + this.yLegend_Height);
			int totalSize = this.xDataValue.size();
			for(int i = 0; i<totalSize;i++){
				System.out.print(String.format("%-30s", this.xDataValue.get(i)+"("+this.xDataObj.getValueList().get(i)+")"));
				System.out.print(String.format("%-30s",this.yDataValue_Radius.get(i)+"("+this.yDataObjList.get(0).getValueList().get(i)+")"));
				System.out.println(String.format("%-30s",this.yDataValue_Height.get(i)+"("+this.yDataObjList.get(1).getValueList().get(i)+")"));
			}
			//*/
		}
		
		else if(this.resultType.equals(GraphAllData.Type_error)){
			this.readResultCSV();
			this.parsingResultCSV();
			/*
			System.out.println("========== parsing Data===========");
			System.out.println("File Name : " + myUtil.getFileNameIncludeExtension(this.filePath));
			System.out.println("Legend Radius : " + this.yLegend_Radius);
			System.out.println("Legend Height : " + this.yLegend_Height);
			int totalSize = this.xDataValue.size();
			for(int i = 0; i<totalSize;i++){
				System.out.print(String.format("%-30s", this.xDataValue.get(i)+"("+this.xDataObj.getValueList().get(i)+")"));
				System.out.print(String.format("%-30s",this.yDataValue_Radius.get(i)+"("+this.yDataObjList.get(0).getValueList().get(i)+")"));
				System.out.println(String.format("%-30s",this.yDataValue_Height.get(i)+"("+this.yDataObjList.get(1).getValueList().get(i)+")"));
			}
			//*/
		}else if(this.resultType.equals(GraphAllData.Type_formSetError)){
			this.readResultCSV();
			this.parsingResultCSV();
			/*
			System.out.println("========== parsing Data===========");
			System.out.println("File Name : " + myUtil.getFileNameIncludeExtension(this.filePath));
			System.out.println("Legend Radius : " + this.yLegend_Radius);
			System.out.println("Legend Height : " + this.yLegend_Height);
			int totalSize = this.xDataValue.size();
			for(int i = 0; i<totalSize;i++){
				System.out.print(String.format("%-30s", this.xDataValue.get(i)+"("+this.xDataObj.getValueList().get(i)+")"));
				System.out.print(String.format("%-30s",this.yDataValue_Radius.get(i)+"("+this.yDataObjList.get(0).getValueList().get(i)+")"));
				System.out.println(String.format("%-30s",this.yDataValue_Height.get(i)+"("+this.yDataObjList.get(1).getValueList().get(i)+")"));
			}
			//*/
		}
		
		else if(this.resultType.equals(GraphAllData.Type_pitch)){
			this.readResultCSV_pitch_radius();
			this.parsing_pitch_radius();
			/*
			System.out.println("========== parsing Data===========");
			System.out.println("File Name : " + myUtil.getFileNameIncludeExtension(this.filePath));
			System.out.println("Legend pitch radius : " + this.yLegend_pitch_radius);
			
			int totalSize = this.xDataValue.size();
			for(int i = 0; i<totalSize;i++){
				System.out.print(String.format("%-30s", this.xDataValue.get(i)+"("+this.xDataObj.getValueList().get(i)+")"));
				System.out.println(String.format("%-30s",this.yDataValue_pitch_radius.get(i)+"("+this.yDataObjList.get(0).getValueList().get(i)+")"));
				//System.out.println(String.format("%-30s",this.yDataValue_Height.get(i)+"("+this.yDataObjList.get(1).getValueList().get(i)+")"));
			}
			//*/
			
		}else if(this.resultType.equals(GraphAllData.Type_radius)){
			this.readResultCSV_pitch_radius();
			this.parsing_pitch_radius();
			/* 
			System.out.println("========== parsing Data===========");
			System.out.println("File Name : " + myUtil.getFileNameIncludeExtension(this.filePath));
			System.out.println("Legend pitch radius : " + this.yLegend_pitch_radius);
			
			int totalSize = this.xDataValue.size();
			for(int i = 0; i<totalSize;i++){
				System.out.print(String.format("%-30s", this.xDataValue.get(i)+"("+this.xDataObj.getValueList().get(i)+")"));
				System.out.println(String.format("%-30s",this.yDataValue_pitch_radius.get(i)+"("+this.yDataObjList.get(0).getValueList().get(i)+")"));
				//System.out.println(String.format("%-30s",this.yDataValue_Height.get(i)+"("+this.yDataObjList.get(1).getValueList().get(i)+")"));
			}
			//*/
		}
		
		else if(this.resultType.equals(GraphAllData.Type_maximumError)){
			this.readResultCSV_err();
			this.parsing_err();
			/* 
			System.out.println("========== parsing Data===========");
			System.out.println("File Name : " + myUtil.getFileNameIncludeExtension(this.filePath));
			System.out.println("Legend Max Z : " + this.yLegend_MaxErrorZ);
			System.out.println("Legend Max R : " + this.yLegend_MaxErrorR);
			
			int totalSize = this.xDataValue.size();
			for(int i = 0; i<totalSize;i++){
				System.out.print(String.format("%-30s", this.xDataValue.get(i)+"("+this.xDataObj.getValueList().get(i)+")"));
				System.out.print(String.format("%-30s",this.yDataValue_MaxErrorZ.get(i)+"("+this.yDataObjList.get(0).getValueList().get(i)+")"));
				System.out.println(String.format("%-30s",this.yDataValue_MaxErrorR.get(i)+"("+this.yDataObjList.get(1).getValueList().get(i)+")"));
			}
			//*/
		}
		
		else if(this.resultType.equals(GraphAllData.Type_formDataTotal)){
			this.readResultCSV_formDataTotal();
			this.parsing_formDataTotal();
			/* 
			System.out.println("========== parsing Data===========");
			System.out.println("File Name : " + myUtil.getFileNameIncludeExtension(this.filePath));
			System.out.println("Legend External Radius : " + this.yLegend_externalRadius);
			System.out.println("Legend External Pitch : " + this.yLegend_externalPitch);
			
			int totalSize = this.xDataValue.size();
			for(int i = 0; i<totalSize;i++){
				System.out.print(String.format("%-30s", this.xDataValue.get(i)+"("+this.xDataObj.getValueList().get(i)+")"));
				System.out.print(String.format("%-30s",this.yDataValue_externalRadius.get(i)+"("+this.yDataObjList.get(0).getValueList().get(i)+")"));
				System.out.println(String.format("%-30s",this.yDataValue_externalPitch.get(i)+"("+this.yDataObjList.get(1).getValueList().get(i)+")"));
			}
			//*/
		}
		
	}
	
	
	
	
	private void readResultCSV(){
		this.fileDataList = new ArrayList<String>();
		Reader reader = new Reader(this.filePath);
		reader.running();
		for(int i=0; i<reader.getFileDataList().size();i++){
			if(i==0){
				// title is first line of csv file
				// this.graphTitle = reader.getFileDataList().get(0).trim();
			}else{
				this.fileDataList.add(reader.getFileDataList().get(i));
			}
		}
	}
	
	private void readResultCSV_pitch_radius(){
		this.fileDataList = new ArrayList<String>();
		Reader reader = new Reader(this.filePath);
		reader.running();
		for(int i=0; i<reader.getFileDataList().size();i++){
			this.fileDataList.add(reader.getFileDataList().get(i));
		}
	}

	private void readResultCSV_err(){
		this.fileDataList = new ArrayList<String>();
		Reader reader = new Reader(this.filePath);
		reader.running();
		for(int i=0; i<reader.getFileDataList().size();i++){
			if(i==0){
				// title is first line of csv file
				//this.graphTitle = "Maximum Iteration Error";
			}else{
				this.fileDataList.add(reader.getFileDataList().get(i));
			}
		}
	}
	
	private void readResultCSV_formDataTotal(){
		this.fileDataList = new ArrayList<String>();
		Reader reader = new Reader(this.filePath);
		reader.running();
		for(int i=0; i<reader.getFileDataList().size();i++){
			this.fileDataList.add(reader.getFileDataList().get(i));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void parsingResultCSV(){
		this.yDataObjList = new ArrayList<ColumnData>();
		this.xDataObj = new ColumnData();			// x data
		this.yDataObjList.add(new ColumnData());	// y Radius data
		this.yDataObjList.add(new ColumnData());	// y Height data
		
		this.xDataValue= new ArrayList<Float>();
		this.yDataValue_Radius = new ArrayList<Float>();
		this.yDataValue_Height = new ArrayList<Float>();
		
		// 초기화한 리스트에 데이터 넣기
		for(int lineNum = 0; lineNum <this.fileDataList.size();lineNum++){
			String line = this.fileDataList.get(lineNum);
			ArrayList<String> tokens = new ArrayList<String>();
			tokens = myUtil.splitData_csv(line, ",");
			
			if(lineNum == 0){
				this.xDataObj.setDataName(tokens.get(0));
				
				if(this.resultType.equals(GraphAllData.Type_conditioner)){
					this.yDataObjList.get(0).setDataName(iterationName+"_"+tokens.get(1));
					this.yDataObjList.get(1).setDataName(iterationName+"_"+tokens.get(2));
					this.yLegend_Radius = iterationName+"_"+tokens.get(1);
					this.yLegend_Height = iterationName+"_"+tokens.get(2);
				}else if(this.resultType.equals(GraphAllData.Type_error)){
					this.yDataObjList.get(0).setDataName(iterationName+"_"+tokens.get(2));
					this.yDataObjList.get(1).setDataName(iterationName+"_"+tokens.get(3));
					this.yLegend_Radius = iterationName+"_"+tokens.get(2);
					this.yLegend_Height = iterationName+"_"+tokens.get(3);
				}else if(this.resultType.equals(GraphAllData.Type_formSetError)){
					this.yDataObjList.get(0).setDataName(iterationName+"_"+tokens.get(2));
					this.yDataObjList.get(1).setDataName(iterationName+"_"+tokens.get(3));
					this.yLegend_Radius = iterationName+"_"+tokens.get(2);
					this.yLegend_Height = iterationName+"_"+tokens.get(3);
				}
			}else {
				// x 축 데이터 
				this.xDataObj.addValue(tokens.get(0));
				this.xDataValue.add(Float.parseFloat(tokens.get(0)));
				
				if(this.resultType.equals(GraphAllData.Type_conditioner)){
					// Column obj에 Str 데이터 저장
					this.yDataObjList.get(0).addValue(tokens.get(1));
					this.yDataObjList.get(1).addValue(tokens.get(2));
					// Float 데이터를 시스트에 저장  
					this.yDataValue_Radius.add(Float.parseFloat(tokens.get(1)));
					this.yDataValue_Height.add(Float.parseFloat(tokens.get(2)));
				}else if(this.resultType.equals(GraphAllData.Type_error)){
					// Column obj에 Str 데이터 저장
					this.yDataObjList.get(0).addValue(tokens.get(2));
					this.yDataObjList.get(1).addValue(tokens.get(3));
					// Float 데이터를 시스트에 저장  
					this.yDataValue_Radius.add(Float.parseFloat(tokens.get(2)));
					this.yDataValue_Height.add(Float.parseFloat(tokens.get(3)));
				}else if(this.resultType.equals(GraphAllData.Type_formSetError)){
					// Column obj에 Str 데이터 저장
					this.yDataObjList.get(0).addValue(tokens.get(2));
					this.yDataObjList.get(1).addValue(tokens.get(3));
					// Float 데이터를 시스트에 저장  
					this.yDataValue_Radius.add(Float.parseFloat(tokens.get(2)));
					this.yDataValue_Height.add(Float.parseFloat(tokens.get(3)));
				}
			}
		}
	}
	
	private void parsing_pitch_radius(){
		/*
			private ArrayList<Float> yDataValue_pitch_radius;
			private String yLegend_pitch_radius;
		 */
		this.yDataObjList = new ArrayList<ColumnData>();
		this.xDataObj = new ColumnData();			// x data
		this.yDataObjList.add(new ColumnData());	// y Pitch Radius data

		this.xDataValue= new ArrayList<Float>();
		this.yDataValue_pitch_radius = new ArrayList<Float>();
		
		//int dataSize = myUtil.splitData_csv(this.fileDataList.get(0), ",").size();
		
		/*
		this.xDataObj = new ColumnData();
		for(int i=1; i<dataSize;i++){
			ColumnData obj = new ColumnData();
			this.yDataObjList.add(obj);
		}
		//*/
		
		//this.xDataValue = new ArrayList<Float>();
		//this.yDataValue_etcC2 = new ArrayList<Float>();
		//this.yDataValue_etcC3 = new ArrayList<Float>();
		
		
		for(int lineNum = 0; lineNum < this.fileDataList.size(); lineNum++){
			String line = this.fileDataList.get(lineNum);
			ArrayList<String> tokens = new ArrayList<String>();
			tokens = myUtil.splitData_csv(line, ",");
			//System.out.println("etc1 : "+tokens);
			if(lineNum == 0){
				this.xDataObj.setDataName(tokens.get(1));
				this.yDataObjList.get(0).setDataName(iterationName+"_"+tokens.get(2));
				this.yLegend_pitch_radius = iterationName+"_"+tokens.get(2);
				/*
				for(int i=0;i<tokens.size()-1;i++){
					//this.yDataObjList.get(i).setDataName(this.iterationName+"_"+tokens.get(i+1));
					this.yDataObjList.get(i).setDataName(myUtil.getFileName(this.filePath)+"_"+tokens.get(i+1));
				}
				//*/
				
				
				//this.yLegend_etcC2 = this.iterationName+"_"+tokens.get(1);
				//this.yLegend_etcC3 = this.iterationName+"_"+tokens.get(2);
				//this.yLegend_etcC2 = myUtil.getFileName(this.filePath)+"_"+tokens.get(1);
				//this.yLegend_etcC3 = myUtil.getFileName(this.filePath)+"_"+tokens.get(2);
				/*
				if(this.resultType.equals(GraphAllData.mFormdataTotal)){
					this.yLegend_etcC2 = this.iterationName+"_"+tokens.get(1);
					this.yLegend_etcC3 = this.iterationName+"_"+tokens.get(2);
				}else{
					this.yLegend_etcC2 = "error-C2";
					this.yLegend_etcC3 = "error-C3";
				}
				//*/
			}else {
				/*
				// x 축 데이터 
				this.xDataObj.addValue(tokens.get(0));
				this.xDataValue.add(Float.parseFloat(tokens.get(0)));
				
				if(this.resultType.equals(GraphAllData.Type_conditioner)){
					// Column obj에 Str 데이터 저장
					this.yDataObjList.get(0).addValue(tokens.get(1));
					this.yDataObjList.get(1).addValue(tokens.get(2));
					// Float 데이터를 시스트에 저장  
					this.yDataValue_Radius.add(Float.parseFloat(tokens.get(1)));
					this.yDataValue_Height.add(Float.parseFloat(tokens.get(2)));
				//*/
				
				// x 축 데이터 
				this.xDataObj.addValue(tokens.get(1));
				this.xDataValue.add(Float.parseFloat(tokens.get(1)));
				// y 축 데이터
				this.yDataObjList.get(0).addValue(tokens.get(2));
				this.yDataValue_pitch_radius.add(Float.parseFloat(tokens.get(2)));
				
				/*
				this.xDataObj.addValue(tokens.get(0));
				this.xDataValue.add(Float.parseFloat(tokens.get(0)));
				for(int i=0;i<tokens.size()-1;i++){
					this.yDataObjList.get(i).addValue(tokens.get(i+1));
				}
				this.yDataValue_etcC2.add(Float.parseFloat(tokens.get(1)));
				this.yDataValue_etcC3.add(Float.parseFloat(tokens.get(2)));
				//*/
			}
		}
	}
	
	private void parsing_err(){
		this.yDataObjList = new ArrayList<ColumnData>();
		this.xDataObj = new ColumnData();			// x data
		this.yDataObjList.add(new ColumnData());	// z data
		this.yDataObjList.add(new ColumnData());	// r data
		
		this.xDataValue = new ArrayList<Float>();
		this.yDataValue_MaxErrorZ = new ArrayList<Float>();
		this.yDataValue_MaxErrorR = new ArrayList<Float>();
		
		// set value at columnDataObj
		for(int lineNum = 0; lineNum <this.fileDataList.size();lineNum++){
			String line = this.fileDataList.get(lineNum);
			ArrayList<String> tokens = new ArrayList<String>();
			tokens = myUtil.splitData_csv(line, ",");
			
			if(lineNum == 0){
				this.xDataObj.setDataName(tokens.get(0));
				
				this.yDataObjList.get(0).setDataName(tokens.get(1));
				this.yDataObjList.get(1).setDataName(tokens.get(2));
				this.yLegend_MaxErrorZ = tokens.get(1);
				this.yLegend_MaxErrorR = tokens.get(2);
			}else {
				// x 축 데이터 
				this.xDataObj.addValue(tokens.get(0));
				this.xDataValue.add(Float.parseFloat(tokens.get(0)));
				
				// Column obj에 Str 데이터 저장
				this.yDataObjList.get(0).addValue(tokens.get(1));
				this.yDataObjList.get(1).addValue(tokens.get(2));
				// Float 데이터를 시스트에 저장  
				this.yDataValue_MaxErrorZ.add(Float.parseFloat(tokens.get(1)));
				this.yDataValue_MaxErrorR.add(Float.parseFloat(tokens.get(2)));
			}
		}
	}
	
	private void parsing_formDataTotal(){
		this.yDataObjList = new ArrayList<ColumnData>();
		this.xDataObj = new ColumnData();			// x data
		this.yDataObjList.add(new ColumnData());	// y External Radius
		this.yDataObjList.add(new ColumnData());	// y pitch
		
		this.xDataValue= new ArrayList<Float>();
		this.yDataValue_externalRadius = new ArrayList<Float>();
		this.yDataValue_externalPitch = new ArrayList<Float>();
		
		for(int lineNum = 0; lineNum < this.fileDataList.size(); lineNum++){
			String line = this.fileDataList.get(lineNum);
			ArrayList<String> tokens = new ArrayList<String>();
			tokens = myUtil.splitData_csv(line, ",");
			//myUtil.printArrData(tokens);
			if(lineNum == 0){
				this.xDataObj.setDataName(tokens.get(0));
				
				this.yDataObjList.get(0).setDataName(tokens.get(1));
				this.yDataObjList.get(1).setDataName(tokens.get(2));
				this.yLegend_externalRadius = tokens.get(1);
				this.yLegend_externalPitch = tokens.get(2);
				
			}else {
				this.xDataObj.addValue(tokens.get(0));
				this.xDataValue.add(Float.parseFloat(tokens.get(0)));
				
				this.yDataObjList.get(0).addValue(tokens.get(1));
				this.yDataObjList.get(1).addValue(tokens.get(2));
				
				this.yDataValue_externalRadius.add(Float.parseFloat(tokens.get(1)));
				this.yDataValue_externalPitch.add(Float.parseFloat(tokens.get(2)));
				
			}
		}
	}
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public String getPatternFileName() {
		return patternFileName;
	}

	public void setPatternFileName(String patternFileName) {
		this.patternFileName = patternFileName;
		this.iterationName = myUtil.getFileNameIncludeExtension(this.filePath).replace(this.patternFileName, "");
	}

	/*
	public String getGraphTitle() {
		return graphTitle;
	}
	public void setGraphTitle(String graphTitle) {
		this.graphTitle = graphTitle;
	}
	//*/
	
	public String getIterationName() {
		return iterationName;
	}
	public void setIterationName(String iterationName) {
		this.iterationName = iterationName;
	}

	public ColumnData getxDataObj() {
		// Theta data 
		return xDataObj;
	}

	public ArrayList<ColumnData> getyDataObjList() {
		return yDataObjList;
	}
	
	
	public String getyLegend_Radius() {
		return yLegend_Radius;
	}

	public ArrayList<Float> getyDataValue_Radius() {
		return yDataValue_Radius;
	}

	
	public String getyLegend_Height() {
		return yLegend_Height;
	}

	public ArrayList<Float> getyDataValue_Height() {
		return yDataValue_Height;
	}
		
	public String getyLegend_MaxErrorR() {
		return yLegend_MaxErrorR;
	}

	public ArrayList<Float> getyDataValue_MaxErrorR() {
		return yDataValue_MaxErrorR;
	}

	public String getyLegend_MaxErrorZ() {
		return yLegend_MaxErrorZ;
	}

	public ArrayList<Float> getyDataValue_MaxErrorZ() {
		return yDataValue_MaxErrorZ;
	}
	
	/*
	// 2018.03_update
	public String getyLegend_etcC2() {
		return yLegend_etcC2;
	}

	public ArrayList<Float> getyDataValue_etcC2() {
		return yDataValue_etcC2;
	}

	public String getyLegend_etcC3() {
		return yLegend_etcC3;
	}

	public ArrayList<Float> getyDataValue_etcC3() {
		return yDataValue_etcC3;
	}
	//*/

	public String getyLegend_pitch_radius() {
		return yLegend_pitch_radius;
	}

	public ArrayList<Float> getyDataValue_pitch_radius() {
		return yDataValue_pitch_radius;
	}
	

	public ArrayList<Float> getyDataValue_externalRadius() {
		return yDataValue_externalRadius;
	}

	public ArrayList<Float> getyDataValue_externalPitch() {
		return yDataValue_externalPitch;
	}

	public String getyLegend_externalRadius() {
		return yLegend_externalRadius;
	}

	public String getyLegend_externalPitch() {
		return yLegend_externalPitch;
	}

















	/////////////////////////////////////////////////////////////////////////////////////
	// inner class
	public class ColumnData{
		
		private String dataName;	//Column Name		
		private ArrayList<String> valueList;
		 
		
		public ColumnData(){
			this.valueList = new ArrayList<String>();
		}
		
		public void setDataName(String name){
			// legends로 쓰임 
			this.dataName = name;
		}
		
		public void addValue(String value){
			this.valueList.add(value);
		}
		
		public ArrayList<String> getValueList(){
			// valuse
			return this.valueList;
		}

		public String getDataName() {
			// graph label name
			return dataName;
		}
	}

}
