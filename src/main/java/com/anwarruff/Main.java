package com.anwarruff;

import com.anwarruff.scene.SceneName;
import com.anwarruff.scene.SceneNavigator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HashMap<SceneName, String> scenes = new HashMap<>();
        scenes.put(SceneName.FIRST, "/firstPane.fxml");
        scenes.put(SceneName.LAST, "/lastPane.fxml");

        SceneNavigator.initialize(primaryStage, scenes);
        SceneNavigator.setScene(SceneName.FIRST);
        SceneNavigator.show();
    }
}
