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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RemoveKeyPopUpWindow {

    public static Stage removeKeyWindow() {
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

                        // отправить команду remove_key key (вроде)

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
