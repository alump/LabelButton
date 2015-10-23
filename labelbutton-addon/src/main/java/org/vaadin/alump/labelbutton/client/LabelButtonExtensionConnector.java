package org.vaadin.alump.labelbutton.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.MouseEventDetailsBuilder;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VLabel;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.labelbutton.client.shared.LabelButtonExtensionState;
import org.vaadin.alump.labelbutton.client.shared.LabelButtonServerRpc;

/**
 * Connector for extension version of LabelButton
 */
@Connect(org.vaadin.alump.labelbutton.LabelButtonExtension.class)
public class LabelButtonExtensionConnector extends AbstractExtensionConnector {

    private HandlerRegistration clickReg;
    private ComponentConnector extendedConnector;

    @Override
    protected void extend(ServerConnector serverConnector) {
        extendedConnector = (ComponentConnector)serverConnector;
    }

    @Override
    public void onUnregister() {
        if(clickReg != null) {
            detachClickHandler();
        }
        super.onUnregister();
    }

    @Override
    public LabelButtonExtensionState getState() {
        return (LabelButtonExtensionState)super.getState();
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

    protected VLabel getExtendedWidget() {
        return (VLabel)extendedConnector.getWidget();
    }

    protected void attachClickHandler() {
        clickReg = getExtendedWidget().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (getState().enabled && extendedConnector.isEnabled()) {
                    MouseEventDetails details = MouseEventDetailsBuilder
                            .buildMouseEventDetails(event.getNativeEvent(), getExtendedWidget().getElement());

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
