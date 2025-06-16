package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LabelFactory {

        public static Label getMainLabel(String name) {
            Label label = new Label(name);
            label.setFont(Font.font("System", FontWeight.BOLD, 19));
            label.setTextFill(Color.FORESTGREEN);

            return label;
        }

        public static Label getUsualLabel(String name) {
            Label label = new Label(name);
            label.setFont(new Font(16));
            label.setTextFill(Color.FORESTGREEN);

            return label;
        }

        public static Label getErrorLabel(String name) {
            Label label = new Label(name);
            label.setFont(Font.font("System", FontWeight.BOLD, 16));
            label.setTextFill(Color.RED);

            return label;
        }

        public static Label getResultLabel(String name) {
            Label label = new Label(name);
            label.setFont(Font.font("System", FontWeight.BOLD, 18));
            label.setTextFill(Color.FORESTGREEN);

            return label;
        }

        public static void toErrorLabel(Label label) {
            label.setFont(Font.font("System", FontWeight.BOLD, 16));
            label.setTextFill(Color.RED);
        }

        public static void toResultLabel(Label label) {
            label.setFont(Font.font("System", FontWeight.BOLD, 18));
            label.setTextFill(Color.FORESTGREEN);
        }

    }
