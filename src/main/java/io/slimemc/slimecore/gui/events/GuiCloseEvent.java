package io.slimemc.slimecore.gui.events;

import io.slimemc.slimecore.gui.Gui;
import io.slimemc.slimecore.gui.GuiManager;
import org.bukkit.entity.Player;

public class GuiCloseEvent extends GuiEvent {
    public GuiCloseEvent(GuiManager manager, Gui gui, Player player) {
        super(manager, gui, player);
    }
}
