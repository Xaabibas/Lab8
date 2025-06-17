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
import java.util.Vector;

public class CountByTypePopUpWindow {

    public static Stage countWindow(Vector<String[]> collectionData) {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Count by type");
        TextField type = TextFieldFactory.getFieldWithValidator("type", new TypeValidator());
        VBox textBox = BoxFactory.getTextBox();
        textBox.getChildren().add(type);
        textBox.getChildren().add(new Label());

        Button count = ButtonFactory.getCommitButton();
        count.setTooltip(new Tooltip("Enter the type of ticket you want to count"));
        count.setText("Count!");
        count.setOnAction(e -> {
            textBox.getChildren().remove(1);
            if (type.getText().trim().isEmpty()) {
                textBox.getChildren().add(LabelFactory.getErrorLabel("Введите корректный Type!"));
            } else {
                textBox.getChildren().add(LabelFactory.getResultLabel(countByType(collectionData, type.getText().trim())));
            }
        });

        VBox box = BoxFactory.getPopUpBox(mainLabel, textBox, count);
        stage.setScene(new Scene(box, 500, 400));
        return stage;
    }

    public static String countByType(Vector<String[]> collectionData, String type) {
        long count = collectionData.stream()
                .filter(item -> item[7].equals(type))
                .count();
        return "Элементов с Type = " + type + " : " + String.valueOf(count);
    }

}
