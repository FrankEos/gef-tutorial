
package gef.tutorial.step.actions;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.AlignmentRetargetAction;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

public class DiagramActionBarContributor extends ActionBarContributor {

    @Override
    protected void buildActions() {
        addRetargetAction(new UndoRetargetAction());
        addRetargetAction(new RedoRetargetAction());
        addRetargetAction(new DeleteRetargetAction());

        // 缩放工具
        addRetargetAction(new ZoomInRetargetAction());
        addRetargetAction(new ZoomOutRetargetAction());

        // 对齐
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.LEFT));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.CENTER));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.RIGHT));

        addRetargetAction(new AlignmentRetargetAction(PositionConstants.TOP));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.MIDDLE));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.BOTTOM));
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

        menuManager.add(getAction(GEFActionConstants.ZOOM_IN));
        menuManager.add(getAction(GEFActionConstants.ZOOM_OUT));

        // 缩放工具框
        // menuManager.add(new ZoomComboContributionItem(getPage()));

        menuManager.add(getAction(GEFActionConstants.ALIGN_LEFT));
        menuManager.add(getAction(GEFActionConstants.ALIGN_CENTER));
        menuManager.add(getAction(GEFActionConstants.ALIGN_RIGHT));
        menuManager.add(getAction(GEFActionConstants.ALIGN_TOP));
        menuManager.add(getAction(GEFActionConstants.ALIGN_MIDDLE));
        menuManager.add(getAction(GEFActionConstants.ALIGN_BOTTOM));
    }

}
