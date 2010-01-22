/**
 * 
 */
package fr.imag.adele.cadse.cadseg.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ui.AbstractUIRunningValidator;
import fr.imag.adele.cadse.core.ui.UIField;

public class UIEnumValidator extends AbstractUIRunningValidator {
	/**
	 * Gets the enum type values.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param enumType
	 *            the enum type
	 * 
	 * @return the enum type values
	 */
	public static List<String> getEnumTypeValues(IType type) {
		List<String> values = new ArrayList<String>();
		try {
			IField[] fields = type.getFields();
			for (IField field : fields) {
				if (field.getElementName().equals("ENUM$VALUES")) {
					continue;
				}
				values.add(field.getElementName());
			}
			return values;
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return values;
	}

	@Override
	public void notifieValueChanged(UIField field, Object value) {
		if (field.getAttributeDefinition() == CadseGCST.ENUM_TYPE_at_JAVA_CLASS_) {
			IType javaClass = value instanceof IType ? (IType) value : null;
			if (javaClass != null) {
				Object values = getEnumTypeValues(javaClass);
				_uiPlatform.setVisualValue(CadseGCST.ENUM_TYPE_at_VALUES_,
						values, false);
				EnumTypeManager.setMustBeGeneratedAttribute(_uiPlatform
						.getItem(), false);
				_uiPlatform.setEnabled(CadseGCST.ENUM_TYPE_at_VALUES_, false);
			} else {
				_uiPlatform.setEnabled(CadseGCST.ENUM_TYPE_at_VALUES_, true);
				EnumTypeManager.setMustBeGeneratedAttribute(_uiPlatform
						.getItem(), true);
			}
		}
	}

	@Override
	public void initAfterUI() {
		List<String> values = EnumTypeManager.getEnumTypeValues(_uiPlatform
				.getItem());
		_uiPlatform.setVisualValue(CadseGCST.ENUM_TYPE_at_VALUES_, values,
				false);
		boolean g = EnumTypeManager.isMustBeGeneratedAttribute(_uiPlatform
				.getItem());
		_uiPlatform.setEnabled(CadseGCST.ENUM_TYPE_at_VALUES_, g);
	}

	@Override
	public boolean validValue(UIField field, Object value) {
		if (field.getAttributeDefinition() == CadseGCST.ENUM_TYPE_at_JAVA_CLASS_) {
			boolean booleanValue = EnumTypeManager
					.isMustBeGeneratedAttribute(_uiPlatform.getItem());
			if (!booleanValue) { // not generated
				IType javaClass = value instanceof IType ? (IType) value : null;
				if (javaClass == null) {
					_uiPlatform
							.setMessageError("You must select a java enum type");
					return true;
				}
			}
		}
		return false;
	}
}