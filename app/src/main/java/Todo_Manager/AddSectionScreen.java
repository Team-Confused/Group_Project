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

public class Test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Fields
        Label title = new Label("Section Name :");
        Label description = new Label("Description : ");
        TextField name = new TextField();
        TextArea des = new TextArea();

        //Button
        Button button = new Button("Add");
        button.setAlignment(Pos.CENTER_RIGHT);
      //  button.setOnAction();

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
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        //Boderpane
        BorderPane pane = new BorderPane();
        pane.setCenter(grid);
        pane.setBottom(hBox);
        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(pane, 600, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
