
package gef.tutorial.step.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;

public class ArrowConnectionEditPart extends CustomAbstractConnectionEditPart {

    @Override
    protected IFigure createFigure() {
        super.createFigure();
        PolylineConnection connection = (PolylineConnection) super.createFigure();

        connection.setTargetDecoration(new PolygonDecoration());

        return connection;
    }

}
