package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BoxFactory {

    public static HBox getBoxWithButtons(Button... buttons) {
        HBox box = new HBox();
        box.setSpacing(17);
        box.setAlignment(Pos.CENTER);

        box.getChildren().addAll(buttons);

        return box;
    }

    public static VBox getPopUpBox(Node... es) {
        VBox box = new VBox();
        box.setBackground(new Background(new BackgroundFill(Color.AZURE, new CornerRadii(5), Insets.EMPTY)));
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(es);
        box.setSpacing(10);
        box.setPadding(new Insets(40, 30, 40, 30));

        return box;
    }

    public static VBox getTextBox() {
        VBox textBox = new VBox();
        textBox.setSpacing(12);
        textBox.setAlignment(Pos.CENTER);
        textBox.setPadding(new Insets(15.0, 10.0, 15.0, 10.0));

        return textBox;
    }

}
