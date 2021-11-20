package io.slimemc.slimecore.gui.events;

import io.slimemc.slimecore.gui.Gui;
import io.slimemc.slimecore.gui.GuiManager;
import org.bukkit.entity.Player;

public class GuiOpenEvent extends GuiEvent {
    public GuiOpenEvent(GuiManager manager, Gui gui, Player player) {
        super(manager, gui, player);
    }
}
