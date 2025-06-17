package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.CountByTypePopUpWindow;

import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.TextFieldFactory;
import ProcessEngine.GraphicCore.SignWindow.SignWindow;
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
        Label mainLabel = LabelFactory.getMainLabel(GraphicRun.localizator.getString("count by type"));
        TextField type = TextFieldFactory.getFieldWithValidator(GraphicRun.localizator.getString("type"), new TypeValidator());
        VBox textBox = BoxFactory.getTextBox();
        textBox.getChildren().add(type);
        textBox.getChildren().add(new Label());

        Button count = ButtonFactory.getCommitButton();
        count.setTooltip(new Tooltip(GraphicRun.localizator.getString("enter type")));
        count.setText(GraphicRun.localizator.getString("count!"));
        count.setOnAction(e -> {
            textBox.getChildren().remove(1);
            if (type.getText().trim().isEmpty()) {
                textBox.getChildren().add(LabelFactory.getErrorLabel(GraphicRun.localizator.getString("insert correct type")));
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
        return GraphicRun.localizator.getString("elements of type") + type + ": " + count;
    }

}
