package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories;

import javafx.scene.control.Button;

public class ButtonFactory {

    public static Button getCommandButton(String name) {
        Button button = new Button(name);
        button.setPrefWidth(95);

        return button;
    }

    public static Button getCommitButton() {
        Button button = new Button("Commit");
        button.setPrefWidth(90);

        return button;
    }

}
