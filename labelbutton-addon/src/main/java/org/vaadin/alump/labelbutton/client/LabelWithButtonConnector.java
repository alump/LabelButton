package org.vaadin.alump.labelbutton.client;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.PreElement;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.vaadin.client.MouseEventDetailsBuilder;
import com.vaadin.client.Profiler;
import com.vaadin.client.WidgetUtil;
import com.vaadin.client.annotations.OnStateChange;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.AbstractComponentState;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.FieldRpc;
import com.vaadin.shared.ui.ComponentStateUtil;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.labelbutton.client.shared.LabelButtonServerRpc;
import org.vaadin.alump.labelbutton.client.shared.LabelButtonState;
import org.vaadin.alump.labelbutton.client.shared.LabelWithButtonState;

import java.util.Iterator;

/**
 * Connector for LabelButton component
 */
@Connect(org.vaadin.alump.labelbutton.LabelWithButton.class)
public class LabelWithButtonConnector extends AbstractComponentConnector implements FocusHandler, BlurHandler {

    private HandlerRegistration clickReg;

    private JsArrayString buttonStyleNames = (JsArrayString)JsArrayString.createArray().cast();

    @Override
    public LnBWidget getWidget() {
        return (LnBWidget)super.getWidget();
    }

    @Override
    public LabelWithButtonState getState() {
        return (LabelWithButtonState)super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent event) {
        super.onStateChanged(event);

        getWidget().setLabelFirst(getState().labelFirst);

        // Following code is taken from LabelConnector and modified
        boolean sinkOnloads = false;
        switch (getState().contentMode) {
            case PREFORMATTED:
                PreElement preElement = Document.get().createPreElement();
                preElement.setInnerText(getState().text);
                // clear existing content
                getWidget().getLabel().setHTML("");
                // add preformatted text to dom
                getWidget().getLabel().getElement().appendChild(preElement);
                break;

            case TEXT:
                getWidget().getLabel().setText(getState().text);
                break;

            case HTML:
            case RAW:
                sinkOnloads = true;
            case XML:
                getWidget().getLabel().setHTML(getState().text);
                break;
            default:
                getWidget().getLabel().setText("");
                break;

        }

        if (sinkOnloads) {
            WidgetUtil.sinkOnloadForImages(getWidget().getLabel().getElement());
        }

        if(getState().clickable && clickReg == null) {
            attachClickHandler();
            getWidget().getButton().setVisible(true);
        } else if(!getState().clickable && clickReg != null) {
            detachClickHandler();
            getWidget().getButton().setVisible(false);
        }
    }

    @OnStateChange({"buttonState"})
    void updateButtonState() {

        updateButtonStyleNames();

        if(getState().buttonState.captionAsHtml) {
            getWidget().getButton().setHtml(getState().buttonState.caption);
        } else {
            getWidget().getButton().setText(getState().buttonState.caption);
        }

        getWidget().getButton().setTabIndex(getState().buttonState.tabIndex);
    }

    protected void attachClickHandler() {
        clickReg = getWidget().getButton().addClickHandler(new ClickHandler() {
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

    @Override
    public void onFocus(FocusEvent event) {
        getRpcProxy(FieldRpc.FocusAndBlurServerRpc.class).focus();
    }

    @Override
    public void onBlur(BlurEvent event) {
        getRpcProxy(FieldRpc.FocusAndBlurServerRpc.class).blur();
    }

    protected void updateButtonStyleNames() {
        AbstractComponentState state = this.getState().buttonState;
        String primaryStyleName = this.getWidget().getStylePrimaryName();
        this.setButtonStyleName("v-readonly", getState().readOnly);

        String newStyle;
        for(int i$ = 0; i$ < this.buttonStyleNames.length(); ++i$) {
            newStyle = this.buttonStyleNames.get(i$);
            this.setButtonStyleName(newStyle, false);
        }

        this.buttonStyleNames.setLength(0);
        if(ComponentStateUtil.hasStyles(state)) {
            Iterator var5 = state.styles.iterator();

            while(var5.hasNext()) {
                newStyle = (String)var5.next();
                this.setButtonStyleName(newStyle, true);
                this.buttonStyleNames.push(newStyle);
            }
        }

        if(state.primaryStyleName != null && !state.primaryStyleName.equals(primaryStyleName)) {
            this.getWidget().getButton().setStylePrimaryName(state.primaryStyleName);
        }
    }

    protected void setButtonStyleName(String styleName, boolean add) {
        this.getWidget().getButton().setStyleName(styleName, add);
    }
}
