
package gef.tutorial.step.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;

abstract public class AbstractConnectionModel extends AbstractModel {
    private HelloModel source, target;

    public HelloModel getSource() {
        return source;
    }

    public void setSource(HelloModel source) {
        this.source = source;
    }

    public HelloModel getTarget() {
        return target;
    }

    public void setTarget(HelloModel target) {
        this.target = target;
    }

    public void attachSource() {
        if (!source.getModelSourceConnections().contains(this)) {
            source.addSourceConnection(this);
        }
    }

    public void attachTarget() {
        if (!target.getModelTargetConnections().contains(this)) {
            target.addTargetConnection(this);
        }
    }

    public void dettachSource() {
        source.removeSourceConnection(this);
    }

    public void dettachTarget() {
        target.removeTargetConnection(this);
    }

    // bend connection point begin
    @SuppressWarnings("rawtypes")
    private List bendpoints = new ArrayList();

    public static final String P_BEND_POINT = "_bend_point";

    @SuppressWarnings("unchecked")
    public void addBendPoint(int index, Point point) {
        bendpoints.add(index, point);
        firePropertyChange(P_BEND_POINT, null, null);
    }

    @SuppressWarnings("rawtypes")
    public List getBendpoints() {
        return bendpoints;
    }

    public void removeBendpoint(int index) {
        bendpoints.remove(index);
        firePropertyChange(P_BEND_POINT, null, null);
    }
    
    @SuppressWarnings("unchecked")
    public void replaceBendpoint(int index, Point point) {
        bendpoints.set(index, point);
    }
    // bend connection point end

}
