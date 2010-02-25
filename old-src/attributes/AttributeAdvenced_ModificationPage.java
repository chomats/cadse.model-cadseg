package fr.imag.adele.cadse.cadseg.pages.attributes;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;

/**
 @generated
 */
public class AttributeAdvenced_ModificationPage extends PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldTransient;
	/**
	    @generated
	 */
	protected DCheckBoxUI fieldRequire;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldNatif;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldFinalValue;

	/**
	    @generated
	 */
	protected DCheckBoxUI fieldCannotBeUndefined;

	/**
	    @generated
	 */
	protected AttributeAdvenced_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public AttributeAdvenced_ModificationPage(Item item) {
		super("advenced", "advenced", "advenced", "", false, 3);
		this.item = item;
		this.fieldTransient = createFieldTransient();
		this.fieldRequire = createFieldRequire();
		this.fieldNatif = createFieldNatif();
		this.fieldFinalValue = createFieldFinalValue();
		this.fieldCannotBeUndefined = createFieldCannotBeUndefined();
		setActionPage(null);
		addLast(this.fieldTransient, this.fieldRequire, this.fieldNatif,
				this.fieldFinalValue, this.fieldCannotBeUndefined);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldTransient() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_TRANSIENT, "transient",
				EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldRequire() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_REQUIRE, "require",
				EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldNatif() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_NATIF, "natif",
				EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldFinalValue() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_FINAL_VALUE,
				"final-value", EPosLabel.none, mc, null);
	}

	/**
	    @generated
	 */
	public DCheckBoxUI createFieldCannotBeUndefined() {
		StringToBooleanModelControler mc = new StringToBooleanModelControler();
		return new DCheckBoxUI(CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED,
				"cannot-be-undefined", EPosLabel.none, mc, null);
	}

}
