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

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.refactoring.RenameSupport;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import fede.workspace.eclipse.java.JMergeUtil;
import fede.workspace.tool.loadmodel.model.jaxb.CCadse;
import fede.workspace.tool.loadmodel.model.jaxb.CItemType;
import fede.workspace.tool.loadmodel.model.jaxb.CLink;
import fede.workspace.tool.loadmodel.model.jaxb.CLinkType;
import fede.workspace.tool.view.WSPlugin;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.AbstractModelController;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ic.IC_ForChooseFile;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.ui.DChooseFileUI;

/**
 * The Class MigrateCodePagesAction.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class MigrateCodePagesAction extends AbstractActionPage {

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

			if (element instanceof IProject) {
				return true;
			}
			return false;
		}

	}

	/** The old project path. */
	IPath				oldProjectPath;

	/** The old project. */
	IProject			oldProject;

	/** The old cadse. */
	CCadse				oldCadse;

	/** The new project path. */
	IPath				newProjectPath;

	/** The new project. */
	IProject			newProject;

	/** The new cadse. */
	CCadse				newCadse;

	/** The cadse viewer filter. */
	public ViewerFilter	cadseViewerFilter	= new CadseViewerFilter();

	/**
	 * The Class MC_OldCadse.
	 */
	class MC_OldCadse extends AbstractModelController {

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IModelController#getValue()
		 */
		public Object getValue() {
			return oldProjectPath;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieValueChanged(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public void notifieValueChanged(UIField field, Object value) {
			oldProjectPath = (IPath) value;
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
			oldProjectPath = null;
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
			oldProject = getProject((IPath) value);
			if (oldProject == null || !oldProject.exists()) {
				setMessageError("Select a valid java project");
				return true;
			}
			try {
				oldCadse = readCadse(oldProject);
				if (oldCadse == null) {
					setMessageError("Select a valid cadse jar");
					return true;
				}
				IJavaProject jp = JavaCore.create((IProject) oldProject);
				if (jp == null || !jp.exists()) {
					setMessageError("select a java project");
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
			} catch (CoreException e) {
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
	 * The Class MC_NewCadse.
	 */
	class MC_NewCadse extends AbstractModelController {

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IModelController#getValue()
		 */
		public Object getValue() {
			return newProjectPath;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see fr.imag.adele.cadse.core.ui.IEventListener#notifieValueChanged(fr.imag.adele.cadse.core.ui.UIField,
		 *      java.lang.Object)
		 */
		public void notifieValueChanged(UIField field, Object value) {
			newProjectPath = (IPath) value;
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
			newProjectPath = null;
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
			newProject = getProject((IPath) value);
			if (newProject == null || !newProject.exists()) {
				setMessageError("Select a valid java project");
				return true;
			}
			try {
				newCadse = readCadse(newProject);
				if (newCadse == null) {
					setMessageError("Select a valid cadse jar");
					return true;
				}
				IJavaProject jp = JavaCore.create((IProject) newProject);
				if (jp == null || !jp.exists()) {
					setMessageError("select a java project");
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
			} catch (CoreException e) {
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
			return WORKSPACE;
		}
	}

	/**
	 * Creates the old cadse field.
	 * 
	 * @return the d choose file ui
	 */
	public DChooseFileUI createOldCadseField() {
		return new DChooseFileUI("selectJar", "Select old cadse", EPosLabel.left, new MC_OldCadse(), new IC_Import(),
				"Select old cadse");
	}

	/**
	 * Creates the new cadse field.
	 * 
	 * @return the d choose file ui
	 */
	public DChooseFileUI createNewCadseField() {
		return new DChooseFileUI("selectJar", "Select new cadse", EPosLabel.left, new MC_NewCadse(), new IC_Import(),
				"Select new cadse");
	}

	/**
	 * Read cadse.
	 * 
	 * @param p
	 *            the p
	 * 
	 * @return the c cadse
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 * @throws CoreException
	 *             the core exception
	 */
	public CCadse readCadse(IProject p) throws IOException, JAXBException, CoreException {
		IFile f = p.getFile("model/cadse.xml");
		if (!f.exists()) {
			throw new IOException("Cannot found /model/cadse.xml");
		}

		InputStream imput = f.getContents();
		JAXBContext jc = JAXBContext.newInstance("fede.workspace.tool.loadmodel.model.jaxb", this.getClass()
				.getClassLoader());
		Unmarshaller m = jc.createUnmarshaller();
		return (CCadse) m.unmarshal(imput);
	}

	/**
	 * Write cadse.
	 * 
	 * @param p
	 *            the p
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 * @throws CoreException
	 *             the core exception
	 */
	public void writeCadse(IProject p) throws IOException, JAXBException, CoreException {
		IFile f = p.getFile("model/cadse.xml");
		JAXBContext jc = JAXBContext.newInstance("fede.workspace.tool.loadmodel.model.jaxb", this.getClass()
				.getClassLoader());
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		Writer writer = new StringWriter();
		m.marshal(oldCadse, writer);
		JMergeUtil.setContent(f, writer.toString(), new NullProgressMonitor());
	}

	/** The its_old. */
	HashMap<String, CItemType>	its_old;

	/** The its_new. */
	HashMap<String, CItemType>	its_new;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imag.adele.cadse.core.impl.ui.AbstractActionPage#doFinish(java.lang.Object)
	 */
	@Override
	public void doFinish(Object monitor) throws Exception {
		super.doFinish(monitor);
		IProgressMonitor pmo = (IProgressMonitor) monitor;
		its_old = new HashMap<String, CItemType>();
		its_new = new HashMap<String, CItemType>();
		for (CItemType cit : oldCadse.getItemType()) {
			its_old.put(cit.getId(), cit);
		}
		for (CItemType cit : newCadse.getItemType()) {
			its_new.put(cit.getId(), cit);
		}
		IFolder oldsources = oldProject.getFolder("sources");
		IJavaProject oldjp = JavaCore.create(oldProject);
		IPackageFragmentRoot joldsources = oldjp.getPackageFragmentRoot(oldsources);
		String oldCstClass = oldCadse.getCstClass();
		IPackageFragment pn = joldsources.getPackageFragment(getPackage(oldCstClass));
		String oldCstCn = getClassName(oldCstClass);
		ICompilationUnit cu = pn.getCompilationUnit(oldCstCn + ".java");
		IType cstType = cu.getType(oldCstCn);

		for (CItemType cit : oldCadse.getItemType()) {
			CItemType newcit = its_new.get(cit.getId());
			if (newcit == null) {
				continue;
			}
			String oldcst = cit.getCstName();
			String newcst = cit.getCstName();
			if (!oldcst.equals(newcst)) {
				IField cstField = cstType.getField(oldcst);
				if (cstField.exists()) {
					RenameSupport.create(cstField, newcst, RenameSupport.UPDATE_REFERENCES);
					cit.setCstName(newcst);
				}
			}
			HashMap<String, CLinkType> ltits_new = new HashMap<String, CLinkType>();

			for (CLink clt : newcit.getOutgoingLink()) {
				if (!(clt instanceof CLinkType))
					continue;
				ltits_new.put(((CLinkType) clt).getName(), (CLinkType) clt);
			}
			for (CLink clt : cit.getOutgoingLink()) {
				if (!(clt instanceof CLinkType))
					continue;

				CLinkType newclt = ltits_new.get(((CLinkType) clt).getName());
				if (newclt == null)
					continue;
				oldcst = ((CLinkType) clt).getCstName();
				newcst = newclt.getCstName();
				if (!oldcst.equals(newcst)) {
					IField cstField = cstType.getField(oldcst);
					if (cstField.exists()) {
						RenameSupport.create(cstField, newcst, RenameSupport.UPDATE_REFERENCES);
						((CLinkType) clt).setCstName(newcst);
					}
				}
			}
			pmo.worked(1);
		}
		writeCadse(oldProject);
		// finish1(pmo);
	}

	/**
	 * Finish1.
	 * 
	 * @param pmo
	 *            the pmo
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 * @throws CoreException
	 *             the core exception
	 */
	private void finish1(IProgressMonitor pmo) throws IOException, JAXBException, CoreException {
		CadseCore.getCadseDomain().beginOperation("Import binary cadse");
		List<IJavaElement> destinations = new ArrayList<IJavaElement>();
		List<IJavaElement> elements = new ArrayList<IJavaElement>();
		List<String> names = new ArrayList<String>();
		try {
			pmo.beginTask("migrate code from " + oldCadse.getName() + " to " + newCadse.getName(), oldCadse
					.getItemType().size() * 2 + 1);

			its_old = new HashMap<String, CItemType>();
			its_new = new HashMap<String, CItemType>();
			IFolder oldsources = oldProject.getFolder("sources");
			IFolder newsources = newProject.getFolder("sources");
			IJavaProject oldjp = JavaCore.create(oldProject);
			IJavaProject newjp = JavaCore.create(newProject);

			IPackageFragmentRoot joldsources = oldjp.getPackageFragmentRoot(oldsources);
			IPackageFragmentRoot jnewsources = newjp.getPackageFragmentRoot(newsources);

			for (CItemType cit : oldCadse.getItemType()) {
				its_old.put(cit.getId(), cit);
			}
			for (CItemType cit : newCadse.getItemType()) {
				its_new.put(cit.getId(), cit);
			}
			pmo.worked(1);

			for (CItemType cit : oldCadse.getItemType()) {
				pmo.worked(1);

				String qclazz = cit.getManagerClass();
				if (qclazz == null)
					continue;

				String oldpn = getPackage(qclazz);
				String oldcn = getClassName(qclazz);

				CItemType newcit = its_new.get(cit.getId());
				if (newcit == null) {
					System.out.println("Cannot migrate " + qclazz);
					continue;
				}
				String newqclazz = newcit.getManagerClass();
				if (newqclazz == null) {
					System.out.println("Cannot migrate " + qclazz);
					continue;
				}
				String newpn = getPackage(newqclazz);
				String newcn = getClassName(newqclazz);
				IFile fo = oldsources.getFile(new Path(oldpn.replace('.', '/')).append(oldcn + ".java"));
				IFile fn = newsources.getFile(new Path(newpn.replace('.', '/')).append(newcn + ".java"));
				if (!fo.exists() || !fn.exists()) {
					System.out.println("Cannot migrate " + qclazz);
					continue;
				}

				ICompilationUnit oldcu = JavaCore.createCompilationUnitFrom(fo);
				ICompilationUnit newcu = JavaCore.createCompilationUnitFrom(fn);
				if (oldcu == null || newcu == null || !oldcu.exists() || !newcu.exists()) {
					System.out.println("Cannot migrate " + qclazz);
					continue;
				}

				IJavaElement findelement = oldcu;
				IJavaElement newdestination = joldsources.createPackageFragment(oldpn, true, pmo);
				;
				String newname = oldcn + ".java";

				if (!oldpn.equals(newpn)) {
					// move
					System.out.println("move " + oldpn + " to " + newpn);
					newdestination = joldsources.createPackageFragment(newpn, true, pmo);
				}
				if (!oldcn.equals(newcn)) {
					System.out.println("rename " + oldcn + " to " + newcn);
					newname = newcn + ".java";
				}
				elements.add(findelement);
				destinations.add(newdestination);
				names.add(newname);

				cit.setManagerClass(oldCadse.getName() + "/" + newpn + "." + newcn);
				System.out.println("change manager to " + cit.getManagerClass());
			}
			IJavaModel jm = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot());
			jm.rename((IJavaElement[]) elements.toArray(new IJavaElement[elements.size()]),
					(IJavaElement[]) destinations.toArray(new IJavaElement[destinations.size()]), (String[]) names
							.toArray(new String[names.size()]), false, pmo);

			writeCadse(oldProject);
			//			
			// for (CItemType cit : cadse.getItemType()) {
			// Item sourceItemType = its.get(cit.getId());
			// if (sourceItemType == null) continue;
			// for( CLinkType clt : cit.getOutgoingLink()) {
			//					
			// }
			// pmo.worked(1);
			// }

		} catch (JavaModelException e) {
			System.out.println(e.getStatus());
			e.printStackTrace();
		} finally {
			CadseCore.getCadseDomain().endOperation();
		}
	}

	/**
	 * Gets the class name.
	 * 
	 * @param qclazz
	 *            the qclazz
	 * 
	 * @return the class name
	 */
	private String getClassName(String qclazz) {
		int i = qclazz.lastIndexOf('.');
		return qclazz.substring(i + 1);
	}

	/**
	 * Gets the package.
	 * 
	 * @param qclazz
	 *            the qclazz
	 * 
	 * @return the package
	 */
	private String getPackage(String qclazz) {
		int i;
		if (qclazz == null)
			return null;
		i = qclazz.indexOf('/');
		if (i != -1) {
			qclazz = qclazz.substring(i + 1);
		}
		i = qclazz.lastIndexOf('.');
		return qclazz.substring(0, i);
	}

	/**
	 * Gets the project.
	 * 
	 * @param selectJar2
	 *            the select jar2
	 * 
	 * @return the project
	 */
	private IProject getProject(IPath selectJar2) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(selectJar2.lastSegment());
	}

}
