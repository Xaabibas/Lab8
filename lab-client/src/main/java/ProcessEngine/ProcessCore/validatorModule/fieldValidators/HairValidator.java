package ProcessEngine.ProcessCore.validatorModule.fieldValidators;

import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.ProcessCore.validatorModule.Validator;
import moduls.HairColor;

import java.util.Arrays;

public class HairValidator implements Validator {
    @Override
    public boolean validate(String line) {
        try {
            if (line.isEmpty()) {
                return true;
            }

            HairColor.valueOf(line);
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
        Arrays.stream(HairColor.values()).forEach(a -> message.append(a.toString()).append(" "));
        message.append(GraphicRun.localizator.getString("can be null"));
        return message.toString();
    }
}
