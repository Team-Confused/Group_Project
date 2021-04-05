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
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddSubTaskScreen extends Application {
    @Override
    public void start(Stage primaryStage) {

        //Labels
        Label SubTask = new Label("Sub-Task Name: ");
        Label Description = new Label(" Description: ");
        Label dueDate = new Label(" Date: ");
        Label priority = new Label("Priority");

        //Fields
        ComboBox combobox = new ComboBox();
        combobox.getItems().addAll("Highest","High", "Medium","Low");
        DatePicker date = new DatePicker();
        TextArea description = new TextArea();
        TextField subTask = new TextField();

        // Next Button
        Button next = new Button("Next");
        next.setAlignment(Pos.CENTER_RIGHT);

        //Adding Button to Hbox
        HBox hBox = new HBox();
        hBox.getChildren().add(next);
        hBox.setAlignment(Pos.CENTER_RIGHT);



        //Grid pane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(SubTask, 0,0);
        grid.add(dueDate, 0,1);
        grid.add(Description, 0,3);
        grid.add(priority, 0,2);
        grid.add(subTask, 1,0);
        grid.add(date, 1,1);
        grid.add(combobox, 1,2);
        grid.add(description, 1,3);
        grid.setHgap(10);
        grid.setVgap(10);


        BorderPane pane = new BorderPane();
        pane.setCenter(grid);
        pane.setBottom(hBox);

        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(pane, 600,350 );
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }

}
