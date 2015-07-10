package org.vaadin.alump.labelbutton;

import com.vaadin.shared.MouseEventDetails;

/**
 * Created by alump on 10/07/15.
 */
public class LabelClickEvent {

    private final LabelButton label;
    private final MouseEventDetails details;

    public LabelClickEvent(LabelButton label) {
        this(label, null);
    }

    public LabelClickEvent(LabelButton label, MouseEventDetails details) {
        this.label = label;
        this.details = details;
    }

    /**
     * Get clicked LabelButton
     * @return LabelButton clicked
     */
    public LabelButton getLabel() {
        return label;
    }

    /**
     * Cursor X in client's coordinate
     * @return X coordinate, or -1 if undefined
     */
    public int getClientX() {
        if (null != details) {
            return details.getClientX();
        } else {
            return -1;
        }
    }

    /**
     * Cursor Y in client's coordinate
     * @return Y coordinate, or -1 if undefined
     */
    public int getClientY() {
        if (null != details) {
            return details.getClientY();
        } else {
            return -1;
        }
    }

    /**
     * Cursor X in relative coordinate
     * @return X coordinate, or -1 if undefined
     */
    public int getRelativeX() {
        if (null != details) {
            return details.getRelativeX();
        } else {
            return -1;
        }
    }

    /**
     * Cursor Y in relative coordinate
     * @return Y coordinate, or -1 if undefined
     */
    public int getRelativeY() {
        if (null != details) {
            return details.getRelativeY();
        } else {
            return -1;
        }
    }

    /**
     * Check if Alt key was pressed down when clicked
     * @return true if Alt key was pressed down. false if not or undefined
     */
    public boolean isAltKey() {
        if (null != details) {
            return details.isAltKey();
        } else {
            return false;
        }
    }

    /**
     * Check if Ctrl key was pressed down when clicked
     * @return true if Ctrl key was pressed down. false if not or undefined
     */
    public boolean isCtrlKey() {
        if (null != details) {
            return details.isCtrlKey();
        } else {
            return false;
        }
    }

    /**
     * Check if Meta key was pressed down when clicked
     * @return true if Meta key was pressed down. false if not or undefined
     */
    public boolean isMetaKey() {
        if (null != details) {
            return details.isMetaKey();
        } else {
            return false;
        }
    }

    /**
     * Check if Shift key was pressed down when clicked
     * @return true if Shift key was pressed down. false if not or undefined
     */
    public boolean isShiftKey() {
        if (null != details) {
            return details.isShiftKey();
        } else {
            return false;
        }
    }
}
