
package gef.tutorial.step.model;

import java.util.ArrayList;
import java.util.List;

public class ContentsModel extends AbstractModel {
    public static final String P_CHILDREN = "_children";

    @SuppressWarnings("rawtypes")
    private List children = new ArrayList();

    @SuppressWarnings("unchecked")
    public void addChild(Object child) {
        children.add(child);
        firePropertyChange(P_CHILDREN, null, null);
    }

    @SuppressWarnings("rawtypes")
    public List getChildren() {
        return children;
    }

    public void removeChild(Object child) {
        children.remove(child);
        firePropertyChange(P_CHILDREN, null, null);
    }
}
