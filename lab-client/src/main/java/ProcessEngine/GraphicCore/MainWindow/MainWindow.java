package ProcessEngine.GraphicCore.MainWindow;

import ProcessEngine.DataCore.AuthCheck;
import ProcessEngine.DataCore.DataRun;
import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.ControlPanel.ControlPanel;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.DataSheet;
import ProcessEngine.GraphicCore.MainWindow.VisualizationArea.VisualizationArea;
import ProcessEngine.GraphicCore.SignWindow.SignUpWindow.SignUpWindow;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class MainWindow {

    protected AuthCheck authCheckData;
    protected NetworkManager networkManager;
    protected Stage stage;
    protected DataRun dataRun;
    private BorderPane root;
    DataSheet dataSheet; // таблица с данными
    VisualizationArea visualizationArea; // область визуализации

    public MainWindow(AuthCheck authCheckData, NetworkManager networkManager, Stage stage) {
        this.authCheckData = authCheckData;
        this.networkManager = networkManager;
        this.stage = stage;
        dataRun = new DataRun(networkManager);
        dataRun.asyncAutoUpdateCollectionData(authCheckData.getLogin(), authCheckData.getPassword());
        dataSheet = new DataSheet(dataRun, networkManager, authCheckData.getLogin(), authCheckData.getPassword());
        visualizationArea = new VisualizationArea(dataRun, networkManager, authCheckData.getLogin(), authCheckData.getPassword());
    }

    public void window() {
        root = new BorderPane();

        root.setBackground(new Background(new BackgroundFill(Color.MINTCREAM, new CornerRadii(5), Insets.EMPTY)));

        ControlPanel controlPanel = new ControlPanel(dataRun, networkManager, authCheckData.getLogin(), authCheckData.getPassword()); // панель кнопок

        HBox upLine = upLine(); // верхняя панель
        HBox bottomLine = bottomLine();

        root.setTop(upLine);
        root.setLeft(controlPanel.getCommands());
        root.setCenter(dataSheet.getDataSheet(stage));
        root.setBottom(bottomLine);

        Scene scene = new Scene(root, 1400, 600);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    private HBox upLine() {
        HBox upLine = new HBox();
        upLine.setSpacing(30);
        upLine.setPadding(new Insets(1, 15, 1, 0));
        upLine.setAlignment(Pos.CENTER_RIGHT);
        upLine.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(0), Insets.EMPTY)));

        Button language = new Button(GraphicRun.localizator.getString("language")); // setOnAction -> Возможность смены языка (выезжающим списком)
        language.setFont(new Font(14));
        language.setOnAction(
                event -> {
                    Stage subStage = new Stage();

                    Button russian = ButtonFactory.getCommandButton(GraphicRun.localizator.getString("russian"));
                    russian.setOnAction(
                            event1 -> {
                                GraphicRun.localizator.setBundle(ResourceBundle.getBundle("resources.Resource", new Locale("ru")));
                                window();
                                subStage.close();
                            });

                    Button latish = ButtonFactory.getCommandButton(GraphicRun.localizator.getString("latish"));
                    latish.setOnAction(
                            event1 -> {
                                GraphicRun.localizator.setBundle(ResourceBundle.getBundle("resources.Resource", new Locale("lv")));
                                window();
                                subStage.close();
                            }
                    );

                    Button island = ButtonFactory.getCommandButton(GraphicRun.localizator.getString("island"));
                    island.setOnAction(
                            event1 -> {
                                GraphicRun.localizator.setBundle(ResourceBundle.getBundle("resources.Resource", new Locale("is")));
                                window();
                                subStage.close();
                            }
                    );

                    Button english = ButtonFactory.getCommandButton(GraphicRun.localizator.getString("english"));
                    english.setOnAction(
                            event1 -> {
                                GraphicRun.localizator.setBundle(ResourceBundle.getBundle("resources.Resource", new Locale("en")));
                                window();
                                subStage.close();
                            }
                    );

                    VBox box = BoxFactory.getPopUpBox();
                    box.getChildren().addAll(russian, latish, island, english);

                    subStage.setScene(new Scene(box, 400, 400));

                    subStage.show();
                }
        );

        Label name = new Label(authCheckData.getLogin());
        name.setFont(Font.font("System", FontWeight.BOLD, 15));
        name.setTextFill(Color.BLUE);

        Hyperlink logOut = new Hyperlink(GraphicRun.localizator.getString("log out")); // setOnAction Выход в стартовое окно
        logOut.setTextFill(Color.RED);
        logOut.setFont(Font.font("System", FontWeight.BOLD, 16));
        logOut.setOnAction(event -> {
            authCheckData.setAuthSuccess(false);
            new Thread(GraphicRun.runSpecialThreadTask(authCheckData, stage)).start();
            SignUpWindow.signUpWindow(stage, authCheckData);
        });

        upLine.getChildren().addAll(language, name, logOut);

        return upLine;
    }

    private HBox bottomLine() {
        Button tableButton = new Button(GraphicRun.localizator.getString("table view"));
        tableButton.setOnAction(
                event -> root.setCenter(dataSheet.getDataSheet(stage))
        );
        Button coordButton = new Button(GraphicRun.localizator.getString("visualization"));
        coordButton.setOnAction(
                event -> root.setCenter(visualizationArea.startPane(stage))
        );


        HBox bottomLine = new HBox();
        bottomLine.setSpacing(20);
        bottomLine.setPadding(new Insets(1, 15, 1, 0));
        bottomLine.setAlignment(Pos.CENTER_LEFT);
        bottomLine.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(0), Insets.EMPTY)));

        bottomLine.getChildren().addAll(tableButton, coordButton);
        return bottomLine;
    }

}