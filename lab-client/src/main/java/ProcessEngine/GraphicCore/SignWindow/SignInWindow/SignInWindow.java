package ProcessEngine.GraphicCore.SignWindow.SignInWindow;

import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.TextFieldFactory;
import ProcessEngine.GraphicCore.SignWindow.SignUpWindow.SignUpWindow;
import ProcessEngine.GraphicCore.SignWindow.SignWindow;
import ProcessEngine.DataCore.AuthCheck;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignInWindow {
    
    public static void signInWindow(Stage stage, AuthCheck authCheckData) {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setBackground(Background.EMPTY);
        box.setPadding(new Insets(50.0));

        Button continueButton = new Button("Continue");
        continueButton.setTextFill(Color.GREEN);
        continueButton.setFont(new Font(15));
        continueButton.setPadding(new Insets(8));

        VBox innerBox = new VBox(10);
        innerBox.setAlignment(Pos.CENTER);
        innerBox.setPadding(new Insets(15.0, 10.0, 15.0, 10.0));
        innerBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(30), Insets.EMPTY)));
        innerBox.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(30), new BorderWidths(3.0))));

        VBox textBox = new VBox();
        textBox.setSpacing(12);
        textBox.setPadding(new Insets(15.0, 10.0, 15.0, 10.0));
        TextField loginField = TextFieldFactory.getFieldWithValidator("name", new NameValidator());
        TextField passwordField = TextFieldFactory.getPasswordField("password");
        textBox.getChildren().addAll(loginField, passwordField);

        Label error = new Label("Неверное имя пользователя или пароль");
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
                error.setText("Введите значения всех полей");
                textBox.getChildren().add(error);
            } else {
                boolean checkAuthResult = SignWindow.checkAuthInfo(login, password, "login");
                if (!checkAuthResult) {
                    if (!textBox.getChildren().contains(error)) {
                        textBox.getChildren().add(error);
                    }
                } else {
                    authCheckData.setAuthSeccess(checkAuthResult);
                    authCheckData.setLoginPassword(login, password);
                }
            }
        });

        Label mainLabel = new Label("Sign in!");
        mainLabel.setFont(new Font(50));
        mainLabel.setTextFill(Color.GREEN);

        Hyperlink button = new Hyperlink("Sign up!");
        button.setOnAction(actionEvent -> SignUpWindow.signUpWindow(stage, authCheckData));

        Label lowLabel = new Label("Yet haven't account?");
        lowLabel.setTextFill(Color.GREEN);

        innerBox.getChildren().addAll(mainLabel, textBox, continueButton, lowLabel, button);
        box.getChildren().add(innerBox);

        Scene scene = new Scene(box, 600, 500);
        scene.setFill(Color.AZURE);

        stage.setScene(scene);
        stage.show();
    }

}
