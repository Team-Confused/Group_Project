package Todo_Manager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreen {

    //define the background
    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));

    //login screen scene
    public static Scene getLoginScene(Stage primaryStage){
        //text field (email, password)
        TextField emailIn = new TextField();
        PasswordField passwordIn = new PasswordField();

        //labels
        Label emailL  = new Label("Email: ");
        Label passwordL = new Label("Password: ");
        Label error = new Label("The password or email is wrong.");

        //set the visibility of the "error" label
        error.setVisible(false);

        //login button
        Button login = new Button("Login");
        //when the login button is pressed
        login.setOnAction(value->{
            boolean check = false;

            //get the inputted strings the user gave
            String email = emailIn.getText();
            String password = passwordIn.getText();

            //auto-fail if the email or password is blank
            if(email.isBlank() || password.isBlank()){
                Boolean cleck = false;
            }

            // when the user enters both an email and a password
            else {
                try {
                    //run the login method from Manager with the email and password as its parameters
                    check = Manager.login(email, password);
                }
                //exception catch
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //if there is a failure in logging in (causable by leaving a box blank or inputting bad credentials)
            if (check == false){
                //set the visibility for the "error" label to visible
                error.setVisible(true);
            }

            //based on if the user is an admin or not, enter the main scene or the admin main scene
            else{
                boolean isAdmin = Manager.getLoggedInUser().isAdmin();

                //if the user is an admin, get the admin scene
                if(isAdmin) primaryStage.setScene(adminMainScreen.getAdminMainScene(primaryStage));

                //if the user is not an admin, get the generic main scene
                else primaryStage.setScene(MainScreen.getMainScene(primaryStage));

            }

        });

        //build the scene
        GridPane grid = new GridPane();
        grid.setBackground(BLUEBACKGROUND);
        grid.setAlignment(Pos.CENTER);
        grid.add(emailL,0,0);
        grid.add(passwordL,0,1);
        grid.add(emailIn,1,0);
        grid.add(passwordIn,1,1);
        grid.add(login,1,2);
        grid.add(error,1,3);
        grid.setHgap(5);
        grid.setVgap(5);




        return new Scene(grid,600,350);
    }
}
