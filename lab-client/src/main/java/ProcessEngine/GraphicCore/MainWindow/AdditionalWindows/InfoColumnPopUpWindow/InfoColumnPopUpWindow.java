package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.InfoColumnPopUpWindow;

import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InfoColumnPopUpWindow {
    public static Stage InfoWindow(String[] value) {
        VBox box = BoxFactory.getPopUpBox();
        box.getChildren().addAll(
                LabelFactory.getMainLabel(GraphicRun.localizator.getString("info")),
                BoxFactory.getBoxWithNodes(
                        BoxFactory.getTextBox(
                                LabelFactory.getUsualLabel(GraphicRun.localizator.getString("key") + ": "),
                                LabelFactory.getUsualLabel("id:"),
                                LabelFactory.getUsualLabel(GraphicRun.localizator.getString("name") + ": "),
                                LabelFactory.getUsualLabel("y: "),
                                LabelFactory.getUsualLabel("x: "),
                                LabelFactory.getUsualLabel(GraphicRun.localizator.getString("creation date") + ": "),
                                LabelFactory.getUsualLabel(GraphicRun.localizator.getString("price") + ": "),
                                LabelFactory.getUsualLabel(GraphicRun.localizator.getString("type") + ": "),
                                LabelFactory.getUsualLabel(GraphicRun.localizator.getString("birthday") + ": "),
                                LabelFactory.getUsualLabel(GraphicRun.localizator.getString("eye color") + ": "),
                                LabelFactory.getUsualLabel(GraphicRun.localizator.getString("hair color") + ": "),
                                LabelFactory.getUsualLabel(GraphicRun.localizator.getString("country") + ": "),
                                LabelFactory.getUsualLabel(GraphicRun.localizator.getString("owner") + ": ")
                                ),
                        BoxFactory.getTextBox(
                                LabelFactory.getAdditionalLabel(value[0]),
                                LabelFactory.getAdditionalLabel(value[1]),
                                LabelFactory.getAdditionalLabel(value[2]),
                                LabelFactory.getAdditionalLabel(value[3]),
                                LabelFactory.getAdditionalLabel(value[4]),
                                LabelFactory.getAdditionalLabel(GraphicRun.localizator.getDate(value[5])),
                                LabelFactory.getAdditionalLabel(value[6]),
                                LabelFactory.getAdditionalLabel(value[7]),
                                LabelFactory.getAdditionalLabel(value[8]),
                                LabelFactory.getAdditionalLabel(value[9]),
                                LabelFactory.getAdditionalLabel(value[10]),
                                LabelFactory.getAdditionalLabel(value[11]),
                                LabelFactory.getAdditionalLabel(value[12])

                        )

                )
        );



        Stage stage = new Stage();
        stage.setScene(new Scene(box, 500, 600));

        return stage;
    }
}
