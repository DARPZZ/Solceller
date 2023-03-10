package com.example.solceller;
import Model.Graph;
import Model.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.TreeMap;

public class BestWorst implements iChangeScene
{

    SearchSite date = new SearchSite();
    Button buttonBack = new Button("Back");
    Button btnAccept = new Button("Enter");
    int choiceIndex, monthIndex;
    String choiceID, month;
    ArrayList<Entry> dataMin,dataMax;
    Graph MyGraph = new Graph("Best and Worst Production Day", "Hour", "Production", Type.CURVE_CHART);
    Graph MyGraph2 = new Graph("Best and Worst Production Day", "Hour", "Production", Type.CURVE_CHART);
    public Scene createBestWorstScene()
    {
        AnchorPane anchorPane = new AnchorPane();
        Label indTDate = new Label("Enter Month");
        indTDate.setLayoutY(130);
        indTDate.setLayoutX(10);
        buttonBack.setPrefWidth(50);
        Scene scene = new Scene(anchorPane, 950, 700);

        ArrayList<String> choices = new ArrayList<>();
        for (int i = 0; i < FileHelper.sites.size(); i++)
        {
            choices.add(i+1 + ". " + FileHelper.sites.get(i).getSiteID());
        }
        ChoiceBox<String> choiceBox = new ChoiceBox<>(FXCollections.observableList(choices));
        date.searchSite(anchorPane,choiceBox);
        choiceBox.setOnAction(actionEvent -> {
            choiceIndex = Integer.parseInt(choiceBox.getValue().substring(0, choiceBox.getValue().indexOf(".")))-1;
            choiceID = choiceBox.getValue().substring(choiceBox.getValue().indexOf(" ")+1);
            System.out.println("choiceIndex " + choiceIndex);
            System.out.println("choiceID " + choiceID);
        });

        ObservableList<Month.MonthEnum> monthsList = FXCollections.observableArrayList(Month.MonthEnum.values());

        // Creates ComboBox with choices of month and set instance variables for month
        ComboBox<Month.MonthEnum> choiceMonth = new ComboBox<>(FXCollections.observableList(monthsList));
        choiceMonth.setOnAction(actionEvent -> {
            monthIndex = Integer.parseInt(choiceMonth.getValue().MONTH_NO.substring(0, choiceMonth.getValue().getMonthNo().indexOf(" ")));
            month = choiceMonth.getValue().getMonthNo().substring(choiceMonth.getValue().getMonthNo().indexOf("-")+2);
            System.out.println("monthIndex " + monthIndex);
            System.out.println("choiceMonth " + month);
        });

        choiceMonth.setLayoutX(10);
        choiceMonth.setLayoutY(150);
        choiceMonth.setPrefWidth(150);
        choiceBox.setId("choice");

        // Button "accept" creates an arrayList with chosen values
        btnAccept.setOnAction(actionEvent ->
        {
            MyGraph.getChart().getData().clear();
           results r =  DataProcessing.minMaxProdValues(FileHelper.sites.get(choiceIndex), monthIndex);

            dataMin = new ArrayList<>(FileHelper.getEntry(FileHelper.sites.get(choiceIndex), monthIndex,r.getIndexMin()));
            dataMax = new ArrayList<>(FileHelper.getEntry(FileHelper.sites.get(choiceIndex), monthIndex,r.getIndexMax()));
            MyGraph.CreateSeriesHour("Min",dataMin);
            MyGraph.CreateSeriesHour("Max",dataMax);
            anchorPane.getChildren().remove(MyGraph.getChart());
            anchorPane.getChildren().remove(MyGraph2.getChart());
            anchorPane.getChildren().add(MyGraph.getChart());


        });

        buttonBack.setPrefWidth(75);
        buttonBack.setLayoutX(10);
        btnAccept.setPrefWidth(75);
        btnAccept.setLayoutX(10);
        btnAccept.setLayoutY(330);
        anchorPane.getChildren().addAll(buttonBack, btnAccept, choiceMonth, choiceBox, MyGraph2.getChart(),indTDate);
        String css = this.getClass().getResource("/basicstyle.css").toExternalForm();
        scene.getStylesheets().add(css);
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
