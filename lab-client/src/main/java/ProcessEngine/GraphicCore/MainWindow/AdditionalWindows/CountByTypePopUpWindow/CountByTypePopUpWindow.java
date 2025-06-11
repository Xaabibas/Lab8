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

public class CountByTypePopUpWindow {

    public static Stage countWindow() {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Count by type");
        TextField type = TextFieldFactory.getFieldWithValidator("type", new TypeValidator());
        Button count = ButtonFactory.getCommitButton(); // Написать setOnAction
        count.setTooltip(new Tooltip("Enter the type of ticket you want to count"));
        count.setText("Count!");
        // Необходимо сделать запрос/подсчет и вывести
        VBox box = BoxFactory.getPopUpBox(mainLabel, type, count);
        stage.setScene(new Scene(box, 500, 400));
        return stage;
    }

}
