import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;


import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
/**
 * This class is used to represent the user management interface
 * @author Refined Storage developers
 *
 */
public class GuiManageUsers extends Composite {
	private Text inputIdRemove;
	private Text inputName;
	private Text inputPassword;


	/**
	 * Object super
	 */
	public Object Super;
	private Text inputIdAdd;
	/**
	 * This method is represents the user management interface along with his functionality
 	 * @param parent the composite parent
	 * @param style the desired style
	 * @param name the current user name
	 * @param Rs the refined storage
	 * @param Db the database
	 */
	public GuiManageUsers(Composite parent, int style, String name, Refined_storage Rs, DbFunctions Db) {
		super(parent, style);
		this.setVisible(false);
		this.setVisible(true);
		Super = this;
		
				Composite composite = new Composite(this, SWT.NONE);
				composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				composite.setBounds(0, 0, 869, 80);
				
						Button btnBack = new Button(composite, SWT.NONE);
						btnBack.setText("Back");
						btnBack.setBounds(10, 10, 75, 25);
						btnBack.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								try {
									((Control) Super).dispose();
									new GuiListUsers(parent, style, name, Rs, Db);
								} catch (Throwable e1) {
									e1.printStackTrace();
								}
							}
						});
						
								Label labelMain_1 = new Label(composite, SWT.NONE);
								labelMain_1.setText("Manage Users");
								labelMain_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
								labelMain_1.setFont(SWTResourceManager.getFont("Corbel", 20, SWT.NORMAL));
								labelMain_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
								labelMain_1.setBounds(342, 23, 229, 47);

		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite_1.setBounds(0, 504, 869, 80);


		Label labelSizing1 = new Label(this, SWT.NONE);
		labelSizing1.setBounds(804, 542, 55, 15);
		Label labelSizing2 = new Label(this, SWT.NONE);
		labelSizing2.setBounds(10, 10, 55, 15);



		String add1 = "admin", add2 ="employee";
		this.pack();
		this.setVisible(true);

		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2.setBounds(480, 86, 303, 361);

		Label labelRemove = new Label(composite_2, SWT.NONE);
		labelRemove.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		labelRemove.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelRemove.setBounds(87, 41, 118, 40);
		labelRemove.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		labelRemove.setAlignment(SWT.CENTER);
		labelRemove.setText("Remove");

		Label labelId2 = new Label(composite_2, SWT.NONE);
		labelId2.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		labelId2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelId2.setBounds(62, 151, 45, 20);
		labelId2.setText("ID");

		inputIdRemove = new Text(composite_2, SWT.BORDER);
		inputIdRemove.setBounds(128, 148, 112, 26);

		Button btnRemove = new Button(composite_2, SWT.NONE);
		btnRemove.setBounds(115, 275, 90, 30);
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String input2 = inputIdRemove.getText();
				int i2 = Db.CheckValidId(input2);


				try {
					int flag = Rs.get_admin().rem_user(Db, i2, Rs);

					if(flag == 0) {
						JOptionPane.showMessageDialog(null, "Successfully removed id: " +i2);
						clear("id2");
					}else if(flag == 1) {
						JOptionPane.showMessageDialog(null, "You can't delete yourself");
						clear("id2");
					}else if(flag == 2) {
						JOptionPane.showMessageDialog(null, "Invalid id");
						clear("id2");
					}else if(flag == 3) {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}



			}
		});
		btnRemove.setText("Remove");

		Composite composite_2_1 = new Composite(this, SWT.NONE);
		composite_2_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_2_1.setBounds(80, 86, 303, 361);

		Combo selectRole = new Combo(composite_2_1, SWT.NONE);
		selectRole.setBounds(155, 217, 100, 23);
		selectRole.add(add1);
		selectRole.add(add2);

		Button btnAdd = new Button(composite_2_1, SWT.NONE);
		btnAdd.setBounds(103, 273, 100, 30);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String input1 = inputIdAdd.getText();
				int i = Db.CheckValidId(input1);
				String value = (String)selectRole.getText();

				try {

					int flag = Rs.get_admin().add_user(Db, value, i, inputName.getText().toString(), inputPassword.getText().toString());

					if(flag == 0) {
						JOptionPane.showMessageDialog(null, "Successfully added id: "+i);
						clear("all");
					}else if(flag == 1) {
						JOptionPane.showMessageDialog(null, "This id is already being used");
						clear("id1");
					}else if(flag == 2) {
						JOptionPane.showMessageDialog(null, "Invalid id");
						clear("id1");
					}else if(flag == 3) {
						JOptionPane.showMessageDialog(null, "Invalid role");
						selectRole.setText("");
					}else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}

				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setText("Add");

		Label labelName = new Label(composite_2_1, SWT.NONE);
		labelName.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		labelName.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelName.setBounds(47, 150, 70, 20);
		labelName.setText("Name");

		Label labelPassword = new Label(composite_2_1, SWT.NONE);
		labelPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		labelPassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelPassword.setBounds(47, 185, 70, 20);
		labelPassword.setText("Password");


		Label labelRole = new Label(composite_2_1, SWT.NONE);
		labelRole.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		labelRole.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelRole.setBounds(47, 217, 70, 20);
		labelRole.setText("Role");

		Label labelId = new Label(composite_2_1, SWT.NONE);
		labelId.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		labelId.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelId.setBounds(47, 111, 70, 20);
		labelId.setText("ID");

		inputIdAdd = new Text(composite_2_1, SWT.BORDER);
		inputIdAdd.setBounds(155, 108, 100, 26);

		inputName = new Text(composite_2_1, SWT.BORDER);
		inputName.setBounds(155, 147, 100, 26);

		inputPassword = new Text(composite_2_1, SWT.BORDER);
		inputPassword.setBounds(155, 182, 100, 26);

		Label labelAdd = new Label(composite_2_1, SWT.NONE);
		labelAdd.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		labelAdd.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelAdd.setBounds(115, 28, 76, 40);
		labelAdd.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		labelAdd.setAlignment(SWT.CENTER);
		labelAdd.setText("Add");

		Label labelLine = new Label(this, SWT.NONE);
		labelLine.setText("______________");
		labelLine.setFont(SWTResourceManager.getFont("Corbel", 16, SWT.NORMAL));
		labelLine.setBounds(361, 461, 147, 40);
	}


	/**
	 * This method is used to clear the text boxes in the interface
	 * @param string : "all" clears all text boxes except the Id for 
	 * remove box, "id1" clears only the Id for register box, "id2" clears 
	 * only the Id for remove box
	 */
	protected void clear(String string) {
		if(string.equals("all")){
			inputName.setText("");
			inputIdAdd.setText("");
			inputPassword.setText("");
		}
		else if(string.equals("id1")) {
			inputIdAdd.setText("");
		}
		else if(string.equals("id2")) {
			inputIdRemove.setText("");
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
