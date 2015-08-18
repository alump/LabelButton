package org.vaadin.alump.labelbutton;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

// JUnit tests here
public class LabelButtonTest {

    @Test
    public void basicLabelButtonStates() {
        LabelButton labelButton = new LabelButton();
        labelButton.setCaption("caption");
        labelButton.setValue("value");

        Assert.assertEquals("caption", labelButton.getCaption());
        Assert.assertEquals("value", labelButton.getValue());

        final AtomicBoolean listenerCalled = new AtomicBoolean(false);
        labelButton.addLabelClickListener(new LabelClickListener() {
            @Override
            public void onLabelClick(LabelClickEvent event) {
                listenerCalled.set(true);
            }
        });
        labelButton.click();
        Assert.assertTrue(listenerCalled.get());

        checkClickListener(labelButton);
    }

    @Test
    public void basicLabelWithButtonsTests() {
        LabelWithButton labelButton = new LabelWithButton();

        checkClickListener(labelButton);
    }

    private void checkClickListener(final LabelButton labelButton) {
        final AtomicBoolean listenerCalled = new AtomicBoolean(false);
        LabelClickListener listener = new LabelClickListener() {
            @Override
            public void onLabelClick(LabelClickEvent event) {
                Assert.assertNotNull(event);
                Assert.assertEquals(labelButton, event.getLabel());

                Assert.assertFalse(event.hasDetails());
                Assert.assertEquals(-1, event.getClientX());
                Assert.assertEquals(-1, event.getClientY());
                Assert.assertEquals(-1, event.getRelativeX());
                Assert.assertEquals(-1, event.getRelativeY());
                Assert.assertFalse(event.isAltKey());
                Assert.assertFalse(event.isCtrlKey());
                Assert.assertFalse(event.isMetaKey());
                Assert.assertFalse(event.isShiftKey());
                listenerCalled.set(true);
            }
        };
        labelButton.click();

        labelButton.addLabelClickListener(listener);
        labelButton.click();
        Assert.assertTrue("Listener not called", listenerCalled.get());

        listenerCalled.set(false);
        labelButton.removeLabelClickListener(listener);
        labelButton.click();
        Assert.assertFalse("Listener called when should not", listenerCalled.get());
    }
}
