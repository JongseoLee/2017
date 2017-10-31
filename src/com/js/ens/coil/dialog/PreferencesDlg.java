package com.js.ens.coil.dialog;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.js.ens.coil.core.MainController;
import com.js.ens.coil.core.Preferences;
import com.js.util.myUtil;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PreferencesDlg extends Dialog {
	private MainController MC = MainController.getInstatnce();
	
	private String MarcPath;
	private String MentatPath;
	private String TextEditorPath;
	private String ExcelPath;
	private String CommandSolving;
	private String CommandPost;
	
	private Map<String,String> PreferencesMap = new HashMap<String,String>();
	
	private Text textMarcPath;
	private Text textMentatPath;
	private Text textTextEditorPath;
	private Text textExcelPath;
	private Text textCommandSolving;
	private Text textCommandPost;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public PreferencesDlg(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		FormLayout fl_container = new FormLayout();
		fl_container.marginTop = 5;
		fl_container.marginRight = 5;
		fl_container.marginLeft = 5;
		fl_container.marginBottom = 5;
		container.setLayout(fl_container);
		
		Label lblPreferences = new Label(container, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			lblPreferences.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));	
		}else{
			lblPreferences.setFont(SWTResourceManager.getFont(".SF NS Text", 12, SWT.BOLD));
		}
		FormData fd_lblPreferences = new FormData();
		fd_lblPreferences.top = new FormAttachment(0, 10);
		fd_lblPreferences.left = new FormAttachment(0, 10);
		lblPreferences.setLayoutData(fd_lblPreferences);
		lblPreferences.setText("Preferences");
		
		Label lblMarcPath = new Label(container, SWT.NONE);
		FormData fd_lblMarcPath = new FormData();
		fd_lblMarcPath.top = new FormAttachment(lblPreferences, 30);
		fd_lblMarcPath.left = new FormAttachment(lblPreferences, 10, SWT.LEFT);
		lblMarcPath.setLayoutData(fd_lblMarcPath);
		lblMarcPath.setText("Marc path");
		
		textMarcPath = new Text(container, SWT.BORDER);
		textMarcPath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				MarcPath = text.getText().trim();
			}
		});
		FormData fd_textMarcPath = new FormData();
		fd_textMarcPath.top = new FormAttachment(lblMarcPath, 6);
		fd_textMarcPath.left = new FormAttachment(lblMarcPath, 0, SWT.LEFT);
		fd_textMarcPath.right = new FormAttachment(100,-50);
		textMarcPath.setLayoutData(fd_textMarcPath);
		textMarcPath.setText(MC.getPreferencesObj().getPreferencesValue(Preferences.MarcPath));
		
		final Button btnMarcPathFileExplorer = new Button(container, SWT.NONE);
		btnMarcPathFileExplorer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(btnMarcPathFileExplorer.getShell(),SWT.OPEN);
				dlg.setText("Select Marc execution file.");
				
				String [] extNames = {"All(*.*)"};
				String [] extType = {"*.*"};
				
				dlg.setFilterNames(extNames);
				dlg.setFilterExtensions(extType);
				// Open ProjectFolder.
				//dlg.setFilterPath(this.getAppPath());
				
				dlg.setFilterNames(extNames);
				String path = dlg.open();
				if (path != null){
					textMarcPath.setText(path);
				}
			}
		});
		FormData fd_btnMarcPathFileExplorer = new FormData();
		fd_btnMarcPathFileExplorer.top = new FormAttachment(textMarcPath, -2, SWT.TOP);
		fd_btnMarcPathFileExplorer.left = new FormAttachment(textMarcPath, 5, SWT.RIGHT);
		btnMarcPathFileExplorer.setLayoutData(fd_btnMarcPathFileExplorer);
		btnMarcPathFileExplorer.setText("...");
		
		Label lblMentatPath = new Label(container, SWT.NONE);
		FormData fd_lblMentatPath = new FormData();
		fd_lblMentatPath.top = new FormAttachment(textMarcPath, 20);
		fd_lblMentatPath.left = new FormAttachment(lblMarcPath, 0, SWT.LEFT);
		lblMentatPath.setLayoutData(fd_lblMentatPath);
		lblMentatPath.setText("Mentat path");
		
		textMentatPath = new Text(container, SWT.BORDER);
		textMentatPath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				MentatPath = text.getText().trim();
			}
		});
		FormData fd_textMentatPath = new FormData();
		fd_textMentatPath.top = new FormAttachment(lblMentatPath, 6);
		fd_textMentatPath.left = new FormAttachment(textMarcPath, 0, SWT.LEFT);
		fd_textMentatPath.right = new FormAttachment(textMarcPath, 0, SWT.RIGHT);
		textMentatPath.setLayoutData(fd_textMentatPath);
		textMentatPath.setText(MC.getPreferencesObj().getPreferencesValue(Preferences.MentatPath));
		
		final Button btnMentatPathFileExplorer = new Button(container, SWT.NONE);
		btnMentatPathFileExplorer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(btnMentatPathFileExplorer.getShell(),SWT.OPEN);
				dlg.setText("Select Mentat execution file.");
				
				String [] extNames = {"All(*.*)"};
				String [] extType = {"*.*"};
				
				dlg.setFilterNames(extNames);
				dlg.setFilterExtensions(extType);
				// Open ProjectFolder.
				//dlg.setFilterPath(this.getAppPath());
				
				dlg.setFilterNames(extNames);
				String path = dlg.open();
				if (path != null){
					textMentatPath.setText(path);
				}
			}
		});
		FormData fd_btnMentatPathFileExplorer = new FormData();
		fd_btnMentatPathFileExplorer.top = new FormAttachment(textMentatPath, -2, SWT.TOP);
		fd_btnMentatPathFileExplorer.left = new FormAttachment(textMentatPath, 5, SWT.RIGHT);
		btnMentatPathFileExplorer.setLayoutData(fd_btnMentatPathFileExplorer);
		btnMentatPathFileExplorer.setText("...");
		
		Label lblTexteditorPath = new Label(container, SWT.NONE);
		FormData fd_lblTexteditorPath = new FormData();
		fd_lblTexteditorPath.top = new FormAttachment(textMentatPath, 20);
		fd_lblTexteditorPath.left = new FormAttachment(lblMarcPath, 0, SWT.LEFT);
		lblTexteditorPath.setLayoutData(fd_lblTexteditorPath);
		lblTexteditorPath.setText("TextEditor path");
		
		textTextEditorPath = new Text(container, SWT.BORDER);
		textTextEditorPath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				TextEditorPath = text.getText().trim();
			}
		});
		FormData fd_textTextEditorPath = new FormData();
		fd_textTextEditorPath.top = new FormAttachment(lblTexteditorPath, 6);
		fd_textTextEditorPath.left = new FormAttachment(textMarcPath, 0, SWT.LEFT);
		fd_textTextEditorPath.right = new FormAttachment(textMarcPath, 0, SWT.RIGHT);
		textTextEditorPath.setLayoutData(fd_textTextEditorPath);
		textTextEditorPath.setText(MC.getPreferencesObj().getPreferencesValue(Preferences.TextEditorPath));
		
		final Button btnTextEditorPathFileExplorer = new Button(container, SWT.NONE);
		btnTextEditorPathFileExplorer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(btnTextEditorPathFileExplorer.getShell(),SWT.OPEN);
				dlg.setText("Select TextEditor execution file.");
				
				String [] extNames = {"All(*.*)"};
				String [] extType = {"*.*"};
				
				dlg.setFilterNames(extNames);
				dlg.setFilterExtensions(extType);
				// Open ProjectFolder.
				//dlg.setFilterPath(this.getAppPath());
				
				dlg.setFilterNames(extNames);
				String path = dlg.open();
				if (path != null){
					textTextEditorPath.setText(path);
				}
			}
		});
		FormData fd_btnTextEditorPathFileExplorer = new FormData();
		fd_btnTextEditorPathFileExplorer.top = new FormAttachment(textTextEditorPath, -2, SWT.TOP);
		fd_btnTextEditorPathFileExplorer.left = new FormAttachment(textTextEditorPath, 5, SWT.RIGHT);
		btnTextEditorPathFileExplorer.setLayoutData(fd_btnTextEditorPathFileExplorer);
		btnTextEditorPathFileExplorer.setText("...");
		
		Label lblExcelPath = new Label(container, SWT.NONE);
		FormData fd_lblExcelPath = new FormData();
		fd_lblExcelPath.top = new FormAttachment(textTextEditorPath, 20);
		fd_lblExcelPath.left = new FormAttachment(lblMarcPath, 0, SWT.LEFT);
		lblExcelPath.setLayoutData(fd_lblExcelPath);
		lblExcelPath.setText("Excel Path");
		
		textExcelPath = new Text(container, SWT.BORDER);
		textExcelPath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				ExcelPath = text.getText().trim();
			}
		});
		FormData fd_textExcelPath = new FormData();
		fd_textExcelPath.top = new FormAttachment(lblExcelPath, 6);
		fd_textExcelPath.left = new FormAttachment(textMarcPath, 0, SWT.LEFT);
		fd_textExcelPath.right = new FormAttachment(textMarcPath, 0, SWT.RIGHT);
		textExcelPath.setLayoutData(fd_textExcelPath);
		textExcelPath.setText(MC.getPreferencesObj().getPreferencesValue(Preferences.ExcelPath));
		
		final Button btnExcelPathFileExplorer = new Button(container, SWT.NONE);
		btnExcelPathFileExplorer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(btnExcelPathFileExplorer.getShell(),SWT.OPEN);
				dlg.setText("Select Excel execution file.");
				
				String [] extNames = {"All(*.*)"};
				String [] extType = {"*.*"};
				
				dlg.setFilterNames(extNames);
				dlg.setFilterExtensions(extType);
				// Open ProjectFolder.
				//dlg.setFilterPath(this.getAppPath());
				
				dlg.setFilterNames(extNames);
				String path = dlg.open();
				if (path != null){
					textExcelPath.setText(path);
				}
			}
		});
		FormData fd_btnExcelPathFileExplorer = new FormData();
		fd_btnExcelPathFileExplorer.top = new FormAttachment(textExcelPath, -2, SWT.TOP);
		fd_btnExcelPathFileExplorer.left = new FormAttachment(textExcelPath, 5, SWT.RIGHT);
		btnExcelPathFileExplorer.setLayoutData(fd_btnExcelPathFileExplorer);
		btnExcelPathFileExplorer.setText("...");
		
		Label lblCommandSovling = new Label(container, SWT.NONE);
		FormData fd_lblCommandSovling = new FormData();
		fd_lblCommandSovling.top = new FormAttachment(textExcelPath, 60);
		fd_lblCommandSovling.left = new FormAttachment(lblMarcPath, 0, SWT.LEFT);
		lblCommandSovling.setLayoutData(fd_lblCommandSovling);
		lblCommandSovling.setText("Command (Sovling)");
		
		textCommandSolving = new Text(container, SWT.BORDER);
		textCommandSolving.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				CommandSolving = text.getText().trim();
			}
		});
		FormData fd_textCommandSolving = new FormData();
		fd_textCommandSolving.top = new FormAttachment(lblCommandSovling, 6);
		fd_textCommandSolving.left = new FormAttachment(textMarcPath, 0, SWT.LEFT);
		fd_textCommandSolving.right = new FormAttachment(100, -10);
		textCommandSolving.setLayoutData(fd_textCommandSolving);
		textCommandSolving.setText(MC.getPreferencesObj().getPreferencesValue(Preferences.CommandSolving));
		
		Label lblCommandPost = new Label(container, SWT.NONE);
		FormData fd_lblCommandPost = new FormData();
		fd_lblCommandPost.top = new FormAttachment(textCommandSolving, 20);
		fd_lblCommandPost.left = new FormAttachment(lblMarcPath, 0, SWT.LEFT);
		lblCommandPost.setLayoutData(fd_lblCommandPost);
		lblCommandPost.setText("Command (Post)");
		
		textCommandPost = new Text(container, SWT.BORDER);
		textCommandPost.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				CommandPost = text.getText().trim();
			}
		});
		FormData fd_textCommandPost = new FormData();
		fd_textCommandPost.top = new FormAttachment(lblCommandPost, 6);
		fd_textCommandPost.left = new FormAttachment(textMarcPath, 0, SWT.LEFT);
		fd_textCommandPost.right = new FormAttachment(100, -10);
		textCommandPost.setLayoutData(fd_textCommandPost);
		textCommandPost.setText(MC.getPreferencesObj().getPreferencesValue(Preferences.CommandPost));
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button btnOk = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		btnOk.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				running();					
			}
		});
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 550);
	}
	
	private void running(){
		MC.getPreferencesObj().setPreferencesValue(Preferences.MarcPath, this.MarcPath);
		MC.getPreferencesObj().setPreferencesValue(Preferences.MentatPath, this.MentatPath);
		MC.getPreferencesObj().setPreferencesValue(Preferences.TextEditorPath, this.TextEditorPath);
		MC.getPreferencesObj().setPreferencesValue(Preferences.ExcelPath, this.ExcelPath);
		MC.getPreferencesObj().setPreferencesValue(Preferences.CommandSolving, this.CommandSolving);
		MC.getPreferencesObj().setPreferencesValue(Preferences.CommandPost, this.CommandPost);
		MC.Setting_Preferences_Run();
	}
}
