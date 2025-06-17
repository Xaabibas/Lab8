package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.SumOfPricePopUpWindow;

import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.DataCore.DataRun;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Vector;

public class SumOfPricePopUpWindow {

    public static Stage sumWindow(DataRun dataRun) {
        Stage stage = new Stage();

        Label mainLabel = LabelFactory.getMainLabel(countSumOfPrice(dataRun.getCollectionData()));
        mainLabel.setTextFill(Color.MEDIUMBLUE);
        mainLabel.setFont(Font.font("System", FontWeight.BOLD, 30));

        VBox box = BoxFactory.getPopUpBox(mainLabel);

        stage.setScene(new Scene(box, 800, 600));

        return stage;
    }

    public static String countSumOfPrice(Vector<String[]> collectionData) {
        double sum = collectionData.stream()
            .mapToDouble(item -> Double.parseDouble(item[6]))
            .sum();
        return GraphicRun.localizator.getString("sum") + " " + sum;
    }

}
