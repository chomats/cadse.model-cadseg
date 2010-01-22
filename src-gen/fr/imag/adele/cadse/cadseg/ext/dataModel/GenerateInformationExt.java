package fr.imag.adele.cadse.cadseg.ext.dataModel;


import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.util.Convert;
import java.lang.String;



/**
    @generated
*/
public class GenerateInformationExt {

	/**
	    @generated
	*/
	public GenerateInformationExt() {
	}
	/**
		@generated
	*/
	public static final String getCSTNameAttribute(Item generateInformation) {
		return generateInformation.getAttributeWithDefaultValue(CadseGCST.GENERATE_INFORMATION_at_CSTNAME_, null);
	}

	/**
		@generated
	*/
	public static final void setCSTNameAttribute(Item generateInformation, String value) {
		try {
			generateInformation.setAttribute(CadseGCST.GENERATE_INFORMATION_at_CSTNAME_, value);
		} catch (Throwable t) {

		}
	}



}

