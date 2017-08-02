package com.anwarruff.ui;

import com.anwarruff.scene.SceneNavigator;
import com.anwarruff.scene.SceneName;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainPane extends StackPane {

    public MainPane() {
        SceneNavigator.initialize(this, SceneName.FIRST);
    }

    /**
     * Changes the scene to the Node specified.
     * @param node Node object to set
     */
    public void setPane(Node node) {
        this.getChildren().setAll(node);
    }
}
