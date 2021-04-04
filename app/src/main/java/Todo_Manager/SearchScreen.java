package Todo_Manager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchScreen  {

    // search GUI
    public static  Scene getSearchScene(Stage primaryStage) {

        //"search box" text field
        TextField searchText = new TextField();
        //searchText.setPromptText();


        // search Button
        Button button = new Button();
        button.setText("Search");
        //when button is pressed
        button.setOnAction(value -> {
            //logic for calling the Search method in Manager

            boolean check = false;

            //get the user's text input
            String search = searchText.getText();
            //automatically fail if the user didn't provide a search parameter
            if (search.isBlank()) {
                boolean check2 = false;

            }
            //if the user provided input to search
            else {
                try {
                    //run the Search method in Manager with the user input as its parameter
                    Manager.Search(search);

                    //do the same thing, except with Manager's searchTask method
                    Manager.searchTask(search);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        //set the position of the button
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


        Scene scene = new Scene(searchbar, 600, 400);

        //return the scene
        return scene;
    }
}



