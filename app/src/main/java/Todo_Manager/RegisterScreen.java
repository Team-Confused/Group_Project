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