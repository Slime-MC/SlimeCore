package io.slimemc.slimecore.gui.events;

import io.slimemc.slimecore.gui.Gui;
import io.slimemc.slimecore.gui.GuiManager;
import org.bukkit.entity.Player;

public abstract class GuiEvent {
    public final GuiManager manager;
    public final Gui gui;
    public final Player player;

    public GuiEvent(GuiManager manager, Gui gui, Player player) {
        this.manager = manager;
        this.gui = gui;
        this.player = player;
    }
}
