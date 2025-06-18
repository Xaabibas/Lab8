package ProcessEngine.GraphicCore.MainWindow.VisualizationArea;

import ProcessEngine.DataCore.DataRun;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.UpdatePopUpWindow.UpdatePopUpWindow;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import javafx.animation.ScaleTransition;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import moduls.Coordinates;
import moduls.Ticket;
import network.Request;

import java.util.Arrays;

public class VisualizationArea {

    protected DataRun dataRun;
    private final ScrollPane scrollPane;
    private final Pane root;
    private final int width = 10_000;
    private final NetworkManager networkManager;
    private final String login;
    private final String password;


    public VisualizationArea(DataRun dataRun, NetworkManager networkManager, String login, String password) {
        this.dataRun = dataRun;
        this.scrollPane = new ScrollPane();
        scrollPane.setPannable(true);
        this.root = new Pane();
        root.setPrefSize(width, width);
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
        scrollPane.setContent(root);
        this.networkManager = networkManager;
        this.password = password;
        this.login = login;
    }

    public ScrollPane startPane(Stage owner) {
        for (String[] i : dataRun.getCollectionData()) {
            double size = 2 * Math.log(Float.parseFloat(i[6]));
            double x = Float.parseFloat(i[3]) % width;
            double y = Long.parseLong(i[4]) % width;

            Circle circle = new Circle();
            circle.setCenterX(x);
            circle.setCenterY(y);
            circle.setRadius(size);

            if (i[12].equals(login)) {
                circle.setFill(Color.GREEN);
            } else {
                circle.setFill(Color.RED);
            }

            ScaleTransition scale = new ScaleTransition(Duration.millis(3000), circle);
            scale.setFromX(0.2);
            scale.setToX(1);
            scale.setFromY(0.2);
            scale.setToY(1);

            scale.play();

            root.getChildren().add(circle);
        }

        autoUpdating(owner);

        return scrollPane;
    }

    private void updatePane(Stage owner) {
        root.getChildren().clear();
        for (String[] i : dataRun.getCollectionData()) {
            double size = 2 * Math.log(Float.parseFloat(i[6]));
            double x = Float.parseFloat(i[3]) % width;
            double y = Long.parseLong(i[4]) % width;

            Circle circle = new Circle();
            circle.setCenterX(x);
            circle.setCenterY(y);
            circle.setRadius(size);
            if (login.equals(i[12])) {
                circle.setFill(Color.GREEN);
            } else {
                circle.setFill(Color.RED);
            }

            circle.setOnMouseClicked(
                    event -> {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            ContextMenu menu = new ContextMenu();
                            MenuItem update = getUpdate(i);
                            MenuItem remove = getRemove(i, circle);
                            menu.getItems().addAll(update, remove);
                            menu.setX(event.getScreenX());
                            menu.setY(event.getScreenY());

                            menu.show(owner);
                        }
                    }
            );

            ScaleTransition scale = new ScaleTransition(Duration.millis(3000), circle);
            scale.setFromX(0.2);
            scale.setToX(1);
            scale.setFromY(0.2);
            scale.setToY(1);
            scale.play();

            root.getChildren().add(circle);
        }
    }

    private MenuItem getRemove(String[] i, Circle circle) {
        MenuItem remove = new MenuItem("remove");
        remove.setOnAction(
                event1 -> {
                    //                    Stage stage = RemoveKeyPopUpWindow.removeKeyWithTextWindow(networkManager, login, password, i[0]);
                    Stage stage = new Stage();
                    VBox box = BoxFactory.getPopUpBox();
                    Label label = LabelFactory.getMainLabel("Удалить данный элемент? (ключ - " + i[0] + ")");
                    Button yes = ButtonFactory.getCommitButton();
                    Label error = LabelFactory.getErrorLabel("");
                    yes.setText("yes");

                    yes.setOnAction(
                            event2 -> {
                                Request request = new Request();
                                request.setUser(login);
                                request.setPassword(Arrays.toString(password
                                        .chars()
                                        .mapToObj(c -> String.valueOf((char) c))
                                        .toArray(String[]::new))
                                );
                                request.setCommandName("remove_key");
                                request.setTokens("remove_key " + i[0]);
                                String answer = networkManager.sendAndReceive(request);

                                if (answer.equals("Элемент успешно удален")) {
                                    ScaleTransition exit = new ScaleTransition(Duration.millis(3000), circle);
                                    exit.setFromX(1);
                                    exit.setToX(0);
                                    exit.setFromY(1); // Доработать анимацию
                                    exit.setToY(0);
                                    exit.play();
                                    error.setText("Элемент успешно удален");
                                    LabelFactory.toResultLabel(error);
                                } else if (answer.equals("[ERROR] Не достаточно прав для взаимодействия с данным элементом")) {
                                    error.setText("Нет прав доступа");
                                    LabelFactory.toErrorLabel(error);
                                } else {
                                    error.setText("Нет элемента с таким ключом");
                                    LabelFactory.toErrorLabel(error);
                                }
                            });

                    box.getChildren().addAll(label, yes, error);
                    stage.setScene(new Scene(box, 500, 300));

                    stage.show();
                }
        );
        return remove;
    }

    private MenuItem getUpdate(String[] i) {
        MenuItem update = new MenuItem("update");
        update.setOnAction(
                event1 -> {
                    Stage stage = UpdatePopUpWindow.secondWindow(networkManager, login, password, i, Long.parseLong(i[0]));
                    stage.show();
                    // Анимация???
                }
        );
        return update;
    }

    protected void autoUpdating(Stage owner) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled()) {
                    updatePane(owner);
                    Thread.sleep(3000);
                }
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
