package fr.imag.adele.cadse.cadseg.pages.ui;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;

/**
 * @generated
 */
public class DComboModificationPage1_ModificationPage extends
		DisplayModificationPage1_ModificationPage {

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldExtendsIC;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldExtendsMC;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldExtendsUI;

	/**
	 * @generated
	 */
	protected DComboModificationPage1_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public DComboModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "DCombo", "DCombo", "", false, 3);
		this.item = item;
		this.fieldExtendsIC = createFieldExtendsIC();
		this.fieldExtendsMC = createFieldExtendsMC();
		this.fieldExtendsUI = createFieldExtendsUI();
		setActionPage(null);
		addLast(this.fieldExtendsIC, this.fieldExtendsMC, this.fieldExtendsUI);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldExtendsIC() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_IC, "extendsIC",
				EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldExtendsMC() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_MC, "extendsMC",
				EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldExtendsUI() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.DISPLAY_at_EXTENDS_UI, "extendsUI",
				EPosLabel.none, mc, null);
	}

}
