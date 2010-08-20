/**
 * 
 */
package fr.imag.adele.cadse.cadseg.pages.ic;

import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.core.Item;

public class IC_ItemTypeTemplateForTextFromManager extends IC_ItemTypeTemplateForText {
	@Override
	protected Item getItemFromContext() {
		Item manager = getContext().getPartParent();
		return ManagerManager.getItemType(manager);
	}
}