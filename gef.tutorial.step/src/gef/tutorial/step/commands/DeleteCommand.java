
package gef.tutorial.step.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import gef.tutorial.step.model.AbstractConnectionModel;
import gef.tutorial.step.model.ContentsModel;
import gef.tutorial.step.model.HelloModel;

public class DeleteCommand extends Command {

    private ContentsModel contentsModel;

    private HelloModel helloModel;

    @SuppressWarnings("rawtypes")
    private List sourceConns = new ArrayList();

    @SuppressWarnings("rawtypes")
    private List targetConns = new ArrayList();

    @SuppressWarnings("unchecked")
    @Override
    public void execute() {

        // delete all line relative
        sourceConns.addAll(helloModel.getModelSourceConnections());
        targetConns.addAll(helloModel.getModelTargetConnections());

        for (int i = 0; i < sourceConns.size(); i++) {
            AbstractConnectionModel model = (AbstractConnectionModel) sourceConns.get(i);
            model.dettachSource();
            model.dettachTarget();
        }

        for (int i = 0; i < targetConns.size(); i++) {
            AbstractConnectionModel model = (AbstractConnectionModel) targetConns.get(i);
            model.dettachSource();
            model.dettachTarget();
        }

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
        
        for (int i = 0; i < sourceConns.size(); i++) {
            AbstractConnectionModel model = (AbstractConnectionModel) sourceConns.get(i);
            model.attachSource();
            model.attachTarget();
        }

        for (int i = 0; i < targetConns.size(); i++) {
            AbstractConnectionModel model = (AbstractConnectionModel) targetConns.get(i);
            model.attachSource();
            model.attachTarget();
        }
    }
}
