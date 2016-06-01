
package gef.tutorial.step.commands;

import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.AbstractConnectionModel;
import gef.tutorial.step.model.HelloModel;

public class ReconnectConnectionCommand extends Command {
    private HelloModel source, target;

    private AbstractConnectionModel connection;

    @Override
    public void execute() {

        connection.attachSource();
        connection.attachTarget();
    }

    public void setConnectionModel(Object model) {
        this.connection = (AbstractConnectionModel) model;
    }

    public void setNewSource(Object model) {
        source = (HelloModel) model;
        connection.setSource(source);
    }

    public void setNewTarget(Object model) {
        target = (HelloModel) model;
        connection.setTarget(target);
    }

    @Override
    public void undo() {
        connection.attachSource();
        connection.attachTarget();
    }
}
