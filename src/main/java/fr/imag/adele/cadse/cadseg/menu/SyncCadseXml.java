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
import fr.imag.adele.cadse.cadseg.managers.CadseManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.IItemNode;
import fr.imag.adele.cadse.core.IMenuAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CCadse;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CItemType;

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
		Item[] its = ItemTypeManager.getAllAllItemType(cadse, null);
		
		LogicalWorkspace lw = cadse.getLogicalWorkspace();
		File cadseFile = new File(cadse.getMainMappingContent(File.class), "model/cadse.xml");
		try {
			CCadse ccadse = read(new FileInputStream(cadseFile));
			for(CItemType cit : ccadse.getItemType()) {
				UUID id = uuid(cit.getId());
				Item it = find(its, cadse, cit.getName(), cit.getPackageName(), cit.getId());
				if (it == null) {
					// create it
					continue;
				}
				ItemTypeManager.setDefaultInstanceNameAttribute(it, cit.getDefaultShortName());
				ItemTypeManager.setIdRuntimeAttribute(it, cit.getId());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private Item find(Item[] its, Item cadse, String name, String packageName, String id) {
		for (Item it : its) {
			if (it.getName().equals(name))
				return it;
		}
		return null;
	}

	private UUID uuid(String id) {
		return UUID.fromString(id);
	}

	public CCadse read(InputStream s) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance("fr.imag.adele.fede.workspace.as.initmodel.jaxb", this.getClass().getClassLoader());
		Unmarshaller m = jc.createUnmarshaller();
		return (CCadse) m.unmarshal(s);
	}
}
