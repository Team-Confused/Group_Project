package Todo_Manager;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SortScreen  {


    //create the a scene to sort
    public static Scene getSortScene(Stage primaryStage)  {


        // Sort button
        Button button = new Button();
        button.setText("Sort");

        // Check box for sort
        CheckBox cb1 = new CheckBox("Task");
        CheckBox cb2 = new CheckBox("Labels");
        CheckBox cb3 = new CheckBox("Date");
        CheckBox cb4 = new CheckBox("Priority");
       // checkbox1.setIndeterminate(false);\

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("Highest","High", "Medium","Low");

        //Adding button to hbox
        HBox box = new HBox();
        box.getChildren().add(button);
        box.setAlignment(Pos.CENTER_RIGHT);

        //datepicker to select date for sort parameter
        DatePicker date = new DatePicker();

        //dropdown menu
        VBox dropdownbox = new VBox();
        dropdownbox.getChildren().addAll(date,comboBox);
        dropdownbox.setAlignment(Pos.CENTER_LEFT);
        dropdownbox.setSpacing(50);

        // Adding checkbox to vbox
        VBox box1 = new VBox();
        box1.getChildren().addAll(cb1, cb2, cb3, cb4);;
        box1.setPadding(new Insets(5,5,5,5));
        box1.setSpacing(35);
        box1.setAlignment(Pos.TOP_LEFT);


        //Boderpane
        BorderPane sortbar = new BorderPane();
        sortbar.setLeft(box1); // checkbox - vbox
        sortbar.setBottom(box); // sort button
        sortbar.setCenter(dropdownbox);
        sortbar.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(sortbar, 600, 400);
       // primaryStage.setScene(scene);
        //primaryStage.show();

        //return the scene
        return scene;
    }
}
