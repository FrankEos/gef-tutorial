
package gef.tutorial.step.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class HelloModel extends AbstractModel {

    public static final String P_CONSTRAINT = "_constraint";

    public static final String P_TEXT = "_text";

    private String text = "node";

    private Rectangle constraint;

    public Rectangle getConstraint() {
        return constraint;
    }

    public void setConstraint(Rectangle constraint) {
        this.constraint = constraint;
        firePropertyChange(P_CONSTRAINT, null, constraint);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        firePropertyChange(P_TEXT, null, text);
    }

    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {

        IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
                new TextPropertyDescriptor(P_TEXT, "Greeting")
        };

        return descriptors;
    }

    @Override
    public Object getPropertyValue(Object id) {

        if (id.equals(P_TEXT)) {
            return text;
        }
        return null;
    }

    @Override
    public boolean isPropertySet(Object id) {
        if (id.equals(P_TEXT)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setPropertyValue(Object id, Object value) {
        if (id.equals(P_TEXT)) {
            setText((String) value);
        }
    }

    // connection begin
    public static final String P_SOURCE_CONN = "_source_connection";

    public static final String P_TARGET_CONN = "_target_connection";

    @SuppressWarnings("rawtypes")
    private List sourceConns = new ArrayList();

    @SuppressWarnings("rawtypes")
    private List targetConns = new ArrayList();

    @SuppressWarnings("unchecked")
    public void addSourceConnection(Object conn) {
        sourceConns.add(conn);
        firePropertyChange(P_SOURCE_CONN, null, null);
    }

    @SuppressWarnings("unchecked")
    public void addTargetConnection(Object conn) {
        targetConns.add(conn);
        firePropertyChange(P_TARGET_CONN, null, null);
    }

    public void removeSourceConnection(Object conn) {
        sourceConns.remove(conn);
        firePropertyChange(P_SOURCE_CONN, null, null);
    }

    public void removeTargetConnection(Object conn) {
        targetConns.remove(conn);
        firePropertyChange(P_TARGET_CONN, null, null);
    }

    @SuppressWarnings("rawtypes")
    public List getModelSourceConnections() {
        return sourceConns;
    }

    @SuppressWarnings("rawtypes")
    public List getModelTargetConnections() {
        return targetConns;
    }

    // connection end

}
