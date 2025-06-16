package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.RemoveGrPopUpWindow;

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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import moduls.Ticket;
import network.Request;

import java.util.Arrays;

public class RemoveGrPopUpWindow {

    public static Stage removeGreaterWindow(NetworkManager networkManager, String login, String password) {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Insert price"); // Сравнение идет по цене
        TextField priceField = TextFieldFactory.getFieldWithValidator("price", new PriceValidator());
        VBox textBox = BoxFactory.getTextBox();
        textBox.getChildren().add(priceField);
        Button commit = ButtonFactory.getCommitButton();

        VBox box = BoxFactory.getPopUpBox(mainLabel, textBox, commit);
        Scene scene = new Scene(box, 300, 400);
        stage.setScene(scene);
        Label error = new Label("Некорректное значение поля price");
        error.setTextFill(Color.RED);

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

                        request.setCommandName("remove_greater");
                        request.setTokens("remove_greater");

                        String netAnswer = networkManager.sendAndReceive(request);

                        // Какое-то сообщение наверное надо вывести
                        textBox.getChildren().remove(error);

                    } catch (IllegalArgumentException e) {
                        if (!textBox.getChildren().contains(error)) {
                            textBox.getChildren().add(error);
                        }
                    }
                }
        );

        return stage;
    }

}
