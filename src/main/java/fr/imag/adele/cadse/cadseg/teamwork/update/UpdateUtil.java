package fr.imag.adele.cadse.cadseg.teamwork.update;

import java.util.List;
import java.util.UUID;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.impl.internal.TWUtil;

/**
 * Methods used to compute list of operation to perform 
 * from requirements and impacts of an update operation.
 * 
 * @author Thomas
 *
 */
public class UpdateUtil {

	/**
	 * Computes impacts of requirements.
	 * 
	 * @param updateState update state containing update operation definition.
	 */
	public static void computeImpacts(UpdateState updateState) {
		UpdateDefinition definition = updateState.getDefinition();
		definition.clearImpacts();
		
		for (Operation op : definition.getRequirements()) {
			computeImpacts(op, updateState);
		}
	}

	private static void computeImpacts(Operation requirementOp, UpdateState updateState) {
		UUID itemId = requirementOp.getItemId();
		List<Operation> impacts = updateState.getDefinition().getImpacts();
		LogicalWorkspace lw = updateState.getTransaction();
		
		// add this operation
		Operation impactOp = requirementOp.clone();
		impactOp.addCause(requirementOp);
		updateState.getDefinition().getImpacts().add(impactOp);
		
		Item item = null;
		if (!impactOp.isImport()) {
			item = lw.getItem(impactOp.getItemId());
		}
		
		// manage coupled propagation
		if (impactOp.isRevert()) {
			RevertOperation revertOp = (RevertOperation) impactOp;
			
			for (Link link : item.getIncomingLinks()) {
				if (!TWUtil.isCoupled(link.getLinkType()))
					continue;
				
				Item srcItem = link.getSource();
				RevertOperation srcImpactOp = new RevertOperation(srcItem.getId(), false);
				srcImpactOp.addCause(impactOp);
				updateState.getDefinition().getImpacts().add(impactOp);
			}
			for (Link link : item.getOutgoingLinks()) {
				if (!TWUtil.isCoupled(link.getLinkType()))
					continue;
				
				Item destItem = link.getDestination();
				RevertOperation destImpactOp = new RevertOperation(destItem.getId(), false);
				destImpactOp.addCause(impactOp);
				updateState.getDefinition().getImpacts().add(destImpactOp);
			}
		}
		
		//TODO finish compute impacts
	}

	public static void computeOperationsToPerform(UpdateState updateState) {
		UpdateDefinition definition = updateState.getDefinition();
		
		for (Operation impactOp : definition.getImpacts()) {
			OpToPerform opToPerform = new OpToPerform(impactOp);
			
			//TODO order operations
			updateState.addOperationToPerform(opToPerform);
		}
	}

}