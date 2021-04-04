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


    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));
    public static Scene getLoginScene(Stage primaryStage){
        TextField emailIn = new TextField();
        PasswordField passwordIn = new PasswordField();
        Label emailL  = new Label("Email: ");
        Label passwordL = new Label("Password: ");
        Label error = new Label("The password or email is wrong.");
        error.setVisible(false);
        Button login = new Button("Login");
        login.setOnAction(value->{
            boolean check = false;
            String email = emailIn.getText();
            String password = passwordIn.getText();
            if(email.isBlank() || password.isBlank()){
                Boolean cleck = false;
            }else {
                try {
                    check = Manager.login(email, password);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (check == false){
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
