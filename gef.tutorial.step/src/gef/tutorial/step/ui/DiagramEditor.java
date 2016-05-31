
package gef.tutorial.step.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;

import gef.tutorial.step.Activator;
import gef.tutorial.step.model.ContentsModel;
import gef.tutorial.step.model.HelloModel;
import gef.tutorial.step.model.LineConnectionModel;
import gef.tutorial.step.parts.PartFactory;

public class DiagramEditor extends GraphicalEditorWithPalette {

    public static final String ID = "gef.tutorial.step.ui.DiagramEditor";

    GraphicalViewer viewer;

    public DiagramEditor() {
        setEditDomain(new DefaultEditDomain(this));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void createActions() {
        super.createActions();

        ActionRegistry registry = getActionRegistry();

        IAction action = new DirectEditAction((IWorkbenchPart) this);

        registry.registerAction(action);

        getSelectionActions().add(action.getId());
    }

    @Override
    protected void configureGraphicalViewer() {
        super.configureGraphicalViewer();

        viewer = getGraphicalViewer();

        viewer.setEditPartFactory(new PartFactory());

        // create key handle

        KeyHandler keyHandler = new KeyHandler();

        keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
                getActionRegistry().getAction(ActionFactory.DELETE.getId()));

        keyHandler.put(KeyStroke.getPressed(SWT.F2, 0), getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT));

        // getGraphicalViewer().setKeyHandler(keyHandler);
        getGraphicalViewer().setKeyHandler(new GraphicalViewerKeyHandler(getGraphicalViewer()).setParent(keyHandler));
    }

    @Override
    protected void initializeGraphicalViewer() {
        ContentsModel parent = new ContentsModel();

        HelloModel child1 = new HelloModel();
        child1.setConstraint(new Rectangle(0, 0, -1, -1));
        parent.addChild(child1);

        HelloModel child2 = new HelloModel();
        child2.setConstraint(new Rectangle(30, 30, -1, -1));
        parent.addChild(child2);

        HelloModel child3 = new HelloModel();
        child3.setConstraint(new Rectangle(10, 80, 80, 50));
        parent.addChild(child3);

        viewer.setContents(parent);
    }

    @Override
    public void doSave(IProgressMonitor monitor) {

    }

    @Override
    protected PaletteRoot getPaletteRoot() {
        PaletteRoot root = new PaletteRoot();

        PaletteGroup toolGroup = new PaletteGroup("Tools");

        ToolEntry tool = new SelectionToolEntry();
        toolGroup.add(tool);

        root.setDefaultEntry(tool);

        tool = new MarqueeToolEntry();
        toolGroup.add(tool);

        // Paint section
        PaletteDrawer drawer = new PaletteDrawer("Paint");

        ImageDescriptor descriptor = Activator.getImageDescriptor("icons/HelloModel.png");

        CreationToolEntry helloModelCreationEntry = new CreationToolEntry(null, "创建HelloModel模型",
                new SimpleFactory(HelloModel.class), descriptor, descriptor);

        drawer.add(helloModelCreationEntry);

        // Connection section
        PaletteDrawer connDrawer = new PaletteDrawer("Connection");
        ImageDescriptor connDescription = Activator.getImageDescriptor("icons/connection.png");
        ConnectionCreationToolEntry connCreationEntry = new ConnectionCreationToolEntry(null, "创建连接",
                new SimpleFactory(LineConnectionModel.class), connDescription, connDescription);
        connDrawer.add(connCreationEntry);

        root.add(toolGroup);
        root.add(drawer);
        root.add(connDrawer);

        return root;

    }

}
