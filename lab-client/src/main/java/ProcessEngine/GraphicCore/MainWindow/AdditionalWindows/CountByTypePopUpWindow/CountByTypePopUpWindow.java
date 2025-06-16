package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.CountByTypePopUpWindow;

import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.TextFieldFactory;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.TypeValidator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Vector;

public class CountByTypePopUpWindow {

    public static Stage countWindow(Vector<String[]> vectorStringCollection) {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Count by type");
        TextField type = TextFieldFactory.getFieldWithValidator("type", new TypeValidator());
        Button count = ButtonFactory.getCommitButton();
        count.setTooltip(new Tooltip("Enter the type of ticket you want to count"));
        count.setText("Count!");
        count.setOnAction(e -> {
            if (type.getText().trim().isEmpty()) {
                mainLabel.setFont(Font.font("System", FontWeight.BOLD, 30));
                mainLabel.setTextFill(javafx.scene.paint.Color.RED);
                mainLabel.setText("Введите корректный Type!");
            } else {
                mainLabel.setText("Результат: " + countByType(vectorStringCollection, type.getText().trim()));
                mainLabel.setFont(Font.font("System", FontWeight.BOLD, 35));
                mainLabel.setTextFill(javafx.scene.paint.Color.AZURE);
            }
        });
        VBox box = BoxFactory.getPopUpBox(mainLabel, type, count);
        stage.setScene(new Scene(box, 500, 400));
        return stage;
    }

    public static String countByType(Vector<String[]> vectorStringCollection, String typeString) {
        int totalCountByType = 0;
        for (int i = 0; i < vectorStringCollection.size(); ++i) {
            if (vectorStringCollection.get(i)[7].equals(typeString)) {
                totalCountByType += 1;
            }
        }

        return String.valueOf(totalCountByType);
    }

}
