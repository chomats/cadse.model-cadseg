package fr.imag.adele.cadse.cadseg.managers;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.var.ContextVariable;

public interface IBuildManager {

	public void generate(ContextVariable cxt, Item enumType);
}
