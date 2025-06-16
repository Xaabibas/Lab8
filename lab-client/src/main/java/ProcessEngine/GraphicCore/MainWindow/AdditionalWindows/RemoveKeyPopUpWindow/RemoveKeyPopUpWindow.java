package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.RemoveKeyPopUpWindow;

import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.TextFieldFactory;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.KeyValidator;
import network.Request;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Arrays;

public class RemoveKeyPopUpWindow {

    public static Stage removeKeyWindow(NetworkManager networkManager, String login, String password) {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Insert Key");
        VBox textBox = BoxFactory.getTextBox();
        Label label = new Label();
        TextField keyField = TextFieldFactory.getFieldWithValidator("key", new KeyValidator());
        textBox.getChildren().addAll(keyField, label);
        Button commit = ButtonFactory.getCommitButton();

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
                        request.setCommandName("remove_key");
                        request.setTokens("remove_key" + " " + key);
                        String netAnswer = networkManager.sendAndReceive(request);

                        if (!netAnswer.equals("Элемент успешно удален")) {
                            label.setText("В коллекции нет \nэлемента с таким ключом");
                            LabelFactory.toErrorLabel(label);
                        } else {
                            label.setText(netAnswer);
                            LabelFactory.toResultLabel(label);
                        }


                    } catch (IllegalArgumentException e) {
                        label.setText("Введите корректное значение key");
                        LabelFactory.toErrorLabel(label);
                    } catch (Exception e) {
                        label.setText("Нет элемента с таким key"); // Это надо? Если да, то в try надо будет кидать exception и здесь как раз выводить сообщение
                        LabelFactory.toErrorLabel(label);
                    }
                }
        );

        VBox box = BoxFactory.getPopUpBox(mainLabel, textBox, commit);
        Scene scene = new Scene(box, 600, 400);
        stage.setScene(scene);

        return stage;
    }

}
