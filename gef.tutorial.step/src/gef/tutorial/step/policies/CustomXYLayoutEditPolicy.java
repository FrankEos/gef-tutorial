
package gef.tutorial.step.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import gef.tutorial.step.commands.ChangeConstraintCommand;
import gef.tutorial.step.commands.CreateCommand;
import gef.tutorial.step.model.HelloModel;
import gef.tutorial.step.model.LineConnectionModel;

public class CustomXYLayoutEditPolicy extends XYLayoutEditPolicy {

    @Override
    protected Command createAddCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
        return null;
    }

    @Override
    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        ChangeConstraintCommand command = new ChangeConstraintCommand();
        command.setModel(child.getModel());
        command.setConstraint((Rectangle) constraint);

        return command;
    }

    @Override
    protected Command getCreateCommand(CreateRequest request) {
        Object model = request.getNewObject();

        Command cmd = null;
        if (model instanceof HelloModel) {

            Rectangle constraint = (Rectangle) getConstraintFor(request);

            HelloModel helloModel = (HelloModel) request.getNewObject();
            helloModel.setConstraint(constraint);
            CreateCommand command = new CreateCommand();
            command.setContentsModel(getHost().getModel());
            command.setHelloModel(helloModel);
            cmd = command;
        } else if (model instanceof LineConnectionModel) {
//            CreateConnectionCommand command = new CreateConnectionCommand();
//            cmd = command;
        }

        return cmd;
    }

    @Override
    protected Command getDeleteDependantCommand(Request request) {
        return null;
    }

    @Override
    public Command getCommand(Request request) {

        return super.getCommand(request);
    }
}
