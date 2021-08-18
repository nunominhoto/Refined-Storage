import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
/**
 * This class is used to represent the admin main interface
 * @author Refined Storage developers
 *
 */
public class GuiAdminMenu extends Composite {

	/**
	 * Object Super
	 */
	public Object Super;
	/**
	 * This method represents the admin main interface
	 * @param parent the composite parent
	 * @param style the desired style
	 * @param name the current user name
	 * @param Rs the refined storage
	 * @param Db the databse
	 */
	public GuiAdminMenu(Composite parent, int style, String name, Refined_storage Rs, DbFunctions Db) {
		super(parent, style);
		setLayout(null);
		
		this.setVisible(false);
		this.setVisible(true);
		Super = this;
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_1.setBounds(0, 504, 869, 80);
		
		Label labelMessage = new Label(composite_1, SWT.NONE);
		labelMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelMessage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		labelMessage.setBounds(302, 23, 264, 47);
		labelMessage.setText(name +  ", you are logged in.");
		labelMessage.setFont(SWTResourceManager.getFont("Corbel", 16, SWT.NORMAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite.setBounds(0, 0, 869, 80);
		
		
		Label labelMain = new Label(composite, SWT.NONE);
		labelMain.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelMain.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		labelMain.setBounds(369, 23, 131, 47);
		labelMain.setFont(SWTResourceManager.getFont("Corbel", 20, SWT.NORMAL));
		labelMain.setText("Main Menu");
		
		Button btn_logout = new Button(composite, SWT.NONE);
		btn_logout.setBounds(750, 22, 87, 32);
		btn_logout.addSelectionListener(new SelectionAdapter() {
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
		btn_logout.setText("logout");
		
		
		//Element that assures correct window size
		Label labelSizing1 = new Label(this, SWT.NONE);
		labelSizing1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		labelSizing1.setBounds(804, 555, 65, 29);
		
		Button btnMaterial = new Button(this, SWT.NONE);
		btnMaterial.setImage(SWTResourceManager.getImage(GuiAdminMenu.class, "/icons/actions/factory/factory.png"));
		btnMaterial.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					((Control) Super).setVisible(false);
					new GuiUpdateInventory(parent, style, name, Rs, Db);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		});
		btnMaterial.setBounds(53, 163, 124, 77);
		btnMaterial.setText("Manage Items");
		
		Button btnInventory = new Button(this, SWT.NONE);
		btnInventory.setImage(SWTResourceManager.getImage(GuiAdminMenu.class, "/icons/editor_page_source.png"));
		btnInventory.setText("List Inventory");
		btnInventory.setBounds(258, 163, 124, 77);
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
		
		Button btnOrders = new Button(this, SWT.NONE);
		btnOrders.setImage(SWTResourceManager.getImage(GuiAdminMenu.class, "/wbp-meta/org/eclipse/swt/dnd/DragSource.gif"));
		btnOrders.setText("Orders");
		btnOrders.setBounds(468, 163, 124, 77);
		btnOrders.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					((Control) Super).setVisible(false);
					new GuiListOrders(parent, style, name, Rs, Db);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}	
			}	
		});
		
		Button btnSearch = new Button(this, SWT.NONE);
		btnSearch.setImage(SWTResourceManager.getImage(GuiAdminMenu.class, "/icons/find.png"));
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnSearch.setText("Search Product");
		btnSearch.setBounds(677, 163, 124, 77);
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
		btnStock.setImage(SWTResourceManager.getImage(GuiAdminMenu.class, "/icons/alignment/v/menu/fill.gif"));
		btnStock.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnStock.setText("Manage Stock");
		btnStock.setBounds(151, 357, 124, 77);
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
		
		Button btnStaff = new Button(this, SWT.NONE);
		btnStaff.setImage(SWTResourceManager.getImage(GuiAdminMenu.class, "/icons/full/message_info.png"));
		btnStaff.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					((Control) Super).setVisible(false);
					new GuiListUsers(parent, style, name, Rs, Db);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}	
			}
		});
		btnStaff.setText("Staff");
		btnStaff.setBounds(372, 357, 124, 77);
		
		Button btnSettings = new Button(this, SWT.NONE);
		btnSettings.setImage(SWTResourceManager.getImage(GuiAdminMenu.class, "/org/eclipse/jface/fieldassist/images/warn_ovr@2x.png"));
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
		btnSettings.setBounds(581, 357, 124, 77);
		
		Label labelLine = new Label(this, SWT.NONE);
		labelLine.setText("______________");
		labelLine.setFont(SWTResourceManager.getFont("Corbel", 16, SWT.NORMAL));
		labelLine.setBounds(361, 461, 147, 40);
		
		this.setVisible(true);
		
		Label labelSizing2 = new Label(this, SWT.NONE);
		labelSizing2.setBounds(0, 31, 55, 15);
		
		this.pack();
		this.setVisible(true);
	}

	@Override
	protected void checkSubclass() {
	}
}
