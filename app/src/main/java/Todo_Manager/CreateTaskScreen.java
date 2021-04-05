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
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

public class CreateTaskScreen {

    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));

    public static Scene getCreateTaskScene(Stage primaryStage){
        GridPane root = new GridPane();
        Button makeTask = new Button("Create Task");

        Label title = new Label("Title: ");
        Label description = new Label("Description: ");
        Label deadline = new Label("Deadline: ");
        Label priority = new Label("Priority: ");
        Label error = new Label("You have an empty field.");
        error.setVisible(false);
        TextField titleIn = new TextField();
        TextArea descriptionIn = new TextArea();
        DatePicker deadlineIn = new DatePicker();
        ObservableList<Priority> options = FXCollections.observableArrayList(Priority.Low, Priority.Medium,Priority.High,Priority.ASAP);
        ComboBox<Priority> priorityIn = new ComboBox<Priority>(options);

        makeTask.setOnAction(value ->{
            if(titleIn.getText().isBlank() || descriptionIn.getText().isBlank()||deadlineIn.getValue() == null){
                error.setVisible(true);
            }else{
                try {
                    Manager.addTask(titleIn.getText(),descriptionIn.getText(),Date.from(Instant.from(deadlineIn.getValue().atStartOfDay(ZoneId.systemDefault()))),priorityIn.getSelectionModel().getSelectedItem());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                primaryStage.setScene(MainScreen.getMainScene(primaryStage));
            }

        });

        root.add(title,0,0);
        root.add(description,0,1);
        root.add(deadline,0,2);
        root.add(priority,0,3);

        root.add(titleIn,1,0);
        root.add(descriptionIn,1,1);
        root.add(deadlineIn,1,2);
        root.add(priorityIn,1,3);
        root.add(makeTask,1,4);
        root.add(error,1,5);
        root.setBackground(BLUEBACKGROUND);
        return new Scene(root,600,400);
    }
}
