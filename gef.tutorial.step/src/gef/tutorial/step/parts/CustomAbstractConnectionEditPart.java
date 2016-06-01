
package gef.tutorial.step.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

import gef.tutorial.step.model.AbstractConnectionModel;
import gef.tutorial.step.policies.CustomBendpointEditPolicy;
import gef.tutorial.step.policies.CustomConnectionEditPolicy;
import gef.tutorial.step.policies.CustomConnectionEndpointEditPolicy;

import org.eclipse.draw2d.geometry.Point;

abstract public class CustomAbstractConnectionEditPart extends AbstractConnectionEditPart
        implements PropertyChangeListener {

    @Override
    protected IFigure createFigure() {

        PolylineConnection connection = new PolylineConnection();
        connection.setConnectionRouter(new BendpointConnectionRouter());

        return connection;
    }

    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.CONNECTION_ROLE, new CustomConnectionEditPolicy());
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new CustomConnectionEndpointEditPolicy());
        installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new CustomBendpointEditPolicy());
    }

    @Override
    public void activate() {
        super.activate();

        ((AbstractConnectionModel) getModel()).addPropertyChangeListener(this);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        ((AbstractConnectionModel) getModel()).removePropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(AbstractConnectionModel.P_BEND_POINT)) {
            refreshBendpoints();
        }

    }

    @SuppressWarnings({
            "unchecked", "rawtypes"
    })
    protected void refreshBendpoints() {

        List bendpoints = ((AbstractConnectionModel) getModel()).getBendpoints();

        List constraint = new ArrayList();

        for (int i = 0; i < bendpoints.size(); i++) {
            constraint.add(new AbsoluteBendpoint((Point) bendpoints.get(i)));
        }

        getConnectionFigure().setRoutingConstraint(constraint);
    }

    @Override
    protected void refreshVisuals() {
        refreshBendpoints();
    }
}
