package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.RemoveLowPopUpWindow;

import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.TextFieldFactory;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.PriceValidator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import moduls.Ticket;
import network.Request;

import java.util.Arrays;

public class RemoveLowPopUpWindow {

    public static Stage removeLowerWindow(NetworkManager networkManager, String login, String password) {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Insert price"); // Сравнение идет по цене
        TextField priceField = TextFieldFactory.getFieldWithValidator("price", new PriceValidator());
        Label label = LabelFactory.getErrorLabel("");
        VBox textBox = BoxFactory.getTextBox();
        textBox.getChildren().addAll(priceField, label);
        Button commit = ButtonFactory.getCommitButton();

        VBox box = BoxFactory.getPopUpBox(mainLabel, textBox, commit);
        Scene scene = new Scene(box, 600, 400);
        stage.setScene(scene);

        commit.setOnAction(
                event -> {
                    try {
                        float price = Float.parseFloat(priceField.getText());

                        Ticket fictitious = new Ticket();
                        fictitious.setPrice(price);

                        Request request = new Request();
                        request.setUser(login);
                        request.setPassword(Arrays.toString(password
                                .chars()
                                .mapToObj(c -> String.valueOf((char) c))
                                .toArray(String[]::new))
                        );
                        request.setObj(fictitious);

                        request.setCommandName("remove_lower");
                        request.setTokens("remove_lower");

                        String netAnswer = networkManager.sendAndReceive(request);

                        label.setText(netAnswer);
                        LabelFactory.toResultLabel(label);

                    } catch (IllegalArgumentException e) {
                        label.setText("Введите корректное значение");
                        LabelFactory.toErrorLabel(label);
                    }
                }
        );

        return stage;
    }

}
