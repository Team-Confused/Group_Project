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
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class RegisterScreen {
    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));
    private static File picFile;
    public static Scene getRegisterScene(Stage primaryStage){
        Label error = new Label("Either you have made an error or this email has already been used.");
        error.setVisible(false);
        TextField firstNameF = new TextField();
        TextField lastNameF = new TextField();
        TextField emailF = new TextField();
        PasswordField passwordF = new PasswordField();
        FileChooser getPicture = new FileChooser();
        TextArea bioF = new TextArea();

        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        getPicture.getExtensionFilters().add(imageFilter);
        //getPicture.setSelectedExtensionFilter(imageFilter);
        CheckBox isAdmin = new CheckBox();
        GridPane grid = new GridPane();
        Button getFile = new Button("Picture");
        getFile.setOnAction(value ->{
            getPicture.setTitle("Select your picture");
            picFile = getPicture.showOpenDialog(primaryStage);
        });
        Button register = new Button("Register");
        register.setOnAction(value ->{
            Boolean pass = false;
            if(firstNameF.getText().isBlank()|lastNameF.getText().isBlank()||passwordF.getText().isBlank()|| emailF.getText().isBlank() || picFile == null){
                pass = false;
            }else {
                try {
                    pass = Manager.addUser(firstNameF.getText(), lastNameF.getText(), passwordF.getText(), bioF.getText(), emailF.getText(), Paths.get(picFile.getAbsolutePath()), isAdmin.isSelected());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(pass){
                primaryStage.setScene(LoginOrRegisterScreen.getLogRegScreen(primaryStage));
            }else{
                error.setVisible(true);
            }
        });

        grid.add(firstNameF,1,0);
        grid.add(lastNameF,1,1);
        grid.add(emailF,1,2);
        grid.add(passwordF,1,3);
        grid.add(isAdmin,1,4);
        grid.add(getFile,1,5);
        grid.add(bioF,1,6);
        grid.add(register,1, 7);
        grid.add(error,1,8);

        grid.add(new Label("First Name:"),0,0);
        grid.add(new Label("Last Name:"),0,1);
        grid.add(new Label("Email:"),0,2);
        grid.add(new Label("Password:"),0,3);
        grid.add(new Label("Admin?"),0,4);
        grid.add(new Label("Profile Picture:"),0,5);
        grid.add(new Label("Bio:"),0,6);
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(BLUEBACKGROUND);
        grid.setHgap(5);
        grid.setVgap(5);

        return new Scene(grid,600,350);

    }



}
