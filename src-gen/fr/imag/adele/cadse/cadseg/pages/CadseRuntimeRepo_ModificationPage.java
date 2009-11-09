package fr.imag.adele.cadse.cadseg.pages;

import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.FieldsCore;

/**
 @generated
 */
public class CadseRuntimeRepo_ModificationPage extends PageImpl {

	/**
	    @generated
	 */
	public Item item;

	/**
	    @generated
	 */
	protected DTextUI fieldItemRepoLogin;

	/**
	    @generated
	 */
	protected DTextUI fieldDefaultContentRepoURL;

	/**
	    @generated
	 */
	protected DTextUI fieldItemRepoPasswd;

	/**
	    @generated
	 */
	protected DTextUI fieldItemRepoURL;

	/**
	    @generated
	 */
	protected CadseRuntimeRepo_ModificationPage(String id, String label,
			String title, String description, boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	    @generated
	 */
	public CadseRuntimeRepo_ModificationPage(Item item) {
		super("repo", "repo", "repo", "repo Page", false, 3);
		this.item = item;
		this.fieldItemRepoLogin = createFieldItemRepoLogin();
		this.fieldDefaultContentRepoURL = createFieldDefaultContentRepoURL();
		this.fieldItemRepoPasswd = createFieldItemRepoPasswd();
		this.fieldItemRepoURL = createFieldItemRepoURL();
		setActionPage(null);
		addLast(this.fieldItemRepoLogin, this.fieldDefaultContentRepoURL,
				this.fieldItemRepoPasswd, this.fieldItemRepoURL);

		registerListener();
	}

	protected void registerListener() {
		// add init and register
	}

	/**
	    @generated
	 */
	public DTextUI createFieldItemRepoLogin() {
		return new DTextUI(CadseGCST.CADSE_RUNTIME_at_ITEM_REPO_LOGIN,
				"itemRepoLogin", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldDefaultContentRepoURL() {
		return new DTextUI(CadseGCST.CADSE_RUNTIME_at_DEFAULT_CONTENT_REPO_URL,
				"defaultContentRepoURL", EPosLabel.left,
				new MC_AttributesItem(), null, 1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldItemRepoPasswd() {
		return new DTextUI(CadseGCST.CADSE_RUNTIME_at_ITEM_REPO_PASSWD,
				"itemRepoPasswd", EPosLabel.left, new MC_AttributesItem(),
				null, 1, "", false, false, false);
	}

	/**
	    @generated
	 */
	public DTextUI createFieldItemRepoURL() {
		return new DTextUI(CadseGCST.CADSE_RUNTIME_at_ITEM_REPO_URL,
				"itemRepoURL", EPosLabel.left, new MC_AttributesItem(), null,
				1, "", false, false, false);
	}

}
