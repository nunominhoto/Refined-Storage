
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * This class is used to represent the password change interface
 * @author Refined Storage developers
 *
 */

public class GuiChangePassword extends Composite {
	/**
	 * Object Super
	 */
	public Object Super;
	private Text inputOld;
	private Text inputNew;
	private Text inputConfirm;
	/**
	 * This method represents the password change interface and his main functionalities
	 * @param parent the composite parent
	 * @param style the desired style
	 * @param name the current user name
	 * @param Rs the refined storage
	 * @param Db the database
	 * @throws Throwable error generating interface
	 */
	public GuiChangePassword(Composite parent, int style, String name, Refined_storage Rs, DbFunctions Db) throws Throwable {
		super(parent, style);
		setLayout(null);
		Super = this;
		
		this.setVisible(false);
		this.setVisible(true);
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite_1.setBounds(0, 504, 869, 80);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite.setBounds(0, 0, 869, 80);
		
		Label labelMain_1 = new Label(composite, SWT.NONE);
		labelMain_1.setText("Change Password");
		labelMain_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelMain_1.setFont(SWTResourceManager.getFont("Corbel", 20, SWT.NORMAL));
		labelMain_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		labelMain_1.setBounds(337, 23, 195, 47);
		
		Button btnBack = new Button(composite, SWT.NONE);
		btnBack.setBounds(10, 10, 75, 25);
		btnBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {		
				try {
					((Control) Super).dispose();
					new GuiSettings(parent, style, name, Rs, Db);
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBack.setText("Back");
		
		
		//Element that assures correct window size
		Label labelSizing1 = new Label(this, SWT.NONE);
		labelSizing1.setBounds(763, 542, 106, 41);
		
		Label labelLine = new Label(this, SWT.NONE);
		labelLine.setText("______________");
		labelLine.setFont(SWTResourceManager.getFont("Corbel", 16, SWT.NORMAL));
		labelLine.setBounds(361, 461, 147, 41);
		
		this.setVisible(true);
		
		Label labelSizing2 = new Label(this, SWT.NONE);
		labelSizing2.setBounds(10, 10, 55, 15);
		
		this.pack();
		this.setVisible(true);
		
		Composite composite_2_1 = new Composite(this, SWT.NONE);
		composite_2_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_2_1.setBounds(235, 86, 398, 361);
		
		Label lblNewPassword = new Label(composite_2_1, SWT.NONE);
		lblNewPassword.setText("New Password");
		lblNewPassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblNewPassword.setBounds(33, 130, 80, 20);
		
		Label lblConfirmNewPassword = new Label(composite_2_1, SWT.NONE);
		lblConfirmNewPassword.setText("Confirm New Password");
		lblConfirmNewPassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblConfirmNewPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblConfirmNewPassword.setBounds(33, 188, 127, 47);
		
		Label lblOldPassword = new Label(composite_2_1, SWT.NONE);
		lblOldPassword.setText("Old Password");
		lblOldPassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblOldPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblOldPassword.setBounds(33, 72, 80, 20);
		
		Button btnConfirm = new Button(composite_2_1, SWT.NONE);
		btnConfirm.setBounds(149, 274, 100, 30);
		btnConfirm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
				int flag = 4;
				
				if(Rs.get_user_prio() == 1) {					
					flag = Rs.get_admin().change_password(Db, inputOld.getText(), inputNew.getText(), inputConfirm.getText());					
				}else if(Rs.get_user_prio() == 2){				
					flag = Rs.get_employee().change_password(Db, inputOld.getText(), inputNew.getText(), inputConfirm.getText());					
				}
				
				if(flag == 0) {
					JOptionPane.showMessageDialog(null, "Password changed successfully");
				}else if(flag == 1) {
					JOptionPane.showMessageDialog(null, "New password can't be the same as the old");
				}else if(flag == 2) {
					JOptionPane.showMessageDialog(null, "New password must match with the confirmation");
				}else if(flag == 3) {
					JOptionPane.showMessageDialog(null, "Incorrect old password");	
				}else {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnConfirm.setText("Confirm");
		
		inputConfirm = new Text(composite_2_1, SWT.BORDER | SWT.PASSWORD);
		inputConfirm.setBounds(183, 185, 162, 29);
		
		inputNew = new Text(composite_2_1, SWT.BORDER | SWT.PASSWORD);
		inputNew.setBounds(183, 127, 162, 29);
		
		inputOld = new Text(composite_2_1, SWT.BORDER | SWT.PASSWORD);
		inputOld.setBounds(183, 69, 162, 29);
		
		
		
		
		
		
		
		 
		
	}

	@Override
	protected void checkSubclass() {
	}
}