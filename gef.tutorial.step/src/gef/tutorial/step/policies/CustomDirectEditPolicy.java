
package gef.tutorial.step.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

import gef.tutorial.step.commands.DirectEditCommand;

public class CustomDirectEditPolicy extends DirectEditPolicy {

    @Override
    protected Command getDirectEditCommand(DirectEditRequest req) {

        DirectEditCommand command = new DirectEditCommand();

        command.setModel(getHost().getModel());

        command.setText((String) req.getCellEditor().getValue());

        return command;

    }

    @Override
    protected void showCurrentEditValue(DirectEditRequest arg0) {
        // TODO Auto-generated method stub

    }

}
