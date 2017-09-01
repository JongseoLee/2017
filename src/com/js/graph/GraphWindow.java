package com.js.graph;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;



public class GraphWindow extends Application{
	private static String path = "";
	
	@Override
	public void start(Stage primaryStage){
		// TODO Auto-generated method stub
		StackPane root = new StackPane();
		WebView wv = new WebView();
		final WebEngine eng = wv.getEngine();
		
		try {
			System.out.println("--->"+path);
			File file = new File(path);
			URL url = file.toURI().toURL();
			System.out.println("Local URL: " + url.toString());
			eng.load(url.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		root.getChildren().addAll(wv);
		Scene scene = new Scene(root );
		primaryStage.setTitle("Simcos Graph");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		for(String str : args){
			path = str;
		}
		Application.launch(args);
	}
}
