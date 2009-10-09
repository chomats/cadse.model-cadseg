package fr.imag.adele.cadse.cadseg.managers.content;

import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import java.util.Collection;
import java.util.List;

/**
 * @generated
 */
public class AspectJProjectContentModelManager extends JavaProjectContentModelManager {

	/**
	 * @generated
	 */
	public AspectJProjectContentModelManager() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	public String computeUniqueName(Item item, String shortName, Item parent, LinkType lt) {
		StringBuilder sb = new StringBuilder();
		try {
			Object value;
			sb.append(parent.getQualifiedName());
			if (sb.length() != 0) {
				sb.append(".");
			}
			sb.append(shortName);
			return sb.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * @generated
	 */
	@Override
	public String getDisplayName(Item item) {
		try {
			Object value;
			Item currentItem;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * get links 'content-model' from 'AspectJProjectContentModel' to
	 * 'ResourceContentModel'.
	 * 
	 * @generated
	 */
	static public List<Link> getContentModelLink(Item aspectJProjectContentModel) {
		return aspectJProjectContentModel.getOutgoingLinks(WorkspaceCST.ASPECT_JPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL);
	}

	/**
	 * @generated
	 */
	static public Collection<Item> getContentModelAll(Item aspectJProjectContentModel) {
		return aspectJProjectContentModel.getOutgoingItems(WorkspaceCST.ASPECT_JPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL,
				false);
	}

	/**
	 * @generated
	 */
	static public Collection<Item> getContentModel(Item aspectJProjectContentModel) {
		return aspectJProjectContentModel.getOutgoingItems(WorkspaceCST.ASPECT_JPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL,
				true);
	}

	/**
	 * @generated
	 */
	static public void addContentModel(Item aspectJProjectContentModel, Item value) throws CadseException {
		aspectJProjectContentModel.addOutgoingItem(WorkspaceCST.ASPECT_JPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL, value);
	}

	/**
	 * @generated
	 */
	static public void removeContentModel(Item aspectJProjectContentModel, Item value) throws CadseException {
		aspectJProjectContentModel.removeOutgoingItem(WorkspaceCST.ASPECT_JPROJECT_CONTENT_MODEL_lt_CONTENT_MODEL,
				value);
	}

}
