package com.js.ens.coil.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.js.ens.coil.core.MainController;
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

public class NewDlg extends Dialog {
	private MainController MC = MainController.getInstatnce();
	private Text textProjectName;
	private Text textWorkspace;
	
	private String projectName;
	private String workspace;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public NewDlg(Shell parentShell) {
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
		
		Label lblNewProject = new Label(container, SWT.NONE);
		if(myUtil.checkOS().equals("window")){
			lblNewProject.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));	
		}else{
			lblNewProject.setFont(SWTResourceManager.getFont(".SF NS Text", 12, SWT.BOLD));
		}
		FormData fd_lblNewProject = new FormData();
		fd_lblNewProject.top = new FormAttachment(0, 10);
		fd_lblNewProject.left = new FormAttachment(0, 10);
		lblNewProject.setLayoutData(fd_lblNewProject);
		lblNewProject.setText("New Project");
		
		Label lblProjectName = new Label(container, SWT.NONE);
		FormData fd_lblProjectName = new FormData();
		fd_lblProjectName.top = new FormAttachment(lblNewProject, 30);
		fd_lblProjectName.left = new FormAttachment(lblNewProject, 10, SWT.LEFT);
		lblProjectName.setLayoutData(fd_lblProjectName);
		lblProjectName.setText("Project Name");
		
		textProjectName = new Text(container, SWT.BORDER);
		textProjectName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				projectName = text.getText().trim();
			}
		});
		FormData fd_textProjectName = new FormData();
		fd_textProjectName.top = new FormAttachment(lblProjectName, -2, SWT.TOP);
		fd_textProjectName.left = new FormAttachment(lblProjectName, 10);
		fd_textProjectName.right = new FormAttachment(lblProjectName, 110,SWT.RIGHT);
		textProjectName.setLayoutData(fd_textProjectName);
		
		Label lblWorkspacePath = new Label(container, SWT.NONE);
		FormData fd_lblWorkspacePath = new FormData();
		fd_lblWorkspacePath.top = new FormAttachment(lblProjectName, 20);
		fd_lblWorkspacePath.left = new FormAttachment(lblProjectName, 0, SWT.LEFT);
		lblWorkspacePath.setLayoutData(fd_lblWorkspacePath);
		lblWorkspacePath.setText("Workspace Path");
		
		textWorkspace = new Text(container, SWT.BORDER);
		textWorkspace.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				workspace = text.getText().trim();
			}
		});
		FormData fd_textWorkspace = new FormData();
		fd_textWorkspace.top = new FormAttachment(lblWorkspacePath, 10);
		fd_textWorkspace.left = new FormAttachment(lblProjectName, 0, SWT.LEFT);
		fd_textWorkspace.right = new FormAttachment(100, - 50);
		textWorkspace.setLayoutData(fd_textWorkspace);
		textWorkspace.setText(MC.getBaseWorkspace());
		if(!myUtil.checkPath(MC.getBaseWorkspace())) myUtil.makeDirectory(MC.getBaseWorkspace());
		
		
		final Button btnExplorer = new Button(container, SWT.NONE);
		btnExplorer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog  dlg = new DirectoryDialog(btnExplorer.getShell());
				dlg.setFilterPath(MC.getBaseWorkspace());
				dlg.setMessage("Select a workspace");
				String path = dlg.open();
				if (path != null){
					textWorkspace.setText(path);
				}
			}
		});
		FormData fd_btnExplorer = new FormData();
		fd_btnExplorer.top = new FormAttachment(textWorkspace, -2, SWT.TOP);
		fd_btnExplorer.left = new FormAttachment(textWorkspace, 5,SWT.RIGHT);
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
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(projectName != null && workspace != null){
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
		MC.File_New_Run(projectName, workspace);
	}
}
