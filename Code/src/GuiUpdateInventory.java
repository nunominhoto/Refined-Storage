import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;


import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * This class represents the inventory update interface
 * @author Refined Storage developers
 *
 */
public class GuiUpdateInventory extends Composite {
	private Text inputID1;
	private Text inputName;
	private Text inputBrand;
	/**
	 * Object super
	 */
	public Object Super;
	private Text inputID2;
	/**
	 * This method is represents the inventory update interface and his functions
	 * @param parent the composite parent
	 * @param style the wanted style
	 * @param name the name of the current user
	 * @param Rs the refined storage
	 * @param Db the database
	 */
	public GuiUpdateInventory(Composite parent, int style, String name, Refined_storage Rs, DbFunctions Db) {
		super(parent, style);
		setLayout(null);
		Super = this;
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite.setBounds(0, 0, 869, 80);
		
		Label labelMain_1 = new Label(composite, SWT.NONE);
		labelMain_1.setText("Material");
		labelMain_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelMain_1.setFont(SWTResourceManager.getFont("Corbel", 20, SWT.NORMAL));
		labelMain_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		labelMain_1.setBounds(359, 23, 89, 47);
		
		Button btnBack = new Button(composite, SWT.NONE);
		btnBack.setBounds(10, 10, 75, 25);
		btnBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					((Control) Super).dispose();
					new GuiAdminMenu(parent, style, name, Rs, Db);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}	
			}
		});
		btnBack.setText("Back");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite_1.setBounds(0, 504, 869, 80);
		
		Composite composite_2_1 = new Composite(this, SWT.NONE);
		composite_2_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_2_1.setBounds(80, 86, 303, 361);
		
		Label labelName_1 = new Label(composite_2_1, SWT.NONE);
		labelName_1.setText("Name");
		labelName_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelName_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		labelName_1.setBounds(47, 150, 70, 20);
		
		Label labelPassword = new Label(composite_2_1, SWT.NONE);
		labelPassword.setText("Brand");
		labelPassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		labelPassword.setBounds(47, 185, 70, 20);
		
		Label labelRole = new Label(composite_2_1, SWT.NONE);
		labelRole.setText("Quantity");
		labelRole.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelRole.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		labelRole.setBounds(47, 217, 70, 20);
		
		Label labelId = new Label(composite_2_1, SWT.NONE);
		labelId.setText("ID");
		labelId.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelId.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		labelId.setBounds(47, 111, 70, 20);
		
		Label labelAdd = new Label(composite_2_1, SWT.NONE);
		labelAdd.setText("Register");
		labelAdd.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelAdd.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		labelAdd.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		labelAdd.setAlignment(SWT.CENTER);
		labelAdd.setBounds(102, 28, 102, 40);
		
		inputID1 = new Text(composite_2_1, SWT.BORDER);
		inputID1.setBounds(155, 108, 100, 26);
		
		inputName = new Text(composite_2_1, SWT.BORDER);
		inputName.setBounds(155, 147, 100, 26);
		
		inputBrand = new Text(composite_2_1, SWT.BORDER);
		inputBrand.setBounds(155, 182, 100, 26);
		
		Spinner spinner = new Spinner(composite_2_1, SWT.BORDER);
		spinner.setBounds(155, 214, 100, 26);
		
		DateTime inputDate = new DateTime(composite_2_1, SWT.BORDER);
		inputDate.setBounds(102, 258, 102, 28);
		
		Button btnRegister = new Button(composite_2_1, SWT.NONE);
		btnRegister.setBounds(95, 321, 90, 30);
		btnRegister.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				String input1 = inputID1.getText();
				int id = Db.CheckValidId(input1);
				String date = getdate(inputDate);
				
				int flag = Rs.get_admin().add_item_to_db(Db, id, inputName.getText().toString(), inputBrand.getText().toString(), spinner.getSelection(), date);
				
				if(flag == 0) {
					JOptionPane.showMessageDialog(null, "Added: "+inputName.getText().toString());
					clear("all");
				}else if(flag == 1) {
					JOptionPane.showMessageDialog(null, "ID ALREADY EXISTS: " +id);
					inputID1.setText("");
					clear("id");
				}else if(flag == 2) {
					JOptionPane.showMessageDialog(null, "ID is invalid ");
				}else if(flag == 3) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			
			}
		});
		btnRegister.setText("Register");
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_2.setBounds(480, 86, 303, 361);
		
		Label lblDelete = new Label(composite_2, SWT.NONE);
		lblDelete.setText("Delete");
		lblDelete.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDelete.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		lblDelete.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblDelete.setAlignment(SWT.CENTER);
		lblDelete.setBounds(106, 39, 118, 40);
		
		Label labelId2 = new Label(composite_2, SWT.NONE);
		labelId2.setText("ID");
		labelId2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelId2.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		labelId2.setBounds(52, 157, 45, 20);
		
		inputID2 = new Text(composite_2, SWT.BORDER);
		inputID2.setBounds(112, 154, 112, 26);
		
		Button btnDelete = new Button(composite_2, SWT.NONE);
		btnDelete.setBounds(121, 321, 90, 30);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String input2 = inputID2.getText();
				int i2 = Db.CheckValidId(input2);
				
				
				int flag = Rs.get_admin().remove_item_from_db(Db, i2);
				
				if(flag == 0) {
					JOptionPane.showMessageDialog(null, "Deleted: "+i2);
					clear("id2");
				}else if(flag == 1) {
					JOptionPane.showMessageDialog(null, "Invalid Id");
					clear("id2");				
				}else if(flag == 2) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnDelete.setText("Delete");
		
		//ensures correct window size
		Label labelSizing1 = new Label(this, SWT.NONE);
		labelSizing1.setBounds(820, 569, 49, 15);
		Label labelSizing2 = new Label(this, SWT.NONE);
		labelSizing2.setBounds(10, 10, 55, 15);
		
		Label labelLine = new Label(this, SWT.NONE);
		labelLine.setText("______________");
		labelLine.setFont(SWTResourceManager.getFont("Corbel", 16, SWT.NORMAL));
		labelLine.setBounds(361, 461, 147, 40);
		
		Label labelMain = new Label(this, SWT.NONE);
		labelMain.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		labelMain.setText("Material");
		labelMain.setBounds(362, 30, 102, 40);
		
		this.pack();
		this.setVisible(true);
		

	}


	/**
	 * This method is used to clear the text boxes in the interface
	 * @param string : "all" clears all text boxes except the Id for 
	 * remove box, "id1" clears only the Id for register box, "id2" clears 
	 * only the Id for remove box
	 */
	protected void clear(String string) {
		if(string.equals("all")) {
			inputID1.setText("");
			inputName.setText("");
			inputBrand.setText("");
		}
		else if(string.equals("id1")) inputID1.setText("");
		else if(string.equals("id2")) inputID2.setText("");
	}
	/**
	 * This method is user to pass DateTime variable to string
	 * @param inputDate the DateTime variable intended
	 * @return string with the date time format (days months years)
	 */
	protected String getdate(DateTime inputDate) {
		int month = inputDate.getMonth()+1;
		int day = inputDate.getDay();
		int year = inputDate.getYear();
		String days = Integer.toString(day);
		String months = Integer.toString(month);
		String years = Integer.toString(year);
		String s = days +" "+ months +" " + years;
	
		return s;
	}

	@Override
	protected void checkSubclass() {}
}
