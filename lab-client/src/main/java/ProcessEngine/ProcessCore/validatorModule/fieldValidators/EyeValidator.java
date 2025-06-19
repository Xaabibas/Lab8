package ProcessEngine.ProcessCore.validatorModule.fieldValidators;

import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.ProcessCore.validatorModule.Validator;
import moduls.EyeColor;

import java.util.Arrays;

public class EyeValidator implements Validator {
    @Override
    public boolean validate(String line) {
        try {
            if (line.isEmpty()) {
                return true;
            }

            EyeColor.valueOf(line);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] Введите корректное значение");
        }
        return false;
    }

    @Override
    public String message() {
        StringBuilder message = new StringBuilder();
        message.append(GraphicRun.localizator.getString("correct values"));
        Arrays.stream(EyeColor.values()).forEach(a -> message.append(a).append(" "));
        message.append(GraphicRun.localizator.getString("can be null"));
        return message.toString();
    }
}
