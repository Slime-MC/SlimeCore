package io.slimemc.slimecore.gui.methods;

import io.slimemc.slimecore.gui.events.GuiPageEvent;

public interface Pageable {
    void onPageChange(GuiPageEvent event);
}
