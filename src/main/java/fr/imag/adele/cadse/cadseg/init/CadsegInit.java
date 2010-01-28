package fr.imag.adele.cadse.cadseg.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import fr.imag.adele.cadse.cadseg.managers.CadseG_WLWCListener;
import fr.imag.adele.cadse.cadseg.managers.CadseG_WorkspaceListener;
import fr.imag.adele.cadse.cadseg.managers.attributes.AttributeSpaceKeyType;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ExtItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.PageSpaceKeyType;
import fr.imag.adele.cadse.cadseg.menu.ItemActionGroup;
import fr.imag.adele.cadse.cadseg.menu.TeamWorkMenuActionContributor;
import fr.imag.adele.cadse.cadseg.migration.MigrationInit;
import fr.imag.adele.cadse.cadseg.operation.WorkspaceActionContributor;
import fr.imag.adele.cadse.cadseg.pages.PageInit;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.IGenerateContent;
import fr.imag.adele.cadse.core.InitAction;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.WorkspaceListener;
import fr.imag.adele.cadse.core.impl.AbstractLinkTypeManager;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.key.DefaultKeyDefinitionImpl;
import fr.imag.adele.cadse.core.transaction.delta.ImmutableItemDelta;
import fr.imag.adele.cadse.core.transaction.delta.ImmutableWorkspaceDelta;
import fr.imag.adele.cadse.core.var.ContextVariableImpl;

public class CadsegInit implements InitAction {

	@Override
	public void init() {
		CadseGCST.CADSE.setHasQualifiedNameAttribute(false);
		CadseGCST.CADSE.setKeyDefinition(new DefaultKeyDefinitionImpl(CadseGCST.CADSE, null));
		
		CadseGCST.ITEM_lt_INSTANCE_OF.setIsNatif(true);
		//CadseGCST.CADSE_DEFINITION.setKeyDefinition(new DefaultKeyDefinitionImpl(CadseGCST.CADSE_DEFINITION, null));
		new CadseG_WLWCListener();
		new CadseG_WorkspaceListener();
		CadseCore.theItem.addActionContributeur(new WorkspaceActionContributor());
		CadseCore.theItem.addActionContributeur(new TeamWorkMenuActionContributor());
		CadseCore.theItem.setIsAbstract(true);
		
		CadseGCST.CADSE.setIsAbstract(true);
		CadseGCST.CONTENT_ITEM.setIsAbstract(true);
		CadseGCST.LINK_TYPE_TYPE.setIsAbstract(true);
		CadseGCST.VIEW_DESCRIPTION.setIsAbstract(true);
		CadseGCST.UILISTENER.setIsAbstract(true);
		CadseGCST.RUNTIME_ITEM.setIsAbstract(true);
		
		CadseGCST.TYPE_DEFINITION.setHasNameAttribute(true);
		CadseGCST.TYPE_DEFINITION.setHasQualifiedNameAttribute(true);
		CadseGCST.TYPE_DEFINITION_lt_MODIFICATION_PAGES.setIsNatif(true);
		CadseGCST.TYPE_DEFINITION_lt_CREATION_PAGES.setIsNatif(true);
		CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_.setIsNatif(true);
		
		CadseGCST.PAGE.setHasQualifiedNameAttribute(false);
		CadseGCST.ATTRIBUTE.setHasNameAttribute(true);
		CadseGCST.ATTRIBUTE.setHasQualifiedNameAttribute(false);
		CadseGCST.DATA_MODEL.setHasNameAttribute(true);
		CadseGCST.DATA_MODEL.setHasQualifiedNameAttribute(false);
		CadseGCST.INTERACTION_CONTROLLER.setHasQualifiedNameAttribute(false);
		CadseGCST.MODEL_CONTROLLER.setHasQualifiedNameAttribute(false);
		CadseGCST.MENU_ACTION.setHasQualifiedNameAttribute(false);
		CadseGCST.MENU.setHasQualifiedNameAttribute(false);
		CadseGCST.FIELD.setHasQualifiedNameAttribute(false);

		
		CadseGCST.DATA_MODEL.setKeyDefinition(new DefaultKeyDefinitionImpl(
				CadseGCST.DATA_MODEL, CadseGCST.CADSE_DEFINITION));

		CadseGCST.TYPE_DEFINITION
				.setKeyDefinition(new DefaultKeyDefinitionImpl(
						CadseGCST.TYPE_DEFINITION, CadseGCST.CADSE) {
					@Override
					protected String convertName(String name) {
						if (name == null)
							return null;
						return name.toUpperCase();
					}

					@Override
					protected String getName(Item item) {
						return convertName(super.getName(item));
					}
				});

		//CadseGCST.EXT_ITEM_TYPE.setKeyDefinition(new DefaultKeyDefinitionImpl(
		//		CadseGCST.EXT_ITEM_TYPE, null));
		CadseGCST.PAGE.setKeyDefinition(new PageSpaceKeyType(CadseGCST.PAGE,
				CadseGCST.TYPE_DEFINITION));
		
		CadseGCST.ATTRIBUTE.setKeyDefinition(new AttributeSpaceKeyType(CadseGCST.ATTRIBUTE,
					CadseGCST.TYPE_DEFINITION));
		
		CadseGCST.FIELD.setKeyDefinition(new DefaultKeyDefinitionImpl(CadseGCST.FIELD, CadseGCST.TYPE_DEFINITION,
				CadseGCST.ITEM_at_NAME_, CadseGCST.FIELD_lt_ATTRIBUTE));

		CadseGCST.FIELD_lt_ATTRIBUTE.setManager(new AbstractLinkTypeManager() {
			@Override
			public Collection<Item> getSelectingDestination(Item field) {
				Item container = field.getPartParent();
				Item itemType = null;
				Collection<Item> allAttributes = new ArrayList<Item>();

				if (container.getType() == CadseGCST.EXT_ITEM_TYPE) {
					itemType = ExtItemTypeManager.getRefType(container);
					allAttributes.addAll(ExtItemTypeManager.getAttributes(container));
				} else if (container.getType() == CadseGCST.ITEM_TYPE) {
					itemType = container;
				}
				if (itemType != null) {
					Item[] ret = ItemTypeManager.getAllAttributes(null, itemType, null, true);
					allAttributes.addAll(Arrays.asList(ret));
				}
				return allAttributes;
			}
		});
		
		CadseGCST.VIEW.setKeyDefinition(new DefaultKeyDefinitionImpl(CadseGCST.VIEW, CadseGCST.CADSE_DEFINITION));

		try {
			PageInit.init();
			CadseGCST.ITEM.addActionContributeur(new ItemActionGroup());
		} catch (CadseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MigrationInit.init();
		
		//*****/
		WorkspaceListener listener = new WorkspaceListener() {

			@Override
			public void workspaceChanged(ImmutableWorkspaceDelta wd) {
				HashSet<Item> views = new HashSet<Item>();
				for (ImmutableItemDelta itemDelta : wd.getItems()) {
					if (itemDelta.isDeleted()) {
						continue;
					}
					Item item = itemDelta.getItem();
					Item view = item.getPartParent(CadseGCST.VIEW);
					if (view != null) {
						views.add(view);
					}
				}

				for (Item item : views) {
					((IGenerateContent) item.getContentItem()).generate(ContextVariableImpl.DEFAULT);
				}
			}

		};
		CadseCore.getLogicalWorkspace().addListener(listener, 0xFFFF);
	}

}
