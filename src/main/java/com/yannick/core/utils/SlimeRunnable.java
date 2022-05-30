package com.yannick.core.utils;

public interface SlimeRunnable {
    void run();

    default void onComplete() {

    }

    default void onCancel() {

    }
}
