/**
 * MIT License
 *
 * Copyright (c) 2021 Team-Confused
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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



