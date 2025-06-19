package ProcessEngine.ProcessCore.validatorModule.fieldValidators;

import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.ProcessCore.validatorModule.ValidationException;
import ProcessEngine.ProcessCore.validatorModule.Validator;

public class PriceValidator implements Validator {
    @Override
    public boolean validate(String line) {
        try {
            if (Float.parseFloat(line) > 0) {
                return true;
            }
            throw new ValidationException("[ERROR] Число должно быть больше 0");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Значение должно быть числом");
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    @Override
    public String message() {
        return GraphicRun.localizator.getString("correct price") + GraphicRun.localizator.getString("can't be null");
    }
}
