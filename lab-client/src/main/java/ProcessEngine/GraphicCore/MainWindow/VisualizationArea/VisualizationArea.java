package ProcessEngine.GraphicCore.MainWindow.VisualizationArea;

import ProcessEngine.DataCore.DataRun;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.UpdatePopUpWindow.UpdatePopUpWindow;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import network.Request;

import javafx.animation.ScaleTransition;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class VisualizationArea {

    protected DataRun dataRun;
    private final ScrollPane scrollPane;
    private final Pane root;
    private final long width = 1200;
    private final NetworkManager networkManager;
    private final String login;
    private final String password;
    private final float a = 21341;
    private final float b = 5454;
    private final int cellSize = 50;
    private final HashMap<Long, Circle> circles = new HashMap<>();
    private final Pane background = drawGrid();

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
        root.getChildren().clear();
        circles.clear();
        root.getChildren().addAll(background);
        for (String[] i : dataRun.getCollectionData()) {
            Circle circle = makeCircle(i, owner);

            root.getChildren().addAll(circle);
            circles.put(Long.parseLong(i[0]), circle);
        }
        autoUpdating(owner);

        return scrollPane;
    }

    private void updatePane(Stage owner) {
        Set<Long> keys = new HashSet<>();
        for (String[] i : dataRun.getCollectionData()) {
            long key = Long.parseLong(i[0]);
            keys.add(key);
            if (circles.containsKey(key)) {
                Circle circle = circles.get(key);
                long x = Math.floorMod((long) Float.parseFloat(i[3]) + width / 2, width);
                long y = Math.floorMod(-Long.parseLong(i[4]) + width / 2, width);
                if (circle.getCenterX() != x || circle.getCenterY() != y) {
                    TranslateTransition lineTransition = new TranslateTransition(new Duration(1000), circle);
                    lineTransition.setToX(x - circle.getCenterX());
                    lineTransition.setToY(y - circle.getCenterY());
                    lineTransition.play();

                }
                double size = 2 * Math.log(Float.parseFloat(i[6]));
                if (size != circle.getRadius()) {
                    ScaleTransition scaleTransition = new ScaleTransition(new Duration(1000), circle);
                    if (size > circle.getRadius()){
                        scaleTransition.setFromX(circle.getRadius() / size);
                        scaleTransition.setFromY(circle.getRadius() / size);
                        scaleTransition.setToX(1);
                        scaleTransition.setToY(1);
                    } else {
                        scaleTransition.setFromX(1);
                        scaleTransition.setFromY(1);
                        scaleTransition.setToX(size / circle.getRadius());
                        scaleTransition.setToY(size / circle.getRadius());
                    }
                    scaleTransition.play();
                    circle.setRadius(size);
                }
                continue;
            }

            Circle circle = makeCircle(i, owner);

            root.getChildren().add(circle);
            circles.put(Long.parseLong(i[0]), circle);
        }
        Set<Long> removeSet = new HashSet<>();
        for (Long key : circles.keySet()) {
            if (!keys.contains(key)) {
                Circle circle = circles.get(key);
                ScaleTransition exit = new ScaleTransition(Duration.millis(3000), circle);
                exit.setFromX(1);
                exit.setToX(0);
                exit.setFromY(1);
                exit.setToY(0);
                exit.play();
                removeSet.add(key);
            }
        }
        removeSet.forEach(circles::remove);
    }

    private Circle makeCircle(String[] value, Stage owner) {
        double size = 2 * Math.log(Float.parseFloat(value[6]));
        long x = Math.floorMod((long) Float.parseFloat(value[3]) + width / 2, width);
        long y = Math.floorMod(-Long.parseLong(value[4]) + width / 2, width);

        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(size);

        circle.setOnMouseClicked(
                event -> {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        ContextMenu menu = new ContextMenu();
                        MenuItem update = getUpdate(value);
                        MenuItem remove = getRemove(value);
                        menu.getItems().addAll(update, remove);
                        menu.setX(event.getScreenX());
                        menu.setY(event.getScreenY());

                        menu.show(owner);
                    }
                }
        );

        if (value[12].equals(login)) {
            circle.setFill(Color.GREEN);
        } else {
            circle.setFill(new Color(Math.abs(value[12].hashCode()) % a / a, 0.1, Math.abs(value[12].hashCode()) % b / b, 1));
        }

        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);

        ScaleTransition scale = new ScaleTransition(Duration.millis(3000), circle);
        scale.setFromX(0.2);
        scale.setToX(1);
        scale.setFromY(0.2);
        scale.setToY(1);
        scale.play();

        return circle;
    }

    private Pane drawGrid() {
        Pane background = new Pane();
        background.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));

        for (int x = 0; x <= width; x += cellSize) {
            Line line = new Line(x, 0, x, width);
            line.setStroke(Color.LIGHTGRAY);
            line.setStrokeWidth(1);
            background.getChildren().add(line);
        }

        for (int y = 0; y <= width; y += cellSize) {
            Line line = new Line(0, y, width, y);
            line.setStroke(Color.LIGHTGRAY);
            line.setStrokeWidth(1);
            background.getChildren().add(line);
        }

        // Оси
        double center = width / 2.0;
        Line horizontalAxis = new Line(0, center, width, center);
        horizontalAxis.setStroke(Color.GRAY);
        horizontalAxis.setStrokeWidth(3);

        Line verticalAxis = new Line(center, 0, center, width);
        verticalAxis.setStroke(Color.GRAY);
        verticalAxis.setStrokeWidth(3);

        background.getChildren().addAll(horizontalAxis, verticalAxis);

        return background;
    }

    private MenuItem getRemove(String[] i) {
        MenuItem remove = new MenuItem("remove");
        remove.setOnAction(
                event1 -> {
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
                }
        );
        return update;
    }

    protected void autoUpdating(Stage owner) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled()) {
                    Platform.runLater(() -> updatePane(owner));
                    Thread.sleep(3000);
                }
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.setPriority(10);
        thread.start();
    }
}
