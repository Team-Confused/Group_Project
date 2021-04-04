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


    //define the background color
    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));


    //compile list of users and their UUIDs
    public static List stringOfUsers()
    {
        List<String> stringofUsers = new ArrayList<String>();
        //for each user, add a short string of their simple information to the list "stringofUsers"
        for(User user : Manager.getUsers())
        {
            //format the added string as (<firstName> <lastName> "UUID:" <UUID>)
            stringofUsers.add(user.getFirstName() + " " + user.getLastName() + "\tUUID:" + user.getId());
        }

        //return the list
        return stringofUsers;
    }

    public static Scene getAdminMainScene(Stage primaryStage){

        ListView<String> taskListView= new ListView<>();
        taskListView.getItems().addAll(stringOfUsers());

        //VBoxes
        VBox one = new VBox();
        VBox two = new VBox();
        VBox three = new VBox();


        //button for exit
        Button close = new Button("Exit Program");

        //when exit button is clicked
        close.setOnAction(value->{
            try {
                //logout
                Manager.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //exit the program
            System.exit(0);
        });

        //logout button
        Button logout = new Button("Logout");
        //when button is pressed
        logout.setOnAction(value->{
            try {
                //logout
                Manager.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //go back to the "loginOrRegisterScreen"
            primaryStage.setScene(LoginOrRegisterScreen.getLogRegScreen(primaryStage));
        });

        //"Password Reset" button
        Button reset = new Button("Admin-level Password Reset");
        //on the clicking of button "Password Reset", go to the password reset page
        reset.setOnAction(value->{
            //go to the password reset page
            primaryStage.setScene(passwordResetScreen.getPasswordResetScreen(primaryStage));
        });

        //create the layout ... all of it
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

        //set background color
        root.setBackground(BLUEBACKGROUND);
        return new Scene(root,600,350);

    }
}
