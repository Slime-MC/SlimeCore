package io.slimemc.slimecore.gui.methods;

import io.slimemc.slimecore.gui.events.GuiDropItemEvent;

public interface Droppable {
    boolean onDrop(GuiDropItemEvent event);
}
