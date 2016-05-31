
package gef.tutorial.step.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.EditPolicy;

import gef.tutorial.step.model.ContentsModel;
import gef.tutorial.step.policies.CustomXYLayoutEditPolicy;

public class ContentsEditPart extends EditPartWithListener {

    @Override
    protected IFigure createFigure() {
        Layer figure = new Layer();
        figure.setLayoutManager(new XYLayout());
        return figure;
    }

    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new CustomXYLayoutEditPolicy());
    }

    @SuppressWarnings("rawtypes")
    protected List getModelChildren() {
        return ((ContentsModel) getModel()).getChildren();

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(ContentsModel.P_CHILDREN)) {
            refreshChildren();
        }
    }

}
