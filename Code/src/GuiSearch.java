import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * This class is used to represent the search interface
 * @author Refined Storage developers
 *
 */
public class GuiSearch extends Composite {
	private Table table;
	/**
	 * Object super
	 */
	public Object Super;
	private Text input;
	/**
	 * This method  represents the search interface along with his functionality
	 * @param parent the composite parent
	 * @param style the desired style
	 * @param name the current user name
	 * @param Rs the refined storage
	 * @param Db the database
	 * @throws Throwable error generating interface
	 */
	public GuiSearch(Composite parent, int style, String name, Refined_storage Rs, DbFunctions Db) throws Throwable {
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		Label labelMain_1 = new Label(composite, SWT.NONE);
		labelMain_1.setText("Search");
		labelMain_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelMain_1.setFont(SWTResourceManager.getFont("Corbel", 20, SWT.NORMAL));
		labelMain_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		labelMain_1.setBounds(404, 23, 86, 47);
		
		
		//Element that assures correct window size
		Label labelSizing1 = new Label(this, SWT.NONE);
		labelSizing1.setBounds(794, 564, 75, 20);
		Label labelSizing2 = new Label(this, SWT.NONE);
		labelSizing2.setBounds(10, 10, 55, 15);
		
		this.setVisible(true);
		
		this.pack();
		this.setVisible(true);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(126, 162, 578, 269);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableColumn tcID = new TableColumn(table, SWT.LEFT);
		tcID.setText("ID");
		TableColumn tcName = new TableColumn(table, SWT.NULL);
		tcName.setText("Name");
		
		TableColumn tcBrand = new TableColumn(table, SWT.NULL);
		tcBrand.setText("Brand");
	
		TableColumn tcQty = new TableColumn(table, SWT.NULL);
		tcQty.setText("Qty");
		TableColumn tcDate = new TableColumn(table, SWT.NULL);
		tcDate.setText("Modified on");
		tcID.setWidth(60);
		tcName.setWidth(150);
		tcBrand.setWidth(120);
		tcQty.setWidth(60);
		int w = 578-(60+150+120+60)-4;
		tcDate.setWidth(w);
		
		Button btnBrand = new Button(this, SWT.RADIO);
		btnBrand.setBounds(614, 97, 90, 16);
		btnBrand.setText("Brand");
		
		Button btnProductName = new Button(this, SWT.RADIO);
		btnProductName.setBounds(472, 97, 119, 16);
		btnProductName.setText("Product Name");
		
		Button btnID = new Button(this, SWT.RADIO);
		btnID.setSelection(true);
		btnID.setBounds(403, 97, 55, 16);
		btnID.setText("ID");		
		
		input = new Text(this, SWT.BORDER);
		input.setBounds(127, 95, 162, 21);
		
		Button btnSearch = new Button(this, SWT.NONE);
		btnSearch.setBounds(297, 93, 75, 25);
		btnSearch.setText("Search");
		
		Label labelLine = new Label(this, SWT.NONE);
		labelLine.setText("______________");
		labelLine.setFont(SWTResourceManager.getFont("Corbel", 16, SWT.NORMAL));
		labelLine.setBounds(361, 461, 147, 40);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(Rs.get_user_prio() == 1) {
					String[][] a = Rs.get_admin().search_item(Db, btnID.getSelection(), btnProductName.getSelection(), btnBrand.getSelection(), input.getText());
					
					if(a != null) {
						table.removeAll();
						int i = Integer.parseInt(a[0][0]);
						TableItem item;
						for(int j =0; j<i;j++) {
							item=new TableItem(table, SWT.NONE);
							item.setText(a[j+1]);
						}
						
					}
				
				}else if(Rs.get_user_prio() == 2){
					String[][] et = Rs.get_employee().search_item(Db, btnID.getSelection(), btnProductName.getSelection(), btnBrand.getSelection(), input.getText());
					
					if(et != null) {
						table.removeAll();
						int i = Integer.parseInt(et[0][0]);
						TableItem item;
						for(int j =0; j<i;j++) {
							item=new TableItem(table, SWT.NONE);
							item.setText(et[j+1]);
						}
					}
					
				}
			}
		});
		 
	}

	@Override
	protected void checkSubclass() {
	}
}
