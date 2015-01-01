package org.appfuse.tutorial.webapp.client.ui.people.edit;

import java.util.List;

import org.appfuse.tutorial.webapp.client.application.ApplicationResources;
import org.appfuse.tutorial.webapp.client.proxies.PersonProxy;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.IntegerBox;
import com.github.gwtbootstrap.client.ui.LongBox;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

/**
 * @author ivangsa
 *
 */
public class EditPersonViewImpl extends Composite implements EditPersonView {
    interface Binder extends UiBinder<Widget, EditPersonViewImpl> {
    }

    private static final Binder BINDER = GWT.create(Binder.class);

    interface Driver extends RequestFactoryEditorDriver<PersonProxy, EditPersonViewImpl> {
    }

    @UiField(provided = true)
    protected ApplicationResources i18n = GWT.create(ApplicationResources.class);
    private EditPersonView.Delegate delegate;
    @UiField
    protected Paragraph subheading;
    @UiField
    LongBox id;
    @UiField
    IntegerBox version;
    @UiField
    TextBox firstName;
    @UiField
    TextBox lastName;
    @UiField
    Button saveButton;
    @UiField
    protected Button deleteButton;
    @UiField
    Button cancelButton;

    /**
*
*/
    public EditPersonViewImpl() {
        super();
        initWidget(BINDER.createAndBindUi(this));
    }

    public void setDelegate(EditPersonView.Delegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public RequestFactoryEditorDriver<PersonProxy, ? extends EditPersonView> createEditorDriver() {
        RequestFactoryEditorDriver<PersonProxy, EditPersonViewImpl> editorDriver = GWT.create(Driver.class);
        editorDriver.initialize(this);
        return editorDriver;
    }

    @Override
    public void showErrors(List<EditorError> errors) {
        if (errors != null && !errors.isEmpty()) {
            SafeHtmlBuilder b = new SafeHtmlBuilder();
            for (EditorError error : errors) {
                if (error.getPath() != null && !"".equals(error.getPath())) {
                    b.appendEscaped(error.getPath()).appendEscaped(": ");
                }
                b.appendEscaped(error.getMessage()).appendEscaped("\n");
            }
            Window.alert(b.toSafeHtml().asString());
        }
    }

    @UiHandler("saveButton")
    public void onSaveButtonClick(ClickEvent event) {
        delegate.saveClicked();
    }

    @UiHandler("deleteButton")
    public void onDeleteButtonClick(ClickEvent event) {
        delegate.deleteClicked();
    }

    @UiHandler("cancelButton")
    public void onCancelButtonClick(ClickEvent event) {
        delegate.cancelClicked();
    }

    @Override
    public void setEnabled(boolean b) {
        // TODO
    }
}