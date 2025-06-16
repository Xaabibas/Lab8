package ProcessEngine.GraphicCore.MainWindow.VisualizationArea;

import ProcessEngine.DataCore.DataRun;
import javafx.animation.ScaleTransition;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import moduls.Coordinates;
import moduls.Ticket;

public class VisualizationArea {

    protected DataRun dataRun;
    private final ScrollPane scrollPane;
    private final Pane root;


    public VisualizationArea(DataRun dataRun) {
        this.dataRun = dataRun;
        this.scrollPane = new ScrollPane();
        scrollPane.setPannable(true);
        this.root = new Pane();
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
        scrollPane.setContent(root);
    }

    public ScrollPane startPane() {
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

        autoUpdating();

        return scrollPane;
    }

    private void updatePane() {
        root.getChildren().clear();
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
    }

    protected void autoUpdating() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled()) {
                    updatePane();
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
