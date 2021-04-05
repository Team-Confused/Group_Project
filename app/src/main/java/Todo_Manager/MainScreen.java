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
import javafx.scene.control.Control;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class MainScreen {
    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));
    public static Scene getMainScene(Stage primaryStage){
        ListView<Task> taskListView= new ListView<>();
        taskListView.getItems().addAll(Manager.getTasks());

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
        Button sort = new Button("Sort");
        sort.setOnAction(value->{

        });
        Button newTask = new Button("Add new Task");
        newTask.setOnAction(value->{
            primaryStage.setScene(CreateTaskScreen.getCreateTaskScene(primaryStage));
        });
        Button newSubTask = new Button("Add new Subtask");
        newSubTask.setOnAction(value->{

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

        one.getChildren().addAll(sort,newTask,newSubTask,markAsComplete);

        VBox two = new VBox();
        Button search = new Button("Search");
        search.setOnAction(value->{ primaryStage.setScene(SearchScreen.getSearchScene(primaryStage));

        });
        Button modifyTask = new Button("Modify Task");
        modifyTask.setOnAction(value->{
            primaryStage.setScene(ModifyTaskScreen.getModifyTaskScene(primaryStage,taskListView.getSelectionModel().getSelectedItem()));
        });
        Button addSection = new Button("Add new Section");
        addSection.setOnAction(value->{

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

        two.getChildren().addAll(search,modifyTask,addSection,removeTask);
        VBox three = new VBox();

        Button close = new Button("Exit Program");
        close.setOnAction(value->{
            try {
                Manager.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        });
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
        return new Scene(root,650,350);

    }
}
