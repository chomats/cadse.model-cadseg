package fr.imag.adele.cadse.cadseg.teamwork.db;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.teamwork.db.ModelVersionDBException;
import fr.imag.adele.teamwork.db.ModelVersionDBService;
import fr.imag.adele.teamwork.db.TransactionException;

public class DBUtil {

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
}
