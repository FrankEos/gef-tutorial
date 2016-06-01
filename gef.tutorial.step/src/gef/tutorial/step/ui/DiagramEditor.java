
package gef.tutorial.step.ui;

import java.io.FileWriter;
import java.util.EventObject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.graph.Path;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
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
import org.eclipse.gef.ui.actions.AlignmentAction;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;

import gef.tutorial.step.Activator;
import gef.tutorial.step.model.ArrowConnectionModel;
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

        // 水平方向对齐
        action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.LEFT);
        registry.registerAction(action);

        getSelectionActions().add(action.getId());

        action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.CENTER);
        registry.registerAction(action);

        getSelectionActions().add(action.getId());

        action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.RIGHT);
        registry.registerAction(action);

        getSelectionActions().add(action.getId());
        // 垂直方向对齐

        action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.TOP);
        registry.registerAction(action);

        getSelectionActions().add(action.getId());

        action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.MIDDLE);
        registry.registerAction(action);

        getSelectionActions().add(action.getId());

        action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.BOTTOM);
        registry.registerAction(action);

        getSelectionActions().add(action.getId());

    }

    @Override
    protected void configureGraphicalViewer() {
        super.configureGraphicalViewer();

        viewer = getGraphicalViewer();

        // 设置缩放功能
        ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
        viewer.setRootEditPart(rootEditPart);

        // 获得ZoomManager
        ZoomManager manager = rootEditPart.getZoomManager();
        // 注册放大的Action
        IAction action = new ZoomInAction(manager);
        getActionRegistry().registerAction(action);

        // 注册缩小的Action
        action = new ZoomOutAction(manager);
        getActionRegistry().registerAction(action);

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

        // HelloModel child1 = new HelloModel();
        // child1.setConstraint(new Rectangle(0, 0, -1, -1));
        // parent.addChild(child1);
        //
        // HelloModel child2 = new HelloModel();
        // child2.setConstraint(new Rectangle(30, 30, -1, -1));
        // parent.addChild(child2);
        //
        // HelloModel child3 = new HelloModel();
        // child3.setConstraint(new Rectangle(10, 80, 80, 50));
        // parent.addChild(child3);

        viewer.setContents(parent);
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        performSave(monitor, (IPathEditorInput) getEditorInput());
        getCommandStack().markSaveLocation();
    }

    // 保存xml文件
    private void performSave(IProgressMonitor monitor, IPathEditorInput input) {
        // TODO Auto-generated method stub

        try {
            FileWriter writer;
            writer = new FileWriter(input.getPath().toFile().getPath());

            writer.write("dirty");
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isDirty() {
        return getCommandStack().isDirty();
    }

    @Override
    public void commandStackChanged(EventObject event) {
        firePropertyChange(IEditorPart.PROP_DIRTY);
        super.commandStackChanged(event);
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

        ConnectionCreationToolEntry arrowConnCreationEntry = new ConnectionCreationToolEntry(null, "创建带箭头的连接",
                new SimpleFactory(ArrowConnectionModel.class), connDescription, connDescription);

        connDrawer.add(connCreationEntry);
        connDrawer.add(arrowConnCreationEntry);

        root.add(toolGroup);
        root.add(drawer);
        root.add(connDrawer);

        return root;

    }

}
