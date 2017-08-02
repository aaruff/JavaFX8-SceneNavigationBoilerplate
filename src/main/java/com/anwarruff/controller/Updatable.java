package com.anwarruff.controller;

import com.anwarruff.scene.SceneName;

public interface Updatable {
    /**
     * Performs any updates required when navigating to an Updatable controller.
     * @param previousSceneName The previous scene name
     */
    void onNavigateUpdate(SceneName previousSceneName);
}
