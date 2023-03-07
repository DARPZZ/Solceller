package com.example.solceller;

import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HourByHour implements iChangeScene
{
    Button buttonBack = new Button("Back");

    public Scene createHourByHourScene()
    {

        // create the content for the hour by hour view
        AnchorPane anchorPane = new AnchorPane();

        buttonBack.setPrefWidth(50);

        anchorPane.getChildren().add(buttonBack);

        Scene scene = new Scene(anchorPane, 800, 600);

        return scene;
    }


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
