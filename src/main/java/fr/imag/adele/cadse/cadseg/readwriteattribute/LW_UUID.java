package fr.imag.adele.cadse.cadseg.readwriteattribute;

import java.util.UUID;

import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.attribute.UUIDAttributeType;

public class LW_UUID extends LW_Attribute {



	@Override
	public Class<?> getTypeJava(boolean primitive) {
		return UUID.class;
	}

	@Override
	public Class<? extends IAttributeType<?>> getAttributeDefinitionTypeJava() {
		return UUIDAttributeType.class;
	}
}
