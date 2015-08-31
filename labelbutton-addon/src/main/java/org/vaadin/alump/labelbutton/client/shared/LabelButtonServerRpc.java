package org.vaadin.alump.labelbutton.client.shared;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.ServerRpc;

/**
 * ServerRPC implemented by LabelButton
 */
public interface LabelButtonServerRpc extends ServerRpc {

    void onClick(MouseEventDetails mouseEventDetails);
}
