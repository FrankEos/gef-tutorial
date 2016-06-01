
package gef.tutorial.step.parts;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.swt.widgets.Text;

import gef.tutorial.step.model.HelloModel;

public class CustomDirectEditManager extends DirectEditManager {

    private HelloModel helloModel;

    @SuppressWarnings("rawtypes")
    public CustomDirectEditManager(GraphicalEditPart source, Class editorType, CellEditorLocator locator) {
        super(source, editorType, locator);
        helloModel = (HelloModel) source.getModel();
    }

    @Override
    protected void initCellEditor() {
        getCellEditor().setValue(helloModel.getText());
        
        Text text = (Text)getCellEditor().getControl();
        text.selectAll();
    }

}
