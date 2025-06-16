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
        TextField keyField = TextFieldFactory.getFieldWithValidator("key", new KeyValidator());
        textBox.getChildren().add(keyField);
        Button commit = ButtonFactory.getCommitButton();
        Label error = new Label("Введите корректное значение key");
        error.setTextFill(Color.RED);

        commit.setOnAction(
                event -> {
                    try {
                        long key = Long.parseLong(keyField.getText());

                        Request insertRequest = new Request();
                        insertRequest.setUser(login);
                        insertRequest.setPassword(Arrays.toString(password
                            .chars()
                            .mapToObj(c -> String.valueOf((char) c))
                            .toArray(String[]::new))
                        );
                        insertRequest.setCommandName("remove_key");
                        insertRequest.setTokens("remove_key" + " " + key);
                        String netAnswer = networkManager.sendAndReceive(insertRequest);

                        if (!netAnswer.equals("Элемент успешно удален")) {
                            mainLabel.setFont(Font.font("System", FontWeight.BOLD, 19));
                            mainLabel.setTextFill(javafx.scene.paint.Color.RED);
                            mainLabel.setText("В коллекции нет \nэлемента с таким ключом");
                        } else {
                            mainLabel.setFont(Font.font("System", FontWeight.BOLD, 19));
                            mainLabel.setTextFill(javafx.scene.paint.Color.FORESTGREEN);
                            mainLabel.setText("Insert your data");
                        }

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
