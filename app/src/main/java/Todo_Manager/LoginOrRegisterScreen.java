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
    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));
    public static Scene getLogRegScreen(Stage primaryStage){
        Button startRegistration = new Button("Register");
        startRegistration.setOnAction(value ->{
            primaryStage.setScene(RegisterScreen.getRegisterScene(primaryStage));
        });
        Button startLogin = new Button("Login");
        startLogin.setOnAction(value ->{
            primaryStage.setScene(LoginScreen.getLoginScene(primaryStage));
        });
        HBox logReg = new HBox();
        logReg.getChildren().addAll(startRegistration,startLogin);
        logReg.setAlignment(Pos.CENTER);
        logReg.setSpacing(50);
        logReg.setBackground(BLUEBACKGROUND);//This bitch goes on whatever container you put in the scene.
        Scene loginOrRegister = new Scene(logReg,600,350);

        return loginOrRegister;


    }
}
