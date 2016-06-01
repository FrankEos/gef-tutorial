
package gef.tutorial.step.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.AbstractConnectionModel;

public class MoveBendpointCommand extends Command {

    private AbstractConnectionModel connection;

    private Point oldLocation, newLocation;

    private int index;

    @Override
    public void execute() {

        oldLocation = (Point) connection.getBendpoints().get(index);
        connection.replaceBendpoint(index, newLocation);

    }

    public void setConnection(Object connection) {
        this.connection = (AbstractConnectionModel) connection;
    }

    public void setIndex(int i) {
        index = i;
    }

    public void setNewLocation(Point loc) {
        this.newLocation = loc;
    }

    @Override
    public void undo() {
        connection.replaceBendpoint(index, oldLocation);
    }

}
