package com.example.solceller;
import Model.Graph;
import Model.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.TreeMap;

public class Month implements iChangeScene
{
    Button buttonBack = new Button("Back");
    Button btnAccept = new Button("Enter");
    Graph MyGraph = new Graph("Production for month: ", "Day", "Production", Type.BAR_CHART);
    int choiceIndex;
    String choiceID;
    String month;
    int monthIndex;
    ArrayList<Entry> data;
    public Scene createMonthScene() {
        buttonBack.setPrefWidth(50);
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 950, 700);


        ArrayList<String> choices = new ArrayList<>();
        for (int i = 0; i < HelloController.sites.size(); i++)
        {
            choices.add(i+1 + ". " + HelloController.sites.get(i).getSiteID());
        }
        ComboBox<String> choiceBox = new ComboBox<>(FXCollections.observableList(choices));

        choiceBox.setOnAction(actionEvent -> {
            choiceIndex = Integer.parseInt(choiceBox.getValue().substring(0, choiceBox.getValue().indexOf(".")))-1;
            choiceID = choiceBox.getValue().substring(choiceBox.getValue().indexOf(" ")+1);
            System.out.println("choiceIndex " + choiceIndex);
            System.out.println("choiceID " + choiceID);
        });

        choiceBox.setLayoutX(20);
        choiceBox.setLayoutY(70);
        choiceBox.setPrefWidth(100);

        ObservableList<MonthEnum> monthsList = FXCollections.observableArrayList(MonthEnum.values());


        // Creates ComboBox with choices of month and set instance variables for month
        ComboBox<MonthEnum> choiceMonth = new ComboBox<>(FXCollections.observableList(monthsList));
        choiceMonth.setOnAction(actionEvent -> {
            monthIndex = Integer.parseInt(choiceMonth.getValue().MONTH_NO.substring(0, choiceMonth.getValue().getMonthNo().indexOf(" ")));
            month = choiceMonth.getValue().getMonthNo().substring(choiceMonth.getValue().getMonthNo().indexOf("-")+2);
            System.out.println("monthIndex " + monthIndex);
            System.out.println("choiceMonth " + month);
        });

        choiceMonth.setLayoutX(20);
        choiceMonth.setLayoutY(200);
        choiceMonth.setPrefWidth(100);

        // Button "accept" creates an arrayList with chosen values
        btnAccept.setOnAction(actionEvent ->
        {
            data = new ArrayList<>(FileHelper.getEntry(HelloController.sites.get(choiceIndex), monthIndex));
            TreeMap<String, Integer> dailyProduction = fillMap(data);
            MyGraph.getChart().getData().clear();
            MyGraph.CreateSeries("SID: " + choiceID, dailyProduction);
            MyGraph.getChart().setTitle("Production for month: " + month);
        });

        buttonBack.setPrefWidth(50);
        btnAccept.setPrefWidth(50);
        btnAccept.setLayoutY(100);
        btnAccept.setLayoutX(20);
        MyGraph.getChart().setLayoutX(350);

        anchorPane.getChildren().addAll(buttonBack, btnAccept, choiceMonth, choiceBox, MyGraph.getChart());


        return scene;
    }
    /**
     * This section cchanges back to the main scene
     * Can you also change scene to other scenes
     * @param stage
     * @param scene
     */
    @Override
    public void changescene(Stage stage, Scene scene)
    {
        buttonBack.setOnAction(event -> stage.setScene(scene));
    }

    public enum MonthEnum {
        JANUARY("1 - January"),
        FEBRUARY("2 - February"),
        MARCH("3 - March"),
        APRIL("4 - April"),
        MAY("5 - May"),
        JUNE("6 - June"),
        JULY("7 - July"),
        AUGUST("8 - August"),
        SEPTEMBER("9 - September"),
        OCTOBER("10 - October"),
        NOVEMBER("11 - November"),
        DECEMBER("12 - December");

        private final String MONTH_NO;

        MonthEnum (String monthNo)
        {
            this.MONTH_NO = monthNo;
        }

        public String getMonthNo()
        {
            return this.MONTH_NO;
        }
    }

    /**
     * Creates a TreeMap from an arrayList
     * @param entryArrayList An arrayList containing Entry object to be added to TreeMap
     * @return TreeMap filled with day as key and production as value
     */
    public TreeMap<String, Integer> fillMap(ArrayList<Entry> entryArrayList)
    {
        TreeMap<String, Integer> dailyTotals = new TreeMap<>();
        String day = "";
        int production = 0;
        for (Entry entry : entryArrayList)
        {
            try
            {
                day = entry.getDay();
                production = entry.getOnline() + entry.getOffline();
            }
            catch (Exception ignored)
            {}

            if (dailyTotals.containsKey(day))
            {
                // If the day already exists in the map, add the production value to the existing total
                int total = dailyTotals.get(day);
                total += production;
                dailyTotals.put(day, total);
            }
            else
            {
                // If the day doesn't exist in the map, create a new entry with the production value
                dailyTotals.put(day, production);
            }
        }
        return dailyTotals;
    }
}
