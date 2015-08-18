package org.vaadin.alump.labelbutton;

import com.vaadin.data.Property;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import org.vaadin.alump.labelbutton.client.shared.LabelButtonServerRpc;
import org.vaadin.alump.labelbutton.client.shared.LabelButtonState;

import java.util.ArrayList;
import java.util.List;

/**
 * Adds button like features to Label.
 */
public class LabelButton extends Label {

    /**
     * Style name in all LabelButtons
     */
    public static final String STYLENAME = "labelbutton";

    /**
     * Style name added when clickable
     */
    public static final String CLICKABLE_STYLENAME = "lb-clickable";

    private List<LabelClickListener> clickListeners = new ArrayList<LabelClickListener>();

    private LabelButtonServerRpc serverRpc = new LabelButtonServerRpc() {
        @Override
        public void onClick(MouseEventDetails details) {
            fireClick(details);
        }
    };

    /**
     * @see com.vaadin.ui.Button
     */
    public LabelButton() {
        addStyleName(STYLENAME);
        registerRpc(serverRpc, LabelButtonServerRpc.class);
        setClickable(true);
    }

    /**
     * @see com.vaadin.ui.Button
     */
    public LabelButton(String content) {
        this();
        setValue(content);
    }

    /**
     * Create new LabelButton with content text and click listener
     * @param content Content shown in label
     * @param clickListener Label's click listener
     */
    public LabelButton(String content, LabelClickListener clickListener) {
        this(content, ContentMode.TEXT, clickListener);
    }

    /**
     * Create new LabelButton with content text and click listener
     * @param caption Caption of label (shown by parent)
     * @param content Content shown in label
     * @param clickListener Label's click listener
     */
    public LabelButton(String caption, String content, LabelClickListener clickListener) {
        this(caption, content, ContentMode.TEXT, clickListener);
    }

    /**
     * Create new LabelButton with given content mode and click listener
     * @param content Content shown in label
     * @param contentMode Content mode
     * @param clickListener Label's click listener
     */
    public LabelButton(String content, ContentMode contentMode, LabelClickListener clickListener) {
        this(content, contentMode);
        addLabelClickListener(clickListener);
    }

    /**
     * Create new LabelButton with given content mode and click listener
     * @param caption Caption of label (shown by parent)
     * @param content Content shown in label
     * @param contentMode Content mode
     * @param clickListener Label's click listener
     */
    public LabelButton(String caption, String content, ContentMode contentMode, LabelClickListener clickListener) {
        this(content, contentMode);
        setCaption(caption);
        addLabelClickListener(clickListener);
    }

    /**
     * @see com.vaadin.ui.Button
     */
    public LabelButton(Property contentSource) {
        this();
        setPropertyDataSource(contentSource);
        setContentMode(ContentMode.TEXT);
    }

    /**
     * @see com.vaadin.ui.Button
     */
    public LabelButton(String content, ContentMode contentMode) {
        this();
        setValue(content);
        setContentMode(contentMode);
        setWidth(100, Unit.PERCENTAGE);
    }

    /**
     * @see com.vaadin.ui.Button
     */
    public LabelButton(Property contentSource, ContentMode contentMode) {
        this();
        setPropertyDataSource(contentSource);
        setContentMode(contentMode);
        setWidth(100, Unit.PERCENTAGE);
    }

    @Override
    protected LabelButtonState getState() {
        return (LabelButtonState) super.getState();
    }

    @Override
    protected LabelButtonState getState(boolean markAsDirty) {
        return (LabelButtonState) super.getState(markAsDirty);
    }

    /**
     * Change clickable state of LabelButton
     * @param clickable true set clickable, false to unset clickable state
     */
    public void setClickable(boolean clickable) {
        getState().clickable = clickable;
        if(clickable) {
            addStyleName(CLICKABLE_STYLENAME);
        } else {
            removeStyleName(CLICKABLE_STYLENAME);
        }
    }

    /**
     * Check if LabelButton is currently on clickable mode
     * @return true if clickable
     */
    public boolean isClickable() {
        return getState(false).clickable;
    }

    /**
     * Add click listener
     * @param listener Click listener added
     */
    public void addLabelClickListener(LabelClickListener listener) {
        if(listener == null) {
            throw new IllegalArgumentException("null listener given");
        }
        clickListeners.add(listener);
    }

    /**
     * Remove click listener
     * @param listener Click listener removed
     */
    public void removeLabelClickListener(LabelClickListener listener) {
        clickListeners.remove(listener);
    }

    protected void fireClick() {
        fireClick(null);
    }

    protected void fireClick(MouseEventDetails details) {
        LabelClickEvent event = new LabelClickEvent(LabelButton.this, details);

        for(LabelClickListener listener : clickListeners) {
            listener.onLabelClick(event);
        }
    }

    /**
     * Will cause LabelButton to emit click signals when clickable
     */
    public void click() {
        if(this.isEnabled() && !this.isReadOnly() && this.isClickable()) {
            this.fireClick();
        }
    }
}
