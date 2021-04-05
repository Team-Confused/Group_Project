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

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class AddSectionScreen{
    public static Scene getAddSectionScreen(Stage primaryStage)  {
        //Fields
        Label title = new Label("Section Name :");
        Label description = new Label("Description : ");
        Label error = new Label("You have an empty field.");
        error.setVisible(false);
        TextField name = new TextField();
        TextArea des = new TextArea();


        //Button
        Button button = new Button("Add");
        button.setAlignment(Pos.CENTER_RIGHT);
        button.setOnAction(value ->{
            Boolean pass = false;
            if(name.getText().isBlank()||des.getText().isBlank()) {
                error.setVisible(true);
            }
            else{
                try{
                    Manager.addSection(name.getText(),des.getText());

                }catch (IOException e){
                    e.printStackTrace();
                }
                    primaryStage.setScene(MainScreen.getMainScene(primaryStage));
            }
        });

        //HBox
        HBox hBox = new HBox();
        hBox.getChildren().add(button);
        hBox.setAlignment(Pos.CENTER_RIGHT);

        //Grid pane
        GridPane grid = new GridPane();
        grid.add(title,0,0);
        grid.add(description,0,1);
        grid.add(name,1,0);
        grid.add(des,1,1);
        grid.add(error,1,8);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        //Boderpane
        BorderPane pane = new BorderPane();
        pane.setCenter(grid);
        pane.setBottom(hBox);
        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(pane, 600, 350);
       // primaryStage.setScene(scene);
      //  primaryStage.show();
        return scene;
    }
}
