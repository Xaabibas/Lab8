package ProcessEngine.GraphicCore.MainWindow.DataSheet;

import ProcessEngine.DataCore.DataRun;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.concurrent.Task;
import javafx.application.Platform;

public class DataSheet {

    protected DataRun dataRun; // dataRun.getCollectionData() - даст коллекцию (уже потокобезопасно)

    public DataSheet(DataRun dataRun) {
        this.dataRun = dataRun;
    }

    public GridPane getDataSheet() {
        GridPane sheet = new GridPane();
        sheet.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(1))));

        // startAutoParallelUpdateDataSheet(sheet);

        return sheet;
    }

    protected void startAutoParallelUpdateDataSheet(GridPane sheet) {
        Task<Void> updateTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled()) {
                    // запрашиваешь новые данные из dataRun
                    // че то обновляешь для графических элементов
                    Platform.runLater(() -> {
                        sheet.getChildren().clear();
                    });
                    Thread.sleep(3000);
                }
                return null;
            }
        };

        Thread newThread = new Thread(updateTask);
        newThread.setDaemon(true);
        newThread.start();
    }
    
}
