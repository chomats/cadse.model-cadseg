/**
 * 
 */
package fr.imag.adele.cadse.cadseg.managers.dataModel;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.key.DefaultKeyImpl;
import fr.imag.adele.cadse.core.key.Key;

public final class PageSpaceKey extends DefaultKeyImpl {
	final boolean	modificationPage;

	PageSpaceKey(Item source, PageSpaceKeyType type, String name, Key parentKey) {
		super(type, parentKey, name);
		modificationPage = PageManager.isModificationPage(source);
	}

	PageSpaceKey(Item source, PageSpaceKeyType type, String name, Key parentKey, boolean b) {
		super(type, parentKey, name);
		modificationPage = b;
	}

	@Override
	protected int computeHashCode() {
		return super.computeHashCode() ^ Boolean.valueOf(modificationPage).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PageSpaceKey) {
			return super.equals((PageSpaceKey) obj) && ((PageSpaceKey) obj).modificationPage == modificationPage;
		}
		return false;
	}

}