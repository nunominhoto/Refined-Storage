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

//import com.sun.media.sound.Toolkit;

import org.eclipse.swt.widgets.Spinner;

/**
 * This class is used to represent the stock management interface
 * @author Refined Storage developers
 *
 */

public class GuiManageInventory extends Composite {
	/**
	 * Object super
	 */
	public Object Super;
	private Text inputId;
	/**
	 * This method represents the stock management interface along with his functionality
	 * @param parent the composite parent
	 * @param style the desired style
	 * @param name the current user name
	 * @param Rs refined storage
	 * @param Db the database
	 * @throws Throwable error generating interface
	 */
	public GuiManageInventory(Composite parent, int style, String name, Refined_storage Rs, DbFunctions Db) throws Throwable {
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
		labelMain_1.setText("Stock Management");
		labelMain_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelMain_1.setFont(SWTResourceManager.getFont("Corbel", 20, SWT.NORMAL));
		labelMain_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		labelMain_1.setBounds(342, 23, 278, 47);
		Button btnBack = new Button(composite, SWT.NONE);
		btnBack.setBounds(10, 10, 75, 25);
		btnBack.setText("Back");
		btnBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					((Control) Super).dispose();
					if(Rs.get_user_prio() == 1) {
						new GuiAdminMenu(parent, style, name, Rs, Db);
					}else if(Rs.get_user_prio() == 2) {
						new GuiEmployeeMenu(parent, style, name, Rs, Db);
					}	
				} catch (Throwable e1) {
					e1.printStackTrace();
				}	
			}	
		});
		
		
		//Element that assures correct window size
		Label labelSizing1 = new Label(this, SWT.NONE);
		labelSizing1.setBounds(766, 559, 103, 25);
		Label labelSizing2 = new Label(this, SWT.NONE);
		labelSizing2.setBounds(10, 10, 55, 15);
		
		Label labelLine = new Label(this, SWT.NONE);
		labelLine.setText("______________");
		labelLine.setFont(SWTResourceManager.getFont("Corbel", 16, SWT.NORMAL));
		labelLine.setBounds(361, 461, 147, 40);
		
		this.setVisible(true);
		
		this.pack();
		this.setVisible(true);
		
		Composite composite_2_1 = new Composite(this, SWT.NONE);
		composite_2_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_2_1.setBounds(402, 86, 303, 361);
		
		Label lblQuantity = new Label(composite_2_1, SWT.NONE);
		lblQuantity.setText("Quantity");
		lblQuantity.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblQuantity.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblQuantity.setBounds(73, 194, 70, 20);
		
		Button btnConfirm = new Button(composite_2_1, SWT.NONE);
		btnConfirm.setBounds(145, 268, 100, 32);
		btnConfirm.setText("Confirm");
		
		
		Button btnRemove = new Button(composite_2_1, SWT.RADIO);
		btnRemove.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnRemove.setBounds(196, 99, 86, 28);
		btnRemove.setText("Remove");
		
		Button btnAdd = new Button(composite_2_1, SWT.FLAT | SWT.RADIO);
		btnAdd.setSelection(true);
		btnAdd.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnAdd.setBounds(73, 99, 86, 28);
		btnAdd.setText("Add");
		
		Spinner spinner = new Spinner(composite_2_1, SWT.BORDER);
		spinner.setBounds(177, 191, 105, 28);
		
		Composite composite_2_1_1 = new Composite(this, SWT.NONE);
		composite_2_1_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2_1_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_2_1_1.setBounds(146, 86, 303, 361);
		
		Label lblCurrentStock = new Label(composite_2_1_1, SWT.NONE);
		lblCurrentStock.setText("Current Stock");
		lblCurrentStock.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblCurrentStock.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblCurrentStock.setBounds(24, 190, 90, 20);
		
		Label labelStock = new Label(composite_2_1_1, SWT.NONE);
		labelStock.setBounds(151, 190, 90, 25);
		
		Label lblId = new Label(composite_2_1_1, SWT.NONE);
		lblId.setText("ID");
		lblId.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblId.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblId.setBounds(24, 102, 70, 20);
		
		inputId = new Text(composite_2_1_1, SWT.BORDER);
		inputId.setBounds(151, 99, 90, 28);
		
		Button btnCheckStock = new Button(composite_2_1_1, SWT.NONE);
		btnCheckStock.setBounds(104, 266, 90, 32);
		btnCheckStock.setText("Check Stock");
		btnCheckStock.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String input1 = inputId.getText();
				int id = Db.CheckValidId(input1);
				boolean m = false;
		
				if(id!=0) {
					try {
			
						int qty = Db.getQuantityFromID(id);
						labelStock.setText(String.valueOf(qty));
						
						
					} catch (Throwable e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
					}
					
					try {
						 m = DbFunctions.check_id(id, "material");
						} catch (Throwable e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(m==false) {
							JOptionPane.showMessageDialog(null, "Invalid id");
						}
				} else if(id==0) {
					JOptionPane.showMessageDialog(null, "Invalid id");
				}
			}
		});
		btnConfirm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i=1;
				boolean m=false;
				String input1 = inputId.getText();
				int id = Db.CheckValidId(input1);
				try {
					m = DbFunctions.check_id(id, "material");
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				if((Rs.get_user_prio() == 1) && (m == true)) {
					
					i = Rs.get_admin().manage_inventory(Db, id, spinner.getSelection(), btnAdd.getSelection(), btnRemove.getSelection());
				}else if((Rs.get_user_prio() == 2)&& (m == true)){
					
					i = Rs.get_employee().manage_inventory(Db, id, spinner.getSelection(), btnAdd.getSelection(), btnRemove.getSelection());
				} else if(m == false) {
					JOptionPane.showMessageDialog(null, "Invalid id");
				} 
				if (i==0) {
					JOptionPane.showMessageDialog(null, "Not enough stock available");
				}
			}
		});
		
	}
	




	@Override
	protected void checkSubclass() {
	}
}
