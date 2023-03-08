package com.example.solceller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class Månede implements iChangeScene
{
    Button buttonBack = new Button("Back");
    public Scene createmånedScene() {
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 950, 700);
            anchorPane.getChildren().addAll(buttonBack);
        return scene;
    }
    /**
     * This section cchanges back to the main scene
     * Can you also change scene to other scenes
     * @param stage
     * @param scene
     */
    @Override
    public void changescene(Stage stage, Scene scene)
    {
        buttonBack.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(scene);
            }
        });
    }
}
