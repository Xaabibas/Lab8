package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.SumOfPricePopUpWindow;

import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SumOfPricePopUpWindow {

    public static Stage sumWindow() {
        Stage stage = new Stage();
        // Необходимо сделать запрос/подсчет и вывести
        Label mainLabel = LabelFactory.getMainLabel("Sum of price");

        VBox box = BoxFactory.getPopUpBox(mainLabel);

        stage.setScene(new Scene(box, 500, 600));

        return stage;
    }

}
