
package gef.tutorial.step.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import gef.tutorial.step.model.ArrowConnectionModel;
import gef.tutorial.step.model.ContentsModel;
import gef.tutorial.step.model.HelloModel;
import gef.tutorial.step.model.LineConnectionModel;

public class PartFactory implements EditPartFactory {

    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        EditPart part = getPartFromElement(model);
        part.setModel(model);

        return part;

    }

    private EditPart getPartFromElement(Object modelElement) {
        if (modelElement instanceof HelloModel) {
            return new HelloEditorPart();
        } else if (modelElement instanceof ContentsModel) {
            return new ContentsEditPart();
        } else if (modelElement instanceof LineConnectionModel) {
            return new LineConnectionEditPart();
        } else if (modelElement instanceof ArrowConnectionModel) {
            return new ArrowConnectionEditPart();
        }

        return null;
    }

}
