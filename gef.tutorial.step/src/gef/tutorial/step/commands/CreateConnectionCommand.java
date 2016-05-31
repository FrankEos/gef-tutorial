
package gef.tutorial.step.commands;

import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.AbstractConnectionModel;
import gef.tutorial.step.model.HelloModel;

public class CreateConnectionCommand extends Command {
    private HelloModel source, target;

    private AbstractConnectionModel connection;

    public boolean canExecute() {
        if (source == null || target == null) {
            return false;
        }

        if (source.equals(target)) {
            return false;
        }

        return true;
    }

    @Override
    public void execute() {
        connection.attachSource();
        connection.attachTarget();
    }

    public void setConnection(Object model) {
        connection = (AbstractConnectionModel) model;
    }

    public void setSource(Object model) {
        this.source = (HelloModel) model;
        connection.setSource(source);
    }

    public void setTarget(Object model) {
        this.target = (HelloModel) model;
        connection.setTarget(target);
    }

    @Override
    public void undo() {
        connection.dettachSource();
        connection.dettachTarget();
    }
}
