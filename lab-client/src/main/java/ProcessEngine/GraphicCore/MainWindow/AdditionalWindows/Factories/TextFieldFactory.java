package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories;

import ProcessEngine.ProcessCore.validatorModule.Validator;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class TextFieldFactory {

        public static TextField getFieldWithValidator(String text, Validator validator) {
            TextField field = new TextField();
            field.setPromptText(text);
            field.setPrefWidth(180);
            Tooltip tooltip = new Tooltip(validator.message());
            tooltip.setFont(new Font(10));
            tooltip.setShowDelay(new Duration(2));
            field.setTooltip(tooltip);

            field.focusedProperty().addListener(
                    (arg0, oldValue, newValue) -> {
                        if (!newValue) {
                            if (!validator.validate(field.getText())) {
                                field.setText("");
                            }
                        }
                    }
            );

            return field;
        }

    }
