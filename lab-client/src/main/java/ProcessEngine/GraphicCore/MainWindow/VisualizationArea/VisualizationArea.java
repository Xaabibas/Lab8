package ProcessEngine.GraphicCore.MainWindow.VisualizationArea;

import ProcessEngine.DataCore.DataRun;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import moduls.Coordinates;
import moduls.Ticket;

public class VisualizationArea {

    protected DataRun dataRun;
    private GraphicsContext gc;

    public VisualizationArea(DataRun dataRun) {
        this.dataRun = dataRun;
    }

    public ScrollPane getVisualizationArea() {
        StackPane root = new StackPane();
        root.setPrefSize(1000, 1000);
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));

        Canvas canvas = new Canvas(2000, 2000);
        gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setPrefSize(300, 300);
        scrollPane.setMaxSize(Float.MAX_VALUE, Long.MAX_VALUE);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-focus-color: transparent;");


        for (String[] i : dataRun.getCollectionData()) {
            Ticket ticket = new Ticket();
            ticket.setPrice(Float.parseFloat(i[6]));
            ticket.setCoordinates(new Coordinates(Float.parseFloat(i[3]), Long.parseLong(i[4])));
            drawTicket(ticket);
        }


        return scrollPane;
    }

    public void drawTicket(Ticket ticket) {
        float size = ticket.getPrice();
        float x = ticket.getCoordinates().getX();
        long y = ticket.getCoordinates().getY();

        gc.setFill(Color.TOMATO);
        gc.fillOval(x, y, size / 500, size / 500);

    }
    
}
