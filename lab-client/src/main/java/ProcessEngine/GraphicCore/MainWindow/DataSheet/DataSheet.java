package ProcessEngine.GraphicCore.MainWindow.DataSheet;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class DataSheet {

    public DataSheet() {}

    public GridPane getDataSheet() {
        GridPane sheet = new GridPane();
        sheet.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(1))));

        return sheet;
    }
    
}
