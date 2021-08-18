import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * This class is used to represent the main employee interface
 * @author Refined Storage developers
 *
 */
public class GuiEmployeeMenu extends Composite {

	/**
	 * Object Super
	 */
	public Object Super;
	/**
	 * This method represents the main employee interface and his main functionalities
	 * @param parent the composite parent
	 * @param style the desired style
	 * @param name the current user name
	 * @param Rs the refined storage
	 * @param Db the database
	 */
	public GuiEmployeeMenu(Composite parent, int style, String name, Refined_storage Rs, DbFunctions Db) {
		super(parent, style);
		setLayout(null);
		
		this.setVisible(false);
		this.setVisible(true);
		Super = this;
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite.setBounds(0, 0, 869, 80);
		
		Label labelMain_1 = new Label(composite, SWT.NONE);
		labelMain_1.setText("Main Menu");
		labelMain_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelMain_1.setFont(SWTResourceManager.getFont("Corbel", 20, SWT.NORMAL));
		labelMain_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		labelMain_1.setBounds(368, 23, 158, 47);
		
		Button btn_logout_1 = new Button(composite, SWT.NONE);
		btn_logout_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Rs.perform_a_logout();
					((Control) Super).setVisible(false);
					new GuiLogin(parent, style, Rs);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}	
			}
		});
		btn_logout_1.setText("logout");
		btn_logout_1.setBounds(750, 22, 87, 32);
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite_1.setBounds(0, 504, 869, 80);
		
		Label labelMessage_1 = new Label(composite_1, SWT.NONE);
		labelMessage_1.setText("<dynamic>, you are logged in.");
		labelMessage_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelMessage_1.setFont(SWTResourceManager.getFont("Corbel", 16, SWT.NORMAL));
		labelMessage_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		labelMessage_1.setBounds(302, 23, 265, 47);
		
		
		//Element that assures correct window size
		Label labelSizing1 = new Label(this, SWT.NONE);
		labelSizing1.setBounds(831, 564, 38, 20);
		
		Button btnInventory = new Button(this, SWT.NONE);
		btnInventory.setImage(SWTResourceManager.getImage(GuiEmployeeMenu.class, "/icons/editor_page_source.png"));
		btnInventory.setText("List Inventory");
		btnInventory.setBounds(262, 253, 124, 77);
		btnInventory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					((Control) Super).setVisible(false);
					new GuiListInventory(parent, style, name, Rs, Db);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}	
			}	
		});
		
		Button btnSearch = new Button(this, SWT.NONE);
		btnSearch.setImage(SWTResourceManager.getImage(GuiEmployeeMenu.class, "/icons/find.png"));
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnSearch.setText("Search Product");
		btnSearch.setBounds(483, 253, 124, 77);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {		
				try {
					((Control) Super).dispose();
					new GuiSearch(parent, style, name, Rs, Db);
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		Button btnStock = new Button(this, SWT.NONE);
		btnStock.setImage(SWTResourceManager.getImage(GuiEmployeeMenu.class, "/icons/alignment/v/menu/fill.gif"));
		btnStock.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnStock.setText("Manage Stock");
		btnStock.setBounds(52, 253, 124, 77);
		btnStock.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					((Control) Super).setVisible(false);
					new GuiManageInventory(parent, style, name, Rs, Db);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}	
			}	
		});
		
		Button btnSettings = new Button(this, SWT.NONE);
		btnSettings.setImage(SWTResourceManager.getImage(GuiEmployeeMenu.class, "/icons/full/message_warning.png"));
		btnSettings.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					((Control) Super).setVisible(false);
					new GuiSettings(parent, style, name, Rs, Db);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}	
			}
		});
		btnSettings.setText("Settings");
		btnSettings.setBounds(686, 253, 124, 77);
		
		this.setVisible(true);
		
		Label labelSizing2 = new Label(this, SWT.NONE);
		labelSizing2.setBounds(10, 10, 55, 15);
		
		this.pack();
		this.setVisible(true);
		
		Label labelLine_1 = new Label(this, SWT.NONE);
		labelLine_1.setText("______________");
		labelLine_1.setFont(SWTResourceManager.getFont("Corbel", 16, SWT.NORMAL));
		labelLine_1.setBounds(361, 461, 147, 40);
	}

	@Override
	protected void checkSubclass() {
	}
}
