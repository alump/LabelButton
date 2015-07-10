package org.vaadin.alump.labelbutton.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.vaadin.client.MouseEventDetailsBuilder;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.label.LabelConnector;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.labelbutton.client.shared.LabelButtonServerRpc;
import org.vaadin.alump.labelbutton.client.shared.LabelButtonState;

/**
 * Connector for LabelButton component
 */
@Connect(org.vaadin.alump.labelbutton.LabelButton.class)
public class LabelButtonConnector extends LabelConnector {

    private HandlerRegistration clickReg;

    @Override
    public LabelButtonState getState() {
        return (LabelButtonState) super.getState();
    }

    @Override
    public void onUnregister() {
        if(clickReg != null) {
            detachClickHandler();
        }
        super.onUnregister();
    }

    @Override
    public void onStateChanged(StateChangeEvent event) {
        super.onStateChanged(event);

        if(getState().clickable && clickReg == null) {
            attachClickHandler();
        } else if(!getState().clickable && clickReg != null) {
            detachClickHandler();
        }
    }

    protected void attachClickHandler() {
        clickReg = getWidget().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                //TODO: also add text selection check here!
                if (getState().enabled) {
                    MouseEventDetails details = MouseEventDetailsBuilder
                            .buildMouseEventDetails(event.getNativeEvent(), getWidget()
                                    .getElement());

                    event.stopPropagation();
                    event.preventDefault();
                    getRpcProxy(LabelButtonServerRpc.class).onClick(details);
                }
            }
        });
    }

    protected void detachClickHandler() {
        clickReg.removeHandler();
        clickReg = null;
    }

}
