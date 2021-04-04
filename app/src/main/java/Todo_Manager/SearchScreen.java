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
        public static  Scene getSearchScene(Stage primaryStage) {


            TextField searchText = new TextField();
            //searchText.setPromptText();


            // Button
            Button button = new Button();
            button.setText("Search");
            button.setOnAction(value -> {
                boolean check = false;
                String search = searchText.getText();
                if (search.isBlank()) {
                    boolean check2 = false;
                    ;
                } else {
                    try {
                        Manager.Search(search);
                        Manager.searchTask(search);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });
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

            //primaryStage.setScene(scene);
            //primaryStage.show();
            return scene;
        }
    }



