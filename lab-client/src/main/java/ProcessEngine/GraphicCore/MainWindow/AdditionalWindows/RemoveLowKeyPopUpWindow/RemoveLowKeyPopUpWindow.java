package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.RemoveLowKeyPopUpWindow;

import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.TextFieldFactory;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.KeyValidator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import network.Request;

import java.util.Arrays;

public class RemoveLowKeyPopUpWindow {

    public static Stage removeLowerKeyWindow(NetworkManager networkManager, String login, String password) {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Insert Key");
        VBox textBox = BoxFactory.getTextBox();
        TextField keyField = TextFieldFactory.getFieldWithValidator("key", new KeyValidator());
        textBox.getChildren().add(keyField);
        Button commit = ButtonFactory.getCommitButton();
        Label error = new Label("Введите корректное значение key");
        error.setTextFill(Color.RED);

        commit.setOnAction(
                event -> {
                    try {
                        long key = Long.parseLong(keyField.getText());

                        Request request = new Request();
                        request.setUser(login);
                        request.setPassword(Arrays.toString(password
                                .chars()
                                .mapToObj(c -> String.valueOf((char) c))
                                .toArray(String[]::new))
                        );
                        request.setCommandName("remove_lower_key");
                        request.setTokens("remove_lower_key" + " " + key);
                        String netAnswer = networkManager.sendAndReceive(request);

                        // Какое-то сообщение наверное вывести

                        textBox.getChildren().remove(error);

                    } catch (IllegalArgumentException e) {
                        if (!textBox.getChildren().contains(error)) {
                            textBox.getChildren().add(error);
                        }
                    } catch (Exception e) {
                        error.setText("Нет элемента с таким key"); // Это надо? Если да, то в try надо будет кидать exception и здесь как раз выводить сообщение
                    }
                }
        );

        VBox box = BoxFactory.getPopUpBox(mainLabel, textBox, commit);
        Scene scene = new Scene(box, 300, 400);
        stage.setScene(scene);

        return stage;
    }

}
