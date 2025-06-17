package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.CountByTypePopUpWindow;

import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.TextFieldFactory;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.TypeValidator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import network.Request;

import java.util.Arrays;

public class CountByTypePopUpWindow {

    public static Stage countWindow(NetworkManager networkManager, String login, String password) {
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
                textBox.getChildren().add(LabelFactory.getResultLabel(countByType(networkManager, login, password, type.getText().trim())));
            }
        });

        VBox box = BoxFactory.getPopUpBox(mainLabel, textBox, count);
        stage.setScene(new Scene(box, 500, 400));
        return stage;
    }

    public static String countByType(NetworkManager networkManager, String login, String password, String type) {
        Request request = new Request();
        request.setUser(login);
        request.setPassword(Arrays.toString(password
                .chars()
                .mapToObj(c -> String.valueOf((char) c))
                .toArray(String[]::new))
        );
        request.setCommandName("count_by_type");
        request.setTokens("count_by_type " + type);

        return networkManager.sendAndReceive(request);
    }

}
