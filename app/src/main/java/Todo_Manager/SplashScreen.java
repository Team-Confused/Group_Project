package Todo_Manager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class SplashScreen {
    private static final Background BLUEBACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));
    public static Scene getSplashScene() {
        Label copyright = new Label("Copyright Info Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        copyright.setAlignment(Pos.BOTTOM_CENTER);
        copyright.setMaxSize(550, 350);
        copyright.setWrapText(true);
        copyright.setTextAlignment(TextAlignment.CENTER);


        Image img = new Image("file:ConfusedLogo.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(new Image(img.getUrl(), 800, 300, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Pane pane = new Pane();
        pane.getChildren().addAll(copyright);

        pane.setBackground(new Background(backgroundImage));
        Scene splashScene = new Scene(pane, 600, 350);
        return splashScene;
    }

}
