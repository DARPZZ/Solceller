package com.example.solceller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.time.format.DateTimeFormatter;

    public class FindDate
    {
        javafx.scene.control.DatePicker datePicker = new javafx.scene.control.DatePicker();

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
                    }
                }
            });
            return datePicker;
        }

        public void Search(AnchorPane anchorPane)
        {
            for (int i = 0; i < HelloController.sites.size(); i++) {
               // System.out.println(HelloController.sites.get(i).getSiteID());
            }
            TextField textField = new TextField();
            textField.setLayoutY(250);
            textField.setLayoutX(50);
            textField.setPromptText("Search");
            int pik = HelloController.sites.get(0).getSiteID();
            textField.setOnKeyPressed(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent keyEvent)
                {
                    System.out.println(HelloController.sites.get(0).getSiteID());
                    if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                        System.out.println(textField.getText());

                        if (Integer.parseInt(textField.getText()) == (pik))
                        {
                            System.out.println("correct");
                        }else
                        {
                            System.out.println("Hej");
                        }

                    }

                }
            });
            anchorPane.getChildren().add(textField);
        }
    }
