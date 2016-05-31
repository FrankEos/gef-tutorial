
package gef.tutorial.step.commands;

import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.HelloModel;

public class DirectEditCommand extends Command {

    private String oldText, newText;

    private HelloModel helloModel;

    @Override
    public void execute() {
        oldText = helloModel.getText();

        helloModel.setText(newText);
    }

    public void setModel(Object model) {
        this.helloModel = (HelloModel) model;
    }

    public void setText(String newText) {
        this.newText = newText;
    }

    @Override
    public void undo() {
        helloModel.setText(oldText);
    }

}
