package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.UpdatePopUpWindow;

import ProcessEngine.ProcessCore.validatorModule.fieldValidators.KeyValidator;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.*;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.InsertPopUpWindow.InsertPopUpWindow;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdatePopUpWindow {

    public static Stage updateWindow() {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Update");
        TextField key = TextFieldFactory.getFieldWithValidator("key", new KeyValidator());
        Button commit = ButtonFactory.getCommitButton(); // Надо проверить валидность ключа и перейти к обновлению данных
        commit.setOnAction(
                event -> {
                    Stage subStage = InsertPopUpWindow.insertWindow();

                    subStage.show(); // скорее всего переделаю
                }
        );
        VBox box = BoxFactory.getPopUpBox(mainLabel, key, commit);

        stage.setScene(new Scene(box, 500, 600));

        return stage;
    }
    
}
