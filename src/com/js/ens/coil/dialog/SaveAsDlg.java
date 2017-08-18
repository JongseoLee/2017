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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.js.ens.coil.core.MainController;
import com.js.util.myUtil;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class SaveAsDlg extends Dialog {
	private MainController MC = MainController.getInstatnce();
	
	private Text textNewProjectName;
	private Text textNewWorkspace;
	private String newProjectName;
	private String newWorkspace;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public SaveAsDlg(Shell parentShell) {
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
		
		Label lblSaveAs = new Label(container, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			lblSaveAs.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));	
		}else{
			lblSaveAs.setFont(SWTResourceManager.getFont(".SF NS Text", 12, SWT.BOLD));
		}
		FormData fd_lblSaveAs = new FormData();
		fd_lblSaveAs.top = new FormAttachment(0, 10);
		fd_lblSaveAs.left = new FormAttachment(0, 10);
		lblSaveAs.setLayoutData(fd_lblSaveAs);
		lblSaveAs.setText("Save as");
		
		Label lblDoYouSaveAs = new Label(container, SWT.NONE);
		FormData fd_lblDoYouSavaAs = new FormData();
		fd_lblDoYouSavaAs.top = new FormAttachment(lblSaveAs, 30);
		fd_lblDoYouSavaAs.left = new FormAttachment(lblSaveAs, 10, SWT.LEFT);
		lblDoYouSaveAs.setLayoutData(fd_lblDoYouSavaAs);
		lblDoYouSaveAs.setText("Do you save as SimcomDB?");
		
		Label lblProjectName = new Label(container, SWT.NONE);
		FormData fd_lblProjectName = new FormData();
		fd_lblProjectName.top = new FormAttachment(lblDoYouSaveAs, 20);
		fd_lblProjectName.left = new FormAttachment(lblSaveAs, 10, SWT.LEFT);
		lblProjectName.setLayoutData(fd_lblProjectName);
		lblProjectName.setText("New Project Name");
		
		textNewProjectName = new Text(container, SWT.BORDER);
		textNewProjectName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				newProjectName = text.getText().trim();
			}
		});
		FormData fd_textNewProjectName = new FormData();
		fd_textNewProjectName.top = new FormAttachment(lblProjectName, -2, SWT.TOP);
		fd_textNewProjectName.left = new FormAttachment(lblProjectName, 10);
		fd_textNewProjectName.right = new FormAttachment(lblProjectName, 110,SWT.RIGHT);
		textNewProjectName.setLayoutData(fd_textNewProjectName);
		
		Label lblWorkspacePath = new Label(container, SWT.NONE);
		FormData fd_lblWorkspacePath = new FormData();
		fd_lblWorkspacePath.top = new FormAttachment(lblProjectName, 20);
		fd_lblWorkspacePath.left = new FormAttachment(lblProjectName, 0, SWT.LEFT);
		lblWorkspacePath.setLayoutData(fd_lblWorkspacePath);
		lblWorkspacePath.setText("New Workspace Path");
		
		textNewWorkspace = new Text(container, SWT.BORDER);
		textNewWorkspace.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				newWorkspace = text.getText().trim();
			}
		});
		FormData fd_textNewWorkspace = new FormData();
		fd_textNewWorkspace.top = new FormAttachment(lblWorkspacePath, 10);
		fd_textNewWorkspace.left = new FormAttachment(lblProjectName, 0, SWT.LEFT);
		fd_textNewWorkspace.right = new FormAttachment(100, - 50);
		textNewWorkspace.setLayoutData(fd_textNewWorkspace);
		textNewWorkspace.setText(MC.getBaseWorkspace());
		if(!myUtil.checkPath(MC.getBaseWorkspace())) myUtil.makeDirectory(MC.getBaseWorkspace());
		

		final Button btnExplorer = new Button(container, SWT.NONE);
		btnExplorer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog  dlg = new DirectoryDialog(btnExplorer.getShell());
				dlg.setFilterPath(MC.getBaseWorkspace());
				dlg.setMessage("Select a new workspace");
				String path = dlg.open();
				if (path != null){
					textNewWorkspace.setText(path);
				}
			}
		});
		FormData fd_btnExplorer = new FormData();
		fd_btnExplorer.top = new FormAttachment(textNewWorkspace, -2, SWT.TOP);
		fd_btnExplorer.left = new FormAttachment(textNewWorkspace, 5,SWT.RIGHT);
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
				if(MC.getCoilDBObj() != null){
					if(newProjectName != null && newWorkspace != null){
						running();
					}
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
		MC.File_SaveAs_Run(newProjectName, newWorkspace);
	}

}
