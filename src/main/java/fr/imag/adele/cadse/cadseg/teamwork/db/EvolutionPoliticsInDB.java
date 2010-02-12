package fr.imag.adele.cadse.cadseg.teamwork.db;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;
import fr.imag.adele.teamwork.db.DBConnectionException;
import fr.imag.adele.teamwork.db.ModelVersionDBException;
import fr.imag.adele.teamwork.db.ModelVersionDBService;
import fr.imag.adele.teamwork.db.TransactionException;

public class EvolutionPoliticsInDB {
	
	private LogicalWorkspaceTransaction _transaction;
	private Map<ItemType, Boolean> _evolPoliticsSetIndDB = new HashMap<ItemType, Boolean>(); 

	public EvolutionPoliticsInDB(LogicalWorkspaceTransaction transaction) {
		_transaction = transaction;
	}

	public void checkEvolPoliticsSetInDB(UUID itemId, ModelVersionDBService db) throws TransactionException, DBConnectionException {
		Item item = _transaction.getItem(itemId);
		ItemType itemType = item.getType();
		UUID itemTypeId = itemType.getId();
		
		Boolean isAlreadySet = _evolPoliticsSetIndDB.get(itemType);
		if ((isAlreadySet != null) && isAlreadySet)
			return;
		
		DBUtil.connectToDB(db, itemType);
		for (IAttributeType<?> attrType : itemType.getAllAttributeTypes()) {
			
			if (TWUtil.isTWAttribute(attrType))
				continue;
			if (TWUtil.isInternalCadseAttribute(attrType))
				continue;
			
			try {
				if (TWUtil.isLinkType(attrType)) {
					db.setLinkSrcVersionSpecific(attrType.getId(), attrType.isTWRevSpecific());
					db.setLinkDestVersionSpecific(attrType.getId(), !TWUtil.isBranchDestination(attrType));
				} else
					db.setObjectAttVersionSpecific(itemTypeId, getAttributeName(attrType), attrType.isTWRevSpecific());
			} catch (ModelVersionDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// set parent as shared attribute
		try {
			db.setObjectAttVersionSpecific(itemTypeId, DBUtil.PARENT_ATTR_NAME, false);
		} catch (ModelVersionDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getAttributeName(IAttributeType<?> attrType) {
		return attrType.getName();
	}
}
