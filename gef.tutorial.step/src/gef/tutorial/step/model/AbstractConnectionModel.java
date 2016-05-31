
package gef.tutorial.step.model;

abstract public class AbstractConnectionModel {
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

    public void attchSource() {
        if (!source.getModelSourceConnections().contains(this)) {
            source.addSourceConnection(this);
        }
    }

    public void attchTarget() {
        if (!target.getModelTargetConnections().contains(this)) {
            target.addTargetConnection(this);
        }
    }

    public void detachSource() {
        source.removeSourceConnection(this);
    }

    public void detachTarget() {
        target.removeTargetConnection(this);
    }

}
