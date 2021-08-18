import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.SWT;
/**
 * This class is used to represents the start of the main program opening the login interface
 * @author Refined Storage developers
 *
 */
public class GuiMain {
	/**
	 * Object super
	 */
	public Object Super;
	
	private static Refined_storage Rs = new Refined_storage();

	/**
	 * This method is used to start the login interface
	 * @param args arguments that are needed (0)
	 * @throws Throwable error generating interface
	 */
	public static void main(String[] args) throws Throwable {
		
		Display display = new Display();
		Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		shell.setLayout(new GridLayout(1, false));
		
		new GuiLogin(shell, SWT.NONE, Rs);
		
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	protected void checkSubclass() {
	}
}