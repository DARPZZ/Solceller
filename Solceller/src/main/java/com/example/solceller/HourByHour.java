package com.example.solceller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HourByHour
{
    public Scene createHourByHourScene() {

        // create the content for the hour by hour view
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 800, 600);

        return scene;
    }

}
