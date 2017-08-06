# JavaFX 8 Scene Navigation Boilerplate

Boilerplate code for scene navigation in JavaFX 8. 

### Implementation 

Scene navigation is implemented by maintaining a collection of scenes stored in the `SceneNavigator` that manages
navigating from one scene to the next. Each scene is given a name defined in the enum `SceneName`. The `SceneName` is
used to map scenes to their controllers, and vice versa.


### Configuration 

##### Step 1: Naming Scenes

Scene names can be added by modifying the `SceneName` enum, and create a name for each scene in your application.  This name is used to map each
 Scene to Controller. For example, if you had two scenes First and Last, you would modify it as follows.
```
public enum SceneName {
    FIRST, LAST
}
``` 

##### Step 2: Adding FXML Scene Layouts
All scene layouts must specify their controllers. For example, if you had a scene which will be managed
by controller "com.anwarruff.controller.FirstController", then you would specify it in your fxml file as follows:
```
<StackPane fx:controller="com.anwarruff.controller.FirstController">
  <BorderPane>
     <center>
      <Label text="Screen: First">
     </center>
     <bottom>
        <Button text="Next" />
     </bottom>
  </BorderPane>
</StackPane>

```


##### Step 3: Associating Scenes with FXML Panes and Controllers

To associate Scenes and Controllers simply modify the `scenes` map by adding a scene name and its corresponding 
FXML file location. For example, if your application has two Scenes FIRST and LAST, with the corresponding FXML files 
"/firstPane.fxml" and "lastPane.fxml" you would call `scenes.put(name, location)` as follows:
```
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HashMap<SceneName, String> scenes = new HashMap<>();
        scenes.put(SceneName.FIRST, "/firstPane.fxml");
        scenes.put(SceneName.LAST, "/lastPane.fxml");

        SceneNavigator.initialize(primaryStage, scenes);
        SceneNavigator.setScene(SceneName.FIRST);
        SceneNavigator.show();
    }
    
    ...
}
```

##### Step 4: Controller Setup 

To use a controller with the SceneNavigator it must implement the `Updateable` interface, which is used to provide 
controllers with a `onNavigateUpdate(SceneName previousSceneName)` method. When navigating to a new scene, the 
`onNavigateUpdate` function is called with the previous scene name. This would allow a controller to determine the 
source from which the user has navigated from. Below is an example of an JavaFX Controller implementing `Updateable`, 
and updating a `Label` with the previous screen name:
```
public class FirstController implements Updatable, Initializable {
    @Override
    public void onNavigateUpdate(SceneName previousSceneName) {
        notifyLabel.setText("Previous: " + previousSceneName.toString());
    }
    
    ...
}

```

##### Step 5: Display the First Scene

To set the first scene to be displayed when the JavaFX app starts, simply call `SceneNavigator.setScene(sceneName)`
 with the `SceneName` for the first scene. 
For example, if you wanted `SceneName.FIRST` to be the first scene to load on startup, then you would call 
`SceneNavigator.setScene(SceneName.FIRST)` as follows:
```
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HashMap<SceneName, String> scenes = new HashMap<>();
        scenes.put(SceneName.FIRST, "/firstPane.fxml");
        scenes.put(SceneName.LAST, "/lastPane.fxml");

        SceneNavigator.initialize(primaryStage, scenes);
        SceneNavigator.setScene(SceneName.FIRST);
        SceneNavigator.show();
    }
    
    ...
}
```
 
##### Step 6: Switching Scenes

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

### Running the Demo
Use the following command to run the demo 
```
gradlew run
```
On startup you will see the first scene (SceneName.FIRST): 
![Image of first screen](http://anwarruff.com/first-1/)

When the next button is selected the last scene (SceneName.LAST) will be displayed. 
![Image of last screen](http://anwarruff.com/second_1/)

Both scenes display the name of the previous screen displayed before it. If the previous button is selected
you will return to the first scene, but an additional message will be displayed naming the previous stage (Last).
![Image of first screen](http://anwarruff.com/first_2/)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details



