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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lombok.extern.java.Log;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@Log
public class
SortScreenWindow {

    //define the background color
    private static final Background GreenBackground = new Background(new BackgroundFill(Color.color(0.00,0.48,0.40, 0.9), CornerRadii.EMPTY, Insets.EMPTY));
    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));

    public Scene createNewWindow_Old(Stage window)
    {
        GridPane root = new GridPane();
        Button makeTask = new Button("Create Task");

        //checkboxes
        CheckBox taskCB = new CheckBox("Task");
        CheckBox labelsCB = new CheckBox("Labels");
        CheckBox dateCB = new CheckBox("Date");
        CheckBox priorityCB = new CheckBox("Priority");

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
                    Manager.addTask(titleIn.getText(),descriptionIn.getText(), Date.from(Instant.from(deadlineIn.getValue().atStartOfDay(ZoneId.systemDefault()))),priorityIn.getSelectionModel().getSelectedItem());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                window.setScene(MainScreen.getMainScene(window));
            }

        });

        root.add(taskCB,0,0);
        root.add(labelsCB,0,1);
        root.add(dateCB,0,2);
        root.add(priorityCB,0,3);

        root.add(titleIn,1,0);
        root.add(descriptionIn,1,1);
        root.add(deadlineIn,1,2);
        root.add(priorityIn,1,3);
        root.add(makeTask,1,4);
        root.add(error,1,5);
        root.setBackground(BLUEBACKGROUND);
        return new Scene(root,600,400);
    }

    public static void createNewWindow() {

        //create an instance of the window
        Stage window = new Stage();


        GridPane root = new GridPane();

        //button
        Button sortButton = new Button("Sort");
        //Button mainMenu = new Button("Main Menu");


        Label sortParameters = new Label("sortParameters: ");
        Label SortTitle = new Label("Sort");


        //checkboxes//

        //task
        CheckBox taskCB = new CheckBox("Task");
        ComboBox taskOrderCB = new ComboBox();
        taskOrderCB.getItems().addAll("Ascending", "Descending");
        taskOrderCB.setVisible(false);

        //labels
        CheckBox labelsCB = new CheckBox("Labels");

        Task Task = new Task();

        ObservableList labels = FXCollections.observableArrayList();
        //System.out.println("labels:"+labels);

        for (Task task : Manager.getTasks())
        {
            System.out.println("observable array list" + task.getLabel() );
            if(task.getLabel() != null)
            labels.add(task.getLabel());
        }
        System.out.println("labels: " + labels);


        ComboBox labelsListCB = new ComboBox();
        labelsListCB.getItems().addAll(labels);
        labelsListCB.setVisible(false);

        //date
        CheckBox dateCB = new CheckBox("Date");
        DatePicker datePickerThing = new DatePicker();
        datePickerThing.setVisible(false);

        //priority
        CheckBox priorityCB = new CheckBox("Priority");
        ComboBox priorityComboBox = new ComboBox();
        priorityComboBox.getItems().addAll(Priority.ASAP,Priority.High,Priority.Medium,Priority.Low);
        priorityComboBox.setVisible(false);


        //event triggers

        //task ascending or descending
        taskCB.setOnAction(value ->{
            taskOrderCB.setVisible(true);
        });

        //labels list combo box
        labelsCB.setOnAction(value ->{
            labelsListCB.setVisible(true);
        });

        //date
        dateCB.setOnAction(value ->{
            datePickerThing.setVisible(true);
        });

        //priority
        priorityCB.setOnAction(value ->{
            priorityComboBox.setVisible(true);
        });

        //sort button
        sortButton.setOnAction(value ->{
            //call sort
            try {
                //task, label, date, priority
                log.info("Sort tasks of:" + Manager.getLoggedInUser().getFirstName() + " " + Manager.getLoggedInUser().getLastName() +
                        "\n Options Selected: " +
                        "\n\ttask: " + taskCB.isSelected() + "\torder: " + taskOrderCB.getSelectionModel().getSelectedItem() +
                        "\n\tlabel: " + labelsCB.isSelected() );

                Priority priorityValue = (Priority) priorityComboBox.getSelectionModel().getSelectedItem();
                MainScreen.setListOfTasks(Sort.sortBy(taskCB.isSelected(),
                                            labelsCB.isSelected(),
                                            dateCB.isSelected(),
                                            priorityCB.isSelected(),
                                            datePickerThing.getValue(),
                                            (String) labelsListCB.getSelectionModel().getSelectedItem(),
                                            priorityValue));
                MainScreen.updateScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
            window.close();
        });

        //VBox one = new VBox();
        //one.getChildren().addAll();

        root.setVgap(10);
        root.setAlignment(Pos.CENTER);

        root.add(taskCB,0,0);
        root.add(taskOrderCB,1,0);

        root.add(labelsCB,0,1);
        root.add(labelsListCB, 1,1);

        root.add(dateCB,0,2);
        root.add(datePickerThing,1,2);

        root.add(priorityCB,0,3);
        root.add(priorityComboBox,1,3);


        root.add(sortButton,1,4);
        //root.add(mainMenu, 2,4);
        //root.add(error,1,5);

        //background
        root.setBackground(GreenBackground);


        Parent content = root;

        // create scene containing the content
        Scene scene = new Scene(content,400,250);

        window.setScene(scene);

        // make window visible
        window.show();
    }
}