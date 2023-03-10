package com.example.solceller;
import Model.Graph;
import Model.Type;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HourByHour implements iChangeScene
{
    SearchSite date = new SearchSite();
    Button buttonBack = new Button("Back");
    Button btnAccept = new Button("Enter");
    Graph myGraph = new Graph("Hour by hour", "Hours", "Production", Type.BAR_CHART);
    Graph tempGraph = new Graph("Hour by hour", "Hours", "Production", Type.BAR_CHART);
    int choiceIndex;
    int day;
    int month;
    String choiceID;
    ArrayList<Entry> data;


    public Scene createHourByHourScene()
    {
        // create the content for the hour by hour view
        AnchorPane anchorPane = new AnchorPane();

        buttonBack.setPrefWidth(75);
        buttonBack.setLayoutX(10);


        Scene scene = new Scene(anchorPane, 1000, 500);
        String css = this.getClass().getResource("/basicstyle.css").toExternalForm();
        scene.getStylesheets().add(css);

        ArrayList<String> choices = new ArrayList<>();
        for (int i = 0; i < FileHelper.sites.size(); i++)
        {
            choices.add(i+1 + ". " + FileHelper.sites.get(i).getSiteID());
        }
        ChoiceBox<String> choiceBox = new ChoiceBox<>(FXCollections.observableList(choices));

            choiceBox.setId("choice");
        choiceBox.setOnAction(actionEvent ->
        {
            choiceIndex = Integer.parseInt(choiceBox.getValue().substring(0, choiceBox.getValue().indexOf(".")))-1;
            choiceID = choiceBox.getValue().substring(choiceBox.getValue().indexOf(" ")+1);
            System.out.println("choiceIndex " + choiceIndex);
            System.out.println("choiceID " + choiceID);
        });

        date.searchSite(anchorPane,choiceBox);
        DatePicker datePicker = new DatePicker();
        datePicker.setLayoutY(150);
        datePicker.setLayoutX(10);
        datePicker.setOnAction(actionEvent ->
        {
            String formattedDate = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            day = Integer.parseInt(formattedDate.substring(0, formattedDate.indexOf("-")));
            month = Integer.parseInt(formattedDate.substring(formattedDate.indexOf("-") + 1, formattedDate.indexOf("-") + 3));

        });

        btnAccept.setOnAction(actionEvent ->
        {
            data = new ArrayList<>(FileHelper.getEntry(FileHelper.sites.get(choiceIndex), month, day));
            myGraph.getChart().getData().clear();
            myGraph.CreateSeriesHour("SID: " + choiceID, data);
            anchorPane.getChildren().remove(myGraph.getChart());
            anchorPane.getChildren().remove(tempGraph.getChart());
            anchorPane.getChildren().add(myGraph.getChart());
        });


        btnAccept.setPrefWidth(75);
        btnAccept.setLayoutY(330);
        btnAccept.setLayoutX(10);
        Label indTDate = new Label("Enter Date");
        indTDate.setLayoutY(130);
        indTDate.setLayoutX(10);

        anchorPane.getChildren().addAll(buttonBack, indTDate, btnAccept, datePicker, choiceBox, tempGraph.getChart());

        return scene;
    }

    /**
     * This section changes back to the main scene
     * @param stage
     * @param scene
     */
    @Override
    public void changescene(Stage stage, Scene scene)
    {
        buttonBack.setOnAction(event -> stage.setScene(scene));
    }
}
