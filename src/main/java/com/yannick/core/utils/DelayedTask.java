package com.yannick.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class DelayedTask {
    private SlimeRunnable runnable;
    private int lastId = -1;
    private boolean async;
    private int lastRepeatingCount;
    private Plugin plugin;

    public DelayedTask(Plugin plugin, SlimeRunnable runnable) {
        this.plugin = plugin;
        this.runnable = runnable;
    }

    public DelayedTask setRunnable(SlimeRunnable runnable) {
        this.runnable = runnable;
        return this;
    }

    public SlimeRunnable getRunnable() {
        return runnable;
    }

    public DelayedTask async() {
        return setAsync(true);
    }

    public DelayedTask setAsync(boolean async) {
        this.async = async;
        return this;
    }

    public DelayedTask run() {
        if (async) {
            lastId = Bukkit.getScheduler().runTask(plugin, () -> {
                runnable.run();
                runnable.onComplete();
            }).getTaskId();
        } else {
            lastId = Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                runnable.run();
                runnable.onComplete();
            }).getTaskId();
        }
        return this;
    }

    public DelayedTask run(long delay) {
        if (async) {
            lastId = Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
                runnable.run();
                runnable.onComplete();
            }, delay).getTaskId();
        } else {
            lastId = Bukkit.getScheduler().runTaskLater(plugin, () -> {
                runnable.run();
                runnable.onComplete();
            }, delay).getTaskId();
        }
        return this;
    }

    public DelayedTask repeat(long initialDelay, long repeatingDelay) {
        if (async) {
            lastId = Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, runnable::run, initialDelay, repeatingDelay);
        } else {
            lastId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, runnable::run, initialDelay, repeatingDelay);
        }
        return this;
    }

    public DelayedTask repeat(long initialDelay, long repeatingDelay, int count) {
        lastRepeatingCount = 0;
        if (async) {
            lastId = Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, () -> {
                lastRepeatingCount++;
                runnable.run();
                if (lastRepeatingCount >= count) {
                    Bukkit.getScheduler().cancelTask(lastId);
                    runnable.onComplete();
                }
            }, initialDelay, repeatingDelay);
        } else {
            lastId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                lastRepeatingCount++;
                runnable.run();
                if (lastRepeatingCount >= count) {
                    Bukkit.getScheduler().cancelTask(lastId);
                    runnable.onComplete();
                }
            }, initialDelay, repeatingDelay);
        }
        return this;
    }

    public DelayedTask cancel() {
        return cancel(lastId);
    }

    public DelayedTask cancel(int id) {
        Bukkit.getScheduler().cancelTask(id);
        runnable.onCancel();
        return this;
    }

    public int getLastId() {
        return lastId;
    }

    public int getLastRepeatingCount() {
        return lastRepeatingCount;
    }
}
