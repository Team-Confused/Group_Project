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

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SortScreen  {


    //create the a scene to sort
    public static Scene getSortScene(Stage primaryStage)  {


        // Sort button
        Button button = new Button();
        button.setText("Sort");

        // Check box for sort
        CheckBox cb1 = new CheckBox("Task");
        CheckBox cb2 = new CheckBox("Labels");
        CheckBox cb3 = new CheckBox("Date");
        CheckBox cb4 = new CheckBox("Priority");
       // checkbox1.setIndeterminate(false);\

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("Highest","High", "Medium","Low");

        //Adding button to hbox
        HBox box = new HBox();
        box.getChildren().add(button);
        box.setAlignment(Pos.CENTER_RIGHT);

        //datepicker to select date for sort parameter
        DatePicker date = new DatePicker();

        //dropdown menu
        VBox dropdownbox = new VBox();
        dropdownbox.getChildren().addAll(date,comboBox);
        dropdownbox.setAlignment(Pos.CENTER_LEFT);
        dropdownbox.setSpacing(50);

        // Adding checkbox to vbox
        VBox box1 = new VBox();
        box1.getChildren().addAll(cb1, cb2, cb3, cb4);;
        box1.setPadding(new Insets(5,5,5,5));
        box1.setSpacing(35);
        box1.setAlignment(Pos.TOP_LEFT);


        //Boderpane
        BorderPane sortbar = new BorderPane();
        sortbar.setLeft(box1); // checkbox - vbox
        sortbar.setBottom(box); // sort button
        sortbar.setCenter(dropdownbox);
        sortbar.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(sortbar, 600, 400);
       // primaryStage.setScene(scene);
        //primaryStage.show();

        //return the scene
        return scene;
    }
}
