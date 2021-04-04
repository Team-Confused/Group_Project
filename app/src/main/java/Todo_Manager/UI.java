package Todo_Manager;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UI extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //this is the code for the scene that stays up for 6 seconds

        primaryStage.setScene(SplashScreen.getSplashScene());
        primaryStage.setResizable(false);
        primaryStage.show();
        //The following is the stuff that let's it 'pause' for a few seconds
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(1000);//This is how long it stays on the splash screen todo: reset to 5000 it's just lower to make testing faster
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                //this block is the code that happens after 6 seconds
                primaryStage.setScene(LoginOrRegisterScreen.getLogRegScreen(primaryStage));
                primaryStage.setTitle("Todo List Manager");
                primaryStage.show();

            }
        });
        new Thread(sleeper).start();
    }
}
