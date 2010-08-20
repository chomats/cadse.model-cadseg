package fr.imag.adele.cadse.cadseg.contents;

import java.util.Set;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fr.imag.adele.cadse.core.Item;

public class Page_MF extends IPDEContributor {
	/*
	 * (non-Javadoc)
	 * 
	 * @seefede.workspace.eclipse.composition.java.IPDEContributor#
	 * computeImportsPackage(java.util.Set)
	 */
	public void computeImportsPackage(Item item, Set<String> imports) {
		imports.add("fede.workspace.model.manager.properties");
		imports.add("fr.imag.adele.cadse.core.ui");
		imports.add("fr.imag.adele.cadse.core.impl.ui");
	}
}