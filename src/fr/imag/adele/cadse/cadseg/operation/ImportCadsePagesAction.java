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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import javax.xml.bind.JAXBException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import adele.util.io.ZipUtil;
import fede.workspace.model.manager.properties.impl.ic.IC_ForChooseFile;
import fede.workspace.model.manager.properties.impl.ui.DChooseFileUI;
import fede.workspace.tool.loadmodel.model.jaxb.CItemType;
import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.core.CadseDomain;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.ProjectAssociation;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.delta.ItemDelta;
import fr.imag.adele.cadse.core.delta.LinkDelta;
import fr.imag.adele.cadse.core.delta.SetAttributeOperation;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.CadseIllegalArgumentException;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.AbstractModelController;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.UIField;

/**
 * The Class ImportCadsePagesAction.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ImportCadsePagesAction extends AbstractActionPage {

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
				return (file.getName().startsWith(CadseRuntime.CADSE_NAME_SUFFIX) && file.getName().endsWith(
						".zip"));
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
	IPath				selectJar			= new Path("");

	/** The file. */
	File				file;

	/** The cadse. */
	String				cadse;

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
		 * @see fr.imag.adele.cadse.core.ui.AbstractModelController#notifieSubValueAdded(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		@Override
		public void notifieSubValueAdded(UIField field, Object added) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.AbstractModelController#notifieSubValueRemoved(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		@Override
		public void notifieSubValueRemoved(UIField field, Object removed) {
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

		@Override
		public Object defaultValue() {
			return selectJar;
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
			if (file == null || !file.exists() || file.isDirectory()) {
				setMessageError("Select a valid cadse zip file");
				return true;
			}
			try {
				cadse = readCadse(file);
				if (cadse == null) {
					setMessageError("Select a valid cadse zip file");
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
			return new String[] { CadseRuntime.CADSE_NAME_SUFFIX + "*.zip" };
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
		return new DChooseFileUI("selectJar", "Select cadse deployed zip", EPosLabel.left, new MC_Import(),
				new IC_Import(), "Select cadse deployed zip");
	}

	/**
	 * Read cadse.
	 * 
	 * @param f
	 *            the f
	 * 
	 * @return the string
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	public String readCadse(File f) throws IOException, JAXBException {
		JarFile jis = new JarFile(f);
		ZipEntry entry = jis.getEntry(ExportCadsePagesAction.MELUSINE_DIR_CADSENAME);
		if (entry == null) {
			entry = jis.getEntry("/" + ExportCadsePagesAction.MELUSINE_DIR_CADSENAME);
			if (entry == null) {
				throw new IOException("Cannot found " + ExportCadsePagesAction.MELUSINE_DIR_CADSENAME);
			}
		}
		InputStream imput = jis.getInputStream(entry);
		BufferedReader isr = new BufferedReader(new InputStreamReader(imput));
		return isr.readLine();
	}

	/**
	 * Read cadse uuid.
	 * 
	 * @param f
	 *            the f
	 * 
	 * @return the compact uuid
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	public CompactUUID readCadseUUID(File f) throws IOException, JAXBException {
		JarFile jis = new JarFile(f);
		ZipEntry entry = jis.getEntry(ExportCadsePagesAction.MELUSINE_DIR_CADSENAME_ID);
		if (entry == null) {
			entry = jis.getEntry("/" + ExportCadsePagesAction.MELUSINE_DIR_CADSENAME_ID);
			if (entry == null) {
				throw new IOException("Cannot found " + ExportCadsePagesAction.MELUSINE_DIR_CADSENAME_ID);
			}
		}
		InputStream imput = jis.getInputStream(entry);
		BufferedReader isr = new BufferedReader(new InputStreamReader(imput));
		return new CompactUUID(isr.readLine());
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
		CadseCore.getCadseDomain().beginOperation("Import cadse");
		try {
			File f = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
			File pf = new File(f, cadse);
			ZipUtil.unzipFile(file, pf);
			File melusineDir = new File(pf, ".melusine-dir");
			File[] filesserxml = melusineDir.listFiles();
			Collection<URL> itemdescription = new ArrayList<URL>();
			for (File fser : filesserxml) {
				if (fser.getName().endsWith(".ser")) {
					itemdescription.add(fser.toURI().toURL());
				}
			}
			Collection<ProjectAssociation> projectAssociationSet = new ArrayList<ProjectAssociation>();
			ProjectAssociation pa = new ProjectAssociation(readCadseUUID(file), cadse);
			projectAssociationSet.add(pa);
			LogicalWorkspaceTransaction transaction = CadseCore.getLogicalWorkspace().createTransaction();

			transaction.loadItems(itemdescription);
			migrate(transaction);
			transaction.commit(false, true, false, projectAssociationSet);
			checkAction(transaction);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CadseCore.getCadseDomain().endOperation();
		}
	}

	private void checkAction(LogicalWorkspaceTransaction transaction) {
		Collection<ItemDelta> operations = transaction.getItemOperations();
		LogicalWorkspace lw = CadseCore.getLogicalWorkspace();
		for (ItemDelta itemDelta : operations) {
			Item gI = lw.getItem(itemDelta.getId());
			if (gI == null) {
				System.err.println("Cannot found commited item "+itemDelta);
				continue;
			}
			Item parent = gI.getPartParent();
			if (parent == null && itemDelta.getPartParent() != null) {
				System.err.println("Parent not setted "+itemDelta+" -> "+itemDelta.getPartParent());
			} else {
				if (parent != null && itemDelta.getPartParent() != null) {
					if (!parent.getId().equals(itemDelta.getPartParent().getId())) {
						System.err.println("Parent not same "+itemDelta+" -> "+itemDelta.getPartParent()+"<>"+parent);
					}
				}
			}
				
		}
		
	}

	private void migrate(LogicalWorkspaceTransaction transaction) throws CadseException {
	Collection<ItemDelta> operations = transaction.getItemOperations();
		for (ItemDelta itemDelta : operations) {
			
			
			if (itemDelta.getType() == null) {
				if (itemDelta.getBaseItem() != null) {
					itemDelta.setType(itemDelta.getBaseItem().getType());
				}
				else
					System.out.println("type has no type "+itemDelta);
			}
			
			if (!itemDelta.isLoaded())
				continue;
			
			SetAttributeOperation uuid_att = itemDelta.getSetAttributeOperation("UUID_ATTRIBUTE");
			if (uuid_att != null) {
				if (itemDelta.getType() == null) {
					System.out.println("Import erroor type is null");
				} else if (itemDelta.getType() == CadseGCST.CADSE_DEFINITION) {
					itemDelta.setAttribute(CadseGCST.CADSE_DEFINITION_at_ID_RUNTIME_, uuid_att.getCurrentValue());
				} else if (itemDelta.isInstanceOf(CadseGCST.PAGE)) {
					itemDelta.setAttribute(CadseGCST.PAGE_at_ID_RUNTIME_, uuid_att.getCurrentValue());
				} else if (itemDelta.isInstanceOf(CadseGCST.ATTRIBUTE)) {
					itemDelta.setAttribute(CadseGCST.ATTRIBUTE_at_ID_RUNTIME_, uuid_att.getCurrentValue());
				} else if (itemDelta.isInstanceOf(CadseGCST.ABSTRACT_ITEM_TYPE)) {
					itemDelta.setAttribute(CadseGCST.ABSTRACT_ITEM_TYPE_at_ID_RUNTIME_, uuid_att.getCurrentValue());
				} else {
					System.out.println("Cannot set UUID_ATTRIBUTE for type "+itemDelta.getType().getName());
				}
				//remove old valeur
				itemDelta.setAttribute("UUID_ATTRIBUTE", null);
			}
			if (itemDelta.getType() == CadseGCST.LINK) {
				if (itemDelta.getName().startsWith("#invert_part")) {
					itemDelta.delete(false);
					for(Link l : itemDelta.getIncomingLinks()) {
						l.delete();
					}
				}
				LinkDelta l = itemDelta.getOutgoingLink(CadseGCST.LINK_lt_INVERSE_LINK);
				if (l != null && l.getDestination().getName().startsWith("#invert_part")) {
					l.delete();
				}
			}
			SetAttributeOperation committed_date_value = itemDelta.getSetAttributeOperation(CadseGCST.ITEM_at_COMMITTED_DATE);
			if (committed_date_value != null) {
				if (committed_date_value.getCurrentValue() instanceof Date) {
					Date d = (Date) committed_date_value.getCurrentValue();
					itemDelta.setAttribute(CadseGCST.ITEM_at_COMMITTED_DATE_, d.getTime());
				}
			}
		}
		
		for (ItemDelta itemDelta : operations) {
			if (!itemDelta.isLoaded())
				continue;
			for (LinkDelta l : itemDelta.getOutgoingLinkOperations()) {
				if (l.getLinkTypeName().startsWith("#parent:") ||
						l.getLinkTypeName().startsWith("#invert_part") ) {
					if (itemDelta.getPartParent() == null) {
						itemDelta.setParent(l.getDestination(), null);
					}
					if (getOutgoingLink(CadseGCST.ITEM_lt_PARENT) == null) {
						itemDelta.createLink(CadseGCST.ITEM_lt_PARENT, l.getDestination());
					}
					l.delete();
				}
				if (l.getLinkType() != null && l.getLinkType().isPart() && l.getDestination().getPartParent() == null) {
					l.getDestination().setParent(l.getSource(), l.getLinkType());
				}
			}
			for (LinkDelta l : itemDelta.getOutgoingLinkOperations(CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES)) {
				if (l.getDestination().getType() == null) {
					IAttributeType<?> att = l.getSource().getType().getAttributeType(l.getDestinationName(), false);
					
					 if (att != null) {
						LinkDelta latt = itemDelta.getOutgoingLink(CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES,att.getId());
						if (latt != null) {
							l.delete();
						} else
							l.changeDestination(att);
					 }
						
					else 
						l.delete();
				}
			}
		}
		for (ItemDelta itemDelta : operations) {
			if (!itemDelta.isLoaded())
				continue;
			if (itemDelta.getPartParent() == null && itemDelta.getType() != null && itemDelta.getType().isPartType()) {
				System.out.println("Error cannot found parent for "+itemDelta.getQualifiedName());
			}
		}
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
		if (selectJar2 == null) {
			return null;
		}
		IResource r = ResourcesPlugin.getWorkspace().getRoot().findMember(selectJar2);
		if (r != null) {
			return r.getLocation().toFile();
		}
		return selectJar2.toFile();
	}

}
