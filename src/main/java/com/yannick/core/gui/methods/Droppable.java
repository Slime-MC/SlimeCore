package com.yannick.core.gui.methods;

import com.yannick.core.gui.events.GuiDropItemEvent;

public interface Droppable {
    boolean onDrop(GuiDropItemEvent event);
}
