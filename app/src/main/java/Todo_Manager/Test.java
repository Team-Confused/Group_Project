package Todo_Manager;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label SubTask = new Label("Sub-Task Name: ");
        Label Description = new Label(" Description: ");
        Label dueDate = new Label(" Date: ");
        Label priority = new Label("Priority");

        ComboBox combobox = new ComboBox();
        combobox.getItems().addAll("Highest","High", "Medium","Low");

        DatePicker date = new DatePicker();
        TextArea description = new TextArea();
        TextField subTask = new TextField();

        // Next Button
        Button next = new Button("Next");
        next.setAlignment(Pos.CENTER_RIGHT);
        HBox hBox = new HBox();
        hBox.getChildren().add(next);
        hBox.setAlignment(Pos.CENTER_RIGHT);



         //Grid pane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(SubTask, 0,0);
        grid.add(dueDate, 0,1);
        grid.add(Description, 0,3);
        grid.add(priority, 0,2);
        grid.add(subTask, 1,0);
        grid.add(date, 1,1);
        grid.add(combobox, 1,2);
        grid.add(description, 1,3);
       // grid.add(hBox,10,10);
        grid.setHgap(10);
        grid.setVgap(10);


        BorderPane pane = new BorderPane();
        pane.setCenter(grid);
        pane.setBottom(hBox);

        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(pane, 600,350 );
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
