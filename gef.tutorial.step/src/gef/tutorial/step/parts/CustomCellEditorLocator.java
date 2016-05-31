
package gef.tutorial.step.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;

public class CustomCellEditorLocator implements CellEditorLocator {

    private IFigure figure;

    public CustomCellEditorLocator(IFigure f) {
        figure = f;
    }

    @Override
    public void relocate(CellEditor cellEditor) {
        Text text = (Text) cellEditor.getControl();

        Rectangle rect = figure.getBounds().getCopy();

        figure.translateToAbsolute(rect);

        text.setBounds(rect.x, rect.y, rect.width, rect.height);
    }

}
