package com.example.solceller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        stage.setResizable(false);
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 950, 700);
        MainMenu mainMenu = new MainMenu();
         mainMenu.changescene(stage, anchorPane.getScene());
        anchorPane.getChildren().addAll(mainMenu.getButton(),mainMenu.getbuttonMonth(),mainMenu.getBestWorstButton(), mainMenu.getImageView());
        stage.setTitle("Solar cell");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args)
    {
        launch();
    }
}