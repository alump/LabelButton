package org.vaadin.alump.labelbutton.client.shared;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.ServerRpc;

/**
 * Created by alump on 10/07/15.
 */
public interface LabelButtonServerRpc extends ServerRpc {

    void onClick(MouseEventDetails mouseEventDetails);
}
