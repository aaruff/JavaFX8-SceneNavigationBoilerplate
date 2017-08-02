package com.anwarruff.controller;

import com.anwarruff.scene.SceneNavigator;
import com.anwarruff.scene.SceneName;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LastController implements Initializable, Updatable {
    @FXML
    Button nextButton;

    @FXML
    Label notifyLabel;

    @Override
    public void onNavigateUpdate(SceneName previousSceneName) {
        notifyLabel.setText("Previous: " + previousSceneName.toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialized second");
    }

    @FXML
    private void previousButtonClicked() {
        SceneNavigator.setScene(SceneName.FIRST);
    }
}
