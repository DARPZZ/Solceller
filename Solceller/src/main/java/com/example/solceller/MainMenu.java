package com.example.solceller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenu implements iChangeScene
{
HourByHour hourByHour = new HourByHour();
Month month = new Month();
BestWorst bestWorst = new BestWorst();

    //region getter and attributes
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
        imageView.setFitHeight(500);
        imageView.setFitWidth(1000);
        imageView.setY(35);
        imageView.setX(1);
        return imageView;
    }


    public Label getLabelHeader(Scene scene)
    {
        Label label = new Label("Bæredygtig Energi fra Solceller");
        label.setLayoutX((scene.getWidth() / 2 -225));
        label.setLayoutY(50);
        label.setId("title");
        return label;
    }

        //endregion
    private Button buttonHByH = new Button();
    private Button buttonMonth = new Button();
    private Button bestWorstButton = new Button();
    private final ImageView imageView = new ImageView();

    /**
     * Changes from the diffrent scenes
     * @param stage Getting the diffrent stages
     * @param scene Getting the diffrent scenes
     */
    public void changescene(Stage stage, Scene scene)
{
    hourByHour.changescene(stage,scene);
    month.changescene(stage,scene);
    bestWorst.changescene(stage,scene);
    buttonHByH.setOnAction(event ->
    {
        {
            Scene sceneHourByHour = hourByHour.createHourByHourScene();
            stage.setScene(sceneHourByHour);
    }
});
    buttonMonth.setOnAction(event ->
    {
        Scene createmånedScene = month.createMonthScene();

        stage.setScene(createmånedScene);
    });
    bestWorstButton.setOnAction(event ->
    {
        Scene createBestWorstScene = bestWorst.createBestWorstScene();

        stage.setScene(createBestWorstScene);
    });
}

}
