package fr.imag.adele.cadse.cadseg.teamwork;

import org.eclipse.jface.preference.IPreferenceStore;

import fr.imag.adele.cadse.cadseg.eclipse.Activator;

/**
 * Used to get and set values of TeamWork preferences.
 * 
 * @author Thomas
 *
 */
public class TeamWorkPreferences {

	private static final String DEFAULT_USER_NAME = "";
	private static final String TEAMWORK_USER_PROP_NAME = "teamwork.user";

	/**
	 * Set user name for versioning.
	 * 
	 * @param userName new user name
	 */
	public static void setUserName(String userName) {
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		prefStore.setValue(TEAMWORK_USER_PROP_NAME, userName);
	}

	/**
	 * Returns user name for versioning.
	 * 
	 * @return user name for versioning.
	 */
	public static String getUserName() {
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		if (!prefStore.contains(TEAMWORK_USER_PROP_NAME))
			return DEFAULT_USER_NAME;
			
		String val = prefStore.getString(TEAMWORK_USER_PROP_NAME);
		if (val == null)
			return DEFAULT_USER_NAME;
		else
			return val;
	}
}
