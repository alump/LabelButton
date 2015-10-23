package org.vaadin.alump.labelbutton;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.Label;

/**
 * Event fired when Label is clicked
 */
public class LabelClickEvent {

    private final Label label;
    private final MouseEventDetails details;

    public static final int UNDEFINED_COORDINATE = -1;

    public LabelClickEvent(Label label) {
        this(label, null);
    }

    public LabelClickEvent(Label label, MouseEventDetails details) {
        this.label = label;
        this.details = details;
    }

    /**
     * Get clicked Label
     * @return Label clicked
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Get clicked LabelButton (if clicked label was a LabelButton)
     * @return LabelButton clicked, or null if clicked label wasn't a LabelButton
     */
    public LabelButton getLabelButton() {
        if(label instanceof LabelButton) {
            return (LabelButton)label;
        } else {
            return null;
        }
    }

    /**
     * Cursor X in client's coordinate
     * @return X coordinate, or -1 if undefined
     */
    public int getClientX() {
        if (null != details) {
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
        if (null != details) {
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
        if (null != details) {
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
        if (null != details) {
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
