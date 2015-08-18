package org.vaadin.alump.labelbutton.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.PreElement;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.StyleConstants;
import com.vaadin.client.ui.VButton;
import com.vaadin.client.ui.VLabel;

/**
 * Created by alump on 18/08/15.
 */
public class LnBWidget extends FlowPanel implements HasEnabled {

    private VLabel label;
    private VButton button;
    private boolean enabled;
    private boolean labelFirst;

    public LnBWidget() {
        label = GWT.create(VLabel.class);
        button = GWT.create(VButton.class);

        setOrder(label, button);

        label.addStyleName("label-with-button");
        button.addStyleName("button-with-label");
    }

    protected void setOrder(Widget first, Widget second) {
        labelFirst = (first == label);
        clear();
        add(first);
        add(second);
    }

    public VLabel getLabel() {
        return label;
    }

    public VButton getButton() {
        return button;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        label.setStyleName(StyleConstants.DISABLED, !enabled);
        button.setStyleName(StyleConstants.DISABLED, !enabled);
    }

    /**
     * Change order of children in DOM tree
     * @param labelFirst true to set label first, false to set button first
     */
    public void setLabelFirst(boolean labelFirst) {
        if(this.labelFirst != labelFirst) {
            if(labelFirst) {
                setOrder(label, button);
            } else {
                setOrder(button, label);
            }
        }
    }
}
