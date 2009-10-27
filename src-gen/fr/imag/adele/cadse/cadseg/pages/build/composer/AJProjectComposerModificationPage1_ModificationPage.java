package fr.imag.adele.cadse.cadseg.pages.build.composer;

import fede.workspace.model.manager.properties.IInteractionControllerForList;
import fede.workspace.model.manager.properties.impl.ic.IC_LinkForBrowser_Combo_List;
import fede.workspace.model.manager.properties.impl.mc.LinkModelController;
import fede.workspace.model.manager.properties.impl.ui.DListUI;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.impl.ui.PageImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.PageFactory;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.ui.field.core.FieldsCore;

/**
 * @generated
 */
public class AJProjectComposerModificationPage1_ModificationPage extends
		EclipseComposerModificationPage1_ModificationPage {

	/**
	 * @generated
	 */
	protected AJProjectComposerModificationPage1_ModificationPage(String id,
			String label, String title, String description,
			boolean isPageComplete, int hspan) {
		super(id, label, title, description, isPageComplete, hspan);
	}

	/**
	 * @generated
	 */
	public AJProjectComposerModificationPage1_ModificationPage(Item item) {
		super("modification-page1", "AJProjectComposer", "AJProjectComposer",
				"", false, 3);
		this.item = item;
		this.__short_name__ = createInternalNameField();
		this.fieldComposerLinks = createFieldComposerLinks();
		setActionPage(null);
		addLast(this.__short_name__, this.fieldComposerLinks);

		registerListener();
	}

	protected void registerListener() {
		super.registerListener();
		// add init and register
	}

}
