package ProcessEngine.GraphicCore.MainWindow;

import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.GraphicCore.MainWindow.ControlPanel.ControlPanel;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.DataSheet;
import ProcessEngine.GraphicCore.MainWindow.VisualizationArea.VisualizationArea;
import ProcessEngine.GraphicCore.SignWindow.SignUpWindow.SignUpWindow;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import ProcessEngine.DataCore.AuthCheck;
import ProcessEngine.DataCore.DataRun;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.FontWeight;

public class MainWindow {

    protected AuthCheck authCheckData;
    protected NetworkManager networkManager;
    protected Stage stage;
    protected DataRun dataRun;

    public MainWindow(AuthCheck authCheckData, NetworkManager networkManager, Stage stage) {
        this.authCheckData = authCheckData;
        this.networkManager = networkManager;
        this.stage = stage;
        dataRun = new DataRun(networkManager);
        dataRun.asyncAutoUpdateCollectionData(authCheckData.getLogin(), authCheckData.getPassword());
    }    

    public void window() {
        BorderPane root = new BorderPane();

        root.setBackground(new Background(new BackgroundFill(Color.MINTCREAM, new CornerRadii(5), Insets.EMPTY)));

        ControlPanel controlPanel = new ControlPanel(dataRun); // панель кнопок

        HBox upLine = upLine(); // верхняя панель

        DataSheet dataSheet = new DataSheet(dataRun); // таблица с данными

        VisualizationArea visualizationArea = new VisualizationArea(dataRun); // область визуализации

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

        Label name = new Label(authCheckData.getLogin());
        name.setFont(Font.font("System", FontWeight.BOLD, 15));
        name.setTextFill(Color.GREEN);

        Hyperlink logOut = new Hyperlink("log out"); // setOnAction Выход в стартовое окно
        logOut.setTextFill(Color.RED);
        logOut.setFont(Font.font("System", FontWeight.BOLD, 16));
        logOut.setOnAction(event -> {
            authCheckData.setAuthSeccess(false);
            new Thread(GraphicRun.runSpecialThreadTask(authCheckData, stage)).start();
            SignUpWindow.signUpWindow(stage, authCheckData);
        });

        upLine.getChildren().addAll(language, name, logOut);

        return upLine;
    }
    
}