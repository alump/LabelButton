package org.vaadin.alump.labelbutton;

import com.vaadin.event.FieldEvents;
import com.vaadin.shared.ui.ComponentStateUtil;
import com.vaadin.ui.Component;
import org.vaadin.alump.labelbutton.client.shared.LabelWithButtonState;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * An alternative for LabelButton. Here client side will have both label and a button.
 */
public class LabelWithButton extends LabelButton implements Component.Focusable {

    FieldEvents.FocusAndBlurServerRpcImpl focusBlurRpc = new FieldEvents.FocusAndBlurServerRpcImpl(this) {

        @Override
        protected void fireEvent(Event event) {
            LabelWithButton.this.fireEvent(event);
        }
    };


    public LabelWithButton() {
        super();
        registerRpc(focusBlurRpc);
    }

    public LabelWithButton(String labelContent) {
        this();
        setValue(labelContent);
    }

    public LabelWithButton(String labelContent, LabelClickListener clickListener) {
        this();
        setValue(labelContent);
        addLabelClickListener(clickListener);
    }

    /**
     * Create new LabelWithButton with content text and click listener
     * @param caption Caption of label (shown by parent)
     * @param content Content shown in label
     * @param buttonContent Content shown in button
     * @param clickListener Label's click listener
     */
    public LabelWithButton(String caption, String content, String buttonContent, LabelClickListener clickListener) {
        this();
        setCaption(caption);
        setValue(content);
        setButtonCaption(buttonContent);
        addLabelClickListener(clickListener);
    }

    @Override
    protected LabelWithButtonState getState() {
        return (LabelWithButtonState)super.getState();
    }

    @Override
    protected LabelWithButtonState getState(boolean markAsDirty) {
        return (LabelWithButtonState) super.getState(markAsDirty);
    }

    /**
     * Define caption shown in button
     * @param caption Caption shown in button
     */
    public void setButtonCaption(String caption) {
        getState().buttonState.caption = caption;
    }

    /**
     * Get caption of button
     * @return Caption of button
     */
    public String getButtonCaption() {
        return getState(false).buttonState.caption;
    }

    /**
     * Define if HTML content is allowed in button
     * @param htmlContentAllowed true to allow HTML content, false to disallow
     */
    public void setButtonHtmlContentAllowed(boolean htmlContentAllowed) {
        getState().buttonState.captionAsHtml = htmlContentAllowed;
    }

    /**
     * Check if HTML content is allowed in button
     * @return true if HTML is allowed in button caption
     */
    public boolean isButtonHtmlContentAllowed() {
        return getState(false).buttonState.captionAsHtml;
    }

    public void addButtonStyleName(String style) {
        if(style != null && !"".equals(style)) {
            if(!style.contains(" ")) {
                if(this.getState().buttonState.styles == null) {
                    this.getState().buttonState.styles = new ArrayList();
                }

                List styles1 = this.getState().buttonState.styles;
                if(!styles1.contains(style)) {
                    styles1.add(style);
                }

            } else {
                StringTokenizer styles = new StringTokenizer(style, " ");

                while(styles.hasMoreTokens()) {
                    this.addStyleName(styles.nextToken());
                }

            }
        }
    }

    public void removeButtonStyleName(String style) {
        if(ComponentStateUtil.hasStyles(this.getState())) {
            StringTokenizer tokenizer = new StringTokenizer(style, " ");

            while(tokenizer.hasMoreTokens()) {
                this.getState().buttonState.styles.remove(tokenizer.nextToken());
            }
        }
    }

    /**
     * Way to verify if label is before button in DOM tree
     * @return true if label is first, false if button
     */
    public boolean isLabelFirst() {
        return getState(false).labelFirst;
    }

    /**
     * Define if label, or button, is first in DOM tree
     * @param labelFirst true set label first, false to set button first
     */
    public void setLabelFirst(boolean labelFirst) {
        getState().labelFirst = labelFirst;
    }

    @Override
    public int getTabIndex() {
        return getState(false).buttonState.tabIndex;
    }

    @Override
    public void setTabIndex(int tabIndex) {
        getState().buttonState.tabIndex = tabIndex;
    }

    @Override
    public void focus() {
        super.focus();
    }
}
