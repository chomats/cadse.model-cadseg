package fr.imag.adele.cadse.cadseg.teamwork.db;

import java.util.Map;
import java.util.UUID;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;
import fr.imag.adele.teamwork.db.ModelVersionDBException;
import fr.imag.adele.teamwork.db.ModelVersionDBService;
import fr.imag.adele.teamwork.db.TransactionException;

public class DBUtil {
	
	private static final String NOT_DEFINED_IN_THIS_REVISION = " not defined in this revision.";
	
	public static final String TW_COMMIT_DATE_ATTR_NAME = "TW_COMMIT_DATE";
	public static final String TW_COMMITER_ATTR_NAME = "TW_COMMITER";
	public static final String TW_COMMENT_ATTR_NAME = "TW_COMMENT";

	/**
	 * Returns all revisions of specified item.
	 * 
	 * @param item an item
	 * @return all revisions of specified item.
	 * @throws TransactionException 
	 * @throws ModelVersionDBException 
	 */
	public static int[] getAllRevisions(Item item) throws TransactionException, ModelVersionDBException {
		ModelVersionDBService db = CadseCore.getCadseDomain().getModelVersionDBService();
		connectToDB(db, item.getType());
		
		return db.getObjectRevNbs(item.getId());
	}
	
	/**
	 * Connect to the db managing specified item types and its instances.
	 * 
	 * @param db       a model version database service
	 * @param itemType an item type
	 * @throws TransactionException
	 */
	public static void connectToDB(ModelVersionDBService db, ItemType itemType)
			throws TransactionException {
		Item cadseRuntime = itemType.getOutgoingItem(
				CadseGCST.TYPE_DEFINITION_lt_CADSE, true);
		String cadseName = cadseRuntime.getName();
		DBConnexionParams dbParams = DBConnexionParams
				.getConnectionParams(cadseName);
		String url = dbParams.getUrl();
		String login = dbParams.getLogin();
		String pwd = dbParams.getPassword();
		if ((login == null) || (login.trim().length() == 0)) {
			db.setConnectionURL(url);
		} else {
			db.setConnectionURL(url, login, pwd);
		}
	}

	/**
	 * Returns a human readable text representing state (values of attributes) of specified item revision.
	 * 
	 * @param item      an item 
	 * @param itemRev   item revision number  
	 * @return a human readable text representing state (values of attributes) of specified item revision.
	 * @throws TransactionException 
	 * @throws ModelVersionDBException 
	 */
	public static String getRevisionStateStr(Item item, int itemRev) throws TransactionException, ModelVersionDBException {
		ModelVersionDBService db = CadseCore.getCadseDomain().getModelVersionDBService();
		connectToDB(db, item.getType());
		
		StringBuffer sb = new StringBuffer();
		Map<String, Object> stateMap = db.getObjectState(item.getId(), itemRev);
		
		appendTeamWorkAttrVal(sb, "Commit comment ", TW_COMMENT_ATTR_NAME, stateMap);
		appendTeamWorkAttrVal(sb, "Committer ", TW_COMMITER_ATTR_NAME, stateMap);
		appendTeamWorkAttrVal(sb, "Commit date ", TW_COMMIT_DATE_ATTR_NAME, stateMap);
		
		for (IAttributeType<?> attrType : item.getLocalAllAttributeTypes()) {
			if (TWUtil.isLinkType(attrType))
				continue;
			
			if (TWUtil.isTWAttribute(attrType))
				continue;
			
			if (TWUtil.isInternalCadseAttribute(attrType))
				continue;
			
			String attrName = attrType.getName();
			sb.append(attrName);
			if (stateMap.containsKey(attrName)) {
				Object attrVal = stateMap.get(attrName);
				sb.append(" = ");
				sb.append(attrVal);
			} else {
				sb.append(NOT_DEFINED_IN_THIS_REVISION);
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}

	/**
	 * Appends to specified string buffer of string representing value of specified attribute.
	 * 
	 * @param sb              a string buffer
	 * @param displayAttrName label used to describe the attribute
	 * @param attrName        attribute name
	 * @param stateMap        an item state
	 */
	private static void appendTeamWorkAttrVal(StringBuffer sb,
			String displayAttrName, String attrName, Map<String, Object> stateMap) {
		sb.append(displayAttrName);
		if (stateMap.containsKey(attrName))
			sb.append(": " + stateMap.get(attrName));
		else
			sb.append(NOT_DEFINED_IN_THIS_REVISION);
		sb.append("\n");
	}
}
