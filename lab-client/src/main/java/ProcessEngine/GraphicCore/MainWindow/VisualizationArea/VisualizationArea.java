package ProcessEngine.GraphicCore.MainWindow.VisualizationArea;

import ProcessEngine.DataCore.DataRun;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.RemoveKeyPopUpWindow.RemoveKeyPopUpWindow;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.UpdatePopUpWindow.UpdatePopUpWindow;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import javafx.animation.ScaleTransition;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import moduls.Coordinates;
import moduls.Ticket;

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
            Ticket ticket = new Ticket();
            ticket.setPrice(Float.parseFloat(i[6]));
            ticket.setCoordinates(new Coordinates(Float.parseFloat(i[3]), Long.parseLong(i[4])));

            float size = ticket.getPrice() / 1000;
            double x = (ticket.getCoordinates().getX());
            double y = (ticket.getCoordinates().getY());

            Circle circle = new Circle();
            circle.setCenterX(x);
            circle.setCenterY(y);
            circle.setRadius(size);

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
            Ticket ticket = new Ticket();
            ticket.setPrice(Float.parseFloat(i[6]));
            ticket.setCoordinates(new Coordinates(Float.parseFloat(i[3]), Long.parseLong(i[4])));

            double size = 2 * Math.log(ticket.getPrice());
            double x = ticket.getCoordinates().getX() % width;
            double y = (ticket.getCoordinates().getY()) % width;

            Circle circle = new Circle();
            circle.setCenterX(x);
            circle.setCenterY(y);
            circle.setRadius(size);

            circle.setOnMouseClicked(
                    event -> {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            ContextMenu menu = new ContextMenu();
                            MenuItem update = new MenuItem("update");
                            update.setOnAction(
                                    event1 -> {
                                        Stage stage = UpdatePopUpWindow.secondWindow(networkManager, login, password, i, Long.parseLong(i[0]));
                                        stage.show();
                                        // Анимация???
                                    }
                            );
                            MenuItem remove = new MenuItem("remove");
                            remove.setOnAction(
                                    event1 -> {
                                        Stage stage = RemoveKeyPopUpWindow.removeKeyWithTextWindow(networkManager, login, password, i[0]);
                                        ScaleTransition exit = new ScaleTransition(Duration.millis(3000), circle);
                                        exit.setFromX(1);
                                        exit.setToX(0);
                                        exit.setFromY(1); // Доработать анимацию
                                        exit.setToY(0);
                                        exit.play();
                                        stage.show();
                                    }
                            );
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
