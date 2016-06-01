
package gef.tutorial.step.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import gef.tutorial.step.commands.DeleteConnectionCommand;

public class CustomConnectionEditPolicy extends ConnectionEditPolicy {

    @Override
    protected Command getDeleteCommand(GroupRequest req) {

        DeleteConnectionCommand command = new DeleteConnectionCommand();

        command.setConnectionModel(getHost().getModel());

        return command;
    }

}
