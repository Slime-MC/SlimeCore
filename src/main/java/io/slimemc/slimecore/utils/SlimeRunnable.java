package io.slimemc.slimecore.utils;

public interface SlimeRunnable {
    void run();

    default void onComplete() {

    }

    default void onCancel() {

    }
}
