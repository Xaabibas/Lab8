package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.RemoveLowKeyPopUpWindow;

import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.TextFieldFactory;
import ProcessEngine.GraphicCore.SignWindow.SignWindow;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.KeyValidator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import network.Request;

import java.util.Arrays;

public class RemoveLowKeyPopUpWindow {

    public static Stage removeLowerKeyWindow(NetworkManager networkManager, String login, String password) {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel(GraphicRun.localizator.getString("insert key"));
        VBox textBox = BoxFactory.getTextBox();
        Label label = new Label();
        TextField keyField = TextFieldFactory.getFieldWithValidator(GraphicRun.localizator.getString("key"), new KeyValidator());
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
                        request.setCommandName("remove_lower_key");
                        request.setTokens("remove_lower_key " + key);
                        String netAnswer = networkManager.sendAndReceive(request);

                        if (!netAnswer.equals("Младшие элементы были успешно удалены")) {
                            label.setText(GraphicRun.localizator.getString("no key"));
                            LabelFactory.toErrorLabel(label);
                        } else {
                            label.setText(netAnswer);
                            LabelFactory.toResultLabel(label);
                        }


                    } catch (IllegalArgumentException e) {
                        label.setText(GraphicRun.localizator.getString("insert correct values"));
                        LabelFactory.toErrorLabel(label);
                    } catch (Exception e) {
                        label.setText(GraphicRun.localizator.getString("key"));
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
