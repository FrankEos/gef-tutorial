
package gef.tutorial.step.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.AbstractConnectionModel;

public class DeleteBendpointCommand extends Command {

    private AbstractConnectionModel connection;

    private Point oldLocation;

    private int index;

    @Override
    public void execute() {

        oldLocation = (Point) connection.getBendpoints().get(index);
        connection.removeBendpoint(index);

    }

    public void setConnectionModel(Object connection) {
        this.connection = (AbstractConnectionModel) connection;
    }

    public void setIndex(int i) {
        index = i;
    }

    @Override
    public void undo() {
        connection.addBendPoint(index, oldLocation);
    }

}
