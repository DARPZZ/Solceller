package com.example.solceller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FileHelper.initializeFromFile();
        stage.setResizable(false);
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 1000, 500);
        MainMenu mainMenu = new MainMenu();
        mainMenu.changescene(stage, anchorPane.getScene());
        String css = this.getClass().getResource("/basicstyle.css").toExternalForm();
        scene.getStylesheets().add(css);
        anchorPane.getChildren().addAll(mainMenu.getButton(),mainMenu.getbuttonMonth(),mainMenu.getBestWorstButton(), mainMenu.getImageView(), mainMenu.getLabelHeader(scene));
        stage.setTitle("Solar cell");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}