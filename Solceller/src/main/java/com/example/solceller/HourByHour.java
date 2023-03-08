package com.example.solceller;
import Model.Graph;
import Model.Type;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.print.Collation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class HourByHour implements iChangeScene
{
    Button buttonBack = new Button("Back");
    Button btnAccept = new Button("Enter");
    Graph MyGraph = new Graph("Hour by hour", "Hours", "Production", Type.BAR_CHART);
    int choiceIndex;
    String choiceID;
    ArrayList<Entry> data;


    public Scene createHourByHourScene()
    {
        buttonBack.setPrefWidth(50);
        // create the content for the hour by hour view
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 950, 700);

        ArrayList<String> choices = new ArrayList<>();
        for (int i = 0; i < HelloController.sites.size(); i++)
        {
            choices.add(i + ". " + HelloController.sites.get(i).getSiteID());
        }
        ComboBox<String> choiceBox = new ComboBox<>(FXCollections.observableList(choices));

        choiceBox.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                choiceIndex = Integer.parseInt(choiceBox.getValue().substring(0, choiceBox.getValue().indexOf(".")));
                choiceID = choiceBox.getValue().substring(choiceBox.getValue().indexOf(" ")+1);
                System.out.println("choiceIndex " + choiceIndex);
                System.out.println("choiceID " + choiceID);
            }
        });

        choiceBox.setLayoutX(20);
        choiceBox.setLayoutY(70);
        choiceBox.setPrefWidth(100);


        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                String formattedDate = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                int day = Integer.parseInt(formattedDate.substring(choiceIndex, formattedDate.indexOf("-")));
                int month = Integer.parseInt(formattedDate.substring(formattedDate.indexOf("-") + 1, formattedDate.indexOf("-") + 3));

                System.out.println(day);
                System.out.println(month);

                // Mangler null check
                try
                {
                    data = new ArrayList<>(FileHelper.getEntry(HelloController.sites.get(choiceIndex), month, day));

                }
                catch (Exception e)
                {
                    System.out.println("Ingen data for den valgte dato/SID");
                }
                     // Virker. Testet med den 15. december 2022
            }
        });



        btnAccept.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                MyGraph.getChart().getData().clear();
                MyGraph.CreateSeries(choiceID, data);
            }
        });


        buttonBack.setPrefWidth(50);
        btnAccept.setPrefWidth(50);
        btnAccept.setLayoutY(100);
        btnAccept.setLayoutX(20);
        MyGraph.getChart().setLayoutX(350);


        anchorPane.getChildren().addAll(buttonBack, btnAccept, datePicker, choiceBox, MyGraph.getChart());


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
