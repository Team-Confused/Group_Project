package Todo_Manager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreen {
    //define the background color
    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));

    //main screen
    public static Scene getMainScene(Stage primaryStage){

        //initialize taskListView and have it hold the tasks
        ListView<Task> taskListView= new ListView<>();
        taskListView.getItems().addAll(Manager.getTasks());

        VBox one = new VBox();

        //sort button
        Button sort = new Button("Sort");
        //when sort button is pressed
        sort.setOnAction(value->{
            //enter the sort screen
            primaryStage.setScene(SortScreen.getSortScene(primaryStage));
        });

        //add a new task
        Button newTask = new Button("Add new Task");
        //if the button is pressed to add a new task
        newTask.setOnAction(value->{

        });

        //add a new subtask
        Button newSubTask = new Button("Add new Subtask");
        //if the button is pressed to add a new subtask
        newSubTask.setOnAction(value->{

        });

        //add sort, newtask, and newSubTask into a VBox
        one.getChildren().addAll(sort,newTask,newSubTask);

        VBox two = new VBox();

        //search button
        Button search = new Button("Search");
        //when the search button is pressed
        search.setOnAction(value->{
            //enter the Search page
            primaryStage.setScene(SearchScreen.getSearchScene(primaryStage));
        });

        //modify task button
        Button modifyTask = new Button("Modify Task");
        //when the "modify task" button is pressed
        modifyTask.setOnAction(value->{

        });

        //add new section button
        Button addSection = new Button("Add new Section");
        //when the "add new section" button is pressed
        addSection.setOnAction(value->{

        });

        //put search, modifyTask, and addSection in the same VBox
        two.getChildren().addAll(search,modifyTask,addSection);

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
        return new Scene(root,600,350);

    }
}
