package com.example.solceller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenu implements iChangeScene
{
HourByHour hourByHour = new HourByHour();
Månede månede = new Månede();
BestWorst bestWorst = new BestWorst();

    //region getter and buttons attributes
    public Button getButton()
    {
        buttonHByH.setPrefWidth(200);
        buttonHByH.setPrefHeight(30);
        buttonHByH.setLayoutX(100);
        buttonHByH.setText("Hour by Hour");
        return buttonHByH;
    }
    public Button getbuttonMonth()
    {

        buttonMonth.setPrefWidth(200);
        buttonMonth.setPrefHeight(30);
        buttonMonth.setLayoutX(350);
        buttonMonth.setText("Month by Month");
        return buttonMonth;
    }

    public Button getBestWorstButton()
    {

        bestWorstButton.setPrefWidth(200);
        bestWorstButton.setPrefHeight(30);
        bestWorstButton.setLayoutX(600);
        bestWorstButton.setText("Show best and worst");
        return bestWorstButton;
    }
    public ImageView getImageView() throws  FileNotFoundException
    {
        Image img = new Image(new FileInputStream("Solceller/src/main/java/Billeder/solar-cells-491701.jpg"));
        ImageView imageView = new ImageView();
        imageView.setImage(img);
        imageView.setFitHeight(675);
        imageView.setFitWidth(1000);
        imageView.setY(35);
        imageView.setX(1);
        return imageView;
    }
        //endregion
    private Button buttonHByH = new Button();
    private Button buttonMonth = new Button();
    private Button bestWorstButton = new Button();
    private final ImageView imageView = new ImageView();

public void changescene(Stage stage, Scene scene)
{
    hourByHour.changescene(stage,scene);
    månede.changescene(stage,scene);
    bestWorst.changescene(stage,scene);
    buttonHByH.setOnAction(new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            {
                // create a new scene for the hour by hour view
                Scene sceneHourByHour = hourByHour.createHourByHourScene();
                // set the new scene on the primary stage
                stage.setScene(sceneHourByHour);
        }
    }
    });
    buttonMonth.setOnAction(new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            Scene createmånedScene = månede.createmånedScene();
            // set the new scene on the primary stage
            stage.setScene(createmånedScene);
        }
    });
    bestWorstButton.setOnAction(new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            Scene createBestWorstScene = bestWorst.createBestWorstScene();
            // set the new scene on the primary stage
            stage.setScene(createBestWorstScene);
        }

    });
}
}
