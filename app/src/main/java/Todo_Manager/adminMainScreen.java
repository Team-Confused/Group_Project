package Todo_Manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class adminMainScreen {
    public static List stringOfUsers()
    {
        List<String> stringofUsers = new ArrayList<String>();
        for(User user : Manager.getUsers())
        {
            stringofUsers.add(user.getFirstName() + " " + user.getLastName() + "\tUUID:" + user.getId());
        }

        return stringofUsers;
    }


    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));
    public static Scene getAdminMainScene(Stage primaryStage){

        ListView<String> taskListView= new ListView<>();
        taskListView.getItems().addAll(stringOfUsers());

        //VBoxes
        VBox one = new VBox();
        VBox two = new VBox();
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
        Button reset = new Button("Admin-level Password Reset");
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
