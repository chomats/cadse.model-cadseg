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
import java.io.FileOutputStream;
import java.io.StringBufferInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.osgi.service.prefs.BackingStoreException;

import adele.util.io.FileUtil;
import adele.util.io.ZipUtil;
import fede.workspace.eclipse.composition.java.EclipsePluginContentManger;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CItemType;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.AbstractModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IPage;
import fr.imag.adele.cadse.core.ui.IActionPage;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.ui.UIPlatform;
import fr.imag.adele.cadse.util.Assert;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.dialog.SWTDialog;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.ICRunningField;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_ForChooseFile;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DCheckBoxUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DChooseFileUI;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DTextUI;

/**
 * The Class ExportCadsePagesAction.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ExportCadsePagesAction extends SWTDialog {

	public ExportCadsePagesAction(SWTUIPlatform swtuiPlatforms, String title, String label) {
		super(swtuiPlatforms, title, label);
		addLast(createNameField(), createImportField(), createTimeStampField());
	}

	@Override
	protected IActionPage getFinishAction() {
		return new MyAction();
	}

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
			if (element instanceof IContainer) {
				return true;
			}
			return false;
		}

	}

	/** The select jar. */
	IPath				selectJar;

	/** The file. */
	File				file;

	/** The tstamp. */
	boolean				tstamp				= true;

	String				name				= "";

	/** The cadse viewer filter. */
	public ViewerFilter	cadseViewerFilter	= new CadseViewerFilter();

	/**
	 * The Class MC_TSTamp.
	 */
	class MC_TSTamp extends AbstractModelController {

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IModelController#getValue()
		 */
		@Override
		public Object getValue() {
			return tstamp;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieValueChanged(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		@Override
		public void notifieValueChanged(UIField field, Object value) {
			tstamp = (Boolean) value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.AbstractModelController#notifieValueDeleted(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		@Override
		public void notifieValueDeleted(UIField field, Object oldvalue) {
			tstamp = true;
		}

		public ItemType getType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object defaultValue() {
			return true;
		}

	}

	/**
	 * The Class MC_TSTamp.
	 */
	class MC_name extends AbstractModelController {

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IModelController#getValue()
		 */
		@Override
		public Object getValue() {
			return name;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fr.imag.adele.cadse.core.ui.IEventListener#notifieValueChanged(fr
		 * .imag.adele.cadse.core.ui.UIField, java.lang.Object)
		 */
		@Override
		public void notifieValueChanged(UIField field, Object value) {
			name = (String) value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fr.imag.adele.cadse.core.ui.AbstractModelController#notifieValueDeleted
		 * (fr.imag.adele.cadse.core.ui.UIField, java.lang.Object)
		 */
		@Override
		public void notifieValueDeleted(UIField field, Object oldvalue) {
		}

		public ItemType getType() {
			return null;
		}

		@Override
		public Object defaultValue() {
			return "";
		}

	}

	/**
	 * The Class MC_Import.
	 */
	class MC_Import extends AbstractModelController {
		IEclipsePreferences	node;

		@Override
		public void init(UIPlatform uiPlatform) {
			super.init(uiPlatform);
			// 1. get config pref node
			node = new ConfigurationScope().getNode("fr.imag.adele.cadse.export-cadse");
			String path = node.get("export-path", "");
			if (path != null) {
				selectJar = new org.eclipse.core.runtime.Path(path);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IModelController#getValue()
		 */
		@Override
		public Object getValue() {
			return selectJar;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieValueChanged(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		@Override
		public void notifieValueChanged(UIField field, Object value) {
			selectJar = (IPath) value;
			node.put("export-path", selectJar.toPortableString());
			try {
				node.flush();
			} catch (BackingStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			if (file == null || !file.exists() || !file.isDirectory()) {
				_uiPlatform.setMessageError("Select a folder");
				return true;
			}
			return false;
		}

	}

	/**
	 * The Class IC_Import.
	 */
	class IC_Import extends IC_ForChooseFile {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fede.workspace.model.manager.properties.impl.ic.IC_ForChooseFile#
		 * getViewerFilter()
		 */
		@Override
		protected ViewerFilter getViewerFilter() {
			return cadseViewerFilter;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fede.workspace.model.manager.properties.impl.ic.IC_ForChooseFile#
		 * getKind()
		 */
		@Override
		public int getKind() {
			return FOLDER_EXT + WORKSPACE;
		}
	}

	/**
	 * Creates the import field.
	 * 
	 * @return the d choose file ui
	 */
	public DTextUI createNameField() {
		return _swtuiPlatforms.createTextUI(_page, "name", "name:", EPosLabel.left, new MC_name(), null, 1, false,
				false, false, false, false);
	}

	/**
	 * Creates the import field.
	 * 
	 * @return the d choose file ui
	 */
	public DChooseFileUI<IC_Import> createImportField() {
		return _swtuiPlatforms.createDChooseFileUI(_page, "selectJar", "Select folder", EPosLabel.left, 
				new MC_Import(), new IC_Import(), "Select folder");
	}

	/**
	 * Creates the time stamp field.
	 * 
	 * @return the uI field
	 */
	public DCheckBoxUI<ICRunningField> createTimeStampField() {
		return _swtuiPlatforms.createCheckBoxUI(_page, "tstamp", "add time stamp", EPosLabel.none, new MC_TSTamp(), null);
	}

	/** The cadsedef. */
	Item[]	cadsedef;

	/**
	 * Sets the cadsedef.
	 * 
	 * @param cadsedef
	 *            the new cadsedef
	 */
	public void setCadsedef(Item[] cadsedef) {
		Assert.isTrue(cadsedef != null && cadsedef.length > 0);
		this.cadsedef = cadsedef;
		this.name = cadsedef[0].getQualifiedName();
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
	private HashMap<Item, File>	its;
	
	class MyAction extends AbstractActionPage {
		@Override
		public void doFinish(UIPlatform uiPlatform, Object monitor) throws Exception {
			super.doFinish(uiPlatform, monitor);
			IProgressMonitor pmo = (IProgressMonitor) monitor;
			ExportImportCadseFunction e = new ExportImportCadseFunction();
			e.exportItems(pmo, file, name, tstamp, cadsedef);
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
		IResource r = ResourcesPlugin.getWorkspace().getRoot().findMember(selectJar2);
		if (r != null) {
			return r.getLocation().toFile();
		}
		return selectJar2.toFile();
	}

}

///*
///** The its_c. */
//	private HashMap<String, CItemType>	its_c;
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see fr.imag.adele.cadse.core.impl.ui.AbstractActionPage#doFinish(java.lang.Object)
//	 */
//	@Override
//	public void doFinish(UIPlatform uiPlatform, Object monitor) throws Exception {
//		super.doFinish(uiPlatform, monitor);
//		IProgressMonitor pmo = (IProgressMonitor) monitor;
//		CadseCore.getCadseDomain().beginOperation("Import cadse");
//		try {
//			EclipsePluginContentManger project = (EclipsePluginContentManger) cadsedef.getContentItem();
//
//			String qname = cadsedef.getQualifiedName();
//			File pf = new File(file, qname + "-src.zip");
//			if (tstamp) {
//				Date d = new Date(System.currentTimeMillis());
//				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HHmm");
//				System.out.println(formatter.format(d));
//				pf = new File(file, qname + "-src-" + formatter.format(d) + ".zip");
//			}
//			ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(pf));
//			HashMap<File, String> files = new HashMap<File, String>();
//			IProject eclipseProject = project.getProject();
//			File eclipseProjectFile = eclipseProject.getLocation().toFile();
//			FileUtil.deleteDir(new File(eclipseProjectFile, MELUSINE_DIR));
//			files.put(eclipseProjectFile, "");
//			File wsFile = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
//			File melusineDir = new File(wsFile, ".cadse");
//			pmo.beginTask("export cadse " + cadsedef.getName(), 3);
//			getPersistanceFileAll(melusineDir, cadsedef, files, new HashSet<Item>());
//			pmo.worked(1);
//			ZipUtil.zip(files, outputStream);
//			pmo.worked(2);
//			ZipUtil.addEntryZip(outputStream, new StringBufferInputStream(qname), MELUSINE_DIR_CADSENAME, -1);
//			ZipUtil.addEntryZip(outputStream, new StringBufferInputStream(cadsedef.getId().toString()),
//					MELUSINE_DIR_CADSENAME_ID, -1);
//			pmo.worked(3);
//			outputStream.close();
//		} catch (RuntimeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			CadseCore.getCadseDomain().endOperation();
//		}
//	}
//
//	/**
//	 * Gets the persistance file all.
//	 * 
//	 * @param melusineDir
//	 *            the melusine dir
//	 * @param item
//	 *            the item
//	 * @param files
//	 *            the files
//	 * @param items
//	 *            the items
//	 * 
//	 * @return the persistance file all
//	 */
//	public static void getPersistanceFileAll(File melusineDir, Item item, HashMap<File, String> files, Set<Item> items) {
//
//		if (items.contains(item)) {
//			System.err.println("entry duplicate " + item.getId() + " " + item.getQualifiedName());
//			return;
//		}
//
//		items.add(item);
//
//		File xmlfile = new File(melusineDir, item.getId().toString() + ".ser");
//		files.put(xmlfile, MELUSINE_DIR);
//		xmlfile = new File(melusineDir, item.getId().toString() + ".xml");
//		if (xmlfile.exists()) {
//			files.put(xmlfile, MELUSINE_DIR);
//		}
//
//		List<? extends Link> links = item.getOutgoingLinks();
//		for (Link link : links) {
//			if (!link.getLinkType().isPart()) {
//				continue;
//			}
//			if (!link.isLinkResolved()) {
//				continue;
//			}
//			getPersistanceFileAll(melusineDir, link.getDestination(), files, items);
//		}
//	}
//*/
