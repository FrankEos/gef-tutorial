
package gef.tutorial.step.commands;

import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.AbstractConnectionModel;

public class DeleteConnectionCommand extends Command {

    private AbstractConnectionModel connection;

    @Override
    public void execute() {

        connection.dettachSource();
        connection.dettachTarget();
    }

    public void setConnectionModel(Object model) {
        this.connection = (AbstractConnectionModel) model;
    }

    @Override
    public void undo() {
        connection.attachSource();
        connection.attachTarget();
    }
}
