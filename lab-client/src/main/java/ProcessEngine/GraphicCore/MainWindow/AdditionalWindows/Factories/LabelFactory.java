package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LabelFactory {

        public static Label getMainLabel(String name) {
            Label label = new Label(name);
            label.setFont(Font.font("System", FontWeight.BOLD, 20));
            label.setTextFill(Color.FORESTGREEN);

            return label;
        }

        public static Label fetUsualLabel(String name) {
            Label label = new Label(name);
            label.setFont(new Font(16));
            label.setTextFill(Color.FORESTGREEN);

            return label;
        }

    }
