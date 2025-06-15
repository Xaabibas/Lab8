package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.SumOfPricePopUpWindow;

import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.Vector;

public class SumOfPricePopUpWindow {

    public static Stage sumWindow(Vector<String[]> vectorStringCollection) {
        Stage stage = new Stage();

        Label mainLabel = LabelFactory.getMainLabel(countSumOfPrice(vectorStringCollection));
        mainLabel.setTextFill(Color.AZURE);
        mainLabel.setFont(Font.font("System", FontWeight.BOLD, 48));

        VBox box = BoxFactory.getPopUpBox(mainLabel);

        stage.setScene(new Scene(box, 500, 600));

        return stage;
    }

    public static String countSumOfPrice(Vector<String[]> vectorStringCollection) {
        double sumOfPrice = 0;
        for (int i = 0; i < vectorStringCollection.size(); ++i) {
            sumOfPrice += Double.parseDouble(vectorStringCollection.get(i)[6]);
        }
        return String.valueOf(sumOfPrice);
    }

}
