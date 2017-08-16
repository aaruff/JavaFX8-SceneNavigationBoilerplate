package com.anwarruff.controller;

import com.anwarruff.scene.SceneNavigator;
import com.anwarruff.scene.SceneName;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstController implements Initializable, Swappable {
    @FXML
    Button nextButton;

    @FXML
    Label notifyLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialized first");
    }

    @Override
    public void onLoad(SceneName previousSceneName) {
        if (previousSceneName != SceneName.FIRST) {
            notifyLabel.setText("Previous: " + previousSceneName.toString());
        }
    }

    @Override
    public void onUnload() {

    }

    @FXML
    private void nextButtonClicked() {
        SceneNavigator.setScene(SceneName.LAST);
    }

}
