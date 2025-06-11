package ProcessEngine.GraphicCore.SignWindow.SignUpWindow;

import ProcessEngine.GraphicCore.SignWindow.SignInWindow.SignInWindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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

public class SignUpWindow {

    public static void signUpWindow(Stage stage) {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)));
        box.setPadding(new Insets(50.0));

        Hyperlink button = new Hyperlink("Log in!");
        button.setOnAction(actionEvent -> SignInWindow.signInWindow(stage));

        Button continueButton = new Button("Continue");
        continueButton.setTextFill(Color.GREEN);
        continueButton.setFont(new Font(15));
        continueButton.setPadding(new Insets(8));


        VBox innerBox = new VBox(10);
        innerBox.setAlignment(Pos.CENTER);
        innerBox.setPadding(new Insets(15.0, 10.0, 15.0, 10.0));
        innerBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(30), Insets.EMPTY)));
        innerBox.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(30), new BorderWidths(3.0))));

        TextField nameField = new TextField();
        nameField.setFont(new Font(12));
        nameField.setPrefColumnCount(30);
        nameField.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(5), Insets.EMPTY)));
        nameField.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));

        TextField passwordField = new PasswordField();
        passwordField.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
        passwordField.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(5), Insets.EMPTY)));
        passwordField.setFont(new Font(12));

        continueButton.setOnAction(actionEvent -> {
            String name = nameField.getText();
            String password = passwordField.getText();

        });

        Label mainLabel = new Label("Sign up!");
        mainLabel.setFont(new Font(50));
        mainLabel.setTextFill(Color.GREEN);

        Label lowLabel = new Label("Already have account?");
        lowLabel.setTextFill(Color.GREEN);

        innerBox.getChildren().addAll(mainLabel, nameField, passwordField, continueButton, lowLabel, button);
        box.getChildren().add(innerBox);

        Scene scene = new Scene(box, 600, 500);
        scene.setFill(Color.AZURE);

        stage.setScene(scene);

        stage.show();
    }
    
}
