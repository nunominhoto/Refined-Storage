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
 * This class is used to represent the Login interface
 * @author Refined Storage developers
 *
 */

public class GuiLogin extends Composite {
	private Text inputId;
	private Text inputPassword;
	/**
	 * Object Super	
	 */
	public Object Super;
	/**
	 * This method represents the login interface and his functionality
	 * @param parent the composite parent
	 * @param style the desired style
	 * @param Rs the refined storage
	 * @throws Throwable error generating interface
	 */
	public GuiLogin(Composite parent, int style, Refined_storage Rs) throws Throwable{
		super(parent, style);
		setBackground(SWTResourceManager.getColor(255, 255, 255));
		Super = this;
		setLayout(null);
		
		Label labelId = new Label(this, SWT.NONE);
		labelId.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelId.setBounds(532, 185, 29, 31);
		labelId.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		labelId.setText("ID");
		
		Label labelPassword = new Label(this, SWT.NONE);
		labelPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelPassword.setBounds(532, 302, 87, 31);
		labelPassword.setText("Password");
		labelPassword.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		
		inputId = new Text(this, SWT.BORDER);
		inputId.setBounds(532, 222, 230, 30);
		
		inputPassword = new Text(this, SWT.BORDER);
		inputPassword.setBounds(532, 339, 230, 30);
		inputPassword.setEchoChar('*');
		
		
		Button btnSubmit = new Button(this, SWT.NONE);
		btnSubmit.setGrayed(true);
		btnSubmit.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		btnSubmit.setBounds(594, 435, 111, 57);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = Rs.get_Db().CheckValidId(inputId.getText());
				if (i!=0) {
						try {
						if(Rs.perform_a_login(i, inputPassword.getText())) {
							if(Rs.get_user_prio() == 1) {
								JOptionPane.showMessageDialog(null, Rs.get_admin().get_name() + " logged in.");
								((Control) Super).setVisible(false);
								new GuiAdminMenu(parent, style, Rs.get_admin().get_name(), Rs, Rs.get_Db());
							}else if(Rs.get_user_prio() == 2) {
								JOptionPane.showMessageDialog(null, Rs.get_employee().get_name() + " logged in.");
								((Control) Super).setVisible(false);
								new GuiEmployeeMenu(parent, style, Rs.get_employee().get_name(), Rs, Rs.get_Db());
							}
						}
						else 
							JOptionPane.showMessageDialog(null, "Wrong credentials.");
					} catch (Throwable e1) {
						System.err.println(e1.getMessage());
					}
						
				}else if(i==0) JOptionPane.showMessageDialog(null, "Wrong credentials.");
			}	
		});
		btnSubmit.setText("Submit");
		
	
		//Element that assures correct window size
		Label labelSizing = new Label(this, SWT.NONE);
		labelSizing.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelSizing.setBounds(797, 555, 55, 15);
		
		this.pack();
		this.setVisible(true);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite.setBounds(0, 0, 421, 570);
		composite.setLayout(null);
		
		Label imageLogin = new Label(composite, SWT.NONE);
		imageLogin.setBounds(0, -35, 435, 420);
		imageLogin.setImage(SWTResourceManager.getImage("C:\\RefinedStorage\\Capture.PNG"));
		
		Label imageLogin_1 = new Label(composite, SWT.NONE);
		imageLogin_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		imageLogin_1.setImage(SWTResourceManager.getImage("C:\\RefinedStorage\\logo.png"));
		imageLogin_1.setBounds(154, 391, 112, 153);
		
		Label labelMain = new Label(this, SWT.NONE);
		labelMain.setBounds(420, 79, 432, 41);
		labelMain.setAlignment(SWT.CENTER);
		labelMain.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		labelMain.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		labelMain.setFont(SWTResourceManager.getFont("Corbel", 25, SWT.NORMAL));
		labelMain.setText("Refined Storage");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_1.setBounds(420, 0, 432, 80);
		
		
		
	}
	
	@Override
	protected void checkSubclass() {
	}
}