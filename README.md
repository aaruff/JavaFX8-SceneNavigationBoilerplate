# JavaFX 8 Scene Navigation Boilerplate

Boilerplate code for scene navigation in JavaFX 8. 

### Implementation 

Scene navigation is implemented by maintaining a single static reverence to a 
StackPane. 
And when a new scene is to be displayed, a `SceneName` is used to reference and load the corresponding 
Pane and Controller.


### Configuration 

##### Step 1: Name each scene

Modify the `SceneName` enum, and create a name for each scene in your application.  This name is used to map each
 Pane to Controller. For example, if you had two scenes First and Last, you would modify it as follows.
```
public enum SceneName {
    FIRST, LAST
}
``` 

##### Step 2: Associate each scene with a Pane and Controller 

Modify the `SceneNavigator.loadAllScenes()` function declare all of the fxml Panes used in your application, with
the `loadScene(SceneName sceneName, String fxmlResource)` function. For example, if your application uses two
FXML Pane files, you would load them as follows:
```
    private static void loadAllScenes() {
        loadScene(SceneName.FIRST, "/firstPane.fxml");
        loadScene(SceneName.LAST, "/lastPane.fxml");
    }
```

##### Step 3: Have all Controllers implement the Updatable interface

All Controllers must implement the `Updateable` interface, which is used to provide all controllers with a 
`onNavigateUpdate(SceneName previousSceneName)` 
method. When navigating to a new scene, the `onNavigateUpdate` function is called with the previous scene name. 
 Note: When the first scene is loaded, `onNavigateUpdate`  sets the previous scene name to the current scene name.
 Below is an example of an FXML Controller implementing `Updateable`:
```
public class FirstController implements Updatable, Initializable {
    @Override
    public void onNavigateUpdate(SceneName previousSceneName) {
        notifyLabel.setText("Previous: " + previousSceneName.toString());
    }
    
    ...
}

```
 
## How to use 

To switch from one scene to another simply call the `SceneNavigator.setScene(SceneName.Name)` function. For example, 
to switch to scene `SceneName.Last` when a button click event is handled by `nextButtonClicked()`, put the `setScene`
function call in the handler as follows: 
```
public class FirstController implements Updatable, Initializable {
    @FXML
    private void nextButtonClicked() {
        SceneNavigator.setScene(SceneName.LAST);
    }
    
    ...
}
```

### Building

```
gradlew build
```

### Run

```
gradlew run
```

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details



