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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class RemoveSubTaskScreen {

    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));

    public static Scene getRemoveSubTaskScene(Stage primaryStage, Task workingTask){
        ListView<SubTask> labelView= new ListView<>();
        labelView.getItems().addAll(workingTask.getSubtaskList());
        labelView.setCellFactory(param -> new ListCell<SubTask>(){
            @Override
            protected void updateItem(SubTask  item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item==null) {
                    setGraphic(null);
                    setText(null);
                    // other stuff to do...

                }else{

                    // set the width's
                    setMinWidth(param.getWidth());
                    setMaxWidth(param.getWidth());
                    setPrefWidth(param.getWidth());

                    // allow wrapping
                    setWrapText(true);

                    setText(item.toString());


                }
            }
        });
        labelView.getSelectionModel().selectFirst();

        Button removeLabel = new Button("Remove");
        removeLabel.setOnAction(value->{
            if(!labelView.getItems().isEmpty()) {
                try {
                    Manager.removeSubTask(workingTask, labelView.getSelectionModel().getSelectedItem());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            primaryStage.setScene(MainScreen.getMainScene(primaryStage));
        });
        HBox root = new HBox();
        root.getChildren().addAll(labelView,removeLabel);
        root.setAlignment(Pos.BASELINE_LEFT);
        root.setBackground(BLUEBACKGROUND);
        return new Scene(root,600,400);
    }
}
