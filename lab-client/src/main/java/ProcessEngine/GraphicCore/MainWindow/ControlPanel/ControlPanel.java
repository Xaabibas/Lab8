package ProcessEngine.GraphicCore.MainWindow.ControlPanel;

import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.CountByTypePopUpWindow.CountByTypePopUpWindow;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.InsertPopUpWindow.InsertPopUpWindow;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.RemoveGrPopUpWindow.RemoveGrPopUpWindow;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.RemoveKeyPopUpWindow.RemoveKeyPopUpWindow;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.RemoveLowKeyPopUpWindow.RemoveLowKeyPopUpWindow;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.RemoveLowPopUpWindow.RemoveLowPopUpWindow;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.SumOfPricePopUpWindow.SumOfPricePopUpWindow;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.UpdatePopUpWindow.UpdatePopUpWindow;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import ProcessEngine.DataCore.DataRun;
import network.Request;

import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControlPanel {

    protected DataRun dataRun;
    protected NetworkManager networkManager;
    protected String login;
    protected String password;
    
    public ControlPanel(DataRun dataRun, NetworkManager networkManager, String login, String password) {
        this.dataRun = dataRun;
        this.networkManager = networkManager;
        this.login = login;
        this.password = password;
    }

    public VBox getCommands() {
        VBox commands = new VBox();
        commands.setAlignment(Pos.CENTER);
        commands.setSpacing(17);
        commands.setPadding(new Insets(30, 30, 30, 30));

        Button update = ButtonFactory.getCommandButton("update");
        update.setOnAction(event -> {
            Stage stage = UpdatePopUpWindow.updateWindow(networkManager, login, password);
            stage.show();
        });

        Button insert = ButtonFactory.getCommandButton("insert");
        insert.setOnAction(event -> {
            Stage stage = InsertPopUpWindow.insertWindow(networkManager, login, password);
            stage.show();
        });

        HBox first = BoxFactory.getBoxWithButtons(update, insert);

        Button removeGreater = ButtonFactory.getCommandButton("remove greater");
        removeGreater.setOnAction(event -> {
            Stage stage = RemoveGrPopUpWindow.removeGreaterWindow();

            stage.show();
        });

        Button removeLower = ButtonFactory.getCommandButton("remove lower");
        removeLower.setOnAction(event -> {
            Stage stage = RemoveLowPopUpWindow.removeLowerWindow();

            stage.show();
        });

        HBox second = BoxFactory.getBoxWithButtons(removeGreater, removeLower);
        second.setPadding(new Insets(17, 0, 0, 0));

        Button removeLowerKey = ButtonFactory.getCommandButton("remove lower by key");
        removeLowerKey.setOnAction(event -> {
            Stage stage = RemoveLowKeyPopUpWindow.removeLowerKeyWindow();

            stage.show();
        });

        Button removeKey = ButtonFactory.getCommandButton("remove by key");
        removeKey.setOnAction(event -> {
            Stage stage = RemoveKeyPopUpWindow.removeKeyWindow(networkManager, login, password);

            stage.show();
        });

        HBox third = BoxFactory.getBoxWithButtons(removeLowerKey, removeKey);
        third.setPadding(new Insets(0, 0, 17, 0));

        Button sum = ButtonFactory.getCommandButton("sum of price");
        sum.setOnAction(event -> {
            Stage stage = SumOfPricePopUpWindow.sumWindow(dataRun.getCollectionData());

            stage.show();
        });

        Button count = ButtonFactory.getCommandButton("count by type");
        count.setOnAction(event -> {
            Stage stage = CountByTypePopUpWindow.countWindow(dataRun.getCollectionData());

            stage.show();
        });

        HBox fourth = BoxFactory.getBoxWithButtons(sum, count);

        Button printAscending = ButtonFactory.getCommandButton("print ascending"); // Написать setOnAction
        Button clear = ButtonFactory.getCommandButton("clear");
        HBox fifth = BoxFactory.getBoxWithButtons(printAscending, clear);
        clear.setOnAction(event -> {
            Request request = new Request();
            request.setUser(login);
            request.setPassword(Arrays.toString(password
                .chars()
                .mapToObj(c -> String.valueOf((char) c))
                .toArray(String[]::new))
            );
            request.setCommandName("clear");
            request.setTokens("clear");

            networkManager.sendAndReceive(request);
        });

        commands.getChildren().addAll(first, second, third, fourth, fifth);

        return commands;
    }
    
}
