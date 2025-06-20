package ProcessEngine.ProcessCore.validatorModule.fieldValidators;

import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.ProcessCore.validatorModule.ValidationException;
import ProcessEngine.ProcessCore.validatorModule.Validator;

public class XValidator implements Validator {
    @Override
    public boolean validate(String line) {
        try {
            if (Float.parseFloat(line) > -626) {
                return true;
            }
            throw new ValidationException("[ERROR] Значение поля x должно быть больше -626");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Значение поля должно быть числом типа Float");
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public String message() {
        return GraphicRun.localizator.getString("correct x") + GraphicRun.localizator.getString("can't be null");
    }
}
