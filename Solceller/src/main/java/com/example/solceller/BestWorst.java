package com.example.solceller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BestWorst implements iChangeScene
{
    SearchSite date = new SearchSite();
    Button buttonBack = new Button("Back");
    public Scene createBestWorstScene()
    {
        AnchorPane anchorPane = new AnchorPane();
        buttonBack.setPrefWidth(75);
        buttonBack.setLayoutX(10);
        anchorPane.getChildren().addAll(buttonBack);
        Scene scene = new Scene(anchorPane, 1000, 500);
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
