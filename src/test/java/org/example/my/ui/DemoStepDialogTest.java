package org.example.my.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;
import org.junit.Test;
import org.pentaho.di.core.Const;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.sdk.samples.steps.demo.DemoStepMeta;
import org.pentaho.di.ui.core.PropsUI;
import org.pentaho.di.ui.core.widget.LabelText;
import org.pentaho.di.ui.core.widget.TableView;
import org.pentaho.di.ui.trans.step.BaseStepDialog;
// import org.eclipse.wb.swt.SWTResourceManager;

public class DemoStepDialogTest {

    /**
     * Called when the user cancels the dialog.
     */
    private void cancel() {
        System.out.println("cancel is running");
    }

    private void ok() {
        System.out.println("ok is running");
    }


    private LabelText wKuduMasters;
    private LabelText wDbFieldName;
    private LabelText wTableName;
    protected TableView m_wFieldsTable;

    @Test
    public void test2() {
        Class<?> PKG = DemoStepMeta.class; // for i18n purposes
        Display display = new Display();
        Shell shell = new Shell(display);

	    PropsUI.init(display, 1);
	    PropsUI props = PropsUI.getInstance();
        props.setLook( shell );

        // ------------------------------------------------------- //
        // SWT code for building the actual settings dialog        //
        // ------------------------------------------------------- //
        FormLayout formLayout = new FormLayout();
        formLayout.marginWidth = Const.FORM_MARGIN;
        formLayout.marginHeight = Const.FORM_MARGIN;
        shell.setLayout( formLayout );
        shell.setText( "Demo.Shell.Title" );
        int middle = props.getMiddlePct();
        int margin = Const.MARGIN;

        // Stepname line
        Label wlStepname = new Label(shell, SWT.RIGHT);
        wlStepname.setText( "System.Label.StepName");
        props.setLook( wlStepname );
        FormData fdlStepname = new FormData();
        fdlStepname.left = new FormAttachment( 0, 0 );
        fdlStepname.right = new FormAttachment( middle, -margin );
        fdlStepname.top = new FormAttachment( 0, margin );
        wlStepname.setLayoutData( fdlStepname );

        Text wStepname = new Text( shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
        String stepname = "stepnameVar";
        wStepname.setText( stepname );
        props.setLook( wStepname );

        // The ModifyListener used on all controls. It will update the meta object to
        // indicate that changes are being made.
        ModifyListener lsMod = new ModifyListener() {
            public void modifyText( ModifyEvent e ) {
                // meta.setChanged();
                System.out.println("modifyText be started ... ");
            }
        };

        wStepname.addModifyListener( lsMod );
        FormData fdStepname = new FormData();
        fdStepname.left = new FormAttachment( middle, 0 );
        fdStepname.top = new FormAttachment( 0, margin );
        fdStepname.right = new FormAttachment( 100, 0 );
        wStepname.setLayoutData( fdStepname );

        LabelText wHelloFieldName = new LabelText( shell, "Demo.FieldName.Label", null);
        props.setLook( wHelloFieldName );
        wHelloFieldName.addModifyListener( lsMod );
        FormData fdValName = new FormData();
        fdValName.left = new FormAttachment( 0, 0 );
        fdValName.right = new FormAttachment( 100, 0 );
        fdValName.top = new FormAttachment( wStepname, margin );
        wHelloFieldName.setLayoutData( fdValName );

        //fields
        Label wlFields = new Label(shell, SWT.NONE);
        wlFields.setText(BaseMessages.getString(PKG, "Demo.Table.Fields.Label"));
        props.setLook(wlFields);
        FormData fdlFields = new FormData();
        fdlFields.left = new FormAttachment(0, 0);
        // fdlFields.top = new FormAttachment(gConnect, margin);
        fdlFields.top = new FormAttachment(wTableName, margin);
        wlFields.setLayoutData(fdlFields);


        // OK and cancel buttons
        Button wOK = new Button( shell, SWT.PUSH );
        wOK.setText( "System.Button.OK" );
        Button wCancel = new Button( shell, SWT.PUSH );
        wCancel.setText( "System.Button.Cancel" );

        // setButtonPositions( new Button[] { wOK, wCancel }, margin, wHelloFieldName );
        BaseStepDialog.positionBottomButtons( shell, new Button[] { wOK, wCancel }, margin, wTableName );

        // Add listeners for cancel and OK
        Listener lsCancel = new Listener() {
            public void handleEvent( Event e ) {
                cancel();
            }
        };
        Listener lsOK = new Listener() {
            public void handleEvent( Event e ) {
                ok();
            }
        };

        wCancel.addListener( SWT.Selection, lsCancel );
        wOK.addListener( SWT.Selection, lsOK );

        // default listener (for hitting "enter")
        SelectionAdapter lsDef = new SelectionAdapter() {
            public void widgetDefaultSelected( SelectionEvent e ) {
                ok();
            }
        };
        wStepname.addSelectionListener( lsDef );
        wHelloFieldName.addSelectionListener( lsDef );

        // Detect X or ALT-F4 or something that kills this window and cancel the dialog properly
        shell.addShellListener( new ShellAdapter() {
            public void shellClosed( ShellEvent e ) {
                cancel();
            }
        } );

        // Set/Restore the dialog size based on last position on screen
        // The setSize() method is inherited from BaseStepDialog
        // setSize();
        // BaseStepDialog.setSize(shell);

        // populate the dialog with the values from the meta object
        // populateDialog();
        wStepname.selectAll();
        wHelloFieldName.setText( "set Text" );

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();

        System.out.println("test2 end ... ");
    }

    public static void main(String[] args) {
//        UIEntry2 entry2 = new UIEntry2();
//        entry2.test2();

    }
}
