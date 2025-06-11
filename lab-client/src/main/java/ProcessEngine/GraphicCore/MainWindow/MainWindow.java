package ProcessEngine.GraphicCore.MainWindow;

import ProcessEngine.GraphicCore.MainWindow.ControlPanel.ControlPanel;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.DataSheet;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.FontWeight;
import javafx.application.Platform;

public class MainWindow {

    protected String login;

    public MainWindow(String login) {
        this.login = login;
    }    

    public void window(Stage stage) {
        BorderPane root = new BorderPane();

        root.setBackground(new Background(new BackgroundFill(Color.MINTCREAM, new CornerRadii(5), Insets.EMPTY)));

        ControlPanel controlPanel = new ControlPanel();

        HBox upLine = upLine();

        DataSheet dataSheet = new DataSheet();

        root.setTop(upLine);
        root.setLeft(controlPanel.getCommands());
        root.setCenter(dataSheet.getDataSheet());

        Scene scene = new Scene(root, 1000, 700);

        stage.setScene(scene);
        stage.show();
    }

    private HBox upLine() {
        HBox upLine = new HBox();
        upLine.setSpacing(30);
        upLine.setPadding(new Insets(1, 15, 1, 0));
        upLine.setAlignment(Pos.CENTER_RIGHT);
        upLine.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(0), Insets.EMPTY)));

        Button language = new Button("language"); // setOnAction -> Возможность смены языка (выезжающим списком)
        language.setFont(new Font(14));
        Label name = new Label(login);
        name.setFont(Font.font("System", FontWeight.BOLD, 15));
        name.setTextFill(Color.GREEN);
        Hyperlink logOut = new Hyperlink("log out"); // setOnAction Выход в стартовое окно
        logOut.setTextFill(Color.RED);
        logOut.setFont(Font.font("System", FontWeight.BOLD, 16));
        logOut.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        upLine.getChildren().addAll(language, name, logOut);

        return upLine;
    }

    
}