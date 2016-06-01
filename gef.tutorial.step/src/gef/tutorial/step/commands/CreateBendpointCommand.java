
package gef.tutorial.step.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.AbstractConnectionModel;

public class CreateBendpointCommand extends Command {

    private AbstractConnectionModel connection;

    private Point location;

    private int index;

    @Override
    public void execute() {
        connection.addBendPoint(index, location);
    }

    public void setConnection(Object connection) {
        this.connection = (AbstractConnectionModel) connection;
    }

    public void setIndex(int i) {
        index = i;
    }

    public void setLocation(Point loc) {
        this.location = loc;
    }

    @Override
    public void undo() {
        connection.removeBendpoint(index);
    }
}
