package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.RemoveKeyPopUpWindow;

import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.TextFieldFactory;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.KeyValidator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RemoveKeyPopUpWindow {

    public static Stage removeKeyWindow() {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Insert Key");
        TextField key = TextFieldFactory.getFieldWithValidator("key", new KeyValidator());
        Button commit = ButtonFactory.getCommitButton(); // Написать setOnAction

        VBox box = BoxFactory.getPopUpBox(mainLabel, key, commit);
        Scene scene = new Scene(box, 300, 400);
        stage.setScene(scene);

        return stage;
    }

}
