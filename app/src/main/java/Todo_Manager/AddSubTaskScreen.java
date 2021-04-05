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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

public class AddSubTaskScreen{
    public static Scene getAddSubTaskScreen(Stage primaryStage, Task workingTask) {

        //Labels
        Label SubTask = new Label("Sub-Task Name: ");
        Label Description = new Label(" Description: ");
        Label dueDate = new Label(" Date: ");
        Label priority = new Label("Priority");
        Label error = new Label("You have an empty field.");
        error.setVisible(false);
        ObservableList<Priority> options = FXCollections.observableArrayList(Priority.Low, Priority.Medium,Priority.High,Priority.ASAP);

        //Fields for data
        ComboBox<Priority> priorityIn = new ComboBox<Priority>(options);
        DatePicker date = new DatePicker();
        TextArea description = new TextArea();
        TextField subTask = new TextField();

        // Next Button
        Button next = new Button("Next");
        next.setAlignment(Pos.CENTER_RIGHT);
        next.setOnAction(value ->{
            Boolean pass = false;
            if(subTask.getText().isBlank()|| description.getText().isBlank()||date.getValue() == null){
                error.setVisible(true);
            }
            else{
                try{
                   Manager.addSubTask(workingTask,subTask.getText(), description.getText(), Date.from(Instant.from(date.getValue().atStartOfDay(ZoneId.systemDefault()))),priorityIn.getSelectionModel().getSelectedItem());

                }catch (IOException e){
                    e.printStackTrace();
                }
                primaryStage.setScene(MainScreen.getMainScene(primaryStage));
            }
        });

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
        grid.add(priorityIn, 1,2);
        grid.add(description, 1,3);
        grid.add(error,1,8);
        grid.setHgap(10);
        grid.setVgap(10);


        BorderPane pane = new BorderPane();
        pane.setCenter(grid);
        pane.setBottom(hBox);

        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(pane, 600,350 );
        return scene;
    }


}
