package fr.imag.adele.cadse.cadseg.teamwork.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Utility methods related to UI used by teamwork. 
 * @author Thomas
 *
 */
public class TWUIUtil {

	/**
	 * Returns an error status (used by SWT dialogs) with specified message.
	 * 
	 * @param errorMsg error message
	 * @return an error status (used by SWT dialogs) with specified message.
	 */
	public static Status createErrorStatus(String errorMsg) {
		return new Status(IStatus.ERROR, "Model.Workspace.Workspace", errorMsg);
	}
}
