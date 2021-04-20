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


        //priority
        CheckBox priorityCB = new CheckBox("Priority");
        ComboBox priorityComboBox = new ComboBox();
        priorityComboBox.getItems().addAll(Priority.ASAP,Priority.High,Priority.Medium,Priority.Low);
        priorityComboBox.setVisible(false);

////////////////////////////////////////////////////////////////////////
        //event triggers


        //labels list combo box
        labelsCB.setOnAction(value ->{
            labelsListCB.setVisible(true);
        });

        //date {does nothing now, but allows for future expansion}
        dateCB.setOnAction(value ->{
            log.info("Date box is selected.");
        });

        //priority
        priorityCB.setOnAction(value ->{
            priorityComboBox.setVisible(true);
        });

        //sort button
        sortButton.setOnAction(value ->{
            //call sort
            try {
                //log the information
                log.info("Sort tasks of:" + Manager.getLoggedInUser().getFirstName() + " " + Manager.getLoggedInUser().getLastName());

                //set a variable "priorityValue" to be the value of the prioritycombobox
                Priority priorityValue = (Priority) priorityComboBox.getSelectionModel().getSelectedItem();
                Object label = labelsListCB.getSelectionModel().getSelectedItem();
                //reset the list of tasks on Mainscreen by thereturned ArrayList of Sort
                MainScreen.setListOfTasks(Sort.sortBy(taskCB.isSelected(),
                                            labelsCB.isSelected(),
                                            label,
                                            dateCB.isSelected(),
                                            priorityCB.isSelected(),
                                            priorityValue));
                //update the mainscreen
                MainScreen.updateScene();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //close the "sort" window
            window.close();
        });



        ///////////////////////////////////////////////////////////////////////////////////

        //set allignment and distance between buttons, tags, etc.
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);

        //task's things
        root.add(taskCB,0,0);

        //label button and list
        root.add(labelsCB,0,1);
        root.add(labelsListCB, 1,1);

        //date selectable button and the thing to select the date
        root.add(dateCB,0,2);

        //priority box and the list of priority to filter by
        root.add(priorityCB,0,3);
        root.add(priorityComboBox,1,3);

        //sort button
        root.add(sortButton,1,4);


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
