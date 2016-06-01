
package gef.tutorial.step.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import gef.tutorial.step.commands.CreateConnectionCommand;
import gef.tutorial.step.commands.ReconnectConnectionCommand;

public class CustomGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest req) {

        CreateConnectionCommand command = (CreateConnectionCommand) req.getStartCommand();
        command.setTarget(getHost().getModel());

        return command;
    }

    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest req) {
        CreateConnectionCommand command = new CreateConnectionCommand();
        command.setConnection(req.getNewObject());
        command.setSource(getHost().getModel());
        req.setStartCommand(command);

        return command;
    }

    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest request) {
        ReconnectConnectionCommand command = new ReconnectConnectionCommand();
        command.setConnectionModel(request.getConnectionEditPart().getModel());

        command.setNewSource(getHost().getModel());

        return command;
    }

    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        ReconnectConnectionCommand command = new ReconnectConnectionCommand();
        command.setConnectionModel(request.getConnectionEditPart().getModel());

        command.setNewTarget(getHost().getModel());

        return command;
    }

}
