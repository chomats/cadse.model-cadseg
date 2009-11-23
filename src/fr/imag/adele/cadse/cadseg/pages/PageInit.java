package fr.imag.adele.cadse.cadseg.pages;

import fr.imag.adele.cadse.cadseg.validators.JavaPackageValidator;
import fr.imag.adele.cadse.core.CadseGCST;

public class PageInit {

	public static void init() {
		JavaClassValidator v  = new JavaClassValidator();
		v.setClazz(JavaPackageValidator.class);
		v.setListenAttributes(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_);
		CadseGCST.CADSE_DEFINITION.addValidators(v);
	}
}
