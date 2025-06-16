package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.SumOfPricePopUpWindow;

import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;

import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import network.Request;

import java.util.Arrays;
import java.util.Vector;

public class SumOfPricePopUpWindow {

    public static Stage sumWindow(NetworkManager networkManager, String login, String password) {
        Stage stage = new Stage();

        Label mainLabel = LabelFactory.getMainLabel(countSumOfPrice(networkManager, login, password));
        mainLabel.setTextFill(Color.AZURE);
        mainLabel.setFont(Font.font("System", FontWeight.BOLD, 48));

        VBox box = BoxFactory.getPopUpBox(mainLabel);

        stage.setScene(new Scene(box, 800, 600));

        return stage;
    }

    public static String countSumOfPrice(NetworkManager networkManager, String login, String password) {
        Request request = new Request();
        request.setUser(login);
        request.setPassword(Arrays.toString(password
                .chars()
                .mapToObj(c -> String.valueOf((char) c))
                .toArray(String[]::new))
        );
        request.setCommandName("sum_of_price");
        request.setTokens("sum_of_price");

        return networkManager.sendAndReceive(request);
    }

}
