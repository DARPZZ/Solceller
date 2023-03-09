package com.example.solceller;
import Model.Graph;
import Model.Type;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HourByHour implements iChangeScene
{
    findDate date = new findDate();
    Button buttonBack = new Button("Back");
    Button btnAccept = new Button("Enter");
    Graph MyGraph = new Graph("Hour by hour", "Hours", "Production", Type.BAR_CHART);
    int choiceIndex;
    int day;
    int month;
    String choiceID;
    ArrayList<Entry> data;



    public Scene createHourByHourScene()
    {

        buttonBack.setPrefWidth(50);
        // create the content for the hour by hour view
        AnchorPane anchorPane = new AnchorPane();

        buttonBack.setPrefWidth(50);

        Scene scene = new Scene(anchorPane, 950, 700);

        ArrayList<String> choices = new ArrayList<>();
        for (int i = 0; i < HelloController.sites.size(); i++)
        {
            choices.add(i+1 + ". " + HelloController.sites.get(i).getSiteID());
        }
        ChoiceBox<String> choiceBox = new ChoiceBox<>(FXCollections.observableList(choices));

        choiceBox.setOnAction(actionEvent ->
        {
            choiceIndex = Integer.parseInt(choiceBox.getValue().substring(0, choiceBox.getValue().indexOf(".")))-1;
            choiceID = choiceBox.getValue().substring(choiceBox.getValue().indexOf(" ")+1);
            System.out.println("choiceIndex " + choiceIndex);
            System.out.println("choiceID " + choiceID);
        });

        choiceBox.setLayoutX(20);
        choiceBox.setLayoutY(70);
        choiceBox.setPrefWidth(100);



        date.searchSite(anchorPane,choiceBox);
        DatePicker datePicker = new DatePicker();
        datePicker.setLayoutY(30);
        datePicker.setOnAction(actionEvent ->
        {
            String formattedDate = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            day = Integer.parseInt(formattedDate.substring(choiceIndex, formattedDate.indexOf("-")));
            month = Integer.parseInt(formattedDate.substring(formattedDate.indexOf("-") + 1, formattedDate.indexOf("-") + 3));

        });

        btnAccept.setOnAction(actionEvent ->
        {
            data = new ArrayList<>(FileHelper.getEntry(HelloController.sites.get(choiceIndex), month, day));
            MyGraph.getChart().getData().clear();
            MyGraph.CreateSeriesHour("SID: " + choiceID, data);
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
        buttonBack.setOnAction(event -> stage.setScene(scene));
    }
}
