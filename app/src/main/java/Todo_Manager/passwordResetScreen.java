/**
 * MIT License
 * <p>
 * Copyright (c) 2021 Team-Confused
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
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
import java.util.UUID;

//start/generate logging class
@Log
public class passwordResetScreen {

    //definition of background color
    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));

    public static Scene getPasswordResetScreen(Stage primaryStage) {
        //boolean that stores if the user is an admin
        boolean isAdmin = Manager.getLoggedInUser().isAdmin();

        log.info("user " + Manager.getLoggedInUser().getFirstName() + " " + Manager.getLoggedInUser().getLastName() + " is an admin: " + isAdmin);

        //if the user is admin
        if (isAdmin) {
            //return getAdminPasswordResetScreen(primaryStage);
        }
        //if the user is not an admin, or some error occurs in determining if the user is an admin (for security reasons)
            return getUserPasswordResetScreen(primaryStage);

    }


    //getUserPasswordResetScreen
    /*
            UI for a user to reset their password
            Upon fulfilling a few initial conditions, the method userPasswordReset in Manager is called
            The return is a scene
    */
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

        //set the visibility of "error" to not visible
        error.setVisible(false);

        //button to change the password
        Button changePassword = new Button("changePassword");

        //lambda expression to run when button is pressed
        changePassword.setOnAction(value -> {
            //value to determine if success in resetting the password
            int resetPassword = -1;

            //get the strings the user had provided
            String oldPasswordText = oldPassword.getText();
            String newPasswordText = newPassword.getText();

            //filter out (auto-fail) if either of the fields is empty
            if (oldPasswordText.isBlank() || newPasswordText.isBlank()) {
                resetPassword = -1;
            }
            //confirm that the user-imputed "old password" matches what the password is currently known as
            else if (oldPasswordText.equals(Manager.getLoggedInUser().getPassword())) {
                try {

                    //reset the password
                    resetPassword = Manager.userPasswordReset(newPasswordText);

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }

            //on success of resetting the password, return to the main screen
            if (resetPassword == 1) {
                primaryStage.setScene(MainScreen.getMainScene(primaryStage));

            }
            //if there was an error in changing the password
            else {
                error.setVisible(true);

            }

        });

        //assemble the various elements in the GridPane
        GridPane passwordResetScreen = new GridPane();
        passwordResetScreen.setBackground(BLUEBACKGROUND);
        passwordResetScreen.setAlignment(Pos.CENTER);
        passwordResetScreen.add(userMode, 1, 0);
        passwordResetScreen.add(oldPasswordL, 0, 1);
        passwordResetScreen.add(newPasswordL, 0, 2);
        passwordResetScreen.add(oldPassword, 1, 1);
        passwordResetScreen.add(newPassword, 1, 2);
        passwordResetScreen.add(changePassword, 1, 3);
        passwordResetScreen.add(error, 1, 4);
        passwordResetScreen.setHgap(5);
        passwordResetScreen.setVgap(5);

        //return the assembled scene
        return new Scene(passwordResetScreen, 600, 350);
    }


    public static Scene getAdminPasswordResetScreen(Stage primaryStage, User workingUser) {
        //text input fields (old password, new password)
        // TextField user = new TextField();
        PasswordField newPassword = new PasswordField();

        //labels
        //Label userL = new Label("User UUID:");
        Label newPasswordL = new Label("New Password: ");
        Label error = new Label("Error in resetting password.");

        //text field
        Text userMode = new Text("Admin Password Reset");

        //set the visibility of "error" to not visible
        error.setVisible(false);

        //button to change the password
        Button changePassword = new Button("Change User's Password");

        //lambda expression to run when button is pressed
        changePassword.setOnAction(value -> {
            //value to determine if success in resetting the password
            int resetPassword = -1;

            //get the strings the user had provided
            //String userUUIDText = user.getText();
            String newPasswordText = newPassword.getText();

            //filter out (auto-fail) if either of the fields is empty
            if (newPasswordText.isBlank()) {
                resetPassword = -1;
            }

            //reset the selected user's password
            else {
                try {
                    //reset the password
                    resetPassword = Manager.adminPasswordReset(workingUser.getId(), newPasswordText);
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }

            if (resetPassword == 1) {
                primaryStage.setScene(adminMainScreen.getAdminMainScene(primaryStage));

            }
            //if there was an error in changing the password
            else {
                error.setVisible(true);

            }

        });


        //grid pane
        GridPane passwordResetScreen = new GridPane();
        passwordResetScreen.setBackground(BLUEBACKGROUND);
        passwordResetScreen.setAlignment(Pos.CENTER);

        //labels
        passwordResetScreen.add(userMode, 1, 0);
        //passwordResetScreen.add(userL,0,1);
        passwordResetScreen.add(newPasswordL, 0, 3);

        //text input fields
        //passwordResetScreen.add(user,1,1);
        passwordResetScreen.add(newPassword, 1, 3);
        passwordResetScreen.add(changePassword, 1, 4);
        passwordResetScreen.add(error, 1, 5);
        passwordResetScreen.setHgap(5);
        passwordResetScreen.setVgap(5);

        //return the scene
        return new Scene(passwordResetScreen, 600, 350);
    }
}
