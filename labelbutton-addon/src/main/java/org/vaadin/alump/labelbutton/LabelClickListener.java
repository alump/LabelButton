package org.vaadin.alump.labelbutton;

/**
 * Interface to implement if you want to listen clicks of LabelButton
 */
public interface LabelClickListener {

    /**
     * Called when label is clicked
     * @param event Event information
     */
    void onLabelClick(LabelClickEvent event);
}
