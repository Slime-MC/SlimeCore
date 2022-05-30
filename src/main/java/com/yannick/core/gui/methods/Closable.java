package com.yannick.core.gui.methods;

import com.yannick.core.gui.events.GuiCloseEvent;

public interface Closable {
    void onClose(GuiCloseEvent event);
}
