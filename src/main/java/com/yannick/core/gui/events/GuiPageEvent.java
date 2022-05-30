package com.yannick.core.gui.events;

import com.yannick.core.gui.Gui;
import com.yannick.core.gui.GuiManager;

public class GuiPageEvent {
    final Gui gui;
    final GuiManager manager;
    final int lastPage;
    final int page;

    public GuiPageEvent(Gui gui, GuiManager manager, int lastPage, int page) {
        this.gui = gui;
        this.manager = manager;
        this.lastPage = lastPage;
        this.page = page;
    }
}
