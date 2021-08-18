import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * This class is used to represent the list users interface
 * @author Refined Storage developers
 *
 */
public class GuiListUsers extends Composite {
	private Table table;

	/**
	 * Object Super
	 */
	public Object Super;
	/**
	 * This method represents the list users interface and his functionality
	 * @param parent the composite parent
	 * @param style the desired style
	 * @param name the current user name
	 * @param Rs the refined storage
	 * @param Db the database
	 * @throws Throwable error generating interface
	 */
	public GuiListUsers(Composite parent, int style, String name, Refined_storage Rs, DbFunctions Db) throws Throwable {
		super(parent, style);
		Super = this;
		this.setVisible(false);
		this.setVisible(true);
		setLayout(null);
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite_1.setBounds(0, 504, 869, 80);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(208, 107, 461, 334);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tcId = new TableColumn(table, SWT.CENTER);
		tcId.setWidth(75);
		tcId.setText("ID");
		
		TableColumn tcName = new TableColumn(table, SWT.CENTER);
		tcName.setWidth(195);
		tcName.setText("Name");
		
		TableColumn tcPriority = new TableColumn(table, SWT.CENTER);
		tcPriority.setWidth(187);
		tcPriority.setText("Priority");
		
		//Element that assures correct window size
		Label labelSizing1 = new Label(this, SWT.NONE);
		labelSizing1.setBounds(808, 564, 61, 20);
		
		ResultSet result_set = Rs.get_admin().list_users(Db);
		
		if(result_set != null) {
			
			TableItem item;
			try {
				while (result_set.next()) {
					item=new TableItem(table, SWT.NONE);
					item.setText(new String[] { String.valueOf(result_set.getInt("id")),
																result_set.getString("name") ,
																	result_set.getString("priority")});
	 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
			
		}
		
		this.pack();
		this.setVisible(true);
		
		Button btnAddRemove = new Button(this, SWT.NONE);
		btnAddRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					((Control) Super).setVisible(false);
					new GuiManageUsers(parent, style, name, Rs, Db);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}	
			}
		});
		btnAddRemove.setBounds(53, 107, 100, 30);
		btnAddRemove.setText("Add/Remove");
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite.setBounds(0, 0, 869, 80);
		
		Button btnBack = new Button(composite, SWT.NONE);
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
		btnBack.setBounds(10, 10, 75, 25);
		
		Label labelMain_1 = new Label(composite, SWT.NONE);
		labelMain_1.setText("Users");
		labelMain_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelMain_1.setFont(SWTResourceManager.getFont("Corbel", 20, SWT.NORMAL));
		labelMain_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		labelMain_1.setBounds(397, 23, 75, 47);
		
		Label labelLine = new Label(this, SWT.NONE);
		labelLine.setText("______________");
		labelLine.setFont(SWTResourceManager.getFont("Corbel", 16, SWT.NORMAL));
		labelLine.setBounds(361, 461, 147, 40);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
