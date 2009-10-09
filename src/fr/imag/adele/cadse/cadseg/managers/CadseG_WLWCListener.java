/**
 *
 */
package fr.imag.adele.cadse.cadseg.managers;

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

import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fede.workspace.eclipse.java.manager.JavaProjectContentManager;
import fr.imag.adele.cadse.cadseg.WorkspaceCST;
import fr.imag.adele.cadse.cadseg.managers.actions.MenuAbstractManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeManager;
import fr.imag.adele.cadse.cadseg.managers.attributes.LinkManager;
import fr.imag.adele.cadse.cadseg.managers.build.ComposerLinkManager;
import fr.imag.adele.cadse.cadseg.managers.build.CompositeItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.CreationDialogManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.EnumTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ModificationDialogManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.PageManager;
import fr.imag.adele.cadse.cadseg.managers.ui.DListManager;
import fr.imag.adele.cadse.cadseg.managers.ui.DisplayManager;
import fr.imag.adele.cadse.cadseg.managers.ui.FieldManager;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseRootCST;
import fr.imag.adele.cadse.core.ContentItem;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LinkType;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.delta.ItemDelta;
import fr.imag.adele.cadse.core.delta.LinkDelta;
import fr.imag.adele.cadse.core.delta.MappingOperation;
import fr.imag.adele.cadse.core.delta.SetAttributeOperation;
import fr.imag.adele.cadse.core.transaction.AbstractLogicalWorkspaceTransactionListener;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;
import fr.imag.adele.cadse.core.ui.EPosLabel;
import fr.imag.adele.cadse.core.util.Convert;
import fr.imag.adele.cadse.core.var.ContextVariable;

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
		return cxt.getAttribute((Item) _parent, WorkspaceCST.CADSE_DEFINITION_at_PACKAGENAME_);
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

	private static final String	CREATION_PAGE_TITLE_PREFIX	= "Create ";
	private static final String	MODIFICATION_PAGE_PREFIX	= "modification-page-";
	private static final String	CREATION_PAGE_PREFIX		= "creation-page-";
	private static final String	PARENT_NAME					= "#invert_part_";

	private static final String	EMPTY_PAGE_CREATION_ID		= "creation-page1";
	private static final String	EMPTY_PAGE_MODIFICATION_ID	= "modification-page1";
	private static Logger		_logger						= Logger
																	.getLogger("fr.imag.adele.cadse.cadseg.CadseG_WLWCListener");

	public CadseG_WLWCListener() {
		WorkspaceCST.ABSTRACT_ITEM_TYPE.addLogicalWorkspaceTransactionListener(this);
		WorkspaceCST.PAGE.addLogicalWorkspaceTransactionListener(this);
		WorkspaceCST.MANAGER.addLogicalWorkspaceTransactionListener(this);
		WorkspaceCST.CADSE_DEFINITION.addLogicalWorkspaceTransactionListener(this);
		WorkspaceCST.ATTRIBUTE.addLogicalWorkspaceTransactionListener(this);
		WorkspaceCST.FIELD.addLogicalWorkspaceTransactionListener(this);
		WorkspaceCST.MENU_ACTION.addLogicalWorkspaceTransactionListener(this);
	}

	@Override
	public void notifyCreatedItem(LogicalWorkspaceTransaction wc, ItemDelta item) throws CadseException {

		/*
		 * Create dialog item and first page
		 */
		if (item.isInstanceOf(WorkspaceCST.ABSTRACT_ITEM_TYPE)) {
			createDialogAndPages(wc, item);
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
		if (item.isInstanceOf(WorkspaceCST.CADSE_DEFINITION)) {
			createdCadseDefintionItem(wc, item);
		}

		/*
		 * Create a field from an attribute
		 */
		if (item.getType() == WorkspaceCST.ATTRIBUTE) {
			final ItemDelta itemType = item.getPartParent();
			syncFieldFromAttribute(wc, itemType, item, false);
		}

	}

	/**
	 * An item of type ABSTRACT_ITEM_TYPE is been created. You must create a
	 * creation dialog, a modification dialog, a first creation dialog page and
	 * a first modification dialog page...
	 */
	private void createDialogAndPages(LogicalWorkspaceTransaction wc, ItemDelta item) throws CadseException {

		ItemDelta itemType = item;

		ItemDelta itemCreationDialog = createCreationDialog(wc, itemType);

		ItemDelta itemModificationDialog = createModificationDialog(wc, itemType);

		createFirstCreationPage(wc, itemCreationDialog);

		createFirstModificationPage(wc, itemModificationDialog, true);

	}

	/**
	 * Create a modification dialog
	 * 
	 * @param wc
	 *            the working copy where create it
	 * @param itemType
	 *            the parent of the new modification dialog
	 * @return the new modification dialog
	 * @throws CadseException
	 */
	private ItemDelta createModificationDialog(LogicalWorkspaceTransaction wc, Item itemType) throws CadseException {
		ItemDelta itemModificationDialog = wc.createItemIfNeed(null, ModificationDialogManager.DEFAULT_SHORT_NAME,
				WorkspaceCST.MODIFICATION_DIALOG, itemType, WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_MODIFICATION_DIALOG);
		return itemModificationDialog;
	}

	/**
	 * Create a creation dialog
	 * 
	 * @param wc
	 *            the working copy where create it
	 * @param itemType
	 *            the parent of the new creation dialog
	 * @return the new creation dialog
	 * @throws CadseException
	 */
	private ItemDelta createCreationDialog(LogicalWorkspaceTransaction wc, Item itemType) throws CadseException {
		ItemDelta itemCreationDialog = wc.createItemIfNeed(null, CreationDialogManager.DEFAULT_SHORT_NAME,
				WorkspaceCST.CREATION_DIALOG, itemType, WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_CREATION_DIALOG);
		return itemCreationDialog;
	}

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
		wc.createItemIfNeed(null, CadseDefinitionManager.DATA_MODEL, WorkspaceCST.DATA_MODEL, cadseDefinition,
				WorkspaceCST.CADSE_DEFINITION_lt_DATA_MODEL);
		// june 09, 2009 removed configuration model
		// wc.createItemIfNeed(null, CadseDefinitionManager.CONFIGURATION_MODEL,
		// WorkspaceCST.CONFIGURATION_MODEL,
		// cadseDefinition, WorkspaceCST.CADSE_DEFINITION_lt_CONFIGURATION);
		wc.createItemIfNeed(null, CadseDefinitionManager.BUILD_MODEL, WorkspaceCST.BUILD_MODEL, cadseDefinition,
				WorkspaceCST.CADSE_DEFINITION_lt_BUILD);
		wc.createItemIfNeed(null, CadseDefinitionManager.VIEW_MODEL, WorkspaceCST.VIEW_MODEL, cadseDefinition,
				WorkspaceCST.CADSE_DEFINITION_lt_VIEW_MODEL);
		wc.createItemIfNeed(null, CadseDefinitionManager.MAPPING, WorkspaceCST.MAPPING_MODEL, cadseDefinition,
				WorkspaceCST.CADSE_DEFINITION_lt_MAPPING);
	}

	/**
	 * Create a first creation page
	 * 
	 * @param wc
	 *            the current working copy
	 * @param itemCreationDialog
	 *            the creation dialog, the parent of the page which create
	 * @throws CadseException
	 */
	private ItemDelta createFirstCreationPage(LogicalWorkspaceTransaction wc, Item itemCreationDialog)
			throws CadseException {
		Item abstype = itemCreationDialog.getPartParent();
		String name = CREATION_PAGE_PREFIX + abstype.getName();

		return wc.createItemIfNeed(null, name, WorkspaceCST.PAGE, itemCreationDialog,
				WorkspaceCST.CREATION_DIALOG_lt_PAGES, WorkspaceCST.PAGE_at_HSPAN,
				ManagerManager.DEFAULT_HSPAN_FIRST_PAGE, WorkspaceCST.PAGE_at_TITLE, CREATION_PAGE_TITLE_PREFIX
						+ abstype.getName(), WorkspaceCST.PAGE_at_IS_REMOVED_, false,
				WorkspaceCST.PAGE_at_DESCRIPTION_, "");
	}

	/**
	 * Create a first modification page
	 * 
	 * @param wc
	 *            the current working copy
	 * @param modificationDialog
	 *            the modification dialog, the parent of the page which create
	 * @throws CadseException
	 */
	private ItemDelta createFirstModificationPage(LogicalWorkspaceTransaction wc, Item modificationDialog,
			boolean doCreatefield) throws CadseException {
		Item abstype = modificationDialog.getPartParent();

		String name = MODIFICATION_PAGE_PREFIX + abstype.getName();

		ItemDelta modificationPage1Item = wc.createItemIfNeed(null, name, WorkspaceCST.PAGE, modificationDialog,
				WorkspaceCST.MODIFICATION_DIALOG_lt_PAGES, WorkspaceCST.PAGE_at_HSPAN_,
				ManagerManager.DEFAULT_HSPAN_FIRST_PAGE, WorkspaceCST.PAGE_at_TITLE_, abstype.getName(),
				WorkspaceCST.PAGE_at_IS_REMOVED_, false, WorkspaceCST.PAGE_at_DESCRIPTION_, "");

		if (doCreatefield) {
			Item[] attributes = ItemTypeManager.getAllAttributes(null, abstype, null, true);
			for (Item attribute : attributes) {
				Item itemType = PageManager.getItemTypeFromPage(modificationPage1Item);
				doCreateField(wc, itemType, attribute, modificationPage1Item);
			}
		}

		return modificationPage1Item;
	}

	/**
	 * The value of attribute definition {@link CadseRootCST#ITEM_TYPE_at_NAME_}
	 * is changed. if the type of the item where the value is changed is
	 * WorkspaceCST.EXT_ITEM_TYPE, you must change the name of first
	 * modification page and first creation page.
	 */
	@Override
	public void notifyChangeAttribute(LogicalWorkspaceTransaction wc, ItemDelta item, SetAttributeOperation attOperation)
			throws CadseException {

		if (!item.isAdded() && item.getType() == WorkspaceCST.CADSE_DEFINITION
				&& attOperation.getAttributeDefinition() == WorkspaceCST.CADSE_DEFINITION_at_PACKAGENAME_) {
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

		if (item.getType() == WorkspaceCST.CADSE_DEFINITION
				&& attOperation.getAttributeDefinition() == CadseRootCST.ITEM_TYPE_at_NAME_) {
			ContextVariable oldContext = wc.getOldContext();
			ContextVariable newContext = wc.getNewContext();
			addRenameCadseDefinitionMappingOperartion(item, oldContext, newContext);
		}

		if (item.isInstanceOf(WorkspaceCST.ABSTRACT_ITEM_TYPE)
				&& attOperation.getAttributeDefinition() == CadseRootCST.ITEM_TYPE_at_NAME_) {
			syncCompositeType(wc, null, item, null);
			syncPages(item, attOperation);

			// for (Item lt :
			// item.getIncomingItems(WorkspaceCST.LINK_lt_DESTINATION)) {
			// if (LinkManager.isPart(lt)) {
			// syncLinkPart(wc, (ItemDelta) lt, true);
			// }
			// }

			for (Item lt : item.getOutgoingItems(WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES, true)) {
				if (lt.getType() == WorkspaceCST.LINK && LinkManager.isPart(lt)) {
					syncLinkPart(wc, (ItemDelta) lt, true);
				}
			}
		}

		if (item.isInstanceOf(WorkspaceCST.ITEM_TYPE)
				&& attOperation.getAttributeDefinition() == CadseRootCST.ITEM_TYPE_at_NAME_) {
			ItemDelta manager = (ItemDelta) ManagerManager.getManagerFromItemType(item);
			if (manager != null) {
				manager.setName(attOperation.getCurrentValue() + "-manager");
			}
			ContextVariable oldContext = wc.getOldContext();
			ContextVariable newContext = wc.getNewContext();
			addRenameOperation(manager, oldContext, newContext);

			// Item creationDialog = ItemTypeManager.getCreationDialog(item);
			// if (creationDialog != null) {
			// for (Item p : CreationDialogManager.getPages(creationDialog)) {
			// addRenameOperation((ItemDelta) p, oldContext, newContext);
			// }
			// }
			//
			// Item modificationDialog =
			// ItemTypeManager.getModificationDialog(item);
			// if (modificationDialog != null) {
			// for (Item p :
			// ModificationDialogManager.getPages(modificationDialog)) {
			// addRenameOperation((ItemDelta) p, oldContext, newContext);
			// }
			// }
		}

		if (item.getType() == WorkspaceCST.PAGE
				& attOperation.getAttributeDefinition() == CadseRootCST.ITEM_TYPE_at_NAME_) {
			ContextVariable oldContext = wc.getOldContext();
			ContextVariable newContext = wc.getNewContext();
			addRenameOperation(item, oldContext, newContext);
		}

		// if (item.isInstanceOf(WorkspaceCST.MANAGER)
		// && attOperation.getAttributeDefinition() ==
		// CadseRootCST.ITEM_TYPE_at_NAME_) {
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
		// // if (itemAnnoted.getType() == WorkspaceCST.ITEM_TYPE) {
		// // return itemAnnotation.computeRenameChange(change,
		// // itemAnnoted.getShortName() + "-manager", newCxt, oldCxt);
		// // }
		// // return null;
		// // }
		// }
		// }

		if (item.getType() == WorkspaceCST.ITEM_TYPE
				&& attOperation.getAttributeDefinition() == CadseRootCST.ITEM_TYPE_at_NAME_) {
			// Display name

			Item manager = ManagerManager.getManagerFromItemType(item);
			if (manager != null) {
				if (ManagerManager.getHumanNameAttribute(manager).equals(attOperation.getPrecCurrentValue())) {
					ManagerManager.setHumanNameAttribute(manager, (String) attOperation.getCurrentValue());
				}
			}

		}

		if (item.isInstanceOf(WorkspaceCST.ATTRIBUTE)) {
			if (attOperation.getAttributeDefinition() == WorkspaceCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_) {
				syncFieldFromAttribute(wc, item.getPartParent(), item, false);
			}
			if (attOperation.getAttributeDefinition() == CadseRootCST.ITEM_TYPE_at_NAME_) {
				syncFieldFromAttribute(wc, item.getPartParent(), item, false);
			}
			if (attOperation.getAttributeDefinition() == WorkspaceCST.ATTRIBUTE_at_IS_LIST_) {
				syncDisplayFromAttribute(wc, item);
			}

			if (attOperation.getAttributeDefinition() == WorkspaceCST.ATTRIBUTE_at_MAX_) {
				syncDisplayFromAttribute(wc, item);
			}

			if (attOperation.getAttributeDefinition() == WorkspaceCST.ATTRIBUTE_at_CLASS_ATTRIBUTE_) {
				syncDisplayFromAttribute(wc, item);
			}
		}

		if (item.isInstanceOf(WorkspaceCST.LINK)) {
			if (attOperation.getAttributeDefinition() == WorkspaceCST.LINK_at_COMPOSITION_) {
				syncCompositeType(wc, item, null, attOperation);
			}

			if (attOperation.getAttributeDefinition() == WorkspaceCST.LINK_at_PART_) {
				syncLinkPart(wc, item, Convert.toBoolean(attOperation.getCurrentValue()));
			}
			if (attOperation.getAttributeDefinition() == CadseRootCST.ITEM_TYPE_at_NAME_) {
				syncLinkPart(wc, item, LinkManager.isPart(item));
			}
		}

		if (item.isInstanceOf(WorkspaceCST.MENU_ACTION)
				&& attOperation.getAttributeDefinition() == CadseRootCST.ITEM_TYPE_at_NAME_) {
			if (Convert.equals(attOperation.getPrecCurrentValue(), MenuAbstractManager.getLabelAttribute(item))) {
				MenuAbstractManager.setLabelAttribute(item, (String) attOperation.getCurrentValue());
			}

		}
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

	/**
	 * 
	 * @param item
	 *            an itemtype or an extension
	 * @param attOperation
	 *            an attribute opertion sur un name
	 * @throws CadseException
	 */
	private void syncPages(ItemDelta item, SetAttributeOperation attOperation) throws CadseException {
		// change id of the first creation page if added
		ItemDelta itemCreationDialog = item.getOutgoingItem(WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_CREATION_DIALOG, false);
		ItemDelta itemFirstCreationPage = itemCreationDialog.getOutgoingItem(WorkspaceCST.CREATION_DIALOG_lt_PAGES,
				false);
		if (itemFirstCreationPage.isAdded()) {
			itemFirstCreationPage.setName(CREATION_PAGE_PREFIX + attOperation.getCurrentValue());
			itemFirstCreationPage.setAttribute(WorkspaceCST.PAGE_at_TITLE_, CREATION_PAGE_TITLE_PREFIX
					+ attOperation.getCurrentValue());
		}

		// change id of the first modification page if added
		ItemDelta itemModificationDialog = item.getOutgoingItem(WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_MODIFICATION_DIALOG,
				false);
		ItemDelta itemFirstModificationPage = itemModificationDialog.getOutgoingItem(
				WorkspaceCST.MODIFICATION_DIALOG_lt_PAGES, false);
		if (itemFirstModificationPage.isAdded()) {
			itemFirstModificationPage.setName(MODIFICATION_PAGE_PREFIX + attOperation.getCurrentValue());
			itemFirstModificationPage.setAttribute(WorkspaceCST.PAGE_at_TITLE_, attOperation.getCurrentValue());
		}

		// rename pages
		if (!item.isAdded()) {
			String oldName = (String) attOperation.getPrecCurrentValue();
			for (Item p : CreationDialogManager.getPages(itemCreationDialog)) {
				ItemDelta pageDelta = (ItemDelta) p;
				if (pageDelta.isAdded()) {
					continue;
				}
				String namePage = pageDelta.getName();
				if (namePage.equals(CREATION_PAGE_PREFIX + oldName)) {
					pageDelta.setName(CREATION_PAGE_PREFIX + attOperation.getCurrentValue());
				}

				// rename the title attribute of page
				String pageTitle = PageManager.getTitle(pageDelta);
				if (pageTitle.equals(CREATION_PAGE_TITLE_PREFIX + oldName)) {
					pageDelta.setAttribute(WorkspaceCST.PAGE_at_TITLE_, CREATION_PAGE_TITLE_PREFIX
							+ attOperation.getCurrentValue());
				}
			}
			for (Item p : ModificationDialogManager.getPages(itemModificationDialog)) {
				ItemDelta pageDelta = (ItemDelta) p;
				if (pageDelta.isAdded()) {
					continue;
				}
				String namePage = pageDelta.getName();
				if (namePage.equals(MODIFICATION_PAGE_PREFIX + oldName)) {
					pageDelta.setName(MODIFICATION_PAGE_PREFIX + attOperation.getCurrentValue());
				}

				// rename the title attribute of page
				String pageTitle = PageManager.getTitle(pageDelta);
				if (pageTitle.equals(oldName)) {
					pageDelta.setAttribute(WorkspaceCST.PAGE_at_TITLE_, attOperation.getCurrentValue());
				}
			}
		}
	}

	private void syncLinkPart(LogicalWorkspaceTransaction wc, ItemDelta link, boolean ispart) throws CadseException {
		ItemDelta sourceLink = (ItemDelta) LinkManager.getSource(link);
		ItemDelta destLink = (ItemDelta) LinkManager.getDestination(link);
		ItemDelta linkParentPart = (ItemDelta) LinkManager.getInverseLink(link);

		if (sourceLink == null) {
			Logger.getLogger("CadseG_Sync").log(Level.SEVERE, "link has no source " + link);
			return;
		}
		if (sourceLink.getType() == WorkspaceCST.EXT_ITEM_TYPE) {
			return;
		}

		if (ispart) {
			if (linkParentPart != null) {
				ItemDelta sourceLinkParentPart = (ItemDelta) LinkManager.getSource(linkParentPart);
				ItemDelta destLinkParentPart = (ItemDelta) LinkManager.getDestination(linkParentPart);

				if (sourceLinkParentPart != destLink) {
					if (linkParentPart.getName().startsWith(PARENT_NAME)) {
						linkParentPart.delete(true);
					}
					linkParentPart = null;
				} else if (destLinkParentPart != sourceLink) {
					LinkManager.setDestination(linkParentPart, sourceLink);
				}
				if (destLink != null && linkParentPart != null) {
					linkParentPart.setName(PARENT_NAME + link.getName() + "_to_" + sourceLink.getName());
				}
				// force min and max
				LinkManager.setMinAttribute(linkParentPart, 0);
				LinkManager.setMaxAttribute(linkParentPart, 1);

				if (linkParentPart != null && linkParentPart.getName() != null
						&& linkParentPart.getName().startsWith(PARENT_NAME)) {
					LinkManager.setDevGeneratedAttribute(linkParentPart, true);
					linkParentPart.setAttribute(CadseRootCST.ITEM_TYPE_at_ITEM_HIDDEN_, true);
					linkParentPart.setAttribute(CadseRootCST.ITEM_TYPE_at_ITEM_READONLY_, true);
					LinkManager.setHiddenAttribute(linkParentPart, true);
				}
			}
			if (linkParentPart == null && destLink != null) {
				linkParentPart = wc.createItem(WorkspaceCST.LINK, destLink,
						WorkspaceCST.ABSTRACT_ITEM_TYPE_lt_ATTRIBUTES);
				linkParentPart.setName(PARENT_NAME + link.getName() + "_to_" + sourceLink.getName());
				LinkManager.setDestination(linkParentPart, sourceLink);
				LinkManager.setInverseLink(linkParentPart, link);
				LinkManager.setInverseLink(link, linkParentPart);
				LinkManager.setMinAttribute(linkParentPart, 0);
				LinkManager.setMaxAttribute(linkParentPart, 1);
				LinkManager.setHiddenAttribute(linkParentPart, true);
				LinkManager.setAggregationAttribute(linkParentPart, false);
				LinkManager.setMustBeInitializedAttribute(linkParentPart, false);

				LinkManager.setDevGeneratedAttribute(linkParentPart, true);
				linkParentPart.setAttribute(CadseRootCST.ITEM_TYPE_at_ITEM_HIDDEN_, true);
				linkParentPart.setAttribute(CadseRootCST.ITEM_TYPE_at_ITEM_READONLY_, true);
			}
		} else {
			if (linkParentPart != null && linkParentPart.getName() != null
					&& linkParentPart.getName().startsWith(PARENT_NAME)) {
				linkParentPart.delete(true);
			}
		}

	}

	private void syncDisplayFromAttribute(LogicalWorkspaceTransaction wc, ItemDelta item) throws CadseException {
		Collection<Item> fields = item.getIncomingItems(WorkspaceCST.FIELD_lt_ATTRIBUTE);
		for (Item f : fields) {
			try {
				syncDisplay(wc, f);
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Delete the manager associated at an item type
	 */
	@Override
	public void notifyDeletedItem(LogicalWorkspaceTransaction wc, ItemDelta item) throws CadseException {
		if (item.isInstanceOf(WorkspaceCST.ABSTRACT_ITEM_TYPE)) {
			ItemDelta manager = (ItemDelta) ManagerManager.getManagerFromItemType(item);
			if (manager != null && !manager.isDeleted()) {
				manager.delete(item.getDeleteOperation(), 0);
			}

			// delete link which point to this
			Collection<Item> links = item.getIncomingItems(WorkspaceCST.LINK_lt_DESTINATION);
			for (Item linkTothis : links) {
				((ItemDelta) linkTothis).delete(item.getDeleteOperation(), 0);
			}
		}

		if (item.getType() == WorkspaceCST.LINK) {
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

			if (LinkManager.isPart(item)) {
				Item inverseLink = LinkManager.getInverseLink(item);
				if (inverseLink != null) {
					inverseLink.delete(true);
				}
			}
		}

		if (item.isInstanceOf(WorkspaceCST.ATTRIBUTE)) {
			syncFieldFromAttribute(wc, item.getPartParent(false, true), item, true);
		}
	}

	/**
	 * manage double click
	 */
	@Override
	public void notifyDoubleClick(LogicalWorkspaceTransaction wc, ItemDelta item) {
		if (item.isInstanceOf(WorkspaceCST.ABSTRACT_ITEM_TYPE)) {
			Item manager = ManagerManager.getManagerFromItemType(item);
			if (manager != null) {
				IFile jf = manager.getContentItem().getMainMappingContent(IFile.class);
				openFile(jf);
			}
		}
		if (item.isInstanceOf(WorkspaceCST.PAGE)) {
			IFile jf = item.getContentItem().getMainMappingContent(IFile.class);
			openFile(jf);
		}
		if (item.isInstanceOf(WorkspaceCST.MANAGER)) {
			IFile jf = item.getContentItem().getMainMappingContent(IFile.class);
			openFile(jf);
		}

		if (item.isInstanceOf(WorkspaceCST.ENUM_TYPE)) {
			IType type = EnumTypeManager.getEnumQualifiedClass(ContextVariable.DEFAULT, item);
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

		if (link.getLinkType() == WorkspaceCST.FIELD_lt_ATTRIBUTE) {
			ItemDelta field = link.getSource();
			syncDisplay(wc, field);
		}

		if (link.getLinkType() == WorkspaceCST.LINK_lt_DESTINATION) {
			ItemDelta linkType = link.getSource();
			if (LinkManager.isPart(linkType)) {
				syncLinkPart(wc, linkType, true);
			}
		}

		if (link.getLinkType() == WorkspaceCST.LINK_lt_INVERSE_LINK) {
			Item link1 = link.getSource();
			Item inverseLink = link.getResolvedDestination();
			Link inverseLink_link = LinkManager.getInverseLinkLink(inverseLink);
			if (inverseLink_link == null) {
				inverseLink.createLink(WorkspaceCST.LINK_lt_INVERSE_LINK, link1);
			} else {
				// if (inverseLink_link.getResolvedDestination() != null) {
				// } else {
				// }
			}
		}
	}

	private void syncDisplay(LogicalWorkspaceTransaction wc, Item field) throws CadseException {
		Item attribute = FieldManager.getAttribute(field);

		if (attribute == null) {
			return; // error cannot find the attribute...
		}
		ItemType attribute_type = attribute.getType();
		ItemType goodDisplayType = getDisplayTypeFromAttribute(attribute, attribute_type);

		if (goodDisplayType != null) {
			ItemDelta displayField = (ItemDelta) FieldManager.getDisplay(field);
			ItemType displayType = displayField == null ? null : displayField.getType();

			if (displayType != null) {
				if (displayType != goodDisplayType) {
					displayField.delete(true);
					Link field_to_display = field.getOutgoingLink(WorkspaceCST.FIELD_lt_DISPLAY);
					if (field_to_display != null) {
						field_to_display.delete();
					}
					displayField = null;
				} else {
					// nothing
				}
			}
			if (displayField == null) {
				displayField = wc.createItemIfNeed(null, DisplayManager.DEFAULT_SHORT_NAME, goodDisplayType, field,
						WorkspaceCST.FIELD_lt_DISPLAY);
			}
			if (displayField.getType() == WorkspaceCST.DBROWSER) {
				sync_IC_and_MC(wc, displayField, getMCType(displayField), WorkspaceCST.DBROWSER_lt_MC,
						getICTypeForDBrowserUI(displayField), WorkspaceCST.DBROWSER_lt_IC);

			} else if (displayField.getType() == WorkspaceCST.DCOMBO) {
				sync_IC_and_MC(wc, displayField, getMCType(displayField), WorkspaceCST.DCOMBO_lt_MC,
						getICTypeForDCombo(displayField), WorkspaceCST.DCOMBO_lt_IC);
			} else if (displayField.getType() == WorkspaceCST.DTEXT) {
				sync_IC_and_MC(wc, displayField, getMCType(displayField), WorkspaceCST.DTEXT_lt_MC, null,
						WorkspaceCST.DCOMBO_lt_IC);
			} else if (displayField.getType() == WorkspaceCST.DLIST) {
				if (displayField.isAdded()) {
					DListManager.setEditableButtonAttribute(displayField, true);
				}
				sync_IC_and_MC(wc, displayField, getMCType(displayField), WorkspaceCST.DLIST_lt_MC,
						getICTypeForDList(displayField), WorkspaceCST.DLIST_lt_IC);
			} else if (displayField.getType() == WorkspaceCST.DCHECK_BOX) {
				sync_IC_and_MC(wc, displayField, getMCType(displayField), WorkspaceCST.DCHECK_BOX_lt_MC, null,
						WorkspaceCST.DCHECK_BOX_lt_IC);
			}
		}
	}

	@Override
	public void notifyDeletedLink(LogicalWorkspaceTransaction wc, LinkDelta link) throws CadseException {
		if (link.getLinkType() == WorkspaceCST.FIELD_lt_ATTRIBUTE) {
			ItemDelta field = link.getSource();
			Item display = FieldManager.getDisplay(field);
			if (display instanceof ItemDelta) {
				display.delete(false);
			}
		}
		if (link.getLinkType() == WorkspaceCST.LINK_lt_DESTINATION && link.getDestination().isDeleted()) {
			// the destination is deleted, the link must be delete
			link.getSource().delete(link.getDeleteOperation(), 0);
		}
	}

	private ItemType getDisplayTypeFromAttribute(Item attribute, ItemType attribute_type) throws CadseException {

		if (AttributeManager.isIsListAttribute(attribute)) {
			return WorkspaceCST.DLIST;

		} else {
			if (attribute_type == WorkspaceCST.BOOLEAN) {
				return WorkspaceCST.DCHECK_BOX;
			} else if (attribute_type == WorkspaceCST.DOUBLE) {
				return WorkspaceCST.DTEXT;
			} else if (attribute_type == WorkspaceCST.ENUM) {
				return WorkspaceCST.DBROWSER;
			} else if (attribute_type == WorkspaceCST.INTEGER) {
				return WorkspaceCST.DTEXT;
			} else if (attribute_type == WorkspaceCST.LINK) {
				final int max = LinkManager.getMaxAttribute(attribute);
				if (max == 0 || max == 1) {
					return WorkspaceCST.DBROWSER;
				} else {
					return WorkspaceCST.DLIST;
				}
			} else if (attribute_type == WorkspaceCST.STRING) {
				return WorkspaceCST.DTEXT;
			} else if (attribute_type == WorkspaceCST.DATE) {
				return WorkspaceCST.DTEXT;
			} else if (attribute_type == WorkspaceCST.LONG) {
				return WorkspaceCST.DTEXT;
			} else if (attribute_type == WorkspaceCST.URL) {
				return WorkspaceCST.DBROWSER;
			} else if (attribute_type == WorkspaceCST.UUID) {
				return WorkspaceCST.DTEXT;
			}
		}
		return null;
	}

	/**
	 * Do create field.
	 * 
	 * @param attribute
	 *            the attribute
	 * @param creationPage
	 *            the page
	 * 
	 * @return the item
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	private void syncFieldFromAttribute(LogicalWorkspaceTransaction wc, ItemDelta itemType, ItemDelta attribute,
			boolean force) throws CadseException {
		if (attribute.isDeleted()) {
			Item pc1 = getFirstCreationPage(wc, itemType, false);
			Item pm1 = getFirstModificationPage(wc, itemType, false);
			Item field_pc1 = getFieldFromAttribute(attribute, pc1);
			if (field_pc1 != null) {
				field_pc1.delete(true);
			}
			Item field_pm1 = getFieldFromAttribute(attribute, pm1);
			if (field_pm1 != null) {
				field_pm1.delete(true);
			}
			return;
		}
		Item pc1 = getFirstCreationPage(wc, itemType, false);
		Item pm1 = getFirstModificationPage(wc, itemType, true);
		Item field_pc1 = getFieldFromAttribute(attribute, pc1);
		Item field_pm1 = getFieldFromAttribute(attribute, pm1);
		if (AttributeManager.isClassAttributeAttribute(attribute)) {
			if (field_pc1 != null) {
				field_pc1.delete(false);
			}
			if (field_pm1 != null) {
				field_pm1.delete(false);
			}
			return;
		}

		String shortName = doShortName(attribute);
		String label = attribute.getName();
		if (field_pm1 != null) {
			field_pm1.setName(shortName);
			field_pm1.setAttribute(WorkspaceCST.FIELD_at_LABEL_, label);
		}
		if (field_pc1 != null) {
			field_pc1.setName(shortName);
			field_pc1.setAttribute(WorkspaceCST.FIELD_at_LABEL_, label);
		}
		if (force || AttributeManager.isMustBeInitializedAttribute(attribute)) {
			if (field_pc1 == null) {
				// create la page de creation if need
				if (pc1 == null) {
					pc1 = getFirstCreationPage(wc, itemType, true);
				}

				// create field in creation page if the field must be
				// initialized at creation time or force flag is true
				field_pc1 = doCreateField(wc, itemType, attribute, pc1);
			}
		} else {
			if (field_pc1 != null && field_pc1 instanceof ItemDelta) {
				if (((ItemDelta) field_pc1).isAdded()) {
					field_pc1.delete(false);
				}
			}
		}
		if (shortName != null && shortName.startsWith(PARENT_NAME)) {
			if (field_pm1 != null) {
				field_pm1.delete(true);
			}
			return;
		}
		if (field_pm1 == null) {
			// create field in modification page
			field_pm1 = doCreateField(wc, itemType, attribute, pm1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createdItem(fr.imag.adele
	 *      .cadse.core.Item)
	 */
	private void sync_IC_and_MC(LogicalWorkspaceTransaction wc, Item dbrowser, ItemType mcType, LinkType display_mc,
			ItemType icType, LinkType display_ic) throws CadseException {

		sync_IC_and_MC(wc, dbrowser, DisplayManager.MC_DEFAULT_SHORT_NAME, DisplayManager.getItemMC(dbrowser), mcType,
				display_mc);
		sync_IC_and_MC(wc, dbrowser, DisplayManager.IC_DEFAULT_SHORT_NAME, DisplayManager.getItemIC(dbrowser), icType,
				display_ic);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createdItem(fr.imag.adele
	 *      .cadse.core.Item)
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
	 * @see fede.workspace.model.manager.DefaultItemManager#createdItem(fr.imag.adele
	 *      .cadse.core.Item)
	 */
	private ItemType getMCType(Item displayItem) throws CadseException {

		Item field = displayItem.getPartParent();
		Item attribute = FieldManager.getAttribute(field);

		if (attribute == null) {
			return null;
		}
		ItemType attribute_type = attribute.getType();
		if (AttributeManager.isIsListAttribute(attribute)) {
			if (attribute_type == WorkspaceCST.ENUM) {
				return WorkspaceCST.MC_STRING_LIST_TO_ENUM_LIST;
			} else if (attribute_type == WorkspaceCST.LINK) {
				return WorkspaceCST.LINK_MODEL_CONTROLLER;
			} else if (attribute_type == WorkspaceCST.STRING) {
				return WorkspaceCST.LIST_OF_STRING_MODEL_CONTROLLER;
			}
			return WorkspaceCST.LIST_OF_STRING_MODEL_CONTROLLER;
		} else {
			if (attribute_type == WorkspaceCST.BOOLEAN) {
				return WorkspaceCST.STRING_TO_BOOLEAN_MODEL_CONTROLLER;
			} else if (attribute_type == WorkspaceCST.DOUBLE) {
				return null;
			} else if (attribute_type == WorkspaceCST.ENUM) {
				return WorkspaceCST.STRING_TO_ENUM_MODEL_CONTROLLER;
			} else if (attribute_type == WorkspaceCST.LINK) {
				if (displayItem.getType() == WorkspaceCST.DCHECK_BOX) {
					return WorkspaceCST.MC_LINK_TO_BOOLEAN;
				}
				return WorkspaceCST.LINK_MODEL_CONTROLLER;
			} else if (attribute_type == WorkspaceCST.STRING) {
				return null;
			} else if (attribute.getType() == WorkspaceCST.INTEGER) {
				return WorkspaceCST.INT_MODEL_CONTROLLER;
			} else if (attribute.getType() == WorkspaceCST.DATE) {
				return WorkspaceCST.MC_DATE;
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
			if (attribute_type == WorkspaceCST.ENUM) {
				return WorkspaceCST.IC_ENUM_FOR_LIST;
			} else if (attribute_type == WorkspaceCST.LINK) {
				return WorkspaceCST.IC_LINK_FOR_BROWSER_COMBO_LIST;
			} else if (attribute_type == WorkspaceCST.STRING) {
				return WorkspaceCST.IC_STRING_LIST_FOR_LIST;
			}
			return WorkspaceCST.IC_STRING_LIST_FOR_LIST;
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
			if (attribute_type == WorkspaceCST.BOOLEAN) {
				return null;
			} else if (attribute_type == WorkspaceCST.DOUBLE) {
				return null;
			} else if (attribute_type == WorkspaceCST.ENUM) {
				return WorkspaceCST.IC_ENUM_FOR_BROWSER_COMBO;

			} else if (attribute_type == WorkspaceCST.INTEGER) {
				return null;

			} else if (attribute_type == WorkspaceCST.LINK) {
				Item itemtypedest = LinkManager.getDestination(attribute);

				Item[] incomingLinkType = ItemTypeManager.getIncomingLinkTypesOfPart(itemtypedest);

				if (incomingLinkType.length == 1 && incomingLinkType[0] != attribute) {
					return WorkspaceCST.IC_PART_LINK_FOR_BROWSER_COMBO_LIST;
				} else {
					return WorkspaceCST.IC_LINK_FOR_BROWSER_COMBO_LIST;
				}

			} else if (attribute_type == WorkspaceCST.STRING) {

			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fede.workspace.model.manager.DefaultItemManager#createdItem(fr.imag.adele
	 *      .cadse.core.Item)
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
			if (attribute_type == WorkspaceCST.BOOLEAN) {
				return null;
			} else if (attribute_type == WorkspaceCST.DOUBLE) {
				return null;
			} else if (attribute_type == WorkspaceCST.ENUM) {
				return WorkspaceCST.IC_ENUM_FOR_BROWSER_COMBO;
			} else if (attribute_type == WorkspaceCST.INTEGER) {
				return null;
			} else if (attribute_type == WorkspaceCST.LINK) {
				return WorkspaceCST.IC_LINK_FOR_BROWSER_COMBO_LIST;
			} else if (attribute_type == WorkspaceCST.STRING) {
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

	private Item getFieldFromAttribute(Item attribute, Item p1) {
		if (p1 == null) {
			return null;
		}

		Collection<Item> fields = getFieldsFromAttribute(attribute);
		if (fields == null || fields.size() == 0) {
			return null;
		}
		for (Item f : fields) {
			if (f.getPartParent() == p1) {
				return f;
			}
		}
		return null;
	}

	private Collection<Item> getFieldsFromAttribute(Item attribute) {
		return attribute.getIncomingItems(WorkspaceCST.FIELD_lt_ATTRIBUTE);
	}

	/**
	 * Do create field.
	 * 
	 * @param attribute
	 *            the attribute
	 * @param page
	 *            the page
	 * 
	 * @return the new field
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	public static ItemDelta doCreateField(LogicalWorkspaceTransaction wc, Item itemType, Item attribute, Item page)
			throws CadseException {
		// Item itemType = PageManager.getItemTypeFromPage(page);
		if (AttributeManager.isClassAttributeAttribute(attribute)) {
			return null;
		}

		String shortName = doShortName(attribute);
		String label = attribute.getName();
		EPosLabel pos = EPosLabel.defaultpos;
		return wc.createItemIfNeed(null, shortName, WorkspaceCST.FIELD, page, WorkspaceCST.PAGE_lt_FIELDS,
				WorkspaceCST.FIELD_lt_ATTRIBUTE, attribute, WorkspaceCST.FIELD_at_LABEL_, label,
				WorkspaceCST.FIELD_at_POSITION_, pos);
	}

	/**
	 * Cherche la premier page r�solu. Si il y en a aucune, il en cree une.
	 * 
	 * @param modificationDialog
	 *            the modification dialog
	 * 
	 * @return the or create first page
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	public Item getFirstModificationPage(LogicalWorkspaceTransaction wc, Item itemType, boolean createIt)
			throws CadseException {

		Item modificationDialog = ItemTypeManager.getModificationDialog(itemType);
		if (modificationDialog == null) {
			if (!createIt) {
				return null;
			}
			modificationDialog = createModificationDialog(wc, itemType);
		}

		for (Link l : modificationDialog.getOutgoingLinks()) {
			// Select link has kind Part and destination.
			if (l.getLinkType() == WorkspaceCST.MODIFICATION_DIALOG_lt_PAGES) {
				// if l is resovled, take this destination to return list.
				if (l.isLinkResolved()) {
					return l.getResolvedDestination();
				}
			}
		}
		if (!createIt) {
			return null;
		}

		return createFirstModificationPage(wc, modificationDialog, false);
	}

	/**
	 * Cherche la premier page r�solu. Si il y en a aucune, il en cree une.
	 * 
	 * @param modificationDialog
	 *            the modification dialog
	 * 
	 * @return the first page or null if not found and createIt is false
	 * 
	 * @throws CadseException
	 *             the melusine exception
	 */
	public Item getFirstCreationPage(LogicalWorkspaceTransaction wc, Item itemType, boolean createIt)
			throws CadseException {

		Item creationDialog = ItemTypeManager.getCreationDialog(itemType);
		if (creationDialog == null) {
			if (!createIt) {
				return null;
			}
			creationDialog = createCreationDialog(wc, itemType);
		}

		for (Link l : creationDialog.getOutgoingLinks()) {
			// Select link has kind Part and destination.
			if (l.getLinkType() == WorkspaceCST.CREATION_DIALOG_lt_PAGES) {
				// if l is resovled, take this destination to return list.
				if (l.isLinkResolved()) {
					final Item page = l.getResolvedDestination();

					if (PageManager.isIsRemovedAttribute(page)) {
						continue;
					}
					return page;
				}
			}
		}
		if (!createIt) {
			return null;
		}

		return createFirstCreationPage(wc, creationDialog);
	}

	@Override
	public void notifyLoadedItem(LogicalWorkspaceTransaction workspaceLogiqueWorkingCopy, List<ItemDelta> loadedItems) {
		for (ItemDelta oper : loadedItems) {
			if (oper.getType() != WorkspaceCST.FIELD) {
				continue;
			}
			tryToRecreateAttributeLink(oper);
		}

		for (ItemDelta oper : loadedItems) {
			if (oper.getType() != WorkspaceCST.LINK) {
				continue;
			}
			if (LinkManager.isPart(oper)) {
				try {
					syncLinkPart(workspaceLogiqueWorkingCopy, oper, true);
				} catch (CadseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		for (ItemDelta oper : loadedItems) {
			if (oper.getType() != WorkspaceCST.CREATION_DIALOG) {
				continue;
			}
			String dv = oper.getAttribute(WorkspaceCST.CREATION_DIALOG_at_DEFAULT_SHORT_NAME_);
			if (dv != null && dv.length() != 0) {
				String gv = oper.getAttribute(WorkspaceCST.CREATION_DIALOG_at_GENERATE_AUTOMATIC_SHORT_NAME_);
				if (gv != null && gv.length() != 0) {
					_logger.log(Level.SEVERE, "Cannot set generated value to " + dv + " for item "
							+ oper.getDisplayName(), " : gv exists : " + gv);
				} else {
					try {
						oper.setAttribute(WorkspaceCST.CREATION_DIALOG_at_GENERATE_AUTOMATIC_SHORT_NAME_, dv);

					} catch (CadseException e) {
						_logger.log(Level.SEVERE, "Cannot set generated value to " + dv + " for item "
								+ oper.getDisplayName(), e);
					}
				}
			}
			try {
				oper.setAttribute(WorkspaceCST.CREATION_DIALOG_at_DEFAULT_SHORT_NAME_, null);
			} catch (CadseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			for (ItemDelta oper : loadedItems) {
				Collection<LinkDelta> links = oper.getOutgoingLinkOperations();
				for (LinkDelta linkDelta : links) {
					if (linkDelta.getLinkType() == CadseRootCST.ITEM_TYPE_lt_MODIFIED_ATTRIBUTES) {
						final ItemDelta destination = linkDelta.getDestination();
						if (destination == null
								|| destination.getType() == CadseRootCST.UNRESOLVED_ATTRIBUTE_TYPE
								|| (destination.getName().startsWith("#parent:") && destination.getType() == CadseRootCST.LINK_DEFINITION_ATTIBUTE_TYPE)) {
							try {
								linkDelta.delete();
								System.out.println("cadseg remove " + linkDelta);
							} catch (CadseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							System.out.println("cadseg ok " + linkDelta + "\n  dest= " + destination + "\n  dest type="
									+ destination.getType());
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
					field.createLink(WorkspaceCST.FIELD_lt_ATTRIBUTE, attribute);
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
				try {
					compositeitem.setName(itemType.getName() + "-composite");
				} catch (CadseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
						WorkspaceCST.COMPOSITE_ITEM_TYPE, buildmodel, WorkspaceCST.BUILD_MODEL_lt_COMPOSITE_TYPES);
				compositeitem.createLink(WorkspaceCST.COMPOSITE_ITEM_TYPE_lt_ITEM_TYPE, itemType);
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

	/**
	 * Migrate item type.
	 * 
	 * @param createDialog
	 *            the create dialog
	 * @param newCxt
	 *            the new cxt
	 * @param oldCxt
	 *            the old cxt
	 */
	public static void migrateCreationDialogManager(ItemDelta createDialog, ContextVariable newCxt,
			ContextVariable oldCxt) {
		Collection<Item> pages = CreationDialogManager.getPages(createDialog);
		JavaFileContentManager cm = (JavaFileContentManager) createDialog.getContentItem();
		if (cm != null) {
			cm.migrateContentItem(createDialog, newCxt, oldCxt);
		}
		for (Item p : pages) {
			migratePage((ItemDelta) p, newCxt, oldCxt);
		}
	}

	/**
	 * Migrate item type.
	 * 
	 * @param modificationDialog
	 *            the modification dialog
	 * @param newCxt
	 *            the new cxt
	 * @param oldCxt
	 *            the old cxt
	 */
	public static void migrateModificationDialogManager(Item modificationDialog, ContextVariable newCxt,
			ContextVariable oldCxt) {
		Collection<Item> pages = ModificationDialogManager.getPages(modificationDialog);
		for (Item p : pages) {
			migratePage((ItemDelta) p, newCxt, oldCxt);
		}
	}

	// old project
	// new project
	// old package
	// new cmlppackage

	@Override
	public void notifyMigratePartLink(LogicalWorkspaceTransaction wc, ItemDelta childItem, ItemDelta newPartParent,
			LinkType lt, LinkDelta newPartLink, LinkDelta oldPartLink) throws CadseException {
		if (childItem.getType() == WorkspaceCST.ITEM_TYPE) {
			ItemDelta itemtype = childItem;
			// L'itemtype migre dans un nouveau cadse?
			ItemDelta manager = (ItemDelta) ManagerManager.getManagerFromItemType(itemtype);
			Item oldCadseDefinition = manager.getPartParent(WorkspaceCST.CADSE_DEFINITION);
			Item newCadseDefinition = newPartParent.getPartParent(WorkspaceCST.CADSE_DEFINITION);
			if (oldCadseDefinition != newCadseDefinition) {
				// new cadse, you must migrate the manager...
				manager.migratePartLink(CadseDefinitionManager.getMapping(newCadseDefinition),
						WorkspaceCST.MAPPING_MODEL_lt_MANAGERS);
			}
			ContextVariable oldContext = wc.getOldContext();
			ContextVariable newContext = wc.getNewContext();

			migrateManager(manager, itemtype, newContext, oldContext);
			ItemDelta createDialog = (ItemDelta) ItemTypeManager.getCreationDialog(itemtype);
			if (createDialog != null) {
				migrateCreationDialogManager(createDialog, newContext, oldContext);
			}
			Item modificationDialog = ItemTypeManager.getModificationDialog(itemtype);
			if (modificationDialog != null) {
				migrateModificationDialogManager(modificationDialog, newContext, oldContext);
			}
		}
		if (childItem.getType() == WorkspaceCST.MANAGER) {
			ItemDelta manager = childItem;
			ContentItem cm = manager.getContentItem();
			// Item cadseDefinition =
			// manager.getPartParent(WorkspaceCST.CADSE_DEFINITION);
			ContextVariable oldContext = wc.getOldContext();
			ContextVariable newContext = wc.getNewContext();
			cm.migrateContentItem(manager, newContext, oldContext);
		}

	}
}