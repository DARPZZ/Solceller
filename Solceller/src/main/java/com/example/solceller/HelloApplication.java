package com.example.solceller;

import javafx.application.Application;
import javafx.beans.binding.StringExpression;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class HelloApplication extends Application
{
    private static ArrayList<Scene> scenes = new ArrayList<>();


    @Override
    public void start(Stage stage) throws IOException, URISyntaxException
    {

        stage.setResizable(false);
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 950, 700);
         MainMenu mainMenu = new MainMenu();
         mainMenu.changescene(stage, anchorPane.getScene());
        anchorPane.getChildren().addAll(mainMenu.getButton(),mainMenu.getbuttonMonth(),mainMenu.getBestWorstButton(), mainMenu.getImageView());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args)
    {
        launch();
    }
}