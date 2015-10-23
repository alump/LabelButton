package org.vaadin.alump.labelbutton;

/**
 * These style name apply to LabelButton, but do not apply to Labels extended with LabelButtonExtension.
 */
public interface LabelButtonStyles {

    /**
     * Adds pencil at the end of label when in clickable mode. To be used when clicking label open some edit view.
     * Requires FontAwesome.
     */
    String PENCIL_WHEN_CLICKABLE = "lb-pencil";

    /**
     * Showns pointer cursor when mouse on top of clickable label
     */
    String POINTER_WHEN_CLICKABLE = "lb-pointer";

    /**
     * Underlines label's text when hovering in clickable mode.
     */
    String HOVER_UNDERLINE_WHEN_CLICKABLE = "lb-hover-underline";

    /**
     * Underlines label's text when clickable mode (also in hover mode).
     */
    String UNDERLINE_WHEN_CLICKABLE = "lb-underline";

    /**
     * This will prevent user from selecting the text, that can prevent some click events
     */
    String DISABLE_USER_SELECTION = "lb-preventselect";
}
