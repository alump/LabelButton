package org.vaadin.alump.labelbutton;

import com.vaadin.server.AbstractExtension;
import com.vaadin.server.Extension;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.Label;
import org.vaadin.alump.labelbutton.client.shared.LabelButtonExtensionState;
import org.vaadin.alump.labelbutton.client.shared.LabelButtonServerRpc;

import java.util.ArrayList;
import java.util.List;

/**
 * LabelButton features as extension that can be used with normal Labels. Extension adds some stylenames to extended
 * component, but styling rules are left to application.
 */
public class LabelButtonExtension extends AbstractExtension {

    private Label label;

    private List<LabelClickListener> clickListeners = new ArrayList<LabelClickListener>();

    private LabelButtonServerRpc serverRpc = new LabelButtonServerRpc() {
        @Override
        public void onClick(MouseEventDetails details) {
            fireClick(details);
        }
    };

    protected LabelButtonExtension(Label label) {
        this.label = label;
        extend(label);
        registerRpc(serverRpc, LabelButtonServerRpc.class);
    }

    @Override
    protected LabelButtonExtensionState getState() {
        return (LabelButtonExtensionState)super.getState();
    }

    @Override
    protected LabelButtonExtensionState getState(boolean markDirty) {
        return (LabelButtonExtensionState)super.getState(markDirty);
    }

    /**
     * Get LabelButtonExtension of given label. New extension will be added if not found.
     * @param label Label with extension
     * @return LabelButtonExtension of given label.
     */
    public static LabelButtonExtension get(Label label) {
        return get(label, true);
    }

    /**
     * Get LabelButtonExtension of given label.
     * @param label Label with extension
     * @param createIfMissing If true extension will be added if not found.
     * @return LabelButtonExtension of given label, or null if no extension.
     */
    public static LabelButtonExtension get(Label label, boolean createIfMissing) {
        for(Extension extension : label.getExtensions()) {
            if(extension instanceof LabelButtonExtension) {
                return (LabelButtonExtension)extension;
            }
        }

        if(createIfMissing) {
            return new LabelButtonExtension(label);
        } else {
            return null;
        }
    }

    /**
     * Remove extension from given label
     * @param label Label with extension
     * @return true if extension was found and removed, false if extension was not found
     */
    public static boolean remove(Label label) {
        LabelButtonExtension extension = get(label, false);
        if(extension != null) {
            extension.remove();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void remove() {
        super.remove();
        this.label = null;
    }

    /**
     * Change clickable state of LabelButtonExtension
     * @param clickable true set clickable, false to unset clickable state
     */
    public void setClickable(boolean clickable) {
        getState().clickable = clickable;
        if(clickable) {
            label.addStyleName(LabelButton.CLICKABLE_STYLENAME);
        } else {
            label.removeStyleName(LabelButton.CLICKABLE_STYLENAME);
        }
    }

    /**
     * Check if LabelButtonExtension is currently on clickable mode
     * @return true if clickable
     */
    public boolean isClickable() {
        return getState(false).clickable;
    }

    protected void fireClick(MouseEventDetails details) {
        LabelClickEvent event = new LabelClickEvent(label, details);

        for(LabelClickListener listener : clickListeners) {
            listener.onLabelClick(event);
        }
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

    /**
     * Get extended label
     * @return Label extended
     */
    public Label getLabel() {
        return label;
    }
}
