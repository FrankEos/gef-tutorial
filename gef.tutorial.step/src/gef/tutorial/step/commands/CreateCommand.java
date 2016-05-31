
package gef.tutorial.step.commands;

import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.ContentsModel;
import gef.tutorial.step.model.HelloModel;

public class CreateCommand extends Command {

    private ContentsModel contentsModel;

    private HelloModel helloModel;

    @Override
    public void execute() {
        contentsModel.addChild(helloModel);
    }

    public void setContentsModel(Object model) {
        this.contentsModel = (ContentsModel) model;
    }

    public void setHelloModel(Object model) {
        this.helloModel = (HelloModel) model;
    }

    @Override
    public void undo() {
        contentsModel.removeChild(helloModel);
    }
}
