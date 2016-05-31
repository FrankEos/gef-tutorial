
package gef.tutorial.step.actions;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import gef.tutorial.step.ui.DiagramEditor;
import gef.tutorial.step.ui.DiagramEditorInput;

public class DiagramAction extends Action implements ISelectionListener, IWorkbenchAction {
    public final static String ID = "gef.step.diagram";

    private IWorkbenchWindow window;

    // private IStructuredSelection selection;

    public DiagramAction(IWorkbenchWindow window) {
        this.window = window;
        setId(ID);
        setText("&Diagram");
        setToolTipText("Draw the GEF diagram .");
        window.getSelectionService().addSelectionListener(this);
    }

    @Override
    public void dispose() {
        window.getSelectionService().removeSelectionListener(this);
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {

    }

    @Override
    public void run() {

        String path = openFileDialog();

        if (path != null) {
            IEditorInput input = new DiagramEditorInput(new Path(path));
            IWorkbenchPage page = window.getActivePage();
            try {
                page.openEditor(input, DiagramEditor.ID, true);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    private String openFileDialog() {

        FileDialog dialog = new FileDialog(window.getShell(), SWT.NONE);
        dialog.setText("Open A GEF File");
        dialog.setFilterExtensions(new String[] {
                "*.diagram"
        });
        return dialog.open();

    }

}
