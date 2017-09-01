package com.js.ens.coil.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;

import com.js.ens.coil.core.MainController;
import com.js.util.myUtil;

public class OpenDlg extends Dialog {
	private MainController MC = MainController.getInstatnce();
	
	private Text textDBFile;
	
	private String textDBFilePath = "null";
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public OpenDlg(Shell parentShell) {
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
		
		Label lblOpen = new Label(container, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			lblOpen.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));	
		}else{
			lblOpen.setFont(SWTResourceManager.getFont(".SF NS Text", 12, SWT.BOLD));
		}
		FormData fd_lblOpen = new FormData();
		fd_lblOpen.top = new FormAttachment(0, 10);
		fd_lblOpen.left = new FormAttachment(0, 10);
		lblOpen.setLayoutData(fd_lblOpen);
		lblOpen.setText("Open");
		
		Label lblSelectDbFile = new Label(container, SWT.NONE);
		FormData fd_lblSelectDbFile = new FormData();
		fd_lblSelectDbFile.top = new FormAttachment(lblOpen, 30);
		fd_lblSelectDbFile.left = new FormAttachment(lblOpen, 10, SWT.LEFT);
		lblSelectDbFile.setLayoutData(fd_lblSelectDbFile);
		lblSelectDbFile.setText("Select Simcos db(SimcosDB.sdb) File");
		
		textDBFile = new Text(container, SWT.BORDER);
		textDBFile.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				textDBFilePath = text.getText().trim();
			}
		});
		FormData fd_textWorkspace = new FormData();
		fd_textWorkspace.top = new FormAttachment(lblSelectDbFile, 10);
		fd_textWorkspace.left = new FormAttachment(lblSelectDbFile, 0, SWT.LEFT);
		fd_textWorkspace.right = new FormAttachment(100, - 50);
		textDBFile.setLayoutData(fd_textWorkspace);
		
		final Button btnExplorer = new Button(container, SWT.NONE);
		btnExplorer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(btnExplorer.getShell(),SWT.OPEN);
				dlg.setText("Select SimcosDB file.");
				
				String [] extNames = {"DBFile(*.sdb)"};
				String [] extType = {"*.sdb"};
				
				dlg.setFilterNames(extNames);
				dlg.setFilterExtensions(extType);
				// Open ProjectFolder.
				//dlg.setFilterPath(this.getAppPath());
				
				dlg.setFilterNames(extNames);
				String path = dlg.open();
				if (path == null){
					textDBFile.setText("");
				}else {
					textDBFile.setText(path);
				}
			}
		});
		FormData fd_btnExplorer = new FormData();
		fd_btnExplorer.top = new FormAttachment(textDBFile, -2, SWT.TOP);
		fd_btnExplorer.left = new FormAttachment(textDBFile, 5,SWT.RIGHT);
		btnExplorer.setLayoutData(fd_btnExplorer);
		btnExplorer.setText("...");

		
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
				if(!textDBFilePath.equals("null")){
					running();					
				}
			}
		});
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
	
	private void running(){
		MC.File_Open_Run(textDBFilePath);
	}
}
