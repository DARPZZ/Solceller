package com.example.solceller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BestWorst implements iChangeScene
{
    Button buttonBack = new Button("Back");

    public Scene createBestWorstScene()
    {

        Label label = new Label("ko");
        label.setLayoutX(50);
        label.setLayoutY(50);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(buttonBack,label);
        Scene scene = new Scene(anchorPane, 950, 700);
        return scene;
    }

    /**
     * This section cchanges back to the main scene
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
