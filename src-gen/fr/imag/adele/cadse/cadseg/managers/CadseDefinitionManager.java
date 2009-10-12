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
package fr.imag.adele.cadse.cadseg.managers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.osgi.framework.BundleException;

import fede.workspace.dependencies.eclipse.java.fix.IFixManager;
import fede.workspace.eclipse.java.fields.DefaultJavaSourceUserController;
import fede.workspace.eclipse.java.fields.JavaFieldsCore;
import fede.workspace.eclipse.java.osgi.OsgiManifest;
import fede.workspace.model.manager.properties.FieldsCore;
import fede.workspace.model.manager.properties.IC_ForCheckedViewer;
import fede.workspace.model.manager.properties.IFieldContenProposalProvider;
import fede.workspace.model.manager.properties.Proposal;
import fede.workspace.model.manager.properties.impl.ic.IC_Abstract;
import fede.workspace.model.manager.properties.impl.ic.IC_StaticArrayOfObjectForBrowser_Combo;
import fede.workspace.model.manager.properties.impl.ui.DCheckBoxUI;
import fede.workspace.model.manager.properties.impl.ui.DTextUI;
import fede.workspace.tool.eclipse.MappingManager;
import fr.imag.adele.cadse.cadseg.DefaultWorkspaceManager;
import fr.imag.adele.cadse.cadseg.IC_ItemTypeTemplateForText;
import fr.imag.adele.cadse.cadseg.IModelWorkspaceManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.cadseg.contents.CadseDefinitionCIF;
import fr.imag.adele.cadse.cadseg.contents.CadseDefinitionContent;
import fr.imag.adele.cadse.cadseg.fields.RegExContentProposalProvider;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.CreationDialogManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.cadseg.model.Event;
import fr.imag.adele.cadse.cadseg.operation.WorkspaceActionContributor;
import fr.imag.adele.cadse.cadseg.path.ParsePath;
import fr.imag.adele.cadse.cadseg.type.EventType;
import fr.imag.adele.cadse.core.CadseDomain;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.core.CompactUUID;
import fr.imag.adele.cadse.core.IContentItemFactory;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.impl.ui.AbstractActionPage;
import fr.imag.adele.cadse.core.impl.ui.CreationAction;
import fr.imag.adele.cadse.core.impl.ui.MC_AttributesItem;
import fr.imag.adele.cadse.core.impl.ui.ModificationAction;
import fr.imag.adele.cadse.core.key.SpaceKeyType;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.ui.IInteractionController;
import fr.imag.adele.cadse.core.ui.IModelController;
import fr.imag.adele.cadse.core.ui.Pages;
import fr.imag.adele.cadse.core.ui.UIField;
import fr.imag.adele.cadse.core.util.Convert;
import java.lang.String;
import fr.imag.adele.cadse.core.var.ContextVariable;

/**
 * The Class CadseDefinitionManager.
 */
public class CadseDefinitionManager extends CadseRuntimeManager implements IModelWorkspaceManager, IFixManager {
	public static final String	MAPPING				= "mapping";

	public static final String	BUILD_MODEL			= "build-model";

	public static final String	CONFIGURATION_MODEL	= "configuration-model";

	public static final String	DATA_MODEL			= "data-model";

	/** The Constant DEFAULT_SHORT_NAME. */
	public static final String	VIEW_MODEL			= "view-model";

	

	/**
	 * Gets the uUID.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the uUID
	 */
	public static CompactUUID getIdRuntime(Item cadseDefinition) {
		if (cadseDefinition.getType() == CadseGCST.CADSE_RUNTIME) {
			return cadseDefinition.getId();
		}
		
		String uuid_str = cadseDefinition.getAttribute(CadseGCST.CADSE_DEFINITION_at_ID_RUNTIME_);
		if (uuid_str == null || uuid_str.length() == 0) {
			CompactUUID uuid = CompactUUID.randomUUID();
			uuid_str = uuid.toString();
			try {
				cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_ID_RUNTIME_, uuid_str);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return uuid;
		}
		return new CompactUUID(uuid_str);
	}
	
	/**
	 * Gets the uUID.
	 * 
	 * @param itemtype
	 *            the itemtype
	 * 
	 * @return the uUID
	 */
	public static CompactUUID getIdDef(Item cadseDefinition) {
		if (cadseDefinition.getType() == CadseGCST.CADSE_RUNTIME) {
			return new CompactUUID(cadseDefinition.getAttribute(CadseGCST.CADSE_RUNTIME_at_ID_DEFINITION_));
		}
		return cadseDefinition.getId();
	}

	/**
	 * The Class CorrectManifestAfterRenameChange.
	 */
	final class CorrectManifestAfterRenameChange extends Change {

		/** The manager. */
		CadseDefinitionContent	manager;

		/** The old default package. */
		String							oldDefaultPackage;

		/** The new default package. */
		String							newDefaultPackage;

		/** The old unique name. */
		String							oldUniqueName;

		/**
		 * Instantiates a new correct manifest after rename change.
		 * 
		 * @param manager
		 *            the manager
		 * @param oldDefaultPackage
		 *            the old default package
		 * @param newDefaultPackage
		 *            the new default package
		 * @param oldUniqueName
		 *            the old unique name
		 */
		public CorrectManifestAfterRenameChange(CadseDefinitionContent manager, String oldDefaultPackage,
				String newDefaultPackage, String oldUniqueName) {
			super();
			this.manager = manager;
			this.oldDefaultPackage = oldDefaultPackage;
			this.newDefaultPackage = newDefaultPackage;
			this.oldUniqueName = oldUniqueName;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ltk.core.refactoring.Change#getModifiedElement()
		 */
		@Override
		public Object getModifiedElement() {
			return manager;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ltk.core.refactoring.Change#getName()
		 */
		@Override
		public String getName() {
			return "Correct Manifest for item " + manager.getItem().getQualifiedName();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.ltk.core.refactoring.Change#initializeValidationData(
		 * org.eclipse.core.runtime.IProgressMonitor)
		 */
		@Override
		public void initializeValidationData(IProgressMonitor pm) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.ltk.core.refactoring.Change#isValid(org.eclipse.core.
		 * runtime.IProgressMonitor)
		 */
		@Override
		public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException, OperationCanceledException {
			IFile manifestFile = manager.getManifestFile();
			if (!manifestFile.exists()) {
				System.out.println("refactoring error !!! " + manifestFile.getFullPath().toPortableString());
			}
			return new RefactoringStatus();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.ltk.core.refactoring.Change#perform(org.eclipse.core.
		 * runtime.IProgressMonitor)
		 */
		@Override
		public Change perform(IProgressMonitor pm) throws CoreException {
			try {
				OsgiManifest omf = new OsgiManifest(manager.getProject());
				omf.removeEntry(OsgiManifest.EXPORT_PACKAGE, oldDefaultPackage);
				omf.putArray(OsgiManifest.EXPORT_PACKAGE, true, false, newDefaultPackage);
				omf.put(OsgiManifest.BUNDLE_SYMBOLICNAME, manager.getItem().getQualifiedName());
				omf.put(OsgiManifest.BUNDLE_NAME, manager.getItem().getQualifiedName());
				omf.put(OsgiManifest.BUNDLE_SYMBOLICNAME, manager.getItem().getQualifiedName() + ";singleton:=true");
				omf.put(OsgiManifest.BUNDLE_ACTIVATOR, newDefaultPackage + ".Activator");
				manager.computeManifest(omf);
				StringBuilder sb = new StringBuilder();
				omf.write(sb);
				MappingManager.generate(manager.getProject(), new Path("META-INF"), "MANIFEST.MF", sb.toString(), pm);
			} catch (BundleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

	}

	/**
	 * The Class SelectionMC.
	 */
	public static class SelectionMC extends MC_AttributesItem {

		/**
		 * Instantiates a new selection mc.
		 */
		public SelectionMC() {
			super();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fr.imag.adele.cadse.core.ui.AbstractModelController#validValueChanged
		 * (fr.imag.adele.cadse.core.ui.UIField, java.lang.Object)
		 */
		@Override
		public boolean validValueChanged(UIField field, Object value) {
			Item currentItem = getItem();
			Item source = LinkManager.getSource(currentItem);
			Item dest = LinkManager.getDestination(currentItem);
			ParsePath pp = new ParsePath(source, dest, (String) value);
			String error = pp.getError();
			if (error != null) {
				setMessageError(error);
				return true;
			}
			return false;

		}

	}

	/**
	 * The Class ValidFieldUC.
	 */
	public static class ValidFieldUC extends IC_Abstract implements IInteractionController,
			IFieldContenProposalProvider {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fede.workspace.model.manager.properties.IFieldContenProposalProvider
		 * #getAutoActivationCharacters()
		 */
		public char[] getAutoActivationCharacters() {
			return new char[] { '\\', '[', '(' };
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fede.workspace.model.manager.properties.IFieldContenProposalProvider
		 * #getCommandId()
		 */
		public String getCommandId() {
			return ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fede.workspace.model.manager.properties.IFieldContenProposalProvider
		 * #getContentProposalProvider()
		 */
		public IContentProposalProvider getContentProposalProvider() {
			return new RegExContentProposalProvider(true);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * fede.workspace.model.manager.properties.IFieldContenProposalProvider
		 * #getProposalAcceptanceStyle()
		 */
		public int getProposalAcceptanceStyle() {
			return ContentProposalAdapter.PROPOSAL_INSERT;
		}

		public Object setControlContents(String newValue) {
			return null;
		}

		public Object getValueFromProposal(Proposal proposal) {
			return proposal.getContent();
		}

		public ItemType getType() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// /** The Constant IMPORTS_ATTRIBUTE. */
	// public static final String IMPORTS_ATTRIBUTE = "imports";

	/** The Constant SOURCES. */
	private static final String	SOURCES	= "sources";




	
	/** The Constant DEFAULT_PACKAGE. */
	public final static String	DEFAULT_PACKAGE	= "model.workspace.";

	/**
	 * Instantiates a new cadse definition manager.
	 */
	public CadseDefinitionManager() {
	}

	

	/**
	 * Gets the all dependencies cadse.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the all dependencies cadse
	 */
	public static List<Item> getAllDependenciesCadse(Item cadseDefinition) {
		List<Item> ret = new ArrayList<Item>();
		Collection<Item> aextends = getExtends(cadseDefinition);
		if (aextends.size() != 0) {
			ret.addAll(aextends);
			for (Item subcadse : aextends) {
				ret.addAll(getAllDependenciesCadse(subcadse));
			}
		}
		return ret;
	}

	/**
	 * Gets the dependencies cadses and me.
	 * 
	 * @param cadsedef
	 *            the cadsedef
	 * 
	 * @return the dependencies cadses and me
	 */
	public static Item[] getDependenciesCadsesAndMe(Item cadsedef) {
		List<Item> allcadse = getAllDependenciesCadse(cadsedef);
		allcadse.add(cadsedef);
		Item[] ret = allcadse.toArray(new Item[allcadse.size()]);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#init(fr.imag.adele.cadse
	 * .core.ItemType)
	 */
	@Override
	public void init() {
		CadseGCST.CADSE_DEFINITION.setSpaceKeyType(new SpaceKeyType(CadseGCST.CADSE_DEFINITION, null));
		new CadseG_WLWCListener();
		new CadseG_WorkspaceListener();
		CadseCore.theItem.addActionContributeur(new WorkspaceActionContributor());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#computeUniqueName(fr.
	 * imag.adele.cadse.core.Item, java.lang.String,
	 * fr.imag.adele.cadse.core.Item, fr.imag.adele.cadse.core.LinkType)
	 */
	@Override
	public String computeQualifiedName(Item item, String shortid, Item parent, LinkType type) {
		if (shortid.contains("."))
			return shortid;				
		return CadseRuntime.CADSE_NAME_SUFFIX + shortid;
	}

	/**
	 * Gets the display name.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the display name
	 * 
	 * @generated
	 */
	@Override
	public String getDisplayName(Item item) {
		try {
			Object value;
			return item.getName();
		} catch (Throwable e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * Gets the package name.
	 * 
	 * @param wsModelItem
	 *            the ws model item
	 * 
	 * @return the package name
	 */
	public static String getPackageName(Item wsModelItem) {
		return wsModelItem.getAttribute(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_);
	}

	/**
	 * Gets the imports.
	 * 
	 * @param wsModelItem
	 *            the ws model item
	 * 
	 * @return the imports
	 */
	public static List<String> getImports(Item wsModelItem) {
		return wsModelItem.getAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS_);
	}

	@Override
	public IContentItemFactory getContentItemFactory() {
		return new CadseDefinitionCIF();
	}

	/**
	 * Checks for package.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return true, if successful
	 */
	static public boolean hasPackage(Item item) {
		String p = getPackageName(item);
		return (p != null && p.length() != 0);
	}

	/**
	 * Gets the default package.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param item
	 *            the item
	 * 
	 * @return the default package
	 */
	static public String getDefaultPackage(ContextVariable cxt, Item item) {
		String p = getPackageName(item);
		if (p != null && p.length() != 0) {
			return p;
		}
		return DEFAULT_PACKAGE + cxt.getAttribute(item, CadseGCST.ITEM_at_NAME_).toLowerCase();
	}

	/**
	 * Gets the default package path.
	 * 
	 * @param cxt
	 *            the cxt
	 * @param item
	 *            the item
	 * 
	 * @return the default package path
	 */
	public static IPath getDefaultPackagePath(ContextVariable cxt, Item item) {
		return new Path(SOURCES).append(new Path(getDefaultPackage(cxt, item).replace('.', '/')));
	}

	/**
	 * Creates the creation dialog_ creation dialog.
	 * 
	 * @param parent
	 *            the parent
	 * @param type
	 *            the type
	 * @param lt
	 *            the lt
	 * 
	 * @return the pages
	 */
	// public static Pages createCreationDialog_CreationDialog(Item parent,
	// ItemType type, LinkType lt) {
	// CreationAction action = new CreationAction(parent, type, lt,
	// CreationDialogManager.DEFAULT_SHORT_NAME);
	//
	// DTextUI d;
	// DTextUI g;
	// DCheckBoxUI a;
	// DCheckBoxUI e;
	// Pages ret = FieldsCore.createWizard(action,
	// FieldsCore.createPage("page1", "Create a creation dialog",
	// "Create a creation dialog", 3, d = FieldsCore.createTextField(
	// CadseGCST.CREATION_DIALOG_at_DEFAULT_SHORT_NAME,
	// "default short name"), a = FieldsCore
	// .createCheckBox(CadseGCST.CREATION_DIALOG_at_AUTOMATIC_SHORT_NAME,
	// "automatic short name"),
	// e =
	// FieldsCore.createCheckBox(CadseGCST.CREATION_DIALOG_at_EXTENDS_DIALOG_CONTROLLER,
	// "add action creation dialog"), g = FieldsCore.createTextField(
	// CadseGCST.CREATION_DIALOG_at_GENERATE_AUTOMATIC_SHORT_NAME,
	// "generate automatic short name",
	// 1, "", new IC_ItemTypeTemplateForText() {
	// @Override
	// protected Item getItemFromContext() {
	// Item itemtype = getContext().getPartParent();
	// return itemtype;
	// }
	// }, null)));
	// new CreationDialogMC(d, a, e, g);
	// return ret;
	// }

	// /**
	// * Creates the creation dialog_ modification dialog.
	// *
	// * @param item
	// * the item
	// *
	// * @return the pages
	// */
	// public static Pages createCreationDialog_ModificationDialog(Item item) {
	// AbstractActionPage action = new ModificationAction(item);
	//
	// DTextUI d;
	// DTextUI g;
	// DCheckBoxUI a;
	// DCheckBoxUI e;
	// Pages ret = FieldsCore.createWizard(action,
	// FieldsCore.createPage("page1", "Creation dialog",
	// "Creation dialog", 3, d = FieldsCore.createTextField(
	// CadseGCST.CREATION_DIALOG_at_DEFAULT_SHORT_NAME,
	// "default short name"), a = FieldsCore
	// .createCheckBox(CadseGCST.CREATION_DIALOG_at_AUTOMATIC_SHORT_NAME,
	// "automatic short name"),
	// e =
	// FieldsCore.createCheckBox(CadseGCST.CREATION_DIALOG_at_EXTENDS_DIALOG_CONTROLLER,
	// "extends dialog controller"), g = FieldsCore.createTextField(
	// CadseGCST.CREATION_DIALOG_at_GENERATE_AUTOMATIC_SHORT_NAME,
	// "generate automatic short name",
	// 1, "", new IC_ItemTypeTemplateForText() {
	// @Override
	// protected Item getItemFromContext() {
	// Item itemtype = getContext().getPartParent();
	// return itemtype;
	// }
	// }, null)));
	// new CreationDialogMC(d, a, e, g);
	// return ret;
	// }

	/**
	 * TODO rename to getAllItemType.
	 * 
	 * @param cadseDefinition
	 *            the workspace model
	 * 
	 * @return the item types
	 */
	public static Item[] getItemTypes2(Item cadseDefinition) {
		Item dataModel = getMainDataModel(cadseDefinition);
		return ItemTypeManager.getAllItemType(dataModel);
	}

	/**
	 * Return the datamodel of the model cadseDefinition.
	 * 
	 * @param cadseDefinition
	 *            A cadseDefinition
	 * 
	 * @return A data model
	 */
	public static Item getMainDataModel(Item cadseDefinition) {
		Item dataModel = cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL, true);
		return dataModel;
	}

	/**
	 * Gets the view model.
	 * 
	 * @param item
	 *            the item
	 * 
	 * @return the view model
	 */
	public static Item getViewModel(Item item) {
		Item viewModel = item.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL, true);
		if (viewModel == null) {
			List<Item> viewModels = CadseGCST.VIEW_MODEL.getItems();
			for (Item aViewModel : viewModels) {
				Item parentViewModel = aViewModel.getPartParent();
				if (parentViewModel == item) {
					// find good
					try {
						aViewModel.getPartParent(true);
					} catch (Throwable e) {
					}
					return aViewModel;
				}
			}
			try {
				viewModel = CadseCore.createItemIfNeed(null, VIEW_MODEL, CadseGCST.VIEW_MODEL, item,
						CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return viewModel;
	}

	/**
	 * set a link 'view-model' from 'CadseDefinition' to 'ViewModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setViewModel(Item cadseDefinition, Item value) throws CadseException {
		cadseDefinition.setOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL,value);
	}

	/**
	 * Gets the imports attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the imports attribute
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final List<String> getImportsAttribute(Item cadseDefinition) {
		try {
			List<String> list = cadseDefinition.getAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS_);

			if (list == null)
				return null;

			return new ArrayList<String>(list);
		} catch (Throwable t) {
			return new ArrayList<String>();
		}
	}

	/**
	 * Sets the imports attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param valueList
	 *            the value list
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void setImportsAttribute(Item cadseDefinition, List<String> valueList) {
		try {
			List<String> list = new ArrayList<String>(valueList);
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * Adds the imports attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @not generated
	 */
	@SuppressWarnings("unchecked")
	public static final void addImportsAttribute(Item cadseDefinition, String value) {
		try {
			List<String> list = (List<String>) cadseDefinition.getAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS);
			if (list == null) {
				list = new ArrayList<String>();
			}
			String setvalue = value;
			list.add(setvalue);
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS, list);
			((IGenerateContent) cadseDefinition.getContentItem()).generate(ContextVariable.DEFAULT);
		} catch (Throwable t) {

		}
	}

	/**
	 * Removes the imports attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public static final void removeImportsAttribute(Item cadseDefinition, String value) {
		try {

			List<String> list = cadseDefinition.getAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS_);
			if (list == null) {
				return;
			}
			list.remove(value);
			if (list.size() == 0)
				cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS_, null);
			else
				cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_IMPORTS_, list);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the packagename attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the packagename attribute
	 * 
	 * @generated
	 */
	public static final String getPackagenameAttribute(Item cadseDefinition) {
		return cadseDefinition.getAttributeWithDefaultValue(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_, null);
	}

	/**
	 * Sets the packagename attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setPackagenameAttribute(Item cadseDefinition, String value) {
		try {
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the vendor name attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the vendor name attribute
	 * 
	 * @generated
	 */
	public static final String getVendorNameAttribute(Item cadseDefinition) {
		return cadseDefinition.getAttributeWithDefaultValue(CadseGCST.CADSE_DEFINITION_at_VENDOR_NAME_, null);
	}

	/**
	 * Sets the vendor name attribute.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @generated
	 */
	public static final void setVendorNameAttribute(Item cadseDefinition, String value) {
		try {
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_VENDOR_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final String getVersionCadseAttribute(Item cadseDefinition) {
		return cadseDefinition.getAttributeWithDefaultValue(CadseGCST.CADSE_DEFINITION_at_VERSION_CADSE_, null);
	}

	/**
	 * @generated
	 */
	public static final void setVersionCadseAttribute(Item cadseDefinition, String value) {
		try {
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_VERSION_CADSE_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * @generated
	 */
	public static final String getCommentaryAttribute(Item cadseDefinition) {
		return cadseDefinition.getAttributeWithDefaultValue(CadseGCST.CADSE_DEFINITION_at_COMMENTARY_, null);
	}

	/**
	 * @generated
	 */
	public static final void setCommentaryAttribute(Item cadseDefinition, String value) {
		try {
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_COMMENTARY_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getCadseNameAttribute(Item cadseDefinition) {
		return cadseDefinition.getAttributeWithDefaultValue(CadseGCST.CADSE_DEFINITION_at_CADSE_NAME_, null);
	}

	/**
		@generated
	*/
	public static final void setCadseNameAttribute(Item cadseDefinition, String value) {
		try {
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_CADSE_NAME_, value);
		} catch (Throwable t) {

		}
	}

	/**
		@generated
	*/
	public static final String getIdRuntimeAttribute(Item cadseDefinition) {
		return cadseDefinition.getAttributeWithDefaultValue(CadseGCST.CADSE_DEFINITION_at_ID_RUNTIME_, null);
	}

	/**
		@generated
	*/
	public static final void setIdRuntimeAttribute(Item cadseDefinition, String value) {
		try {
			cadseDefinition.setAttribute(CadseGCST.CADSE_DEFINITION_at_ID_RUNTIME_, value);
		} catch (Throwable t) {

		}
	}

	/**
	 * Gets the mapping model.
	 * 
	 * @param wsModel
	 *            the ws model
	 * 
	 * @return the mapping model
	 */
	public static Item getMappingModel(Item wsModel) {
		Item mappingModel = wsModel.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_MAPPING, true);
		if (mappingModel == null) {
			try {
				mappingModel = CadseCore.createItemIfNeed(null, MAPPING, CadseGCST.MAPPING_MODEL, wsModel,
						CadseGCST.CADSE_DEFINITION_lt_MAPPING);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mappingModel;
	}

	

	

	/**
	 * Gets the builds the model.
	 * 
	 * @param wsmodel
	 *            the wsmodel
	 * 
	 * @return the builds the model
	 */
	public static Item getBuildModel(Item wsmodel) {
		Item buildModel = wsmodel.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_BUILD, true);
		return buildModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#validate(fr.imag.adele
	 * .cadse.core.Item, fr.imag.adele.cadse.core.IItemManager.ProblemReporter)
	 */
	@Override
	public List<Item> validate(Item item, ProblemReporter reporter) {
		// TODO Auto-generated method stub
		return super.validate(item, reporter);
	}

	/**
	 * Gets the workspace model_static.
	 * 
	 * @param modelName
	 *            the model name
	 * 
	 * @return the workspace model_static
	 */
	public static Item getWorkspaceModel_static(String modelName) {
		LogicalWorkspace wsModel = CadseCore.getLogicalWorkspace();
		if (wsModel != null) {
			return wsModel.getItem(modelName);
		}
		return null;
	}
	
	/**
	 * Gets the workspace model_static.
	 * 
	 * @param modelName
	 *            the model name
	 * 
	 * @return the workspace model_static
	 */
	public Item getWorkspaceModel(Item source) {
		return source;
	}

	/**
	 * Gets the source folder.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the source folder
	 */
	public static IContainer getSourceFolder(Item cadseDefinition) {
		CadseDefinitionContent cm = (CadseDefinitionContent) cadseDefinition.getContentItem();
		if (cm != null) {
			return cm.getSourceFolder(ContextVariable.DEFAULT);
		}
		return null;
	}

	public static IFile getCSTCU(ContextVariable cxt, Item cadseDefinition) {
		return CadseDefinitionManager.getJavaFile(cadseDefinition, "cst", GenerateJavaIdentifier
				.javaPackageNameFileCST_FromCadseDefinition(cxt, cadseDefinition), GenerateJavaIdentifier
				.javaClassNameFileCST_FromCadseDefinition(cxt, cadseDefinition));
	}

	/**
	 * Gets the java file.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param pn
	 *            the pn
	 * @param cn
	 *            the cn
	 * 
	 * @return the java file
	 */
	public static IFile getJavaFile(Item cadseDefinition, String key, String pn, String cn) {

		String projectName = GenerateJavaIdentifier.ow(cadseDefinition, "project." + key, cadseDefinition
				.getQualifiedName());
		IContainer sourceFolder = CadseDefinitionManager.getSourceFolder(cadseDefinition);
		String srcName = GenerateJavaIdentifier.ow(cadseDefinition, "src." + key, sourceFolder.getProjectRelativePath()
				.toPortableString());

		IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		sourceFolder = p.getFolder(srcName);
		IPath javapath = new Path(pn.replace('.', '/')).append(cn + ".java");
		return sourceFolder.getFile(javapath);

	}

	/**
	 * Gets the java type.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param f
	 *            the f
	 * @param cn
	 *            the cn
	 * 
	 * @return the java type
	 */
	public static IType getJavaType(Item cadseDefinition, IFile f, String cn) {
		ICompilationUnit cu = null;
		IType javatype = null;
		if (f.exists()) {
			cu = (ICompilationUnit) JavaCore.create(f);
			if (cu != null) {
				javatype = cu.getType(cn);
			}
		}
		return javatype;
	}

	/**
	 * Gets the version.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the version
	 */
	public static int getVersion(Item cadseDefinition) {
		return Convert.toInt(cadseDefinition.getAttribute(CadseGCST.ITEM_at_TW_VERSION),
				CadseGCST.ITEM_at_TW_VERSION_, 0);
	}

	/**
	 * get a link 'configuration' from 'WorkspaceModel' to 'ConfigurationModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the configuration link
	 * 
	 * @generated
	 */
	static public Link getConfigurationLink(Item cadseDefinition) {
		return cadseDefinition.getOutgoingLink(CadseGCST.CADSE_DEFINITION_lt_CONFIGURATION);
	}

	/**
	 * get all link destination 'configuration' from 'WorkspaceModel' to
	 * 'ConfigurationModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the configuration all
	 * 
	 * @generated
	 */
	static public Item getConfigurationAll(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_CONFIGURATION, false);
	}

	/**
	 * get resolved link destination 'configuration' from 'WorkspaceModel' to
	 * 'ConfigurationModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the configuration
	 * 
	 * @generated
	 */
	static public Item getConfiguration(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_CONFIGURATION, true);
	}

	/**
	 * set a link 'configuration' from 'CadseDefinition' to
	 * 'ConfigurationModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setConfiguration(Item cadseDefinition, Item value) throws CadseException {
		cadseDefinition.setOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_CONFIGURATION,value);
	}

	/**
	 * get a link 'build' from 'WorkspaceModel' to 'BuildModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the builds the link
	 * 
	 * @generated
	 */
	static public Link getBuildLink(Item cadseDefinition) {
		return cadseDefinition.getOutgoingLink(CadseGCST.CADSE_DEFINITION_lt_BUILD);
	}

	/**
	 * get all link destination 'build' from 'WorkspaceModel' to 'BuildModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the builds the all
	 * 
	 * @generated
	 */
	static public Item getBuildAll(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_BUILD, false);
	}

	/**
	 * get resolved link destination 'build' from 'WorkspaceModel' to
	 * 'BuildModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the builds the
	 * 
	 * @generated
	 */
	static public Item getBuild(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_BUILD, true);
	}

	/**
	 * set a link 'build' from 'CadseDefinition' to 'BuildModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setBuild(Item cadseDefinition, Item value) throws CadseException {
		cadseDefinition.setOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_BUILD,value);
	}

	/**
	 * get a link 'mapping' from 'WorkspaceModel' to 'MappingModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the mapping link
	 * 
	 * @generated
	 */
	static public Link getMappingLink(Item cadseDefinition) {
		return cadseDefinition.getOutgoingLink(CadseGCST.CADSE_DEFINITION_lt_MAPPING);
	}

	/**
	 * get all link destination 'mapping' from 'WorkspaceModel' to
	 * 'MappingModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the mapping all
	 * 
	 * @generated
	 */
	static public Item getMappingAll(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_MAPPING, false);
	}

	/**
	 * get resolved link destination 'mapping' from 'WorkspaceModel' to
	 * 'MappingModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the mapping
	 * 
	 * @generated
	 */
	static public Item getMapping(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_MAPPING, true);
	}

	/**
	 * set a link 'mapping' from 'CadseDefinition' to 'MappingModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setMapping(Item cadseDefinition, Item value) throws CadseException {
		cadseDefinition.setOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_MAPPING,value);
	}

	/**
	 * get a link 'view-model' from 'WorkspaceModel' to 'ViewModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the view model link
	 * 
	 * @generated
	 */
	static public Link getViewModelLink(Item cadseDefinition) {
		return cadseDefinition.getOutgoingLink(CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL);
	}

	/**
	 * get all link destination 'view-model' from 'WorkspaceModel' to
	 * 'ViewModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the view model all
	 * 
	 * @generated
	 */
	static public Item getViewModelAll(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL, false);
	}

	/**
	 * get a link 'data-model' from 'WorkspaceModel' to 'DataModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the data model link
	 * 
	 * @generated
	 */
	static public Link getDataModelLink(Item cadseDefinition) {
		return cadseDefinition.getOutgoingLink(CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL);
	}

	/**
	 * get all link destination 'data-model' from 'WorkspaceModel' to
	 * 'DataModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the data model all
	 * 
	 * @generated
	 */
	static public Item getDataModelAll(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL, false);
	}

	/**
	 * get resolved link destination 'data-model' from 'WorkspaceModel' to
	 * 'DataModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * 
	 * @return the data model
	 * 
	 * @generated
	 */
	static public Item getDataModel(Item cadseDefinition) {
		return cadseDefinition.getOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL, true);
	}

	/**
	 * set a link 'data-model' from 'CadseDefinition' to 'DataModel'.
	 * 
	 * @param cadseDefinition
	 *            the cadse definition
	 * @param value
	 *            the value
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 * 
	 * @generated
	 */
	static public void setDataModel(Item cadseDefinition, Item value) throws CadseException {
		cadseDefinition.setOutgoingItem(CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL,value);
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @seefede.workspace.dependencies.eclipse.java.fix.IFixManager#
	 * findItemWithExportPackageWithType(java.lang.String, java.lang.String)
	 */
	public Item[] findItemWithExportPackageWithType(String qualifiedPackage, String typeDef) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.dependencies.eclipse.java.fix.IFixManager#findLT(fr.imag
	 * .adele.cadse.core.Item, fr.imag.adele.cadse.core.Item)
	 */
	public LinkType findLT(Item itemSource, Item destination) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.dependencies.eclipse.java.fix.IFixManager#resolve(org.
	 * eclipse.jdt.core.IJavaProject, fr.imag.adele.cadse.core.Item,
	 * java.lang.String, java.lang.String, boolean, java.util.List)
	 */
	public void resolve(IJavaProject sourceProject, Item itemSource, String qualifiedPackageName, String typeName,
			boolean addImport, List<IJavaCompletionProposal> ret) {
		List<String> imports = getImports(itemSource);
		if (imports != null && imports.contains(qualifiedPackageName)) {
			return; // allready
		}
		ret.add(new CadseDefinitionJavaCompletionProposal(itemSource, qualifiedPackageName, addImport));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#canCreateLink(fr.imag
	 * .adele.cadse.core.Item, fr.imag.adele.cadse.core.Item,
	 * fr.imag.adele.cadse.core.LinkType)
	 */
	@Override
	public String canCreateLink(Item source, Item dest, LinkType lt) {
		if (source.getQualifiedName().equals(CadseDomain.CADSE_ROOT_MODEL)) {
			return "Cannot extend this cadse : it's the root cadse";
		}

		if (lt == CadseGCST.CADSE_RUNTIME_lt_EXTENDS) {
			if (dest == source) {
				return "Cannot extends self";
			}

			List<Item> deps = getAllDependenciesCadse(dest);
			if (deps.contains(source)) {
				return "Cannot extend this cadse";
			}
		}
		return super.canCreateLink(source, dest, lt);
	}
	
	@Override
	public boolean hasImageByItem() {
		return false;
	}

}