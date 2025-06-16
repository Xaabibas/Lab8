package ProcessEngine.GraphicCore.MainWindow.VisualizationArea;

import ProcessEngine.DataCore.DataRun;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import moduls.Coordinates;
import moduls.Ticket;

public class VisualizationArea {

    protected DataRun dataRun;
    private final int size = 1000;


    public VisualizationArea(DataRun dataRun) {
        this.dataRun = dataRun;
    }

    public ScrollPane getVisualizationArea() {
        Pane root = new Pane();

        root.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));

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

            root.getChildren().add(circle);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPannable(true);

        scrollPane.setContent(root);

        return scrollPane;
    }
}
