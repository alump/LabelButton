package org.vaadin.alump.labelbutton;

import com.vaadin.ui.Label;
import junit.framework.Assert;
import org.junit.Test;

// JUnit tests here
public class LabelButtonTest {

	@Test
	public void simpleExtensionTest() {
		Label label = new Label("Foo bar");
		Assert.assertNull(LabelButtonExtension.get(label, false));
		LabelButtonExtension extension = LabelButtonExtension.get(label);
		Assert.assertNotNull(extension);
		Assert.assertEquals(extension, LabelButtonExtension.get(label, false));
        Assert.assertEquals(label, extension.getLabel());

		LabelButtonExtension.remove(label);
		Assert.assertNull(LabelButtonExtension.get(label, false));
        Assert.assertNull(extension.getLabel());
	}
}
