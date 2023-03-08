package com.example.solceller;

import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
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
                if (keyEvent.getCode().equals(KeyCode.ENTER))
                {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String formattedValue = datePicker.getValue().format(formatter);

                    System.out.println(formattedValue);
                }
            }
        });
        return datePicker;
    }
}
