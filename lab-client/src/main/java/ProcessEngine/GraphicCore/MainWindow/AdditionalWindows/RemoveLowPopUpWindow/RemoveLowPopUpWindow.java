package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.RemoveLowPopUpWindow;

import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.TextFieldFactory;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.PriceValidator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RemoveLowPopUpWindow {

    public static Stage removeLowerWindow() {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Insert price"); // Сравнение идет по цене
        TextField price = TextFieldFactory.getFieldWithValidator("price", new PriceValidator());
        Button commit = ButtonFactory.getCommitButton(); // Написать setOnAction

        VBox box = BoxFactory.getPopUpBox(mainLabel, price, commit);
        Scene scene = new Scene(box, 300, 400);
        stage.setScene(scene);

        return stage;
    }

}
