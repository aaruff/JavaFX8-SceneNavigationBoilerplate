package com.anwarruff.scene;

import com.anwarruff.controller.Updatable;
import com.anwarruff.ui.MainPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;

public class SceneNavigator {
    private static final HashMap<SceneName, Updatable> updatableControllers = new HashMap<>();
    private static final HashMap<SceneName, Pane> panes = new HashMap<>();

    private static SceneName currentSceneName;
    private static MainPane mainPane;

    private static boolean scenesLoaded = false;

    /**
     * Lazy loads all scenes, and sets the current scene to firstSceneName.
     * @param mainPane The main stacked pane use as a stage for all scenes
     * @param firstSceneName The first scene to be displayed
     */
    public static void initialize(MainPane mainPane, SceneName firstSceneName) {
        if (scenesLoaded) {
            return;
        }
        SceneNavigator.mainPane = mainPane;
        currentSceneName = firstSceneName;

        loadAllScenes();
        scenesLoaded = true; // Can't use set setScene() until scene loaded flag is set to true

        setScene(firstSceneName);
    }

    /**
     * Load all scenes used in this application.
     */
    private static void loadAllScenes() {
        loadScene(SceneName.FIRST, "/firstPane.fxml");
        loadScene(SceneName.LAST, "/lastPane.fxml");
    }

    private static void loadScene(SceneName sceneName, String fxmlResource) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneNavigator.class.getResource(fxmlResource));
            Pane pane = loader.load();
            pane.prefHeightProperty().bind(mainPane.heightProperty());
            pane.prefWidthProperty().bind(mainPane.widthProperty());
            panes.put(sceneName, pane);
            updatableControllers.put(sceneName, loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the next scene specified by nextSceneName.
     * @param nextSceneName The name of the next scene.
     */
    public static void setScene(SceneName nextSceneName) {
        if (!scenesLoaded) {
            return;
        }
        Pane currentPane = panes.get(currentSceneName);
        mainPane.getChildren().remove(currentPane);

        Pane nextPane = panes.get(nextSceneName);
        mainPane.getChildren().add(nextPane);

        Updatable nextController = updatableControllers.get(nextSceneName);
        nextController.onNavigateUpdate(currentSceneName);

        currentSceneName = nextSceneName;
    }
}
