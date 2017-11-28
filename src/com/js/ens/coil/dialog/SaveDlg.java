package com.js.ens.coil.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.js.ens.coil.core.AppFolder;
import com.js.ens.coil.core.MainController;
import com.js.util.myUtil;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class SaveDlg extends Dialog {
	private MainController MC = MainController.getInstatnce();
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public SaveDlg(Shell parentShell) {
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
		
		Label lblSave = new Label(container, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			lblSave.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));	
		}else{
			lblSave.setFont(SWTResourceManager.getFont(".SF NS Text", 12, SWT.BOLD));
		}
		FormData fd_lblSave = new FormData();
		fd_lblSave.top = new FormAttachment(0, 10);
		fd_lblSave.left = new FormAttachment(0, 10);
		lblSave.setLayoutData(fd_lblSave);
		lblSave.setText("Save");
		
		Label lblDoYouSave = new Label(container, SWT.NONE);
		FormData fd_lblDoYouSave = new FormData();
		fd_lblDoYouSave.top = new FormAttachment(lblSave, 15);
		fd_lblDoYouSave.left = new FormAttachment(10);
		lblDoYouSave.setLayoutData(fd_lblDoYouSave);
		lblDoYouSave.setText("Do you save SimcosDB?");
		
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
				if(MC.getCoilDBObj()!=null){
					String dbFile = myUtil.setPath(MC.getCoilDBObj().getProjectFolderPath(), MC.getProjectName()+"_"+AppFolder.dbFileName);
					//System.out.println("db file path : "+dbFile);
					if(myUtil.checkPath(dbFile)){
						running();					
					}else{
						//System.out.println("running else");
					}
					
				}else {
					//System.out.println("DB object is null");
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
		return new Point(300, 200);
	}
	
	private void running(){
		MC.File_Save_Run();
	}
}

