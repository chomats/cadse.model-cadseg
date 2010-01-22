package fr.imag.adele.cadse.cadseg.pages.attributes;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;

/**
 @generated
 */
public class StringStringAdvenced_ModificationPage extends PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldNotEmpty;

	/**
	    @generated
	 */
	protected StringStringAdvenced_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public StringStringAdvenced_ModificationPage(Item item) {
		super("string-advenced", "String Advenced", "String Advenced", "",
				false, 3);
		this.item = item;
		this.fieldNotEmpty = createFieldNotEmpty();
		setActionPage(null);
		addLast(this.fieldNotEmpty);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldNotEmpty() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.STRING_at_NOT_EMPTY, "not-empty",
				EPosLabel.none, mc, null);
	}

}
