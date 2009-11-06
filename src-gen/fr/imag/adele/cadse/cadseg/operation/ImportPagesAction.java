/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package fr.imag.adele.cadse.cadseg.operation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import fede.workspace.model.manager.properties.impl.ic.IC_ForChooseFile;
import fede.workspace.model.manager.properties.impl.ui.DChooseFileUI;
import fede.workspace.tool.loadmodel.model.jaxb.CCadse;
import fede.workspace.tool.loadmodel.model.jaxb.CItemType;
import fede.workspace.tool.loadmodel.model.jaxb.CLink;
import fede.workspace.tool.loadmodel.model.jaxb.CLinkType;
import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.AbstractModelController;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * The Class ImportPagesAction.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ImportPagesAction extends AbstractActionPage {

	/**
	 * The Class CadseViewerFilter.
	 */
	class CadseViewerFilter extends ViewerFilter {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
		 *      java.lang.Object, java.lang.Object)
		 */
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof IFile) {
				IFile file = (IFile) element;
				return (file.getName().endsWith(
						".jar"));
			}
			if (element instanceof IContainer) {
				IContainer folder = (IContainer) element;
				IResource[] listFiles = null;
				try {
					listFiles = folder.members();
				} catch (CoreException e) {
				}
				if (listFiles != null) {
					for (int i = 0; i < listFiles.length; i++) {
						if (select(viewer, folder, listFiles[i])) {
							return true;
						}
					}
				}
			}
			return false;
		}

	}

	/** The select jar. */
	IPath				selectJar;

	/** The file. */
	File				file;

	/** The cadse. */
	CCadse				cadse;

	/** The cadse viewer filter. */
	public ViewerFilter	cadseViewerFilter	= new CadseViewerFilter();

	/**
	 * The Class MC_Import.
	 */
	class MC_Import extends AbstractModelController {

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IModelController#getValue()
		 */
		public Object getValue() {
			return selectJar;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieValueChanged(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public void notifieValueChanged(UIField field, Object value) {
			selectJar = (IPath) value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.AbstractModelController#notifieValueDeleted(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		@Override
		public void notifieValueDeleted(UIField field, Object oldvalue) {
			selectJar = null;
		}

		/**
		 * Valid value changed.
		 * 
		 * @param value
		 *            the value
		 * 
		 * @return the string
		 */
		@Override
		public boolean validValueChanged(UIField field, Object value) {
			file = getFile((IPath) value);
			if (file == null || !file.exists()) {
				setMessageError("Select a valid cadse jar");
				return true;
			}
			try {
				cadse = readCadse(file);
				if (cadse == null) {
					setMessageError("Select a valid cadse jar");
					return true;
				}
				String name = cadse.getName();
				Item cadseDef = CadseCore.getItem(name, null, CadseGCST.CADSE_DEFINITION, null, null);
				if (cadseDef != null) {
					setMessageError("cannot import : allready exists");
					return true;
				}
			} catch (IOException e) {
				WSPlugin.logException(e);
				setMessageError("Select a valid cadse jar : " + e.getMessage());
				return true;
			} catch (JAXBException e) {
				WSPlugin.logException(e);
				setMessageError("Select a valid cadse jar : " + e.getMessage());
				return true;
			} catch (CadseException e) {
				WSPlugin.logException(e);
				setMessageError("Select a valid cadse jar : " + e.getMessage());
				return true;
			}
			return false;
		}

		public ItemType getType() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	/**
	 * The Class IC_Import.
	 */
	class IC_Import extends IC_ForChooseFile {

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_ForChooseFile#getFileFilter()
		 */
		@Override
		protected String[] getFileFilter() {
			return new String[] {  "*.jar" };
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_ForChooseFile#getViewerFilter()
		 */
		@Override
		protected ViewerFilter getViewerFilter() {
			return cadseViewerFilter;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fede.workspace.model.manager.properties.impl.ic.IC_ForChooseFile#getKind()
		 */
		@Override
		public int getKind() {
			return FILE_EXT;
		}
	}

	/**
	 * Creates the import field.
	 * 
	 * @return the d choose file ui
	 */
	public DChooseFileUI createImportField() {
		return new DChooseFileUI("selectJar", "Select cadse deployed jar", EPosLabel.left, new MC_Import(),
				new IC_Import(), "Select cadse deployed jar");
	}

	/**
	 * Read cadse.
	 * 
	 * @param f
	 *            the f
	 * 
	 * @return the c cadse
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	public CCadse readCadse(File f) throws IOException, JAXBException {
		JarFile jis = new JarFile(f);
		ZipEntry entry = jis.getEntry("model/cadse.xml");
		if (entry == null) {
			entry = jis.getEntry("/model/cadse.xml");
			if (entry == null) {
				throw new IOException("Cannot found /model/cadse.xml");
			}
		}
		InputStream imput = jis.getInputStream(entry);
		JAXBContext jc = JAXBContext.newInstance("fede.workspace.tool.loadmodel.model.jaxb", this.getClass()
				.getClassLoader());
		Unmarshaller m = jc.createUnmarshaller();
		return (CCadse) m.unmarshal(imput);
	}

	/**
	 * Gets the select jar.
	 * 
	 * @return the select jar
	 */
	public IPath getSelectJar() {
		return selectJar;
	}

	/**
	 * Sets the select jar.
	 * 
	 * @param selectJar
	 *            the new select jar
	 */
	public void setSelectJar(IPath selectJar) {
		this.selectJar = selectJar;
	}

	/** The its. */
	HashMap<String, Item>		its;

	/** The its_c. */
	HashMap<String, CItemType>	its_c;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.impl.ui.AbstractActionPage#doFinish(java.lang.Object)
	 */
	@Override
	public void doFinish(Object monitor) throws Exception {
		super.doFinish(monitor);
		IProgressMonitor pmo = (IProgressMonitor) monitor;
		CadseCore.getCadseDomain().beginOperation("Import binary cadse");
		try {
			pmo.beginTask("import cadse " + cadse.getName(), cadse.getItemType().size() * 2 + 1);
			String shortname = cadse.getName();
			// assert file != null
			// assert cadse != null
			Item cadsedef = CadseCore.createItemIfNeed(cadse.getName(), shortname, CadseGCST.CADSE_DEFINITION, null,
					null);
			Item dm = CadseDefinitionManager.getDataModel(cadsedef);
			its = new HashMap<String, Item>();
			its_c = new HashMap<String, CItemType>();

			for (CItemType cit : cadse.getItemType()) {
				its_c.put(cit.getId(), cit);
			}
			pmo.worked(1);
			LogicalWorkspaceTransaction copy = CadseCore.getLogicalWorkspace().createTransaction();
			for (CItemType cit : cadse.getItemType()) {
				try {
					findOrCreateItemType(copy, cit.getId(), dm);
				} catch (Throwable e) {
					e.printStackTrace();
				}
				pmo.worked(1);

			}
			for (CItemType cit : cadse.getItemType()) {
				Item sourceItemType = its.get(cit.getId());
				if (sourceItemType == null) {
					continue;
				}
				for (CLink clt : cit.getOutgoingLink()) {
					if (!(clt instanceof CLinkType)) {
						continue;
					}
					try {
						createLinkType(copy, (CLinkType) clt, sourceItemType);
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
				pmo.worked(1);
			}
			copy.commit();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CadseCore.getCadseDomain().endOperation();
		}
	}

	/**
	 * Find or create item type.
	 * 
	 * @param uuid
	 *            the uuid
	 * @param dm
	 *            the dm
	 * 
	 * @return the item
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	private Item findOrCreateItemType(LogicalWorkspaceTransaction copy, String uuid, Item dm) throws CadseException {
		Item ret = its.get(uuid);
		if (ret != null) {
			return ret;
		}
		CItemType cit = its_c.get(uuid);
		Item superItem = null;
		if (cit.getSuperTypeName() != null) {
			try {
				superItem = findOrCreateItemType(copy, cit.getSuperTypeName(), dm);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		if (cit != null) {
			ret = createItemType(copy, cit, dm, superItem);
			if (ret != null) {
				its.put(uuid, ret);
			}
			return ret;
		}
		return null;
	}

	/**
	 * Creer un item type dans cadseg � partir d'un nom, du data model et de son
	 * supertype.
	 * 
	 * @param datamodel
	 *            The data model where the new item type is inserted.
	 * @param superItem
	 *            The super type of the new type. the type of superType must be
	 *            cadseg's ItemType.
	 * @param cit
	 *            the cit
	 * 
	 * @return the item
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	public Item createItemType(LogicalWorkspaceTransaction copy, CItemType cit, Item datamodel, Item superItem)
			throws CadseException {

		// get the model

		// create the itemtype
		Item theitemtype = copy.createItem(CadseGCST.ITEM_TYPE, datamodel, CadseGCST.DATA_MODEL_lt_TYPES);

		// set the short name and compute the unique name from parent and
		// parent link type setting before
		CadseCore.setName(theitemtype, cit.getName());

		if (superItem != null) {
			theitemtype.createLink(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, superItem);
		}

		theitemtype.setAttribute(CadseGCST.ITEM_TYPE_at_IS_ABSTRACT_, cit.isIsAbstract());
		theitemtype.setAttribute(CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_, cit.isIsRootElement());
		theitemtype.setAttribute(CadseGCST.ITEM_TYPE_at_HAS_CONTENT_, cit.isHasContent());
		theitemtype.setAttribute(CadseGCST.TYPE_DEFINITION_at_ID_RUNTIME_, cit.getId());

		// get the mapping model
		Item mappingModel = CadseDefinitionManager.getMappingModel(ItemTypeManager.getCadseDefinition(theitemtype));

		// create the manager
		Item managerItem = copy.createItem(CadseGCST.MANAGER, mappingModel, CadseGCST.MAPPING_MODEL_lt_MANAGERS);

		ItemTypeManager.setIsAbstractAttribute(theitemtype, false);

		// set the short name and compute the unique name from parent and
		// parent link type setting before
		CadseCore.setName(managerItem, theitemtype.getName() + "-manager");

		// ManagerManager.setManagerType(managerItem, "default");
		ManagerManager.setHumanNameAttribute(managerItem, cit.getDisplayName());
		ManagerManager.setUniqueNameTemplate(managerItem, "${#parent.qualified-name}{.}${#name}");
		ManagerManager.setDisplayNameTemplateAttribute(managerItem, "${#name}");
		// create a link form manager to theitemtype
		ManagerManager.setItemType(managerItem, theitemtype);

		ManagerManager.setIconAttribute(managerItem, cit.getIcon());
		// managerItem.setAttribute(ManagerManager.MANAGER_CLASS_ATTRIBUTE,
		// cit.getManagerClass());

		if (superItem != null) {
			Item supermanager = ManagerManager.getManagerFromItemType(superItem);
			ManagerManager.setUniqueNameTemplate(managerItem, ManagerManager.getUniqueNameTemplate(supermanager));
			ManagerManager.setDisplayNameTemplateAttribute(managerItem, ManagerManager
					.getDisplayNameTemplateAttribute(supermanager));
		}

		copy.commit();
		return theitemtype;
	}

	/**
	 * Creates the link type.
	 * 
	 * @param ltname
	 *            the ltname
	 * @param sourceItemType
	 *            the source item type
	 * 
	 * @return the item
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	public Item createLinkType(LogicalWorkspaceTransaction copy, CLinkType ltname, Item sourceItemType)
			throws CadseException {

		Item destItemType = its.get(ltname.getDestination());
		if (destItemType == null) {
			throw new CadseException("Cannot find the item type {0}.", ltname.getDestination());
		}

		Item thelinktype = copy.createItem(CadseGCST.LINK, sourceItemType,
				CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES);
		CadseCore.setName(thelinktype, ltname.getName());
		LinkManager.setMaxAttribute(thelinktype, ltname.getMax());
		LinkManager.setMinAttribute(thelinktype, ltname.getMin());
		LinkManager.setDestinationAttribute(thelinktype, destItemType);
		LinkManager.setIsListAttribute(thelinktype, ltname.getMax() != 0 && ltname.getMax() != 1);
		thelinktype.setAttribute(CadseGCST.LINK_at_AGGREGATION, Boolean.toString(ltname.isIsAggregation()));
		thelinktype.setAttribute(CadseGCST.LINK_at_PART, Boolean.toString(ltname.isIsPart()));
		thelinktype.setAttribute(CadseGCST.ATTRIBUTE_at_REQUIRE, Boolean.toString(ltname.isIsRequire()));
		thelinktype.setAttribute(CadseGCST.LINK_at_ANNOTATION, Boolean.toString(ltname.isIsAnnotation()));
		thelinktype.setAttribute(CadseGCST.LINK_at_COMPOSITION, Boolean.toString(ltname.isIsComposition()));
		thelinktype.setAttribute(CadseGCST.LINK_at_SELECTION, ltname.getSelectionExpression());
		// TODO inverse link

		return thelinktype;

	}

	/**
	 * Gets the file.
	 * 
	 * @param selectJar2
	 *            the select jar2
	 * 
	 * @return the file
	 */
	private File getFile(IPath selectJar2) {
		IResource r = ResourcesPlugin.getWorkspace().getRoot().findMember(selectJar2);
		if (r != null) {
			return r.getLocation().toFile();
		}
		return selectJar2.toFile();
	}

}
