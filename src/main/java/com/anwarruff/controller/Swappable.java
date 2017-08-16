package com.anwarruff.controller;

import com.anwarruff.scene.SceneName;

/**
 * Interface for controllers that support being loaded and unloaded on the fly.
 * This primarily useful when keeping a cached collection of pre loaded controllers, for each scene. When a new
 * controller is loaded the onLoad() method is called. When unloaded, the onUnload() method is called.
 */
public interface Swappable {
    /**
     * Performs any updates required when navigating to an Swappable controller.
     * @param previousSceneName The previous scene name
     */
    void onLoad(SceneName previousSceneName);

    /**
     * Executes any clean up required when unloading a controller.
     */
    void onUnload();
}
