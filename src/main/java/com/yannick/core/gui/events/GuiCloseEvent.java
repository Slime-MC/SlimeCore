package com.yannick.core.gui.events;

import com.yannick.core.gui.Gui;
import com.yannick.core.gui.GuiManager;
import org.bukkit.entity.Player;

public class GuiCloseEvent extends GuiEvent {
    public GuiCloseEvent(GuiManager manager, Gui gui, Player player) {
        super(manager, gui, player);
    }
}
