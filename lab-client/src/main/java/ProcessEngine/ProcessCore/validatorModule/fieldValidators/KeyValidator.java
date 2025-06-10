package ProcessEngine.ProcessCore.validatorModule.fieldValidators;

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
        return "Значение поля должно быть числом типа Long. Не может быть null";
    }
}
