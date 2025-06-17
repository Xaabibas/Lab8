package ProcessEngine.GraphicCore.SignWindow.SignUpWindow;

import ProcessEngine.DataCore.AuthCheck;
import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.TextFieldFactory;
import ProcessEngine.GraphicCore.SignWindow.SignInWindow.SignInWindow;
import ProcessEngine.GraphicCore.SignWindow.SignWindow;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.NameValidator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignUpWindow {

    public static void signUpWindow(Stage stage, AuthCheck authCheckData) {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)));
        box.setPadding(new Insets(50.0));

        Hyperlink button = new Hyperlink(GraphicRun.localizator.getString("sign in"));
        button.setOnAction(actionEvent -> SignInWindow.signInWindow(stage, authCheckData));

        Button continueButton = new Button(GraphicRun.localizator.getString("continue"));
        continueButton.setTextFill(Color.BLACK);
        continueButton.setFont(new Font(15));
        continueButton.setPadding(new Insets(8));

        VBox innerBox = new VBox(10);
        innerBox.setAlignment(Pos.CENTER);
        innerBox.setPadding(new Insets(15.0, 10.0, 15.0, 10.0));
        innerBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(30), Insets.EMPTY)));
        innerBox.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, new CornerRadii(30), new BorderWidths(3.0))));

        VBox textBox = new VBox();
        textBox.setSpacing(12);
        textBox.setPadding(new Insets(15.0, 10.0, 15.0, 10.0));
        TextField loginField = TextFieldFactory.getFieldWithValidator(GraphicRun.localizator.getString("login"), new NameValidator());
        TextField passwordField = TextFieldFactory.getPasswordField(GraphicRun.localizator.getString("password"));
        textBox.getChildren().addAll(loginField, passwordField);

        Label error = new Label(GraphicRun.localizator.getString("one more user"));
        error.setTextFill(Color.RED);

        loginField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textBox.getChildren().remove(error);
            }
        });

        continueButton.setOnAction(actionEvent -> {
            String login = loginField.getText();
            String password = passwordField.getText();

            if (login.isEmpty() || password.isEmpty()) {
                textBox.getChildren().remove(error);
                error.setText(GraphicRun.localizator.getString("insert all values"));
                textBox.getChildren().add(error);
            } else {
                boolean checkAuthResult = SignWindow.checkAuthInfo(login, password, "register");
                if (!checkAuthResult) {
                    if (!textBox.getChildren().contains(error)) {
                        textBox.getChildren().add(error);
                    }
                } else {
                    authCheckData.setAuthSuccess(checkAuthResult);
                    authCheckData.setLoginPassword(login, password);
                }
            }
        });

        Label mainLabel = new Label(GraphicRun.localizator.getString("sign up"));
        mainLabel.setFont(new Font(50));
        mainLabel.setTextFill(Color.MEDIUMBLUE);

        Label lowLabel = new Label(GraphicRun.localizator.getString("already have account?"));
        lowLabel.setTextFill(Color.MEDIUMBLUE);

        innerBox.getChildren().addAll(mainLabel, textBox, continueButton, lowLabel, button);
        box.getChildren().add(innerBox);

        Scene scene = new Scene(box, 600, 500);
        scene.setFill(Color.AZURE);

        stage.setScene(scene);

        stage.show();
    }

}
