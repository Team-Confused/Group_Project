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

//start/generate logging class
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
        Label error = new Label("Error in resetting password.");

        //text field
        Text userMode = new Text("User Password Reset");

        error.setVisible(false);
        Button changePassword = new Button("changePassword");

        //lambda expression to run when button is pressed
        changePassword.setOnAction(value -> {
            int resetPassword = -1;

            String oldPasswordText = oldPassword.getText();
            String newPasswordText = newPassword.getText();
            if (oldPasswordText.isBlank() || newPasswordText.isBlank()) {
                resetPassword = -1;
            }
            else if(oldPasswordText.equals(Manager.getLoggedInUser().getPassword())){
                try {
                    resetPassword = Manager.userPasswordReset(newPasswordText);
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }

            if (resetPassword == 1) {
                primaryStage.setScene(MainScreen.getMainScene(primaryStage));

            }
            //if there was an error in changing the password
            else {
                error.setVisible(true);

            }

        });


        GridPane passwordResetScreen = new GridPane();
        passwordResetScreen.setBackground(BLUEBACKGROUND);
        passwordResetScreen.setAlignment(Pos.CENTER);
        passwordResetScreen.add(userMode, 1,0);
        passwordResetScreen.add(oldPasswordL,0,1);
        passwordResetScreen.add(newPasswordL,0,2);
        passwordResetScreen.add(oldPassword,1,1);
        passwordResetScreen.add(newPassword,1,2);
        passwordResetScreen.add(changePassword,1,3);
        passwordResetScreen.add(error,1,4);
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
