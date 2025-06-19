package ProcessEngine.ProcessCore.validatorModule.fieldValidators;

import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.ProcessCore.validatorModule.Validator;

public class YValidator implements Validator {
    @Override
    public boolean validate(String line) {
        try {
            Long.parseLong(line);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Значение должно быть числом типа Long");
        }
        return false;
    }

    @Override
    public String message() {
        return GraphicRun.localizator.getString("correct long") + GraphicRun.localizator.getString("can't be null");
    }
}
