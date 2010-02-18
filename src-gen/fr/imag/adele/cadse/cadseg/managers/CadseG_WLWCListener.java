/**
 *
 */
package fr.imag.adele.cadse.cadseg.managers;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.refactoring.IJavaRefactorings;
import org.eclipse.jdt.core.refactoring.descriptors.RenameJavaElementDescriptor;
import org.eclipse.jdt.ui.refactoring.RenameSupport;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fede.workspace.eclipse.java.manager.JavaProjectContentManager;
import fede.workspace.tool.eclipse.MappingManager;
import fr.imag.adele.cadse.cadseg.contents.CadseDefinitionContent;
import fr.imag.adele.cadse.cadseg.generate.GenerateJavaIdentifier;
import fr.imag.adele.cadse.cadseg.managers.actions.MenuAbstractManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkTypeManager;
import fr.imag.adele.cadse.cadseg.managers.build.ComposerLinkManager;
import fr.imag.adele.cadse.cadseg.managers.build.CompositeItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.DataModelManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.PageManager;
import fr.imag.adele.cadse.cadseg.managers.ui.DListManager;
import fr.imag.adele.cadse.cadseg.managers.ui.DisplayManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.core.content.ContentItem;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.transaction.delta.ItemDelta;
import fr.imag.adele.cadse.core.transaction.delta.LinkDelta;
import fr.imag.adele.cadse.core.transaction.delta.MappingOperation;
import fr.imag.adele.cadse.core.transaction.delta.SetAttributeOperation;
import fr.imag.adele.cadse.core.transaction.AbstractLogicalWorkspaceTransactionListener;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.util.Convert;
import fr.imag.adele.cadse.core.var.ContextVariable;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;
import fr.imag.adele.fede.workspace.si.view.View;
import org.eclipse.jdt.core.refactoring.IJavaRefactorings;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import org.eclipse.jdt.core.refactoring.IJavaRefactorings;

class RenameJavaClassMappingOperartion extends RenameMappingOperation {

	public RenameJavaClassMappingOperartion(ItemDelta parent, ContextVariable oldCxt, ContextVariable newCxt)
			throws CadseException {
		super(parent, oldCxt, newCxt, "rename class");
	}

	@Override
	public void commit(LogicalWorkspace wl, Item goodItem) {
		refactoringRename(IJavaRefactorings.RENAME_COMPILATION_UNIT);
	}

	@Override
	protected IJavaElement getJavaElement(ContextVariable context) {
		JavaFileContentManager cm = (JavaFileContentManager) ((ItemDelta) _parent).getBaseItem().getContentItem();
		return cm.getCompilationUnit(context);
	}
}

class RenameJavaClassRefMappingOperartion extends RenameMappingOperation {
	IJavaElement	_oldE;
	IJavaElement	_newE;

	public RenameJavaClassRefMappingOperartion(ItemDelta parent, ContextVariable oldCxt, ContextVariable newCxt,
			IFile oldF, IFile newF) throws CadseException {
		super(parent, oldCxt, newCxt, "rename class");
		_oldE = JavaCore.create(oldF);
		if (_oldE == null) {
			throw new NullPointerException("Cannot find java element form file " + oldF);
		}
		_newE = JavaCore.create(newF);
		if (_newE == null) {
			throw new NullPointerException("Cannot find java element form file " + newF);
		}
	}

	@Override
	public void commit(LogicalWorkspace wl, Item goodItem) {
		refactoringRename(IJavaRefactorings.RENAME_COMPILATION_UNIT);
	}

	@Override
	protected IJavaElement getJavaElement(ContextVariable context) {
		return (context == _newcontext) ? _newE : _oldE;
	}
}

abstract class RenameMappingOperation extends MappingOperation {
	final protected ContextVariable	_newcontext;
	final protected ContextVariable	_oldcontext;
	final protected String			_desc;

	public RenameMappingOperation(ItemDelta parent, ContextVariable oldCxt, ContextVariable newCxt, String desc)
			throws CadseException {
		super(parent);
		_oldcontext = oldCxt;
		_newcontext = newCxt;
		_desc = desc;
	}

	protected void refactoringRename(String id, String... params) {
		try {
			IJavaElement oldElement = getJavaElement(_oldcontext);
			IJavaElement newElement = getJavaElement(_newcontext);

			Map<String, String> arguments = new HashMap<String, String>();
			for (int i = 0; i < params.length;) {
				String k = params[i++];
				String v = params[i++];
				arguments.put(k, v);
			}
			arguments.put("name", newElement.getElementName());
			arguments.put("input", oldElement.getHandleIdentifier());

			RenameJavaElementDescriptor javaElementDescriptor = new RenameJavaElementDescriptor(id,
					id == IJavaRefactorings.RENAME_JAVA_PROJECT ? null : oldElement.getResource().getProject()
							.getName(), _desc, _desc, arguments, 0);
			javaElementDescriptor.setJavaElement(oldElement);
			javaElementDescriptor.setNewName(newElement.getElementName());

			int type = oldElement.getElementType();
			switch (type) {
				case IJavaElement.PACKAGE_FRAGMENT:
				case IJavaElement.TYPE:
					javaElementDescriptor.setUpdateQualifiedNames(true);
					break;
				default:
			}
			if (oldElement.getElementType() != IJavaElement.PACKAGE_FRAGMENT_ROOT) {
				javaElementDescriptor.setUpdateReferences(true);
			}

			RenameSupport renameSupport = RenameSupport.create(javaElementDescriptor);
			Shell parent = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			renameSupport.perform(parent, new ProgressMonitorDialog(parent));

		} catch (CoreException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected abstract IJavaElement getJavaElement(ContextVariable context);

	@Override
	protected String getLabel() {
		return _desc + " " + getJavaElement(_oldcontext).getElementName() + " to "
				+ getJavaElement(_newcontext).getElementName();
	}
}

class RenamePackageMappingOperartion extends RenameMappingOperation {
	boolean	_renameSubpackages	= true;

	public RenamePackageMappingOperartion(ItemDelta parent, ContextVariable oldCxt, ContextVariable newCxt)
			throws CadseException {
		super(parent, oldCxt, newCxt, "rename all package");
	}

	@Override
	public void commit(LogicalWorkspace wl, Item goodItem) {
		refactoringRename(IJavaRefactorings.RENAME_PACKAGE, "hierarchical", Boolean.toString(_renameSubpackages));
	}

	private String getPackageName(ContextVariable cxt) {
		return cxt.getAttribute((Item) _parent, CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_);
	}

	@Override
	protected IJavaElement getJavaElement(ContextVariable context) {
		JavaProjectContentManager cm = (JavaProjectContentManager) ((ItemDelta) _parent).getBaseItem().getContentItem();
		return cm.createPackage(context, getPackageName(context));
	}

}

class RenameCadseDefinitionMappingOperartion extends RenameMappingOperation {

	public RenameCadseDefinitionMappingOperartion(ItemDelta parent, ContextVariable oldCxt, ContextVariable newCxt)
			throws CadseException {
		super(parent, oldCxt, newCxt, "rename project");
	}

	@Override
	public void commit(LogicalWorkspace wl, Item goodItem) {
		refactoringRename(IJavaRefactorings.RENAME_JAVA_PROJECT);
	}

	@Override
	protected IJavaElement getJavaElement(ContextVariable context) {
		JavaProjectContentManager cm = (JavaProjectContentManager) ((ItemDelta) _parent).getBaseItem().getContentItem();
		return cm.getJavaProject(context);
	}
}

public final class CadseG_WLWCListener extends AbstractLogicalWorkspaceTransactionListener {

	private final class CustomManagerOperation extends MappingOperation {
		private CustomManagerOperation(ItemDelta parent) throws CadseException {
			super(parent);
		}

		@Override
		protected String getLabel() {
			return "custom manager";
		}

		@Override
		public void commit(LogicalWorkspace wl, Item theItemType) {
			ItemType it = (ItemType) theItemType;
			Item cm = ItemTypeManager.getCadseDefinition(theItemType);
			CadseDefinitionContent contentcm = (CadseDefinitionContent) cm.getContentItem();
			String qClass = it.getItemManagerClass();
			IFile f = contentcm.getCustomJavaSourceElementContainer(wl.getContext()).getFile(
					new Path(qClass.replace('.', '/') + ".java"));
			if (!f.exists()) {
				String superQClass = GenerateJavaIdentifier.getQualifiedManager(wl.getContext(), it, ItemTypeManager
						.getManager(it), false);
				String superCN = JavaIdentifier.getlastclassName(superQClass);
				String superPN = JavaIdentifier.getPackageName(superQClass);

				String cn = JavaIdentifier.getlastclassName(qClass);
				String pn = JavaIdentifier.getPackageName(qClass);
				try {
					StringBuilder sb = new StringBuilder();
					sb.append("package ").append(pn).append(";\n");
					sb.append("\n");
					if (!superPN.equals(pn))
						sb.append("import ").append(superQClass).append(";\n\n");
					sb.append("public class ").append(cn).append(" extends ").append(superCN).append(" {\n");
					sb.append("\n\n}\n");

					String str = sb.toString();
					MappingManager.createContainer(f.getParent(), View.getDefaultMonitor());
					f.create(new ByteArrayInputStream(str.getBytes(f.getProject().getDefaultCharset())), true, View
							.getDefaultMonitor());
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

//	private static final String	CREATION_PAGE_TITLE_PREFIX	= "Create ";
//	private static final String	MODIFICATION_PAGE_PREFIX	= "modification-page-";
//	private static final String	CREATION_PAGE_PREFIX		= "creation-page-";
//	// private static final String PARENT_NAME = "#invert_part_";
//
//	private static final String	EMPTY_PAGE_CREATION_ID		= "creation-page1";
//	private static final String	EMPTY_PAGE_MODIFICATION_ID	= "modification-page1";
	private static Logger		_logger						= Logger
																	.getLogger("fr.imag.adele.cadse.cadseg.CadseG_WLWCListener");

	public CadseG_WLWCListener() {
		CadseGCST.TYPE_DEFINITION.addLogicalWorkspaceTransactionListener(this);
		CadseGCST.DATA_MODEL.addLogicalWorkspaceTransactionListener(this);
		CadseGCST.PAGE.addLogicalWorkspaceTransactionListener(this);
		CadseGCST.MANAGER.addLogicalWorkspaceTransactionListener(this);
		CadseGCST.CADSE_DEFINITION.addLogicalWorkspaceTransactionListener(this);
		CadseGCST.ATTRIBUTE.addLogicalWorkspaceTransactionListener(this);
		CadseGCST.FIELD.addLogicalWorkspaceTransactionListener(this);
		CadseGCST.MENU_ACTION.addLogicalWorkspaceTransactionListener(this);
		CadseGCST.CONTENT_ITEM_TYPE.addLogicalWorkspaceTransactionListener(this);
	}

	@Override
	public void notifyCreatedItem(LogicalWorkspaceTransaction wc, ItemDelta item) throws CadseException {
		if (!isInCadseDefinition(item))
			return;
		if (item.isInstanceOf(CadseGCST.CONTENT_ITEM_TYPE)) {
			Item linkType = item.getIncomingItem(CadseGCST.CONTENT_LINK_TYPE_lt_CONTENT_DEFINITION);
			if (linkType == null) {
				Item itemType = item.getPartParent();
				if (itemType.isInstanceOf(CadseGCST.MANAGER)) {
					itemType = ManagerManager.getItemType(itemType);
				}
				linkType = wc.createItem(CadseGCST.CONTENT_LINK_TYPE, itemType , CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES);
				linkType.setName("contents");
			}
			LinkTypeManager.setDestination(linkType, CadseGCST.CONTENT_ITEM);
			LinkTypeManager.setMaxAttribute(linkType, 1);
			LinkTypeManager.setMinAttribute(linkType, 0);
			AttributeManager.setHiddenInComputedPagesAttribute(linkType, true);
			linkType.createLink(CadseGCST.CONTENT_LINK_TYPE_lt_CONTENT_DEFINITION, item);
		}
		
		/*
		 * Create dialog item and first page
		 */
		if (item.isInstanceOf(CadseGCST.TYPE_DEFINITION)) {
		//	createDialogAndPages(wc, item);
		}

		if (item.isInstanceOf(CadseGCST.EXT_ITEM_TYPE)) {
			if (item.getOutgoingLink(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE) == null)
				item.setOutgoingItem(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, CadseGCST.EXT_ITEM);
		}

		/* the attribute package name */
		if (item.isInstanceOf(CadseGCST.ITEM_TYPE)) {
			Item parent = item.getPartParent();
			if (parent != null && parent.isInstanceOf(CadseGCST.DATA_MODEL)) {
				item.setAttribute(CadseGCST.ITEM_TYPE_at_PACKAGE_NAME_, DataModelManager.getQualifiedDM(parent));
			}

			Item cr = null;
			if (parent != null) {
				if (parent.isInstanceOf(CadseGCST.CADSE))
					cr = parent;
				else
					cr = parent.getPartParent(CadseGCST.CADSE);
				if (cr != null)
					item.createLink(CadseGCST.TYPE_DEFINITION_lt_CADSE, cr);
			}

			if (item.getOutgoingLink(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE) == null)
				item.setOutgoingItem(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, CadseGCST.ITEM);

			if (cr.isInstanceOf(CadseGCST.CADSE_DEFINITION) && item.getType() != CadseGCST.EXT_ITEM_TYPE) { // manager
				// seul les items des Cadse Definition(cadseg) et non pas des
				// extention peuvent avoir des manager
				try {
					// / TODO Test remove this line
					// /ItemTypeManager.setIsAbstractAttribute(item, false);

					Item mappingModel = CadseDefinitionManager.getMappingModel(cr);

					Item managerItem = wc.createItem(CadseGCST.MANAGER, mappingModel,
							CadseGCST.MAPPING_MODEL_lt_MANAGERS);

					// ManagerManager.setManagerType(managerItem, "default");
					ManagerManager.setHumanNameAttribute(managerItem, item.getName());
					ManagerManager.setUniqueNameTemplate(managerItem, "${#parent.qualified-name}{.}${#name}");
					ManagerManager.setDisplayNameTemplateAttribute(managerItem, "${#name}");
					//
					// create a link form manager to theitemtype
					ManagerManager.setItemType(managerItem, item);

				} catch (CadseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		/*
		 * The manager is created in the dialog action. Not here.
		 */
		// manager in ItemType creation page
		//
		/*
		 * A CADSE definition is been created. Create items data model,
		 * configuration model, build model, view model, mapping model.
		 */
		if (item.isInstanceOf(CadseGCST.CADSE_DEFINITION)) {
			createdCadseDefintionItem(wc, item);
		}

//		/*
//		 * Create a field from an attribute
//		 */
//		if (item.getType() == CadseGCST.ATTRIBUTE) {
//			final ItemDelta itemType = item.getPartParent();
//			syncFieldFromAttribute(wc, itemType, item, false);
//		}

		if (item.getType() == CadseGCST.INTEGER) {
			item.setAttribute(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "0");
		}

		if (item.getType() == CadseGCST.LINK_TYPE) {
			Item parent = item.getPartParent();
			if (parent != null) {
				item.createLink(CadseGCST.LINK_TYPE_lt_SOURCE, parent);
			}
		}

	}

//	/**
//	 * An item of type TYPE_DEFINITION is been created. You must create a
//	 * creation dialog, a modification dialog, a first creation dialog page and
//	 * a first modification dialog page...
//	 */
//	private void createDialogAndPages(LogicalWorkspaceTransaction wc, ItemDelta item) throws CadseException {
//
//		ItemDelta itemType = item;
//
//		ItemDelta itemCreationDialog = createCreationDialog(wc, itemType);
//
//		ItemDelta itemModificationDialog = createModificationDialog(wc, itemType);
//
//		createFirstCreationPage(wc, itemCreationDialog);
//
//		createFirstModificationPage(wc, itemModificationDialog, true);
//
//	}

//	/**
//	 * Create a modification dialog
//	 * 
//	 * @param wc
//	 *            the working copy where create it
//	 * @param itemType
//	 *            the parent of the new modification dialog
//	 * @return the new modification dialog
//	 * @throws CadseException
//	 */
//	private ItemDelta createModificationDialog(LogicalWorkspaceTransaction wc, Item itemType) throws CadseException {
//		ItemDelta itemModificationDialog = wc.createItemIfNeed(null, ModificationDialogManager.DEFAULT_SHORT_NAME,
//				CadseGCST.MODIFICATION_DIALOG, itemType, CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_DIALOG);
//		return itemModificationDialog;
//	}
//
//	/**
//	 * Create a creation dialog
//	 * 
//	 * @param wc
//	 *            the working copy where create it
//	 * @param itemType
//	 *            the parent of the new creation dialog
//	 * @return the new creation dialog
//	 * @throws CadseException
//	 */
//	private ItemDelta createCreationDialog(LogicalWorkspaceTransaction wc, Item itemType) throws CadseException {
//		ItemDelta itemCreationDialog = wc.createItemIfNeed(null, CreationDialogManager.DEFAULT_SHORT_NAME,
//				CadseGCST.CREATION_DIALOG, itemType, CadseGCST.TYPE_DEFINITION_lt_CREATION_DIALOG);
//		return itemCreationDialog;
//	}

	/**
	 * A CADSE definition is been created. Create items data model,
	 * configuration model, build model, view model, mapping model.
	 * 
	 * @param wc
	 *            the current working copy
	 * @param cadseDefinition
	 *            the new cadse definition, the parent of new items
	 * @throws CadseException
	 */
	private void createdCadseDefintionItem(LogicalWorkspaceTransaction wc, ItemDelta cadseDefinition)
			throws CadseException {
		wc.createItemIfNeed(null, CadseDefinitionManager.DATA_MODEL, CadseGCST.DATA_MODEL, cadseDefinition,
				CadseGCST.CADSE_DEFINITION_lt_DATA_MODEL);
		// june 09, 2009 removed configuration model
		// wc.createItemIfNeed(null, CadseDefinitionManager.CONFIGURATION_MODEL,
		// CadseGCST.CONFIGURATION_MODEL,
		// cadseDefinition, CadseGCST.CADSE_DEFINITION_lt_CONFIGURATION);
		wc.createItemIfNeed(null, CadseDefinitionManager.BUILD_MODEL, CadseGCST.BUILD_MODEL, cadseDefinition,
				CadseGCST.CADSE_DEFINITION_lt_BUILD);
		wc.createItemIfNeed(null, CadseDefinitionManager.VIEW_MODEL, CadseGCST.VIEW_MODEL, cadseDefinition,
				CadseGCST.CADSE_DEFINITION_lt_VIEW_MODEL);
		wc.createItemIfNeed(null, CadseDefinitionManager.MAPPING, CadseGCST.MAPPING_MODEL, cadseDefinition,
				CadseGCST.CADSE_DEFINITION_lt_MAPPING);
	}
//
//	/**
//	 * Create a first creation page
//	 * 
//	 * @param wc
//	 *            the current working copy
//	 * @param itemCreationDialog
//	 *            the creation dialog, the parent of the page which create
//	 * @throws CadseException
//	 */
//	private ItemDelta createFirstCreationPage(LogicalWorkspaceTransaction wc, Item itemCreationDialog)
//			throws CadseException {
//		Item abstype = itemCreationDialog.getPartParent();
//		String name = CREATION_PAGE_PREFIX + abstype.getName();
//
//		return wc.createItemIfNeed(null, name, CadseGCST.PAGE, itemCreationDialog, CadseGCST.TYPE_DEFINITION_lt_CREATION_PAGES,
//				CadseGCST.PAGE_at_TITLE,
//				CREATION_PAGE_TITLE_PREFIX + abstype.getName(), CadseGCST.PAGE_at_IS_REMOVED_, false,
//				CadseGCST.PAGE_at_DESCRIPTION_, "");
//	}

//	/**
//	 * Create a first modification page
//	 * 
//	 * @param wc
//	 *            the current working copy
//	 * @param modificationDialog
//	 *            the modification dialog, the parent of the page which create
//	 * @throws CadseException
//	 */
//	private ItemDelta createFirstModificationPage(LogicalWorkspaceTransaction wc, Item modificationDialog,
//			boolean doCreatefield) throws CadseException {
//		Item abstype = modificationDialog.getPartParent();
//
//		String name = MODIFICATION_PAGE_PREFIX + abstype.getName();
//
//		ItemDelta modificationPage1Item = wc.createItemIfNeed(null, name, CadseGCST.PAGE, modificationDialog,
//				CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES, CadseGCST.PAGE_at_TITLE_, abstype.getName(),
//				CadseGCST.PAGE_at_LABEL_, abstype.getName(), CadseGCST.PAGE_at_IS_REMOVED_, false,
//				CadseGCST.PAGE_at_DESCRIPTION_, "");
//
//		if (doCreatefield) {
//			Item[] attributes = ItemTypeManager.getAllAttributes(null, abstype, null, true);
//			for (Item attribute : attributes) {
//				Item itemType = PageManager.getItemTypeFromPage(modificationPage1Item);
//				doCreateField(wc, itemType, attribute, modificationPage1Item);
//			}
//		}
//
//		return modificationPage1Item;
//	}

	/**
	 * The value of attribute definition {@link CadseGCST#ITEM_TYPE_at_NAME_} is
	 * changed. if the type of the item where the value is changed is
	 * CadseGCST.EXT_ITEM_TYPE, you must change the name of first modification
	 * page and first creation page.
	 */
	@Override
	public void notifyChangeAttribute(LogicalWorkspaceTransaction wc, ItemDelta item, SetAttributeOperation attOperation)
			throws CadseException {

		if (!isInCadseDefinition(item))
			return;
		
		if (attOperation.getAttributeDefinition() == CadseGCST.ITEM_TYPE_at_CUSTOM_MANAGER_) {
			try {
				item.setAttribute(CadseGCST.ITEM_TYPE_at_MANAGER_CLASS_, GenerateJavaIdentifier.getQualifiedManager(wc
						.getNewContext(), item.getAdapter(ItemType.class), ItemTypeManager.getManager(item),
						attOperation.getCurrentValue() == Boolean.TRUE));
			} catch (Throwable e1) {
			}
			if (attOperation.getCurrentValue() == Boolean.TRUE) {
				item.addMappingOperaion(new CustomManagerOperation(item));
			}
		}
		if (!item.isAdded() && item.getType() == CadseGCST.CADSE_DEFINITION
				&& attOperation.getAttributeDefinition() == CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_) {
			ContextVariable oldContext = wc.getOldContext();
			ContextVariable newContext = wc.getNewContext();

			IFile oldCst = CadseDefinitionManager.getCSTCU(oldContext, item);
			IFile newCst = CadseDefinitionManager.getCSTCU(oldContext, item);
			if (!(oldCst.equals(newCst))) {
				try {
					addRenameJavaClassRefMappingOperartion(item, oldContext, newContext, oldCst, newCst);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
			addRenamePackageOperation(item, oldContext, newContext);
		}

		if (item.getType() == CadseGCST.CADSE_DEFINITION
				&& attOperation.getAttributeDefinition() == CadseGCST.ITEM_at_NAME_) {
			if (item.isAdded()) {
				if (((String) attOperation.getCurrentValue()).contains(".")) {
					item.setQualifiedName((String) attOperation.getCurrentValue());
				} else {
					item.setQualifiedName(CadseRuntime.CADSE_NAME_SUFFIX + attOperation.getCurrentValue());
				}
			} else {
				String name = item.getAttribute(CadseGCST.ITEM_at_QUALIFIED_NAME_);

				if (name.startsWith(CadseRuntime.CADSE_NAME_SUFFIX)) {
					item.setQualifiedName(CadseRuntime.CADSE_NAME_SUFFIX + attOperation.getCurrentValue());
				}
			}
		}

		if (item.getType() == CadseGCST.CADSE_DEFINITION
				&& attOperation.getAttributeDefinition() == CadseGCST.ITEM_at_QUALIFIED_NAME_) {
			ContextVariable oldContext = wc.getOldContext();
			ContextVariable newContext = wc.getNewContext();
			addRenameCadseDefinitionMappingOperartion(item, oldContext, newContext);
		}

		if (item.isInstanceOf(CadseGCST.TYPE_DEFINITION)
				&& attOperation.getAttributeDefinition() == CadseGCST.ITEM_at_NAME_) {
			syncCompositeType(wc, null, item, null);
			//syncPages(wc, item, attOperation);
		}

		if (item.isInstanceOf(CadseGCST.ITEM_TYPE) && attOperation.getAttributeDefinition() == CadseGCST.ITEM_at_NAME_) {

			ItemDelta manager = (ItemDelta) ManagerManager.getManagerFromItemType(item);
			if (manager != null) {
				manager.setName(attOperation.getCurrentValue() + "-manager");
				String oldHn = manager.getAttribute(CadseGCST.MANAGER_at_HUMAN_NAME_);
				if (oldHn == null || oldHn.equals(attOperation.getPrecCurrentValue()))
					ManagerManager.setHumanNameAttribute(manager, item.getName());
			}
			ContextVariable oldContext = wc.getOldContext();
			ContextVariable newContext = wc.getNewContext();
			addRenameOperation(manager, oldContext, newContext);
		}

		if (item.getType() == CadseGCST.PAGE & attOperation.getAttributeDefinition() == CadseGCST.ITEM_at_NAME_) {
			ContextVariable oldContext = wc.getOldContext();
			ContextVariable newContext = wc.getNewContext();
			addRenameOperation(item, oldContext, newContext);
		}

//		if (attOperation.getAttributeDefinition() == CadseGCST.CREATION_DIALOG_at_DEFAULT_SHORT_NAME_) {
//			if (attOperation.getCurrentValue() == null || ((String) attOperation.getCurrentValue()).isEmpty()) {
//				ItemDelta itemType = item.getPartParent();
//				if (itemType.exists())
//					syncFieldName(wc, getFirstCreationPage(wc, itemType, true));
//			}
//		}
//
//		if (attOperation.getAttributeDefinition() == CadseGCST.CREATION_DIALOG_at_AUTOMATIC_SHORT_NAME_) {
//			if (attOperation.getCurrentValue() == Boolean.FALSE) {
//				ItemDelta itemType = item.getPartParent();
//				if (itemType.exists())
//					syncFieldName(wc, getFirstCreationPage(wc, itemType, true));
//			}
//		}
		// if (item.isInstanceOf(CadseGCST.MANAGER)
		// && attOperation.getAttributeDefinition() ==
		// CadseGCST.ITEM_at_NAME_) {
		// ItemDelta manager = item;
		// if (manager != null && !manager.isAdded()) {
		// ContextVariable oldContext = wc.getOldContext();
		// ContextVariable newContext = wc.getNewContext();
		//
		// final RenameJavaClassMappingOperartion renameOper = new
		// RenameJavaClassMappingOperartion(manager);
		// renameOper.setCn(GenerateJavaIdentifier.getManagerClassName(newContext,
		// manager));
		// renameOper.setOldcn(GenerateJavaIdentifier.getManagerPackage(oldContext,
		// manager));
		// manager.addMappingOperaion(renameOper);
		// // manager.getContentItem().migrateContentItem(manager,
		// // oldContext, newContext);
		// // (attOperation.getCurrentValue() + "-manager");
		// /*
		// * it --> listener
		// *
		// * item -> modified item
		// *
		// * modified-item.setAttribute(item.getShortName()+"-manager")
		// */
		//
		// // /*
		// // * (non-Javadoc)
		// // *
		// // * @see
		// //
		// fede.workspace.model.manager.DefaultItemManager#computeRenameAnnotationChange(org.eclipse.ltk.core.refactoring.CompositeChange,
		// // * fr.imag.adele.cadse.core.Item,
		// // fr.imag.adele.cadse.core.Item,
		// // * fr.imag.adele.cadse.core.var.ContextVariable,
		// // * fr.imag.adele.cadse.core.var.ContextVariable)
		// // */
		// // @Override
		// // public RefactoringStatus
		// // computeRenameAnnotationChange(CompositeChange
		// // change, Item itemAnnotation,
		// // Item itemAnnoted, ContextVariable newCxt, ContextVariable
		// // oldCxt) {
		// // if (itemAnnoted.getType() == CadseGCST.ITEM_TYPE) {
		// // return itemAnnotation.computeRenameChange(change,
		// // itemAnnoted.getShortName() + "-manager", newCxt, oldCxt);
		// // }
		// // return null;
		// // }
		// }
		// }

		if (item.getType() == CadseGCST.ITEM_TYPE && attOperation.getAttributeDefinition() == CadseGCST.ITEM_at_NAME_) {
			// Display name

			Item manager = ManagerManager.getManagerFromItemType(item);
			if (manager != null) {
				if (ManagerManager.getHumanNameAttribute(manager).equals(attOperation.getPrecCurrentValue())) {
					ManagerManager.setHumanNameAttribute(manager, (String) attOperation.getCurrentValue());
				}
			}

		}

//		if (item.isInstanceOf(CadseGCST.ATTRIBUTE)) {
//			ItemDelta itemType = item.getPartParent();
//			if (itemType.exists()) {
//				if (attOperation.getAttributeDefinition() == CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_) {
//					syncFieldFromAttribute(wc, itemType, item, false);
//				}
//				if (attOperation.getAttributeDefinition() == CadseGCST.ITEM_at_NAME_) {
//					syncFieldFromAttribute(wc, itemType, item, false);
//				}
//			}
////			if (attOperation.getAttributeDefinition() == CadseGCST.ATTRIBUTE_at_IS_LIST_) {
////				syncDisplayFromAttribute(wc, item);
////			}
////
////			if (attOperation.getAttributeDefinition() == CadseGCST.LINK_at_MAX_) {
////				syncDisplayFromAttribute(wc, item);
////			}
//
//		}

		if (item.isInstanceOf(CadseGCST.LINK_TYPE)) {
			if (attOperation.getAttributeDefinition() == CadseGCST.LINK_TYPE_at_COMPOSITION_) {
				syncCompositeType(wc, item, null, attOperation);
			}
		}

		if (item.isInstanceOf(CadseGCST.MENU_ACTION)
				&& attOperation.getAttributeDefinition() == CadseGCST.ITEM_at_NAME_) {
			if (Convert.equals(attOperation.getPrecCurrentValue(), MenuAbstractManager.getLabelAttribute(item))) {
				MenuAbstractManager.setLabelAttribute(item, (String) attOperation.getCurrentValue());
			}

		}
	}

	private boolean isInCadseDefinition(ItemDelta item) {
		return item.isInstanceOf(CadseGCST.CADSE_DEFINITION) || item.getPartParent(CadseGCST.CADSE_DEFINITION) != null;
	}

	private void addRenameOperation(ItemDelta anItemDelta, ContextVariable oldContext, ContextVariable newContext)
			throws CadseException {
		if (anItemDelta == null || anItemDelta.isAdded()) {
			return;
		}

		RenameJavaClassMappingOperartion renameOper = anItemDelta
				.getMappingOperation(RenameJavaClassMappingOperartion.class);
		if (renameOper == null) {
			renameOper = new RenameJavaClassMappingOperartion(anItemDelta, oldContext, newContext);
		}
	}

	private void addRenamePackageOperation(ItemDelta anItemDelta, ContextVariable oldContext, ContextVariable newContext)
			throws CadseException {
		if (anItemDelta == null || anItemDelta.isAdded()) {
			return;
		}

		RenamePackageMappingOperartion renameOper = anItemDelta
				.getMappingOperation(RenamePackageMappingOperartion.class);
		if (renameOper == null) {
			renameOper = new RenamePackageMappingOperartion(anItemDelta, oldContext, newContext);
		}
	}

	private void addRenameCadseDefinitionMappingOperartion(ItemDelta anItemDelta, ContextVariable oldContext,
			ContextVariable newContext) throws CadseException {
		if (anItemDelta == null || anItemDelta.isAdded()) {
			return;
		}

		RenameCadseDefinitionMappingOperartion renameOper = anItemDelta
				.getMappingOperation(RenameCadseDefinitionMappingOperartion.class);
		if (renameOper == null) {
			renameOper = new RenameCadseDefinitionMappingOperartion(anItemDelta, oldContext, newContext);
		}
	}

	private void addRenameJavaClassRefMappingOperartion(ItemDelta anItemDelta, ContextVariable oldContext,
			ContextVariable newContext, IFile oldF, IFile newF) throws CadseException {
		if (anItemDelta == null || anItemDelta.isAdded()) {
			return;
		}

		RenameJavaClassRefMappingOperartion renameOper = anItemDelta
				.getMappingOperation(RenameJavaClassRefMappingOperartion.class);
		if (renameOper == null) {
			renameOper = new RenameJavaClassRefMappingOperartion(anItemDelta, oldContext, newContext, oldF, newF);
		}
	}

//	/**
//	 * 
//	 * @param item
//	 *            an itemtype or an extension
//	 * @param attOperation
//	 *            an attribute opertion sur un name
//	 * @throws CadseException
//	 */
//	private void syncPages(LogicalWorkspaceTransaction wc, ItemDelta item, SetAttributeOperation attOperation)
//			throws CadseException {
//		// change id of the first creation page if added
//		ItemDelta itemCreationDialog = item.getOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_CREATION_DIALOG, false);
//		ItemDelta itemFirstCreationPage = itemCreationDialog.getOutgoingItem(CadseGCST.CREATION_DIALOG_lt_PAGES, false);
//		if (itemFirstCreationPage.isAdded()) {
//			itemFirstCreationPage.setName(CREATION_PAGE_PREFIX + attOperation.getCurrentValue());
//			itemFirstCreationPage.setAttribute(CadseGCST.PAGE_at_TITLE_, CREATION_PAGE_TITLE_PREFIX
//					+ attOperation.getCurrentValue());
//			itemFirstCreationPage.setAttribute(CadseGCST.PAGE_at_LABEL_, CREATION_PAGE_TITLE_PREFIX
//					+ attOperation.getCurrentValue());
//			syncFieldName(wc, itemFirstCreationPage);
//		}
//
//		// change id of the first modification page if added
//		ItemDelta itemModificationDialog = item.getOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_DIALOG,
//				false);
//		ItemDelta itemFirstModificationPage = itemModificationDialog.getOutgoingItem(
//				CadseGCST.MODIFICATION_DIALOG_lt_PAGES, false);
//		if (itemFirstModificationPage != null && itemFirstModificationPage.isAdded()) {
//			itemFirstModificationPage.setName(MODIFICATION_PAGE_PREFIX + attOperation.getCurrentValue());
//			itemFirstModificationPage.setAttribute(CadseGCST.PAGE_at_TITLE_, attOperation.getCurrentValue());
//			itemFirstModificationPage.setAttribute(CadseGCST.PAGE_at_LABEL_, attOperation.getCurrentValue());
//
//			syncFieldName(wc, itemFirstCreationPage);
//		}
//
//		// rename pages
//		if (!item.isAdded()) {
//			String oldName = (String) attOperation.getPrecCurrentValue();
//			for (Item p : CreationDialogManager.getPages(itemCreationDialog)) {
//				ItemDelta pageDelta = (ItemDelta) p;
//				if (pageDelta.isAdded()) {
//					continue;
//				}
//				String namePage = pageDelta.getName();
//				if (namePage.equals(CREATION_PAGE_PREFIX + oldName)) {
//					pageDelta.setName(CREATION_PAGE_PREFIX + attOperation.getCurrentValue());
//				}
//
//				// rename the title attribute of page
//				String pageTitle = PageManager.getTitle(pageDelta);
//				if (pageTitle.equals(CREATION_PAGE_TITLE_PREFIX + oldName)) {
//					pageDelta.setAttribute(CadseGCST.PAGE_at_TITLE_, CREATION_PAGE_TITLE_PREFIX
//							+ attOperation.getCurrentValue());
//					pageDelta.setAttribute(CadseGCST.PAGE_at_LABEL_, CREATION_PAGE_TITLE_PREFIX
//							+ attOperation.getCurrentValue());
//				}
//			}
//			for (Item p : ModificationDialogManager.getPages(itemModificationDialog)) {
//				ItemDelta pageDelta = (ItemDelta) p;
//				if (pageDelta.isAdded()) {
//					continue;
//				}
//				String namePage = pageDelta.getName();
//				if (namePage.equals(MODIFICATION_PAGE_PREFIX + oldName)) {
//					pageDelta.setName(MODIFICATION_PAGE_PREFIX + attOperation.getCurrentValue());
//				}
//
//				// rename the title attribute of page
//				String pageTitle = PageManager.getTitle(pageDelta);
//				if (pageTitle.equals(oldName)) {
//					pageDelta.setAttribute(CadseGCST.PAGE_at_TITLE_, attOperation.getCurrentValue());
//					pageDelta.setAttribute(CadseGCST.PAGE_at_LABEL_, attOperation.getCurrentValue());
//				}
//			}
//		}
//	}

//	private void syncDisplayFromAttribute(LogicalWorkspaceTransaction wc, ItemDelta item) throws CadseException {
//		Collection<Item> fields = item.getIncomingItems(CadseGCST.FIELD_lt_ATTRIBUTE);
//		for (Item f : fields) {
//			try {
//				syncDisplay(wc, f);
//			} catch (RuntimeException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

	/**
	 * Delete the manager associated at an item type
	 */
	@Override
	public void notifyDeletedItem(LogicalWorkspaceTransaction wc, ItemDelta item) throws CadseException {
		if (!isInCadseDefinition(item))
			return;
		
		if (item.isInstanceOf(CadseGCST.CONTENT_ITEM_TYPE)) {
			Item linkType = item.getIncomingItem(CadseGCST.CONTENT_LINK_TYPE_lt_CONTENT_DEFINITION);
			if (linkType != null) {
				linkType.delete(false);
			}
		}
		
		if (item.isInstanceOf(CadseGCST.TYPE_DEFINITION)) {
			ItemDelta manager = (ItemDelta) ManagerManager.getManagerFromItemType(item);
			if (manager != null && !manager.isDeleted()) {
				manager.delete(item.getDeleteOperation(), 0);
			}

			// delete link which point to this
			Collection<Item> links = item.getIncomingItems(CadseGCST.LINK_TYPE_lt_DESTINATION);
			for (Item linkTothis : links) {
				((ItemDelta) linkTothis).delete(item.getDeleteOperation(), 0);
			}
		}

		if (item.getType() == CadseGCST.LINK_TYPE) {
			Item composerlink = ComposerLinkManager.getComposerLinkFromLink(item);
			if (composerlink != null) {
				try {
					composerlink.delete(true);
				} catch (CadseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			syncCompositeType(wc, item, null, null);

			if (LinkTypeManager.isPart(item)) {
				Item inverseLink = LinkTypeManager.getInverseLink(item);
				if (inverseLink != null) {
					inverseLink.delete(true);
				}
			}
		}

//		if (item.isInstanceOf(CadseGCST.ATTRIBUTE)) {
//			ItemDelta partParent = item.getPartParent(false, true);
//			if (partParent != null)
//				syncFieldFromAttribute(wc, partParent, item, true);
//		}
	}

	/**
	 * manage double click
	 */
	@Override
	public void notifyDoubleClick(LogicalWorkspaceTransaction wc, ItemDelta item) {
		if (item.isInstanceOf(CadseGCST.TYPE_DEFINITION)) {
			Item manager = ManagerManager.getManagerFromItemType(item);
			if (manager != null) {
				IFile jf = manager.getContentItem().getMainMappingContent(IFile.class);
				openFile(jf);
			}
		}
		if (item.isInstanceOf(CadseGCST.PAGE)) {
			IFile jf = item.getContentItem().getMainMappingContent(IFile.class);
			openFile(jf);
		}
		if (item.isInstanceOf(CadseGCST.MANAGER)) {
			IFile jf = item.getContentItem().getMainMappingContent(IFile.class);
			openFile(jf);
		}

		if (item.isInstanceOf(CadseGCST.ENUM_TYPE)) {
			IType type = EnumTypeManager.getEnumQualifiedClass(ContextVariableImpl.DEFAULT, item);
			if (type != null) {
				IResource jf = type.getResource();
				if (jf != null && jf instanceof IFile) {
					openFile((IFile) jf);
				}
			}
		}
	}

	/**
	 * Open a file
	 * 
	 * @param jf
	 */
	private void openFile(IFile jf) {
		if (jf != null) {
			try {
				IWorkbench workbench = PlatformUI.getWorkbench();
				IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
				IDE.openEditor(activePage, jf, true);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void notifyCreatedLink(LogicalWorkspaceTransaction wc, LinkDelta link) throws CadseException {
		if (!isInCadseDefinition(link.getSource()))
			return;
//		if (link.getLinkType() == CadseGCST.FIELD_lt_ATTRIBUTE) {
//			ItemDelta field = link.getSource();
//			syncDisplay(wc, field);
//		}

		if (link.getLinkType() == CadseGCST.LINK_TYPE_lt_DESTINATION) {
			ItemDelta linkType = link.getSource();
		}

		if (link.getLinkType() == CadseGCST.LINK_TYPE_lt_INVERSE_LINK) {
			Item link1 = link.getSource();
			Item inverseLink = link.getResolvedDestination();
			Link inverseLink_link = LinkTypeManager.getInverseLinkLink(inverseLink);
			if (inverseLink_link == null) {
				inverseLink.createLink(CadseGCST.LINK_TYPE_lt_INVERSE_LINK, link1);
			} else {
				// if (inverseLink_link.getResolvedDestination() != null) {
				// } else {
				// }
			}
		}
//		if (link.getLinkType() == CadseGCST.CREATION_DIALOG_lt_PAGES) {
//			link.getSource().getPartParent().createLink(CadseGCST.ITEM_TYPE_lt_CREATION_PAGES, link.getDestination());
//		}
//
//		if (link.getLinkType() == CadseGCST.MODIFICATION_DIALOG_lt_PAGES) {
//			link.getSource().getPartParent().createLink(CadseGCST.ITEM_TYPE_lt_MODIFICATION_PAGES,
//					link.getDestination());
//		}
//
//		if (link.getLinkType() == CadseGCST.ITEM_TYPE_lt_SUPER_TYPE) {
//			ItemDelta item = link.getSource();
//			ItemDelta itemCreationDialog = item.getOutgoingItem(CadseGCST.TYPE_DEFINITION_lt_CREATION_DIALOG, false);
//			ItemDelta itemFirstCreationPage = itemCreationDialog.getOutgoingItem(CadseGCST.CREATION_DIALOG_lt_PAGES,
//					false);
//			if (itemFirstCreationPage.isAdded()) {
//				syncFieldName(wc, itemFirstCreationPage);
//			}
//		}
	}

	private void syncDisplay(LogicalWorkspaceTransaction wc, ItemDelta displayField) throws CadseException {
		Item attribute = FieldManager.getAttribute(displayField);

		if (attribute == null) {
			return; // error cannot find the attribute...
		}
		//ItemType attribute_type = attribute.getType();
		//ItemType goodDisplayType = getDisplayTypeFromAttribute(attribute, attribute_type);

//		if (goodDisplayType != null) {
//			ItemDelta displayField = (ItemDelta) FieldManager.getDisplay(field);
//			ItemType displayType = displayField == null ? null : displayField.getType();
//
//			if (displayType != null) {
//				if (displayType != goodDisplayType) {
//					displayField.delete(true);
//					Link field_to_display = field.getOutgoingLink(CadseGCST.FIELD_lt_DISPLAY);
//					if (field_to_display != null) {
//						field_to_display.delete();
//					}
//					displayField = null;
//				} else {
//					// nothing
//				}
//			}
//			if (displayField == null) {
//				displayField = wc.createItemIfNeed(null, DisplayManager.DEFAULT_SHORT_NAME, goodDisplayType, field,
//						CadseGCST.FIELD_lt_DISPLAY);
//			}
//			
//		}
		if (displayField.getType() == CadseGCST.DBROWSER) {
			sync_IC_and_MC(wc, displayField, getMCType(displayField), CadseGCST.FIELD_lt_MC,
					getICTypeForDBrowserUI(displayField), CadseGCST.FIELD_lt_IC);

		} else if (displayField.getType() == CadseGCST.DCOMBO) {
			sync_IC_and_MC(wc, displayField, getMCType(displayField), CadseGCST.FIELD_lt_MC,
					getICTypeForDCombo(displayField), CadseGCST.FIELD_lt_IC);
		} else if (displayField.getType() == CadseGCST.DTEXT) {
			sync_IC_and_MC(wc, displayField, getMCType(displayField), CadseGCST.FIELD_lt_MC, null,
					CadseGCST.FIELD_lt_IC);
		} else if (displayField.getType() == CadseGCST.DLIST) {
			if (displayField.isAdded()) {
				DListManager.setEditableButtonAttribute(displayField, true);
			}
			sync_IC_and_MC(wc, displayField, getMCType(displayField), CadseGCST.FIELD_lt_MC,
					getICTypeForDList(displayField), CadseGCST.FIELD_lt_IC);
		} else if (displayField.getType() == CadseGCST.DCHECK_BOX) {
			sync_IC_and_MC(wc, displayField, getMCType(displayField), CadseGCST.FIELD_lt_MC, null,
					CadseGCST.FIELD_lt_IC);
		}
	}

	@Override
	public void notifyDeletedLink(LogicalWorkspaceTransaction wc, LinkDelta link) throws CadseException {
		if (!isInCadseDefinition(link.getSource()))
			return;
		if (link.getLinkType() == CadseGCST.FIELD_lt_ATTRIBUTE) {
			ItemDelta display = link.getSource();
			if (display instanceof ItemDelta) {
				display.delete(false);
			}
		}
		if (link.getLinkType() == CadseGCST.LINK_TYPE_lt_DESTINATION && link.getDestination().isDeleted()) {
			// the destination is deleted, the link must be delete
			link.getSource().delete(link.getDeleteOperation(), 0);
		}

//		if (link.getLinkType() == CadseGCST.CREATION_DIALOG_lt_PAGES) {
//			ItemDelta s;
//			ItemDelta p;
//			if (((s = link.getSource()) != null) && ((p = s.getPartParent()) != null)) {
//				LinkDelta l = p.getOutgoingLink(CadseGCST.ITEM_TYPE_lt_CREATION_PAGES, link.getDestination().getId());
//				if (l != null)
//					l.delete();
//			}
//		}
//
//		if (link.getLinkType() == CadseGCST.MODIFICATION_DIALOG_lt_PAGES) {
//			ItemDelta s;
//			ItemDelta p;
//			if (((s = link.getSource()) != null) && ((p = s.getPartParent()) != null)) {
//				LinkDelta l = p.getOutgoingLink(CadseGCST.ITEM_TYPE_lt_MODIFICATION_PAGES, link.getDestination()
//						.getId());
//				if (l != null)
//					l.delete();
//			}
//		}
	}

	private ItemType getDisplayTypeFromAttribute(Item attribute, ItemType attribute_type) throws CadseException {

		if (AttributeManager.isIsListAttribute(attribute)) {
			return CadseGCST.DLIST;
		} else {
			if (attribute_type == CadseGCST.BOOLEAN) {
				return CadseGCST.DCHECK_BOX;
			} else if (attribute_type == CadseGCST.DOUBLE) {
				return CadseGCST.DTEXT;
			} else if (attribute_type == CadseGCST.ENUM) {
				return CadseGCST.DBROWSER;
			} else if (attribute_type == CadseGCST.INTEGER) {
				return CadseGCST.DTEXT;
			} else if (attribute_type == CadseGCST.LINK_TYPE) {
				final int max = LinkTypeManager.getMax(attribute);
				if (max == 0 || max == 1) {
					return CadseGCST.DBROWSER;
				} else {
					return CadseGCST.DLIST;
				}
			} else if (attribute_type == CadseGCST.STRING) {
				return CadseGCST.DTEXT;
			} else if (attribute_type == CadseGCST.DATE) {
				return CadseGCST.DTEXT;
			} else if (attribute_type == CadseGCST.LONG) {
				return CadseGCST.DTEXT;
			} else if (attribute_type == CadseGCST.URL) {
				return CadseGCST.DBROWSER;
			} else if (attribute_type == CadseGCST.UUID) {
				return CadseGCST.DTEXT;
			}
		}
		return null;
	}

//	/**
//	 * Do create field.
//	 * 
//	 * @param attribute
//	 *            the attribute
//	 * @param creationPage
//	 *            the page
//	 * 
//	 * @return the item
//	 * 
//	 * @throws CadseException
//	 *             the melusine exception
//	 */
//	private void syncFieldFromAttribute(LogicalWorkspaceTransaction wc, ItemDelta itemType, ItemDelta attribute,
//			boolean force) throws CadseException {
//		if (attribute.isDeleted()) {
//			Item pc1 = getFirstCreationPage(wc, itemType, false);
//			Item pm1 = getFirstModificationPage(wc, itemType, false);
//			Item field_pc1 = getFieldFromAttribute(attribute, pc1);
//			if (field_pc1 != null) {
//				field_pc1.delete(true);
//			}
//			Item field_pm1 = getFieldFromAttribute(attribute, pm1);
//			if (field_pm1 != null) {
//				field_pm1.delete(true);
//			}
//			return;
//		}
//		Item pc1 = getFirstCreationPage(wc, itemType, false);
//		Item pm1 = getFirstModificationPage(wc, itemType, true);
//		Item field_pc1 = getFieldFromAttribute(attribute, pc1);
//		Item field_pm1 = getFieldFromAttribute(attribute, pm1);
//
//		String shortName = doShortName(attribute);
//		String label = attribute.getName();
//		if (field_pm1 != null) {
//			field_pm1.setName(shortName);
//			field_pm1.setAttribute(CadseGCST.FIELD_at_LABEL_, label);
//		}
//		if (field_pc1 != null) {
//			field_pc1.setName(shortName);
//			field_pc1.setAttribute(CadseGCST.FIELD_at_LABEL_, label);
//		}
//
//		if (force || AttributeManager.isMustBeInitializedAttribute(attribute)) {
//			if (field_pc1 == null) {
//				// create la page de creation if need
//				if (pc1 == null) {
//					pc1 = getFirstCreationPage(wc, itemType, true);
//				}
//
//				syncFieldName(wc, pc1);
//
//				// create field in creation page if the field must be
//				// initialized at creation time or force flag is true
//				field_pc1 = doCreateField(wc, itemType, attribute, pc1);
//			}
//		} else {
//			if (field_pc1 != null && field_pc1 instanceof ItemDelta) {
//				if (((ItemDelta) field_pc1).isAdded()) {
//					field_pc1.delete(false);
//				}
//			}
//		}
//		if (field_pm1 == null) {
//			// create field in modification page
//			field_pm1 = doCreateField(wc, itemType, attribute, pm1);
//		}
//	}

//	private void syncFieldName(LogicalWorkspaceTransaction wc, Item page) throws CadseException {
//		// if (page == null)
//		// page = attribute.getPartParent(CadseGCST.PAGE);
//
//		boolean addInternalShortName = PageManager.addInternalShortName(page);
//		boolean addInternalAttribute = PageManager.addInternalAttribute(page);
//
//		if (addInternalShortName || addInternalAttribute) {
//			// if (pc1 == null) {
//			// pc1 = getFirstCreationPage(wc, itemType, true);
//			// }
//
//			Item nameField = getFieldFromAttribute(wc, CadseGCST.ITEM_at_NAME_, page);
//			if (nameField == null) {
//				// create name field if not exists
//				nameField = wc.createItemIfNeed(null, CadseGCST.ITEM_at_NAME, CadseGCST.FIELD, page,
//						CadseGCST.PAGE_lt_FIELDS, CadseGCST.FIELD_lt_ATTRIBUTE, CadseGCST.ITEM_at_NAME_,
//						CadseGCST.FIELD_at_LABEL_, CadseGCST.ITEM_at_NAME, CadseGCST.FIELD_at_POSITION_,
//						EPosLabel.defaultpos);
//				List<Link> pageToFields = page.getOutgoingLinks(CadseGCST.PAGE_lt_FIELDS);
//				if (pageToFields.get(0).getDestination() != nameField) {
//					int i = pageToFields.indexOf(page.getOutgoingLink(CadseGCST.PAGE_lt_FIELDS, nameField.getId()));
//					if (i != -1)
//						((LinkDelta) pageToFields.get(i)).moveBefore(pageToFields.get(0));
//				}
//			}
//		} else {
//			ItemDelta nameField = getFieldFromAttribute(wc, CadseGCST.ITEM_at_NAME_, page);
//			if (nameField != null && nameField.isAdded())
//				nameField.delete(true);
//
//		}
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#createdItem(fr.imag.adele
	 * .cadse.core.Item)
	 */
	private void sync_IC_and_MC(LogicalWorkspaceTransaction wc, Item dbrowser, ItemType mcType, LinkType display_mc,
			ItemType icType, LinkType display_ic) throws CadseException {

		sync_IC_and_MC(wc, dbrowser, DisplayManager.MC_DEFAULT_NAME, DisplayManager.getItemMC(dbrowser), mcType,
				display_mc);
		sync_IC_and_MC(wc, dbrowser, DisplayManager.IC_DEFAULT_NAME, DisplayManager.getItemIC(dbrowser), icType,
				display_ic);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#createdItem(fr.imag.adele
	 * .cadse.core.Item)
	 */
	private void sync_IC_and_MC(LogicalWorkspaceTransaction wc, Item displayItem, String shortName,
			Item ic_or_mc_foundItem, ItemType mc_or_ic_ItemType, LinkType display_lt_ic_or_mc) throws CadseException {

		if (mc_or_ic_ItemType != null) {
			if (ic_or_mc_foundItem != null && ic_or_mc_foundItem.getType() != mc_or_ic_ItemType) {
				ic_or_mc_foundItem.delete(true);
				ic_or_mc_foundItem = null;
			}
			if (ic_or_mc_foundItem == null) {
				wc.createItemIfNeed(null, shortName, mc_or_ic_ItemType, displayItem, display_lt_ic_or_mc);
			}
		} else if (ic_or_mc_foundItem != null) {
			ic_or_mc_foundItem.delete(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#createdItem(fr.imag.adele
	 * .cadse.core.Item)
	 */
	private ItemType getMCType(Item displayItem) throws CadseException {

		Item field = displayItem.getPartParent();
		Item attribute = FieldManager.getAttribute(field);

		if (attribute == null) {
			return null;
		}
		ItemType attribute_type = attribute.getType();
		if (AttributeManager.isIsListAttribute(attribute)) {
			if (attribute_type == CadseGCST.ENUM) {
				return CadseGCST.MC_STRING_LIST_TO_ENUM_LIST;
			} else if (attribute_type == CadseGCST.LINK_TYPE) {
				return CadseGCST.MC_LINK;
			} else if (attribute_type == CadseGCST.STRING) {
				return CadseGCST.MC_LIST_OF_STRING;
			}
			return CadseGCST.MC_LIST_OF_STRING;
		} else {
			if (attribute == CadseGCST.ITEM_at_NAME_ || attribute.getBaseItem() == CadseGCST.ITEM_at_NAME_)
				return CadseGCST.MC_NAME_ATTRIBUTE;

			if (attribute_type == CadseGCST.BOOLEAN) {
				return CadseGCST.MC_BOOLEAN;
			} else if (attribute_type == CadseGCST.DOUBLE) {
				return null;
			} else if (attribute_type == CadseGCST.ENUM) {
				return CadseGCST.MC_ENUM;
			} else if (attribute_type == CadseGCST.LINK_TYPE) {
				if (displayItem.getType() == CadseGCST.DCHECK_BOX) {
					return CadseGCST.MC_LINK_TO_BOOLEAN;
				}
				return CadseGCST.MC_LINK;
			} else if (attribute_type == CadseGCST.STRING) {
				return null;
			} else if (attribute.getType() == CadseGCST.INTEGER) {
				return CadseGCST.MC_INTEGER;
			} else if (attribute.getType() == CadseGCST.DATE) {
				return CadseGCST.MC_DATE;
			}

		}
		return null;
	}

	private ItemType getICTypeForDList(Item item) throws CadseException {
		Item field = item.getPartParent();
		Item attribute = FieldManager.getAttribute(field);

		if (attribute == null) {
			return null;
		}

		ItemType attribute_type = attribute.getType();
		if (AttributeManager.isIsListAttribute(attribute)) {
			if (attribute_type == CadseGCST.ENUM) {
				return CadseGCST.IC_ENUM_FOR_LIST;
			} else if (attribute_type == CadseGCST.LINK_TYPE) {
				return CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST;
			} else if (attribute_type == CadseGCST.STRING) {
				return CadseGCST.IC_STRING_LIST_FOR_LIST;
			}
			return CadseGCST.IC_STRING_LIST_FOR_LIST;
		} else {
			// it's not possible
			return null;
		}
	}

	private ItemType getICTypeForDBrowserUI(Item dbrowser) throws CadseException {

		Item field = dbrowser.getPartParent();
		Item attribute = FieldManager.getAttribute(field);

		if (attribute == null) {
			return null;
		}
		ItemType attribute_type = attribute.getType();
		if (AttributeManager.isIsListAttribute(attribute)) {
			// it's not possible
			return null;
		} else {
			if (attribute_type == CadseGCST.BOOLEAN) {
				return null;
			} else if (attribute_type == CadseGCST.DOUBLE) {
				return null;
			} else if (attribute_type == CadseGCST.ENUM) {
				return CadseGCST.IC_ENUM_FOR_BROWSER_COMBO;

			} else if (attribute_type == CadseGCST.INTEGER) {
				return null;

			} else if (attribute_type == CadseGCST.LINK_TYPE) {
				Item itemtypedest = LinkTypeManager.getDestination(attribute);

				Item[] incomingLinkType = ItemTypeManager.getIncomingLinkTypesOfPart(itemtypedest);

				if (incomingLinkType.length == 1 && incomingLinkType[0] != attribute) {
					return CadseGCST.IC_PART_LINK_FOR_BROWSER_COMBO_LIST;
				} else {
					return CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST;
				}

			} else if (attribute_type == CadseGCST.STRING) {

			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fede.workspace.model.manager.DefaultItemManager#createdItem(fr.imag.adele
	 * .cadse.core.Item)
	 */
	private ItemType getICTypeForDCombo(Item item) throws CadseException {
		// TODO:
		Item field = item.getPartParent();
		Item attribute = FieldManager.getAttribute(field);

		if (attribute == null) {
			return null;
		}
		ItemType attribute_type = attribute.getType();
		if (AttributeManager.isIsListAttribute(attribute)) {
			// it's not possible
			return null;
		} else {
			if (attribute_type == CadseGCST.BOOLEAN) {
				return null;
			} else if (attribute_type == CadseGCST.DOUBLE) {
				return null;
			} else if (attribute_type == CadseGCST.ENUM) {
				return CadseGCST.IC_ENUM_FOR_BROWSER_COMBO;
			} else if (attribute_type == CadseGCST.INTEGER) {
				return null;
			} else if (attribute_type == CadseGCST.LINK_TYPE) {
				return CadseGCST.IC_LINK_FOR_BROWSER_COMBO_LIST;
			} else if (attribute_type == CadseGCST.STRING) {
				return null;
			}
		}
		return null;
	}

	/**
	 * Do short name.
	 * 
	 * @param attribute
	 *            the attribute
	 * 
	 * @return the string
	 */
	public static String doShortName(Item attribute) {
		return attribute.getName();
	}

//	private ItemDelta getFieldFromAttribute(LogicalWorkspaceTransaction wc, Item attribute, Item p1) {
//		return getFieldFromAttribute(wc.getItem(attribute), p1);
//
//	}

//	private ItemDelta getFieldFromAttribute(ItemDelta attribute, Item p1) {
//		if (p1 == null) {
//			return null;
//		}
//
//		Collection<ItemDelta> fields = getFieldsFromAttribute(attribute);
//		if (fields == null || fields.size() == 0) {
//			return null;
//		}
//		for (ItemDelta f : fields) {
//			if (f.getPartParent() == p1) {
//				return f;
//			}
//		}
//		return null;
//	}
//
//	private Collection<ItemDelta> getFieldsFromAttribute(ItemDelta attribute) {
//		return attribute.getIncomingItems(CadseGCST.FIELD_lt_ATTRIBUTE, false, true);
//	}

//	/**
//	 * Do create field.
//	 * 
//	 * @param attribute
//	 *            the attribute
//	 * @param page
//	 *            the page
//	 * 
//	 * @return the new field
//	 * 
//	 * @throws CadseException
//	 *             the melusine exception
//	 */
//	public static ItemDelta doCreateField(LogicalWorkspaceTransaction wc, Item itemType, Item attribute, Item page)
//			throws CadseException {
//		// Item itemType = PageManager.getItemTypeFromPage(page);
//
//		String shortName = doShortName(attribute);
//		String label = attribute.getName();
//		EPosLabel pos = EPosLabel.defaultpos;
//		return wc.createItemIfNeed(null, shortName, CadseGCST.FIELD, page, CadseGCST.PAGE_lt_FIELDS,
//				CadseGCST.FIELD_lt_ATTRIBUTE, attribute, CadseGCST.FIELD_at_LABEL_, label,
//				CadseGCST.FIELD_at_POSITION_, pos);
//	}

//	/**
//	 * Cherche la premier page r�solu. Si il y en a aucune, il en cree une.
//	 * 
//	 * @param modificationDialog
//	 *            the modification dialog
//	 * 
//	 * @return the or create first page
//	 * 
//	 * @throws CadseException
//	 *             the melusine exception
//	 */
//	public Item getFirstModificationPage(LogicalWorkspaceTransaction wc, Item itemType, boolean createIt)
//			throws CadseException {
//
//		Item modificationDialog = ItemTypeManager.getModificationDialog(itemType);
//		if (modificationDialog == null) {
//			if (!createIt) {
//				return null;
//			}
//			modificationDialog = createModificationDialog(wc, itemType);
//		}
//
//		for (Link l : modificationDialog.getOutgoingLinks()) {
//			// Select link has kind Part and destination.
//			if (l.getLinkType() == CadseGCST.MODIFICATION_DIALOG_lt_PAGES) {
//				// if l is resovled, take this destination to return list.
//				if (l.isLinkResolved()) {
//					return l.getResolvedDestination();
//				}
//			}
//		}
//		if (!createIt) {
//			return null;
//		}
//
//		return createFirstModificationPage(wc, modificationDialog, false);
//	}

//	/**
//	 * Cherche la premier page r�solu. Si il y en a aucune, il en cree une.
//	 * 
//	 * @param modificationDialog
//	 *            the modification dialog
//	 * 
//	 * @return the first page or null if not found and createIt is false
//	 * 
//	 * @throws CadseException
//	 *             the melusine exception
//	 */
//	public Item getFirstCreationPage(LogicalWorkspaceTransaction wc, Item itemType, boolean createIt)
//			throws CadseException {
//
//		Item creationDialog = ItemTypeManager.getCreationDialog(itemType);
//		if (creationDialog == null) {
//			if (!createIt) {
//				return null;
//			}
//			creationDialog = createCreationDialog(wc, itemType);
//		}
//
//		for (Link l : creationDialog.getOutgoingLinks()) {
//			// Select link has kind Part and destination.
//			if (l.getLinkType() == CadseGCST.CREATION_DIALOG_lt_PAGES) {
//				// if l is resovled, take this destination to return list.
//				if (l.isLinkResolved()) {
//					final Item page = l.getResolvedDestination();
//
//					if (PageManager.isIsRemovedAttribute(page)) {
//						continue;
//					}
//					return page;
//				}
//			}
//		}
//		if (!createIt) {
//			return null;
//		}
//
//		return createFirstCreationPage(wc, creationDialog);
//	}

	@Override
	public void notifyLoadedItem(LogicalWorkspaceTransaction workspaceLogiqueWorkingCopy, List<ItemDelta> loadedItems) {
		for (ItemDelta oper : loadedItems) {
			if (oper.getType() != CadseGCST.FIELD) {
				continue;
			}
			tryToRecreateAttributeLink(oper);
		}
		
		

		for (ItemDelta oper : loadedItems) {
			if (oper.isInstanceOf(CadseGCST.CONTENT_ITEM_TYPE)) {
				try {
					Item linkType = oper.getIncomingItem(CadseGCST.CONTENT_LINK_TYPE_lt_CONTENT_DEFINITION);
					if (linkType == null) {
						Item itemType = oper.getPartParent();
						if (itemType.isInstanceOf(CadseGCST.MANAGER)) {
							itemType = ManagerManager.getItemType(itemType);
						}
						linkType = workspaceLogiqueWorkingCopy.createItem(CadseGCST.CONTENT_LINK_TYPE, itemType , CadseGCST.TYPE_DEFINITION_lt_ATTRIBUTES);
						linkType.setName("contents");
						LinkTypeManager.setDestination(linkType, CadseGCST.CONTENT_ITEM);
						LinkTypeManager.setMaxAttribute(linkType, 1);
						LinkTypeManager.setMinAttribute(linkType, 0);
						AttributeManager.setHiddenInComputedPagesAttribute(linkType, true);
						linkType.createLink(CadseGCST.CONTENT_LINK_TYPE_lt_CONTENT_DEFINITION, oper);
					}
				} catch (Throwable e) {
					e.printStackTrace();
				}
				continue;
			}
		// remove this code (present in import code)
//			if (oper.isInstanceOf(CadseGCST.ATTRIBUTE)) {
//				String uuid = oper.getAttribute("UUID_ATTRIBUTE");
//				if (uuid != null && uuid.length() != 0)
//					try {
//						oper.setAttribute(CadseGCST.ATTRIBUTE_at_ID_RUNTIME_, uuid);
//					} catch (CadseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				try {
//					oper.setAttribute("UUID_ATTRIBUTE", null);
//				} catch (CadseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				continue;
//			}
//			if (oper.isInstanceOf(CadseGCST.TYPE_DEFINITION)) {
//				String uuid = oper.getAttribute("UUID_ATTRIBUTE");
//				if (uuid != null && uuid.length() != 0)
//					try {
//						oper.setAttribute(CadseGCST.TYPE_DEFINITION_at_ID_RUNTIME_, uuid);
//					} catch (CadseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				try {
//					oper.setAttribute("UUID_ATTRIBUTE", null);
//				} catch (CadseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				continue;
//			}
			if (oper.getType() == CadseGCST.CADSE_DEFINITION) {
				String qname = oper.getQualifiedName();
				if (qname == null || !qname.contains("."))
					oper.setQualifiedName(CadseRuntime.CADSE_NAME_SUFFIX + oper.getName());
					// remove this code (present in import code)
//				String uuid = oper.getAttribute("UUID_ATTRIBUTE");
//				if (uuid != null && uuid.length() != 0)
//					try {
//						oper.setAttribute(CadseGCST.CADSE_DEFINITION_at_ID_RUNTIME_, uuid);
//					} catch (CadseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				try {
//					oper.setAttribute("UUID_ATTRIBUTE", null);
//				} catch (CadseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				continue;
			}
			if (oper.getType() == CadseGCST.DATA_MODEL) {
				Collection<LinkDelta> extLinks = oper.getOutgoingLinkOperations();
				for (LinkDelta linkDelta : extLinks) {
					if (linkDelta.getLinkTypeName().equals("ext-types")) {
						try {
							oper.createLink(CadseGCST.DATA_MODEL_lt_TYPES, linkDelta.getDestination());
						} catch (CadseException ignored) {
						}
						try {
							linkDelta.delete();
						} catch (CadseException ignored) {
						}
					}
				}
				continue;
			}
//			if (oper.getType() == CadseGCST.CREATION_DIALOG) {
//				String dv = oper.getAttribute(CadseGCST.CREATION_DIALOG_at_DEFAULT_SHORT_NAME_);
//				if (dv != null && dv.length() != 0) {
//					String gv = oper.getAttribute(CadseGCST.CREATION_DIALOG_at_GENERATE_AUTOMATIC_SHORT_NAME_);
//					if (gv != null && gv.length() != 0) {
//						_logger.log(Level.SEVERE, "Cannot set generated value to " + dv + " for item "
//								+ oper.getDisplayName(), " : gv exists : " + gv);
//					} else {
//						try {
//							oper.setAttribute(CadseGCST.CREATION_DIALOG_at_GENERATE_AUTOMATIC_SHORT_NAME_, dv);
//
//						} catch (CadseException e) {
//							_logger.log(Level.SEVERE, "Cannot set generated value to " + dv + " for item "
//									+ oper.getDisplayName(), e);
//						}
//					}
//				}
//				try {
//					oper.setAttribute(CadseGCST.CREATION_DIALOG_at_DEFAULT_SHORT_NAME_, null);
//				} catch (CadseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				continue;
//			}

		}

		try {
			for (ItemDelta oper : loadedItems) {
				Collection<LinkDelta> links = oper.getOutgoingLinkOperations();
				for (LinkDelta linkDelta : links) {
					if (linkDelta.getLinkType() == CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES) {
						final ItemDelta destination = linkDelta.getDestination();
						if (destination == null
								|| destination.getType() == CadseGCST.UNRESOLVED_ATTRIBUTE_TYPE
								|| (destination.getName().startsWith("#parent:") && destination.getType() == CadseGCST.LINK_TYPE)) {
							try {
								linkDelta.delete();
								// System.out.println("cadseg remove " +
								// linkDelta);
							} catch (CadseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Item tryToRecreateAttributeLink(Item field) {
		try {
			Item attribute = FieldManager.getAttribute(field);
			if (attribute == null) {
				Item page = field.getPartParent();
				if (page == null) {
					_logger.log(Level.SEVERE, "Cannot find the page parent of field " + field.getDisplayName() + "("
							+ field.getId() + ")");
					return null;
				}
				Item dialog = page.getPartParent();
				Item itemType = dialog.getPartParent();
				attribute = ItemTypeManager.getAttribute(itemType, field.getName());
				if (attribute != null) {
					field.createLink(CadseGCST.FIELD_lt_ATTRIBUTE, attribute);
				}
			}
			return attribute;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	private void syncCompositeType(LogicalWorkspaceTransaction workspaceLogiqueWorkingCopy, Item linkDefinition,
			Item itemType, SetAttributeOperation att) {

		if (itemType == null) {
			itemType = linkDefinition.getPartParent();
			if (itemType == null) {
				return;
			}
		}
		Item compositeitem = CompositeItemTypeManager.getCompositeItemFromItemType(itemType);
		boolean iscomposition = ItemTypeManager.isComposition(itemType);
		if (compositeitem != null && iscomposition) {
			// set name
			if (att != null) {
				compositeitem.setName(itemType.getName() + "-composite");
			}
			return;
		}
		if (compositeitem == null && !iscomposition) {
			return;
		}

		if (iscomposition) {
			Item wsmodel = ItemTypeManager.getCadseDefinition(itemType);
			Item buildmodel = CadseDefinitionManager.getBuildModel(wsmodel);
			try {
				compositeitem = workspaceLogiqueWorkingCopy.createItemIfNeed(null, itemType.getName() + "-composite",
						CadseGCST.COMPOSITE_ITEM_TYPE, buildmodel, CadseGCST.BUILD_MODEL_lt_COMPOSITE_TYPES);
				compositeitem.createLink(CadseGCST.COMPOSITE_ITEM_TYPE_lt_ITEM_TYPE, itemType);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				compositeitem.delete(true);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Migrate item type.
	 * 
	 * @param itemType
	 *            the item type
	 * @param newCxt
	 *            the new cxt
	 * @param oldCxt
	 *            the old cxt
	 */
	public static void migrateManager(ItemDelta manager, Item itemType, ContextVariable newCxt, ContextVariable oldCxt) {
		JavaFileContentManager cm = (JavaFileContentManager) manager.getContentItem();
		cm.migrateContentItem(manager, newCxt, oldCxt);
	}

	/**
	 * Migrate item type.
	 * 
	 * @param p
	 *            the p
	 * @param newCxt
	 *            the new cxt
	 * @param oldCxt
	 *            the old cxt
	 */
	public static void migratePage(ItemDelta p, ContextVariable newCxt, ContextVariable oldCxt) {
		JavaFileContentManager cm = (JavaFileContentManager) p.getContentItem();
		if (cm != null) {
			cm.migrateContentItem(p, newCxt, oldCxt);
		}
	}

//	/**
//	 * Migrate item type.
//	 * 
//	 * @param createDialog
//	 *            the create dialog
//	 * @param newCxt
//	 *            the new cxt
//	 * @param oldCxt
//	 *            the old cxt
//	 */
//	public static void migrateCreationDialogManager(ItemDelta createDialog, ContextVariable newCxt,
//			ContextVariable oldCxt) {
//		Collection<Item> pages = CreationDialogManager.getPages(createDialog);
//		JavaFileContentManager cm = (JavaFileContentManager) createDialog.getContentItem();
//		if (cm != null) {
//			cm.migrateContentItem(createDialog, newCxt, oldCxt);
//		}
//		for (Item p : pages) {
//			migratePage((ItemDelta) p, newCxt, oldCxt);
//		}
//	}

//	/**
//	 * Migrate item type.
//	 * 
//	 * @param modificationDialog
//	 *            the modification dialog
//	 * @param newCxt
//	 *            the new cxt
//	 * @param oldCxt
//	 *            the old cxt
//	 */
//	public static void migrateModificationDialogManager(Item modificationDialog, ContextVariable newCxt,
//			ContextVariable oldCxt) {
//		Collection<Item> pages = ModificationDialogManager.getPages(modificationDialog);
//		for (Item p : pages) {
//			migratePage((ItemDelta) p, newCxt, oldCxt);
//		}
//	}

	// old project
	// new project
	// old package
	// new cmlppackage

	@Override
	public void notifyMigratePartLink(LogicalWorkspaceTransaction wc, ItemDelta childItem, ItemDelta newPartParent,
			LinkType lt, LinkDelta newPartLink, LinkDelta oldPartLink) throws CadseException {
		if (childItem.getType() == CadseGCST.ITEM_TYPE) {
			ItemDelta itemtype = childItem;
			// L'itemtype migre dans un nouveau cadse?
			ItemDelta manager = (ItemDelta) ManagerManager.getManagerFromItemType(itemtype);
			Item oldCadseDefinition = manager.getPartParent(CadseGCST.CADSE_DEFINITION);
			Item newCadseDefinition = newPartParent.getPartParent(CadseGCST.CADSE_DEFINITION);
			if (oldCadseDefinition != newCadseDefinition) {
				// new cadse, you must migrate the manager...
				manager.migratePartLink(CadseDefinitionManager.getMapping(newCadseDefinition),
						CadseGCST.MAPPING_MODEL_lt_MANAGERS);
			}
			ContextVariable oldContext = wc.getOldContext();
			ContextVariable newContext = wc.getNewContext();

			migrateManager(manager, itemtype, newContext, oldContext);
//			ItemDelta createDialog = (ItemDelta) ItemTypeManager.getCreationDialog(itemtype);
//			if (createDialog != null) {
//				migrateCreationDialogManager(createDialog, newContext, oldContext);
//			}
//			Item modificationDialog = ItemTypeManager.getModificationDialog(itemtype);
//			if (modificationDialog != null) {
//				migrateModificationDialogManager(modificationDialog, newContext, oldContext);
//			}
		}
		if (childItem.getType() == CadseGCST.MANAGER) {
			ItemDelta manager = childItem;
			ContentItem cm = manager.getContentItem();
			// Item cadseDefinition =
			// manager.getPartParent(CadseGCST.CADSE_DEFINITION);
			ContextVariable oldContext = wc.getOldContext();
			ContextVariable newContext = wc.getNewContext();
			cm.migrateContentItem(manager, newContext, oldContext);
		}

	}
}