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

public class adminMainScreen {
    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));
    public static Scene getMainScene(Stage primaryStage){

        ListView<Task> taskListView= new ListView<>();
        taskListView.getItems().addAll(Manager.getTasks());
        VBox one = new VBox();
        Button sort = new Button("Sort");
        sort.setOnAction(value->{

        });
        Button newTask = new Button("Add new Task");
        newTask.setOnAction(value->{

        });
        Button newSubTask = new Button("Add new Subtask");
        newSubTask.setOnAction(value->{

        });
        one.getChildren().addAll(sort,newTask,newSubTask);
        VBox two = new VBox();
        Button search = new Button("Search");
        search.setOnAction(value->{ primaryStage.setScene(SearchScreen.getSearchScene(primaryStage));

        });
        Button modifyTask = new Button("Modify Task");
        modifyTask.setOnAction(value->{

        });
        Button addSection = new Button("Add new Section");
        addSection.setOnAction(value->{

        });
        two.getChildren().addAll(search,modifyTask,addSection);
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
        return new Scene(root,600,350);

    }
}
