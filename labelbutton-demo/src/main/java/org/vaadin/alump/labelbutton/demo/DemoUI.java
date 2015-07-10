package org.vaadin.alump.labelbutton.demo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.vaadin.alump.labelbutton.LabelButton;
import org.vaadin.alump.labelbutton.LabelButtonStyles;
import org.vaadin.alump.labelbutton.LabelClickEvent;

import java.util.HashSet;
import java.util.Set;

@Theme("demo")
@Title("LabelButton Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    private Set<LabelButton> labelButtons = new HashSet<LabelButton>();

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.alump.labelbutton.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth(100, Unit.PERCENTAGE);
        topLayout.setMargin(true);
        topLayout.setSpacing(true);
        setContent(topLayout);

        // -- left column = labels --
        VerticalLayout leftColumn = createColumn(topLayout);

        LabelButton label1 = new LabelButton("Example 1", "Click for details", event -> showClickDetails(event));
        leftColumn.addComponent(label1);
        labelButtons.add(label1);

        LabelButton label2 = new LabelButton("Example 2 (pencil style)", "Click to modify", event -> modifyLabel(event));
        label2.addStyleName(LabelButtonStyles.PENCIL_WHEN_CLICKABLE);
        leftColumn.addComponent(label2);
        labelButtons.add(label2);

        LabelButton label3 = new LabelButton("Example 3 (underline and pointer styles)", "Show notification",
                event -> Notification.show("Clicked!"));
        label3.addStyleName(LabelButtonStyles.HOVER_UNDERLINE_WHEN_CLICKABLE);
        label3.addStyleName(LabelButtonStyles.POINTER_WHEN_CLICKABLE);
        leftColumn.addComponent(label3);
        labelButtons.add(label3);

        LabelButton label4 = new LabelButton("Example 4 (toggles HTML/text modes)", "<b>Bold</b> and <i>Italic</i>", ContentMode.HTML, event -> {
           if(event.getLabel().getContentMode() == ContentMode.HTML) {
               event.getLabel().setContentMode(ContentMode.TEXT);
           } else {
               event.getLabel().setContentMode(ContentMode.HTML);
           }
        });
        leftColumn.addComponent(label4);
        labelButtons.add(label4);

        // -- right column = options --
        VerticalLayout rightColumn = createColumn(topLayout);

        CheckBox clickableCBox = new CheckBox("Make labels unclickable");
        clickableCBox.addValueChangeListener(event -> setUnclickable((Boolean) event.getProperty().getValue()));
        rightColumn.addComponent(clickableCBox);

        CheckBox disableCBox = new CheckBox("Disable labels");
        disableCBox.addValueChangeListener(event -> setDisabled((Boolean) event.getProperty().getValue()));
        rightColumn.addComponent(disableCBox);

    }

    private static VerticalLayout createColumn(HorizontalLayout parent) {
        VerticalLayout column = new VerticalLayout();
        column.setWidth(100, Unit.PERCENTAGE);
        column.setSpacing(true);
        parent.addComponent(column);
        parent.setExpandRatio(column, 1f);
        return column;
    }

    private void setUnclickable(boolean unclickable) {
        labelButtons.forEach(lb -> lb.setClickable(!unclickable));
    }

    private void setDisabled(boolean disabled) {
        labelButtons.forEach(lb -> lb.setEnabled(!disabled));
    }

    private void showClickDetails(final LabelClickEvent event) {
        Window window = createWindow(event, "Click details");
        VerticalLayout layout = createWindowLayout(window);

        layout.addComponent(createDataLabel("Label value", event.getLabel().getValue()));
        layout.addComponent(createDataLabel("Client coordinates", "X:" + event.getClientX() + ", Y:" + event.getClientY()));
        layout.addComponent(createDataLabel("Relative coordinates", "X:" + event.getRelativeX() + ", Y:" + event.getRelativeY()));
        layout.addComponent(createDataLabel("Modifiers", "ALT:" + event.isAltKey() + ", CTRL:" + event.isCtrlKey() + ", SHIFT:" + event.isShiftKey() + ", META:" + event.isMetaKey()));

        getUI().addWindow(window);
    }

    private void modifyLabel(final LabelClickEvent event) {
        final Window window = createWindow(event, "Modify label value");
        VerticalLayout layout = createWindowLayout(window);

        final TextField textField = new TextField("Label content");
        textField.setWidth(100, Unit.PERCENTAGE);
        textField.setValue(event.getLabel().getValue());
        layout.addComponent(textField);

        Button okButton = new Button("Modify", e -> {
            event.getLabel().setValue(textField.getValue());
            window.close();
        });
        layout.addComponent(okButton);

        getUI().addWindow(window);
        textField.focus();
    }

    private static Window createWindow(LabelClickEvent event, String caption) {
        Window window = new Window(caption);
        window.setResizable(false);
        window.setPositionX(event.getClientX());
        window.setPositionY(event.getClientY());
        return window;
    }

    private static VerticalLayout createWindowLayout(Window window) {
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth(400, Unit.PIXELS);
        layout.setMargin(true);
        layout.setSpacing(true);
        window.setContent(layout);
        return layout;
    }

    private static Label createDataLabel(String caption, String value) {
        Label label = new Label(value);
        label.setCaption(caption);
        return label;
    }

}
