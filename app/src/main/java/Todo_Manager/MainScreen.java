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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainScreen {

    //the list of tasks to be dsiplayed




    //define the background color
    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));
    private static ListView<Task> taskListView;
    //main screen
    public static void setListOfTasks(ArrayList<Task> taskList){
        taskListView = new ListView<>();
        taskListView.getItems().addAll(taskList);
    }
    public static Scene getMainScene(Stage primaryStage){
        //returns the main screen with the full task list
        return getCustomMainScene(primaryStage,Manager.getTasks());
    }


    public static Scene getCustomMainScene(Stage primaryStage,ArrayList<Task> taskList){
        //returns the main screen with whatever list is input.

        //initialize taskListView and have it hold the tasks
        taskListView= new ListView<>();
        taskListView.getItems().addAll(taskList);

        //update taskListView


        //The entire lambda expression below makes text wrap.
        taskListView.setCellFactory(param -> new ListCell<Task>(){
            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item==null) {
                    setGraphic(null);
                    setText(null);
                    // other stuff to do...

                }else{

                    // set the width's
                    setMinWidth(param.getWidth());
                    setMaxWidth(param.getWidth());
                    setPrefWidth(param.getWidth());

                    // allow wrapping
                    setWrapText(true);

                    setText(item.toString());


                }
            }
        });
        taskListView.getSelectionModel().selectFirst();






        VBox one = new VBox();

        //sort button
        Button sort = new Button("Sort");
        //when sort button is pressed
        sort.setOnAction(value->{
            //enter the sort screen
            //primaryStage.setScene(SortScreen.getSortScene(primaryStage));
            SortScreenWindow.createNewWindow();
        });

        //add a new task
        Button newTask = new Button("Add new Task");
        //if the button is pressed to add a new task
        newTask.setOnAction(value->{
            primaryStage.setScene(CreateTaskScreen.getCreateTaskScene(primaryStage));
        });

        //add a new subtask
        Button newSubTask = new Button("Add new Subtask");
        //if the button is pressed to add a new subtask
        newSubTask.setOnAction(value->{
            //Enter new Sub-task screen
            primaryStage.setScene(AddSubTaskScreen.getAddSubTaskScreen(primaryStage, taskListView.getSelectionModel().getSelectedItem()));

        });


        Button markAsComplete = new Button("Mark as complete");
        markAsComplete.setOnAction(value->{
            taskListView.getSelectionModel().getSelectedItem().setTaskCompleted(true);
            try {
                Manager.saveUserData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button addLabel = new Button("Add label");
        addLabel.setOnAction(value->{
            if(!taskListView.getItems().isEmpty()) {
                primaryStage.setScene(AddLabelScreen.getAddLabelScene(primaryStage, taskListView.getSelectionModel().getSelectedItem()));
            }
        });
        Button removeSubTask = new Button("Remove Subtask");
        removeSubTask.setOnAction(value->{
            if(!taskListView.getItems().isEmpty()) {
                primaryStage.setScene(RemoveSubTaskScreen.getRemoveSubTaskScene(primaryStage, taskListView.getSelectionModel().getSelectedItem()));

            }
        });


        //add sort, newtask, and newSubTask into a VBox
        one.getChildren().addAll(sort,newTask,newSubTask,markAsComplete,addLabel,removeSubTask);




        VBox two = new VBox();

        //search button
        Button search = new Button("Search");
        //when the search button is pressed
        search.setOnAction(value->{
            //enter the Search page
            try {
                primaryStage.setScene(SearchScreen.getSearchScene(primaryStage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //modify task button
        Button modifyTask = new Button("Modify Task");
        //when the "modify task" button is pressed
        modifyTask.setOnAction(value->{
            primaryStage.setScene(ModifyTaskScreen.getModifyTaskScene(primaryStage,taskListView.getSelectionModel().getSelectedItem()));
        });

        //add new section button
        Button Sections = new Button("Add or Remove\nSections");
        //when the "add new section" button is pressed
        Sections.setOnAction(value->{
            primaryStage.setScene(SectionViewScreen.getAddTaskToSectionScene(primaryStage));
        });
        Button addTaskToSection = new Button("Add task to\na section");

        addTaskToSection.setOnAction(value->{
            primaryStage.setScene(AddTaskToSectionScreen.getAddTaskToSectionScene(primaryStage,taskListView.getSelectionModel().getSelectedItem()));

        });
        Button removeTaskFromSection = new Button("Remove task from\na section");

        removeTaskFromSection.setOnAction(value->{
            primaryStage.setScene(RemoveTaskFromSectionScreen.getRemoveTaskFromSectionScene(primaryStage,taskListView.getSelectionModel().getSelectedItem()));


        });


        Button removeTask = new Button("Remove Task");
        removeTask.setOnAction(value ->{
            Task workingTask = taskListView.getSelectionModel().getSelectedItem();
            try {
                Manager.removeTask(workingTask);
            } catch (IOException e) {
                e.printStackTrace();
            }
            taskListView.getItems().remove(workingTask);
            taskListView.getSelectionModel().selectFirst();
        });

        Button removeLabel = new Button("Remove label");
        removeLabel.setOnAction(value->{
            if(!taskListView.getItems().isEmpty()) {
                primaryStage.setScene(RemoveLabelScreen.getRemoveLabelScene(primaryStage, taskListView.getSelectionModel().getSelectedItem()));
            }
        });

    //put search, modifyTask, and addSection in the same VBox
        two.getChildren().addAll(search,modifyTask,Sections,addTaskToSection,removeTaskFromSection,removeTask,removeLabel);


        VBox three = new VBox();

        //exit program
        Button close = new Button("Exit Program");
        //when the user clicks the "exit program" button
        close.setOnAction(value->{
            //try to logout the user
            try {
                //logout the user
                Manager.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //close the program
            System.exit(0);
        });

        //logout button
        Button logout = new Button("Logout");
        logout.setOnAction(value->{
            try {
                Manager.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setScene(LoginOrRegisterScreen.getLogRegScreen(primaryStage));
        });

        //"Password Reset" button
        Button reset = new Button("Password Reset");
        //on the clicking of button "Password Reset", go to the password reset page
        reset.setOnAction(value->{
            //go to the password reset page
            primaryStage.setScene(passwordResetScreen.getPasswordResetScreen(primaryStage));
        });





        //build the scene
        reset.setAlignment(Pos.BOTTOM_RIGHT);
        VBox innerThree = new VBox();
        innerThree.getChildren().addAll(close,logout);
        innerThree.setSpacing(10);
        three.getChildren().addAll(innerThree,reset);
        HBox root = new HBox();
        root.getChildren().addAll(taskListView,one,two,three);
        one.setSpacing(10);
        two.setSpacing(10);
        three.setSpacing(240);
        root.setSpacing(10);
        root.setBackground(BLUEBACKGROUND);



        //return the sceen
        return new Scene(root,650,350);


    }
}
