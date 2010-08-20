package fr.imag.adele.cadse.cadseg.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class CadsePrefPage extends PreferencePage implements IWorkbenchPreferencePage {

	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		return new org.eclipse.swt.widgets.Text(parent, SWT.BORDER);
	}

	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
		
	}

}
