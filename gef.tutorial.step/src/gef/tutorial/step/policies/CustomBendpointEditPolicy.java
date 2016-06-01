
package gef.tutorial.step.policies;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;

import gef.tutorial.step.commands.CreateBendpointCommand;
import gef.tutorial.step.commands.DeleteBendpointCommand;
import gef.tutorial.step.commands.MoveBendpointCommand;

public class CustomBendpointEditPolicy extends BendpointEditPolicy {

    @Override
    protected Command getCreateBendpointCommand(BendpointRequest request) {
        Point point = request.getLocation();

        getConnection().translateToRelative(point);

        CreateBendpointCommand command = new CreateBendpointCommand();
        command.setConnection(getHost().getModel());
        command.setLocation(point);
        command.setIndex(request.getIndex());

        return command;
    }

    @Override
    protected Command getDeleteBendpointCommand(BendpointRequest request) {
        DeleteBendpointCommand command = new DeleteBendpointCommand();
        command.setConnectionModel(getHost().getModel());
        command.setIndex(request.getIndex());
        return command;
    }

    @Override
    protected Command getMoveBendpointCommand(BendpointRequest request) {
        Point point = request.getLocation();

        getConnection().translateToRelative(point);

        MoveBendpointCommand command = new MoveBendpointCommand();
        command.setConnection(getHost().getModel());
        command.setNewLocation(point);
        command.setIndex(request.getIndex());

        return command;
    }

}
