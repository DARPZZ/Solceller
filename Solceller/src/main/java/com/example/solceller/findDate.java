package com.example.solceller;

import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.time.format.DateTimeFormatter;

public class findDate
{
    DatePicker datePicker = new DatePicker();

    public DatePicker getDatePicker(AnchorPane anchorPane)
    {
        datePicker.setLayoutY(300);
        datePicker.setLayoutX(10);
        anchorPane.getChildren().addAll(datePicker);
        datePicker.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String formattedValue = datePicker.getValue().format(formatter);

                    System.out.println(formattedValue);
                }
            }
        });
        return datePicker;
    }
    String siteID = "";
    public ChoiceBox searchSite(AnchorPane anchorPane, ChoiceBox choiceBox)
    {
        for (int i = 0; i < HelloController.sites.size(); i++) {
            siteID = String.valueOf(HelloController.sites.get(i).getSiteID());
            choiceBox.getItems().add(siteID);
        }
        TextField SearchField = new TextField();
        SearchField.setLayoutX(20);
        SearchField.setLayoutY(500);

        SearchField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {

                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    for (int i = 0; i < HelloController.sites.size(); i++) {
                       // System.out.println(HelloController.sites.get(i).getSiteID());
                        siteID = String.valueOf(HelloController.sites.get(i).getSiteID());

                        if ((siteID.contains(SearchField.getText()))) {
                            choiceBox.getItems().remove(siteID);
                            choiceBox.getItems().add(siteID);
                        } else {
                            choiceBox.getItems().remove(siteID);
                        }
                    }

                }
            }
        });
        anchorPane.getChildren().add(SearchField);

        return choiceBox;

    }
}
