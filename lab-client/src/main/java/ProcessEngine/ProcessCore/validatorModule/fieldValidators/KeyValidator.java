package ProcessEngine.ProcessCore.validatorModule.fieldValidators;

import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.ProcessCore.validatorModule.Validator;

public class KeyValidator implements Validator {
    @Override
    public boolean validate(String line) {
        try {
            Long.parseLong(line);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String message() {
        return GraphicRun.localizator.getString("correct long") + GraphicRun.localizator.getString("can't be null");
    }
}
