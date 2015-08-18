package org.vaadin.alump.labelbutton.client.shared;

import com.vaadin.shared.annotations.NoLayout;
import com.vaadin.shared.ui.button.ButtonState;

/**
 * LabelWithButtonState extending LabelButtonState by button state
 */
public class LabelWithButtonState extends LabelButtonState {

    public LabelWithButtonState() {
        super();
        this.primaryStyleName = "label-with-button";
    }

    public boolean labelFirst = true;

    public ButtonState buttonState = new ButtonState();
}
