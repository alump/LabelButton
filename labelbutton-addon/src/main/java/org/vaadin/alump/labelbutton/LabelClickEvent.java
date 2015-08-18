package org.vaadin.alump.labelbutton;

import com.vaadin.shared.MouseEventDetails;

/**
 * Event given when LabelButton is clicked
 */
public class LabelClickEvent {

    private final LabelButton label;
    private final MouseEventDetails details;

    /**
     * Coordinate methods will return this if details are missing
     */
    public final static int UNDEFINED_COORDINATE = -1;

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
     * Way to verify if details of click are included to event
     * @return true if details are included, false if those could not have been included
     */
    public boolean hasDetails() {
        return null != details;
    }

    /**
     * Cursor X in client's coordinate
     * @return X coordinate, or -1 if undefined
     */
    public int getClientX() {
        if (hasDetails()) {
            return details.getClientX();
        } else {
            return UNDEFINED_COORDINATE;
        }
    }

    /**
     * Cursor Y in client's coordinate
     * @return Y coordinate, or -1 if undefined
     */
    public int getClientY() {
        if (hasDetails()) {
            return details.getClientY();
        } else {
            return UNDEFINED_COORDINATE;
        }
    }

    /**
     * Cursor X in relative coordinate
     * @return X coordinate, or -1 if undefined
     */
    public int getRelativeX() {
        if (hasDetails()) {
            return details.getRelativeX();
        } else {
            return UNDEFINED_COORDINATE;
        }
    }

    /**
     * Cursor Y in relative coordinate
     * @return Y coordinate, or -1 if undefined
     */
    public int getRelativeY() {
        if (hasDetails()) {
            return details.getRelativeY();
        } else {
            return UNDEFINED_COORDINATE;
        }
    }

    /**
     * Check if Alt key was pressed down when clicked
     * @return true if Alt key was pressed down. false if not or undefined
     */
    public boolean isAltKey() {
        if (hasDetails()) {
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
        if (hasDetails()) {
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
        if (hasDetails()) {
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
        if (hasDetails()) {
            return details.isShiftKey();
        } else {
            return false;
        }
    }
}
