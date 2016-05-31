
package gef.tutorial.step.actions;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

public class DiagramActionBarContributor extends ActionBarContributor {

    @Override
    protected void buildActions() {
        addRetargetAction(new UndoRetargetAction());
        addRetargetAction(new RedoRetargetAction());
        addRetargetAction(new DeleteRetargetAction());
    }

    @Override
    protected void declareGlobalActionKeys() {
        // TODO Auto-generated method stub

    }

    @Override
    public void contributeToMenu(IMenuManager menuManager) {
        super.contributeToMenu(menuManager);
        menuManager.add(getAction(ActionFactory.UNDO.getId()));
        menuManager.add(getAction(ActionFactory.REDO.getId()));
        menuManager.add(getAction(ActionFactory.DELETE.getId()));
    }

}
