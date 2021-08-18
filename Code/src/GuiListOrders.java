import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * This class is used to represent the list orders interface
 * @author Refined Storage developers
 *
 */

public class GuiListOrders extends Composite {
	private Tree tree;
	/**
	 * Object Super
	 */
	public Object Super;
	/**
	 * This method represents the list orders interface
	 * @param parent the composite parent
	 * @param style the desired style
	 * @param name the current user name
	 * @param Rs the refined storage
	 * @param Db the database
	 * @throws Throwable error generating interface
	 */
	public GuiListOrders(Composite parent, int style, String name, Refined_storage Rs, DbFunctions Db) throws Throwable {
		super(parent, SWT.NONE);
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
		labelMain_1.setText("Orders");
		labelMain_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelMain_1.setFont(SWTResourceManager.getFont("Corbel", 20, SWT.NORMAL));
		labelMain_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		labelMain_1.setBounds(390, 23, 89, 47);
		
		
		Button btnBack = new Button(composite, SWT.NONE);
		btnBack.setBounds(10, 10, 75, 25);
		btnBack.setText("Back");
		
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
		
		
		//Element that assures correct window size
		Label labelSizing1 = new Label(this, SWT.NONE);
		labelSizing1.setBounds(763, 542, 106, 42);
		
		this.setVisible(true);
		
		Label labelSizing2 = new Label(this, SWT.NONE);
		labelSizing2.setBounds(10, 10, 55, 15);
		
		this.pack();
		this.setVisible(true);
		
		tree = new Tree(this, SWT.BORDER | SWT.FULL_SELECTION);
		tree.setBounds(145, 107, 578, 333);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		TreeColumn tcId = new TreeColumn(tree, SWT.LEFT);
		tcId.setText("ID");
		TreeColumn tcType = new TreeColumn(tree, SWT.NULL);
		tcType.setText("Type");
		
		TreeColumn tcUrgency = new TreeColumn(tree, SWT.NULL);
		tcUrgency.setText("Urgency");
		
		TreeColumn tcDetails = new TreeColumn(tree, SWT.NULL);
		tcDetails.setText("Details");
	

		tcId.setWidth(106);
		tcType.setWidth(150);
		tcUrgency.setWidth(120);
		tcDetails.setWidth(200);
		
		Label labelLine = new Label(this, SWT.NONE);
		labelLine.setText("______________");
		labelLine.setFont(SWTResourceManager.getFont("Corbel", 16, SWT.NORMAL));
		labelLine.setBounds(361, 461, 147, 40);
		
		 
		//Listar materiais
		String[][] s = Rs.get_admin().list_orders(Db);
		
		if(s != null) {
			
			int i = Integer.parseInt(s[0][0]);
			Button[] detailsButs = new Button[5];
			TreeItem item;
			for(int j =0; j<i;j++) {
				item=new TreeItem(tree, SWT.NONE);
				item.setText(s[j+1]);
				detailsButs[j] = new Button(tree, SWT.PUSH);
				detailsButs[j].setText("Show Details");
				//detailsButs[j].setBounds(393, j*tree.getItemHeight(), 75, 18);
				//System.out.println(table.getitem());
				//detailsButs[j].addSelectionListener(new Start(j));
				TreeItem subsubItem = new TreeItem(item, SWT.NONE);
		        subsubItem.setText(new String[] { "Item ID", "Quantity"});
		     
				int[][] subresult = Db.listMaterialByOrderID(j+1);
				int k = subresult[0][0];
				TreeItem[] subsubItem2 = new TreeItem[k];
				for(int m =0; m<k;m++) {
					subsubItem2[m] = new TreeItem(item, SWT.NONE);
					subsubItem2[m].setText(new String[] { String.valueOf(subresult[m+1][0]), String.valueOf(subresult[m+1][1])});
				}
			}
			//Fim listar materiais
			
		}

		
	}

	@Override
	protected void checkSubclass() {
	}
}
