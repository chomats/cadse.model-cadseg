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

package fr.imag.adele.cadse.cadseg.menu;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import adele.util.io.FileUtil;
import adele.util.io.ZipUtil;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.AbstractModelController;
import fr.imag.adele.cadse.core.impl.ui.UIFieldImpl;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.UIField;
import fede.workspace.eclipse.composition.java.EclipsePluginContentManger;
import fede.workspace.eclipse.java.osgi.OsgiManifest;
import fede.workspace.model.manager.properties.impl.ic.IC_ForChooseFile;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DChooseFileUI;
import fede.workspace.tool.loadmodel.model.jaxb.CItemType;

/**
 * The Class ExportBundlePagesAction.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class ExportBundlePagesAction extends AbstractActionPage {

	/** The Constant MELUSINE_DIR. */
	public static final String	MELUSINE_DIR				= ".melusine-dir/";

	/** The Constant MELUSINE_DIR_CADSENAME. */
	public static final String	MELUSINE_DIR_CADSENAME		= ".melusine-dir/cadsename";

	/** The Constant MELUSINE_DIR_CADSENAME_ID. */
	public static final String	MELUSINE_DIR_CADSENAME_ID	= ".melusine-dir/cadsename.id";

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

	/** The delete old. */
	boolean				deleteOld			= true;

	/** The export source. */
	boolean				exportSource		= true;

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
		public Object getValue() {
			return tstamp;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieValueChanged(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
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

	}

	/**
	 * The Class MC_DeleteOld.
	 */
	class MC_DeleteOld extends AbstractModelController {

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IModelController#getValue()
		 */
		public Object getValue() {
			return deleteOld;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieValueChanged(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public void notifieValueChanged(UIField field, Object value) {
			deleteOld = (Boolean) value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.AbstractModelController#notifieValueDeleted(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		@Override
		public void notifieValueDeleted(UIField field, Object oldvalue) {
			deleteOld = true;
		}

		public ItemType getType() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	/**
	 * The Class MC_ExportSource.
	 */
	class MC_ExportSource extends AbstractModelController {

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IModelController#getValue()
		 */
		public Object getValue() {
			return exportSource;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieValueChanged(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public void notifieValueChanged(UIField field, Object value) {
			exportSource = (Boolean) value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.AbstractModelController#notifieValueDeleted(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		@Override
		public void notifieValueDeleted(UIField field, Object oldvalue) {
			exportSource = true;
		}

		public ItemType getType() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	/**
	 * The Class MC_ChooseFolder.
	 */
	class MC_ChooseFolder extends AbstractModelController {

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
			if (file == null || !file.exists() || !file.isDirectory()) {
				setMessageError("Select a folder");
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
	 * The Class IC_ChooseFolder.
	 */
	class IC_ChooseFolder extends IC_ForChooseFile {

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
			return FOLDER_EXT + WORKSPACE;
		}
	}

	/**
	 * Creates the choose folder field.
	 * 
	 * @return the d choose file ui
	 */
	public DChooseFileUI createChooseFolderField() {
		return new DChooseFileUI("selectJar", "Select folder", EPosLabel.left, new MC_ChooseFolder(),
				new IC_ChooseFolder(), "Select folder");
	}

	/**
	 * Creates the time stamp field.
	 * 
	 * @return the uI field
	 */
	public UIField createTimeStampField() {
		return new DCheckBoxUI("tstamp", "Add time stamp", EPosLabel.none, new MC_TSTamp(), null);
	}

	/**
	 * Creates the delete old field.
	 * 
	 * @return the uI field
	 */
	public UIField createDeleteOldField() {
		return new DCheckBoxUI("delete-old-bundle", "Delete old bundle", EPosLabel.none, new MC_DeleteOld(), null);
	}

	/**
	 * Creates the export source field.
	 * 
	 * @return the uI field
	 */
	public UIField createExportSourceField() {
		return new DCheckBoxUI("export-source", "Export source", EPosLabel.none, new MC_ExportSource(), null);
	}

	/** The cadsedef. */
	Item	cadsedef;

	/**
	 * Sets the cadsedef.
	 * 
	 * @param cadsedef
	 *            the new cadsedef
	 */
	public void setCadsedef(Item cadsedef) {
		this.cadsedef = cadsedef;
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
		try {
			EclipsePluginContentManger project = (EclipsePluginContentManger) cadsedef.getContentItem();
			pmo.beginTask("export cadse " + cadsedef.getName(), 1);

			String qname = CadseDefinitionManager.getUniqueName(cadsedef);
			String qname_version = "";
			String version = "";
			IJavaProject jp = project.getMainMappingContent(IJavaProject.class);
			IProject eclipseProject = project.getProject();
			IPath eclipseProjectPath = eclipseProject.getFullPath();

			// jp.getProject().build(kind, pmo)
			IPath defaultOutputLocation = jp.getOutputLocation();

			IPath manifestPath = new Path(JarFile.MANIFEST_NAME);
			HashSet<IPath> excludePath = new HashSet<IPath>();
			excludePath.add(eclipseProjectPath.append(manifestPath));
			excludePath.add(eclipseProjectPath.append(".project"));
			excludePath.add(eclipseProjectPath.append(".classpath"));
			excludePath.add(eclipseProjectPath.append("run-cadse-" + cadsedef.getName() + ".launch"));
			excludePath.add(eclipseProjectPath.append(".melusine.ser"));
			excludePath.add(eclipseProjectPath.append(".melusine.xml"));

			Manifest mf = new Manifest(eclipseProject.getFile(manifestPath).getContents());
			File pf = new File(file, qname + ".jar");
			File sourceDir = new File(file, qname + ".Source");

			if (tstamp) {
				Date d = new Date(System.currentTimeMillis());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
				String timeStamp = "v" + formatter.format(d);

				version = mf.getMainAttributes().getValue(OsgiManifest.BUNDLE_VERSION);
				String[] partVersion = version.split("\\.");
				if (partVersion.length == 4) {
					version = version + "-" + timeStamp;
				} else {
					version = version + "." + timeStamp;
				}
				mf.getMainAttributes().putValue(OsgiManifest.BUNDLE_VERSION, version);
				pf = new File(file, qname + "_" + version + ".jar");
				qname_version = qname + "_" + version;
				sourceDir = new File(file, qname + ".Source_" + version);
			}

			JarOutputStream outputStream = new JarOutputStream(new FileOutputStream(pf), mf);
			HashMap<IFile, IPath> files = new HashMap<IFile, IPath>();

			IWorkspaceRoot eclipseRoot = eclipseProject.getWorkspace().getRoot();
			IContainer classesFolder = (IContainer) eclipseRoot.findMember(defaultOutputLocation);
			addFolder(files, excludePath, classesFolder, Path.EMPTY);

			IClasspathEntry[] classpaths = jp.getRawClasspath();
			for (IClasspathEntry entry : classpaths) {
				if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
					if (entry.getOutputLocation() != null) {
						classesFolder = (IContainer) eclipseRoot.findMember(entry.getOutputLocation());
						addFolder(files, excludePath, classesFolder, Path.EMPTY);
					}
					IPath sourcePath = entry.getPath();
					excludePath.add(sourcePath);
					continue;
				}
				if (entry.getEntryKind() == IClasspathEntry.CPE_LIBRARY) {

				}
			}

			addFolder(files, excludePath, eclipseProject, Path.EMPTY);
			pmo.beginTask("export cadse " + cadsedef.getName(), files.size());

			Set<IFile> keySet = new TreeSet<IFile>(new Comparator<IFile>() {
				public int compare(IFile o1, IFile o2) {
					return o1.getFullPath().toPortableString().compareTo(o2.getFullPath().toPortableString());
				}
			});
			keySet.addAll(files.keySet());
			for (IFile f : keySet) {
				IPath entryPath = files.get(f);
				pmo.worked(1);
				try {
					ZipUtil.addEntryZip(outputStream, f.getContents(), entryPath.toPortableString(), f
							.getLocalTimeStamp());
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			outputStream.close();
			if (deleteOld) {
				File[] childrenFiles = file.listFiles();
				if (childrenFiles != null) {
					for (File f : childrenFiles) {
						if (f.equals(pf)) {
							continue;
						}

						String fileName = f.getName();

						if (!fileName.startsWith(qname)) {
							continue;
						}
						FileUtil.deleteDir(f);
					}
				}
			}
			if (exportSource && tstamp) {
				sourceDir.mkdir();
				File srcDir = new File(sourceDir, "src");
				srcDir.mkdir();
				File mfDir = new File(sourceDir, "META-INF");
				mfDir.mkdir();
				File modelDir = new File(srcDir, qname_version);
				modelDir.mkdir();
				FileUtil.setFile(new File(sourceDir, "plugin.xml"), "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
						+ "<?eclipse version=\"3.0\"?>\n" + "<plugin>\n"
						+ "<extension point=\"org.eclipse.pde.core.source\">\n" + "<location path=\"src\"/>\n"
						+ "</extension>\n" + "</plugin>\n");
				String vendor_info = CadseDefinitionManager.getVendorNameAttribute(cadsedef);
				FileUtil.setFile(new File(mfDir, "MANIFEST.MF"), "Manifest-Version: 1.0\n"
						+ "Bundle-ManifestVersion: 2\n" + "Bundle-SymbolicName: " + qname + ".Source;singleton:=true\n"
						+ "Bundle-Version: " + version + "\n" + "Bundle-Localization: plugin\n" + "Bundle-Vendor: "
						+ vendor_info + "\n" + "Bundle-Name: " + qname + "\n");

				excludePath.clear();
				files.clear();
				classesFolder = (IContainer) eclipseRoot.findMember(defaultOutputLocation);
				excludePath.add(classesFolder.getFullPath());
				for (IClasspathEntry entry : classpaths) {
					if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
						if (entry.getOutputLocation() != null) {
							classesFolder = (IContainer) eclipseRoot.findMember(entry.getOutputLocation());
							addFolder(files, excludePath, classesFolder, Path.EMPTY);
						}
						IPath sourcePath = entry.getPath();
						excludePath.add(sourcePath);
						ZipUtil.zipDirectory(eclipseRoot.findMember(sourcePath).getLocation().toFile(), new File(
								modelDir, "src.zip"), null);
						continue;
					}
				}
				addFolder(files, excludePath, eclipseProject, Path.EMPTY);
				keySet = files.keySet();
				for (IFile f : keySet) {
					IPath entryPath = files.get(f);
					try {
						FileUtil.copy(f.getLocation().toFile(), new File(modelDir, entryPath.toOSString()), false);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * Adds the folder.
	 * 
	 * @param files
	 *            the files
	 * @param excludePath
	 *            the exclude path
	 * @param container
	 *            the container
	 * @param path
	 *            the path
	 * 
	 * @throws CoreException
	 *             the core exception
	 */
	private void addFolder(HashMap<IFile, IPath> files, HashSet<IPath> excludePath, IContainer container, IPath path)
			throws CoreException {
		IResource[] members = container.members();
		for (IResource r : members) {
			if (excludePath.contains(r.getFullPath())) {
				continue;
			}
			excludePath.add(r.getFullPath());
			if (r.getType() != IResource.FILE) {
				addFolder(files, excludePath, (IContainer) r, path.append(r.getName()));
			} else {
				files.put((IFile) r, path.append(r.getName()));
			}
		}
	}

	/**
	 * Gets the persistance file all.
	 * 
	 * @param melusineDir
	 *            the melusine dir
	 * @param item
	 *            the item
	 * @param files
	 *            the files
	 * 
	 * @return the persistance file all
	 */
	public static void getPersistanceFileAll(File melusineDir, Item item, HashMap<File, String> files) {
		File xmlfile = new File(melusineDir, item.getId().toString() + ".ser");
		files.put(xmlfile, MELUSINE_DIR);

		xmlfile = new File(melusineDir, item.getId().toString() + ".xml");
		if (xmlfile.exists()) {
			files.put(xmlfile, MELUSINE_DIR);
		}

		List<? extends Link> links = item.getOutgoingLinks();
		for (Link link : links) {
			if (!link.getLinkType().isPart()) {
				continue;
			}
			if (!link.isLinkResolved()) {
				continue;
			}
			getPersistanceFileAll(melusineDir, link.getDestination(), files);
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
		if (selectJar2.isEmpty()) {
			return null;
		}

		IResource r = ResourcesPlugin.getWorkspace().getRoot().findMember(selectJar2);
		if (r != null) {
			return r.getLocation().toFile();
		}

		return selectJar2.toFile();
	}

}
