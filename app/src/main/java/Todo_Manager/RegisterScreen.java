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
    //define the background color
    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));

    //file location of the user's photo
    private static File picFile;

    //register GUI
    public static Scene getRegisterScene(Stage primaryStage){
        //"error" label (for when there is an error in registering)
        Label error = new Label("Either you have made an error or this email has already been used.");
        error.setVisible(false);

        //textboxes
        TextField firstNameF = new TextField();
        TextField lastNameF = new TextField();
        TextField emailF = new TextField();
        PasswordField passwordF = new PasswordField();

        //file chooser for the user's profile image
        FileChooser getPicture = new FileChooser();

        //text area for user's bio information
        TextArea bioF = new TextArea();

        //file chooser
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        getPicture.getExtensionFilters().add(imageFilter);
        //getPicture.setSelectedExtensionFilter(imageFilter);

        //is the user account an admin account?
        CheckBox isAdmin = new CheckBox();


        //grid for defining the geography of the gui
        GridPane grid = new GridPane();

        //button to open file chooser and select photo file location
        Button getFile = new Button("Picture");
        //when the button is pressed
        getFile.setOnAction(value ->{
            //get the photo
            getPicture.setTitle("Select your picture");
            picFile = getPicture.showOpenDialog(primaryStage);
        });

        //register button (should be at the bottom of the window)
        Button register = new Button("Register");
        //when you click the "register" button
        register.setOnAction(value ->{
            //perform logic on registering the new user

            Boolean pass = false;
            //if any fo the fields are blank, automatically fail
            if(firstNameF.getText().isBlank()|lastNameF.getText().isBlank()||passwordF.getText().isBlank()|| emailF.getText().isBlank() || picFile == null){
                pass = false;
            }
            //if an input is provied in every textbox and field,
            else {
                try {

                    //run the "addUser" method from Manager, passing-in the inputs the user provided
                    pass = Manager.addUser(firstNameF.getText(), lastNameF.getText(), passwordF.getText(), bioF.getText(), emailF.getText(), Paths.get(picFile.getAbsolutePath()), isAdmin.isSelected());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //if the new user is successfully registered
            if(pass){
                //go back to the login or register screen
                primaryStage.setScene(LoginOrRegisterScreen.getLogRegScreen(primaryStage));
            }else{
                //if there is a failure in registering the new user, un-hide the error box
                error.setVisible(true);
            }
        });

        //build the scene
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

        //return the scene
        return new Scene(grid,600,350);

    }



}
