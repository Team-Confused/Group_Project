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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.extern.java.Log;

import java.io.IOException;

@Log
public class passwordResetScreen {

    //definition of background color
    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));

    public static Scene getPasswordResetScreen(Stage primaryStage){
        //boolean that stores if the user is an admin
        boolean isAdmin = Manager.getLoggedInUser().isAdmin();

        log.info("user "+Manager.getLoggedInUser().getFirstName() + " " + Manager.getLoggedInUser().getLastName() + " is an admin: " + isAdmin);

        //if the user is not admin
        if(isAdmin)
            return getAdminPasswordResetScreen(primaryStage);

        else
            return getUserPasswordResetScreen(primaryStage);
    }


    public static Scene getUserPasswordResetScreen(Stage primaryStage) {
        //text input fields (old password, new password)
        TextField oldPassword = new TextField();
        PasswordField newPassword = new PasswordField();

        //labels
        Label oldPasswordL = new Label("Old Password: ");
        Label newPasswordL = new Label("New Password: ");
        Label error = new Label("The password or email is wrong.");

        //text field
        Text userMode = new Text("User Password Reset");

        error.setVisible(false);
        Button login = new Button("Login");

        //lambda expression to run when button is pressed
        login.setOnAction(value -> {
            boolean check = false;
            String email = oldPassword.getText();
            String password = newPassword.getText();
            if (email.isBlank() || password.isBlank()) {
                Boolean cleck = false;
            } else {
                try {
                    check = Manager.login(email, password);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (check == false) {
                error.setVisible(true);
            } else {
                primaryStage.setScene(MainScreen.getMainScene(primaryStage));
            }

        });


        GridPane passwordResetScreen = new GridPane();
        passwordResetScreen.setBackground(BLUEBACKGROUND);
        passwordResetScreen.setAlignment(Pos.CENTER);
        passwordResetScreen.add(userMode, 0,1);
        passwordResetScreen.add(oldPasswordL,0,0);
        passwordResetScreen.add(newPasswordL,0,1);
        passwordResetScreen.add(oldPassword,1,0);
        passwordResetScreen.add(newPassword,1,1);
        passwordResetScreen.add(login,1,2);
        passwordResetScreen.add(error,1,3);
        passwordResetScreen.setHgap(5);
        passwordResetScreen.setVgap(5);

        return new Scene(passwordResetScreen, 600, 350);
    }


    public static Scene getAdminPasswordResetScreen(Stage primaryStage)
    {
        GridPane AdminPasswordResetScreen = new GridPane();

        return new Scene(AdminPasswordResetScreen, 600, 350);
    }
}
