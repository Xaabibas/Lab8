package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LabelFactory {

    public static Label getMainLabel(String name) {
        Label label = new Label(name);
        label.setFont(Font.font("System", FontWeight.BOLD, 19));
        label.setTextFill(Color.MEDIUMBLUE);

        return label;
    }

    public static Label getUsualLabel(String name) {
        Label label = new Label(name);
        label.setFont(new Font(16));
        label.setTextFill(Color.MEDIUMBLUE);

        return label;
    }

    public static Label getErrorLabel(String name) {
        Label label = new Label(name);
        label.setFont(Font.font("System", FontWeight.BOLD, 16));
        label.setTextFill(Color.RED);
        label.setAlignment(Pos.CENTER);

        return label;
    }

    public static Label getResultLabel(String name) {
        Label label = new Label(name);
        label.setFont(Font.font("System", FontWeight.BOLD, 18));
        label.setTextFill(Color.MEDIUMBLUE);
        label.setAlignment(Pos.CENTER);

        return label;
    }

    public static void toErrorLabel(Label label) {
        label.setFont(Font.font("System", FontWeight.BOLD, 16));
        label.setTextFill(Color.RED);
        label.setAlignment(Pos.CENTER);
    }

    public static void toResultLabel(Label label) {
        label.setFont(Font.font("System", FontWeight.BOLD, 18));
        label.setTextFill(Color.MEDIUMBLUE);
        label.setAlignment(Pos.CENTER);
    }

}
