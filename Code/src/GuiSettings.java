import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * This class is used to represent the settings interface
 * @author Refined Storage developers
 *
 */
public class GuiSettings extends Composite {
	/**
	 * Object super
	 */
	public Object Super;
	
	/**
	 * This method represents the settings interface and his functionality
	 * @param parent the composite parent
	 * @param style the wanted style
	 * @param name the name of the current user
	 * @param Rs the refined storage 
	 * @param Db the database
	 * @throws Throwable error generating interface
	 */
	public GuiSettings(Composite parent, int style, String name, Refined_storage Rs, DbFunctions Db) throws Throwable {
		super(parent, style);
		setLayout(null);
		Super = this;
		
		this.setVisible(false);
		this.setVisible(true);
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite_1.setBounds(0, 504, 869, 80);
		
		Label labelMessage = new Label(composite_1, SWT.NONE);
		labelMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelMessage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		labelMessage.setBounds(302, 23, 263, 47);
		labelMessage.setText(name +  ", you are logged in.");
		labelMessage.setFont(SWTResourceManager.getFont("Corbel", 16, SWT.NORMAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite.setBounds(0, 0, 869, 80);
		
		Label labelMain_1 = new Label(composite, SWT.NONE);
		labelMain_1.setText("Settings");
		labelMain_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelMain_1.setFont(SWTResourceManager.getFont("Corbel", 20, SWT.NORMAL));
		labelMain_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		labelMain_1.setBounds(386, 23, 96, 47);
		
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
		
		
		//Element that assures correct window size
		Label labelSizing1 = new Label(this, SWT.NONE);
		labelSizing1.setBounds(780, 558, 89, 26);
		Label labelSizing2 = new Label(this, SWT.NONE);
		labelSizing2.setBounds(10, 10, 55, 15);
		
		Label labelLine = new Label(this, SWT.NONE);
		labelLine.setText("______________");
		labelLine.setFont(SWTResourceManager.getFont("Corbel", 16, SWT.NORMAL));
		labelLine.setBounds(361, 461, 147, 40);
		
		this.setVisible(true);
		
		this.pack();
		this.setVisible(true);
		
		
		Button btnPassword = new Button(this, SWT.NONE);
		btnPassword.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					((Control) Super).setVisible(false);
					new GuiChangePassword(parent, style, name, Rs, Db);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}	
			}
		});
		btnPassword.setBounds(106, 263, 147, 57);
		btnPassword.setText("Change password");
		
		Label labelVersion = new Label(this, SWT.NONE);
		labelVersion.setBounds(382, 438, 104, 31);
		labelVersion.setText("version 1.0.2 alpha");
		
		Label lblSoftwareRefinedstorage = new Label(this, SWT.NONE);
		lblSoftwareRefinedstorage.setBounds(617, 170, 147, 15);
		lblSoftwareRefinedstorage.setText("Refined Storage Softwares");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(606, 242, 167, 15);
		lblNewLabel.setText("Project developed for LPRO by");
		
		Label lblJooDanielFerreira = new Label(this, SWT.NONE);
		lblJooDanielFerreira.setBounds(613, 271, 153, 15);
		lblJooDanielFerreira.setText("Jo\u00E3o Daniel Ferreira Peixoto");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(595, 292, 201, 15);
		lblNewLabel_1.setText("Nuno de Assis Miranda Schumacher");
		
		Label lblNunoMiguel = new Label(this, SWT.NONE);
		lblNunoMiguel.setBounds(612, 314, 161, 15);
		lblNunoMiguel.setText("Nuno Miguel Veigas Minhoto");
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setBounds(633, 335, 115, 15);
		lblNewLabel_2.setText("Rafael Peres Morais");
		
		Label label = new Label(this, SWT.NONE);
		label.setBounds(678, 191, 30, 15);
		label.setText("2021");
		
		
		 
		
	}

	@Override
	protected void checkSubclass() {
	}
}
