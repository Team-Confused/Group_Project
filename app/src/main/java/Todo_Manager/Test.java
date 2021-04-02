package Todo_Manager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test extends Application implements EventHandler<ActionEvent> {



    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Search
        TextField searchText = new TextField();
        searchText.setPromptText("Search");


        // Button
        Button button = new Button();
        button.setText("Search");
        button.setOnAction(this::handle);
        button.setAlignment(Pos.CENTER_RIGHT);

        HBox box = new HBox();
        box.getChildren().add(button);
        box.setAlignment(Pos.CENTER_RIGHT);

        // Boderpane
        BorderPane searchbar = new BorderPane();
        searchbar.setPadding(new Insets(20));
        searchbar.setRight(searchText);

        searchbar.setBottom(box);
        searchbar.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));





        Scene scene = new Scene(searchbar, 600 , 400);

        primaryStage.setScene(scene);
        primaryStage.show();

    }


    @Override
    public void handle(ActionEvent event) {
      System.out.println("hell0");
    }
}
