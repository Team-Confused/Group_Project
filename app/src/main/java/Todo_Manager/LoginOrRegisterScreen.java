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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class LoginOrRegisterScreen {
    //define the background color
    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));

    //login or register screen
    public static Scene getLogRegScreen(Stage primaryStage){

        //register button
        Button startRegistration = new Button("Register");
        //when "register" button is pressed
        startRegistration.setOnAction(value ->{
            //register screen
            primaryStage.setScene(RegisterScreen.getRegisterScene(primaryStage));
        });

        //login button
        Button startLogin = new Button("Login");
        //when the login button is pressed
        startLogin.setOnAction(value ->{
            //go to the login screen
            primaryStage.setScene(LoginScreen.getLoginScene(primaryStage));
        });

        //build and align the elements
        HBox logReg = new HBox();
        logReg.getChildren().addAll(startRegistration,startLogin);
        logReg.setAlignment(Pos.CENTER);
        logReg.setSpacing(50);
        logReg.setBackground(BLUEBACKGROUND);//This bitch goes on whatever container you put in the scene.
        Scene loginOrRegister = new Scene(logReg,600,350);

        //return the scene
        return loginOrRegister;


    }
}
