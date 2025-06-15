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
import ProcessEngine.DataCore.DataRun;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControlPanel {

    protected DataRun dataRun;
    
    public ControlPanel(DataRun dataRun) {
        this.dataRun = dataRun;
    }

    public VBox getCommands() {
        VBox commands = new VBox();
        commands.setAlignment(Pos.CENTER);
        commands.setSpacing(17);
        commands.setPadding(new Insets(30, 30, 30, 30));

        Button update = ButtonFactory.getCommandButton("update"); // Написать setOnAction
        update.setOnAction(event -> {
            Stage stage = UpdatePopUpWindow.updateWindow();

            stage.show();
        });

        Button insert = ButtonFactory.getCommandButton("insert"); // Написать setOnAction
        insert.setOnAction(event -> {
            Stage stage = InsertPopUpWindow.insertWindow();

            stage.show();
        });

        HBox first = BoxFactory.getBoxWithButtons(update, insert);

        Button removeGreater = ButtonFactory.getCommandButton("remove greater"); // Написать setOnAction
        removeGreater.setOnAction(event -> {
            Stage stage = RemoveGrPopUpWindow.removeGreaterWindow();

            stage.show();
        });

        Button removeLower = ButtonFactory.getCommandButton("remove lower"); // Написать setOnAction
        removeLower.setOnAction(event -> {
            Stage stage = RemoveLowPopUpWindow.removeLowerWindow();

            stage.show();
        });

        HBox second = BoxFactory.getBoxWithButtons(removeGreater, removeLower);
        second.setPadding(new Insets(17, 0, 0, 0));

        Button removeLowerKey = ButtonFactory.getCommandButton("remove lower by key"); // Написать setOnAction
        removeLowerKey.setOnAction(event -> {
            Stage stage = RemoveLowKeyPopUpWindow.removeLowerKeyWindow();

            stage.show();
        });

        Button removeKey = ButtonFactory.getCommandButton("remove by key"); // Написать setOnAction
        removeKey.setOnAction(event -> {
            Stage stage = RemoveKeyPopUpWindow.removeKeyWindow();

            stage.show();
        });

        HBox third = BoxFactory.getBoxWithButtons(removeLowerKey, removeKey);
        third.setPadding(new Insets(0, 0, 17, 0));

        Button sum = ButtonFactory.getCommandButton("sum of price"); // Написать setOnAction
        sum.setOnAction(event -> {
            Stage stage = SumOfPricePopUpWindow.sumWindow(dataRun.getCollectionData());

            stage.show();
        });

        Button count = ButtonFactory.getCommandButton("count by type"); // Написать setOnAction
        count.setOnAction(event -> {
            Stage stage = CountByTypePopUpWindow.countWindow();

            stage.show();
        });

        HBox fourth = BoxFactory.getBoxWithButtons(sum, count);

        Button printAscending = ButtonFactory.getCommandButton("print ascending"); // Написать setOnAction
        Button clear = ButtonFactory.getCommandButton("clear"); // Написать setOnAction
        HBox fifth = BoxFactory.getBoxWithButtons(printAscending, clear);

        commands.getChildren().addAll(first, second, third, fourth, fifth);

        return commands;
    }
    
}
