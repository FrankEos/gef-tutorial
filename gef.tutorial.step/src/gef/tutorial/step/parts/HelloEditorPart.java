
package gef.tutorial.step.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.viewers.TextCellEditor;

import gef.tutorial.step.model.HelloModel;
import gef.tutorial.step.policies.CustomComponentEditPolicy;
import gef.tutorial.step.policies.CustomDirectEditPolicy;
import gef.tutorial.step.policies.CustomGraphicalNodeEditPolicy;

public class HelloEditorPart extends EditPartWithListener implements NodeEditPart {

    private CustomDirectEditManager customDirectEditManager = null;

    @Override
    protected IFigure createFigure() {
        HelloModel model = (HelloModel) getModel();

        Label label = new Label();
        label.setText(model.getText());
        label.setBorder(new CompoundBorder(new LineBorder(), new MarginBorder(3)));
        label.setBackgroundColor(ColorConstants.listBackground);
        label.setOpaque(true);
        return label;
    }

    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new CustomComponentEditPolicy());

        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new CustomDirectEditPolicy());

        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new CustomGraphicalNodeEditPolicy());
    }

    @Override
    protected void refreshVisuals() {
        Rectangle constraint = ((HelloModel) getModel()).getConstraint();

        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), constraint);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(HelloModel.P_CONSTRAINT)) {
            refreshVisuals();
        } else if (evt.getPropertyName().equals(HelloModel.P_TEXT)) {
            Label label = (Label) getFigure();
            label.setText((String) evt.getNewValue());
        } else if (evt.getPropertyName().equals(HelloModel.P_SOURCE_CONN)) {
            refreshSourceConnections();
        } else if (evt.getPropertyName().equals(HelloModel.P_TARGET_CONN)) {
            refreshTargetConnections();
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected List getModelSourceConnections() {
        return ((HelloModel) getModel()).getModelSourceConnections();
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected List getModelTargetConnections() {
        return ((HelloModel) getModel()).getModelTargetConnections();
    }

    @Override
    public void performRequest(Request req) {
        if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
            performDirectEdit();
            return;
        }

        super.performRequest(req);

    }

    private void performDirectEdit() {
        if (customDirectEditManager == null) {
            customDirectEditManager = new CustomDirectEditManager(this, TextCellEditor.class,
                    new CustomCellEditorLocator(getFigure()));
        }

        customDirectEditManager.show();
    }

    @Override
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart arg0) {
        return new ChopboxAnchor(getFigure());
    }

    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request arg0) {
        return new ChopboxAnchor(getFigure());
    }

    @Override
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart arg0) {
        return new ChopboxAnchor(getFigure());
    }

    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request arg0) {
        return new ChopboxAnchor(getFigure());
    }
}
