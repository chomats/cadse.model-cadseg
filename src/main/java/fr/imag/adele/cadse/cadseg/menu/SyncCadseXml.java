package fr.imag.adele.cadse.cadseg.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.StringManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.DataModelManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.TypeDefinition;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;
import fr.imag.adele.cadse.core.transaction.delta.ItemDelta;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CCadse;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CExtensionItemType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CItemType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CValuesType;

/**
 * @see fr.imag.adele.cadse.advancedcadseg.menu.AdvancedMenuActionContributor.SyncCadseXML
 * @author chomats
 */
public class SyncCadseXml extends IMenuAction {

	@Override
	public String getImage() {
		return null;
	}

	@Override
	public String getLabel() {
		return "Sync cadse.xml";
	}

	@Override
	public void run(IItemNode[] selection) throws CadseException {
		Item cadse = selection[0].getItem();
		Item[] its = ItemTypeManager.getAllAllItemType(cadse, null, true);

		LogicalWorkspace lw = cadse.getLogicalWorkspace();
		File cadseFile = new File(cadse.getMainMappingContent(File.class), "model/cadse.xml");
		LogicalWorkspaceTransaction t = lw.createTransaction();
		Item mapping = CadseDefinitionManager.getMapping(cadse);
		try {
			CCadse ccadse = read(new FileInputStream(cadseFile));
			for (CItemType cit : ccadse.getItemType()) {
				UUID id = uuid(cit.getId());
				ItemType it = (ItemType) find(its, cadse, cit.getName(), cit.getPackageName(), cit.getId());
				if (it == null) {
					// create it
					continue;
				}
				final ItemDelta itDelta = t.getItem(it);
				Item manager = ManagerManager.getManagerFromItemType(it);
				if (manager == null) {
					DataModelManager.createManager(t, itDelta, it.getSuperType());
				}
				ItemTypeManager.setDefaultInstanceNameAttribute(itDelta, cit.getDefaultShortName());
				ItemTypeManager.setIdRuntimeAttribute(itDelta, cit.getId());

				for (CValuesType attType : cit.getAttributeType()) {
					Item att = find(it, attType.getKey());
					if (att == null) {
						continue;
					}
					ItemDelta attDelta = t.getItem(att);
					AttributeManager.setIdRuntimeAttribute(attDelta, attType.getId());
					AttributeManager.setDefaultValueAttribute(attDelta, attType.getValue());
					int f = attType.getFlag();

					//
					AttributeManager.setCannotBeUndefinedAttribute(attDelta, (f & Item.CAN_BE_UNDEFINED) == 0);
					AttributeManager.setTransientAttribute(attDelta, (f & Item.TRANSIENT) != 0);
					AttributeManager.setShowInDefaultCpAttribute(attDelta, (f & Item.SHOW_IN_DEFAULT_CP) != 0);

					if (att.isInstanceOf(CadseGCST.STRING)) {
						StringManager.setNotEmptyAttribute(attDelta, (f & Item.NOT_EMPTY) != 0);
					}
				}
			}
			for (CExtensionItemType eit : ccadse.getExtItemType()) {
				for (CValuesType attType : eit.getAttributeType()) {
					TypeDefinition it = (TypeDefinition) find(its, cadse, eit.getName(), eit.getPackageName(), eit
							.getId());
					if (it == null) {
						// create it
						continue;
					}
					Item att = find(it, attType.getKey());
					if (att == null) {
						continue;
					}
					ItemDelta attDelta = t.getItem(att);
					AttributeManager.setIdRuntimeAttribute(attDelta, attType.getId());
					AttributeManager.setDefaultValueAttribute(attDelta, attType.getValue());
					int f = attType.getFlag();

					//
					AttributeManager.setCannotBeUndefinedAttribute(attDelta, (f & Item.CAN_BE_UNDEFINED) == 0);
					AttributeManager.setTransientAttribute(attDelta, (f & Item.TRANSIENT) != 0);
					AttributeManager.setShowInDefaultCpAttribute(attDelta, (f & Item.SHOW_IN_DEFAULT_CP) != 0);

					if (att.isInstanceOf(CadseGCST.STRING)) {
						StringManager.setNotEmptyAttribute(attDelta, (f & Item.NOT_EMPTY) != 0);
					}
				}
			}
			t.commit();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Item find(Item it, String key) {
		Collection<Item> attr = it.getOutgoingItems(CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES, true);
		for (Item a : attr) {
			if (a.getName().equals(key)) {
				return a;
			}
		}
		return null;
	}

	private Item find(Item[] its, Item cadse, String name, String packageName, String id) {
		for (Item it : its) {
			if (it.getName().equals(name)) {
				return it;
			}
		}
		return null;
	}

	private UUID uuid(String id) {
		return UUID.fromString(id);
	}

	public CCadse read(InputStream s) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance("fr.imag.adele.fede.workspace.as.initmodel.jaxb", this.getClass()
				.getClassLoader());
		Unmarshaller m = jc.createUnmarshaller();
		return (CCadse) m.unmarshal(s);
	}
}
