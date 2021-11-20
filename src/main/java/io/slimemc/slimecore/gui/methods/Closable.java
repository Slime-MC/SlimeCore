package io.slimemc.slimecore.gui.methods;

import io.slimemc.slimecore.gui.events.GuiCloseEvent;

public interface Closable {
    void onClose(GuiCloseEvent event);
}
