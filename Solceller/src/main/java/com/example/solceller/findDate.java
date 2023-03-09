package com.example.solceller;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class findDate
{
    String siteID = "";
    public ChoiceBox searchSite(AnchorPane anchorPane, ChoiceBox choiceBox)
    {
        choiceBox.setLayoutX(10);
        choiceBox.setLayoutY(270);
        choiceBox.setPrefWidth(100);


        Label indSite = new Label("Enter site");
        indSite.setLayoutY(250);
        indSite.setLayoutX(10);






        Label indTSearch = new Label("Search site");
        indTSearch.setLayoutX(10);
        indTSearch.setLayoutY(190);




        TextField SearchField = new TextField();
        SearchField.setLayoutX(10);
        SearchField.setLayoutY(210);

        SearchField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    for (int i = 0; i < HelloController.sites.size(); i++) {
                        siteID = (i+1 + ". "+ HelloController.sites.get(i).getSiteID());
                        choiceBox.getItems().remove(siteID);
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
        anchorPane.getChildren().addAll(SearchField,indTSearch,indSite);

        return choiceBox;

    }
}
