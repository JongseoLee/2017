package com.js.graph;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;



public class GraphWindow_bak {

	protected Shell shell;
	
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	
	/* */
	public static void main(String[] args) {
		try {
			GraphWindow_bak window = new GraphWindow_bak();
			window.open("D:\\6____Tool_RCP_Eclipse\\eclipse_kepler\\eclipse\\Simcos_Workspace\\170828\\SimcosData\\Plot\\FS_20170830_153806.html");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//*/
	
	/**
	 * Open the window.
	 */
	public void open(String path) {
		Display display = Display.getDefault();
		createContents(path);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(String path) {
		shell = new Shell();
		shell.setSize(800, 500);
		shell.setText("Simcos graph");
		shell.setLayout(new FormLayout());
		
		Label lblGraphWindow = new Label(shell, SWT.NONE);
		FormData fd_lblGraphWindow = new FormData();
		fd_lblGraphWindow.top = new FormAttachment(0, 10);
		fd_lblGraphWindow.left = new FormAttachment(0, 10);
		lblGraphWindow.setLayoutData(fd_lblGraphWindow);
		lblGraphWindow.setText("Graph Window");
		
		Browser browser = new Browser(shell, SWT.NONE);
		fd_lblGraphWindow.bottom = new FormAttachment(browser, -5);
		FormData fd_browser = new FormData();
		fd_browser.top = new FormAttachment(0, 30);
		fd_browser.left = new FormAttachment(0);
		fd_browser.right = new FormAttachment(100);
		fd_browser.bottom = new FormAttachment(100);
		browser.setLayoutData(fd_browser);
		if(browser.setUrl(path)){
			System.out.println("Success widnow : "+path);
		}else{
			System.out.println("File window : "+path);
		}
		
	}
}
