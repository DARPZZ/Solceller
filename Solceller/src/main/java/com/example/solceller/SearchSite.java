package com.example.solceller;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class SearchSite
{
    String siteID = "";

    /**
     * Getting the site ID, checks if the site ID is the same as in search field
     * @param anchorPane Getting the anchorPane from another class
     * @param choiceBox Getting the choiceBox
     * @return Gives back the new Choicebox
     */
    public ChoiceBox searchSite(AnchorPane anchorPane, ChoiceBox choiceBox)
    {
        choiceBox.setLayoutX(10);
        choiceBox.setLayoutY(270);
        choiceBox.setPrefWidth(150);
        Label indSite = new Label("Enter site");
        indSite.setLayoutY(250);
        indSite.setLayoutX(10);
        Label indTSearch = new Label("Search site");
        indTSearch.setLayoutX(10);
        indTSearch.setLayoutY(190);
        TextField SearchField = new TextField();
        SearchField.setLayoutX(10);
        SearchField.setLayoutY(210);
        SearchField.setPrefWidth(150);
        SearchField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    for (int i = 0; i < FileHelper.sites.size(); i++) {
                        siteID = (i+1 + ". "+ FileHelper.sites.get(i).getSiteID());
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
