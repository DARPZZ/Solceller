package com.example.solceller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;

public class MainMenu implements iChangeScene
{
    DatePicker datePicker = new DatePicker();
HourByHour hourByHour = new HourByHour();
Month month = new Month();
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
        return label;
    }

        //endregion
    private Button buttonHByH = new Button();
    private Button buttonMonth = new Button();
    private Button bestWorstButton = new Button();
    private final ImageView imageView = new ImageView();

public void changescene(Stage stage, Scene scene)
{
    hourByHour.changescene(stage,scene);
    month.changescene(stage,scene);
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
            Scene createmånedScene = month.createMonthScene();
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
public DatePicker findDate()
{
    datePicker.setOnKeyPressed(new EventHandler<KeyEvent>()
    {
        @Override
        public void handle(KeyEvent keyEvent)
        {
            if (keyEvent.getCode().equals(KeyCode.ENTER)){

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
                String formattedValue = datePicker.getValue().format(formatter);
                System.out.println(formattedValue);

            }
        }
    });
 return datePicker;
}
}
