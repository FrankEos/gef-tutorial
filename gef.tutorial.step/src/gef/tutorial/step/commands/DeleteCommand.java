
package gef.tutorial.step.commands;

import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.ContentsModel;
import gef.tutorial.step.model.HelloModel;

public class DeleteCommand extends Command {

    private ContentsModel contentsModel;

    private HelloModel helloModel;

    @Override
    public void execute() {
        contentsModel.removeChild(helloModel);
    }

    public void setContentsModel(Object model) {
        contentsModel = (ContentsModel) model;
    }

    public void setHelloModel(Object model) {
        helloModel = (HelloModel) model;
    }

    @Override
    public void undo() {
        contentsModel.addChild(helloModel);
    }
}
