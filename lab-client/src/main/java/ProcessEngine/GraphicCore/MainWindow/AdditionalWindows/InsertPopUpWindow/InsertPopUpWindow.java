package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.InsertPopUpWindow;

import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.TextFieldFactory;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.CountryValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.DateValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.EyeValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.HairValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.KeyValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.NameValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.PriceValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.TypeValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.XValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.YValidator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InsertPopUpWindow {

    public static Stage insertWindow() {
        Stage stage = new Stage();

        Label mainLabel = LabelFactory.getMainLabel("Insert your data");
        TextField key = TextFieldFactory.getFieldWithValidator("key", new KeyValidator());
        TextField name = TextFieldFactory.getFieldWithValidator("name", new NameValidator());
        TextField x = TextFieldFactory.getFieldWithValidator("x", new XValidator());
        TextField y = TextFieldFactory.getFieldWithValidator("y", new YValidator());
        TextField price = TextFieldFactory.getFieldWithValidator("price", new PriceValidator());
        TextField type = TextFieldFactory.getFieldWithValidator("type", new TypeValidator());
        Label personData = LabelFactory.fetUsualLabel("Person Data");
        TextField birthday = TextFieldFactory.getFieldWithValidator("birthday", new DateValidator());
        TextField country = TextFieldFactory.getFieldWithValidator("country", new CountryValidator());
        TextField eye = TextFieldFactory.getFieldWithValidator("eye color", new EyeValidator());
        TextField hair = TextFieldFactory.getFieldWithValidator("hair color", new HairValidator());

        Button commit = ButtonFactory.getCommitButton(); // Написать setOnAction

        VBox box = BoxFactory.getPopUpBox();
        box.getChildren().addAll(mainLabel, key, name, x, y, price, type, personData, birthday, country, eye, hair, commit);
        Scene scene = new Scene(box, 500, 600);

        stage.setScene(scene);

        return stage;
    }

}
